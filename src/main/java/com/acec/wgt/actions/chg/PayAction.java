package com.acec.wgt.actions.chg;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.chg.ChargeAdvanceEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;
import com.acec.wgt.models.chg.ReleaseEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.chg.AdvanceManager;
import com.acec.wgt.service.chg.PaySelManager;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

/**
 * 
 * @author 包括账务中心的的内容
 * 
 */

public class PayAction extends BaseAction {

    private static final long serialVersionUID = -6602979377900139672L;

    @Autowired
    private com.acec.wgt.service.chg.PayManager payManager;
    @Autowired
    private AreaManager areaManager;

    @Autowired
    private com.acec.wgt.service.chargemanger.BasedataManager chargeBasedataManager;
    @Autowired
    private EdificeManager edificeManager;
    @Autowired
    private CellManager cellManager;
    @Autowired
    private PaySelManager paySelManager;
    @Autowired
    private AdvanceManager advanceManager;

    private Page page = new Page(20);// 每页30条记录

    private ChargeHouseDetailEO entity;
    private ChargeUserPayRecordEO entity2;

    private List detailList;

    private Map map;
    private String pageBar;

    public Map getMap() {
	return map;
    }

    private List viewList1 = new ArrayList();
    private List viewList2;

    public List getViewList1() {
	return this.viewList1;
    }

    public List getViewList2() {
	return this.viewList2;
    }

    public ChargeAdvanceEO getChargeAdvance() {
	return chargeAdvance;
    }

    public void setChargeAdvance(ChargeAdvanceEO chargeAdvance) {
	this.chargeAdvance = chargeAdvance;
    }

    private ChargeAdvanceEO chargeAdvance;
    private ReleaseEO release;

    public ReleaseEO getRelease() {
	return release;
    }

    public void setRelease(ReleaseEO release) {
	this.release = release;
    }

    public static long getSerialVersionUID() {
	return serialVersionUID;
    }

    private ChargeUserPayRecordEO chargeUserPayRecord;

    /**
     * 账务中心筛选页面
     * 
     * @return
     */
    public String accountcenterquery() {
	retList = areaManager.getAreaALL();
	// 取得收费名称
	viewList = chargeBasedataManager.getChargeName();
	// 取所有编号自动填重
	viewList1 = cellManager.getHouseIdALL("");

	return "accountcenterquery";
    }

    /**
     * 自动填充房间编号(暂不适应了)
     * 
     * @throws JSONException
     */
    public void ajaxhouseId() throws JSONException {
	String houseId = getRequestValue("houseId", "");
	String where = "";
	if (houseId.length() > 0)
	    where = " and id like '" + houseId + "%'";
	viewList = cellManager.getHouseIdALL(where);
	String str = JSONUtil.serialize(viewList);
	write(str);

    }

    public String accountcenter() throws UnsupportedEncodingException {
	String areaId = getRequest().getParameter("areaId");
	String houseId = getRequest().getParameter("houseId");
	String beginDate = getRequest().getParameter("beginDate");
	String endDate = getRequest().getParameter("endDate");
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	StringBuffer sb = new StringBuffer();
	sb.append(" where areaId in(").append(areaIds).append(") ");

	if (houseId != null && !houseId.equals("") && !"null".equals(houseId))
	    sb.append(" and house.id like '").append(houseId).append("%' ");
	if (areaId != null && !areaId.equals("") && !"null".equals(areaId))
	    sb.append(" and areaId=").append(areaId);

	if (beginDate != null && !beginDate.equals("") && !"null".equals(beginDate))
	    sb.append(" and recordMonth>='").append(beginDate).append("-01' ");
	if (endDate != null && !endDate.equals("") && !"null".equals(endDate))
	    sb.append(" and recordMonth<='").append(endDate).append("-01' ");

	if (null != areaId && !"".equals(areaId)) {
	    sb.append(" and areaId=" + areaId);
	}

	String[] chargeId = getRequest().getParameterValues("chargeId");
	String appendUrl = "";
	if (chargeId != null && !"null".equals(chargeId)) {
	    String item = "";
	    for (int i = 0; i < chargeId.length; i++) {
	    	item += ",'" + chargeId[i] + "'";
	    	appendUrl+="&chargeId="+chargeId[i];
	    }
	    if (item.length() > 4) {
		sb.append(" and chargeId in(" + item.substring(1) + ")");
	    }
	}

	String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
	if (pageNo == null)
	    pageNo = "1";
	int no = Integer.parseInt(pageNo);
	PaginatorTag pt = paySelManager.getListByPage(no, 20, sb.toString());
	pt.setUrl("pay!accountcenter.action?areaId=" + areaId + "&houseId=" + houseId + "&beginDate=" + beginDate
		+ "&endDate=" + endDate+appendUrl);
	pt.setShowTotal(true);
	pt.setShowAllPages(true);
	pt.setStrUnit("条");
	viewList = pt.getData();
	pageBar = pt.showPage();

	return "accountcenter";
    }

    // 财务收款
    public String pay() throws UnsupportedEncodingException {
	// 通过房号查找房间id
	String houseId = getRequest().getParameter("houseId");
	// 时间段(按季度)
	String beginDate = getRequest().getParameter("beginDate");
	String endDate = getRequest().getParameter("endDate");
	String chargeId = getRequest().getParameter("chargeId");

	String payType = getRequest().getParameter("payType");// 收款方式 收款 ,优惠

	// 取得收费名称
	viewList2 = chargeBasedataManager.getChargeName();

	if (houseId == null || houseId.length() < 8) {
	    forwardStr = "请输入房间编号进行查看";
	} else {

	    viewList = payManager.getArreargeHouseByHouseId(houseId, beginDate, endDate, chargeId, payType);

	    if (viewList == null || viewList.isEmpty()) {
		forwardStr = "没有找到欠费记录，请重新检索";
	    } else {
		// 取出新的收费单号
		getRequest().setAttribute("bh", payManager.getNewBh(houseId.substring(0, 4)));

	    }
	    getRequest().setAttribute("houseId", houseId);
	}

	getRequest().setAttribute("advanceMoney", advanceManager.getAdvance(houseId));

	// 到只查看欠费的页面
	if ("true".equals(getRequest().getParameter("readonly")))
	    return "payreadonly";
	else if ("privilege".equals(payType)) {// 到优惠的页面
	    return "payprivilege";
	} else
	    // 到缴费的页面
	    return "pay";
    }

    /**
     * 保存缴费记录(实收的或优惠的)
     * 
     * @return
     */
    public String paySave() {

	String houseId = getRequest().getParameter("houseId");
	String[] id = getRequest().getParameterValues("id");
	String[] factMoneys = getRequest().getParameterValues("monthFactMoney");
	String[] advances = getRequest().getParameterValues("advanceMoney");// 每月是否使用预存
	String[] privileges = getRequest().getParameterValues("privilegeMoney");// 每月是否使用优惠
	String[] privilegeReasons = getRequest().getParameterValues("privilegeReason");

	String arrearMoney = getRequest().getParameter("arrearMoney");// 尾数优惠类型

	String oughtTotalMoney = getRequest().getParameter("oughtTotalMoney");// 应收款总额
	String factTotalMoney = getRequest().getParameter("factTotalMoney");// 实收款总额

	String advanceSrc = advanceManager.getAdvance(houseId);// 客户预存总额

	String reason1 = getRequest().getParameter("reason1");// 收款
	String bh = getRequest().getParameter("bh");// 编号
	// 交款日期
	String gatheringDate = getRequest().getParameter("gatheringDate");

	HttpSession session = getRequest().getSession();
	String userName = (String) session.getAttribute("userName");// 录入人

	forwardStr = "完成本次缴费。";

	try {

	    payManager.savePay(houseId, "收款", id, factMoneys, advances, privileges, privilegeReasons, arrearMoney,
		    advanceSrc, userName, reason1, bh, gatheringDate);

	    getRequest().setAttribute("recordBh", bh);
	    return "savesuccess";
	} catch (Exception ex) {
	    forwardStr = "缴费失败:原因:" + ex.getMessage();
	    logger.error("实际收费失败", ex);
	    addActionError("" + forwardStr);
	    return ERROR;
	}
    }

    /**
     * pos打印机页面
     * 
     * @return
     */
    public String printview() {
	String recordBh = getRequest().getParameter("recordBh") != null ? getRequest().getParameter("recordBh") : "";
	if (!"".equals(recordBh)) {
	    viewList = payManager.printViewList(recordBh);
	    retList = payManager.printView(recordBh);
	    return "printview";
	}
	return null;
    }

    /**
     * 从预存账户里面扣除
     */
    public String deductAdance() throws UnsupportedEncodingException {

	String houseId = getRequest().getParameter("houseId");
	houseId = java.net.URLDecoder.decode(houseId, "UTF-8");
	String chargeId = getRequest().getParameter("chargeId");
	logger.debug("房间编号" + houseId);
	String xh = getRequest().getParameter("xh");
	HttpSession session = getRequest().getSession();
	String userName = (String) session.getAttribute("userName");// 录入人

	try {
	    float r_money = 0f;
	    if (xh != null && !xh.equals("") && !xh.equals("null")) {
		r_money = payManager.calAdvance(houseId, Integer.parseInt(xh), userName, chargeId);
	    }
	    if (r_money > 0)
		forwardStr = "扣除成功,扣除金额" + r_money;
	    else
		forwardStr = "扣除失败,账户没有金额";

	    return "deductadanceresult";
	} catch (Exception e) {
	    forwardStr = "扣除失败。原因：" + e.getMessage();
	    logger.error("预存账户扣除失败", e);
	    addActionError(forwardStr);
	    return ERROR;
	}

    }

    /**
     * 收费单明细
     * @return
     */
    public String checkDetail(){
	String pid = getRequest().getParameter("pid") != null ? getRequest().getParameter("pid") : "";
	viewList = payManager.getDetailByPayId(pid);
	return "checkdetail";
    }
    
    /**
     * 收据单查询(保存缴费记录后，会跳转到次) 可以根据编号,
     * 
     * @return
     */

    public String viewreceipt() {

	String bh = getRequest().getParameter("recordBh") != null ? getRequest().getParameter("recordBh") : "";
	String rqStr = getRequest().getParameter("rqStr") != null ? getRequest().getParameter("rqStr") : "";
	String ownerName = getRequest().getParameter("ownerName") != null ? getRequest().getParameter("ownerName") : "";
	String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";

	if (!"".equals(bh)) {
	    viewList = payManager.getReceiptForBh(bh);  
	    return "viewreceiptbh";
	}else if (!"".equals(houseId)) {
	    viewList = payManager.getReceiptForHouseId(houseId);
	    return "viewreceipthouse";
	}
	return null;
    }


    public String saveMoney() {

	String newName = getRequest().getParameter("newName");
	if (null != newName && !"".equals(newName))
	    chargeUserPayRecord.setChargeName(newName);
	try {
	    payManager.savePayRecordMoney(chargeUserPayRecord);
	    return "success-3";
	} catch (Exception ex) {
	    forwardStr = "缴费失败:原因:" + ex.getMessage();
	    ex.printStackTrace();
	    return ERROR;
	}
    }

    
    
    
    
    /**
     * 专用保存车位缴费信息
     * @return
     * @throws UnsupportedEncodingException
     */
    public String payCarport() throws UnsupportedEncodingException {
    	// 通过房号查找房间id
    	String houseId = getRequest().getParameter("houseId");
    	// 时间段(按季度)
    	String beginDate = getRequest().getParameter("beginDate");
    	String endDate = getRequest().getParameter("endDate");
    	String chargeId = getRequest().getParameter("chargeId");

    	String payType = getRequest().getParameter("payType");// 收款方式 收款 ,优惠

    	// 取得收费名称
    	viewList2 = chargeBasedataManager.getChargeName();

    	if(houseId == null || houseId.length() < 8)
    	    forwardStr = "请输入房间编号进行查看";
    	else{
    	    viewList = payManager.getArreargeHouseByHouseId(houseId, beginDate, endDate, chargeId, payType);
    	    if (viewList == null || viewList.isEmpty())
    		forwardStr = "没有找到欠费记录，请重新检索";
    	    else{
    		// 取出新的收费单号
    		getRequest().setAttribute("bh", payManager.getNewBh(houseId.substring(0, 4)));

    	    }
    	    getRequest().setAttribute("houseId", houseId);
    	}
    	getRequest().setAttribute("advanceMoney", advanceManager.getAdvance(houseId));

    	// 到只查看欠费的页面
    	if ("true".equals(getRequest().getParameter("readonly")))
    	    return "payreadonly";
    	else if ("privilege".equals(payType)) {// 到优惠的页面
    	    return "payprivilege";
    	}else
    	    // 到缴费的页面
    	    return "paycarport";
    }
    
    
    /**
     * 保存缴费记录(实收的或优惠的)
     * 
     * @return
     */
    public String payCarportSave() {

		String houseId = getRequest().getParameter("houseId");
		String[] id = getRequest().getParameterValues("id");
		String[] factMoneys = getRequest().getParameterValues("monthFactMoney");
		String[] advances = getRequest().getParameterValues("advanceMoney");// 每月是否使用预存
		String[] privileges = getRequest().getParameterValues("privilegeMoney");// 每月是否使用优惠
		String[] privilegeReasons = getRequest().getParameterValues("privilegeReason");
	
		String arrearMoney = getRequest().getParameter("arrearMoney");// 尾数优惠类型

		String advanceSrc = advanceManager.getAdvance(houseId);// 客户预存总额
	
		String reason1 = getRequest().getParameter("reason1");// 收款
		String bh = getRequest().getParameter("bh");// 编号
		// 交款日期
		String gatheringDate = getRequest().getParameter("gatheringDate");
	
		HttpSession session = getRequest().getSession();
		String userName = (String) session.getAttribute("userName");// 录入人
		forwardStr = "完成本次缴费。";
		try {
		    payManager.savePay(houseId, "收款", id, factMoneys, advances, privileges, privilegeReasons, arrearMoney,advanceSrc, userName, reason1, bh, gatheringDate);
		    getRequest().setAttribute("recordBh", bh);
		    return "savecarportsuccess";
		} catch (Exception ex) {
		    forwardStr = "缴费失败:原因:" + ex.getMessage();
		    logger.error("实际收费失败", ex);
		    addActionError("" + forwardStr);
		    return ERROR;
		}
    }    
    
    /*
     * 根据小区id取楼栋
     */
    public String getEdifice() {
	String areaId = getRequest().getParameter("areaId");
	viewList = edificeManager.getAllEdifice(Integer.parseInt(areaId));

	return "ajaxedifice";
    }

    /*
     * 根据小区id取楼栋
     */
    public String getHouseInfo() {
	String edificeId = getRequest().getParameter("edificeId");

	viewList = edificeManager.findHouseForEdifice(edificeId);

	return "ajaxhouse";
    }

    /*
     * 根据小区id取收费项目
     */
    public String getChargeBasedata() {
	String areaId = getRequest().getParameter("areaId");
	viewList = chargeBasedataManager.getAllByAreaId(areaId);
	return "ajaxchargebasedata";
    }

    /*
     * 根据小区id取收费项目
     */
    public String getChargeBasedata1() {
	String areaId = getRequest().getParameter("areaId");
	viewList = chargeBasedataManager.getAllByAreaId(areaId);
	return "ajaxcharge";
    }

    public ChargeUserPayRecordEO getChargeUserPayRecord() {
	return chargeUserPayRecord;
    }

    public void setChargeUserPayRecord(ChargeUserPayRecordEO chargeUserPayRecord) {
	this.chargeUserPayRecord = chargeUserPayRecord;
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

    public ChargeUserPayRecordEO getEntity2() {
	return entity2;
    }

    public void setEntity2(ChargeUserPayRecordEO entity2) {
	this.entity2 = entity2;
    }

    public String getPageBar() {
	return pageBar;
    }

    public void setPageBar(String pageBar) {
	this.pageBar = pageBar;
    }

    public List getDetailList() {
	return detailList;
    }

    public void setDetailList(List detailList) {
	this.detailList = detailList;
    }

}