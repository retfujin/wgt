package com.acec.wgt.service.sys;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.administration.EmployeeDAO;
import com.acec.wgt.models.baseData.AreaDAO;
import com.acec.wgt.models.baseData.AreaEO;

import com.acec.wgt.models.sys.RoleDAO;
import com.acec.wgt.models.sys.SysModelEO;
import com.acec.wgt.models.sys.SysRoleEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.opensymphony.xwork2.ActionContext;



//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AdminUserManager  {
	
	
	private HibernateDao<SysUserEO, Integer> userDao;
	
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private AreaDAO areaDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		userDao = new HibernateDao<SysUserEO, Integer>(sessionFactory, SysUserEO.class);
	}
	/**
	 * 登录后通过权限取树状菜单
	 * @param map 是session的集合
	 * @return 排过续的权限模块列表
	 */
	public List loginTrees(Map map,String pid){
		Integer roleId = (Integer)map.get("roleId");
		List<SysModelEO> ls;
		//是系统管理员
		if(((String)map.get("adminType")).equals("0")){
			ls = userDao.find("from SysModelEO where grade<3");
		}else
			ls = userDao.find("select r.models from SysRoleEO r where r.id=?",roleId);
		
		Iterator<SysModelEO> iter = ls.iterator();
		String modelIds = "";
		while(iter.hasNext()){
			SysModelEO m = iter.next();
			modelIds+=","+m.getId();
			if(m.getGrade()>=3)
				iter.remove();
			
			//只取所属摸个打模块的
			if(pid!=null&&!pid.equals("")){
				if(!m.getId().toString().startsWith(pid)){
					iter.remove();
				}
			}
			
			
		}
		//将授权的模块id的字符串保存到session中
		map.put("modelIds", modelIds);
		
		Collections.sort(ls);
		
		return ls;
	}
	
	
	public SysUserEO checkUser(String userName, String password) {
		SysUserEO se=null;
		List ls = userDao.find("from SysUserEO u where u.userName=? and u.password=?",userName,password);
		if(ls!=null&&!ls.isEmpty()){
			se = (SysUserEO)ls.get(0);
		}
		return se;
	}
	
	/**
	 * 外边调用，初始化物业公司的系统管理员账号
	 * @param companyId
	 * @param companyName
	 * @param userName
	 * @param password
	 */
	public void saveAdminUser(String companyId,String companyName,String userName,String password){}
		
	public Page findUserAll(Page page) {
		return userDao.getAll(page);
	}
	
	public List getUserList(){
		return userDao.getAll();
	}


	public void saveUser(SysUserEO entity) {
		Map map = ActionContext.getContext().getSession();
	    String companyid =(String)map.get("companyId");
	    String companyName = (String)map.get("companyName");
		entity.setAdminType("1");
		entity.setCompanyId(companyid);
		entity.setCompanyName(companyName);
		userDao.save(entity);
		
		//将关联的登录用户信息，更新到员工表中
//		
//		if(entity.getEmployeeId()!=null){
//			employeeDAO.updateUserId(entity.getEmployeeId(), entity.getId(), entity.getUserName());
//			
//		}
		
		
	}
	
	public void updatePass(String newPwd,int id) {
		userDao.createQuery("update SysUserEO set password=? where id=?",newPwd,id).executeUpdate();
	}
	
	
	public List getAllRole(String companyId) {
		return roleDao.getAll(companyId);
	}
	public boolean checkRepeat(String userName) {
		List ls =  userDao.find("from SysUserEO u where  u.userName=?",userName);
		if(!ls.isEmpty())
			return true;
		else
			return false;
	}
	public void delUser(int id) {
		userDao.delete(id);
		
	}
	public SysUserEO getUser(Integer id) {
		return userDao.get(id);
		
	}
	@SuppressWarnings("unchecked")
	public String findAllAreaId(String companyId) {
		List<AreaEO> lsArea  =  areaDAO.findAllAreaByCompany(companyId);	
		String areaIds="";
		for(int i=0;i<lsArea.size();i++){	
			areaIds+=lsArea.get(i).getId()+",";
		}
		if(areaIds.length()>1)
			return areaIds.substring(0,areaIds.length()-1);
		
		//如果一个小区都没有加个0编号的小区，这样不会报错
		return "0";
	}
	
	
	
}
