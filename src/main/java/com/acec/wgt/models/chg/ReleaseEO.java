package com.acec.wgt.models.chg;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_chgRelease")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class ReleaseEO extends IdEntity{
	/**
	 * 地产公司免交、物业公司优惠表
	 * 
	 * releaseMonth 免交月份
	 * submitName 录入人
	 * recordTime 录入时间
	 * chargeBaseDataEO ;关联基础收费项目表
	 * houseEO 关联房间表
	 */
	
	private Date releaseMonth;//开始时间
	private Date releaseMonthEnd;//--结束时间
	private String submitName;//录入人
	private Date  submitTime = new Date();//录入时间
	private Integer count;//--免费月数
	private Integer useCount = 0;//--已经免交月数  在费用生成该字段自动+1  交费时如果不免交的话再-1
	private Date delayMonth;//--顺延开始月份（不在用）
	private Date delayMonthEnd;//--顺延截止月份
	private String explain;//说明原因
	private String releaseState="使用";//--状态     使用        停用
	private float releaseMoney=0;//免交、优惠总金额
	private float useMoney=0;//--已用金额  （地产公司免交用）
	private float remainMoney=0;//--剩余金额（地产公司免交用）
	private String chargeName;//收费项目大分类         
	private String bigType ;//免交 优惠
	private String type ;//物业公司优惠     地产公司免交
	private String mode="";//只对物业公司优惠 ，优惠方式（：免一个月物管费，送68元保险）
	private ChargeBasedataEO chargeBasedata;
	private HouseEO house;
	private String houseId;
	private String ownerName;
	private Integer areaId;
	
	
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
	public float getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(float useMoney) {
		this.useMoney = useMoney;
	}
	public float getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(float remainMoney) {
		this.remainMoney = remainMoney;
	}
	public Integer getUseCount() {
		return useCount;
	}
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public float getReleaseMoney() {
		return releaseMoney;
	}
	public void setReleaseMoney(float releaseMoney) {
		this.releaseMoney = releaseMoney;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	public String getReleaseState() {
		return releaseState;
	}
	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}

	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public ChargeBasedataEO getChargeBasedata() {
		return chargeBasedata;
	}
	public void setChargeBasedata(ChargeBasedataEO chargeBasedata) {
		this.chargeBasedata = chargeBasedata;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}


	public Date getReleaseMonth() {
		return releaseMonth;
	}
	
	public void setReleaseMonth(Date releaseMonth) {
		this.releaseMonth = releaseMonth;
	}
	public Date getReleaseMonthEnd() {
		return releaseMonthEnd;
	}
	
	public void setReleaseMonthEnd(Date releaseMonthEnd) {
		this.releaseMonthEnd = releaseMonthEnd;
	}
	
	public Date getDelayMonth() {
		return delayMonth;
	}
	public void setDelayMonth(Date delayMonth) {
		this.delayMonth = delayMonth;
	}
	
	public Date getDelayMonthEnd() {
		return delayMonthEnd;
	}
	public void setDelayMonthEnd(Date delayMonthEnd) {
		this.delayMonthEnd = delayMonthEnd;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public HouseEO getHouse() {
		return house;
	}
	public void setHouse(HouseEO house) {
		this.house = house;
	}
	
	public String getSubmitName() {
		return submitName;
	}
	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}
	
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
}
