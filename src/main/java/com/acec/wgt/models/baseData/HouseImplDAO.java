package com.acec.wgt.models.baseData;

import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
@Repository
public class HouseImplDAO extends HibernateEntityDao<HouseEO> {

	

	/**
	 * 分页取出管理员权限范围内，所有房间信息列表
	 * @param page
	 * @param areaIds
	 * @param where
	 * @return
	 */
	public PaginatorTag findListByPage(String areaIds,String where , int no , int i){
		return pagedQuery( "from HouseEO where areaId in("+areaIds+") "+where+" order by id" , no  ,i);
	}
	
}
