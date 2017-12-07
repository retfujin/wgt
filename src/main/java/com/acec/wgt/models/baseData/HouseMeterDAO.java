package com.acec.wgt.models.baseData;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.chargemanager.HousechargeEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordEO;
@Repository
public class HouseMeterDAO extends HibernateDao<HouseMeterEO, Integer> {

	
	/**
	 * 更新房间表当前度数
	 * @param houseId
	 * @param meterType
	 * @param nowRecord
	 */
	public void updateHouseMeter(String houseId, String meterType,int nowRecord){
		createQuery("update HouseMeterEO set nowRecord="+nowRecord+" where isNow=true and meterType='"+meterType+"' and house.id='"+houseId+"'");
	}
	
	
	/**
	 * 根据房间编号和表类型，取出表信息资料
	 * @param houseId
	 * @param meterType
	 * @return
	 */
	public List<HouseMeterEO> getHouseMeterByHouseId(String houseId, String meterType){
		return find("from HouseMeterEO where house.id like '"+houseId+"%' and isNow=true and meterType='"+meterType+"'");
	}
	
	public List<Object> getHouseMeterLikeHouseId(String houseId){
		return find("select house.id,meterType from HouseMeterEO where isNow=true and house.id like ?",houseId+"%");
	}
	
	
	/**
	 * 按条件取房间水电表的基础资料(分页)
	 * @param page
	 * @param where
	 * @return
	 */
	public Page getAllHouseMeter(Page page,String where){
		return findPage(page, "from HouseMeterEO where 1=1 "+where+" order by house.id,meterType");
	}
	/**
	 * 按条件取房间水电表的基础资料
	 * @param where
	 * @return
	 */
	public List getAllHouseMeter(String where){
		return find("from HouseMeterEO where 1=1 "+where+" order by house.id,meterType");
	}
	
	/**
	 * 由房间收费项id，取出房间收费项列表
	 * @param id
	 * @return
	 */
	public HousechargeEO getHouseChargeById(Integer id){
		return findUnique(" from HousechargeEO where id="+id);
	}
	
	
	/**
	 * 根据表编号,取出业主表的最后一条记录
	 * @param meterType
	 * @param houseId
	 * @return
	 */
	public OwnerMeterRecordEO getMaxOwnerMeterByCode(String meterCode){
		
		Iterator<OwnerMeterRecordEO> iter = createQuery("from OwnerMeterRecordEO where meterCode='"+meterCode+"' order by recordMonth desc").iterate();
		if(iter.hasNext())
			return iter.next();
		return null;
	}
	
	
	
}
