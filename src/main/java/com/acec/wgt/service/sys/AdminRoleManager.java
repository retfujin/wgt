package com.acec.wgt.service.sys;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.sys.SysModelEO;
import com.acec.wgt.models.sys.SysRoleEO;






//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AdminRoleManager  {
	
	private SysRoleEO entity;
	
	private HibernateDao<SysRoleEO, Integer> roleDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		roleDao = new HibernateDao<SysRoleEO, Integer>(sessionFactory, SysRoleEO.class);
	}
	public List getModelForRole(String id){
		entity = roleDao.get(Integer.valueOf(id));
		
		List<SysModelEO> ls = roleDao.find("from SysModelEO ORDER BY id");

		for (SysModelEO se : ls) {
			for (SysModelEO se1 : entity.getModels())
				if (se1.getId().intValue() == se.getId().intValue()) {
					se.setIsZhong(1);
					break;
				}

		}
		return ls;
		
	
	}
	public Page getAllRole(Page page) {
		return roleDao.getAll(page);
		
	}
	public List getAllModel(){
		return roleDao.find("from SysModelEO");
	}
	
	public SysRoleEO getEntity() {
		return entity;
	}
	
	
	public List findUserForRoleid(Integer id) {
		return roleDao.find("from SysUserEO where role_id=?",id);
	}
	public void delRole(int i) {
		roleDao.delete(i);
		
	}
	public void save(SysRoleEO entity2) {
		roleDao.save(entity2);
		
	}
	
	public SysRoleEO getRole(Integer id){
		return roleDao.get(id);
	}
	/**
	 * 检查角色名是否重复
	 * @param name 角色名
	 * @param companyId 公司Id
	 * @return
	 */
	public List checkRepeat(String name,String companyId) {
		return roleDao.find("from SysRoleEO u where u.name=? and u.companyId=?",name,companyId);
		
	}
	
	
	
}
