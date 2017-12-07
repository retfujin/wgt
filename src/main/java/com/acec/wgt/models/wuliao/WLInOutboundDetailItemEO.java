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
@Table(name = "tb_wuliao_inOutBoundDetailItem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class WLInOutboundDetailItemEO implements java.io.Serializable {

	/**
	 * 入出库单每个物料项目
	 * 
	 * WLCatalogEO 关联物料目录类 
	 * price 单价
	 * num 数量 
	 * amount 金额
	 * goodsAllocation 货位
	 * lotNumber 批号
	 * rq 日期
	 * wlInOutboundEO 关联入出库单类
	 * 
	 * 截至目前库存信息
	 * stocksNum 结存数量
	 * stocksAmount 结存金额
	 * 
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
	
	private String lotNumber;
	
	private Date rq;
	
	@ManyToOne	
	private WLInOutboundEO wlInOutboundEO;
	
	private Integer stocksNum;
	private float stocksAmount;
	
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

	/**
	 * @return the lotNumber
	 */
	public String getLotNumber() {
		return lotNumber;
	}

	/**
	 * @param lotNumber the lotNumber to set
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	/**
	 * @return the rq
	 */
	public Date getRq() {
		return rq;
	}

	/**
	 * @param rq the rq to set
	 */
	public void setRq(Date rq) {
		this.rq = rq;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStocksNum() {
		return stocksNum;
	}

	public void setStocksNum(Integer stocksNum) {
		this.stocksNum = stocksNum;
	}

	public float getStocksAmount() {
		return stocksAmount;
	}

	public void setStocksAmount(float stocksAmount) {
		this.stocksAmount = stocksAmount;
	}

	public WLInOutboundEO getWlInOutboundEO() {
		return wlInOutboundEO;
	}

	public void setWlInOutboundEO(WLInOutboundEO wlInOutboundEO) {
		this.wlInOutboundEO = wlInOutboundEO;
	}



}