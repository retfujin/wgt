package com.acec.wgt.actions.chg;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.utils.CharTools;
import com.acec.core.utils.GetMonth;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.chg.AuditEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.chg.ReportManager;

/**
 * 
 * 报表
 */
public class ReportAction extends BaseAction {

    private static final long serialVersionUID = -6602979377900139672L;

    @Autowired
    private ReportManager reportManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private EdificeManager edificeManager;
    private String pageBar;
    private List elist;
    private Page page = new Page(150);// 每页5条记录

    private String areaId = "";
    private String year = "";
    private String edificeId = "";
    //时间参数
    private String startDate;
    private String endDate;

    
    /**
     * 年度报表
     * 
     * @return
     */
    public String yearreport() {
		String year = getRequest().getParameter("year");
		viewList = reportManager.getYearCharge(Integer.parseInt(year));
		return "yearreport";
    }

    public String yearedificereport(){
    	String areaId = getRequest().getParameter("areaId");
    	String nowYear = getRequest().getParameter("nowYear");
    	viewList = reportManager.getYearEdificeReport(Integer.parseInt(areaId), Integer.parseInt(nowYear));
    	return "yearedificereport";
    }
    
    public String yearhousereport(){
    	String edificeId = getRequest().getParameter("edificeId");
    	String nowYear = getRequest().getParameter("nowYear");
    	viewList = reportManager.getYearHouseReport(edificeId, Integer.parseInt(nowYear));
    	return "yearhousereport";
    }
    
    /**
     * 月度报表
     * 
     * @return
     */
    public String monthreport() {
		String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");
			viewList = reportManager.getMonthReport(startDate,endDate);
		return "monthreport";
    }
	
    public String monthedificereport(){
    	String areaId = getRequest().getParameter("areaId");
    	String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");
		viewList = reportManager.getMonthEdificeReport(Integer.parseInt(areaId), startDate, endDate);
    	return "monthedificereport";
    }
    
    public String monthhousereport(){
    	String edificeId = getRequest().getParameter("edificeId");
    	String startDate = getRequest().getParameter("startDate");
		String endDate = getRequest().getParameter("endDate");
    	viewList = reportManager.getMonthHouseReport(edificeId, startDate, endDate);
    	return "monthhousereport";
    }
    
    
    //明细
    public String perreport() {
		String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "";
		String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
		String beginDate = getRequest().getParameter("beginDate") != null ? getRequest().getParameter("beginDate") : "";
		String endDate = getRequest().getParameter("endDate") != null ? getRequest().getParameter("endDate") : "";
		retList = areaManager.getAreaALL();
		if (!"".equals(areaId))
		    viewList = reportManager.getPerReport(areaId, houseId, beginDate, endDate);
	
		return "perreport";
    }

    //明细分项显示
    public String getPayDetailById(){
    	String detailId = getRequest().getParameter("detailId");
    	elist = reportManager.getPayDetailById(Integer.parseInt(detailId));
    	return "paydetailbyid";
    }
	    
    /**
     * 物业费欠费统计(按小区)
     * 
     * @return
     */
    public String arrearageArea() {

		String endYearMonth = getRequestValue("endYearMonth","");
		if(endYearMonth.length()<6){
			String DATE_FORMAT = "yyyy-MM";
			endYearMonth= new SimpleDateFormat(DATE_FORMAT).format(new java.util.Date());
		}
			
			
			viewList = reportManager.getArrearageArea(endYearMonth);
			getRequest().setAttribute("endYearMonth", endYearMonth);
		return "arrearageArea";
    }   
    
    /**
     * 物业费欠费统计(按小区)
     * 
     * @return
     */
    public String arrearageEdifice() {

		String endYearMonth = getRequestValue("endYearMonth","");
		Integer areaId = getRequestValue("areaId",0);
		if(endYearMonth.length()<6){
			String DATE_FORMAT = "yyyy-MM";
			endYearMonth= new SimpleDateFormat(DATE_FORMAT).format(new java.util.Date());
		}
			
			
			viewList = reportManager.getArrearageEdifice(areaId,endYearMonth);
			getRequest().setAttribute("endYearMonth", endYearMonth);
		return "arrearageEdifice";
    }   
    /**
     * 物业费欠费统计
     * 
     * @return
     */
    public String arrearageHouse() {
    	
    	String endYearMonth = getRequestValue("endYearMonth","");
		String edificeId = getRequestValue("edificeId","0");

		if(endYearMonth.length()<6){
			String DATE_FORMAT = "yyyy-MM";
			endYearMonth= new SimpleDateFormat(DATE_FORMAT).format(new java.util.Date());
		}
			
			
		viewList = reportManager.getArrearageHouse(edificeId,endYearMonth);
		getRequest().setAttribute("endYearMonth", endYearMonth);
		
		return "arrearageHouse";
    }

    /**
     * 物业费欠费明细
     * 
     * @return
     */
    public String arrearagedetail() {
		String houseId = getRequest().getParameter("houseId");
		String pageNo = getRequest().getParameter("pageNo");
		if (pageNo == null)
		    pageNo = "1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = reportManager.getArrearageDetail(no, 50, houseId);
		pt.setUrl("report!arrearagedetail.action?houseId=" + houseId);
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
	
		return "arrearagedetail";
    }

    /**
     * 常规类收费统计
     * @return
     */
    public String payyearlist() {
		String startDate = getRequest().getParameter("startDate") !=null ? getRequest().getParameter("startDate") : "";
		String endDate = getRequest().getParameter("endDate")!=null ? getRequest().getParameter("endDate") :"";
		if(!startDate.equals("") && !endDate.equals(""))
			viewList = reportManager.getPayYear(startDate,endDate);
		
		return "payyearlist";
    }
    
    /**
     * 常规收费明细
     * 
     * @return
     */
    public String paydetail() {
    	
		String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
		String ownerName = getRequest().getParameter("ownerName") != null ? getRequest().getParameter("ownerName") : "";
		String chargeName = getRequestValue("chargeName","");
		String startDate = getRequestValue("startDate","");
		String endDate = getRequestValue("endDate","");
		
		if(houseId.equals("")&&ownerName.equals("")){
			pageBar = "";
			return "paydetail";
		}
		String pageNo = getRequest().getParameter("pageNo");
		if (pageNo == null)
		    pageNo = "1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = reportManager.getPayDetail(no, 50,houseId, ownerName,chargeName,startDate,endDate);
		pt.setUrl("report!paydetail.action?houseId=" + houseId + "&ownerName=" + ownerName+ "&chargeName=" + chargeName+"&startDate="+startDate+"&endDate="+endDate);
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
	
		return "paydetail";
    }

    /**
     * 物业费月度缴费明细
     * @return
     */
    public String paymonthdetail(){
    	retList = areaManager.getAreaALL();
    	String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "";
    	String month = getRequest().getParameter("month") != null ? getRequest().getParameter("month") : "";
    	if(!"".equals(areaId))
    		viewList = reportManager.paymonthdetail(areaId, month);
    	return "paymonthdetail";
    }
    
    
    
    /**
     * 物业管理处收费台账
     * 
     * @return
     */
    public String chargelist() {
		String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "";
		String edificeId = getRequest().getParameter("edificeId") != null ? getRequest().getParameter("edificeId") : "";
		String year = getRequest().getParameter("year") != null ? getRequest().getParameter("year") : "";
		retList = areaManager.getAreaALL();
		for (AreaEO area : (List<AreaEO>) retList) {
		    if (!"".equals(areaId))
			elist = edificeManager.getAllEdifice(Integer.parseInt(areaId));
		    else
			elist = edificeManager.getAllEdifice(area.getId());
		    break;
		}

		if (!"".equals(areaId) && !"".equals(year)) {
		    viewList = reportManager.chargeList(areaId, edificeId, year);
	
		    int beginIndex = (page.getPageNo() - 1) * page.getPageSize();
		    int endIndex = page.getPageNo() * page.getPageSize();
		    page.setTotalCount(viewList.size());
		    if (viewList.size() < endIndex)
			endIndex = viewList.size();
		    logger.debug("从" + beginIndex + "到" + endIndex);
		    viewList = viewList.subList(beginIndex, endIndex);
		}
		return "chargelist";
    }

    /**
     * 前台收费统计
     * 
     * @return
     */
    public String collectreport() {
    	String username = (String)Struts2Utils.getSession().getAttribute("userName");
    	String areaId = getRequest().getParameter("areaId") !=null ? getRequest().getParameter("areaId") : "";
		String beginTime = getRequest().getParameter("beginDate") != null ? getRequest().getParameter("beginDate") : "";
		String endTime = getRequest().getParameter("endDate") != null ? getRequest().getParameter("endDate") : "";
		viewList = reportManager.getCollectReport(areaId,beginTime, endTime,username);
		retList = areaManager.getAreaALL();
		return "collectreport";
    }
    
    /**
     * 前台收费统计(按收费员显示收费项目)
     * 
     * @return
     */
    public String collectreportforuser() {
    	String username = getRequestValue("userName","");
		String beginTime = getRequest().getParameter("beginDate") != null ? getRequest().getParameter("beginDate") : "";
		String endTime = getRequest().getParameter("endDate") != null ? getRequest().getParameter("endDate") : "";
		viewList = reportManager.getCollectReportForUser(beginTime, endTime,username);
		retList = areaManager.getAreaALL();
		return "collectreportforuser";
    }
    
    public String chargehouseid() {
		String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
		String subHouseId = getRequest().getParameter("subHouseId") != null ? getRequest().getParameter("subHouseId") : "";
	
		viewList = reportManager.chargeHouseId(houseId, subHouseId);
	
		int beginIndex = (page.getPageNo() - 1) * page.getPageSize();
		int endIndex = page.getPageNo() * page.getPageSize();
		page.setTotalCount(viewList.size());
		if (viewList.size() < endIndex)
		    endIndex = viewList.size();
		logger.debug("从" + beginIndex + "到" + endIndex);
		viewList = viewList.subList(beginIndex, endIndex);
		return "chargehouseid";
    }

    public String getEdifice() {
		Integer areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("") ? Integer
			.parseInt(getRequest().getParameter("areaId")) : 0;
		viewList = edificeManager.getAllEdifice(areaId);
	
		return "ajaxedifice";
    }

    
    /**
     * 报账首页
     * @return
     */
    public String audit(){
    	String P_forward = "";
    	
    	String username = (String)Struts2Utils.getSession().getAttribute("userName");
    	String areaId = getRequest().getParameter("areaId") !=null ? getRequest().getParameter("areaId") : "";
		String beginTime = getRequest().getParameter("beginTime") != null ? getRequest().getParameter("beginTime") : "";
		String endTime = getRequest().getParameter("endTime") != null ? getRequest().getParameter("endTime") : "";
		
		String _username= getRequest().getParameter("_username") !=null ? getRequest().getParameter("_username") :"";

		
    	//管理员，财务专用
    	if(username.equals("ffadmin")){
        	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
        	if(!"".equals(_username))
        		page = reportManager.getSysAuditList(page, _username, areaIds, beginTime, endTime);
        	else
        		page = reportManager.getSysAuditList(page, username, areaIds, beginTime, endTime);
    		
        	retList = reportManager.getSystemUser();
    		P_forward = "audit";
    	}else{
    	//普通人员	
    		//首先判断该用户是否已报账
    		if("".equals(areaId))
    			areaId = (String)Struts2Utils.getSession().getAttribute("areaIds");
	    	try{
    			List auditList=reportManager.getAuditByUser(username, areaId);
	    		if(!auditList.isEmpty()){
	    			AuditEO audit = (AuditEO)auditList.get(0);
	    			startDate=GetMonth.getNextDay(audit.getEndDate());
	    		}else
	    			startDate="";
	    	}catch (Exception e) {
	    		 e.printStackTrace();
	    		 forwardStr = "" + e.getMessage();
	    		 return "result";
			}
    		viewList = reportManager.getCollectReport(areaId,beginTime, endTime,username);
    		retList = areaManager.getAreaALL();
    		P_forward = "ordinaryaudit";
    	}
    	return P_forward;
    }
    
    /**
     * 保存报账内容
     * @return
     */
    public String saveaudit(){
    	String areaId=getRequest().getParameter("areaId") ;
    	String beginDate=getRequest().getParameter("beginDate");
    	String endDate=getRequest().getParameter("endDate");
    	String sumMoney=getRequest().getParameter("sumMoney");
    	String userName=getRequest().getParameter("userName");
    	
    	AuditEO audit = new AuditEO();
    	audit.setAreaId(Integer.parseInt(areaId));
    	audit.setBeginDate(Date.valueOf(beginDate));
    	audit.setEndDate(Date.valueOf(endDate));
    	audit.setSumMoney(sumMoney);
    	audit.setUserName(userName);
    	audit.setRecordTime(new java.util.Date());
    	audit.setAuditStatus("正在审核");
    	
    	reportManager.saveAudit(audit);
    	forwardUrl="report!roleaudit.action";
    	return "forward";
    }
    
    
    public String roleaudit(){
    	String userName = (String)Struts2Utils.getSession().getAttribute("userName");
    	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
    	page = reportManager.auditList(page, userName, areaIds);
    	return "roleaudit";
    }
    
    public String audittype(){
    	String userName = (String)Struts2Utils.getSession().getAttribute("userName");
    	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
    	page = reportManager.auditList(page, userName, areaIds);
    	retList = reportManager.getSystemUser();
    	return "audit";
    }
    
    
    public String updateaudit(){
    	String id = getRequest().getParameter("id");
    	reportManager.updateaudit(Integer.parseInt(id));
    	forwardUrl="report!audittype.action";
    	return "forward";
    }
   
	public String auditdetail(){
    	String userName = getRequest().getParameter("userName") !=null ? getRequest().getParameter("userName") : "";
    	try{
    		CharTools charTools = new CharTools();
			try {
				userName = new String(userName.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			userName = charTools.Utf8URLdecode(userName);
    	}catch (Exception e) {}    	
    	String beginDate = getRequest().getParameter("beginDate") != null ? getRequest().getParameter("beginDate") : "";
		String endDate = getRequest().getParameter("endDate") != null ? getRequest().getParameter("endDate") : "";
		page = reportManager.auditdetail(page, beginDate, endDate, userName);
		return "auditdetail";
	}
    
    
	
    public String getPageBar() {
    	return pageBar;
    }
    public void setPageBar(String pageBar) {
    	this.pageBar = pageBar;
    }
    public List getElist() {
    	return elist;
    }
    public void setElist(List elist) {
    	this.elist = elist;
    }
    public Page getPage() {
    	return page;
    }
    public void setPage(Page page) {
    	this.page = page;
    }
    public String getAreaId() {
    	return areaId;
    }
    public void setAreaId(String areaId) {
    	this.areaId = areaId;
    }
    public String getYear() {
    	return year;
    }
    public void setYear(String year) {
    	this.year = year;
    }
    public String getEdificeId() {
    	return edificeId;
    }
    public void setEdificeId(String edificeId) {
    	this.edificeId = edificeId;
    }
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}