package com.acec.wgt.models.chargemanager;



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
@Table(name = "tb_chg_housecharge")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class HousechargeEO extends IdEntity{
	/**
	 * 房间收费项目表
	 * nowRecord 当前度数
	 * backRecord 回转度数
	 */

	private ChargeBasedataEO chargeBasedata;
	private HouseEO house ;
	private String coefficient;//入住系数

	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	public ChargeBasedataEO getChargeBasedata() {
		return chargeBasedata;
	}

	public void setChargeBasedata(ChargeBasedataEO chargeBasedata) {
		this.chargeBasedata = chargeBasedata;
	}

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public HouseEO getHouse() {
		return house;
	}

	public void setHouse(HouseEO house) {
		this.house = house;
	}
	public String getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient;
	}
}
