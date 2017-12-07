package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 员工录用审批信息
 * 
 */
@Entity
@Table(name = "tb_administration_employed")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployedEO extends IdEntity {
	private Integer areaId;
	private String areaName;
	
	private String name;
	private String sex; 
	private String age;
	private String education;
	
	private String professional; //专业
	private String title;//职称
	private String position;
	
	private boolean resume;
	private boolean graduation;
	private boolean protitle;//专业资格
	private boolean cardid;
	private boolean guaranty;
	private boolean medical;
	private boolean beginmoney;
	private Date begintime;
	private boolean probationperiod;//试用期
	private String probationmonth;
		
	private String applyid;
	private String applyopinion;
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
    
    private int applyeo;
    
	 
	public int getApplyeo() {
		return applyeo;
	}
	public void setApplyeo(int applyeo) {
		this.applyeo = applyeo;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isResume() {
		return resume;
	}
	public void setResume(boolean resume) {
		this.resume = resume;
	}
	public boolean isGraduation() {
		return graduation;
	}
	public void setGraduation(boolean graduation) {
		this.graduation = graduation;
	}
	public boolean isProtitle() {
		return protitle;
	}
	public void setProtitle(boolean protitle) {
		this.protitle = protitle;
	}
	public boolean isCardid() {
		return cardid;
	}
	public void setCardid(boolean cardid) {
		this.cardid = cardid;
	}
	public boolean isGuaranty() {
		return guaranty;
	}
	public void setGuaranty(boolean guaranty) {
		this.guaranty = guaranty;
	}
	public boolean isMedical() {
		return medical;
	}
	public void setMedical(boolean medical) {
		this.medical = medical;
	}
	public boolean isBeginmoney() {
		return beginmoney;
	}
	public void setBeginmoney(boolean beginmoney) {
		this.beginmoney = beginmoney;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public boolean isProbationperiod() {
		return probationperiod;
	}
	public void setProbationperiod(boolean probationperiod) {
		this.probationperiod = probationperiod;
	}
	public String getProbationmonth() {
		return probationmonth;
	}
	public void setProbationmonth(String probationmonth) {
		this.probationmonth = probationmonth;
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