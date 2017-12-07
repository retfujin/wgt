package com.acec.wgt.models.chargemanager;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.acec.core.orm.hibernate.HibernateDao;;
@Repository
public class ChargeBasedataDAO extends HibernateDao<ChargeBasedataEO, Integer> {

	/**
	 * 找出基础收费项
	 * @param prexChargeId 基础收费项目以prexChargeId开头
	 * @return List
	 */
	public List<ChargeBasedataEO> findListById(String prexChargeId) {
		return find("from ChargeBasedataEO where id like '"+prexChargeId+"%'");
		
	}
	
	
	/**
	 * 根据收费名称和管理处ID得到收费标准
	 * @param chargeName
	 * @param areaId
	 * @return
	 */
	public List<ChargeBasedataEO> findListByNameAareaId(String chargeName,Integer areaId){
		return find("from ChargeBasedataEO where isUser='使用' and chargeName='"+chargeName+"' and areaId='"+areaId+"'");
	}
	
	
	/**
	 * 根据收费编号和管理处ID得到收费标准
	 * @param chargeId
	 * @param areaId
	 * @return
	 */
	public List<ChargeBasedataEO> findListByIdAareaId(String chargeId,Integer areaId){
		return find("from ChargeBasedataEO where id like '"+chargeId+"%' and areaId='"+areaId+"'");
	}
	
	
	/**
	 * 根据收费项目的id取出整个收费项目的记录
	 * @param chargeId
	 * @return
	 */
	public ChargeBasedataEO getChargeBasedataByChargeId(Integer chargeId){
		return findUnique("from ChargeBasedataEO where id='"+chargeId+"'");
	}
	
	
	
	public List<ChargeBasedataEO> findAllList(String str){
		return find("from ChargeBasedataEO where isUser='使用' "+str);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
