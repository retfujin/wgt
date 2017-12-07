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
@Table(name = "tb_suit_visit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SuitVisitEO implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String Visitname;//回访人

	private String Visitdate ;//回访日期
	private String Visittype;//回访方式
	private String Satis;//满意程度
	private String remark;//修改意见
	/** default constructor */
	//一对一
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="suitId")
	private SuitEO suitEO;
	// Constructors

	public SuitVisitEO() {
	}
	/** minimal constructor */
	public SuitVisitEO(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVisitname() {
		return Visitname;
	}
	public void setVisitname(String visitname) {
		Visitname = visitname;
	}
	public String getVisitdate() {
		return Visitdate;
	}
	public void setVisitdate(String visitdate) {
		Visitdate = visitdate;
	}
	public String getVisittype() {
		return Visittype;
	}
	public void setVisittype(String visittype) {
		Visittype = visittype;
	}
	public String getSatis() {
		return Satis;
	}
	public void setSatis(String satis) {
		Satis = satis;
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

}