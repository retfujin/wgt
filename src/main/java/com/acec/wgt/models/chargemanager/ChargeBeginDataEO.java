package com.acec.wgt.models.chargemanager;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.HouseEO;

@Entity
@Table(name = "tb_chgBegindata")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)


public class ChargeBeginDataEO extends IdEntity{


	private Date recordMonthBegin;//缴费起始月份
	private Date recordMonthEnd;//缴费截止月份
	private int months;//相差月数
	private String chargeName;//收费项目中的
	private float chargePrice;//单价
	
	
	private float oughtMoney;//应收
	private float payMoney;//已收
	private float arrearageMoney; //尚欠费用
	
	private Date oughtPayDate;//应缴费截止日期
	
	private String houseType;//房间类型   住宅  商铺
	private String ownerName;
	
	private String houseId; //
	private String areaId;
	private String chargeId;
	
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
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
	public float getOughtMoney() {
		return oughtMoney;
	}
	public void setOughtMoney(float oughtMoney) {
		this.oughtMoney = oughtMoney;
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
	

	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public Date getRecordMonthBegin() {
		return recordMonthBegin;
	}
	public void setRecordMonthBegin(Date recordMonthBegin) {
		this.recordMonthBegin = recordMonthBegin;
	}
	public Date getRecordMonthEnd() {
		return recordMonthEnd;
	}
	public void setRecordMonthEnd(Date recordMonthEnd) {
		this.recordMonthEnd = recordMonthEnd;
	}
	public Date getOughtPayDate() {
		return oughtPayDate;
	}
	public void setOughtPayDate(Date oughtPayDate) {
		this.oughtPayDate = oughtPayDate;
	}
	
	
	
	
}
