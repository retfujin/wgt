package com.acec.wgt.actions.administration;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.administration.ApplyEO;
import com.acec.wgt.models.administration.EmployedEO;
import com.acec.wgt.service.administration.ApplyManager;
import com.acec.wgt.service.administration.EmployedManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.sys.AdminUserManager;


public class ApplyAction extends BaseAction {
    @Autowired
    private ApplyManager applyManger;
    @Autowired
    private PositionManager positionManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private AdminUserManager userManager;
    @Autowired
    private EmployedManager employedManager;
    
    // 分页
    private Page<ApplyEO> page = new Page<ApplyEO>(20);// 每页20条记录

    private ApplyEO entity;
    
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		
		page = applyManger.getPage(page, sb.toString());
		page.setUrl("apply!list.action");	
		return "list";
    }
        
    public String listone() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		
		page = applyManger.getPage(page, sb.toString());
		page.setUrl("apply!listone.action");	
		return "listone";
    }
     
    public String listtwo() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and approvalid ='").append(user.getId()).append("' ");
		page = applyManger.getPage(page, sb.toString());
		page.setUrl("apply!listtwo.action");	
		return "listtwo";
    }
    
    public String add() {
    	retList = areaManager.getAreaALL();
    	viewList = positionManager.getPositions("");
		return "add";
    }
    
    public void getApplyeoid() {
		try {
		    EmployedEO e = employedManager.getByApplyeoId(entity.getId());
		    if(e.getApplyid()!=null && e.getApplyid()!="" && !"".equals(e.getApplyid()))
		    	write("{success:false,msg:'应聘登记正在审核或已审核'}");
		    else
		    	write("{success:true,msg:''}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'获取失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public String edit() {
    	retList = areaManager.getAreaALL();
    	viewList = positionManager.getPositions("");
		entity = applyManger.getById(entity.getId());
		return "edit";
    }
    
    public String editone() {
    	retList = areaManager.getAreaALL();
    	viewList = positionManager.getPositions("");
		entity = applyManger.getById(entity.getId());
		return "editone";
    }
    public String edittwo() {
    	retList = areaManager.getAreaALL();
    	viewList = positionManager.getPositions("");
		entity = applyManger.getById(entity.getId());
		return "edittwo";
    }
    
    public String sel() {
    	entity = applyManger.getById(entity.getId());
		return "sel";
    }


    public void del() {
		try {
		    entity = applyManger.getById(entity.getId());
		    entity.setIsdel("Y");
		    applyManger.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public void save() {
		try {
			
		    if (entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());
		    }else{
		    	employedManager.delByApplyeoid(entity.getId());
		    }
		    String title = getRequestValue("title", "");
		    if(entity.getEmployedtime()==null)
		    	entity.setState("待总经理审核");
		    else
		    	entity.setState("审核完成");
		    
		    applyManger.save(entity);
		    EmployedEO employed =  getByApply(entity);
		    employed.setTitle(title);
		    employed.setApplyeo(entity.getId());
		    
		    employedManager.save(employed);
		    
		    write("{success:true,msg:'保存成功'}");	
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }
   
    public String choose(){
    	viewList = userManager.getUserList();
    	return "choose";
    }
     
    
    public EmployedEO getByApply(ApplyEO entity){
    	EmployedEO e = new EmployedEO();
    	e.setAreaId(entity.getAreaId());
    	e.setAreaName(entity.getAreaName());
    	e.setName(entity.getName());
    	e.setSex(entity.getSex());
    	e.setAge(entity.getAge());
    	e.setEducation(entity.getEducation());
    	e.setProfessional(entity.getProfessional());
    	e.setPosition(entity.getPosition());
    	e.setIsdel("N");
    	e.setRecordid(entity.getRecordid());
    	e.setRecordname(entity.getRecordname());
    	e.setRecordtime(entity.getRecordtime());
    	return e;
    }
    
    public Page<ApplyEO> getPage() {
    	return page;
    }
    public void setPage(Page<ApplyEO> page) {
    	this.page = page;
    }
    public ApplyEO getEntity() {
    	return entity;
    }
    public void setEntity(ApplyEO entity) {
    	this.entity = entity;
    }
   
}