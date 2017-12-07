package com.acec.wgt.models.chargemanager;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;

@Repository
public class ChargePreviewDAO extends HibernateDao<ChargePreviewEO, Integer> {

	
	public List<ChargePreviewEO> findPreviewList(String userName){
		return find("from ChargePreviewEO where managerMen=?",userName);
	}
	 
	public void del(String userName){
		createSQLQuery("delete from tb_chg_preview where manager_men='"+userName+"'").executeUpdate();
	}
	
	public void save(ChargeHouseDetailEO chgHouse,String userName){
		 
		 ChargePreviewEO entity = new ChargePreviewEO();
		 entity.setAreaId(chgHouse.getAreaId());
		 
		 entity.setChargeId(chgHouse.getChargeId());
		 entity.setChargeName(chgHouse.getChargeName());
		 entity.setChargePrice(chgHouse.getChargePrice());
		 entity.setChargeType(chgHouse.getChargeType());
		 entity.setChargeUnit(chgHouse.getChargeUnit());
		
//		 entity.setGatheringDate(chgHouse.getGatheringDate());
		 entity.setHouse(chgHouse.getHouse());
		 entity.setHouseType(chgHouse.getHouseType());
		 entity.setLastRecord(chgHouse.getLastRecord());
//		 entity.setLateFee(chgHouse.getLateFee());
		 entity.setManagerMen(userName);
		 entity.setNowRecord(chgHouse.getNowRecord());
		 entity.setUserNum(chgHouse.getUserNum());
		 
		 entity.setOwnerName(chgHouse.getOwnerName());
		 entity.setManagerName(chgHouse.getManagerName());
		 
		 entity.setRecordMonth(chgHouse.getRecordMonth());
		 
		 
		 entity.setOughtMoney(chgHouse.getOughtMoney());
		 entity.setFactMoney(chgHouse.getFactMoney());
		 entity.setArrearageMoney(chgHouse.getArrearageMoney());
		 save(entity);
	 }
	//从预览表转移到正式表中
	public void generateResult(String userName){
		String hql="insert into ChargeHouseDetailEO(areaId,chargeId,chargeName,chargePrice,chargeType,chargeUnit"
			+",house,houseType,lastRecord,nowRecord,userNum,ownerName,recordMonth,oughtMoney,factMoney,arrearageMoney,managerName)"
			+" select areaId,chargeId,chargeName,chargePrice,chargeType,chargeUnit"
			+",house,houseType,lastRecord,nowRecord,userNum,ownerName,recordMonth,oughtMoney,factMoney,arrearageMoney,managerName"
			+" from ChargePreviewEO where managerMen=?";
		createQuery(hql,userName).executeUpdate();
		
	}
}