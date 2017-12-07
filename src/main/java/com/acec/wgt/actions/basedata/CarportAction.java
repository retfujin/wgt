package com.acec.wgt.actions.basedata;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.utils.CharTools;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.BaseFileUploadAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.CarportEO;
import com.acec.wgt.models.baseData.CarportLeaseEO;
import com.acec.wgt.models.baseData.EdificeEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CarportManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.chargemanger.BasedataManager;
import com.acec.wgt.service.chg.PayManager;
import com.acec.wgt.utils.IsChineseOrEnglish;

/**
 * 
 * 收费管理--车位管理
 * 
 * @author Administrator
 * 
 */
public class CarportAction extends BaseFileUploadAction {

    @Autowired
    private CarportManager carportManager;
    @Autowired
    private EdificeManager edificeManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private CellManager cellManager;
    @Autowired
    private BasedataManager basedataManager;
    @Autowired
    private PayManager payManager;

    private CarportLeaseEO carportLease;
    private CarportEO carport;

    private List retList;
    private List retList_1;
    private Page page = new Page(15);// 每页5条记录
    float a;
    private String pageBar;

    private String areaId;
    private String houseId;
    private String state;
    private String bigType;
    private String carCode;
    private String atherInDate;
    private String enddays; 

    /*
     * 租售车位列表
     */
    public String list() {
	String areaId = getRequestValue("areaId","0");
	String state = getRequest().getParameter("state") != null ? getRequest().getParameter("state") : "";
	String bigType = getRequest().getParameter("bigType") != null ? getRequest().getParameter("bigType") : "";
	String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
	String carCode = getRequest().getParameter("carCode") != null ? getRequest().getParameter("carCode") : "";
	String enddays = getRequest().getParameter("enddays") != null ? getRequest().getParameter("enddays") : "";//到期天数
	String hidd = getRequest().getParameter("hidd");
	String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
	if (pageNo == null)
	    pageNo = "1";
	int no = Integer.parseInt(pageNo);
	
	
	state = IsChineseOrEnglish.convertChinese(state);
	bigType = IsChineseOrEnglish.convertChinese(bigType);
	
	
	
	
	retList = areaManager.getAreaALL();
	retList_1 = carportManager.getCarState();
	PaginatorTag pt = carportManager.getAllCarPortState(no, 15, state, Integer.parseInt(areaId), bigType, houseId,
		carCode,enddays);

	
	String page="?areaId="+areaId+"&state="+state+"&bigType="+bigType+"&houseId"+houseId+"&carCode="+carCode+"&enddays="+enddays;
	pt.setUrl("carport!list.action" + page);
	pt.setShowTotal(true);
	pt.setShowAllPages(true);
	pt.setStrUnit("条");
	viewList = pt.getData();
	pageBar = pt.showPage();

	return "list";
    }

    /**
     * 当前车位的租售状态
     * 
     * @return
     */
    public String downNowCarport() {
	String areaId = getRequest().getParameter("areaId");
	logger.debug("下载导入车位租售模板，小区id" + areaId);
	try {
	    carportManager.writeNowCarportExc(areaId);
	    return null;
	} catch (Exception ex) {
	    ex.printStackTrace();
	    forwardStr = "下载excel失败。原因：" + ex.getMessage();
	    return "result";
	}
    }

    /*
     * 新增车位分配
     */
    public String add() {
	String carportId = getRequest().getParameter("carportId");
	String type = getRequest().getParameter("type");
	getRequest().setAttribute("type", type);
	carport = carportManager.getCarportById(Integer.parseInt(carportId));
	// carportLease =
	// carportManager.getCarportLease(carport.getCarportLeaseId());

	viewList = areaManager.getAreaALL();

	// if(type.equals("1"))//分配机动车
	return "add";
	// else//(type.equals("1"))//分配非机动车
	// return "add2";
    }

    /**
     * 临时车位分配
     * 
     * @return
     */
    public String temporary() {
	viewList = areaManager.getAreaALL();

	return "temporary";
    }

    /*
     * c重新编辑
     */
    public String edit() {
	String carportId = getRequest().getParameter("carportId");
	carport = carportManager.getCarportById(Integer.parseInt(carportId));

	carportLease = carportManager.getCarportLease(carport.getCarportLeaseId());
	viewList = areaManager.getAreaALL();

	try {
	    // 上次的截止日期加1天，作为当前车位租赁的开始日期
	    Calendar c = Calendar.getInstance();
	    c.setTime(carportLease.getOutDate());
	    c.add(Calendar.DATE, 1);
	    atherInDate = Utils.dateToString(c.getTime());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return "edit";
    }

    /*
     * 保存车位分配
     */
    public void save() {
    	int areaId = carportLease.getAreaId();

    	String houseId = carportLease.getHouseId();

    	String mianji = getRequest().getParameter("mianji");
    	String chongdian = getRequest().getParameter("chongdian");
    	String chargeId = getRequest().getParameter("chargeId") != null ? getRequest().getParameter("chargeId") : "";
    	String factMoney = getRequest().getParameter("factMoney");
    	String bh = getRequest().getParameter("bh");// 缴费单号
    	HttpSession session = getRequest().getSession();
    	String userName = (String) session.getAttribute("userName");// 录入人

    	logger.debug("是否充电：" + chongdian);
    	try {
    	    // 当mianji 为0 时STRUTS转换类型出错，只能后台手动set值
    	    carportLease.setMianji(Float.valueOf(mianji));
    	    if (!"".equals(chargeId))
    	    	carportLease.setChargeId(Integer.parseInt(chargeId));
      	    
    	    carportManager.saveCarportLease1(carportLease, houseId, areaId, chongdian, factMoney, bh, userName);
    	    getRequest().setAttribute("recordBh", bh);
    	    write("{success:true,msg:'保存成功'}");
    	} catch (Exception e) {
    	    logger.error(e.getMessage(), e);
    	    write("{success:false,msg:'保存失败。" + e.getMessage() + "'}");
    	}		  
		return;
    }
    /**
     * 续交保存
     * @return
     */
    public void saveather() {

    	String factMoney = getRequest().getParameter("factMoney");
		String bh = getRequest().getParameter("bh");// 缴费单号
	
		String inDate = getRequest().getParameter("inDate");
		String outDate = getRequest().getParameter("outDate");
		String recordMonth = getRequest().getParameter("recordMonth");
	
		int carportLeaseId = carportLease.getId();
		carportLease = carportManager.getCarportLease(carportLeaseId);
		try {
		    carportLease.setRecordMonth(Utils.strToDate(recordMonth));
		    write("{success:true,msg:'保存成功'}");
		} catch (Exception e) {
		    e.printStackTrace();
		    addActionError("缴费日期错误！");
		    write("{success:false,msg:'保存失败。" + e.getMessage() + "'}");
		}
		carportManager.saveCarportLeaseAther1(carportLease, inDate, outDate, factMoney, bh);
		getRequest().setAttribute("recordBh", bh);    	 
		return;
    }

    /*
     * 保存车位分配
     */
    public String saveTeamporary() {

	int areaId = carportLease.getAreaId();

	String houseId = carportLease.getHouseId();

	String chargeId = getRequest().getParameter("chargeId") != null ? getRequest().getParameter("chargeId") : "";
	String factMoney = getRequest().getParameter("factMoney");
	String bh = getRequest().getParameter("bh");// 缴费单号
	HttpSession session = getRequest().getSession();
	String userName = (String) session.getAttribute("userName");// 录入人

	try {
	    // 当mianji 为0 时STRUTS转换类型出错，只能后台手动set值
	    carportLease.setMianji(0);
	    if (!"".equals(chargeId))
		carportLease.setChargeId(Integer.parseInt(chargeId));
	    if (null == houseId) {
		addActionError("没有选择房间");
		return ERROR;
	    }

	    if (null == carportLease.getInDate()) {
		addActionError("没有开始、结束日期");
		return ERROR;
	    }

	    carportManager.saveCarportLeaseTemp(carportLease, houseId, areaId, factMoney, bh, userName);
	    getRequest().setAttribute("recordBh", bh);
	    return "savesuccess";
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    e.printStackTrace();
	    addActionError(e.getMessage());
	    return ERROR;
	}
    }

    /*
     * 停止租赁
     */
    public void stopLease() {
	
		String carportId = getRequest().getParameter("carportId");
		try {
		    carportManager.updateCarportLease(Integer.parseInt(carportId));
		    write("{success:true,msg:'操作成功'}");
		} catch (Exception ex) {
		    logger.error(ex.getMessage(), ex);
		    write("{success:false,msg:'操作失败。"+ex.getMessage()+"'}");
	
		}
    }

    /**
     * 续交租赁类停车费 carportId
     * 
     * @return
     */
    public String payather() {
	String carportId = getRequest().getParameter("carportId"); // 车位编号的ID
	String month = getRequest().getParameter("month");
	CarportLeaseEO carportLease = carportManager.getCarportLeaseByCarportId(Integer.parseInt(carportId));
	String endTime = carportLease.getOutDate().toString();
	try {
	    String[] strPar = Utils.getDateAther(Utils.strToDate(endTime), Integer.parseInt(month));
	    carportManager.saveCarportLeaseAther(carportLease, Utils.strToDate(strPar[0]), Utils.strToDate(strPar[1]),
		    carportLease.getBeginDate(), carportLease.getEndDate());
	} catch (Exception e) {
	    throw new RuntimeException("日期类型转换错误！");
	}
	// 得到交费记录的ID
	// String
	// carportLeaseId=Struts2Utils.getSession().getAttribute("carportLeaseId").toString();
	forwardStr = "操作成功！";

	// forwardUrl = "carport!payMoney.action?id="+carportLeaseId;
	forwardUrl = "/chg/pay!pay.action?houseId=" + carportLease.getHouseId() + "&chargeId="
		+ carportLease.getChargeId();
	return "forward";
    }

    /*
     * 缴费
     */
    public String payMoney() {
	String id = getRequest().getParameter("id");
	carportLease = carportManager.getCarportLease(Integer.parseInt(id));
	String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
	if (pageNo == null)
	    pageNo = "1";
	int no = Integer.parseInt(pageNo);
	// 录入租赁时间直接获取应收 的方式
	// if(carportLease.getBigType().equals("机动车") &&
	// carportLease.getState().equals("出租"))
	// {
	// return "paymoney1";
	// }
	// else
	// {
	page = carportManager.getHouseDetail(page, carportLease.getCarport().getId());
	// pt.setUrl("carport!payMoney.action");
	// pt.setShowTotal(true);
	// pt.setShowAllPages(true);
	// pt.setStrUnit("条");
	// viewList = pt.getData();
	// pageBar=pt.showPage();
	return "paymoney2";
	// }
    }

    public String savePayMoney() {

	String[] factMoney = getRequest().getParameterValues("factMoney");
	String[] detailId = getRequest().getParameterValues("detailId");
	try {
	    carportManager.savePayMoney(factMoney, detailId);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    e.printStackTrace();
	    addActionError("保存优惠时发生错误。" + e.getMessage());
	    return ERROR;
	}
	return SUCCESS;
    }

    /*
     * 机动车交费 地面（地面管理费）、地下（租赁、管理费）
     */
    public String savePayMoney1() {
	forwardUrl = "carport!payList.action";
	try {
	    String oughtMoney1 = getRequest().getParameter("oughtMoney1");
	    String factMoney1 = getRequest().getParameter("factMoney1");
	    String inDate = getRequest().getParameter("inDate");
	    String outDate = getRequest().getParameter("outDate");

	    if (null == inDate || null == outDate) {
		addActionError("没有开始、结束日期");
		return ERROR;
	    }
	    if (null == factMoney1 || "".equals(factMoney1)) {
		addActionError("没有录入实收金额");
		return ERROR;
	    }

	    // 地下车位 租赁费和管理费
	    if (carportLease.getLocal().equals("地下")) {
		String oughtMoney2 = getRequest().getParameter("oughtMoney2");
		String factMoney2 = getRequest().getParameter("factMoney2");

		// if(null == factMoney2 || "".equals(factMoney2))
		// {
		// addActionError("没有录入实收金额");
		// return ERROR;
		// }
		// carportManager.savePayMoney1(carportLease,oughtMoney1,factMoney1,oughtMoney2,factMoney2,inDate,outDate);
		carportManager.savePayMoney3(carportLease, oughtMoney1, factMoney1, inDate, outDate);
	    } else// 地面车位 只有管理费
	    {
		carportManager.savePayMoney2(carportLease, oughtMoney1, factMoney1, inDate, outDate);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    e.printStackTrace();
	    addActionError("保存优惠时发生错误。" + e.getMessage());
	    return ERROR;
	}
	return SUCCESS;

    }

    // 下载excel文件
    public String downExcel() {
	String areaId = getRequest().getParameter("areaId");
	logger.debug("下载导入车位租售模板，小区id" + areaId);
	try {
	    carportManager.writeCaredExc(areaId);
	    return null;
	} catch (Exception ex) {
	    ex.printStackTrace();
	    forwardStr = "下载excel失败。原因：" + ex.getMessage();
	    return "result";
	}
    }

    public String uploadExcel() {
	if (theFile != null) {
	    logger.debug(contenttype);
	    if (!contenttype.equals("application/vnd.ms-excel") && !contenttype.equals("application/octet-stream"))
		forwardStr = "上传的文件类型不正确。类型为：" + contenttype;

	    try {
		carportManager.saveCaredForExc(theFile);
		forwardStr = "上传excel成功。";
	    } catch (Exception ex) {
		forwardStr = "导入excel失败。原因：" + ex.getMessage();
		logger.error("导入车位租售excel失败", ex);
	    }
	} else {
	    forwardStr = "没有获取到文件";
	    logger.error("导入车位租售excel失败,没有获取到文件");
	}
	return "result";
    }

    /**
     * 根据小区id取车位费列表
     * 
     * @return
     */
    public String getCarBasedate() {
	String areaId = getRequest().getParameter("areaId");
	retList = basedataManager.getCarBasedata(areaId);
	return "getcharge";
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

    @SuppressWarnings("unchecked")
    public String getFee() {
	String inDate = getRequest().getParameter("inDate");
	String outDate = getRequest().getParameter("outDate");
	String chargeId = getRequest().getParameter("chargeId");
	try {
	    int cid = Integer.parseInt(chargeId);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date inD = (Date) sdf.parse(inDate);
	    Date outD = (Date) sdf.parse(outDate);

	    int day = (int) ((outD.getTime() - inD.getTime()) / (24 * 60 * 60 * 1000));
	    int month = day / 30;
	    int daylease = day % 30;

	    if (month == 0)
		month = 1;
	    else {
		if (daylease > (month * 2))
		    month += 1;
	    }

	    ChargeBasedataEO cbe = basedataManager.getChargeBasedataById(cid);

	    float money = cbe.getPriceValue() * month;

	    Map map = new HashMap();

	    map.put("factMoney", money);

	    Struts2Utils.renderJson(map);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	// Object[] houseProperty = cellManager.getHouseAddress(houseId);
	//
	// Map map = new HashMap();
	// if (houseProperty == null)
	// map.put("houseAddress", "没有找到业主");
	// else {
	// map.put("houseAddress", houseProperty[0]);
	// map.put("buildnum", houseProperty[1]);
	// map.put("ownerName", houseProperty[2]);
	// map.put("mobTel", houseProperty[3]);
	// }
	// Struts2Utils.renderJson(map);
	// Struts2Utils.renderText(houseAddress);
	return null;
    }

    /*
     * 根据小区id取收费项目
     */
    public String getChargeBasedata() {
	String areaId = getRequest().getParameter("areaId");
	viewList = basedataManager.getAllByAreaId(areaId);
	return "ajaxchargebasedata";
    }

    /*
     * 根据小区id取车位收费项目
     */
    public String getCarChargeByAreaId1() {
	String areaId = getRequest().getParameter("areaId");
	String type = getRequest().getParameter("type");
	if (areaId != null && !areaId.equals(""))
	    retList = basedataManager.getCarCharge1(areaId, type);
	else
	    retList = null;
	return "ajaxcarchargelist1";
    }

    public String getCarChargeByAreaId2() {
	String areaId = getRequest().getParameter("areaId");
	if (areaId != null && !areaId.equals(""))
	    retList = basedataManager.getCarCharge2(areaId);
	else
	    retList = null;
	return "ajaxcarchargelist2";
    }

    // 计算出租车位的管理费用
    @SuppressWarnings("deprecation")
    public String getOughtMoney() {
	String chargeId = getRequest().getParameter("chargeId");
	String beginTime = getRequest().getParameter("beginTime");
	String endTime = getRequest().getParameter("endTime");
	// type=1 车位管理费 2地下车位租赁费
	String type = getRequest().getParameter("type");

	Date date2 = null;
	Date date1 = null;
	try {
	    date2 = com.acec.core.utils.Utils.strToDate(beginTime);
	    date1 = com.acec.core.utils.Utils.strToDate(endTime);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	ChargeBasedataEO ch = basedataManager.getChargeBasedataById(Integer.parseInt(chargeId));
	int i = 0;
	i = 12 * (date1.getYear() - date2.getYear()) + date1.getMonth() - date2.getMonth();
	a = i * ch.getPriceValue();

	if (a == 0) {
	    addActionError("出租时间段最少大于一个月");
	    return ERROR;
	}
	if (type.equals("1"))
	    return "oughtmoney1";
	else
	    return "oughtmoney2";
    }

    /*
     * 机动车 非机动车交费列表
     */
    public String payList() {
	String areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? getRequest()
		.getParameter("areaId") : "0";
	String carCode = getRequest().getParameter("carCode") != null ? getRequest().getParameter("carCode") : "";
	String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
	String state = getRequest().getParameter("state") != null ? getRequest().getParameter("state") : "";
	String bigType = getRequest().getParameter("bigType") != null ? getRequest().getParameter("bigType") : "";
	String local = getRequest().getParameter("local") != null ? getRequest().getParameter("local") : "";
	String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
	String page = "";
	if (null != areaId && !"".equals(areaId)) {
	    page = "?areaId=" + areaId;

	}
	if (pageNo == null)
	    pageNo = "1";
	
	state = IsChineseOrEnglish.convertChinese(state);
	bigType = IsChineseOrEnglish.convertChinese(bigType);
	
	
	int no = Integer.parseInt(pageNo);
	retList = areaManager.getAreaALL();
	PaginatorTag pt = carportManager.getCarPortLease(no, 150, Utils.getCarportState(state),
		Integer.parseInt(areaId), Utils.getCarportBigType(bigType), carCode, houseId, local);
	pt.setUrl("carport!payList.action" + page);
	pt.setShowTotal(true);
	pt.setShowAllPages(true);
	pt.setStrUnit("条");
	viewList = pt.getData();
	pageBar = pt.showPage();
	return "paylist";
    }

    // 车位历史缴费
    public String getHistoryCharge() {
	String areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? getRequest()
		.getParameter("areaId") : "0";
	String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
	String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
	String page = "";
	if (null != areaId && !"".equals(areaId)) {
	    page = "?areaId=" + areaId;

	}
	if (pageNo == null)
	    pageNo = "1";
	int no = Integer.parseInt(pageNo);
	retList = areaManager.getAreaALL();
	PaginatorTag pt = carportManager.getHistoryCharge(no, 15, Integer.parseInt(areaId), houseId);
	pt.setUrl("carport!getHistoryCharge.action" + page);
	pt.setShowTotal(true);
	pt.setShowAllPages(true);
	pt.setStrUnit("条");
	viewList = pt.getData();
	pageBar = pt.showPage();
	return "historycharge";
    }

    /**
     *  车位租售历史记录
     * @return
     */
    public String historylist() {
    	viewList = areaManager.getAreaALL();
    	String areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? getRequest().getParameter("areaId") : "";
    	String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
    	String carCode = getRequest().getParameter("carCode") != null ? getRequest().getParameter("carCode") : "";
    	if(!"".equals(houseId) || !"".equals(carCode))
    		retList = carportManager.historyList(areaId,houseId,carCode);
    	return "historylist";
    }
    
    public void delhistory(){
    	String id = getRequestValue("id", "");
    	try{
    		carportManager.delHistoryCarport(Integer.parseInt(id));
			write("{success:true,msg:'删除成功'}");
		}catch(Exception ex){
			logger.error("保存失败", ex);
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
		}
		return ;
    }
    
    
    
    
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}
	public CarportLeaseEO getCarportLease() {
		return carportLease;
	}
	public void setCarportLease(CarportLeaseEO carportLease) {
		this.carportLease = carportLease;
	}
	public CarportEO getCarport() {
		return carport;
	}
	public void setCarport(CarportEO carport) {
		this.carport = carport;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public float getA() {
		return a;
	}
	public void setA(float a) {
		this.a = a;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBigType() {
		return bigType;
	}
	public void setBigType(String bigType) {
		this.bigType = bigType;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public List getRetList_1() {
		return retList_1;
	}
	public void setRetList_1(List retList_1) {
		this.retList_1 = retList_1;
	}
	public String getAtherInDate() {
		return atherInDate;
	}
	public void setAtherInDate(String atherInDate) {
		this.atherInDate = atherInDate;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getEnddays() {
		return enddays;
	}
	public void setEnddays(String enddays) {
		this.enddays = enddays;
	}
}