package com.acec.wgt.service.chargemanger.generate.formula;


import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;

/**
 * 当前费用的单价
 * @author Administrator
 *
 */

public class VarPriceValue extends ExpressManager {
	
	public Float value=null;
	
	public float getValue(ChargeBasedataEO chb, com.acec.wgt.models.baseData.HouseEO houseEO,ChargeHouseDetailEO chargeHouseDetail) {
			
		value = chb.getPriceValue();

		return value;
	}


}
