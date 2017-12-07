package com.acec.wgt.actions.chargemanager;


import java.util.ArrayList;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;


import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.ChargehouseVO;
import com.acec.wgt.models.chargemanager.HousechargeEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.chargemanger.BasedataManager;
import com.acec.wgt.service.chargemanger.HouseChargeManager;

/**
 * 
 * 收费管理--客户收费项目设置
 * @author Administrator
 *
 */
@Namespace("/chargeowner")
public class OwnerAction extends BaseAction{
	@Autowired
	private HouseChargeManager houseChargeManager;
	@Autowired
	private BasedataManager basedataManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private EdificeManager edificeManager;

	
	
	private String retStr;
	private List retList;
	private List retList_1;
	private String pageBar="";
	

	private HouseEO house;
	private HousechargeEO housecharge;

	
	private Page page = new Page(15);//每页15条记录
	/*
	 * 取业主房间收费项目列表--查询页面
	 */
	public String listquery()
	{
		viewList = areaManager.getAreaALL();		
		return "listquery";
	}
	/*
	 * 取业主房间收费项目列表--结果页
	 */
	public String list()
	{
		Integer areaId = getRequestValue("areaId",0);
		String edificeId = getRequestValue("edificeId","");
		String houseId = getRequestValue("houseId","");
		
		int no = getRequestValue("pageNo", 1);

		PaginatorTag pt = houseChargeManager.getChargeHousePage(areaId,edificeId,houseId,no,15);	
		pt.setShowAllPages(true);
		pt.setShowTotal(true);
		pt.setUrl("owner!list.action"+getParamString(""));
		retList = pt.getData();
		pageBar = pt.showPage();
		
		return "list";
	}
	
	
	/*
	 * 单个房间的收费项目保存
	 */
	public void saveChargeHouseById()
	{	
		try
		{			
			List<HousechargeEO> l = new ArrayList<HousechargeEO>();		
			for(int i=0;i<viewList.size();i++)
			{
				ChargehouseVO ch = (ChargehouseVO) viewList.get(i);
								
				//选择了收费项目
				if(ch.getIsSelect()==1)
				{
					HousechargeEO housechargeEO = new HousechargeEO();
					
					ChargeBasedataEO cb = new ChargeBasedataEO();
					cb.setId(ch.getChargeBasedataId());
					housechargeEO.setChargeBasedata(cb);
					
					
					housechargeEO.setHouse(new HouseEO(ch.getHouseId()));
					l.add(housechargeEO);
				}
			}
			//全部没有选中
			if(l.isEmpty())
				houseChargeManager.deleteHouseCharge("'"+house.getId()+"'");
			else
				houseChargeManager.addChargeAll(l, "'"+house.getId()+"'");
			
			write("{success:true,msg:'保存成功'}");
			
		}catch (Exception ex) 
		{
			logger.error("单个房间的收费项目保存", ex);
			write("{success:false,msg:'保存失败。"+ex.getMessage()+"'}");
		}
		return;
	}
	
	/*
	 * 房间收费管理
	 * 批量设置房间收费项目
	 */
	public String addCharge()
	{
		viewList=basedataManager.getAll();
		retList=areaManager.getAreaALL();

		return "addcharge";
	}
	
	/*
	 * 批量保存房间收费项目
	 */
	public void saveChargeAll()
	{	
		String areaId = getRequest().getParameter("areaId");//小区
		String edificeId = getRequest().getParameter("edificeId");//楼栋 
		String cell = getRequest().getParameter("cell");//单元
		String symbol=getRequest().getParameter("symbol");//大于等于    或   小于
		String layer = getRequest().getParameter("layer");//层数
		String [] chargeId=getRequest().getParameterValues("chargeId");
		if(chargeId==null){
			write("{success:false,msg:'保存失败。请选择收费项目'}");
			return;
		}
		try{
			houseChargeManager.saveChargeAll(areaId, edificeId,cell,symbol,layer,chargeId);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			logger.error("批量保存房间收费项目", ex);
			write("{success:false,msg:'保存失败。"+ex.getMessage()+"'}");
		}			
		
		return;
	}	
	
	
	
	
	

	/*
	 * 根据小区id取楼栋
	 */
	public String getEdifice()
	{
		String areaId = getRequest().getParameter("areaId");
		viewList = edificeManager.getAllEdifice(Integer.parseInt(areaId));
		
		return "ajaxedifice";
	}
	/*
	 * 根据小区id取楼栋
	 */
	public String getHouseInfo()
	{
		String edificeId = getRequest().getParameter("edificeId");
		
		viewList = edificeManager.findHouseForEdifice(edificeId);
		
		return "ajaxhouse";
	}
	/*
	 * 根据小区id取收费项目
	 */
	public String getChargeBasedata()
	{
		String areaId = getRequest().getParameter("areaId");
		viewList = basedataManager.getAllByAreaId(areaId);
		return "ajaxchargebasedata";
	}
	
	/*
	 * 根据小区id取收费项目
	 */
	public String getChargeBasedataAll()
	{
		String areaId = getRequest().getParameter("areaId");
		if(areaId==null)
			return null;
		retList = basedataManager.getAllByAreaId(areaId);
		return "chargebasedata";
	}
	
	/*
	 * 编辑单个房间收费项目
	 */
	public String editChargeHouseById()
	{
		//房间收费项目
		viewList = houseChargeManager.getChargeHouseById(house.getId());
		return "edit";

	}
	
	public String getRetStr() {
		return retStr;
	}
	public void setRetStr(String retStr) {
		this.retStr = retStr;
	}
	
	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}
	
	public List getRetList_1() {
		return retList_1;
	}

	public void setRetList_1(List retList_1) {
		this.retList_1 = retList_1;
	}

	public HouseEO getHouse() {
		return house;
	}
	public void setHouse(HouseEO house) {
		this.house = house;
	}
	
	public HousechargeEO getHousecharge() {
		return housecharge;
	}
	public void setHousecharge(HousechargeEO housecharge) {
		this.housecharge = housecharge;
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public String getPageBar() {
		return pageBar;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}





}
