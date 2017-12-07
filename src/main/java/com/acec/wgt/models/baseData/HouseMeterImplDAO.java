package com.acec.wgt.models.baseData;

import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
@Repository
public class HouseMeterImplDAO extends HibernateEntityDao<HouseMeterEO> {

	
	
	public PaginatorTag getPageHouseMeter(String where , int no , int i){
		
		return pagedQuery("from HouseMeterEO where 1=1" + where, no, i);
	}
	
	
	
}
