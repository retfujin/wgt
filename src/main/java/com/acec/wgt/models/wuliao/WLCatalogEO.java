package com.acec.wgt.models.wuliao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_wuliao_catalog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class WLCatalogEO implements Serializable {
    
	/**
	 * 物料目录item
	 * 
	 * 
	 * id ,物料编号,主键
	 * mnemonicCode ,助记码
	 * name, 物料名称
	 * norm，规格
	 * WLAssortmentEO, 所属类别  关联分类表，外键
	 * unit,单位
	 * minPackage,最小包装
	 * NumPackage,包装数量
	 * upperLimit,上限
	 * lowerLimit,下限
	 * 
	 * lifeOfQualityAssurance,保质期
	 * volume,体积
	 * placeOfProduction,产地
	 * brand,品牌
	 * factory,厂家
	 * planPrice,计划单价
	 * salePrice,销售单价
	 * remarks,备注
	 * 
	 */
	
    @Id
    private int id; 
    private String name; 
    private String mnemonicCode;
    private String norm;
    
    
    
    private String unit;
    private String minPackage;
    private int NumPackage=0;
    private int upperLimit=0;
    private int lowerLimit=0;
    
    private int lifeOfQualityAssurance;
    private int volume=0;
    private String placeOfProduction;
    private String brand;
    private String factory;
    private float planPrice=0F;
    private float salePrice=0F;
    private String remarks;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private WLAssortmentEO wlAssortmentEO;
    
    public WLCatalogEO () {
    }

   

    public int getId () {
        return id;
    }

    public void setId (int val) {
        this.id = val;
    }

    public String getName () {
        return name;
    }

    public void setName (String val) {
        this.name = val;
    }



	public String getMnemonicCode() {
		return mnemonicCode;
	}



	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}



	public String getNorm() {
		return norm;
	}



	public void setNorm(String norm) {
		this.norm = norm;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getMinPackage() {
		return minPackage;
	}



	public void setMinPackage(String minPackage) {
		this.minPackage = minPackage;
	}



	public int getNumPackage() {
		return NumPackage;
	}



	public void setNumPackage(int numPackage) {
		NumPackage = numPackage;
	}



	public int getUpperLimit() {
		return upperLimit;
	}



	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}



	public int getLowerLimit() {
		return lowerLimit;
	}



	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}



	public int getLifeOfQualityAssurance() {
		return lifeOfQualityAssurance;
	}



	public void setLifeOfQualityAssurance(int lifeOfQualityAssurance) {
		this.lifeOfQualityAssurance = lifeOfQualityAssurance;
	}



	public int getVolume() {
		return volume;
	}



	public void setVolume(int volume) {
		this.volume = volume;
	}



	public String getPlaceOfProduction() {
		return placeOfProduction;
	}



	public void setPlaceOfProduction(String placeOfProduction) {
		this.placeOfProduction = placeOfProduction;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getFactory() {
		return factory;
	}



	public void setFactory(String factory) {
		this.factory = factory;
	}



	public float getPlanPrice() {
		return planPrice;
	}



	public void setPlanPrice(float planPrice) {
		this.planPrice = planPrice;
	}



	public float getSalePrice() {
		return salePrice;
	}



	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public WLAssortmentEO getWlAssortmentEO() {
		return wlAssortmentEO;
	}



	public void setWlAssortmentEO(WLAssortmentEO wlAssortmentEO) {
		this.wlAssortmentEO = wlAssortmentEO;
	}







 

}

