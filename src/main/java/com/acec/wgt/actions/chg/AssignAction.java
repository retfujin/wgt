package com.acec.wgt.actions.chg;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeAssignEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.chg.AssignManager;
/**
 * 指定费用录入和支出费用录入
 * 现金账和银行账
 * 物业公司费用表(淮南物业)
 * @author hua
 * @version 2009-11-26
 */
public class AssignAction extends BaseAction{
	
	private static final long serialVersionUID = -6602979377900139672L;

	@Autowired
	private AreaManager areaManager;
	@Autowired
	private EdificeManager edificeManager;
	@Autowired
	private AssignManager assignManager;
	@Autowired
	private CellManager cellmenager;

	
	private Page page = new Page(50);//每页20条记录
	private ChargeUserPayRecordEO entity;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	
	/**
	 * 添加指定费用收入(按小区)
	 * @return
	 */
	public String add(){
		viewList=areaManager.getAreaALL();
	
		return "add";
	}

	/**
	 * 添加指定费用收入(按户临时)
	 * @return
	 */
	@Deprecated
	public String add2(){
		viewList=areaManager.getAreaALL();
		retList=assignManager.getAssignList();
		return "add2";
	}	
	
	/**
	 * 添加指定费用收入(按户常规)
	 * @return
	 */
	public String add3(){
		viewList=areaManager.getAreaALL();
	//	retList=assignManager.getAssignList();
		return "add3";
	}
	
	/**
	 * 按指定项目收费收入
	 * @return
	 */
	@Deprecated
	public void savearea(){
		//判断收费名称是否为新增
		String chargeName=getRequest().getParameter("chargeName");
		String recordMonth=getRequest().getParameter("recordMonth");

		try {
			//保存新增的收费类型
			if (chargeName != null && !"".equals(chargeName)) {
				ChargeAssignEO chargeAssign = new ChargeAssignEO();
				chargeAssign.setAreaId(entity.getAreaId());
				chargeAssign.setChargeName(chargeName);
				assignManager.saveAssign(chargeAssign);

				entity.setChargeName(chargeName);
			}
			assignManager.saveChargeHouseDetail(entity, recordMonth);
			write("{success:true,msg:'操作成功'}");
		} catch (Exception e) {
			e.printStackTrace();
			write("{success:false,msg:'操作失败：原因："+e.getMessage()+"'}");
		}
		return ;
	}
	
	/**
	 * 按指定到户收费收入
	 * @return
	 */
	@Deprecated
	public String savehou(){
		//判断收费名称是否为新增
		String chargeName=getRequest().getParameter("chargeName");
		String beginTime=getRequest().getParameter("beginTime");
		String endTime=getRequest().getParameter("endTime");
		String houseId=getRequest().getParameter("houseId");
		forwardUrl = "assign!add2.action";
		if(beginTime==null || beginTime.equals("")){
			addActionError("请选择开始时间");
			return "forward";
		}else{
			try{
				entity.setRecordMonth(Utils.strToDate(beginTime+"-01"));	
			}catch(Exception er){
				addActionError("日期转换错误");
			}
		}		
		if(endTime==null || endTime.equals("")){
			addActionError("请选择截止时间");
			return "forward";
		}
		try{
			if(Utils.strToDate(beginTime+"-01").after(Utils.strToDate(endTime+"-01"))){
				addActionError("开始时间不能小于截止时间!");
				return "forward";
			}
		}catch (Exception e) {
			addActionError("日期转换错误!");
			return "forward";
		}
		
		if(houseId==null || houseId.equals("")){
			addActionError("请选择房间编号");
			return "forward";
		}else{
			HouseEO house=cellmenager.getHouse(houseId);
			entity.setHouse(house);
		}
		
		//保存新增的收费类型
		if(chargeName!=null && !"".equals(chargeName)){
			ChargeAssignEO chargeAssign=new ChargeAssignEO();
			chargeAssign.setAreaId(entity.getAreaId());
			chargeAssign.setChargeName(chargeName);
			assignManager.saveAssign(chargeAssign);
			
			entity.setChargeName(chargeName);
		}
		entity.setPayName(cellmenager.getOwnerName(entity.getHouse().getId()));
//		assignManager.saveAll(entity);
		assignManager.saveChargeHouseDetailvoid2(entity, beginTime, endTime);
		
		forwardUrl = "report!paydetail.action?houseId="+entity.getHouse().getId();
		return "forward";
	}
	
	
	/**
	 * 按指定到户收费收入(常规类费用)
	 * @return
	 */
	public void save3(){
		//判断收费名称是否为新增
		int chargeId=getRequestValue("chargeId",0);
		String recordMonth=getRequest().getParameter("recordMonth");
		String remark=getRequest().getParameter("remark");
		String houseId=getRequest().getParameter("houseId");
		String factMoney=getRequest().getParameter("factMoney");
		String bh=getRequest().getParameter("bh");
		
		String gatheringTime=getRequest().getParameter("gatheringTime");
		
		HttpSession session = getRequest().getSession();
		String userName = (String) session.getAttribute("userName");// 录入人
		
		if(houseId==null || houseId.equals("")){
			write("{success:false,msg:'操作失败！没有房间编号'}");
		}else if(chargeId<=0){
			write("{success:false,msg:'操作失败！没有收费项目编号'}");
		}else{
			
			try {
				assignManager.saveChargeHouseDetailvoid3(houseId,chargeId,factMoney,bh,userName,remark,recordMonth,gatheringTime);
				write("{success:true,msg:'操作成功'}");
			} catch (Exception e) {
				e.printStackTrace();
				write("{success:false,msg:'操作失败！"+e.getMessage()+"'}");
			}
			
		}
		return ;
	}
	
	/**
	 * 按指定到户收费收入(常规类费用)
	 * @return
	 */
	public void saveArea3(){
		//判断收费名称是否为新增
		int chargeId=getRequestValue("chargeId",0);
		String recordMonth=getRequest().getParameter("recordMonth");
		String remark=getRequest().getParameter("remark");
		Integer areaId=getRequestValue("areaId",0);
		
		String ownerName = getRequestValue("ownerName","");
		
		String factMoney=getRequest().getParameter("factMoney");
		String bh=getRequest().getParameter("bh");
		
		String gatheringTime=getRequest().getParameter("gatheringTime");
		
		HttpSession session = getRequest().getSession();
		String userName = (String) session.getAttribute("userName");// 录入人
		
		if(areaId<0){
			write("{success:false,msg:'操作失败！没有小区编号'}");
		}else if(chargeId<=0){
			write("{success:false,msg:'操作失败！没有收费项目编号'}");
		}else{
			try {
				assignManager.saveChargeAreaDetailvoid3(areaId,ownerName,chargeId,factMoney,bh,userName,remark,recordMonth,gatheringTime);
				write("{success:true,msg:'操作成功'}");
			} catch (Exception e) {
				e.printStackTrace();
				write("{success:false,msg:'操作失败！"+e.getMessage()+"'}");
			}
			
		}
		return ;
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
	

	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public ChargeUserPayRecordEO getEntity() {
		return entity;
	}
	public void setEntity(ChargeUserPayRecordEO entity) {
		this.entity = entity;
	}	 
}