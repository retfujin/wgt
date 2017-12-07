package com.acec.wgt.models.baseData;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;;
@Repository
public class AreaDAO extends HibernateDao<AreaEO, Integer> {

	
	/**
	 * 在管理员权限内，管理员查看自己所管理的管理处
	 * @param areaIds
	 * @return
	 */
	public List findAllArea(String areaIds){
		return find("from AreaEO where id in("+areaIds+") order by orderByArea");
	}
	
	/**
	 * 得到物业公司所有的小区id
	 * @return
	 */
	public List findAllAreaByCompany(String companyId){
		return find("from AreaEO where companyId=? order by orderByArea",companyId);
	}
	
	/**
	 * 在管理员权限内，管理员查看自己所管理的管理处
	 * @param page
	 * @param areaIds
	 * @return
	 */
	public Page<AreaEO> getAllAreaListByPage(Page page,String areaIds){
		return findPage(page, "from AreaEO where id in("+areaIds+") order by orderByArea");
	}
	
	
	/**
	 * 根据管理处ID取出管理处信息
	 * @param areaId
	 * @return
	 */
	public List getListByAreaId(Integer areaId){
		return find("from AreaEO where id = "+areaId);
	}
	
	
	/**
	 * 得到该管理处的公摊比例
	 * @param areaId
	 * @return
	 */
	public Float getPoolRatio(Integer areaId){
		return findUnique("select poolRatio from AreaEO where id="+areaId);
	}
	
 
}
