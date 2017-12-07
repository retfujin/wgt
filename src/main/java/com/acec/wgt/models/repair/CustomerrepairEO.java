package com.acec.wgt.models.repair;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
/**
 * 客户请修登记表
 * @author hua
 * @version 2009-11-29
 */
@Entity
@Table(name = "tb_basedata_customer_repair")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustomerrepairEO extends IdEntity {
   
	private int areaId;
	private String areaName;
	private Date recordMonth;//日期
	private String recordMonthString;//字符串日期
	
	private String acceptedDate;//受理时间

	private String content;//客户姓名 联系电话 及 地址
	private String houseId;//房间编号
	private String repairContext;//请修内容
	private String reserveDate;// 预约时间

	private String wxname ;//维修人员
	private String dispatcherNum;//派工单号
	private String achieveDate;//完成时间
	private String repairprocess;//维修过程
	
	private String repairStatus;//维修状态   维修    未维修
	private String paidService;//服务状态  有偿服务  无偿服务
	private String repairResult;//维修结果
	private String reciprocal;//回访时间
	private String visitResult;//回访结果
	private String comaddress;//非公共区域地址
	private Date endMonth;//时间限制日期
	
	
	private String flowState;////流程状态   0 未处理  1 已处理    2 已回访
	
	public Date getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Date endMonth) {
		this.endMonth = endMonth;
	}
	
	
	@Transient
	public String getRecordMonthString() {
		return date2String(getEndMonth(), DATE_TIME_FORMAT);
	}
	public void setRecordMonthString(String recordMonthString) {
		setEndMonth(string2Date(recordMonthString, DATE_TIME_FORMAT));
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Date getRecordMonth() {
		return recordMonth;
	}
	public void setRecordMonth(Date recordMonth) {
		this.recordMonth = recordMonth;
	}
	public String getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(String acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getRepairContext() {
		return repairContext;
	}
	public void setRepairContext(String repairContext) {
		this.repairContext = repairContext;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getAchieveDate() {
		return achieveDate;
	}
	public void setAchieveDate(String achieveDate) {
		this.achieveDate = achieveDate;
	}

	public String getWxname() {
		return wxname;
	}
	public void setWxname(String wxname) {
		this.wxname = wxname;
	}
	public String getDispatcherNum() {
		return dispatcherNum;
	}
	public void setDispatcherNum(String dispatcherNum) {
		this.dispatcherNum = dispatcherNum;
	}
	
	public String getRepairStatus() {
		return repairStatus;
	}
	public String getPaidService() {
		return paidService;
	}
	public void setPaidService(String paidService) {
		this.paidService = paidService;
	}
	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}
	public String getRepairResult() {
		return repairResult;
	}
	public void setRepairResult(String repairResult) {
		this.repairResult = repairResult;
	}
	public String getReciprocal() {
		return reciprocal;
	}
	public void setReciprocal(String reciprocal) {
		this.reciprocal = reciprocal;
	}
	public String getVisitResult() {
		return visitResult;
	}
	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}
	public String getComaddress() {
		return comaddress;
	}
	public void setComaddress(String comaddress) {
		this.comaddress = comaddress;
	}
	public String getFlowState() {
		return flowState;
	}
	public void setFlowState(String flowState) {
		this.flowState = flowState;
	}
	public String getRepairprocess() {
		return repairprocess;
	}
	public void setRepairprocess(String repairprocess) {
		this.repairprocess = repairprocess;
	}
	
}