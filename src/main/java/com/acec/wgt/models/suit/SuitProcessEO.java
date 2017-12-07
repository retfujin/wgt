package com.acec.wgt.models.suit;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "tb_suit_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SuitProcessEO implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String ProDepartment;//处理单位
	private String Prodate ;//处理日期
	private String Proname;//处理人
	private String remark;//处理结果
	/** default constructor */
	//一对一
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="suitId")
	private SuitEO suitEO;
	// Constructors

	public SuitProcessEO() {
	}
	/** minimal constructor */
	public SuitProcessEO(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProDepartment() {
		return ProDepartment;
	}
	public void setProDepartment(String proDepartment) {
		ProDepartment = proDepartment;
	}
	public String getProname() {
		return Proname;
	}
	public void setProname(String proname) {
		Proname = proname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public SuitEO getSuitEO() {
		return suitEO;
	}
	public void setSuitEO(SuitEO suitEO) {
		this.suitEO = suitEO;
	}
	public String getProdate() {
		return Prodate;
	}
	public void setProdate(String prodate) {
		Prodate = prodate;
	}

}