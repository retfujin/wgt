package com.acec.wgt.models.wuliao;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 物品采购申请
 * 
 */
@Entity
@Table(name = "tb_wuliao_procurementdetail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProcurementdetailEO extends IdEntity {
	
	 
	private String goodsname;
    private String unit;
    private String num;
    private String brand;
    private String price;
    private String inuse;
    private int procurementid;
    
    
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInuse() {
		return inuse;
	}
	public void setInuse(String inuse) {
		this.inuse = inuse;
	}
	public int getProcurementid() {
		return procurementid;
	}
	public void setProcurementid(int procurementid) {
		this.procurementid = procurementid;
	}
	 
    
}