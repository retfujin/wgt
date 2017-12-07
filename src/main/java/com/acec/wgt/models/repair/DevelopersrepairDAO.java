package com.acec.wgt.models.repair;

import org.springframework.stereotype.Repository;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class DevelopersrepairDAO extends HibernateDao<DevelopersrepairEO, Integer> {

	
	/**
	 * 开发商维修单列表
	 * @param page
	 * @param where
	 * @return
	 */
	public Page<DevelopersrepairEO> getDeveRepair(Page page,String where){
		return findPage(page, "from DevelopersrepairEO where 1=1 "+where+" order by date_format(dispatDate,'%Y-%m-%d')");
	}
	
}