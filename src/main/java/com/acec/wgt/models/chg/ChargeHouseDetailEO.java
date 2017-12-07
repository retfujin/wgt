package com.acec.wgt.models.chg;

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

/**
 * 最终收费明细表
 * 
 * recordMonth 缴费月份 chargeName 收费项目名称 chargePrice 单价 chargeUnit 单位 lastRecord
 * 上期度数 nowRecord 本期度数 userNum 用量 oughtMoney 应缴费用 factMoney 实缴费用 payMoney 已付费用
 * arrearageMoney 尚欠费用 gatheringDate 缴费日期 detail 缴费说明 chargeType
 * 费用类型（1固定，2走表(业主)，3公摊） 可要可不要 areaId 小区id， 主要是零星收费时，只关联到小区 houseEO 关联房间
 * chargeBaseDataEO 关联收费项 lateFee 滞纳金
 */
@Entity
@Table(name = "tb_chgHouseDetail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 30)
public class ChargeHouseDetailEO extends IdEntity {

    private Date recordMonth;// 缴费月份
    private String chargeName;// 收费项目中的chargeName
    private float chargePrice = 0;
    private String chargeUnit;
    private Integer lastRecord = 0;
    private Integer nowRecord = 0;
    private Integer userNum = 0;
    private float oughtMoney;// 应缴费用
    private float factMoney;// 实缴费用
    private float arrearageMoney; // 尚欠费用
    private float privilegeMoney;// 优惠金额
    private String privilegeReason;// 优惠原因(现在就是备注)
    private Date gatheringDate;// 缴费日期
    private String chargeType;
    private String houseType;// 房间类型 住宅 商铺
    private String ownerName = "";
    private String payId;
    private String managerName;// 物管员

    private HouseEO house;
    private Integer areaId;
    
    private Date beginDate;
    private Date endDate;
    private int carportLeaseId=0;

    private int chargeId;

    private Float lateFee = 0f;// 滞纳金


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

    public String getChargeType() {
	return chargeType;
    }

    public void setChargeType(String chargeType) {
	this.chargeType = chargeType;
    }

    public String getHouseType() {
	return houseType;
    }

    public void setHouseType(String houseType) {
	this.houseType = houseType;
    }

    public String getOwnerName() {
	return ownerName;
    }

    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    public HouseEO getHouse() {
	return house;
    }

    public float getPrivilegeMoney() {
	return privilegeMoney;
    }

    public void setPrivilegeMoney(float privilegeMoney) {
	this.privilegeMoney = privilegeMoney;
    }

    public String getPrivilegeReason() {
	return privilegeReason;
    }

    public void setPrivilegeReason(String privilegeReason) {
	this.privilegeReason = privilegeReason;
    }

    public void setHouse(HouseEO house) {
	this.house = house;
    }

    public Integer getAreaId() {
	return areaId;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public int getChargeId() {
	return chargeId;
    }

    public void setChargeId(int chargeId) {
	this.chargeId = chargeId;
    }

    public Float getLateFee() {
	return lateFee;
    }

    public void setLateFee(Float lateFee) {
	this.lateFee = lateFee;
    }

    public String getPayId() {
	return payId;
    }

    public void setPayId(String payId) {
	this.payId = payId;
    }

    public String getManagerName() {
	return managerName;
    }

    public void setManagerName(String managerName) {
	this.managerName = managerName;
    }

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCarportLeaseId() {
		return carportLeaseId;
	}

	public void setCarportLeaseId(int carportLeaseId) {
		this.carportLeaseId = carportLeaseId;
	}

}