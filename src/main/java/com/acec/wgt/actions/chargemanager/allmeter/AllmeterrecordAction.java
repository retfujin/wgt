package com.acec.wgt.actions.chargemanager.allmeter;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.utils.StringUtil;
import com.acec.core.web.struts2.BaseFileUploadAction;
import com.acec.wgt.models.chargemanager.AllMeterRecordEO;
import com.acec.wgt.models.chargemanager.AllmeterEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.AllmeterManager;
import com.acec.wgt.service.chargemanger.AllmeterRecordManager;



/**
 * 
 * 收费管理--总表抄表
 * @author Administrator
 *
 */
public class AllmeterrecordAction extends BaseFileUploadAction{	

	private AllmeterEO allmeter;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private AllmeterRecordManager allmeterRecordManager;
	@Autowired
	private AllmeterManager allmeterManager;

	private String areaName;
	private String recordMonth="";
	
	private String pageBar="";
	
	public String inputquery()
	{
		retList  = areaManager.getAreaALL();
		return "inputquery";
	}
	/*
	 * 总表抄表录入
	 */
	public String getAllMeterInput()
	{			
		Integer areaId = getRequestValue("areaId", 0);
		String meterType = getRequest().getParameter("meterType");		
	    String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);
		
		
		String where = " and areaId="+areaId+" and meterType='"+meterType+"'";
		
		PaginatorTag pt = allmeterRecordManager.getAllMeterRecord(where +"and isNow=1", no, 15);
		pt.setShowAllPages(true);
		pt.setShowTotal(true);
		pt.setUrl("allmeterrecord!getAllMeterInput.action"+getParamString("")+"&hidd=1");
		viewList = pt.getData();
		pageBar = pt.showPage();
		
		if(!viewList.isEmpty()){
			AllMeterRecordEO e= (AllMeterRecordEO)viewList.get(0);
			if(e.getRecordMonth()!=null&&e.getRecordMonth().toString().length()>7)
				recordMonth = e.getRecordMonth().toString().substring(0,7);
			return "input";
			
		}else{//还没有生成抄表记录
			return "resultmeterindex";
			
		}
		
	}
	
	public String resultmeter(){
		Integer areaId = getRequestValue("areaId", 0);
		String meterType = getRequest().getParameter("meterType");	
		String recordMonth = getRequest().getParameter("recordMonth");	
		// 生成
		try {
			allmeterRecordManager.resultMeterData(areaId, meterType,recordMonth);
			forwardStr = "生成成功。请选择查询条件进行录入";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("生成失败。"+e.getMessage());
			return "resultmeterindex";
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
			allmeterRecordManager.deleteMeterData(areaId,meterType);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			
			addActionError(e.getMessage());
			return ERROR;
		}
		return "success";
	}
	
	/*
	 * 保存总表抄表数据
	 */
	public String saveMeterData()
	{
		
		String recordTime = getRequest().getParameter("recordTime");
		String employee = getRequest().getParameter("employee");
		String[] nowRecord = getRequest().getParameterValues("nowRecord");
		String[] changeNums = getRequest().getParameterValues("changeNums");
		String[] id = getRequest().getParameterValues("id");
		String[] meterType = getRequest().getParameterValues("meterType");
		try
		{					
			if(null == id)
			{
				addActionError("没有选中要保存的数据");
				return ERROR;
			}
			
			if(null == recordTime || "".equals(recordTime))
			{
				addActionError("没有选择抄表日期");
				return ERROR;
			}
			if(null == meterType || meterType.length<1)
			{
				addActionError("未知表类型");
				return ERROR;
			}
			allmeterRecordManager.saveMeterData(nowRecord,recordTime,employee,id,changeNums,meterType);			
			
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
	public String historyquery()
	{
		retList  = areaManager.getAreaALL();
		return "historyquery";
	}
	
	/*
	 * 总表抄表历史记录查询
	 */
	public String historyList()
	{			
		Integer areaId = getRequestValue("areaId", 0);
		
		String meterType = getRequest().getParameter("meterType");
		String beginMonth = getRequest().getParameter("beginMonth");
		String endMonth = getRequest().getParameter("endMonth");
	    String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);
		
	
		
		String where = " and areaId="+areaId+" and meterType='"+meterType+"'";
		if(!StringUtil.isEmpty(beginMonth))
			where +=" and recordMonth>='"+beginMonth+"-01'";
		if(!StringUtil.isEmpty(endMonth))
			where +=" and recordMonth<='"+endMonth+"-01'";
		where +=" order by recordMonth desc";
		PaginatorTag pt = allmeterRecordManager.getAllMeterRecord(where, no, 15);
		pt.setShowAllPages(true);
		pt.setShowTotal(true);
		pt.setUrl("allmeterrecord!historyList.action"+getParamString(""));
		viewList = pt.getData();
		pageBar = pt.showPage();
				
		return "historylist";
	}
	
	
	/*
	 * 更换总表首页
	 */
	public String changeIndex()
	{
		allmeter=allmeterManager.getAllmeterById(allmeter.getId());
		return "changeindex";
	}
	
	/*
	 * 更换总表
	 */
	public String saveAllmeterChange()
	{
		String oldMeterNum = getRequest().getParameter("oldMeterNum");
		
		try
		{
			if(-1 == allmeter.getAreaId())
				{
					addActionError("没有选择小区！");
					return ERROR;
				}
			
			allmeterRecordManager.saveAllmeterChange(allmeter,oldMeterNum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionError("操作失败！"+e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 批量导入总表收费信息
	 * 导入首页
	 * @return
	 */
	public String getPayMeterList(){
		viewList=areaManager.getAreaALL();
		return "paymeterlist";
	}	
	
	public String getPageBar() {
		return pageBar;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}

	public AllmeterEO getAllmeter() {
		return allmeter;
	}
	public void setAllmeter(AllmeterEO allmeter) {
		this.allmeter = allmeter;
	}	
	public String getRecordMonth() {
		return recordMonth;
	}

	public void setRecordMonth(String recordMonth) {
		this.recordMonth = recordMonth;
	}

	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public List getRetList() {
		return retList;
	}
}
