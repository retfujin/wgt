package com.acec.wgt.actions.chargemanager.generate;



import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.BasedataManager;
import com.acec.wgt.service.chargemanger.generate.GeneratePUBChargeManager;

/**
 * 
 * 收费管理--费用生成公摊类
 * @author Administrator
 *
 */
public class PubgenerateAction extends BaseAction{	


	@Resource
	private AreaManager areaManager;
	@Resource
	private GeneratePUBChargeManager generatePUBChargeManager;
	/*
	 * 费用生成首页
	 */
	public String resultMoneyIndex()
	{
		//小区
		viewList = areaManager.getAreaALL();
		return "fixedindex";
	}
	
	/*
	 * 费用生成
	 */
	public void resultMoney() throws IOException
	{

		Integer areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? Integer.parseInt(getRequest().getParameter("areaId")) : 0;
		String beginRecordMonth = getRequest().getParameter("beginRecordMonth");		
		String chargeName = getRequest().getParameter("chargeName");
		String userName = (String)getSession().get("userName");
		
		if(null == beginRecordMonth || "".equals(beginRecordMonth)){
			forwardStr = "生成失败：没有选择月份";
			write(forwardStr);
		}

		if(StringUtils.isEmpty(chargeName)){
			forwardStr = "生成失败：没有选择收费项目";
			write(forwardStr);
		}
		
		beginRecordMonth +="-01";
		try{
			String chargeIds = generatePUBChargeManager.resultMoney(areaId,"",beginRecordMonth,"",chargeName,userName);
			forwardStr ="生成成功;"+chargeIds;
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			
			forwardStr ="生成失败： "+ e.getMessage();
			
		}
		write(forwardStr);
	}
	

	public String preview(){
		String userName = (String)getSession().get("userName");
		viewList =  generatePUBChargeManager.findPreviewList(userName);
		return "preview";
	}
	public String previewresult(){
		String userName = (String)getSession().get("userName");
		
		String chargeIds = getRequest().getParameter("chargeIds");
		if(StringUtils.isEmpty(chargeIds)){
			forwardStr ="保存失败：没有找到对应的收费项目 ";
			return "ajaxresult";
		}
		
		try{
			generatePUBChargeManager.generateResult(userName,chargeIds);
			forwardStr ="保存成功";
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
			forwardStr ="保存失败： "+ ex.getMessage();
		}
		return "ajaxresult";
	}
}
