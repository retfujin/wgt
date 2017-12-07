package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 请假信息
 * 
 */
@Entity
@Table(name = "tb_administration_vacate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VacateEO extends IdEntity {
	private Integer areaId;
	private String areaName;
	
	private String name;
	private String position;
	private String departmentname;
	private boolean typeone;
	private boolean typetwo;
	private boolean typethree;
	private boolean typefour;
	private boolean typefive;
	private boolean typesix;
	private boolean typeseven;
	private boolean typeeight;
	private Date begindate;
	private Date enddate;
	private String number;
	private String reason;
	private String phone;
	private String address;
	
	private String applyname;
	private Date applydate;
	private String auditid;
	private String auditname;
	private Date auditdate;
	private String approvalid;
	private String approvalname;
	private Date approvaldate;
	
	private String content;
	
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public boolean isTypeone() {
		return typeone;
	}

	public void setTypeone(boolean typeone) {
		this.typeone = typeone;
	}

	public boolean isTypetwo() {
		return typetwo;
	}

	public void setTypetwo(boolean typetwo) {
		this.typetwo = typetwo;
	}

	public boolean isTypethree() {
		return typethree;
	}

	public void setTypethree(boolean typethree) {
		this.typethree = typethree;
	}

	public boolean isTypefour() {
		return typefour;
	}

	public void setTypefour(boolean typefour) {
		this.typefour = typefour;
	}

	public boolean isTypefive() {
		return typefive;
	}

	public void setTypefive(boolean typefive) {
		this.typefive = typefive;
	}

	public boolean isTypesix() {
		return typesix;
	}

	public void setTypesix(boolean typesix) {
		this.typesix = typesix;
	}

	public boolean isTypeseven() {
		return typeseven;
	}

	public void setTypeseven(boolean typeseven) {
		this.typeseven = typeseven;
	}

	public boolean isTypeeight() {
		return typeeight;
	}

	public void setTypeeight(boolean typeeight) {
		this.typeeight = typeeight;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public String getAuditname() {
		return auditname;
	}

	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}

	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	public String getApprovalid() {
		return approvalid;
	}

	public void setApprovalid(String approvalid) {
		this.approvalid = approvalid;
	}

	public String getApprovalname() {
		return approvalname;
	}

	public void setApprovalname(String approvalname) {
		this.approvalname = approvalname;
	}

	public Date getApprovaldate() {
		return approvaldate;
	}

	public void setApprovaldate(Date approvaldate) {
		this.approvaldate = approvaldate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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