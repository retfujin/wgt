package com.acec.wgt.models.baseData;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_basedata_carport_lease")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 5)
/**
 * 业主姓名
 * 车牌
 * 联系方式
 * 租赁开始日期
 * 租赁结束日期
 */
public class CarportLeaseEO extends IdEntity {

    private String bigType;// 机动车 非机动车
    private Date recordMonth;// 缴费时间
    private String type;// 摩托车 自行车 电瓶车 小型车 中型车 大型车
    private String carNums;// 车牌
    private Date inDate;// 开始日期
    private Date outDate;// 截止日期
    private String local;// 位置
    private CarportEO carport;
    private String state; // 出租 出售
    private float mianji = 0.0f;// 面积
    private String carCode;// 车位编号
    private int chargeId;// 收费项目id
    private String ownerName;// 车主

    private String houseId;// 房间id
    private String isNow;// 当前 历史
    private float factMoney = 0;

    private Integer areaId;

    private String electric = "否";// 是否充电(此字段是做续交车位租赁费时使用的专用字段)

    private String cardNum;// 卡号
    private String carColor;// 车名/颜色
    private String phone;// 手机号
    private String remark;// 备注

    private Date beginDate;// 车位开始收费日期
    private Date endDate;// 车位截止收费日期

    public Integer getAreaId() {
	return areaId;
    }

    public void setAreaId(Integer areaId) {
	this.areaId = areaId;
    }

    public float getFactMoney() {
	return factMoney;
    }

    public void setFactMoney(float factMoney) {
	this.factMoney = factMoney;
    }

    public String getIsNow() {
	return isNow;
    }

    public void setIsNow(String isNow) {
	this.isNow = isNow;
    }

    public String getHouseId() {
	return houseId;
    }

    public void setHouseId(String houseId) {
	this.houseId = houseId;
    }

    public int getChargeId() {
	return chargeId;
    }

    public void setChargeId(int chargeId) {
	this.chargeId = chargeId;
    }

    public String getCarCode() {
	return carCode;
    }

    public void setCarCode(String carCode) {
	this.carCode = carCode;
    }

    public String getOwnerName() {
	return ownerName;
    }

    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    public float getMianji() {
	return mianji;
    }

    public void setMianji(float mianji) {
	if (mianji == 0)
	    mianji = 0.1f;
	this.mianji = mianji;
    }

    public String getLocal() {
	return local;
    }

    public void setLocal(String local) {
	this.local = local;
    }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public CarportEO getCarport() {
	return carport;
    }

    public void setCarport(CarportEO carport) {
	this.carport = carport;
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

    public String getCarNums() {
	return carNums;
    }

    public void setCarNums(String carNums) {
	this.carNums = carNums;
    }

    public Date getInDate() {
	return inDate;
    }

    public void setInDate(Date inDate) {
	this.inDate = inDate;
    }

    public Date getOutDate() {
	return outDate;
    }

    public void setOutDate(Date outDate) {
	this.outDate = outDate;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getElectric() {
	return electric;
    }

    public void setElectric(String electric) {
	this.electric = electric;
    }

    public String getCardNum() {
	return cardNum;
    }

    public void setCardNum(String cardNum) {
	this.cardNum = cardNum;
    }

    public String getCarColor() {
	return carColor;
    }

    public void setCarColor(String carColor) {
	this.carColor = carColor;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public Date getBeginDate() {
	return beginDate;
    }

    public void setBeginDate(Date beginDate) {
	this.beginDate = beginDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }


    public Date getRecordMonth() {
	return recordMonth;
    }

    public void setRecordMonth(Date recordMonth) {
	this.recordMonth = recordMonth;
    }

}