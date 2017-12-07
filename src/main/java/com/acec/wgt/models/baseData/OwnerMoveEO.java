package com.acec.wgt.models.baseData;




import javax.persistence.Entity;
import javax.persistence.Table;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.generation.annotations.Note;
import com.acec.generation.annotations.NoteQuery;
import com.acec.wgt.models.IdEntity;


/**
 * entity.
 * 住户搬入、搬出登记表
 * @author ff
 */
@Entity
@Table(name = "tb_basedata_owner_move")
//更宽松的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OwnerMoveEO extends IdEntity{
	
	@Note(name="管理处名称")
	@NoteQuery
	private int areaId;//实则管理处编号
	@Note(name="业主姓名")
	@NoteQuery
	private String ownerName;
	@Note(name="住址")
	private String houseAddress;
	@Note(name="房间编号")
	private String houseId;
	@Note(name="拟搬时间")
	@NoteQuery
	private String carryDate;
	

	@Note(name="搬运人")
	private String carryName;
	@Note(name="搬运人证件号")
	private String carryNumber;
	@Note(name="搬运人联系电话")
	private String carryPhone;
	@Note(name="有无欠费情况")
	private String overdue;
	@Note(name="业主意见")
	private String ownerOpinion;
	@Note(name="放行条号")
	private String releasePass;
	

	
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getHouseAddress() {
		return houseAddress;
	}
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	public String getCarryDate() {
		return carryDate;
	}
	public void setCarryDate(String carryDate) {
		this.carryDate = carryDate;
	}
	public String getCarryName() {
		return carryName;
	}
	public void setCarryName(String carryName) {
		this.carryName = carryName;
	}
	public String getCarryNumber() {
		return carryNumber;
	}
	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}
	public String getCarryPhone() {
		return carryPhone;
	}
	public void setCarryPhone(String carryPhone) {
		this.carryPhone = carryPhone;
	}
	public String getOverdue() {
		return overdue;
	}
	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}
	public String getOwnerOpinion() {
		return ownerOpinion;
	}
	public void setOwnerOpinion(String ownerOpinion) {
		this.ownerOpinion = ownerOpinion;
	}
	public String getReleasePass() {
		return releasePass;
	}
	public void setReleasePass(String releasePass) {
		this.releasePass = releasePass;
	}


	public static void main(String[] args) throws Exception{
		
		com.acec.generation.GenerationJava java = new com.acec.generation.GenerationJava();
		java.setEntity(OwnerMoveEO.class);
		String daoPathName = "com.acec.wgt.models.basedata.OwnerMoveDAO";
		String managerPathName = "com.acec.wgt.service.basedata.OwnerMoveManager";
		String actionPathName = "com.acec.wgt.actions.basedata.OwnerMoveAction";
		java.setPackage(daoPathName, managerPathName, actionPathName);
		java.createJavaFile();
		
		
		com.acec.generation.GenerationJsp jsp = new com.acec.generation.GenerationJsp();
		jsp.setActionName(actionPathName);
		jsp.setEntityClass(OwnerMoveEO.class);
		jsp.createJspFile();
		
	}
}