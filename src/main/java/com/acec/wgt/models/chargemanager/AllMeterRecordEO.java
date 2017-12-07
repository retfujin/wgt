package com.acec.wgt.models.chargemanager;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_chgAllmeterRecord")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 5)

public class AllMeterRecordEO extends com.acec.wgt.models.IdEntity{
	
	
	private Integer allmeterId;//总表的id
	private String meterCode;	
	private String meterCodelocal;//物业公司自己的编号（供电局）
	
	private String meterName;
	private String meterType;	//水电汽
	private String useMeterType;//用表类型  消防用水   经营用水    电梯用电     照明用电
	private String gatherType;//公共用电，业主用电，商户用电，用电损耗      ---公共用水，业主用水，商户用水，用水损耗

	private String assignArea="";//公摊范围
	private String assignType;//分摊方式 1按面积 2按户数
	private String isAll;//是否公摊
	private String unit; //单位
	private String local;//位置

	
	private Date recordMonth; //抄表月份(指生成表数据的月份)
	
	private Integer lastRecord;
	private Integer nowRecord; //当前表读数 
	private Integer useNums;//用量(即收费表数)  
	private Integer changeNums=0;//换表数
	private Float times;//倍率
	private float priceValue;//单价	
	private float totalMoney;
	
	private String checkStatus;//正在录入  正在审核    审核否决    数据锁定
	
	private String recordName; //抄表人
	private Date recordTime;//抄表时间
	private String submitName;//录入人
	private Date submitTime;//录入日期

	private Integer isNow=1;
	private int areaId;
	private String areaName;
	
	private String chargebasedataId;//对应的收费项目id

	public Integer getChangeNums() {
		return changeNums;
	}
	public void setChangeNums(Integer changeNums) {
		this.changeNums = changeNums;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getGatherType() {
		return gatherType;
	}
	public void setGatherType(String gatherType) {
		this.gatherType = gatherType;
	}
	
	public Integer getIsNow() {
		return isNow;
	}
	public void setIsNow(Integer isNow) {
		this.isNow = isNow;
	}

	public String getMeterName() {
		return meterName;
	}
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public String getMeterCode() {
		return meterCode;
	}
	public void setMeterCode(String meterCode) {
		this.meterCode = meterCode;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public String getUseMeterType() {
		return useMeterType;
	}
	public void setUseMeterType(String useMeterType) {
		this.useMeterType = useMeterType;
	}
	
	public String getAssignArea() {
		return assignArea;
	}
	public void setAssignArea(String assignArea) {
		this.assignArea = assignArea;
	}
	public String getAssignType() {
		return assignType;
	}
	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}
	public String getIsAll() {
		return isAll;
	}
	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Date getRecordMonth() {
		return recordMonth;
	}
	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
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
	public Integer getUseNums() {
		return useNums;
	}
	public void setUseNums(Integer useNums) {
		this.useNums = useNums;
	}
	public Float getTimes() {
		return times;
	}
	public void setTimes(Float times) {
		this.times = times;
	}
	public float getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
	}
	public float getTotalMoney() {
	//	totalMoney = ArithUtils.round(totalMoney, 2);
		
		return totalMoney;
	}
	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getSubmitName() {
		return submitName;
	}
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getChargebasedataId() {
		return chargebasedataId;
	}
	public void setChargebasedataId(String chargebaseDataId) {
		this.chargebasedataId = chargebaseDataId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getAllmeterId() {
		return allmeterId;
	}
	public void setAllmeterId(Integer allmeterId) {
		this.allmeterId = allmeterId;
	}
	public String getMeterCodelocal() {
		return meterCodelocal;
	}
	public void setMeterCodelocal(String meterCodelocal) {
		this.meterCodelocal = meterCodelocal;
	}


	
}
