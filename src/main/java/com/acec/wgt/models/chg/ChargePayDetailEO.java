package com.acec.wgt.models.chg;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_chgPayDetail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ChargePayDetailEO extends IdEntity {

    private int chargeHouseDetailId;// 收费表id
    private String houseId;
    private String pay_type;
    private float pay_money = 0;
    private Date record_month;
    private int area_id; // 小区id
    private int charge_id;// 收费项目编号
    private Date gathering_time;// 实缴费用
    private String privilege_reason; // 尚欠费用

    public int getChargeHouseDetailId() {
	return chargeHouseDetailId;
    }

    public void setChargeHouseDetailId(int chargeHouseDetailId) {
	this.chargeHouseDetailId = chargeHouseDetailId;
    }

    public String getHouseId() {
	return houseId;
    }

    public void setHouseId(String houseId) {
	this.houseId = houseId;
    }

    public String getPay_type() {
	return pay_type;
    }

    public void setPay_type(String pay_type) {
	this.pay_type = pay_type;
    }

    public float getPay_money() {
	return pay_money;
    }

    public void setPay_money(float pay_money) {
	this.pay_money = pay_money;
    }

    public Date getRecord_month() {
	return record_month;
    }

    public void setRecord_month(Date record_month) {
	this.record_month = record_month;
    }

    public int getArea_id() {
	return area_id;
    }

    public void setArea_id(int area_id) {
	this.area_id = area_id;
    }

    public int getCharge_id() {
	return charge_id;
    }

    public void setCharge_id(int charge_id) {
	this.charge_id = charge_id;
    }

    public Date getGathering_time() {
	return gathering_time;
    }

    public void setGathering_time(Date gathering_time) {
	this.gathering_time = gathering_time;
    }

    public String getPrivilege_reason() {
	return privilege_reason;
    }

    public void setPrivilege_reason(String privilege_reason) {
	this.privilege_reason = privilege_reason;
    }

}
