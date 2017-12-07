package com.acec.wgt.service.chargemanger.generate.formula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.service.basedata.CellManager;

/**
 * 单个小区的总房间数
 * 
 * @author Administrator
 *
 */
public class VarCountHouseArea extends ExpressManager {
	
		
	
	public float value=0;

	public float getValue(ChargeBasedataEO chargeBasedata, com.acec.wgt.models.baseData.HouseEO house,ChargeHouseDetailEO chargeHouseDetail) {
		List l = session.createQuery("select count(*) from HouseEO where areaId="+house.getAreaId()).list();
		if(!l.isEmpty())
			value = (Integer)l.get(0);
		else
			throw new RuntimeException("小区总房间数为0");
			
		return value;
	}

}
