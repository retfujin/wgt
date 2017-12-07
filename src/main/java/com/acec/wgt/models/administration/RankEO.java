package com.acec.wgt.models.administration;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_administration_rank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RankEO extends IdEntity {

    private String name;
    private String salary;
    private DepartmentEO department;
    private String description;
    private String remarks;
    private String isdel;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSalary() {
	return salary;
    }

    public void setSalary(String salary) {
	this.salary = salary;
    }

    public String getRemarks() {
	return remarks;
    }

    public void setRemarks(String remarks) {
	this.remarks = remarks;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public DepartmentEO getDepartment() {
	return department;
    }

    public void setDepartment(DepartmentEO department) {
	this.department = department;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getIsdel() {
	return isdel;
    }

    public void setIsdel(String isdel) {
	this.isdel = isdel;
    }
}
