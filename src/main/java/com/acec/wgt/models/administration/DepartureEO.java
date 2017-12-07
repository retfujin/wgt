package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 员工离职审批信息
 * 
 */
@Entity
@Table(name = "tb_administration_departure")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepartureEO extends IdEntity {
	private Integer areaId;
	private String areaName; 
	
	private String name;
	private String departmentname; 
	private String workdate;
	private String enddate;
	private String reason;//离职原因
	  
	private String contentid;
	private String content;
	
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
	
	
	private String workoneid;
	private String worktwoid;
	private String workthreeid;
	private String worknameone;
	private String worknametwo;
	private String worknamethree;
	private String workmanagerone;
	private String workmanagertwo;
	private String workmanagerthree;
	private String workremarkone;
	private String workremarktwo;
	private String workremarkthree;
	
	
    private String isdel;
    private int recordid;
    private String recordname;
    private String recordtime;
    private String state;
    
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getWorkoneid() {
		return workoneid;
	}
	public void setWorkoneid(String workoneid) {
		this.workoneid = workoneid;
	}
	public String getWorktwoid() {
		return worktwoid;
	}
	public void setWorktwoid(String worktwoid) {
		this.worktwoid = worktwoid;
	}
	public String getWorkthreeid() {
		return workthreeid;
	}
	public void setWorkthreeid(String workthreeid) {
		this.workthreeid = workthreeid;
	}
	public String getWorknameone() {
		return worknameone;
	}
	public void setWorknameone(String worknameone) {
		this.worknameone = worknameone;
	}
	public String getWorknametwo() {
		return worknametwo;
	}
	public void setWorknametwo(String worknametwo) {
		this.worknametwo = worknametwo;
	}
	public String getWorknamethree() {
		return worknamethree;
	}
	public void setWorknamethree(String worknamethree) {
		this.worknamethree = worknamethree;
	}
	public String getWorkmanagerone() {
		return workmanagerone;
	}
	public void setWorkmanagerone(String workmanagerone) {
		this.workmanagerone = workmanagerone;
	}
	public String getWorkmanagertwo() {
		return workmanagertwo;
	}
	public void setWorkmanagertwo(String workmanagertwo) {
		this.workmanagertwo = workmanagertwo;
	}
	public String getWorkmanagerthree() {
		return workmanagerthree;
	}
	public void setWorkmanagerthree(String workmanagerthree) {
		this.workmanagerthree = workmanagerthree;
	}
	public String getWorkremarkone() {
		return workremarkone;
	}
	public void setWorkremarkone(String workremarkone) {
		this.workremarkone = workremarkone;
	}
	public String getWorkremarktwo() {
		return workremarktwo;
	}
	public void setWorkremarktwo(String workremarktwo) {
		this.workremarktwo = workremarktwo;
	}
	public String getWorkremarkthree() {
		return workremarkthree;
	}
	public void setWorkremarkthree(String workremarkthree) {
		this.workremarkthree = workremarkthree;
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