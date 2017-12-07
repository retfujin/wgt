package com.acec.wgt.actions.chargemanager.allmeter;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.commons.util.Utils;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseFileUploadAction;
import com.acec.wgt.models.chargemanager.AllmeterEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.AllmeterManager;
import com.acec.wgt.service.chargemanger.BasedataManager;
import com.acec.wgt.service.sms.MessageManager;
import com.opensymphony.xwork2.ActionContext;




/**
 * 
 * 收费管理--总表设置
 * @author Administrator
 *
 */
public class AllmeterAction extends BaseFileUploadAction{	

	private AllmeterEO allmeter;

	@Autowired
	private AllmeterManager manager;
	@Autowired
	private AreaManager areaManager;
		
	@Autowired
	private BasedataManager basedataManager;
	@Autowired
	private MessageManager messageManager;
	
	private List retList=new ArrayList();
	private String areaName;
	
	private String pageBar="";
	
	private Page<AllmeterEO> page = new Page<AllmeterEO>(50);//每页150条记录
	
	
	/**
	 * 新增总表
	 * @return
	 */
	public String add()
	{
		retList = areaManager.getAreaALL();
		viewList = basedataManager.getChargeIdAll("'公摊类'");
		return "add";
	}
	
	/**
	 * 编缉总表
	 * @return
	 */
	public String edit()
	{
		allmeter=manager.getAllmeterById(allmeter.getId());
		retList = basedataManager.getChargeIdAll(String.valueOf(allmeter.getAreaId()), "'公摊类'");
		return "edit";
	}
	/**
	 * 查询首页
	 * @return
	 */
	public String listquery()
	{
		retList=areaManager.getAreaALL();
		return "listquery";
	}
	/**
	 * 所有总表列表
	 * @return
	 */
	public String list()
	{
		String areaId=getRequest().getParameter("areaId");
		StringBuilder sb=new StringBuilder();
		if(areaId!=null && !areaId.equals(""))
			sb.append(" and areaId=").append(areaId);
		sb.append(" and state='使用' ");
		page = manager.getListByPage(page,sb.toString());
		//得到查询参数的字符串
		getRequest().setAttribute("parmString",this.getParamString(""));
		return "list";
	}
	
	/**
	 * 保存总表设置
	 * @return
	 */
	public String saveAllmeter()
	{
		try{
			if(-1 == allmeter.getAreaId()){
					addActionError("没有选择小区！");
					return ERROR;
			}
			if("公摊".equals(allmeter.getIsAll())){
				if(StringUtils.isEmpty(allmeter.getAssignArea())&&allmeter.getAssignArea().length()<2){
					
					addActionError("没有选择公摊范围！");
					return ERROR;
				}
			}
			manager.saveOrUpdateAllmeter(allmeter);	
			forwardStr = "保存成功";
		}catch (Exception ex){
			logger.error(ex.getMessage(), ex);
			forwardStr = "保存失败。原因:"+ex.getMessage();;
		}
		return "result";
	}
	
	/*
	 * 删除总表
	 */
	public String delete()
	{
		try {
			manager.deleteAllmeterById(allmeter.getId());
			forwardStr = "删除成功";
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			forwardStr = "删除失败。原因："+ex.getMessage();
		}
		return "result";
	}
	
	
	/*
	 * 取当前小区的所有正在使用的总表
	 */
	public String getUerMenter()
	{
		String areaId = getRequest().getParameter("areaId");
		
		viewList = manager.getUerMenter(Integer.parseInt(areaId));
		return "ajaxchargebase";
	}
	
	/*
	 * 总表报表------------总表更换历史报表
	 */
	public String oldmeterlistquery()
	{
		viewList=areaManager.getAreaALL();
		return "oldmeterlistquery";
	}
	/*
	 * 总表报表------------总表更换历史报表
	 */
	public String oldmeterlist()
	{
		Integer areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("")? Integer.parseInt(getRequest().getParameter("areaId")) : 0;
		String meterType = getRequest().getParameter("meterType") != null ? getRequest().getParameter("meterType") : "";
		page = manager.getOldMeter(page,areaId,meterType);
		getRequest().setAttribute("paramString", getParamString(""));
		return "oldmeterlist";
	}
	
	
	public String ajaxSelectChargeList(){
		String areaId = getRequest().getParameter("areaId");
		viewList = basedataManager.getChargeIdAll(areaId, "'公摊类'");
		return "ajaxselectchargeList";
	}
	
	
	
	//选择公摊范围模式对话框
	public String choiceFrame(){
		return "choiceframe";
	}
	
	/**
	 * 树形菜单
	 * @return
	 */
	public String trees(){
		String url=getRequest().getParameter("url");
		if(url.indexOf("?")>0){
			Enumeration e=getRequest().getParameterNames();
			while(e.hasMoreElements()){
				String name = (String)e.nextElement();
				if(!name.equals("url")){
					url=url+"&"+name+"="+getRequest().getParameter(name);
				}
			}
			url=url+"&";
		}else
			url=url+"?";
		messageManager.setSession(ActionContext.getContext().getSession());
		String urla=url+"aid=";
		List ls1 = messageManager.findlist1(urla);
		String urlb=url+"bid=";
		List ls2 = messageManager.findlist2(urlb);
		ls1.addAll(ls2);
		try{
			viewList = Utils.getSortList(ls1);
		}catch(Exception ex){ex.printStackTrace();}
		return "trees";
	}
	
	public String findHouseAll(){
		String areaId = (String)getRequest().getParameter("aid");
		String edificeId = (String)getRequest().getParameter("bid");
		messageManager.setSession(ActionContext.getContext().getSession());
		String ret="";
		if(areaId!=null&&areaId!="")// user choose area
		{
			viewList = messageManager.findlist3(areaId);
			ret= "chooesArea";
		}
		if(edificeId!=null&&!edificeId.equals("")) //user choose edifice
		{
			viewList = messageManager.findlist5(edificeId);
			ret= "chooesEdifice";
		}
		return "membergroup";
	}
	public String getPageBar() {
		return pageBar;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}

	public AllmeterEO getAllmeter() {
		return allmeter;
	}
	public void setAllmeter(AllmeterEO allmeter) {
		this.allmeter = allmeter;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Page<AllmeterEO> getPage() {
		return page;
	}
	public void setPage(Page<AllmeterEO> page) {
		this.page = page;
	}
	public List getRetList() {
		return retList;
	}
}
