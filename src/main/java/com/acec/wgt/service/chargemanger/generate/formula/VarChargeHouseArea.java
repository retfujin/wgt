package com.acec.wgt.service.chargemanger.generate.formula;


import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;

/**
 * 收费面积
 * @author Administrator
 *
 */
public class VarChargeHouseArea extends ExpressManager {
	
	public Float value=null;
	
	public float getValue(ChargeBasedataEO chb, com.acec.wgt.models.baseData.HouseEO houseEO,ChargeHouseDetailEO chargeHouseDetail) {
		value = houseEO.getBuildnum();
		return value;
	}


}
