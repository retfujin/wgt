package com.acec.wgt.actions.chargemanager.ownermeter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;

import com.acec.core.web.struts2.BaseFileUploadAction;

import com.acec.wgt.models.baseData.HouseMeterEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.HouseMeterSelManager;
import com.acec.wgt.service.chargemanger.HouseMeterrecordManager;
/**
 * 
 * 收费管理--业主表抄表管理
 * @author Administrator
 *
 */
public class OwnermeterrecordAction extends BaseFileUploadAction{
	@Autowired
	private HouseMeterSelManager houseMeterSelManager;
	@Autowired
	private HouseMeterrecordManager houseMeterrecordManager;
	@Autowired
	private AreaManager areaManager;

	private HouseMeterEO houseMeter1;
	private List retList;
	private String areaName;
	private String pageBar;
	private String recordMonth="";
	
	public String inputquery(){
		
		retList  = areaManager.getAreaALL();
		return "inputquery";
	}
	
	//下载excel文件
	public String downExcel(){
		String areaId = getRequest().getParameter("areaId");
		String meterType = getRequest().getParameter("meterType");
		String month = getRequest().getParameter("month");
		logger.debug("下载导入房间模板，小区id"+areaId);
		try{
			houseMeterrecordManager.writeExc(areaId,meterType,month);
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
			forwardStr="下载excel失败。原因："+ex.getMessage();
			return "result";
		}
		
	}
	
	public String uploadExcel(){
		if(theFile!=null){
			logger.debug(contenttype);
			if(!contenttype.equals("application/vnd.ms-excel")&&!contenttype.equals("application/octet-stream"))
				forwardStr="上传的文件类型不正确。类型为："+contenttype;	
			try {
				houseMeterrecordManager.saveForExc(theFile);
				forwardStr="上传excel成功。";
			} catch (Exception ex) {
				forwardStr = "导入excel失败。原因：" + ex.getMessage();
				logger.error("导入业户表excel失败", ex);
			}
		}else{
			forwardStr="没有获取到文件";
			logger.error("导入业户表excel失败,没有获取到文件");
		}
			
		return "result";
	}
	
	/*
	 * 业主表抄表录入
	 */
	public String getOwnerMeterInput()
	{
		int areaId = getRequestValue("areaId", 0);
		String edificeId = getRequestValue("edificeId", "");
		String meterType = getRequestValue("meterType", "");
		String month = getRequestValue("month", "");
		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);

		//page  = houseMeterManager.getOwnerMeterInput(page,areaId,edificeId,meterType);
		
		//显示抄表记录
//		PaginatorTag pt = houseMeterrecordManager.getOwnerMeterInputImpl(areaId, edificeId, meterType, no, 150);
//		pt.setUrl("ownermeterrecord!getOwnerMeterInput.action"+getParamString("")+"&hidd=1");
//		pt.setShowAllPages(true);
//		pt.setShowTotal(true);
//		pt.setStrUnit("条");
//		viewList = pt.getData();
//		pageBar = pt.showPage();
		
		viewList = houseMeterrecordManager.getOwnerMeterInputImpl(areaId, edificeId, meterType,month);
		
		if(!viewList.isEmpty()){
			OwnerMeterRecordEO e= (OwnerMeterRecordEO)viewList.get(0);
			if(e.getRecordMonth()!=null&&e.getRecordMonth().toString().length()>7)
				recordMonth = e.getRecordMonth().toString().substring(0,7);
			return "input";
			
		}else{
			
			return "resultmeterindex";
		}		
	}
	
	
	public void changeNum() throws IOException{
 
		String id = getRequest().getParameter("id");		
		String num = getRequest().getParameter("num"); 
		try{ 
			houseMeterrecordManager.savenum(id, num);
			forwardStr ="数据更新成功;";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			forwardStr ="数据更新失败： "+ e.getMessage();
		}
		write(forwardStr);
	}
	
	public String init(){
		viewList = areaManager.getAreaALL();
		return "init";
	}
	
	public void initsave() throws IOException{
		 
		String areaId = getRequest().getParameter("areaId");		
		String meterType = getRequest().getParameter("meterType"); 
		String month = getRequest().getParameter("month");
		try{ 
			houseMeterrecordManager.initsave(areaId, meterType, month);
			forwardStr ="数据审核成功，数据已生成费用";
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			forwardStr ="数据审核失败： "+ e.getMessage();
		}
		write(forwardStr);
	}
	
	/**
	 * 生成结果页面
	 * @return
	 */
	public String resultmeter(){
		
		int areaId = getRequestValue("areaId", 0);
		String recordMonth = getRequestValue("recordMonth", "");
		String meterType = getRequestValue("meterType", "");
		
		try {
			houseMeterrecordManager.resultMeterData(areaId, meterType, recordMonth);
			forwardStr = "生成成功。请选择查询条件进行录入";
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("生成失败。"+e.getMessage());
			return "resultmeterindex";
		}
		
		return "result";
	}
	
	
	
	/*
	 * 业主抄表数据保存
	 */
	public String saveMeter()
	{
		String recordTime = getRequest().getParameter("recordTime");
		String employee = getRequest().getParameter("employee");
		String[] recordId = getRequest().getParameterValues("id");
		String[] lastRecord = getRequest().getParameterValues("lastRecord");//上期数
		String[] nowRecord = getRequest().getParameterValues("nowRecord");//本期数
		String[] backRecord = getRequest().getParameterValues("backRecord");//回转数
		String[] changeNum = getRequest().getParameterValues("changeNum");//换表数
		String[] meterType = getRequest().getParameterValues("meterType");//表类型
		try
		{
			if(null == recordId)
			{
				addActionError("没有选中要保存的数据");
				return ERROR;
			}
			if(null == meterType || meterType.length<1)
			{
				addActionError("未知表类型");
				return ERROR;
			}
			if(null == recordTime || recordTime.length()<1)
			{
				addActionError("请选择抄表日期");
				return ERROR;
			}
			
			houseMeterrecordManager.saveMeterData(recordId,lastRecord,nowRecord,backRecord,recordTime,employee,changeNum,meterType);
			forwardStr = "保存成功";
			
		}catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			
			addActionError(e.getMessage());
			return ERROR;
		}
		return "result";
	}
	
	/*
	 * 删除当前录入的记录
	 */
	public String delMeterData(){
		
		Integer areaId = getRequestValue("areaId", 0);
		String meterType = getRequest().getParameter("meterType");
		try{
			houseMeterrecordManager.deleteMeterData(areaId,meterType);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			
			addActionError(e.getMessage());
			return ERROR;
		}
		return "success";
	}
	
	//历史查询条件页
	public String historyquery(){
		
		retList  = areaManager.getAreaALL();
		return "historyquery";
	}
	
	/*
	 * 历史查询结果页
	 */
	public String historyList()
	{
		int areaId = getRequestValue("areaId", 0);
		String edificeId = getRequest().getParameter("edificeId");
		String houseId = getRequest().getParameter("houseId");
		String meterType = getRequest().getParameter("meterType") ;	
		String pageNo = getRequest().getParameter("pageNo");
		String beginMonth = getRequest().getParameter("beginMonth");
		String endMonth = getRequest().getParameter("endMonth");
		
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);

		//page  = houseMeterManager.getOwnerMeterInput(page,areaId,edificeId,meterType);
		
		//显示抄表记录
		PaginatorTag pt = houseMeterrecordManager.getHistoryList(areaId, edificeId,houseId,meterType,beginMonth,endMonth, no, 100);
		pt.setUrl("ownermeterrecord!historyList.action"+getParamString("")+"&hidd=1");
		pt.setShowAllPages(true);
		pt.setShowTotal(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
		
		return "historylist";
	}
	
	/*
	 * 业主换表
	 */
	public String changeMeter()
	{
		String meterType = getRequest().getParameter("meterType");
		String houseId = getRequest().getParameter("houseId");
		
		if(null == meterType || null == houseId )
		{
			addActionError("取业主房间表信息失败，请确定房间编号和表类型正确");
			return ERROR;
		}
		
		houseMeter1 = houseMeterrecordManager.getHouseMeter(houseId,com.acec.commons.util.Utils.getMeterTypeById(meterType));
		return "changemeter";
	}
	
	/*
	 * 保存业主换表
	 */
	public String changeMeterSave()
	{
		String oldMeterNum = getRequest().getParameter("oldMeterNum");
		try
		{
			houseMeterrecordManager.changeOwnerMeter(Integer.parseInt(oldMeterNum),houseMeter1);
			forwardStr = "保存成功";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError(e.getMessage());
			return ERROR;
		}
		return "result";
	}

	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}

	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public HouseMeterEO getHouseMeter1() {
		return houseMeter1;
	}
	public void setHouseMeter1(HouseMeterEO houseMeter1) {
		this.houseMeter1 = houseMeter1;
	}
	public HouseMeterSelManager getHouseMeterSelManager() {
		return houseMeterSelManager;
	}
	public void setHouseMeterSelManager(HouseMeterSelManager houseMeterSelManager) {
		this.houseMeterSelManager = houseMeterSelManager;
	}
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public String getRecordMonth() {
		return recordMonth;
	}
	public void setRecordMonth(String recordMonth) {
		this.recordMonth = recordMonth;
	}
	
}