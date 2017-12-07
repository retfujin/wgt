package com.acec.wgt.models.baseData;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
@Repository
public class OwnerDecorateDAO extends HibernateDao<OwnerDecorateEO, Integer>{
  
	
	/**
	 * 得到所有的装修记录
	 */
	public List<OwnerDecorateEO> getAll(){
		return find("from OwnerDecorateEO");
	}

	public Page report003(Page page, String where) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 得到装修记录
	 * @param page
	 * @param where
	 * @return
	 */
	public Page getOwnerDecorate(Page page,String where){
		return findPage(page, "from OwnerDecorateEO where 1=1 "+where);
	}
}
