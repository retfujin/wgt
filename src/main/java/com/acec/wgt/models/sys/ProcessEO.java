package com.acec.wgt.models.sys;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 流程对象
 * 
 * @author lau
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_sys_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class ProcessEO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; // 流程id
	
	private String employeeId; // 员工id
	private String employeeName; // 员工姓名
	
	private String processCode; // 流程模版编号
	private String description;// 流程描述
	private Integer step; // 进行步骤  //99:表示结束
	private Integer state; // 流程状态   0 待处理  ，1 通过  2 不通过
	private String detail; // 流程明细
	private String beginDate;//启动日期
	private String moduleName;//环节名称
	private String moduleExecutorId; // 下一流程处理人员工id
	private String moduleExecutorName; // 下一流程处理人员工姓名
	
	private String url; // 处理页面地址

	@Transient
	private List<ProcessModuleEO> modules = new ArrayList<ProcessModuleEO>();// 流程步骤列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}





	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getModuleExecutorId() {
		return moduleExecutorId;
	}

	public void setModuleExecutorId(String moduleExecutorId) {
		this.moduleExecutorId = moduleExecutorId;
	}

	public String getModuleExecutorName() {
		return moduleExecutorName;
	}

	public void setModuleExecutorName(String moduleExecutorName) {
		this.moduleExecutorName = moduleExecutorName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ProcessModuleEO> getModules() {
		return modules;
	}

	public void setModules(List<ProcessModuleEO> modules) {
		this.modules = modules;
	}

}
