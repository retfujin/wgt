package com.acec.wgt.models.chargemanager;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.EdificeEO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_chgAllmeter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
	/**
	 * 总表信息
	 * meterName 抄表表名称（18栋电梯表、18栋公用照明表）
	 * meterCode 表编号
	 * lastRecord 最后度数 以防止调整 换表
	 * isAll 公摊  不公摊
	 * meterType 表类型  '1':'水表','2':'电表','3':'热水表','4':'暖气表','5':'蒸汽表'  结合uerFor使用
	 * chargePrice 单价
	 * times 倍率
	 * unit 单位
	 * 如果是 公摊电表 就选择 楼栋id
	 * isUser 是否使用 使用 停用
	 */
public class AllmeterEO extends IdEntity{
	
	private String meterCode;	//表编号
	private String meterCodelocal;//物业公司自己的编号（供电局）
	private String meterName;	//抄表表名称
	private String meterType;	//水电气
	private String useMeterType;
	//公共用电，业主用电，商户用电，用电损耗      ---公共用水，业主用水，商户用水，用水损耗  
	private String gatherType;//公共用电，业主用电，商户用电，用电损耗      ---公共用水，业主用水，商户用水，用水损耗
	
	private Integer initRecord;//期初表数
	private Integer lastRecord;//当前表数(新增时与期初度数一样)
	
	private float priceValue;//单价
	private float times;//倍率
	
	private String unit; //单位
	private String local;//位置
	
	
	private int areaId;
	private String areaName;
	private String state;//状态    使用  停用
	
	
	
	private String isAll;//公摊       不公摊
	
	private int changeId;//被替换表的id
	private Date beginTime; //启用日期
	private Date changeTime;//更换日期
	private String changeName;//更换人员
	private String changeReason;//更换原因
	
	
	private String assignAreaName="";  //分摊范围页面显示值 
	private String assignArea="";//分摊范围实际值
	private String assignType;//分摊种类 1 按面积，2按户数
	
	private String chargebasedataId;//对应的收费项目id
	
	
	public String getMeterCodelocal() {
		return meterCodelocal;
	}
	public void setMeterCodelocal(String meterCodelocal) {
		this.meterCodelocal = meterCodelocal;
	}
	
	public String getGatherType() {
		return gatherType;
	}
	public void setGatherType(String gatherType) {
		this.gatherType = gatherType;
	}
	public int getChangeId() {
		return changeId;
	}
	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}

//	public int getGradeId() {
//		return gradeId;
//	}
//	public void setGradeId(int gradeId) {
//		this.gradeId = gradeId;
//	}
//	public int getBelongId() {
//		return belongId;
//	}
//	public void setBelongId(int belongId) {
//		this.belongId = belongId;
//	}

	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	public String getMeterName() {
		return meterName;
	}
	
	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	public String getMeterCode() {
		return meterCode;
	}
	public void setMeterCode(String meterCode) {
		this.meterCode = meterCode;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public String getUseMeterType() {
		return useMeterType;
	}
	public void setUseMeterType(String useMeterType) {
		this.useMeterType = useMeterType;
	}
	public Integer getInitRecord() {
		return initRecord;
	}
	public void setInitRecord(Integer initRecord) {
		this.initRecord = initRecord;
	}
	public Integer getLastRecord() {
		return lastRecord;
	}
	public void setLastRecord(Integer lastRecord) {
		this.lastRecord = lastRecord;
	}
	public float getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(float priceValue) {
		this.priceValue = priceValue;
	}
	public float getTimes() {
		return times;
	}
	public void setTimes(float times) {
		this.times = times;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getAssignAreaName() {
		return assignAreaName;
	}
	public void setAssignAreaName(String assignAreaName) {
		this.assignAreaName = assignAreaName;
	}
	public String getAssignArea() {
		return assignArea;
	}
	public void setAssignArea(String assignArea) {
		this.assignArea = assignArea;
	}
	
	public String getIsAll() {
		return isAll;
	}
	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getChargebasedataId() {
		return chargebasedataId;
	}
	public void setChargebasedataId(String chargebaseDataId) {
		this.chargebasedataId = chargebaseDataId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAssignType() {
		return assignType;
	}
	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}
	

	
	
}
