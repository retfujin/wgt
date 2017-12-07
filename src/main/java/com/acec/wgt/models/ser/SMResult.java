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
@Table(catalog="DB_CustomSMS",schema="dbo",name = "wgt_SMResult")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class SMResult {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	@Column(name="SM_type")
	private Integer SM_Type;
	private Integer SM_ID;
	@Column(name="Subtime")
	private String SubTime;
	@Column(name="Orgaddr")
	private String OrgAddr;
	@Column(name="Destaddr")
	private String DestAddr;
	@Column(name="SM_content")
	private String SM_Content;
	@Column(name="Sendtime")
	private Date SendTime;
	private String MsgID;
	private String Service_ID;
	@Column(name="Fee_usertype")
	private String Fee_UserType;
	@Column(name="Fee_terminal_ID")
	private String Fee_Terminal_ID;
	@Column(name="Msg_fmt")
	private Integer Msg_Fmt;
	@Column(name="Feetype")
	private String FeeType;
	@Column(name="Feecode")
	private Integer FeeCode;
	
	@Column(name="Valid_time")
	private Date Valid_Time;
	@Column(name="Sent_time")
	private Date Sent_Time;
	@Column(name="Msg_status")
	private String Msg_Status;
	@Column(name="Trytimes")
	private Integer TryTimes;
	@Column(name="Recv_status")
	private String Recv_Status;
	@Column(name="Recv_time")
	private Date Recv_Time;
	private Integer Reserve1;
	private String Reserve2;
	private String CreatorID;
	private Integer SMType;
	private String MessageID;
	@Column(name="Destaddrtype")
	private Integer DestAddrType;
	@Column(name="Isdel")
	private String IsDel;
	public String getIsDel() {
		return IsDel;
	}
	public void setIsDel(String isDel) {
		IsDel = isDel;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer id) {
		ID = id;
	}
	public Integer getSM_Type() {
		return SM_Type;
	}
	public void setSM_Type(Integer type) {
		SM_Type = type;
	}
	public Integer getSM_ID() {
		return SM_ID;
	}
	public void setSM_ID(Integer sm_id) {
		SM_ID = sm_id;
	}
	public String getSubTime() {
		return SubTime;
	}
	public void setSubTime(String subTime) {
		SubTime = subTime;
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
	public String getMsgID() {
		return MsgID;
	}
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}
	public String getService_ID() {
		return Service_ID;
	}
	public void setService_ID(String service_ID) {
		Service_ID = service_ID;
	}
	public String getFee_UserType() {
		return Fee_UserType;
	}
	public void setFee_UserType(String fee_UserType) {
		Fee_UserType = fee_UserType;
	}
	public String getFee_Terminal_ID() {
		return Fee_Terminal_ID;
	}
	public void setFee_Terminal_ID(String fee_Terminal_ID) {
		Fee_Terminal_ID = fee_Terminal_ID;
	}
	public Integer getMsg_Fmt() {
		return Msg_Fmt;
	}
	public void setMsg_Fmt(Integer msg_Fmt) {
		Msg_Fmt = msg_Fmt;
	}
	public String getFeeType() {
		return FeeType;
	}
	public void setFeeType(String feeType) {
		FeeType = feeType;
	}
	public Integer getFeeCode() {
		return FeeCode;
	}
	public void setFeeCode(Integer feeCode) {
		FeeCode = feeCode;
	}
	public Date getValid_Time() {
		return Valid_Time;
	}
	public void setValid_Time(Date valid_Time) {
		Valid_Time = valid_Time;
	}
	public Date getSent_Time() {
		return Sent_Time;
	}
	public void setSent_Time(Date sent_Time) {
		Sent_Time = sent_Time;
	}
	public String getMsg_Status() {
		return Msg_Status;
	}
	public void setMsg_Status(String msg_Status) {
		Msg_Status = msg_Status;
	}
	public Integer getTryTimes() {
		return TryTimes;
	}
	public void setTryTimes(Integer tryTimes) {
		TryTimes = tryTimes;
	}
	public String getRecv_Status() {
		return Recv_Status;
	}
	public void setRecv_Status(String recv_Status) {
		Recv_Status = recv_Status;
	}
	public Date getRecv_Time() {
		return Recv_Time;
	}
	public void setRecv_Time(Date recv_Time) {
		Recv_Time = recv_Time;
	}
	public Integer getReserve1() {
		return Reserve1;
	}
	public void setReserve1(Integer reserve1) {
		Reserve1 = reserve1;
	}
	public String getReserve2() {
		return Reserve2;
	}
	public void setReserve2(String reserve2) {
		Reserve2 = reserve2;
	}
	public String getCreatorID() {
		return CreatorID;
	}
	public void setCreatorID(String creatorID) {
		CreatorID = creatorID;
	}
	public Integer getSMType() {
		return SMType;
	}
	public void setSMType(Integer type) {
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
	
	
}
