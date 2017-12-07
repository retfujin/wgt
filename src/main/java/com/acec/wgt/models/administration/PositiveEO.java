package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 员工转正审批信息
 * 
 */
@Entity
@Table(name = "tb_administration_positive")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PositiveEO extends IdEntity {
	private Integer areaId;
	private String areaName;
	private String name;
	private String departmentname;
	private String position;	
	private String probationmonth;
	
	private String worksummaryid;
	private String worksummary;
	private String worksummaryname;
	private Date worksummarydate;
	
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getProbationmonth() {
		return probationmonth;
	}
	public void setProbationmonth(String probationmonth) {
		this.probationmonth = probationmonth;
	}
	
	public String getWorksummaryid() {
		return worksummaryid;
	}
	public void setWorksummaryid(String worksummaryid) {
		this.worksummaryid = worksummaryid;
	}
	public String getWorksummary() {
		return worksummary;
	}
	public void setWorksummary(String worksummary) {
		this.worksummary = worksummary;
	}
	public String getWorksummaryname() {
		return worksummaryname;
	}
	public void setWorksummaryname(String worksummaryname) {
		this.worksummaryname = worksummaryname;
	}
	public Date getWorksummarydate() {
		return worksummarydate;
	}
	public void setWorksummarydate(Date worksummarydate) {
		this.worksummarydate = worksummarydate;
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