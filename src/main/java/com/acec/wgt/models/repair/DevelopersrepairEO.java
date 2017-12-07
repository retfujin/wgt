package com.acec.wgt.models.repair;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
/**
 * 开发商维修单
 * @author hua 
 * @Version 2009-12-01
 */

@Entity
@Table(name = "tb_basedata_developers_repair")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DevelopersrepairEO extends IdEntity {
    
	private int areaId;
	private Integer dispatYear;//派工时间   年 
	private Integer dispatMonth;//月 
	private Integer dispatDay;//日
	private Integer dispatHours;//时
	private Integer dispatMinute;//分
	private String number;//编号
	private String repairAddress;//维修地址
	private String ownerName;//业主姓名
	private String repairPeople;//维修人
	private String ownerPhone;//业主电话
	private String repairContent;//维修内容
	private String repairOf;//维修情况
	private String aSiguature;//甲方签字
	private String supervision;//监理签字
	private String acceptanceTime;//维修完成时间
	private String explain;//业主验收说明
	private String acceptancePeople;//验收人
	private Date asignrecordDate;//甲方签收日期
	private String asignPeople;//甲方签收人
	private String propertyPerson;//物业签收人
	private String repairStatus;//维修状态   已维修   未维修
	private String houseId;
	
	
	private String dispatDate;//派工日期
	
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
	public Integer getDispatYear() {
		return dispatYear;
	}
	public void setDispatYear(Integer dispatYear) {
		this.dispatYear = dispatYear;
	}
	public Integer getDispatMonth() {
		return dispatMonth;
	}
	public void setDispatMonth(Integer dispatMonth) {
		this.dispatMonth = dispatMonth;
	}
	public Integer getDispatDay() {
		return dispatDay;
	}
	public void setDispatDay(Integer dispatDay) {
		this.dispatDay = dispatDay;
	}
	public Integer getDispatHours() {
		return dispatHours;
	}
	public void setDispatHours(Integer dispatHours) {
		this.dispatHours = dispatHours;
	}
	public Integer getDispatMinute() {
		return dispatMinute;
	}
	public void setDispatMinute(Integer dispatMinute) {
		this.dispatMinute = dispatMinute;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRepairAddress() {
		return repairAddress;
	}
	public void setRepairAddress(String repairAddress) {
		this.repairAddress = repairAddress;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getRepairPeople() {
		return repairPeople;
	}
	public void setRepairPeople(String repairPeople) {
		this.repairPeople = repairPeople;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	public String getRepairOf() {
		return repairOf;
	}
	public void setRepairOf(String repairOf) {
		this.repairOf = repairOf;
	}
	public String getaSiguature() {
		return aSiguature;
	}
	public void setaSiguature(String aSiguature) {
		this.aSiguature = aSiguature;
	}
	public String getSupervision() {
		return supervision;
	}
	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}
	public String getAcceptanceTime() {
		return acceptanceTime;
	}
	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getAcceptancePeople() {
		return acceptancePeople;
	}
	public void setAcceptancePeople(String acceptancePeople) {
		this.acceptancePeople = acceptancePeople;
	}
	public Date getAsignrecordDate() {
		return asignrecordDate;
	}
	public void setAsignrecordDate(Date asignrecordDate) {
		this.asignrecordDate = asignrecordDate;
	}
	public String getAsignPeople() {
		return asignPeople;
	}
	public void setAsignPeople(String asignPeople) {
		this.asignPeople = asignPeople;
	}
	public String getPropertyPerson() {
		return propertyPerson;
	}
	public void setPropertyPerson(String propertyPerson) {
		this.propertyPerson = propertyPerson;
	}
	public String getRepairStatus() {
		return repairStatus;
	}
	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}
	public String getDispatDate() {
		return dispatDate;
	}
	public void setDispatDate(String dispatDate) {
		this.dispatDate = dispatDate;
	}
}