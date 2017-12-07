package com.acec.wgt.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TreesVO implements java.io.Serializable {

	private String id;
	private String Name;//名称
	private String gread;//级别
	private Integer supId;//上级ID
	private String url;



	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/** default constructor */
	public TreesVO() {
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGread() {
		return gread;
	}

	public void setGread(String gread) {
		this.gread = gread;
	}

	public Integer getSupId() {
		return supId;
	}

	public void setSupId(Integer supId) {
		this.supId = supId;
	}

	public TreesVO(String id, String name, String gread, Integer supId,
			String url) {
		super();
		this.id = id;
		this.Name = name;
		this.gread = gread;
		this.supId = supId;
		if(!"".equals(url))
			this.url = url+id;
	}
	public TreesVO(Integer id, String name, String gread, Integer supId,
			String url) {
		super();
		this.id = id.toString();
		Name = name;
		this.gread = gread;
		this.supId = supId;
		if(!"".equals(url))
			this.url = url+id;
	}
	
	public String toString(){
		return "id="+id+";name="+Name+";gread="+gread+";supId="+supId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}