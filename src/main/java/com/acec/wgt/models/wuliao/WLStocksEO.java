package com.acec.wgt.models.wuliao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * 库存表
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_wuliao_stocks")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class WLStocksEO implements java.io.Serializable {

	/**
	 * 库存
	 * @see WLCatalogEO 关联物料目录类
	 * price 单价
	 * num 数量 
	 * amount 金额
	 * goodsAllocation 货位
	 * lastRq 末尾出库日期
	 * storeHouseid 仓库id
	 * storeHouseName 仓库名称(不保存，显示用)
	 * 
	 * remarks 备注
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private WLCatalogEO wlCatalogEO;
	
	private float price;
	
	private int num;
	
	private float amount;
	
	private String goodsAllocation;
	
	
	private Date lastRq;
	
	
	private Integer storeHouseid;
	
	@Transient
	private String storeHouseName;
	
	private String remarks;
	// Property accessors

	/**
	 * @return the wlCatalogEO
	 */
	public WLCatalogEO getWlCatalogEO() {
		return wlCatalogEO;
	}

	/**
	 * @param wlCatalogEO the wlCatalogEO to set
	 */
	public void setWlCatalogEO(WLCatalogEO wlCatalogEO) {
		this.wlCatalogEO = wlCatalogEO;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the goodsAllocation
	 */
	public String getGoodsAllocation() {
		return goodsAllocation;
	}

	/**
	 * @param goodsAllocation the goodsAllocation to set
	 */
	public void setGoodsAllocation(String goodsAllocation) {
		this.goodsAllocation = goodsAllocation;
	}





	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the lastRq
	 */
	public Date getLastRq() {
		return lastRq;
	}

	/**
	 * @param lastRq the lastRq to set
	 */
	public void setLastRq(Date lastRq) {
		this.lastRq = lastRq;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the storeHouseid
	 */
	public Integer getStoreHouseid() {
		return storeHouseid;
	}

	/**
	 * @param storeHouseid the storeHouseid to set
	 */
	public void setStoreHouseid(Integer storeHouseid) {
		this.storeHouseid = storeHouseid;
	}

	public String getStoreHouseName() {
		return storeHouseName;
	}

	public void setStoreHouseName(String storeHouseName) {
		this.storeHouseName = storeHouseName;
	}



}