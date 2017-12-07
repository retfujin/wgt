package com.acec.wgt.models.sys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 流程步骤对象
 * 
 * @author lau
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_sys_process_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class ProcessModuleEO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; // 步骤id
	private Integer processId; // 所属流程id
	private Integer moduleStep; // 执行步骤排号
	private String moduleName;// 步骤名称
	private String description;// 步骤描述

	private String executorId;// 执行人id
	private String executorName;// 执行人姓名
	private String executorOpinion;// 执行人意见
	private Date executorTime;// 执行时间

	private int state;// 执行状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Integer getModuleStep() {
		return moduleStep;
	}

	public void setModuleStep(Integer moduleStep) {
		this.moduleStep = moduleStep;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getExecutorId() {
		return executorId;
	}

	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}

	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}

	public String getExecutorOpinion() {
		return executorOpinion;
	}

	public void setExecutorOpinion(String executorOpinion) {
		this.executorOpinion = executorOpinion;
	}

	public Date getExecutorTime() {
		return executorTime;
	}

	public void setExecutorTime(Date executorTime) {
		this.executorTime = executorTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
