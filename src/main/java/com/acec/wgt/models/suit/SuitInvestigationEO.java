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
@Table(name = "tb_suit_investigation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SuitInvestigationEO implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer id;
	private String Invname;//调查人
	private String Invdate ;//调查日期
	private String remark;//调查结果
	/** default constructor */
	//一对一
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="suitId")
	private SuitEO suitEO;
	// Constructors

	public SuitInvestigationEO() {
	}
	/** minimal constructor */
	public SuitInvestigationEO(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInvname() {
		return Invname;
	}
	public void setInvname(String invname) {
		Invname = invname;
	}
	public String getInvdate() {
		return Invdate;
	}
	public void setInvdate(String invdate) {
		Invdate = invdate;
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