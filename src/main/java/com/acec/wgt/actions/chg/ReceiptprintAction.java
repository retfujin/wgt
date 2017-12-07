package com.acec.wgt.actions.chg;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.service.basedata.EdificeManager;

public class ReceiptprintAction extends BaseAction{
	
	private static final long serialVersionUID = -6602979377900139672L;

	@Autowired
	private com.acec.wgt.service.chg.PayManager payManager;
	@Autowired
	private com.acec.wgt.service.basedata.AreaManager areaManager;


	@Autowired
	private EdificeManager edificeManager;
	private Map map;
	

	public String printPaymentAdviceQ(){
		viewList = areaManager.getAreaALL();
		return "printpaymentadvice-q";
	}
	
	
	/*
	 * 打印催缴单
	 */
	public String printpaymentadviceq1(){
		viewList = areaManager.getAreaALL();
		String areaId = getRequest().getParameter("areaId");
		if(areaId!=null&&!areaId.equals(""))
			retList = payManager.getPrintPaymentAdviceInitList(areaId);
		
		return "printpaymentadvice-q1";
	}
	
	
	/*
	 * 催费单次数保存初始化
	 */

	public String printpaymentadviceinitsave(){
		String areaId = getRequest().getParameter("areaId");
		if(areaId!=null&&!areaId.equals(""))
			try{
				payManager.printPaymentAdviceInitSave(areaId);
				Struts2Utils.renderText("初始化成功，请选择小区查询。<a href='javascript:history.back();'>返回</a>");
			}catch(Exception ex){
				Struts2Utils.renderText("初始化失败：原因"+ex.getMessage());
			}
		else
			Struts2Utils.renderText("初始化失败：原因没有小区参数");
			
		return null;
	}

	
	/**
	 * 打印催缴单和交费单
	 */
	public String printPaymentAdvice(){
		String areaId = getRequest().getParameter("areaId");
		String edificeId = getRequest().getParameter("edificeId");
		String houseId = getRequest().getParameter("houseId");
		String advicecount = getRequest().getParameter("advicecount");
		String cellId = getRequest().getParameter("cellId");
		String adviceId = getRequest().getParameter("adviceId");
		String oughtpayDate = getRequest().getParameter("oughtpayDate");
		//催缴单
		if(adviceId!=null&&!adviceId.equals("")){
			getRequest().setAttribute("oldoughtpayDate",payManager.getPrintPaymentAdviceDate(adviceId));
			map = payManager.getPrintPaymentAdvice(areaId, edificeId,cellId, houseId,adviceId,oughtpayDate);
			if(advicecount.equals("1"))
				return "printpaymentadvice1";
			else if(advicecount.equals("2")){
				return "printpaymentadvice2";
			}
			else {
				getRequest().setAttribute("oldoughtpayDate",payManager.getPrintPaymentAdviceDate(adviceId));
				return "printpaymentadvice3";
			}
		}
		//交费单
		else{
			map = payManager.getPrintPaymentAdvice(areaId, edificeId,cellId,houseId);
			return "printpaymentadvice";
		}
	}
	
	
	
	
	/*
	 * 根据小区id取楼栋
	 */
	public String getEdifice(){
		String areaId = getRequest().getParameter("areaId");
		viewList = edificeManager.getAllEdifice(Integer.parseInt(areaId));
		
		return "ajaxedifice";
	}
}