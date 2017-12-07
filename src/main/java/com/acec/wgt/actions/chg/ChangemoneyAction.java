package com.acec.wgt.actions.chg;



import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import com.acec.commons.util.Utils;
import com.acec.core.orm.Page;
import com.acec.core.utils.CharTools;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.chg.ChangemoneyManager;



/**
 * 应收费用调整
 */

public class ChangemoneyAction extends BaseAction{
	
	private static final long serialVersionUID = -6602979377900139672L;

	@Autowired
	private ChangemoneyManager changemoneyManager;	
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private CellManager cellManager;
	
	private Page page = new Page(50);//每页20条记录
	
	private ChargeHouseDetailEO entity;

	/*
	 * 应收金额调整首页
	 * 先给出调整范围
	 */
	public String index()
	{
		viewList = areaManager.getAreaALL();
		retList = cellManager.getHouseList();
		return "index";
	}
	
	/*
	 * 取查询结果
	 */
	public String getInfo()
	{
		String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "";
		String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
		String chargeId = getRequest().getParameter("chargeId") != null ? getRequest().getParameter("chargeId") : "";
		String beginTime = getRequest().getParameter("beginTime") != null ? getRequest().getParameter("beginTime") : "";
		String endTime = getRequest().getParameter("endTime") != null ? getRequest().getParameter("endTime") : "";
		String ownerName = getRequest().getParameter("ownerName")!=null ? getRequest().getParameter("ownerName"):"";
		String id = getRequest().getParameter("id")!=null ? getRequest().getParameter("id"):"";
		
		CharTools charTools = new CharTools();
		try {
			ownerName = new String(ownerName.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ownerName = charTools.Utf8URLdecode(ownerName);
		
		page = changemoneyManager.getInfo(page,areaId,houseId,chargeId,beginTime,endTime,ownerName,id);
	
		return "list";
	}
	
	/*
	 * 更改应收
	 */
	public void updateChargeMoney()
	{
		String[] ids = getRequest().getParameterValues("ids");
		String[] oughtMoneys = getRequest().getParameterValues("oughtMoneys");
		String[] ownerNames = getRequest().getParameterValues("ownerNames");
		
		try
		{
			if(ids==null)
				write("{success:false,msg:'操作失败！没有找到记录'}");
			else
				changemoneyManager.updateChargeOughtMoney(ids,oughtMoneys,ownerNames);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			write("{success:false,msg:'操作失败！"+e.getMessage()+"'}");
			return;
		}
		
		write("{success:true,msg:'保存成功'}");
		return;
	}

	/*
	 * 删除费用
	 */
	public void deleteMoney()
	{
		int pid = getRequestValue("pid",0);
		try
		{
			changemoneyManager.deleteMoney(pid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			write("{success:false,msg:'操作失败！"+e.getMessage()+"'}");
			return;
		}
		write("{success:true,msg:'操作成功！'}");
		
		return;
	}
	
	//改变缴费日期
	public void chargeDate(){
		int pid = getRequestValue("pid",0);
		String rq = getRequestValue("rq","");
		try
		{
			if(pid<1 || rq.length()<5)
				write("{success:false,msg:'操作失败！参数错误，请确认缴费日期是否正确'}");
			else{
				changemoneyManager.chargeDate(pid,rq);
				write("{success:true,msg:'操作成功！'}");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			write("{success:false,msg:'操作失败！"+e.getMessage()+"'}");
			return;
		}
	}
	
	/*
	 * 取收费项目id
	 */
	public String getchargeid()
	{
		String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "0";
		viewList = changemoneyManager.getChargeId(Integer.parseInt(areaId));
		return "ajaxcharge";
	}
	
	
	
	
	
	
	
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ChargeHouseDetailEO getEntity() {
		return entity;
	}

	public void setEntity(ChargeHouseDetailEO entity) {
		this.entity = entity;
	}
	
}
