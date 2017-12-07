package com.acec.wgt.actions.chargemanager.generate;



import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.BasedataManager;
import com.acec.wgt.service.chargemanger.generate.GenerateFixedChargeManager;

/**
 * 
 * 收费管理--费用生成（固定类）
 * @author Administrator
 *
 */
public class GenerateAction extends BaseAction{	


	@Resource
	private AreaManager areaManager;
	@Resource
	private GenerateFixedChargeManager generateFixedChargeManager;
	/*
	 * 固定类费用生成首页
	 */
	public String resultFixedMoneyIndex()
	{
		//小区
		viewList = areaManager.getAreaALL();
		return "fixedindex";
	}
	
	/*
	 * 固定类费用生成
	 */
	public void resultFixedMoney() throws IOException
	{

		Integer areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? Integer.parseInt(getRequest().getParameter("areaId")) : 0;
		String houseId = getRequest().getParameter("houseId");
		String beginRecordMonth = getRequest().getParameter("beginRecordMonth");
		String endRecordMonth = getRequest().getParameter("endRecordMonth");		
		String chargeName = getRequest().getParameter("chargeName");
		String userName = (String)getSession().get("userName");
		
		if(null == beginRecordMonth || "".equals(beginRecordMonth)){
			forwardStr = "生成失败：没有选择开始月份";
			write(forwardStr);
		}
		if(null == endRecordMonth || "".equals(endRecordMonth)){
			forwardStr = "生成失败：没有选择截止月份";
			write(forwardStr);
		}
		if(StringUtils.isEmpty(chargeName)){
			forwardStr = "生成失败：没有选择收费项目";
			write(forwardStr);
		}
		
		beginRecordMonth +="-01";
		endRecordMonth +="-01";
		//如果直接输入房间，则检查下是否有权限
		if(houseId!=null&&!houseId.equals("")){
			String[] houseIds = houseId.split("-");
			String areaIds=(String)getSession().get("areaIds");
			if(!areaIds.contains(houseIds[0])){
				forwardStr = "生成失败：你没有权限生成该小区费用";
				write(forwardStr);
			}
				
		}
		try{
			String chargeIds = generateFixedChargeManager.resultMoney(areaId,houseId,beginRecordMonth,endRecordMonth,chargeName,userName);
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
		viewList =  generateFixedChargeManager.findPreviewList(userName);
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
			generateFixedChargeManager.generateResult(userName,chargeIds);
			forwardStr ="保存成功";
		}catch(Exception ex){
			logger.error(ex.getMessage(), ex);
			forwardStr ="保存失败： "+ ex.getMessage();
		}
		return "ajaxresult";
	}
}
