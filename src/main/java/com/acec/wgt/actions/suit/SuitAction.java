package com.acec.wgt.actions.suit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;

import com.acec.wgt.models.suit.SuitEO;

import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.suit.SuitCheckManager;

import com.opensymphony.xwork2.ActionContext;

public class SuitAction extends BaseAction {

    @Autowired
    private AreaManager areaManager;
    @Autowired
    private EdificeManager edificeManager;
    @Autowired
    private CellManager cellManager;
    @Autowired
    private SuitCheckManager suitCheckManager;



    private List retList;
    private List viewList;
    private List viewList_1;
    private String pageBar;

    private String forwardUrl;// 想要跳转的url
    private String forwardStr;// 处理结果字符串
    private String areaId;
    private String edificeId;
    private String houseId;
    private SuitEO entity;

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    
 // 新增登记
    public String add() {
		retList = areaManager.getAreaALL();
		return "add";
    }

    /**
     * 显示未处理
     * 
     * @return
     */
    public String list1() {
	
		String flowSate = "=0";
		String type = ServletActionContext.getRequest().getParameter("type");
		String areaId = ServletActionContext.getRequest().getParameter("areaId");
		String dayNum = ServletActionContext.getRequest().getParameter("dayNum");
		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if (pageNo == null)
		    pageNo = "1";
		int no = Integer.parseInt(pageNo);
		
		String beginDate = ServletActionContext.getRequest().getParameter("beginDate");
		String endDate = ServletActionContext.getRequest().getParameter("endDate");
		
		String houseId = ServletActionContext.getRequest().getParameter("houseId");
	
		PaginatorTag pt = suitCheckManager.getListNobyPage(flowSate, type, areaId, beginDate, endDate, houseId, dayNum, no, 30);
		pt.setUrl("suit!listsuitProcess.action");
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
		retList = areaManager.getAreaALL();
	
		return "list1";
    }

 // 编辑登记
    public String edit1() {
    	retList = areaManager.getAreaALL();
	String id = ServletActionContext.getRequest().getParameter("id");
	log.debug("取到的值：" + id);
	if (id != null && !id.equals("")){
	    entity = suitCheckManager.getSuit(Integer.parseInt(id));
	    entity.setAreaName(areaManager.getAreaById(entity.getAreaId()).getAreaName()); 
	}
	return "edit1";
    }
    
    
    public String print() {
    	String id = getRequest().getParameter("id");
    	if (id != null && !id.equals("")){
    	    entity = suitCheckManager.getSuit(Integer.parseInt(id));
    	    entity.setAreaName(areaManager.getAreaById(entity.getAreaId()).getAreaName()); 
    	}
    	return "print";
    }
    
    /**
     * 保存新增投诉登记
     * 
     * @return
     */
    public void save() {
	try {
				
		if(StringUtils.isEmpty(entity.getInvestigationState()))
			entity.setFlowState("0");//未处理
		else
			entity.setFlowState("1");//处理
		
	    suitCheckManager.save(entity);
	    write("{success:true,msg:'保存成功'}");
	} catch (Exception ex) {
	    write("{success:false,msg:'保存失败.原因：！" + ex.getMessage() + "'}");
	    ex.printStackTrace();
	}
	return;
    }
 
    /**
     * 删除投诉登记
     * 
     * @return
     */
    public void del() {
		String id = ServletActionContext.getRequest().getParameter("id");
		try {
		    suitCheckManager.del(Integer.parseInt(id));
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'删除失败.原因：" + ex.getMessage()+"'}");
		}
		return ;
    }

    /**
     * 显示处理
     * 
     * @return
     */
    public String list2() {
	
	String flowSate = ">0";
	String type = ServletActionContext.getRequest().getParameter("type");
	String areaId = ServletActionContext.getRequest().getParameter("areaId");
	String dayNum = ServletActionContext.getRequest().getParameter("dayNum");
	String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
	
	String beginDate = ServletActionContext.getRequest().getParameter("beginDate");
	String endDate = ServletActionContext.getRequest().getParameter("endDate");
	
	String houseId = ServletActionContext.getRequest().getParameter("houseId");
	
	if (pageNo == null)
	    pageNo = "1";
	int no = Integer.parseInt(pageNo);

	PaginatorTag pt = suitCheckManager.getListNobyPage(flowSate, type, areaId, beginDate, endDate, houseId, dayNum, no, 30);
	pt.setUrl("suit!listsuitProcess.action");
	pt.setShowTotal(true);
	pt.setShowAllPages(true);
	pt.setStrUnit("条");
	viewList = pt.getData();
	pageBar = pt.showPage();
	retList = areaManager.getAreaALL();

	return "list2";
    }
    
    //回访编辑
    public String edit2() {
    	retList = areaManager.getAreaALL();
    	String id = ServletActionContext.getRequest().getParameter("id");
	
	if (id != null) {
	    entity = suitCheckManager.getSuit(Integer.parseInt(id));
	    retList = areaManager.getAreaALL();
	}
	return "edit2";
    }
    
    //只是显示
    public String view2() {
	String id = ServletActionContext.getRequest().getParameter("id");
	
	if (id != null) {
	    entity = suitCheckManager.getSuit(Integer.parseInt(id));
	    retList = areaManager.getAreaALL();
	}
	return "view2";
    }
    
    /**
     * 保存编辑2
     * 
     * @return
     */
    public void editsave2() {
	try {
	    if ((entity.getSuitDate3() == null) || (entity.getSuitDate3().equals(""))) {
	    	entity.setSuitDate3(null);
	    }
	    if(!StringUtils.isEmpty(entity.getProcessState()))//回访结果不为空
	    	entity.setFlowState("2");
		else
			entity.setFlowState("1");
	    
	 
	    suitCheckManager.save(entity);
	    write("{success:true,msg:'保存成功'}");
	} catch (Exception ex) {
		ex.printStackTrace();
		write("{success:false,msg:'保存失败.原因：" + ex.getMessage()+"'}");
		
	}
	return ;
    }
    
    
    public String checkedit() {// 投诉查看
	String id = ServletActionContext.getRequest().getParameter("id");
	if (id != null && !id.equals(""))
	    entity = suitCheckManager.getSuit(Integer.parseInt(id));
	retList = areaManager.getAreaALL();

	return "addsuitChecka";
    }


    /**
     * 投诉统计
     * 
     * @return
     */
    public String tsstat() {
	String beginTime = getRequest().getParameter("beginTime") != null ? getRequest().getParameter("beginTime") : "";
	String endTime = getRequest().getParameter("endTime") != null ? getRequest().getParameter("endTime") : "";
	viewList = suitCheckManager.tsstat(beginTime, endTime);
	return "tsstat";
    }

    /*
     * 根据小区id取楼栋
     */
    public String getEdifice() {
	String areaId = getRequest().getParameter("areaId");
	viewList = edificeManager.getAllEdifice(Integer.parseInt(areaId));
	return "getedifice";
    }

    /*
     * 根据小区id取楼栋
     */
    public String getHouseInfo() {
	String edificeId = getRequest().getParameter("edificeId");
	viewList = edificeManager.findHouseForEdifice(edificeId);
	return "gethouse";
    }

//    public String getHouseDetail() {
//	String houseId = getRequest().getParameter("houseId");
//	// viewList = cellManager.getOwnerName(houseId);
//	houseEO = cellManager.getHouse(houseId);
//	System.out.println(houseEO.getOwnerName());
//	return "gethousedetail";
//    }

    @SuppressWarnings("unchecked")
    public String getHouseDetail() {
	String houseId = getRequest().getParameter("houseId");

	Object[] houseProperty = cellManager.getHouseAddress(houseId);
	Map map = new HashMap();
	if (houseProperty == null)
	    map.put("houseAddress", "没有找到业主");
	else {
	    map.put("houseAddress", houseProperty[0]);
	    map.put("buildnum", houseProperty[1]);
	    map.put("ownerName", houseProperty[2]);
	    map.put("mobTel", houseProperty[3]);
	}
	Struts2Utils.renderJson(map);
	// Struts2Utils.renderText(houseAddress);
	return null;
    }

  
    public List getViewList() {
	return viewList;
    }

    public void setViewList(List viewList) {
	this.viewList = viewList;
    }

    public String getPageBar() {
	return pageBar;
    }

    public void setPageBar(String pageBar) {
	this.pageBar = pageBar;
    }

    public SuitCheckManager getSuitCheckManager() {
	return suitCheckManager;
    }

    public void setSuitCheckManager(SuitCheckManager suitCheckManager) {
	this.suitCheckManager = suitCheckManager;
    }

    public String getForwardUrl() {
	return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
	this.forwardUrl = forwardUrl;
    }

    public String getForwardStr() {
	return forwardStr;
    }

    public void setForwardStr(String forwardStr) {
	this.forwardStr = forwardStr;
    }

    public String getAreaId() {
	return areaId;
    }

    public void setAreaId(String areaId) {
	this.areaId = areaId;
    }

    public String getEdificeId() {
	return edificeId;
    }

    public void setEdificeId(String edificeId) {
	this.edificeId = edificeId;
    }

    public String getHouseId() {
	return houseId;
    }

    public void setHouseId(String houseId) {
	this.houseId = houseId;
    }

    public SuitEO getEntity() {
	return entity;
    }

    public void setEntity(SuitEO entity) {
	this.entity = entity;
    }

 

    public List getViewList_1() {
	return viewList_1;
    }

    public void setViewList_1(List viewList_1) {
	this.viewList_1 = viewList_1;
    }

    public List getRetList() {
	return retList;
    }

    public void setRetList(List retList) {
	this.retList = retList;
    }

}