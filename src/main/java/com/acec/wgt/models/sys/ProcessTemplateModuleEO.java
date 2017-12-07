package com.acec.wgt.models.sys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 流程模板模块组成
 * 
 * @author lau
 * 
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_sys_process_template_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class ProcessTemplateModuleEO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer templateId;
	private Integer moduleStep;
	private String moduleName;
	private String moduleExecutorId;
	private String moduleExecutorName;
	private String moduleDescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
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

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}


}
