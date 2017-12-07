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
@Table(name = "tb_sys_backup")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SysBackUpEO implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String operator;//备份人员
	private String filepath;//备份的路径
	private Date backuptime = new Date();//备份日期
	private String description;//备注
	// Constructors

	/** default constructor */
	public SysBackUpEO() {
	}
	
	
	/** minimal constructor */
	public SysBackUpEO(Integer id) {
		this.id = id;
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


	

	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	public Date getBackuptime() {
		return backuptime;
	}


	public void setBackuptime(Date backuptime) {
		this.backuptime = backuptime;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	


	

}