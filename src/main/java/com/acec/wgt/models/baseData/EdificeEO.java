package com.acec.wgt.models.baseData;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_basedata_edifice")
// 更宽松的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EdificeEO extends IdStrEntity {

    public EdificeEO() {
    }

    public EdificeEO(String id) {
	setId(id);
    }

    private String edificeName;// 楼盘名称
    private Float buildNum;// 建筑面积
    private String cellNum;// 单元数
    private String layerNum;// 层数
    private String edificeUse;// 楼栋类型 （商铺、住宅、宿舍等)
    private String houseOrientation;// 房间朝向
    private String managerName;// 物管员
    private String generalsituation;// 概要
    private String remark;// 备注

    private AreaEO area;

    public String getEdificeName() {
	return edificeName;
    }

    public void setEdificeName(String edificeName) {
	this.edificeName = edificeName;
    }

    public Float getBuildNum() {
	return buildNum;
    }

    public void setBuildNum(Float buildNum) {
	this.buildNum = buildNum;
    }

    public String getHouseOrientation() {
	return houseOrientation;
    }

    public void setHouseOrientation(String houseOrientation) {
	this.houseOrientation = houseOrientation;
    }

    public String getCellNum() {
	return cellNum;
    }

    public void setCellNum(String cellNum) {
	this.cellNum = cellNum;
    }

    public String getLayerNum() {
	return layerNum;
    }

    public void setLayerNum(String layerNum) {
	this.layerNum = layerNum;
    }

    public String getGeneralsituation() {
	return generalsituation;
    }

    public void setGeneralsituation(String generalsituation) {
	this.generalsituation = generalsituation;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    // 多对一
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public AreaEO getArea() {
	return area;
    }

    public void setArea(AreaEO area) {
	this.area = area;
    }

    public String getEdificeUse() {
	return edificeUse;
    }

    public void setEdificeUse(String edificeUse) {
	this.edificeUse = edificeUse;
    }

    public String getManagerName() {
	return managerName;
    }

    public void setManagerName(String managerName) {
	this.managerName = managerName;
    }

}