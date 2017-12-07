package com.acec.wgt.actions.chargemanager.ownermeter;



import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseFileUploadAction;
import com.acec.wgt.models.baseData.HouseMeterEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.HouseMeterManager;
import com.acec.wgt.service.chargemanger.HouseMeterSelManager;
/**
 * 
 * 收费管理--业主表设置
 * @author Administrator
 *
 */
public class OwnermeterAction extends BaseFileUploadAction{
	@Autowired
	private HouseMeterSelManager houseMeterSelManager;
//	@Autowired
//	private HouseChargeManager houseChargeManager;
	@Autowired
	private HouseMeterManager houseMeterManager;
	@Autowired
	private AreaManager areaManager;
	
	private Page page = new Page(15);//每页15条记录
	private HouseMeterEO houseMeter1;

	private String areaName;
	private String pageBar;
	
	
	public String listquery(){
		retList=areaManager.getAreaALL();//得到所有管理处
		return "listquery";
	}
	/*
	 * 业户表设置
	 */
	public String list()
	{
		String areaId=getRequest().getParameter("areaId") !=null ? getRequest().getParameter("areaId"):"";
		String houseId = getRequest().getParameter("houseId") !=null ? getRequest().getParameter("houseId"):"";
		String meterType = getRequest().getParameter("meterType") !=null ? getRequest().getParameter("meterType"):"";
		String area_id=(String)getSession().get("areaIds");
		StringBuilder sb=new StringBuilder();
		if(!areaId.equals(""))
			sb.append(" and house.areaId=").append(areaId);
		if(!"".equals(houseId))
			sb.append(" and house.id like '").append(houseId).append("%'");
		if(!"".equals(meterType))
			sb.append(" and meterType = '").append(com.acec.commons.util.Utils.getMeterTypeById(meterType)).append("'");
		if(!area_id.equals(""))
			sb.append("and house.areaId in (").append(area_id).append(") ");
		
		//page = houseMeterManager.getAllHouseMeter(page,sb.toString());
	
		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = houseMeterSelManager.getHouseMeterListByPage(no, 15, sb.toString());
		pt.setUrl("ownermeter!list.action"+getParamString(""));
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
		return "list";
	}
	
	public String del(){
		
		String id =  getRequest().getParameter("id");
		try{
			houseMeterManager.delHouseMeter(id);			
			forwardStr = "删除成功";
			forwardUrl = "ownermeter!getOwnerMeter.action";
		}catch(Exception ex){
			logger.error("删除失败", ex);
			forwardStr="删除失败!";
		}
		return "popresult";
	}
	
	
	/*
	 * 更新业户表度数
	 */
	public String updateHouseMeter()
	{
		try
		{
			String id = getRequest().getParameter("id") !=null ? getRequest().getParameter("id"):"";
			//当前度数
			String nowRecord = getRequest().getParameter("nowRecord") !=null ? getRequest().getParameter("nowRecord"):"";
			//回转度数
			String backRecord = getRequest().getParameter("backRecord") !=null ? getRequest().getParameter("backRecord"):"0";
			//倍率
			String times = getRequest().getParameter("timesNow") !=null ? getRequest().getParameter("timesNow") :"1";
			
			if("".equals(id) || "".equals(nowRecord))
			{
				forwardStr ="取业主房间表信息失败";
				return ERROR;
			}
			
			houseMeterManager.updateHouseMeter(Integer.parseInt(id),Integer.parseInt(nowRecord),Integer.parseInt(backRecord),Integer.parseInt(times));
			forwardStr = "更新成功！";
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			forwardStr = "更新表度数失败，请联系管理员!"+e.getMessage();
		}
		return "popresult";
	}
	
	/**
	 * 批量设置业户表资料
	 * @return
	 */
	public String ownermeterall(){
		viewList=areaManager.getAreaALL();
		return "ownermeterall";
	}
	
	public String down(){
		String areaId = getRequest().getParameter("areaId");
		logger.debug("下载导入业主模板，小区id"+areaId);
		try{
			houseMeterManager.writehousemeterExcaa(areaId);
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
			forwardStr="下载excel失败。原因："+ex.getMessage();
			return "result";
		}
	}
	
	
	/**
	 * 上传业户表资料
	 * @return
	 */
	public String uploadhousemeterExcel(){
		if(theFile!=null){
			logger.debug(contenttype);
			if(!contenttype.equals("application/vnd.ms-excel")&&!contenttype.equals("application/octet-stream")){
				forwardStr="上传的文件类型不正确。类型为："+contenttype;	
			}			
			try {
				houseMeterManager.savehousemeterForExc(theFile);
				forwardStr="上传excel成功。";
			} catch (Exception ex) {
				forwardStr = "导入excel失败。原因：" + ex.getMessage();
				logger.error("导入业户表资料excel失败", ex);
			}
		}else{
			forwardStr="没有获取到文件";
			logger.error("导入业户表资料excel失败,没有获取到文件");
		}		
		return "result";
	}
	
	/*
	 * 客户报表---客户表更换历史报表 
	 */
	public String oldmeterlistquery()
	{
		
		viewList=areaManager.getAreaALL();		
		return "oldmeterlistquery";	
	}
	
	/*
	 * 客户报表---客户表更换历史报表
	 */
	public String oldmeterlist()
	{
		Integer areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? Integer.parseInt(getRequest().getParameter("areaId")) : 0;
		String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
		String meterType = getRequest().getParameter("meterType") != null ? getRequest().getParameter("meterType") : "";		
		page  = houseMeterManager.oldmeterlist(page,areaId,houseId,meterType);
		getRequest().setAttribute("paramString", getParamString(""));
		return "oldmeterlist";	
	}
	

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
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
}