package com.acec.wgt.models.chargemanager;

import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;

@Repository
public class HousechargeImplDAO extends HibernateEntityDao<HousechargeEO> {
	
      
	public PaginatorTag getHousecharge(String areaIds,String where,int no , int i){
		//sqlserver
		//return pagedQueryNative("select distinct house_id as a0,isnull(house_address,'') as a1,buildnum as a2,isnull(owner_name,'') as a3,owner_id as a4 ,'' as a5 from view_owner_house_manager where area_id in ("+areaIds+")"+where, no, i);
		//mysql
		return pagedQueryNative("select id ,if(house_address is null,'',house_address) as a1,buildnum,if(owner_name is null,'',owner_name) as a3,0 as a4 ,'' as a5 from tb_basedata_house where area_id in ("+areaIds+")"+where, no, i);
	
	}
	
}
