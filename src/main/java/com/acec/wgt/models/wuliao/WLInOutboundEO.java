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
@Table(name = "tb_wuliao_inOutbound")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class WLInOutboundEO implements java.io.Serializable {

	/**
	 * 入出库单
	 * bh 单号
	 * type 入出库单类型
	 * amount 金额
	 * WLInOutboundDetailItemEO 关联物料项目类
	 * wlStoreHouseEO 关联 仓库类
	 * rq 建账日期
	 * remarks 备注
	 * 
	 * 采购入库多出字段
	 * InvNum;//发票
	 * supplier;供应商
	 * purchaseP;采购人
	 * 
	 * 领料出库多出字段
	 * customName;//客户名称
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	
	private String bh;	
	private String type;	
	private float amount;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="wlInOutboundEO")
	private List<WLInOutboundDetailItemEO> detailItemEO;	
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private WLStorehouseEO wlStoreHouseEO;
	
	private Date rq;	
	private String remarks;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private WLAssortmentEO wLAssortmentEO; //物料类别
	
	
	//采购入库多出字段
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	private WLSupplierEO wlsupplierEO;//供应商
	private String InvNum;//发票
	private String purchaseP;//采购人
	
	//领料出库多出字段
	private String customName;//客户名称
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the bh
	 */
	public String getBh() {
		return bh;
	}

	/**
	 * @param bh the bh to set
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the detailItemEO
	 */
	public List<WLInOutboundDetailItemEO> getDetailItemEO() {
		return detailItemEO;
	}

	/**
	 * @param detailItemEO the detailItemEO to set
	 */
	public void setDetailItemEO(List<WLInOutboundDetailItemEO> detailItemEO) {
		this.detailItemEO = detailItemEO;
	}

	/**
	 * @return the wlStoreHouseEO
	 */
	public WLStorehouseEO getWlStoreHouseEO() {
		return wlStoreHouseEO;
	}

	/**
	 * @param wlStoreHouseEO the wlStoreHouseEO to set
	 */
	public void setWlStoreHouseEO(WLStorehouseEO wlStoreHouseEO) {
		this.wlStoreHouseEO = wlStoreHouseEO;
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

	/**
	 * @return
	 * @see String#toString()
	 */
	public String toString() {
		return bh.toString();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public String getInvNum() {
		return InvNum;
	}

	public void setInvNum(String invNum) {
		InvNum = invNum;
	}

	public String getPurchaseP() {
		return purchaseP;
	}

	public void setPurchaseP(String purchaseP) {
		this.purchaseP = purchaseP;
	}

	/**
	 * @return the wlsupplierEO
	 */
	public WLSupplierEO getWlsupplierEO() {
		return wlsupplierEO;
	}

	/**
	 * @param wlsupplierEO the wlsupplierEO to set
	 */
	public void setWlsupplierEO(WLSupplierEO wlsupplierEO) {
		this.wlsupplierEO = wlsupplierEO;
	}

	/**
	 * @return the customName
	 */
	public String getCustomName() {
		return customName;
	}

	/**
	 * @param customName the customName to set
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}
 
	public WLAssortmentEO getwLAssortmentEO() {
		return wLAssortmentEO;
	}

	public void setwLAssortmentEO(WLAssortmentEO wLAssortmentEO) {
		this.wLAssortmentEO = wLAssortmentEO;
	}

	


	
}