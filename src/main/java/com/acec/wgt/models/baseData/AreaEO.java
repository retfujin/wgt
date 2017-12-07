package com.acec.wgt.models.baseData;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

/**
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_basedata_area")
//宽松的缓存策略.

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AreaEO{
	private Integer id;//小区编号
	private String areaName;//小区名称
	private String areaplace;//小区位置
	private String developer;//开发商
	private String build;//建筑商
	private Integer seatNum;//总幢数
	private Integer flatletNum;//总套数
	private Float occupyNum;//占地面积
	private Float buildNum;//建筑面积
	private Float useNum;//使用面积
	private Integer populationNum;//人口数
	private Date diskTime;//接盘时间
	private String areaManager;//管理处负责人
	private String mobile;//窗口电话
	private Float poolRatio;//公摊比例
	private Integer orderByArea;//排序
	
	private String companyId;//物业公司编号
	private String companyName;//物业公司名称
	
	
	private Integer isChecked=0;
	// Constructors

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaplace() {
		return areaplace;
	}

	public void setAreaplace(String areaplace) {
		this.areaplace = areaplace;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public Integer getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}

	public Integer getFlatletNum() {
		return flatletNum;
	}

	public void setFlatletNum(Integer flatletNum) {
		this.flatletNum = flatletNum;
	}

	public Float getOccupyNum() {
		return occupyNum;
	}

	public void setOccupyNum(Float occupyNum) {
		this.occupyNum = occupyNum;
	}

	public Float getBuildNum() {
		return buildNum;
	}

	public void setBuildNum(Float buildNum) {
		this.buildNum = buildNum;
	}

	public Float getUseNum() {
		return useNum;
	}

	public void setUseNum(Float useNum) {
		this.useNum = useNum;
	}

	public Integer getPopulationNum() {
		return populationNum;
	}

	public void setPopulationNum(Integer populationNum) {
		this.populationNum = populationNum;
	}
	@Transient
	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}
	public Date getDiskTime() {
		return diskTime;
	}

	public void setDiskTime(Date diskTime) {
		this.diskTime = diskTime;
	}

	public String getAreaManager() {
		return areaManager;
	}

	public void setAreaManager(String areaManager) {
		this.areaManager = areaManager;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderByArea() {
		return orderByArea;
	}

	public void setOrderByArea(Integer orderByArea) {
		this.orderByArea = orderByArea;
	}
	public Float getPoolRatio() {
		return poolRatio;
	}

	public void setPoolRatio(Float poolRatio) {
		this.poolRatio = poolRatio;
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