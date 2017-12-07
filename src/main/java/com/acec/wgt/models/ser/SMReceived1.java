package com.acec.wgt.models.ser;


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
@Table(name = "t_wgt_shortmsg002_received")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class SMReceived1 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    private Integer SM_ID;//序号
	@Column(name="Orgaddr")
	private String OrgAddr;//源号码
	@Column(name="Destaddr")
	private String DestAddr;//目的号码
	@Column(name="SM_content")
	private String SM_Content;//信息内容
	@Column(name="Recvtime")
    private String RecvTime;

    private String Reserve1;//
    private String Reserve2;//
    @Column(name="SMtype")
    private String SMType;//23�?25 �?要回复短信时 下发才带有此�?
    private String MessageID;//
    @Column(name="Orgaddrtype")
    private Integer OrgAddrType;
    private Integer ActionID;
    @Column(name="ActionreasonID")
    private Integer ActionReasonID;
    private String ServiceID;//业务类型 用于运营商端对短信进行计费时使用
    @Column(name="Protocoltype")
    private String ProtocolType;
    private Integer Readed;
    private Integer Droped;
    
    private String companyId;
    
	public Integer getSM_ID() {
		return SM_ID;
	}
	public void setSM_ID(Integer sm_id) {
		SM_ID = sm_id;
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
	public String getRecvTime() {
		return RecvTime;
	}
	public void setRecvTime(String recvTime) {
		RecvTime = recvTime;
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
	public Integer getOrgAddrType() {
		return OrgAddrType;
	}
	public void setOrgAddrType(Integer orgAddrType) {
		OrgAddrType = orgAddrType;
	}
	public Integer getActionID() {
		return ActionID;
	}
	public void setActionID(Integer actionID) {
		ActionID = actionID;
	}
	public Integer getActionReasonID() {
		return ActionReasonID;
	}
	public void setActionReasonID(Integer actionReasonID) {
		ActionReasonID = actionReasonID;
	}
	public String getServiceID() {
		return ServiceID;
	}
	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}
	public String getProtocolType() {
		return ProtocolType;
	}
	public void setProtocolType(String protocolType) {
		ProtocolType = protocolType;
	}
	public Integer getReaded() {
		return Readed;
	}
	public void setReaded(Integer readed) {
		Readed = readed;
	}
	public Integer getDroped() {
		return Droped;
	}
	public void setDroped(Integer droped) {
		Droped = droped;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
