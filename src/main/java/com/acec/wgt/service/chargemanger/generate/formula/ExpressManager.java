package com.acec.wgt.service.chargemanger.generate.formula;

import org.hibernate.Session;
import com.acec.wgt.models.baseData.HouseEO;

import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;


public abstract class ExpressManager {
	protected Session session;
	protected String recordMonth;
	
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void setRecordMonth(String recordMonth){
		this.recordMonth = recordMonth;
	}
	public abstract float getValue(ChargeBasedataEO chb,HouseEO houseEO,ChargeHouseDetailEO chargeHouseDetail);
}
