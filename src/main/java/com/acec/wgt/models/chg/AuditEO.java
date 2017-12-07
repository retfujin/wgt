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
@Table(name = "tb_chg_audit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class AuditEO extends IdEntity{
	/**
	 * 报账表
	 */
	private int areaId;//小区
	private Date beginDate;//开始日期
	private Date endDate;//截止日期
	private String sumMoney;//报账总费用
	private String auditStatus;//报账状态
	private String userName;//报账人
	
 
	private Date recordTime = new Date();//记录时间


	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
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
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
}