package com.acec.wgt.models.chargemanager;

public class ChargehouseVO {

	private String houseId;
	private String houseName;//房间名称
	private int chargeBasedataId;
	private String chargeName;//收费名称
	private Float priceValue;
	private String chargeExpression; //计算表达式
	private String expExplain;//计算公式说明
	
	private int isMeter=0;//是否为表类 ：1 是 0否
	private Integer nowRecord;//当前度数
	private Integer backRecord;//回转度数
	private int isSelect=0; //是否有该收费项目 1是 0否
	private String ownerName; //业主名称
	private int ownerId; //业主id
	
	private String managerMen; //物管员
	private int employeeId; //物管员id
	private Float mianji;//面积
	
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getExpExplain() {
		return expExplain;
	}
	public void setExpExplain(String expExplain) {
		this.expExplain = expExplain;
	}
	public Float getMianji() {
		return mianji;
	}
	public void setMianji(Float mianji) {
		this.mianji = mianji;
	}
	public String getChargeExpression() {
		return chargeExpression;
	}
	public void setChargeExpression(String chargeExpression) {
		this.chargeExpression = chargeExpression;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getManagerMen() {
		return managerMen;
	}
	public void setManagerMen(String managerMen) {
		this.managerMen = managerMen;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public int getIsMeter() {
		return isMeter;
	}
	public void setIsMeter(int isMeter) {
		this.isMeter = isMeter;
	}
	public Integer getNowRecord() {
		return nowRecord;
	}
	public void setNowRecord(Integer nowRecord) {
		this.nowRecord = nowRecord;
	}
	public Integer getBackRecord() {
		return backRecord;
	}
	public void setBackRecord(Integer backRecord) {
		this.backRecord = backRecord;
	}
	public int getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(int isSelect) {
		this.isSelect = isSelect;
	}
	public int getChargeBasedataId() {
		return chargeBasedataId;
	}
	public void setChargeBasedataId(int chargeBasedataId) {
		this.chargeBasedataId = chargeBasedataId;
	}
	public Float getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(Float priceValue) {
		this.priceValue = priceValue;
	}
	
	
}
