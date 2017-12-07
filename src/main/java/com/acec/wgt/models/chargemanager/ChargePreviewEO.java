package com.acec.wgt.models.chargemanager;


import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.HouseEO;

@Entity
@Table(name = "tb_chgPreview")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 30)
/**
	 * 生成预览收费明细表
	 * 
	 * recordMonth 缴费月份
	 * chargeName 收费项目名称
	 * chargePrice 单价
	 * chargeUnit 单位
	 * lastRecord 上期度数
	 * nowRecord 本期度数
	 * userNum 用量
	 * oughtMoney 应缴费用
	 * factMoney 实缴费用
	 * payMoney 已付费用
	 * arrearageMoney 尚欠费用
	 * gatheringDate 缴费日期
	 * detail 缴费说明
	 * chargeType 费用类型（1固定，2走表(业主)，3公摊） 可要可不要
	 * areaId 小区id， 主要是零星收费时，只关联到小区
	 * houseEO 关联房间
	 * chargeBaseDataEO 关联收费项
	 * managerMen 操作人员登录名
	 * 
	 * lateFee 滞纳金
	 * arreargeReason 欠费原因 09-3-31增加
	 */
public class ChargePreviewEO extends IdEntity{
	
	
	private Date recordMonth;//缴费月份
	private String chargeBigType;//收费类型大类(管理费用，代支费用，租赁费用等） 
	private String chargeName;//收费项目中的chargeName
	private String otherName;//收费项目中的别名
	private float chargePrice=0;
	private String chargeUnit;
	private Integer lastRecord=0;
	private Integer nowRecord=0;
	private Integer userNum=0;
	private float oughtMoney;//应缴费用
	private float factMoney;//实缴费用
	private float payMoney;//已付费用
	private float arrearageMoney; //尚欠费用
	private Date gatheringDate;//缴费日期
	private String detail="";
	private String chargeType;
	private String houseType;//房间类型   住宅  商铺
	private String habitationType;//入住系数
	
	private String managerMen="";//操作人员登录名
	

	private String arreargeReason="无";
	
	private String isRelease="false";
	private String ownerName="";
	private String managerName="";
	
	private HouseEO house;
	private Integer areaId;
	
	private int chargeId;

	private Float lateFee=0f;// 滞纳金
	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	public String getManagerMen() {
		return managerMen;
	}

	public void setManagerMen(String managerMen) {
		this.managerMen = managerMen;
	}

	public String getChargeBigType() {
		return chargeBigType;
	}

	public void setChargeBigType(String chargeBigType) {
		this.chargeBigType = chargeBigType;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public Date getRecordMonth() {
			return recordMonth;
	}

	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public float getChargePrice() {
		return chargePrice;
	}

	public void setChargePrice(float chargePrice) {
		this.chargePrice = chargePrice;
	}

	public String getChargeUnit() {
		return chargeUnit;
	}

	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	public Integer getLastRecord() {
		return lastRecord;
	}

	public void setLastRecord(Integer lastRecord) {
		this.lastRecord = lastRecord;
	}

	public Integer getNowRecord() {
		return nowRecord;
	}

	public void setNowRecord(Integer nowRecord) {
		this.nowRecord = nowRecord;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public float getOughtMoney() {
		return oughtMoney;
	}

	public void setOughtMoney(float oughtMoney) {
		this.oughtMoney = oughtMoney;
	}

	public float getFactMoney() {
		return factMoney;
	}

	public void setFactMoney(float factMoney) {
		this.factMoney = factMoney;
	}

	public float getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}

	public float getArrearageMoney() {
		return arrearageMoney;
	}

	public void setArrearageMoney(float arrearageMoney) {
		this.arrearageMoney = arrearageMoney;
	}

	public Date getGatheringDate() {
		return gatheringDate;
	}

	public void setGatheringDate(Date gatheringDate) {
		this.gatheringDate = gatheringDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}


	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}


	
	@ManyToOne( cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	public HouseEO getHouse() {
		return house;
	}

	public void setHouse(HouseEO house) {
		this.house = house;
	}
	
	

	

	public String getArreargeReason() {
		return arreargeReason;
	}

	public void setArreargeReason(String arreargeReason) {
		this.arreargeReason = arreargeReason;
	}



	


	public String getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getHabitationType() {
		return habitationType;
	}

	public void setHabitationType(String habitationType) {
		this.habitationType = habitationType;
	}

	public Float getLateFee() {
		return lateFee;
	}

	public void setLateFee(float lateFee) {
		this.lateFee = lateFee;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	
}
