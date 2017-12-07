package com.acec.wgt.service.chg;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.acec.commons.util.PaginatorTag;
import com.acec.commons.persist.HibernateEntityDao;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import org.springframework.stereotype.Repository;

@Repository
public class PaySelManager extends HibernateEntityDao<ChargeHouseDetailEO> {

	
	private Map session;
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	 
	
	public void setSession(Map session){
		
		this.session = session;
	}
	
	public PaginatorTag getListByPage(int no , int i,String where) {

		return pagedPayQuery("select house.id,ownerName,house.buildnum,chargeName,min(recordMonth) as beginDate,max(recordMonth) as endDate,max(gatheringDate) as gatheringDate,sum(oughtMoney),sum(factMoney),sum(arrearageMoney) from ChargeHouseDetailEO "+where+" group by house.id,ownerName,house.buildnum,chargeName order by house.id",no,i);		
	}
	 
}