package com.acec.wgt.models.contract;
 
import java.sql.Date;

import javax.persistence.Entity; 
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_contract_termination")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TerminationEO extends IdEntity {
	//终止劳动合同
	private int areaId;
	private String areaName;
    private String name;
    private String age;
    private String sex;
    private String education;
    private String departmentname;
    private String position; 
    
    private Date begindate;
    private Date enddate;
    private Date endworkdate;//最后工作日
    
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
	public String getDepartid() {
		return departid;
	}
	public void setDepartid(String departid) {
		this.departid = departid;
	}
	public String getOfficeid() {
		return officeid;
	}
	public void setOfficeid(String officeid) {
		this.officeid = officeid;
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
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Date getEndworkdate() {
		return endworkdate;
	}
	public void setEndworkdate(Date endworkdate) {
		this.endworkdate = endworkdate;
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