package com.acec.wgt.models.ser;

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
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tbl_SmsSendBak")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 5)
public class SmsSendBak {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//序号
	private String companyId;//公司id
	private String companyName;//公司名称
	
    private String creatorID; //用户ID
    private String userName;//发送人
    private int smsNum;//发送数量
    private String destAddr;//手机号
    private String destAddString;//存放楼栋、房间名字等
    
    private String smContent;//信息内容
    private Date sendTime;//发送时间
    private Date subTime = new Date();//当前时间
    private Integer fsStatus;//发送状态 1正在审核  2已经发送  3审核不通过
    private Integer smsType;//1公告发布 2用户咨询 3节日祝福 4调查问卷 21 会议通知  23 调查问卷  24短信版催缴 25房产批量发送 26员工生日
    private Integer isDel;//是否删除 1是 2否
    private String qusetionId;//是调查问卷的保存question表对应Id//是短信版催费的保存短信催费信息group表对应id 
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
		if(null==sendTime || sendTime.equals(""))
			sendTime = new Date();
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
	public String getQusetionId() {
		return qusetionId;
	}
	public void setQusetionId(String qusetionId) {
		this.qusetionId = qusetionId;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
