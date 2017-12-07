package com.acec.wgt.service.chargemanger.generate.formula;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
/**
 * 业户水表用量
 * @author Administrator
 *
 */
public class VarOwnerMeter_LSPrice extends ExpressManager{

	public Float value=0F;
	private Map<String,OwnerMeterRecordEO> map = null;
	@Override
	public float getValue(ChargeBasedataEO chb, HouseEO houseEO,ChargeHouseDetailEO chargeHouseDetail) {
		
		if(map==null)
			map = findRecord(session,houseEO.getAreaId(),recordMonth);
		OwnerMeterRecordEO e= map.get(houseEO.getId());
		if(e!=null){
			
			Integer _num = (e.getChangeNum()+(e.getNowRecord()-e.getLastRecord()))*e.getTimes();
			
			chargeHouseDetail.setLastRecord(e.getLastRecord());
			chargeHouseDetail.setNowRecord(e.getNowRecord());
			chargeHouseDetail.setUserNum(_num);
			value = Float.valueOf(_num.toString());
		}
		return value;
	}
	
	
	/**
	 * 查看表某月份是否有数据
	 * 
	 * @param recordMoth
	 * @return  有数据为true 
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String,OwnerMeterRecordEO> findRecord(Session session,int areaId,String recordMonth)
	{
		Map map = new HashMap();
		String hql="from OwnerMeterRecordEO where meterType='水表' and areaId="+areaId+" and recordMonth='"+recordMonth+"'";;
		List _ls = session.createQuery(hql).list();
		for(int i=0;i<_ls.size();i++){
			OwnerMeterRecordEO e = (OwnerMeterRecordEO)_ls.get(i);
			map.put(e.getHouseId(), e);
		}
		return map;
	}
	
	
}
