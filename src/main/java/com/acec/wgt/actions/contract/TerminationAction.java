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
import com.acec.wgt.models.contract.TerminationEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.contract.TerminationManager;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

public class TerminationAction extends BaseAction {
    @Autowired
    private TerminationManager terminationManager;
    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private PositionManager positionManager;
    private List viewList1;
    // 分页
    private Page<TerminationEO> page = new Page<TerminationEO>(20);// 每页20条记录
    private TerminationEO entity;
    SysUserEO user = AuthorityService.getUser();

    public String list() {	
    	String departname = getRequestValue("departname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		
		page = terminationManager.getPage(page,sb.toString());
		page.setUrl("demand!list.action");
		return "list";
    }

    public String add() {
    	viewList = departmentManager.getDepartments("");
		retList = areaManager.getAreaALL();
		viewList1 = positionManager.getPositions("");
    	return "add";
    }
    
    public String edit() {
    	viewList = departmentManager.getDepartments("");
		entity = terminationManager.getById(entity.getId());
		retList = areaManager.getAreaALL();
		viewList1 = positionManager.getPositions("");
		return "edit";
    }
    
    public String sel() {
    	entity = terminationManager.getById(entity.getId());
    	return "sel";
    }

    public void del() {
		try {
		    entity = terminationManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    terminationManager.save(entity);
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
		    if(entity.getDepartdate()==null)
		    	entity.setState("待部门审核");
		    else if(entity.getDepartonedate()==null && (entity.getOfficeid()==null || "".equals(entity.getOfficeid())))
		    	entity.setState("待物业部审核");
		    else if(entity.getOfficedate()==null)
		    	entity.setState("待综合部审核");
		    else 
		    	entity.setState("审核完成");
		    terminationManager.save(entity);
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
 		
		page = terminationManager.getPage(page,sb.toString());
		page.setUrl("demand!listone.action");
		return "listone";
    }
    public String editone() {
		entity = terminationManager.getById(entity.getId());
		return "editone";
    }
    public String listtwo() {	
    	String departname = getRequestValue("departname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and ( departid='").append(user.getId()).append("' or departoneid ='").append(user.getId()).append("' or officeid = '").append(user.getId())
 		.append("' or leadershipid = '").append(user.getId()).append("') ");
		page = terminationManager.getPage(page,sb.toString());
		page.setUrl("demand!listtwo.action");
		return "listtwo";
    }
    public void getInfo() {
		try {
		    entity = terminationManager.getById(entity.getId());
		    String param = "Y";//Y可以审核   N不可以审核
		    if(entity.getDepartid()!=null && entity.getDepartid().equals(user.getId().toString()))
		    	if(null!=entity.getDepartname() && !"".equals(entity.getDepartname()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getDepartoneid()!=null && entity.getDepartoneid().equals(user.getId().toString()))
		    	if(null!=entity.getDepartonename() && !"".equals(entity.getDepartonename()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getOfficeid()!=null && entity.getOfficeid().equals(user.getId().toString()))
		    	if(null!=entity.getOfficename() && !"".equals(entity.getOfficename()))
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
		entity = terminationManager.getById(entity.getId());
		return "edittwo";
    }
    
    
    public Page getPage() {
    	return page;
    }
    public void setPage(Page page) {
    	this.page = page;
    }
    public TerminationEO getEntity() {
    	return entity;
    }
    public void setEntity(TerminationEO entity) {
    	this.entity = entity;
    }

	public List getViewList1() {
		return viewList1;
	}

	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}
    
}
