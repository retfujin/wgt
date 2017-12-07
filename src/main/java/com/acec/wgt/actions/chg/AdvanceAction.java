package com.acec.wgt.actions.chg;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.chg.ChargeAdvanceEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chg.AdvanceManager;
import com.acec.wgt.service.basedata.CellManager;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * @author 预存
 */

public class AdvanceAction extends BaseAction {

    private static final long serialVersionUID = -6602979377900139672L;
    private Page page = new Page(50);// 每页20条记录

    private ChargeAdvanceEO chargeAdvance;
    private List retList;

    @Autowired
    private com.acec.wgt.service.chg.PayManager payManager;

    @Autowired
    private AdvanceManager advanceManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private CellManager cellManager;

    /**
     * 预存列表
     */
    public String advancelist() {
	String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "";
	String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
	String ownerName = getRequest().getParameter("ownerName") != null ? getRequest().getParameter("ownerName") : "";

	retList = areaManager.getAreaALL();
	page = advanceManager.getAdvanceListByPage(page, areaId, houseId, ownerName);
	return "list";
    }

    /**
     * 预存收费
     * 
     * @return
     */
    public String advanceInput() {
	viewList = areaManager.getAreaALL();
	retList = cellManager.getHouseList();

	return "input";
    }

    /**
     * 预存退费
     * 
     * @return
     */
    public String refund() {
	String id = getRequest().getParameter("id");
	String bh = getRequest().getParameter("bh");
	try {
	    bh = advanceManager.refund(id, bh);
	} catch (Exception e) {
	    e.printStackTrace();
	    addActionError("退费操作失败." + e.getMessage());
	    return ERROR;
	}
	forwardStr = "数据保存成功！";
	getRequest().setAttribute("recordBh", bh);
	return "saveback";
    }

    /**
     * 保存预存收费项目
     * 
     * @return
     */
    // @InputConfig(methodName="advanceInput")

    public String save() {
	forwardUrl = "advance!advancelist.action";

//	String chargeBasedataId = getRequest().getParameter("chargeBasedataId");
//	String bigType = getRequest().getParameter("bigType");

	try {
	    if (-1 == chargeAdvance.getAreaId()) {
		addActionError("请先选择小区！");
		return ERROR;
	    }
	    if (null == chargeAdvance.getHouseId() || "".equals(chargeAdvance.getHouseId())) {
		addActionError("请输入房间编号！");
		return ERROR;
	    }
	    if (null == chargeAdvance.getBh() || "".equals(chargeAdvance.getBh())) {
		addActionError("请输入票据号！");
		return ERROR;
	    }
	    if (advanceManager.checkHouse(chargeAdvance.getAreaId(), chargeAdvance.getHouseId()) == false) {
		addActionError("房间编号不合法！请输入正确的房间编号或参看基础资料设置");
		return ERROR;
	    }
	    // if(StringUtils.isEmpty(chargeBasedataId)){
	    // addActionError("请选择收费项目！");
	    // return ERROR;
	    // }

	    // 预交收费项目
//	    chargeAdvance.setChargeId(Integer.parseInt(chargeBasedataId));
//	    chargeAdvance.setBigType(bigType);

//	    advanceManager.saveAdvanceForChargeId(chargeAdvance);
	    advanceManager.saveAdvance(chargeAdvance);
	    forwardStr = "数据保存成功！";
	    getRequest().setAttribute("recordBh", chargeAdvance.getBh());
	    return "savesuccess";

	} catch (Exception e) {
	    e.printStackTrace();
	    addActionError(e.getMessage());
	    return ERROR;
	}
    }

    /*
     * 根据小区id取收费项目
     */
    public String getChargeId() {
	String areaId = getRequest().getParameter("areaId");
	retList = advanceManager.getChargeId(Integer.parseInt(areaId));
	return "ajaxchargebasedata";
    }

    public void ajaxBh() {
	String areaId = getRequest().getParameter("areaId");
	if (areaId != null && areaId.length() == 4) {
	    outString(payManager.getNewBh(areaId));
	}
    }

    public ChargeAdvanceEO getChargeAdvance() {
	return chargeAdvance;
    }

    public void setChargeAdvance(ChargeAdvanceEO chargeAdvance) {
	this.chargeAdvance = chargeAdvance;
    }

    public List getRetList() {
	return retList;
    }

    public void setRetList(List retList) {
	this.retList = retList;
    }

    public Page getPage() {
	return page;
    }

    public void setPage(Page page) {
	this.page = page;
    }

}
