package com.acec.wgt.models.ser;

import java.util.Date;

import javax.persistence.Column;
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

@Table(catalog="DB_CustomSMS",schema="dbo",name = "tbl_SMSendtask")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class SMSendTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
//	public static final int SINGLE_SEND_TYPE = 1;
//    public static final int MULTI_SEND_TYPE = 2;
//    public static final int PPM_SEND_TYPE = 3;
//    public static final int DAILY_OPERATION_TYPE = 4;
//    public static final int WAP_PUSH_TYPE = 5;
//    public static final String BLACK_MODE_VALIDATE = "0";
//    public static final String WHITE_MODE_VALIDATE = "1";
//    public static final String BLACK_AND_WHITE_INVALIDATE = "2";
//    public static final String DAILY_AWAKE_TYPE = "51";
//    public static final String MEETING_INFOR_TYPE = "52";
//    public static final String BULLETIN_SEND_TYPE = "53";
//    public static final String QUESTIONS_RESEARCH_TYPE = "54";
//    public static final String SHORT_MESSAGE_VOTE_TYPE = "55";
//    public static final String SHORT_MESSAGE_ANSWER_TYPE = "56";
	
    private Integer ID;//序号
	@Column(name="CreatorID")
    private String CreatorID; //用户ID
	@Column(name="Taskname")
    private String TaskName;//任务名称
	@Column(name="Smsendednum")
    private int SmSendedNum;//发送数量
	@Column(name="Operationtype")
    private String OperationType;//
	@Column(name="Suboperationtype")
    private String SubOperationType;//
	@Column(name="Sendtype")
    private Integer SendType;//
	@Column(name="Orgaddr")
    private String OrgAddr;//特服号
	
	@Column(name="Destaddr")
    private String DestAddr;//手机号
	
	@Column(name="SM_content")
    private String SM_Content;//信息内容
	
	@Column(name="Sendtime")
    private Date SendTime;//发送时间
	@Column(name="Needstatereport")
    private Integer NeedStateReport;//是否要状态报告
	@Column(name="ServiceID")
    private String ServiceID;//业务类型 用于运营商端对短信进行计费时使用
	@Column(name="Feetype")
    private String FeeType;//资费类型 01表示对用户免费
	@Column(name="Feecode")
    private String FeeCode;//资费代码 0免费
	@Column(name="MsgID")
    private String MsgID;//
	@Column(name="SMtype")
    private String SMType;// 参看 tbl_smType
	@Column(name="MessageID")
    private String MessageID;//
	@Column(name="Destaddrtype")
    private Integer DestAddrType;//
    @Column(name="Subtime")
    private String SubTime;//当前时间
    @Column(name="Taskstatus")
    private Integer TaskStatus;//
    @Column(name="Sendlevel")
    private Integer SendLevel;//发送级别
    @Column(name="Sendstate")
    private Integer SendState =0;//
    @Column(name="Trytimes")
    private Integer TryTimes;//尝试次数

    private Integer Count;//
    
    private Integer SuccessID;//

    private String Reserve1;//

    private String Reserve2;//

	public Integer getID() {
		return ID;
	}
	public void setID(Integer id) {
		ID = id;
	}
	public String getCreatorID() {
		return CreatorID;
	}
	public void setCreatorID(String creatorID) {
		CreatorID = creatorID;
	}
	public String getTaskName() {
		return TaskName;
	}
	public void setTaskName(String taskName) {
		TaskName = taskName;
	}
	public int getSmSendedNum() {
		return SmSendedNum;
	}
	public void setSmSendedNum(int smSendedNum) {
		SmSendedNum = smSendedNum;
	}
	public String getOperationType() {
		return OperationType;
	}
	public void setOperationType(String operationType) {
		OperationType = operationType;
	}
	public String getSubOperationType() {
		return SubOperationType;
	}
	public void setSubOperationType(String subOperationType) {
		SubOperationType = subOperationType;
	}
	public Integer getSendType() {
		return SendType;
	}
	public void setSendType(Integer sendType) {
		SendType = sendType;
	}
	public String getOrgAddr() {
		return OrgAddr;
	}
	public void setOrgAddr(String orgAddr) {
		OrgAddr = orgAddr;
	}
	public String getDestAddr() {
		return DestAddr;
	}
	public void setDestAddr(String destAddr) {
		DestAddr = destAddr;
	}
	public String getSM_Content() {
		return SM_Content;
	}
	public void setSM_Content(String content) {
		SM_Content = content;
	}
	
	public Date getSendTime() {
		return SendTime;
	}
	public void setSendTime(Date sendTime) {
		SendTime = sendTime;
	}
	public Integer getNeedStateReport() {
		return NeedStateReport;
	}
	public void setNeedStateReport(Integer needStateReport) {
		NeedStateReport = needStateReport;
	}
	public String getServiceID() {
		return ServiceID;
	}
	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}
	public String getFeeType() {
		return FeeType;
	}
	public void setFeeType(String feeType) {
		FeeType = feeType;
	}
	public String getFeeCode() {
		return FeeCode;
	}
	public void setFeeCode(String feeCode) {
		FeeCode = feeCode;
	}
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getSMType() {
		return SMType;
	}
	public void setSMType(String type) {
		SMType = type;
	}
	public String getMessageID() {
		return MessageID;
	}
	public void setMessageID(String messageID) {
		MessageID = messageID;
	}
	public Integer getDestAddrType() {
		return DestAddrType;
	}
	public void setDestAddrType(Integer destAddrType) {
		DestAddrType = destAddrType;
	}
	public String getSubTime() {
		return SubTime;
	}
	public void setSubTime(String subTime) {
		SubTime = subTime;
	}
	public Integer getTaskStatus() {
		return TaskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		TaskStatus = taskStatus;
	}
	public Integer getSendLevel() {
		return SendLevel;
	}
	public void setSendLevel(Integer sendLevel) {
		SendLevel = sendLevel;
	}
	public Integer getSendState() {
		return SendState;
	}
	public void setSendState(Integer sendState) {
		SendState = sendState;
	}
	public Integer getTryTimes() {
		return TryTimes;
	}
	public void setTryTimes(Integer tryTimes) {
		TryTimes = tryTimes;
	}
	public Integer getCount() {
		return Count;
	}
	public void setCount(Integer count) {
		Count = count;
	}
	public Integer getSuccessID() {
		return SuccessID;
	}
	public void setSuccessID(Integer successID) {
		SuccessID = successID;
	}
	public String getReserve1() {
		return Reserve1;
	}
	public void setReserve1(String reserve1) {
		Reserve1 = reserve1;
	}
	public String getReserve2() {
		return Reserve2;
	}
	public void setReserve2(String reserve2) {
		Reserve2 = reserve2;
	}
    
}
