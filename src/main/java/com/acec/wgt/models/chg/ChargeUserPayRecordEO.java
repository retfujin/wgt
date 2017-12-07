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
@Table(name = "tb_chgUserPayRecord")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class ChargeUserPayRecordEO extends IdEntity{
	/**
	 * 用户缴费记录表
	 * 
	 * bh 缴费收据编号
	 * factMoney 缴费金额
	 * taxNum 发票号码
	 * chargeName;收费项名称 
	 * submitTime 提交时间
	 * userName 收款人
	 * chargeType 收费类型: 收款、优惠、预交、预扣,滞纳金
	 * houseEO 关联房间
	 * explain 缴费说明
	 * chargeForHouseDetailEOid;//表关联收费详情表
	 * areaId 小区id
	 * managerMen;//所属管理员
	 * 
	 */

	private String bh;
	private String payName;//交款人  针对非业主  没有业主的话 自己录入交款人名称
	private String userName;  //收款人
	private Integer chargeHouseDetailId;

	private Date recordMonth=new Date();//对应缴费详情表中的月份
	private Date gatheringTime=new Date();//收款时间
	private Date submitTime=new Date();//系统提交时间

	private int chargeId;//收费项目编号
	private String chargeName;//收费名称
	private String payType; //收款  预交  滞纳金   预扣  优惠(车位费)
	private Float factMoney=0F;//交费金额
	private String taxNum;
	private Integer areaId; //小区id
	
	private String banci="无";//临时停车缴费中的班次
	private String reason="无";////具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
	
	//现在不用了，取消
	private String type="其它";//零星收费中  交费类型     办证  罚款  保证金  其它()
	
	private Boolean isBack=false;//是否退还   用于保证金退还
	private HouseEO house;
	private Float returnMoney;//退费金额
	
	
	
	public Boolean getIsBack() {
		return isBack;
	}

	public void setIsBack(Boolean isBack) {
		this.isBack = isBack;
	}

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}
	public Date getGatheringTime() {
		return gatheringTime;
	}

	public void setGatheringTime(Date gatheringTime) {
		this.gatheringTime = gatheringTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBanci() {
		return banci;
	}

	/*
	 * 临时停车缴费中的班次
	 */
	public void setBanci(String banci) {
		this.banci = banci;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public Integer getChargeHouseDetailId() {
		return chargeHouseDetailId;
	}
	public void setChargeHouseDetailId(Integer chargeHouseDetailId) {
		this.chargeHouseDetailId = chargeHouseDetailId;
	}
	public Float getFactMoney() {
		return factMoney;
	}

	public void setFactMoney(Float factMoney) {
		this.factMoney = factMoney;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaxNum() {
		return taxNum;
	}

	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Date getRecordMonth() {
		return recordMonth;
	}
	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
	}
	@Override
	public String toString() {
		return "recordMonth="+recordMonth+";chargeName="
			+chargeName+";payType="+payType+";factMoney="+factMoney;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
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
	public Float getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(Float returnMoney) {
		this.returnMoney = returnMoney;
	}	
}