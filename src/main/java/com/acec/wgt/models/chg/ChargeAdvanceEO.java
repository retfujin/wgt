package com.acec.wgt.models.chg;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.HouseEO;


@Entity
@Table(name = "tb_chgAdvance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class ChargeAdvanceEO extends IdEntity{
	/**
	 * 预存表
	 */
	private int areaId;
	private String houseId;
	private String ownerName;//客户名称
	private String bh;//收票据号
	
	private float antimoney;//总预交金额
	private float useMoney;//--已用金额
	private float money;//--剩余金额
		
	private String bigType ;//预缴分类  	预交、免交（地产公司）、优惠（物业公司）
	private String type;//是否退费标记
	private String remark;//说明
	private Date beginTime;//开始时间
	
	private int chargeId;
	private String chargeName;
	private String userName;//收款人
	private Date recordTime = new Date();//收款时间
	
	
	public int getChargeId() {
		return chargeId;
	}
	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public float getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(float useMoney) {
		this.useMoney = useMoney;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public float getAntimoney() {
		return antimoney;
	}
	public void setAntimoney(float antimoney) {
		this.antimoney = antimoney;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}	
}