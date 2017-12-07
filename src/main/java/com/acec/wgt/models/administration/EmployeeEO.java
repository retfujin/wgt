package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

/**
 * 员工信息
 * 
 * 
 */
@Entity
@Table(name = "tb_administration_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployeeEO extends IdEntity {

	private int areaId;
	private String areaName;
	
	private String departname;// 部门
    private String name; // 姓名
    private String sex;// 性别
    private String position;// 岗位
    private String thetitle;//职称
    private Date brithday;// 出生日期
    private String age;
    private String education;
    private String cardid;//身份证
    private String phone;// 电话
    private Date entrydate;// 入职时间
    
    private Date beginlabordate; //劳动合同
    private Date endlabordate;//劳动合同
    private String endyear;//时间期限  
    
    private Date enddate;//离职时间    
    private String remarks;// 备注
    
    private String contracttype;//合同类型   劳动合同1   劳务合同0
    private String insurance;//是否购买意外伤害险    是1  否0
    private String employeetype;//  gl管理   yx一线
    
    private String isdel;
    private int recordid;
    private String recordname;
    private String recordtime;
    
    
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
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getThetitle() {
		return thetitle;
	}
	public void setThetitle(String thetitle) {
		this.thetitle = thetitle;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
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
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}
	public Date getBeginlabordate() {
		return beginlabordate;
	}
	public void setBeginlabordate(Date beginlabordate) {
		this.beginlabordate = beginlabordate;
	}
	public Date getEndlabordate() {
		return endlabordate;
	}
	public void setEndlabordate(Date endlabordate) {
		this.endlabordate = endlabordate;
	}
	public String getEndyear() {
		return endyear;
	}
	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getContracttype() {
		return contracttype;
	}
	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
	public String getEmployeetype() {
		return employeetype;
	}
	public void setEmployeetype(String employeetype) {
		this.employeetype = employeetype;
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
}