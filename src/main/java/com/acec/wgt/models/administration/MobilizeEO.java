package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 内部员工调动信息
 * 
 */
@Entity
@Table(name = "tb_administration_mobilize")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MobilizeEO extends IdEntity {
	private int areaId;
	private String areaName;
	private int newareaId;
	private String newareaName;
	
	private String name;
	private String departmentname; 
	private String position;
	private Date mobilizedate;//调任时间
	private String newdepartname;
	private String newposition;
	private String reason;//
	private String content;
	 
	private String recipient;
	private Date recipientdate;
	
	private String applyid;
	private String applyopinion;//原部门
	private String applyname;
	private Date applydate;
	
	private String departid;
	private String departopinion;
	private String departname;
	private Date departdate;
	
	private String departoneid;
	private String departoneopinion;
	private String departonename;
	private Date departonedate;
	
	private String officeid;
	private String officeopinion;
	private String officename;
	private Date officedate;
	
	private String viceid;
	private String viceopinion;
	private String vicename;
	private Date vicedate;
	
	private String managerid;
	private String manageropinion;
	private String managername;
	private Date managerdate;
		
    private String isdel;
    private int recordid;
    private String recordname;
    private String recordtime;
    private String state;
    
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public int getNewareaId() {
		return newareaId;
	}
	public void setNewareaId(int newareaId) {
		this.newareaId = newareaId;
	}
	public String getNewareaName() {
		return newareaName;
	}
	public void setNewareaName(String newareaName) {
		this.newareaName = newareaName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getMobilizedate() {
		return mobilizedate;
	}
	public void setMobilizedate(Date mobilizedate) {
		this.mobilizedate = mobilizedate;
	}
	public String getNewdepartname() {
		return newdepartname;
	}
	public void setNewdepartname(String newdepartname) {
		this.newdepartname = newdepartname;
	}
	public String getNewposition() {
		return newposition;
	}
	public void setNewposition(String newposition) {
		this.newposition = newposition;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public Date getRecipientdate() {
		return recipientdate;
	}
	public void setRecipientdate(Date recipientdate) {
		this.recipientdate = recipientdate;
	}
	public String getApplyid() {
		return applyid;
	}
	public void setApplyid(String applyid) {
		this.applyid = applyid;
	}
	public String getApplyopinion() {
		return applyopinion;
	}
	public void setApplyopinion(String applyopinion) {
		this.applyopinion = applyopinion;
	}
	public String getApplyname() {
		return applyname;
	}
	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}
	public Date getApplydate() {
		return applydate;
	}
	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}
	public String getDepartid() {
		return departid;
	}
	public void setDepartid(String departid) {
		this.departid = departid;
	}
	public String getDepartopinion() {
		return departopinion;
	}
	public void setDepartopinion(String departopinion) {
		this.departopinion = departopinion;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public Date getDepartdate() {
		return departdate;
	}
	public void setDepartdate(Date departdate) {
		this.departdate = departdate;
	}
	
	public String getDepartoneid() {
		return departoneid;
	}
	public void setDepartoneid(String departoneid) {
		this.departoneid = departoneid;
	}
	public String getDepartoneopinion() {
		return departoneopinion;
	}
	public void setDepartoneopinion(String departoneopinion) {
		this.departoneopinion = departoneopinion;
	}
	public String getDepartonename() {
		return departonename;
	}
	public void setDepartonename(String departonename) {
		this.departonename = departonename;
	}
	public Date getDepartonedate() {
		return departonedate;
	}
	public void setDepartonedate(Date departonedate) {
		this.departonedate = departonedate;
	}
	public String getOfficeid() {
		return officeid;
	}
	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	public String getOfficeopinion() {
		return officeopinion;
	}
	public void setOfficeopinion(String officeopinion) {
		this.officeopinion = officeopinion;
	}
	public String getOfficename() {
		return officename;
	}
	public void setOfficename(String officename) {
		this.officename = officename;
	}
	public Date getOfficedate() {
		return officedate;
	}
	public void setOfficedate(Date officedate) {
		this.officedate = officedate;
	}
	public String getViceid() {
		return viceid;
	}
	public void setViceid(String viceid) {
		this.viceid = viceid;
	}
	public String getViceopinion() {
		return viceopinion;
	}
	public void setViceopinion(String viceopinion) {
		this.viceopinion = viceopinion;
	}
	public String getVicename() {
		return vicename;
	}
	public void setVicename(String vicename) {
		this.vicename = vicename;
	}
	public Date getVicedate() {
		return vicedate;
	}
	public void setVicedate(Date vicedate) {
		this.vicedate = vicedate;
	}
	public String getManagerid() {
		return managerid;
	}
	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}
	public String getManageropinion() {
		return manageropinion;
	}
	public void setManageropinion(String manageropinion) {
		this.manageropinion = manageropinion;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public Date getManagerdate() {
		return managerdate;
	}
	public void setManagerdate(Date managerdate) {
		this.managerdate = managerdate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	
	
}