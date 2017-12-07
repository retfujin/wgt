package com.acec.wgt.models.chargemanager;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.baseData.AreaEO;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_chgBasedata")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
/**
	 * 收费基本设置表
	 * 
	 * chargeName
	 * otherName 
	 * chargeType 
	 * priceValue 单价
	 * priceUnit 单位
	 * isUser 使用状态 使用 停用
	 * chargeExpression 计算表达式
	 * expExplain
	 * meterType 水表，热水表，电表，暖气表，蒸汽表 其他  该字段在表数据统计时用到
	 * 
	 */
public class ChargeBasedataEO {
	private Integer id;

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	private AreaEO area;	
	private String chargeName;// 收费项目
	private String chargeType;//收费类型 （走表类、固定类）

	private float priceValue= 0.0f;//单价
	private String priceUnit;//单位
	private String isUser;//使用    停用
	private String chargeExpression;//计算表达式
	private String meterType;//表类型  水表，热水表，电表，暖气表，蒸汽表 其它  该字段在表数据统计时用到
	
	private String chargeExplain;//收费说明
	private String expExplain;//计算公式说明

	
	private Date beginTime;//启用日期
	private Date endTime;//停用日期
	private int changeId;//替换原来的收费项目编号的id
	
	private Integer areaId;
	private String areaName;
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public ChargeBasedataEO() {
		super();
	}
	public ChargeBasedataEO(Integer id) {
		setId(id);
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getChangeId() {
		return changeId;
	}
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}
	public String getChargeExplain() {
		return chargeExplain;
	}
	public void setChargeExplain(String chargeExplain) {
		this.chargeExplain = chargeExplain;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public float getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getIsUser() {
		return isUser;
	}
	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}
	public String getChargeExpression() {
		return chargeExpression;
	}
	public void setChargeExpression(String chargeExpression) {
		this.chargeExpression = chargeExpression;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public AreaEO getArea() {
		return area;
	}
	public void setArea(AreaEO area) {
		this.area = area;
	}
	public String getExpExplain() {
		return expExplain;
	}
	public void setExpExplain(String expExplain) {
		this.expExplain = expExplain;
	}


}
