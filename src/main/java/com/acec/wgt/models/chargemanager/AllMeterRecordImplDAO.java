package com.acec.wgt.models.chargemanager;

import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;

@Repository
public class AllMeterRecordImplDAO extends HibernateEntityDao<AllMeterRecordEO> {

	
	

	/**
	 * 权限下 所有总表抄表记录记录 分页
	 * @param page
	 * @return
	 */
	 public PaginatorTag getAllMeterRecord(String areaIds, String where,int no, int i){
		 
		 return pagedQuery("from AllMeterRecordEO where areaId in ("+areaIds+") "+where, no, i);
	 }
	
	
}
