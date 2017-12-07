package com.acec.wgt.models.ser;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Table(name = "tbl_mySendMsg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class MySendMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;//序号
    private String creatorID; //用户ID
    private String userName;
    private int smsNum;//发送数量
    private String destAddr;//手机号
    private String destAddString;//存放楼栋、房间名字等
    
    private String smContent;//信息内容
    private Date sendTime;//发送时间
    private Date subTime = new Date();//当前时间
    private Integer fsStatus;//发送状态 1正在审核  2已经发送  3审核不通过
    private Integer smsType;//1公告发布 2用户咨询 3节日祝福 4调查问卷 21 会议通知 5个人信息
    private Integer isDel;//是否删除 1是 2否
    
    private String approvalPerson;//审批人
    private Date approvalTime = new Date();//审批时间
    
    
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getSmsType() {
		return smsType;
	}
	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatorID() {
		return creatorID;
	}
	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}
	public int getSmsNum() {
		return smsNum;
	}
	public void setSmsNum(int smsNum) {
		this.smsNum = smsNum;
	}
	public String getDestAddr() {
		return destAddr;
	}
	public void setDestAddr(String destAddr) {
		this.destAddr = destAddr;
	}
	public String getSmContent() {
		return smContent;
	}
	public void setSmContent(String smContent) {
		this.smContent = smContent;
	}
	
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getSubTime() {
		return subTime;
	}
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	public Integer getFsStatus() {
		return fsStatus;
	}
	public void setFsStatus(Integer fsStatus) {
		this.fsStatus = fsStatus;
	}
	public String getDestAddString() {
		return destAddString;
	}
	public void setDestAddString(String destAddString) {
		this.destAddString = destAddString;
	}
	public String getApprovalPerson() {
		return approvalPerson;
	}
	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
