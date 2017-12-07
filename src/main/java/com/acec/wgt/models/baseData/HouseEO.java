package com.acec.wgt.models.baseData;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdStrEntity;

/**
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tb_basedata_house")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HouseEO extends IdStrEntity {

    // 小区信息
    private Integer areaId;// 所属小区id（冗余字段，方便查询）
    private String areaName;
    // 楼栋信息
    private String edificeId;
    private String edificeName;
    // 房间信息
    private String cell;// 单元号
    private Integer layer;// 房间层数
    private String houseName;// 房间名称
    private String houseAddress;// 房间地址(冗余字段，方便查询)
    private String houseType;// 房间类型 (住宅、商铺)
    private Float buildnum;// 收费面积
    private String houseOrientation;// 房间朝向
    private Float poolArea; // 公摊面积
    private String layerType;// 分多层 和 高层

    // 业主信息
    private String ownerName;// 业主姓名
    private String sex;// 性别
    private String age;
    private String job;
    private String birthday;// 生日
    private String paperType;// 证件类型
    private String paperNum;// 证件号码
    private String phone;// 固定电话
    private String mobTel;// 移动电话
    private String familymember;// 家庭成员

    // 房间状态信息
    private String isSale = "入伙 ";// （房产公司拿来出售） 入伙 、未入伙、空置 ------ （房产公司拿来出租的）
    private String occupancyType;// 入住类型 入住、未入住
    private Date inDate;// 业主入住日期冗余字段，方便查询)
    private String housetypeSub2;// 子类型 自住、出租（针对已经出售的情况）

    private String remark;// 备注

    
    public Integer layercount;//总楼层
    private String paperNumtwo;// 证件号码2
    
    
    public HouseEO() {
    	super();
    }

    public HouseEO(String id) {
    	setId(id);
    }

    public Integer getAreaId() {
    	return areaId;
    }

    public void setAreaId(Integer areaId) {
    	this.areaId = areaId;
    }

    public String getAreaName() {
	return areaName;
    }

    public void setAreaName(String areaName) {
	this.areaName = areaName;
    }

    public String getEdificeId() {
	return edificeId;
    }

    public void setEdificeId(String edificeId) {
	this.edificeId = edificeId;
    }

    public String getEdificeName() {
	return edificeName;
    }

    public void setEdificeName(String edificeName) {
	this.edificeName = edificeName;
    }

    public String getCell() {
	return cell;
    }

    public void setCell(String cell) {
	this.cell = cell;
    }

    public Integer getLayer() {
	return layer;
    }

    public void setLayer(Integer layer) {
	this.layer = layer;
    }

    public String getHouseName() {
	return houseName;
    }

    public void setHouseName(String houseName) {
	this.houseName = houseName;
    }

    public String getHouseAddress() {
	return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
	this.houseAddress = houseAddress;
    }

    public String getHouseType() {
	return houseType;
    }

    public void setHouseType(String houseType) {
	this.houseType = houseType;
    }

    public Float getBuildnum() {
	return buildnum;
    }

    public void setBuildnum(Float buildnum) {
	this.buildnum = buildnum;
    }

    public String getHouseOrientation() {
	return houseOrientation;
    }

    public void setHouseOrientation(String houseOrientation) {
	this.houseOrientation = houseOrientation;
    }

    public Float getPoolArea() {
	return poolArea;
    }

    public void setPoolArea(Float poolArea) {
	this.poolArea = poolArea;
    }

    public String getLayerType() {
	return layerType;
    }

    public void setLayerType(String layerType) {
	this.layerType = layerType;
    }

    public String getOwnerName() {
	return ownerName;
    }

    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getAge() {
	return age;
    }

    public void setAge(String age) {
	this.age = age;
    }

    public String getJob() {
	return job;
    }

    public void setJob(String job) {
	this.job = job;
    }

    public String getBirthday() {
	return birthday;
    }

    public void setBirthday(String birthday) {
	this.birthday = birthday;
    }

    public String getPaperType() {
	return paperType;
    }

    public void setPaperType(String paperType) {
	this.paperType = paperType;
    }

    public String getPaperNum() {
	return paperNum;
    }

    public void setPaperNum(String paperNum) {
	this.paperNum = paperNum;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getMobTel() {
	return mobTel;
    }

    public void setMobTel(String mobTel) {
	this.mobTel = mobTel;
    }

    public String getFamilymember() {
	return familymember;
    }

    public void setFamilymember(String familymember) {
	this.familymember = familymember;
    }

    public String getIsSale() {
	return isSale;
    }

    public void setIsSale(String isSale) {
	this.isSale = isSale;
    }

    public String getOccupancyType() {
	return occupancyType;
    }

    public void setOccupancyType(String occupancyType) {
	this.occupancyType = occupancyType;
    }

    public Date getInDate() {
	return inDate;
    }

    public void setInDate(Date inDate) {
	this.inDate = inDate;
    }

    public String getHousetypeSub2() {
	return housetypeSub2;
    }

    public void setHousetypeSub2(String housetypeSub2) {
	this.housetypeSub2 = housetypeSub2;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    
    
    public Integer getLayercount() {
		return layercount;
	}

	public void setLayercount(Integer layercount) {
		this.layercount = layercount;
	}

	public String getPaperNumtwo() {
		return paperNumtwo;
	}

	public void setPaperNumtwo(String paperNumtwo) {
		this.paperNumtwo = paperNumtwo;
	}

	public void setNoneOwner() {
		this.ownerName = "";
		this.sex = "";
		this.age = "";
		this.birthday = "";
		this.job = "";
		this.paperType = "";
		this.paperNum = "";
		this.phone = "";
		this.mobTel = "";
		this.familymember = "";
		this.occupancyType = "";
		this.inDate = null;
		this.housetypeSub2="";
    }
}