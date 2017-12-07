package com.acec.wgt.actions.administration;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.administration.ApplyEO;
import com.acec.wgt.models.administration.EmployedEO;
import com.acec.wgt.service.administration.ApplyManager;
import com.acec.wgt.service.administration.EmployedManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.models.sys.SysUserEO;

public class EmployedAction extends BaseAction {
    @Autowired
    private EmployedManager employedManger;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private PositionManager positionManager;
    @Autowired
    private ApplyManager applyManager;
    
    // 分页
    private Page<EmployedEO> page = new Page<EmployedEO>(20);// 每页20条记录

    private EmployedEO entity;
    
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
    	String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		
		page = employedManger.getPage(page, sb.toString());
		page.setUrl("employed!list.action");	
		return "list";
    }
    
    public String add() {
    	viewList = areaManager.getAreaALL();
    	retList = positionManager.getPositions("");
    	return "add";
    }

    public String edit() {
    	viewList = areaManager.getAreaALL();
		entity = employedManger.getById(entity.getId());
		retList = positionManager.getPositions("");
		return "edit";
    }
    
    public String sel() {
		entity = employedManger.getById(entity.getId());		
		return "sel";
    }

    public void del() {
		try {
		    entity = employedManger.getById(entity.getId());
		    entity.setIsdel("Y");
		    employedManger.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public void save() {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
		    if (entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());
		    	entity.setRecordtime(sf.format(new Date()));
		    }
		    
		    if(entity.getApplydate()==null)
		    	entity.setState("待入职审核");
		    else if(entity.getDepartdate()==null)
		    	entity.setState("待用人部门审核");
		    else if(!"1030".equals(String.valueOf(entity.getAreaId())) && entity.getDepartonedate()==null && (entity.getOfficeid()==null || "".equals(entity.getOfficeid())))
		     		entity.setState("待物业部审核"); 
		    else if(entity.getOfficedate()==null)
		    	entity.setState("待综合部审核");
		    else if(entity.getVicedate()==null)
		    	entity.setState("待分管副总审核");
		    else if(entity.getManagerdate()==null)
		    	entity.setState("待总经理审核");
		    else{ 
		    	entity.setState("审核完成");
		    	ApplyEO apply = applyManager.getById(entity.getApplyeo());
		    	apply.setApproval(entity.getManagername());
		    	apply.setConsequence("同意");
		    	apply.setEmployedtime(Utils.dateToString(entity.getManagerdate()));
		    	apply.setState("审核完成");
		    	applyManager.save(apply);
		    }
		    employedManger.save(entity);
		    
		    write("{success:true,msg:'保存成功'}");	
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    
    
    
    
    public String listone() {
    	String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		
		page = employedManger.getPage(page, sb.toString());
		page.setUrl("employed!listone.action");	
		return "listone";
    }
    public String editone() {
		entity = employedManger.getById(entity.getId());
		return "editone";
    }
    
    
    
    public String listtwo() {
    	String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (applyid='").append(user.getId()).append("' or departid='").append(user.getId()).append("' or departoneid='");
 		sb.append(user.getId()).append("' or officeid='").append(user.getId()).append("' or viceid='");
 		sb.append(user.getId()).append("' or managerid='").append(user.getId()).append("' )");
		page = employedManger.getPage(page, sb.toString());
		page.setUrl("employed!listtwo.action");	
		return "listtwo";
    }
    public void getInfo() {
		try {
			String param = "Y";//Y可以审核   N不可以审核
		    entity = employedManger.getById(entity.getId());
		    if(entity.getApplyid()!=null && entity.getApplyid().equals(user.getId().toString()))
		    	if(null!=entity.getApplyname() && !"".equals(entity.getApplyname()))
		    		param = "N";
		    	else
		    		param = "Y";
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
		    if(entity.getViceid()!=null && entity.getViceid().equals(user.getId().toString()))
		    	if(null!=entity.getVicename() && !"".equals(entity.getVicename()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getManagerid()!=null && entity.getManagerid().equals(user.getId().toString()))
		    	if(null!=entity.getManagername() && !"".equals(entity.getManagername()))
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
		entity = employedManger.getById(entity.getId());		
		return "edittwo";
    }
   
    
    
    
    public Page<EmployedEO> getPage() {
    	return page;
    }
    public void setPage(Page<EmployedEO> page) {
    	this.page = page;
    }
    public EmployedEO getEntity() {
    	return entity;
    }
    public void setEntity(EmployedEO entity) {
    	this.entity = entity;
    }
   
}