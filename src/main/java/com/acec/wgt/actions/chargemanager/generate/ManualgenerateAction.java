package com.acec.wgt.actions.chargemanager.generate;

import javax.annotation.Resource;
import com.acec.core.web.struts2.BaseFileUploadAction;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.generate.ManualGenerateManager;

/**
 * 
 * 收费管理--手工产生费用，费用直接导入
 * @author Administrator
 *
 */
public class ManualgenerateAction extends BaseFileUploadAction{	


	@Resource
	private AreaManager areaManager;
	@Resource
	private ManualGenerateManager manualGenerateManager;
	
	
	/**
	 * 批量导入期初收费信息首页
	 * @return
	 */
	public String indexo(){
		viewList=areaManager.getAreaALL();
		return "indexo";
	}

	/**
	 * 下载期初收费信息模板
	 * @return
	 */
	public String down(){
		String areaId = getRequest().getParameter("areaId");
		logger.debug("下载导入房间模板，小区id"+areaId);
		try{
			manualGenerateManager.writeExc(areaId);
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
			forwardStr="下载excel失败。原因："+ex.getMessage();
			return "result";
		}
	}
	
	/**
	 * 上传期初收费信息
	 * @return
	 */
	public String upload(){
		if(theFile!=null)
		{
			logger.debug(contenttype);
			if(!contenttype.equals("application/vnd.ms-excel")&&!contenttype.equals("application/octet-stream")){
				forwardStr="上传的文件类型不正确。类型为："+contenttype;	
			}
			
			try {
				manualGenerateManager.saveForExc(theFile);
				forwardStr="上传excel成功。";
			} catch (Exception ex) {
				forwardStr = "导入excel失败。原因：" + ex.getMessage();
				logger.error("导入总表缴费excel失败", ex);
			}
		}else{
			forwardStr="没有获取到文件";
			logger.error("导入总表缴费excel失败,没有获取到文件");
		}
		return "result";
	}
	
	
	
	/**
	 * 批量导入历史费用信息
	 * @return
	 */
	public String indext(){
		viewList = areaManager.getAreaALL();
		return "indext";
	}
	
	/**
	 * 下载历史收费信息模板
	 * @return
	 */
	public String downown(){
		String areaId = getRequest().getParameter("areaId");
		logger.debug("下载导入房间模板，小区id"+areaId);
		try{
			manualGenerateManager.writeExcOwn(areaId);
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
			forwardStr="下载excel失败。原因："+ex.getMessage();
			return "result";
		}
	}
	
	/**
	 * 上传历史收费信息
	 * @return
	 */
	public String uploadown(){
		if(theFile!=null)
		{
			logger.debug(contenttype);
			if(!contenttype.equals("application/vnd.ms-excel")&&!contenttype.equals("application/octet-stream")){
				forwardStr="上传的文件类型不正确。类型为："+contenttype;	
			}
			
			try {
				String num = manualGenerateManager.saveForExcOwn(theFile);
				//暂时不用了
			//	manualGenerateManager.insertPayRecord(num);
				forwardStr="上传excel成功。";
			} catch (Exception ex) {
				forwardStr = "导入excel失败。原因：" + ex.getMessage();
				logger.error("导入历史缴费excel失败", ex);
			}
		}else{
			forwardStr="没有获取到文件";
			logger.error("导入历史缴费excel失败,没有获取到文件");
		}
		return "result";
	}
}