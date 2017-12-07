package com.acec.wgt.service.chargemanger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.chargemanager.AllmeterEO;
import com.acec.wgt.service.basedata.AreaManager;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AllmeterManager {

	private final Logger logger = LoggerFactory.getLogger(AllmeterManager.class);

	private HibernateDao<AllmeterEO, Integer> managerDao;	

	@Autowired
	private AreaManager areaManager;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		managerDao = new HibernateDao<AllmeterEO, Integer>(sessionFactory, AllmeterEO.class);
	}

	/**
	 * 权限下 所有总表 分页
	 * @param page
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<AllmeterEO> getListByPage(Page<AllmeterEO> page,String where) {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from AllmeterEO where areaId in ("+areaIds+") "+where+" order by meterCode";
		return managerDao.find(page, hql, null);
	}

	/**
	 * 权限下 所有总表
	 * @param page
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List getAll() {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from AllmeterEO where state='使用' and area.id in ("+areaIds+")";
		
		return managerDao.find(hql);
	}

	/**
	 * 新增或保存总表
	 * @param allmeter
	 */
	public void saveOrUpdateAllmeter(AllmeterEO allmeter) {
		Map map = getArea();
		allmeter.setAreaName(map.get(allmeter.getAreaId()).toString());
		managerDao.save(allmeter);
	}
	
	public void deleteAllmeterById(int id) {
		managerDao.createQuery("update AllmeterEO set state='停用' where id="+id).executeUpdate();
	}
	
	
	/**
	 * 根据id取总表
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public AllmeterEO getAllmeterById(int id) {
		// TODO Auto-generated method stub
		return managerDao.get(id);
	}

	/**
	 *  取已经停用的总表
	 * @param page
	 * @return
	 */

	public Page getStopMeter(Page page) {
		// TODO Auto-generated method stub
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from AllmeterEO where state='停用' and area.id in ("+areaIds+")";
		
		return managerDao.find(page, hql, null);
	}
	
	
	/**
	 * 取当前小区的所有正在使用的总表
	 */
	public List getUerMenter(int areaId) {
		
		return managerDao.find("from AllmeterEO where state='使用' and areaId= ?",areaId);
	}

	/**
	 * 总表更换历史报表
	 * @param page
	 * @param areaId
	 * @param meterType
	 * @return
	 */
	public Page getOldMeter(Page page, int areaId, String meterType) {

		StringBuffer where = new StringBuffer(" and state='停用'");
		if(areaId >0 )
			where.append(" and areaId="+areaId);
		if(!"".equals(meterType))
			where.append(" and meterType='"+meterType+"'");
		return getListByPage(page, where.toString());
	}
	
	public Map getArea(){
		Map map=new HashMap();
		List<AreaEO> list=areaManager.getAreaALL();
		for(AreaEO area:list){
			map.put(area.getId(), area.getAreaName());
		}
		return map;
	}
}