package com.acec.wgt.models.baseData;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;


@Repository
public class OwnerMoveDAO extends HibernateDao<OwnerMoveEO, Integer> {

	
	/**
	 * 住户搬出/入记录
	 * @param page
	 * @param where
	 * @return
	 */
	public Page getOwnerMover(Page page,String where){
		return findPage(page, "from OwnerMoveEO where 1=1 "+where);
	}
	
	
}
