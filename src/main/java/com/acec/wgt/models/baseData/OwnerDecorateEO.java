package com.acec.wgt.models.baseData;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Table;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

/**
 * entity.
 * 业主装修登记表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_basedata_owner_derorate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OwnerDecorateEO extends IdEntity {
	
	private int areaId;//管理处编号
	private String houseId;//房间编号
	private String housedz;//房间地址 
	private String ownerName;//业主姓名

	private String decoratetel;//联系电话
	private String decoratesxbl;//手续办理
	
	private Date inDate =new  Date(System.currentTimeMillis());//入伙日期
	private Date decorateindate;//装修起始时间
	private Date decorateoutdate;//验收时间
	private String decoratecontributions;//交费情况	

	private String decoratenotes;//备注
	
	private String deposit;//押金
	private String passFee;//出入证费用
	private String isReturn;//押金是否已退还
	private String status;//状态，是否已完工
	private String isDamage;//设备是否损坏，
	private String compensation;//当设备为损坏状态时,应赔偿金额

	
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
	public String getHousedz() {
		return housedz;
	}
	public void setHousedz(String housedz) {
		this.housedz = housedz;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getDecoratetel() {
		return decoratetel;
	}
	public void setDecoratetel(String decoratetel) {
		this.decoratetel = decoratetel;
	}
	public String getDecoratesxbl() {
		return decoratesxbl;
	}
	public void setDecoratesxbl(String decoratesxbl) {
		this.decoratesxbl = decoratesxbl;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getDecorateindate() {
		return decorateindate;
	}
	public void setDecorateindate(Date decorateindate) {
		this.decorateindate = decorateindate;
	}
	public Date getDecorateoutdate() {
		return decorateoutdate;
	}
	public void setDecorateoutdate(Date decorateoutdate) {
		this.decorateoutdate = decorateoutdate;
	}
	public String getDecoratecontributions() {
		return decoratecontributions;
	}
	public void setDecoratecontributions(String decoratecontributions) {
		this.decoratecontributions = decoratecontributions;
	}
	public String getDecoratenotes() {
		return decoratenotes;
	}
	public void setDecoratenotes(String decoratenotes) {
		this.decoratenotes = decoratenotes;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPassFee() {
		return passFee;
	}
	public void setPassFee(String passFee) {
		this.passFee = passFee;
	}
	public String getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsDamage() {
		return isDamage;
	}
	public void setIsDamage(String isDamage) {
		this.isDamage = isDamage;
	}
	public String getCompensation() {
		return compensation;
	}
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	
}