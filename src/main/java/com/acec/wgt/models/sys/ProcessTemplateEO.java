package com.acec.wgt.models.sys;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 流程模板
 * 
 * @author lau
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_sys_process_template")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class ProcessTemplateEO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	//流程id
	private String employeeId; // 员工id
	private String employeeName; // 员工姓名
	private String processCode; // 模块代码
	private String processName; // 模块名称
	private String name; // 流程名
	private String description; // 流程描述

	@Transient
	private List<ProcessTemplateModuleEO> modules;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	

	

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProcessTemplateModuleEO> getModules() {
		return modules;
	}

	public void setModules(List<ProcessTemplateModuleEO> modules) {
		this.modules = modules;
	}
}
