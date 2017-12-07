package com.acec.wgt.models.chg;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class ChargeHouseDetailDAO extends HibernateDao<ChargeHouseDetailEO, Integer> {

	/**
	 * 查找是否有每月收费表中是否有符合条件的收费数据
	 * @param beginRecordMonth
	 * @param prexChargeId 收费编号
	 * @return
	 */
	public long findCount(String beginRecordMonth, String prexChargeId) {
		return findUnique("select count(*) from ChargeHouseDetailEO where recordMonth>='"+beginRecordMonth+"' and chargeId in("+prexChargeId+")");
	}
	
	/**
	 * 查找是否有每月收费表中是否有符合条件的收费数据
	 * @param beginRecordMonth
	 * @param chargeIds 收费编号,多个用，号分隔
	 * @return
	 */
	public long findAllCount(String beginRecordMonth,String chargeIds) {
		return findUnique("select count(*) from ChargeHouseDetailEO where recordMonth>='"+beginRecordMonth+"' and chargeId in ("+chargeIds+")");
	}
	
	/**
	 * 查找是否有每月收费表中是否有符合条件的收费数据
	 * @param beginRecordMonth
	 * @param chargeIds 收费编号,多个用，号分隔
	 * @param houseId 小区具体的范围
	 * @return
	 */
	
	public long findCount(String beginRecordMonth, String chargeIds,String houseId) {
		return findUnique("select count(*) from ChargeHouseDetailEO where recordMonth>='"+beginRecordMonth+"' and chargeId in ("+chargeIds+") and house.id like '"+houseId+"%'");	
	}
	
	/**
	 * 更新缴费记录表中的姓名
	 * @param ownerName
	 * @param houseId
	 * @param date
	 */
	public void getRename(String ownerName,String houseId,String date){
		getSession().createQuery("update ChargeHouseDetailEO set ownerName = '"+ownerName+"' where house.id='"+houseId+"' and date_format(recordMonth,'%Y-%m-%d')>=date_format('"+date+"','%Y-%m-%d') ").executeUpdate();
	}	
	
	public ChargeHouseDetailEO getChargeHouseDetailById(String id){
		return findUnique("from ChargeHouseDetailEO where id="+id);
	}

	public void updChargeHDPayMoney(Float factMoney,String id){
		getSession().createQuery("update ChargeHouseDetailEO set gatheringDate=now(),arrearageMoney=oughtMoney - "+factMoney+" where id="+id).executeUpdate();
	}

	public void updChargeHDALLMoney(Float factMoney,String detailId){
		getSession().createQuery("update ChargeHouseDetailEO set gatheringDate=now(),factMoney=factMoney+"+factMoney+",arrearageMoney=oughtMoney - "+factMoney+" where id="+detailId).executeUpdate();
	}
	
	/**
	 * 更新表tb_chg_house_detail的滞纳金
	 * @param id
	 * @param feeMoney
	 */
	public void updChgHouseDetail(int id,float paramMoney){
		getSession().createSQLQuery("update tb_chg_house_detail set late_fee="+paramMoney+" where id="+id).executeUpdate();
	}
}