package com.acec.wgt.models.sys;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_sys_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SysRoleEO extends com.acec.wgt.models.IdEntity{


	private String companyId;//所属物业公司id
	private String companyName;//所属物业公司名称
	private String name;//角色名
	private String description;//角色描述
	
	
	private List<SysModelEO> models;
	
	// Constructors

	/** default constructor */
	public SysRoleEO() {
	}

	


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String toString(){
		return "id="+this.getId()+";name="+this.getName();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch=FetchType.LAZY)
	public List<SysModelEO> getModels() {
		return models;
	}

	public void setModels(List<SysModelEO> models) {
		this.models = models;
	}




	public String getCompanyId() {
		return companyId;
	}




	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}




	public String getCompanyName() {
		return companyName;
	}




	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}