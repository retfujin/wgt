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
/**
 * 预存详情表
 */

@Entity
@Table(name = "tb_chgAdvanceDetail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class ChargeAdvanceDetailEO extends IdEntity{

	private int areaId;
	private String houseId;
	private String ownerName;//客户名称
	private String bh;//收票据号
	private float oughtMoney;//应收金额
	private float antimoney;//预交金额
	private Date recordMonth;//预交月份
	private int chargeId;//收费编号
	private String chargeName;
	private String bigType ;//预缴分类  	预交、免交（地产公司）、优惠（物业公司）
	private String remark;//说明
	private String userName;//收款人
	private Date submitTime;//录入时间
	
	
	
	
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public float getOughtMoney() {
		return oughtMoney;
	}
	public void setOughtMoney(float oughtMoney) {
		this.oughtMoney = oughtMoney;
	}
	public int getChargeId() {
		return chargeId;
	}
	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public float getAntimoney() {
		return antimoney;
	}
	public void setAntimoney(float antimoney) {
		this.antimoney = antimoney;
	}
	public Date getRecordMonth() {
		return recordMonth;
	}
	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
}
