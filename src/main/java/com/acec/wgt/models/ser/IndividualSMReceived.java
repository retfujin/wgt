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
@Table(name = "tb_individualSMReceived")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class IndividualSMReceived {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    private Integer SM_ID;//序号
	private String OrgAddr;//源号码
	private String DestAddr;//目的号码
	private String SM_Content;//信息内容
    private String RecvTime;
    private String SMType;//23�?25 �?要回复短信时 下发才带有此�?
    private Integer OrgAddrType;
    private String ServiceID;//业务类型 用于运营商端对短信进行计费时使用
    private String ProtocolType;
    private Integer Readed;
    private Integer Droped;
    private String CreatorID;
	public String getCreatorID() {
		return CreatorID;
	}
	public void setCreatorID(String creatorID) {
		CreatorID = creatorID;
	}
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
	public String getSMType() {
		return SMType;
	}
	public void setSMType(String type) {
		SMType = type;
	}
	public Integer getOrgAddrType() {
		return OrgAddrType;
	}
	public void setOrgAddrType(Integer orgAddrType) {
		OrgAddrType = orgAddrType;
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
}
