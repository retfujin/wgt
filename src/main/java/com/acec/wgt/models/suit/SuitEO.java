package com.acec.wgt.models.suit;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_suit_check")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class SuitEO implements java.io.Serializable {

    @Id
    private int id;
    private int areaId;// 管理处
    private String houseId;// 房间号
    
    
    private String suitkid;// 投诉登记代码
    
    private String suitDate;// 投诉日期
    private String suitName;// 业主姓名
    private String suittel;// 联系电话
    private String suitType;// 投诉类型
    private String suitDepartment;// 客户地址
    private String suitWay;// 投诉方式
    private String suitMess;// 投诉内容
    private String name1;// 记录人
    private String suitDate1;// 时间
    
    private String investigationState;// 处理过程
    private String name2;// 记录人
    private String suitDate2;// 时间
    
    private String processState;// 回访过程
    private String name3;// 回访人
    private String suitDate3;// 时间
    
    
    private String remark;// 领导审阅
    private String name4;// 领导签名
    private String suitDate4;// 时间
    private String suitTime; // 处理完毕时间
    
    private String flowState;//流程状态   0 未处理  1 已处理    2 已回访
    private String areaName;
    
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getAreaId() {
	return areaId;
    }

    public void setAreaId(int areaId) {
	this.areaId = areaId;
    }

    public String getSuitkid() {
	return suitkid;
    }

    public void setSuitkid(String suitkid) {
	this.suitkid = suitkid;
    }

    public String getSuitDate() {
	return suitDate;
    }

    public void setSuitDate(String suitDate) {
	this.suitDate = suitDate;
    }

    public String getSuitName() {
	return suitName;
    }

    public void setSuitName(String suitName) {
	this.suitName = suitName;
    }

    public String getSuittel() {
	return suittel;
    }

    public void setSuittel(String suittel) {
	this.suittel = suittel;
    }

    public String getSuitType() {
	return suitType;
    }

    public void setSuitType(String suitType) {
	this.suitType = suitType;
    }

    public String getSuitDepartment() {
	return suitDepartment;
    }

    public void setSuitDepartment(String suitDepartment) {
	this.suitDepartment = suitDepartment;
    }

    public String getSuitWay() {
	return suitWay;
    }

    public void setSuitWay(String suitWay) {
	this.suitWay = suitWay;
    }

    public String getSuitMess() {
	return suitMess;
    }

    public void setSuitMess(String suitMess) {
	this.suitMess = suitMess;
    }

    public String getName1() {
	return name1;
    }

    public void setName1(String name1) {
	this.name1 = name1;
    }

    public String getSuitDate1() {
	return suitDate1;
    }

    public void setSuitDate1(String suitDate1) {
	this.suitDate1 = suitDate1;
    }

    public String getInvestigationState() {
	return investigationState;
    }

    public void setInvestigationState(String investigationState) {
	this.investigationState = investigationState;
    }

    public String getName2() {
	return name2;
    }

    public void setName2(String name2) {
	this.name2 = name2;
    }

    public String getSuitDate2() {
	return suitDate2;
    }

    public void setSuitDate2(String suitDate2) {
	this.suitDate2 = suitDate2;
    }

    public String getProcessState() {
	return processState;
    }

    public void setProcessState(String processState) {
	this.processState = processState;
    }

    public String getName3() {
	return name3;
    }

    public void setName3(String name3) {
	this.name3 = name3;
    }

    public String getSuitDate3() {
	return suitDate3;
    }

    public void setSuitDate3(String suitDate3) {
	this.suitDate3 = suitDate3;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public String getName4() {
	return name4;
    }

    public void setName4(String name4) {
	this.name4 = name4;
    }

    public String getSuitDate4() {
	return suitDate4;
    }

    public void setSuitDate4(String suitDate4) {
	this.suitDate4 = suitDate4;
    }

    public String getSuitTime() {
	return suitTime;
    }

    public void setSuitTime(String suitTime) {
	this.suitTime = suitTime;
    }

    public String getHouseId() {
	return houseId;
    }

    public void setHouseId(String houseId) {
	this.houseId = houseId;
    }

	public String getFlowState() {
		return flowState;
	}

	public void setFlowState(String flowState) {
		this.flowState = flowState;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


    

}