package com.acec.wgt.models.chargemanager;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.core.utils.ArithUtils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.HouseEO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_chgOwnermeterRecord")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 5)

public class OwnerMeterRecordEO extends com.acec.wgt.models.IdEntity{

	private String meterName;
	private String meterCode;//房间表的id
	private String meterType;
	private Integer backRecord;//回转度数
	
	
	private String unit; //单位	
	private float priceValue;	//单价
	private Integer lastRecord;
	private Integer nowRecord;//当前表数   换表时 只记录换表后的表数
	
	private Integer changeNum;//换表用量   当换表用量>0时说明用户换表   此时   总用量=换表用量
	private Integer useNums;//总用量
	private int times;//倍率
	private float totalMoney;//金额
	
	private Date recordMonth;
	
	private String checkStatus;//正在录入  正在审核    审核否决    数据锁定
	private String checkName;
	private Date checkTime;
	private String recordName;
	private Date recordTime;
	private String submitName;
	private Date submitTime;
	private int isNow;

	private int areaId;
	private String edificeId;
	private String houseAddress;
	private String ownerName;
	private float buildnum;//面积
	private String cell;
	private String managerMen;
	private String houseId;
	private String houseName;
	private int chargeId;//收费项目编号，生成表类费用就从此处循环
	
	public Integer getChangeNum() {
		return changeNum;
	}

	public void setChangeNum(Integer changeNum) {
		this.changeNum = changeNum;
	}

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getManagerMen() {
		return managerMen;
	}

	public void setManagerMen(String managerMen) {
		this.managerMen = managerMen;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public float getBuildnum() {
		return buildnum;
	}

	public void setBuildnum(float buildnum) {
		this.buildnum = buildnum;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getEdificeId() {
		return edificeId;
	}

	public void setEdificeId(String edificeId) {
		this.edificeId = edificeId;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getIsNow() {
		return isNow;
	}

	public void setIsNow(int isNow) {
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
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

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public float getTotalMoney() {
		
		//totalMoney = ArithUtils.round(totalMoney, 2);
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getRecordMonth() {
		return recordMonth;
	}

	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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

	public Integer getBackRecord() {
		return backRecord;
	}

	public void setBackRecord(Integer backRecord) {
		this.backRecord = backRecord;
	}
 
}
