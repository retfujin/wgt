package com.acec.wgt.models.chg;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;

/**
 * 催缴记录表
 * @author Administrator
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_payadvicerecord")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class PayAdviceRecordEO extends IdEntity{

	private Integer areaId; //小区id
	private String areaName; //小区
	private String edificeId;//楼栋id
	private String edificeName; //楼栋
	private Integer cell; //单元
	private Integer advicecount=1; //记录催缴次数
	
	private Date oughtpayDate;//本次催缴后应缴费日期
	
	public PayAdviceRecordEO(){
		super();
	}
	
	public PayAdviceRecordEO(Integer areaId, String areaName,
			String edificeId,String edificeName, Integer cell, Integer advicecount) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.edificeId = edificeId;
		this.edificeName = edificeName;
		this.cell = cell;
		this.advicecount = advicecount;
	}
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getEdificeName() {
		return edificeName;
	}
	public void setEdificeName(String edificeName) {
		this.edificeName = edificeName;
	}
	public Integer getCell() {
		return cell;
	}
	public void setCell(Integer cell) {
		this.cell = cell;
	}


	public String getEdificeId() {
		return edificeId;
	}

	public void setEdificeId(String edificeId) {
		this.edificeId = edificeId;
	}

	public Integer getAdvicecount() {
		return advicecount;
	}

	public void setAdvicecount(Integer advicecount) {
		this.advicecount = advicecount;
	}

	public Date getOughtpayDate() {
		return oughtpayDate;
	}

	public void setOughtpayDate(Date oughtpayDate) {
		this.oughtpayDate = oughtpayDate;
	}
	
	
	
	
	
	
	
	
	
	
}
