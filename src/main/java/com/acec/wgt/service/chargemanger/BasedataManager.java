package com.acec.wgt.service.chargemanger;


import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BasedataManager {

	private final Logger logger = LoggerFactory.getLogger(BasedataManager.class);

	private HibernateDao<ChargeBasedataEO, Integer> basedataDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		basedataDao = new HibernateDao<ChargeBasedataEO, Integer>(sessionFactory, ChargeBasedataEO.class);
	}
	
	/**
	 * 取分页显示所有正在使用的收费项目收费项目
	 * @param page
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page getListByPage(Page page,Integer areaId,String areaIds) {
		
		
		String where="";
		if(areaId !=null && !areaId.equals("") && areaId > 0)
			where =" and area.id="+areaId;
		
		String hql = "from ChargeBasedataEO where isUser='使用' "+where+" and area.id in ("+areaIds+") order by chargeName";
		
		return basedataDao.find(page, hql, null);
	}

	
	/**
	 * 根据id取收费项目
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public ChargeBasedataEO getChargeBasedataById(int id) {
		return basedataDao.get(id);
	}

	/**
	 * 保存或更新收费项目 id为null为新增
	 * @param chargeBasedata
	 */
	public void saveOrUpdateChargeBasedata(ChargeBasedataEO chargeBasedata) {
		basedataDao.save(chargeBasedata);		
	}

	/**
	 * 逻辑删除
	 * @param id
	 */
	public void deleteChargeBasedataById(int id) {

		basedataDao.createQuery("update ChargeBasedataEO set isUser='停用',endTime=now() where id="+id).executeUpdate();
	}

	
	/**
	 * 根据小区id取 正在使用的chargeBasedataEO
	 * 去除车位综合服务费，车位租赁费
	 * @return list
	 */
	@Transactional(readOnly = true)
	public List getAllByAreaId(String areaId) {
		
		String hql = "from ChargeBasedataEO where isUser='使用' and area.id ="+areaId+" order by chargeName";
		return basedataDao.find(hql);
	}
	/**
	 * 根据小区id和收费类型(多个，号分隔)
	 * @return list<object[1]> 收费名
	 */
	@Transactional(readOnly = true)
	public List getChargeNameAll(String areaId,String chargeTypes) {
		String hql = "select distinct chargeName from ChargeBasedataEO where isUser='使用' and area.id =? and chargeType in("+chargeTypes+")  order by chargeName";
		return basedataDao.find(hql,Integer.parseInt(areaId));
	}
	/**
	 * 根据小区id和收费类型(多个，号分隔)
	 * @return list<object[1]> 收费名
	 */
	@Transactional(readOnly = true)
	public List getChargeIdAll(String areaId,String chargeTypes) {
		String hql = "from ChargeBasedataEO where isUser='使用' and area.id =? and chargeType in("+chargeTypes+")";
		return basedataDao.find(hql,Integer.parseInt(areaId));
	}
	
	/**
	 * @return list<object[1]> 收费名
	 */
	@Transactional(readOnly = true)
	public List getChargeIdAll(String chargeTypes) {
		String hql = "from ChargeBasedataEO where isUser='使用' and chargeType in("+chargeTypes+")";
		return basedataDao.find(hql);
	}
	
	public List getChargeName(){
		String areaIds = (String)Struts2Utils.getSession().getAttribute("areaIds");
		return basedataDao.find("from ChargeBasedataEO where isUser='使用' and areaId in ("+areaIds+") ");
	}

	/**
	 * 收费编号是否重复
	 * 有该id返回false  没有返回true
	 * @param id
	 * @return 
	 */
	public boolean isNotChargeId(Integer id) {

		List l = basedataDao.find("select count(*) from ChargeBasedataEO where isUser='使用' and id=?",id);
		if(l.get(0).toString().equals("0"))
			return true;
		else
			return false;
	}
	
	
	public List getCarBasedata(String areaId) {
		return basedataDao.find("from ChargeBasedataEO where chargeType='车位类' and areaId="+areaId);
	}
	
	/**
	 * 去车位费  
	 * @param areaId
	 * @return
	 */
	public List getCarCharge1(String areaId,String type) 
	{
		if(type.equals("1"))
			return basedataDao.find("from ChargeBasedataEO where id in('"+areaId+"110201','"+areaId+"110202','"+areaId+"110203','"+areaId+"310201') and area.id="+areaId);
		else
			return basedataDao.find("from ChargeBasedataEO where id in('"+areaId+"110204','"+areaId+"110205','"+areaId+"110206','"+areaId+"110207') and area.id="+areaId);
		
	}	
	
	public List getCarCharge2(String areaId) {
		return basedataDao.find("from ChargeBasedataEO where chargeName='车位综合服务费' and chargeBigType='租赁费用' and area.id="+areaId);
	}

	/**
	 * 取权限下 正在使用的chargeBasedataEO
	 * @return list
	 */
	@Transactional(readOnly = true)
	public List getAll() {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql = "from ChargeBasedataEO where isUser='使用' and area.id in ("+areaIds+") order by id,chargeName";
		return basedataDao.find(hql);
	}
}