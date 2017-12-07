package com.acec.wgt.models.sys;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_sys_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SysLogEO implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String operator;//操作人员
	private String moduleName;//操作的模块
	private Date workTime ;//操作时间

	// Constructors

	/** default constructor */
	public SysLogEO() {
	}
	
	
	/** minimal constructor */
	public SysLogEO(Integer id) {
		this.id = id;
	}
	
	public SysLogEO(String moduleName,String operator){
	
		this.operator=operator;
		this.moduleName=moduleName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	public Date getWorkTime() {
		return workTime;
	}


	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
}