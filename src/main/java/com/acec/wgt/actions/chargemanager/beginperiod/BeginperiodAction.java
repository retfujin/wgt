package com.acec.wgt.actions.chargemanager.beginperiod;

import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseFileUploadAction;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.BeginPeriodManager;

public class BeginperiodAction extends BaseFileUploadAction {
	
	/**
	 * 期初数据
	 */
	private static final long serialVersionUID = -2001270286792277096L;

	@Autowired
	private BeginPeriodManager beginPeriodManager;
	
	@Autowired
	private AreaManager areaManager;
		
	//批量导入、下载页面
	public String initDown(){
		viewList = areaManager.getAreaALL();
		return "initdown";
	}
	
	//下载excel文件
	public String downExcel(){
		String areaId = getRequest().getParameter("areaId");
		logger.debug("下载导入期初数据模板，小区id"+areaId);
		try{
			beginPeriodManager.writeExc(areaId);
			return "downExcel";
		}catch(Exception ex){
			ex.printStackTrace();
			forwardStr="下载excel失败。原因："+ex.getMessage();
			return "result";
		}
		
	}
	//上传客户资料（execl）
	public String uploadExcel(){

		if(theFile!=null)
		{
			logger.debug(contenttype);
			if(!contenttype.equals("application/vnd.ms-excel")&&!contenttype.equals("application/octet-stream")){
				forwardStr="上传的文件类型不正确。类型为："+contenttype;	
			}		
			try {
				beginPeriodManager.saveForExc(theFile);
	
				forwardStr="导入期初数据excel成功。";
			} catch (Exception ex) {
				forwardStr = "导入期初数据excel失败。原因：" + ex.getMessage();
				logger.error("导入期初数据excel失败", ex);
			}
		}else{
			forwardStr="没有获取到文件";
			logger.error("导入期初数据excel失败,没有获取到文件");
		}	
		return "result";
	}
}
