package com.acec.wgt.models.baseData;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.HouseEO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_basedata_carport_lease_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 5)
	/**
	 * 租赁详细记录
	 */

public class CarportLeaseDetailsEO extends IdEntity{
	
	private String bigType;//机动车    非机动车
	private String type;//摩托车   自行车     电瓶车     小型车   中型车     大型车
	private Date recordMonth;//月份
	private String local;//位置
	private Integer carportId;
	private Integer carportLeaseId;
	private String state; //出租    出售
	private float mianji = 0.0f;//面积
	private int chargeId;//收费项目id
	private String chargeBigType;//
	private String chargeName;//
	private String otherName;//
	private String ownerName;//车主
	private String houseId;//房间id	
	private Integer areaId;
	
	private float oughtMoney=0;
	private float factMoney=0;
	private float payMoney=0;
	private float arrearageMoney=0; //尚欠费用
	private Date gatheringDate = new Date();
	private String carCode;//车位编号
	private Integer chargeHouseDetailId;
	
	
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public Integer getChargeHouseDetailId() {
		return chargeHouseDetailId;
	}
	public void setChargeHouseDetailId(Integer chargeHouseDetailId) {
		this.chargeHouseDetailId = chargeHouseDetailId;
	}
	public Date getGatheringDate() {
		return gatheringDate;
	}
	public void setGatheringDate(Date gatheringDate) {
		this.gatheringDate = gatheringDate;
	}
	public float getOughtMoney() {
		return oughtMoney;
	}
	public void setOughtMoney(float oughtMoney) {
		this.oughtMoney = oughtMoney;
	}
	public float getFactMoney() {
		return factMoney;
	}
	public void setFactMoney(float factMoney) {
		this.factMoney = factMoney;
	}
	public float getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(float payMoney) {
		this.payMoney = payMoney;
	}
	public float getArrearageMoney() {
		return arrearageMoney;
	}
	public void setArrearageMoney(float arrearageMoney) {
		this.arrearageMoney = arrearageMoney;
	}
	public String getChargeBigType() {
		return chargeBigType;
	}
	public void setChargeBigType(String chargeBigType) {
		this.chargeBigType = chargeBigType;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getRecordMonth() {
		return recordMonth;
	}
	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public Integer getCarportId() {
		return carportId;
	}
	public void setCarportId(Integer carportId) {
		this.carportId = carportId;
	}
	public Integer getCarportLeaseId() {
		return carportLeaseId;
	}
	public void setCarportLeaseId(Integer carportLeaseId) {
		this.carportLeaseId = carportLeaseId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public float getMianji() {
		return mianji;
	}
	public void setMianji(float mianji) {
		this.mianji = mianji;
	}
	public int getChargeId() {
		return chargeId;
	}
	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
}
