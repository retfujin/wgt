package com.acec.wgt.models.sys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "tb_sys_model")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SysModelEO   implements Comparable  {
	
	private Integer id;
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	private Integer grade;
	private String name;
	private String url;
	
	private String explain1;
	private String iconName;
	private Integer belongId;
	
	
	private Integer isZhong=0;
	// Constructors

	/** default constructor */
	public SysModelEO() {
	}

	




	public String toString(){
		return "id="+this.getId()+";name="+this.getName();
	}

	public Integer getGrade() {
		return grade;
	}


	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	public String getExplain1() {
		return explain1;
	}


	public void setExplain1(String explain1) {
		this.explain1 = explain1;
	}


	public String getIconName() {
		return iconName;
	}



	public void setIconName(String iconName) {
		this.iconName = iconName;
	}



	public Integer getBelongId() {
		return belongId;
	}



	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}


	@Transient
	public Integer getIsZhong() {
		return isZhong;
	}



	public void setIsZhong(Integer isZhong) {
		this.isZhong = isZhong;
	}






	public int compareTo(Object arg0) {
			
		if(this.getId()>=((SysModelEO)arg0).getId())
			return 1;
		else
			return 0;
	}

	
	

}