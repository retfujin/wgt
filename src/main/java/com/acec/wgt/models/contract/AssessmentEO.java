package com.acec.wgt.models.contract;
 
import java.sql.Date;

import javax.persistence.Entity; 
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_contract_assessment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssessmentEO extends IdEntity {
	//劳动合同续签表
	private int areaId;
	private String areaName;
    private String name;
    private String age;
    private String sex;
    private String education;
    private String departmentname;
    private String position; 
     
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
	
	
	private boolean schedule;
	private boolean termination;
	private String mid;
	private String mname;
	private Date mdate;
    private String mapproval;//总经理批准
    
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
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
	public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
	public boolean isTermination() {
		return termination;
	}
	public void setTermination(boolean termination) {
		this.termination = termination;
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public String getMapproval() {
		return mapproval;
	}
	public void setMapproval(String mapproval) {
		this.mapproval = mapproval;
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