package com.acec.wgt.models.baseData;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

/**
 * entity.业主房间表基础资料
 */
@Entity
@Table(name = "tb_basedata_housemeter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class HouseMeterEO extends IdEntity {
	
	private String ownerName;//业主名称
	private String meterType;//表类型
	private Integer initNum;//期初度数
	private Integer nowRecord;//末次度数
	private Integer backRecord=9999;//回转度数
	
	private Boolean isNow=true;
	
	private Date beginTime=new Date(System.currentTimeMillis());//启用日期
	private Date endTime;//更换日期

	private String changeName;//更换人员
	private Date changeTime;//更换时间
	private String changeReason;//更换原因
	
	private HouseEO house;

	private int times=1;//倍率
	
	
	
	
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public Integer getBackRecord() {
		return backRecord;
	}

	public void setBackRecord(Integer backRecord) {
		this.backRecord = backRecord;
	}

	public Boolean getIsNow() {
		return isNow;
	}

	public void setIsNow(Boolean isNow) {
		this.isNow = isNow;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public Integer getInitNum() {
		return initNum;
	}

	public void setInitNum(Integer initNum) {
		this.initNum = initNum;
	}

	public Integer getNowRecord() {
		return nowRecord;
	}

	public void setNowRecord(Integer nowRecord) {
		this.nowRecord = nowRecord;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	public HouseEO getHouse() {
		return house;
	}

	public void setHouse(HouseEO house) {
		this.house = house;
	}

	
}