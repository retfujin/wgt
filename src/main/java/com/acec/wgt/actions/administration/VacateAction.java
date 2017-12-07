package com.acec.wgt.actions.administration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.administration.VacateEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.administration.VacateManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.models.sys.SysUserEO;

public class VacateAction extends BaseAction {
    @Autowired
    private VacateManager vacateManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private PositionManager positionManager;
    @Autowired
    private DepartmentManager departmentManager;
    
    // 分页
    private Page<VacateEO> page = new Page<VacateEO>(20);// 每页20条记录

    private VacateEO entity;
    private List viewList1;
    
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = vacateManager.getPage(page, sb.toString());
		page.setUrl("vacate!list.action");	
		return "list";
    }
    
    public String add() {
    	viewList = areaManager.getAreaALL();
    	retList = departmentManager.getDepartments("");
    	viewList1 = positionManager.getPositions("");
		return "add";
    }

    public String edit() {
    	viewList = areaManager.getAreaALL();
    	entity = vacateManager.getById(entity.getId());
    	retList = departmentManager.getDepartments("");
    	viewList1 = positionManager.getPositions("");
		return "edit";
    }
    
    public String sel() {
    	entity = vacateManager.getById(entity.getId());
		return "sel";
    }

    public void del() {
		try {
		    entity = vacateManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    vacateManager.save(entity);
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
		    if(entity.getAuditdate()==null)
		    	entity.setState("待审核人审核");
		    else if(entity.getApprovaldate()==null)
		    	entity.setState("待批准人审核");
		    else 
		    	entity.setState("审核完成");
		    vacateManager.save(entity);
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
		page = vacateManager.getPage(page, sb.toString());
		page.setUrl("vacate!listone.action");	
		return "listone";
    }
    public String editone() {
    	entity = vacateManager.getById(entity.getId());
		return "editone";
    }
    
    public String listtwo() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (auditid ='").append(user.getId()).append("' or approvalid ='").append(user.getId()).append("' ) ");
		page = vacateManager.getPage(page, sb.toString());
		page.setUrl("vacate!listtwo.action");	
		return "listtwo";
    }
    public void getInfo() {
		try {
		    entity = vacateManager.getById(entity.getId());
		    String param = "Y";//Y可以审核   N不可以审核
		    if(entity.getAuditid()!=null && entity.getAuditid().equals(user.getId().toString()))
		    	if(null!=entity.getAuditname() && !"".equals(entity.getAuditname()))
		    		param = "N";
				else
				    param = "Y";
		    if(entity.getApprovalid()!=null && entity.getApprovalid().equals(user.getId().toString()))
		    	if(null!=entity.getApprovalname() && !"".equals(entity.getApprovalname()))
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
    	entity = vacateManager.getById(entity.getId());
		return "edittwo";
    }
    
    
    
    
    public Page<VacateEO> getPage() {
    	return page;
    }
    public void setPage(Page<VacateEO> page) {
    	this.page = page;
    }
    public VacateEO getEntity() {
    	return entity;
    }
    public void setEntity(VacateEO entity) {
    	this.entity = entity;
    }
	public List getViewList1() {
		return viewList1;
	}
	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}   
}