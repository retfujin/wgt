package com.acec.wgt.models.contract;
 
import java.sql.Date;

import javax.persistence.Entity; 
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_contract_demand")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemandEO extends IdEntity {
	//培训需求申请
	private int areaId;
	private String areaName;
	private Date filldate;//填表日期
	
    private String departname;
    private String applyname;//申请人
    private String way;//方式
    private String arrange;//
    
    private String objectname;
    private String reason;
    private String content;
    
    private String officeid;
    private String officeopinion;
	private String officename;
	private Date officedate;
	
	private String leadershipid;
	private String leadershipopinion;
	private String leadershipname;
	private Date leadershipdate;
    
    private String isdel;
    private Integer recordid;
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
	public Date getFilldate() {
		return filldate;
	}
	public void setFilldate(Date filldate) {
		this.filldate = filldate;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getApplyname() {
		return applyname;
	}
	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getArrange() {
		return arrange;
	}
	public void setArrange(String arrange) {
		this.arrange = arrange;
	}
	
	public String getObjectname() {
		return objectname;
	}
	public void setObjectname(String objectname) {
		this.objectname = objectname;
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
	public String getOfficeid() {
		return officeid;
	}
	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	public String getLeadershipid() {
		return leadershipid;
	}
	public void setLeadershipid(String leadershipid) {
		this.leadershipid = leadershipid;
	}
	public String getLeadershipopinion() {
		return leadershipopinion;
	}
	public void setLeadershipopinion(String leadershipopinion) {
		this.leadershipopinion = leadershipopinion;
	}
	public String getLeadershipname() {
		return leadershipname;
	}
	public void setLeadershipname(String leadershipname) {
		this.leadershipname = leadershipname;
	}
	public Date getLeadershipdate() {
		return leadershipdate;
	}
	public void setLeadershipdate(Date leadershipdate) {
		this.leadershipdate = leadershipdate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
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