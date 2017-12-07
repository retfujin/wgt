package com.acec.wgt.actions.contract;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.contract.DemandEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.contract.DemandManager;
import com.acec.wgt.service.sys.AdminUserManager;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

public class DemandAction extends BaseAction {
    @Autowired
    private DemandManager demandManager;
    @Autowired
    private AdminUserManager userManager;
    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private AreaManager areaManager;
    
    // 分页
    private Page<DemandEO> page = new Page<DemandEO>(20);// 每页20条记录
    private DemandEO entity;
    SysUserEO user = AuthorityService.getUser();

    public String list() {	
    	String departname = getRequestValue("departname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		page = demandManager.getPage(page,sb.toString());
		page.setUrl("demand!list.action");
		return "list";
    }

    public String add() {
    	viewList = departmentManager.getDepartments("");
    	retList = areaManager.getAreaALL();
    	return "add";
    }

    public String edit() {
    	viewList = departmentManager.getDepartments("");
		entity = demandManager.getById(entity.getId());
		retList = areaManager.getAreaALL();
		return "edit";
    }
    
    public String sel() {
    	entity = demandManager.getById(entity.getId());
    	return "sel";
    }

    public void del() {
		try {
		    entity = demandManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    demandManager.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }
    
    public void save() {    	
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
		    if(entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());	
		    	entity.setRecordtime(sf.format(new Date()));
		    }
		    if(entity.getOfficedate()==null)
		    	entity.setState("待综合部审核");
		    else if(entity.getLeadershipdate()==null)
		    	entity.setState("待领导审核");
		    else 
		    	entity.setState("审核完成");
		    demandManager.save(entity);
		    write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public String listone() {	
    	String departname = getRequestValue("departname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		page = demandManager.getPage(page,sb.toString());
		page.setUrl("demand!listone.action");
		return "listone";
    }
    public String editone() {
		entity = demandManager.getById(entity.getId());
		return "editone";
    }
    
    public String listtwo() {	
    	String departname = getRequestValue("departname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (officeid='").append(user.getId()).append("' or leadershipid = '").append(user.getId()).append("') ");
 		page = demandManager.getPage(page,sb.toString());
		page.setUrl("demand!listtwo.action");
		return "listtwo";
    }
    public void getInfo() {
		try {
		    entity = demandManager.getById(entity.getId());
		    String param = "Y";//Y可以审核   N不可以审核
		    if(entity.getOfficeid()!=null && entity.getOfficeid().equals(user.getId().toString()))
		    	if(null!=entity.getOfficename() && !"".equals(entity.getOfficename()))
		    		param = "N";
			    else
			    	param = "Y";
		    if(entity.getLeadershipid()!=null && entity.getLeadershipid().equals(user.getId().toString()))
		    	if(null!=entity.getLeadershipname() && !"".equals(entity.getLeadershipname()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if("N".equals(param)){
		    	write("{success:false,msg:'此信息已审批'}");
	    		return;
		    }
		    write("{success:true,msg:''}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'获取失败.原因：" + ex.getMessage() + "'}");
		}
    }
    public String edittwo() {
		entity = demandManager.getById(entity.getId());
		return "edittwo";
    }
    
    
    
    public String choose(){
    	viewList = userManager.getUserList();
    	return "choose";
    }
     
    
    
    public Page getPage() {
    	return page;
    }
    public void setPage(Page page) {
    	this.page = page;
    }
    public DemandEO getEntity() {
    	return entity;
    }
    public void setEntity(DemandEO entity) {
    	this.entity = entity;
    }
    
}
