package com.acec.wgt.models.chargemanager;

import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;

@Repository
public class OwnerMeterRecordImplDAO extends HibernateEntityDao<OwnerMeterRecordEO> {
	
	/**
	 * 权限下 所有业户表记录 分页
	 * @param page
	 * @param where   查询条件
	 * @return
	 */
	public PaginatorTag getOwnerMeterRecordImpl(String areaIds , String where,int no , int i){
		
		return pagedQuery("from OwnerMeterRecordEO where areaId in ("+areaIds+") "+where, no, i);
		
	}

}
