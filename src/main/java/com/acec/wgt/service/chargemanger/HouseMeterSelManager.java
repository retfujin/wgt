package com.acec.wgt.service.chargemanger;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.HouseMeterEO;
import com.acec.wgt.service.basedata.CellManager;

@Service
public class HouseMeterSelManager extends HibernateEntityDao<HouseMeterEO> {

	CellManager cellManager;

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public PaginatorTag getHouseMeterListByPage(int no , int i,String where) {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		return pagedQuery("from HouseMeterEO where isNow=1 "+where+" order by house.id,meterType",no,i);		
	}

	
}