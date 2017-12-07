package com.acec.wgt.actions.administration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.administration.RecruitmentEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.administration.RecruitmentManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.models.sys.SysUserEO;

public class RecruitmentAction extends BaseAction {
    @Autowired
    private RecruitmentManager recruitmentManager;
    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private PositionManager positionManager;
    private List viewList1;
    
    // 分页
    private Page<RecruitmentEO> page = new Page<RecruitmentEO>(20);// 每页20条记录

    private RecruitmentEO entity;
    
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
 		String departmentname = getRequestValue("departmentname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departmentname))
 			sb.append(" and departmentname like '").append(departmentname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = recruitmentManager.getPage(page, sb.toString());
		page.setUrl("recruitment!list.action");	
		return "list";
    }
    
    public String add() {
    	retList = areaManager.getAreaALL();
    	viewList = departmentManager.getDepartments("");
    	viewList1 = positionManager.getPositions("");
		return "add";
    }

    public String edit() {
    	retList = areaManager.getAreaALL();
		entity = recruitmentManager.getById(entity.getId());
		viewList = departmentManager.getDepartments("");
		viewList1 = positionManager.getPositions("");
		return "edit";
    }

    public String sel() {
    	entity = recruitmentManager.getById(entity.getId());
		return "sel";
    }
    
    public void del() {
		try {
		    entity = recruitmentManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    recruitmentManager.save(entity);
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
		    if(entity.getDepartdate()==null)
		    	entity.setState("待部门审核");
		    else 
		    	entity.setState("审核完成");
		    recruitmentManager.save(entity);
		    write("{success:true,msg:'保存成功'}");	
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    
    
    
    public String editone() {
		entity = recruitmentManager.getById(entity.getId());
		return "editone";
    }
    public String listone() {
 		String departmentname = getRequestValue("departmentname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departmentname))
 			sb.append(" and departmentname like '").append(departmentname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = recruitmentManager.getPage(page, sb.toString());
		page.setUrl("recruitment!listone.action");	
		return "listone";
    }
    public String edittwo() {
		entity = recruitmentManager.getById(entity.getId());
		return "edittwo";
    }
    public void getInfo() {
		try {
		    entity = recruitmentManager.getById(entity.getId());
		    if(entity.getDepartid()!=null && entity.getDepartid().equals(user.getId().toString()))
		    	if(null!=entity.getDepartname() && !"".equals(entity.getDepartname())){
		    		write("{success:false,msg:'此信息已审批'}");
		    		return;
		    	}
		    
		    write("{success:true,msg:''}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'获取失败.原因：" + ex.getMessage() + "'}");
		}
    }
    public String listtwo() {
 		String departmentname = getRequestValue("departmentname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departmentname))
 			sb.append(" and departmentname like '").append(departmentname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append("and departid='").append(user.getId()).append("' ");
		page = recruitmentManager.getPage(page, sb.toString());
		page.setUrl("recruitment!listtwo.action");	
		return "listtwo";
    }
    
    
    
    
    public Page<RecruitmentEO> getPage() {
    	return page;
    }
    public void setPage(Page<RecruitmentEO> page) {
    	this.page = page;
    }
    public RecruitmentEO getEntity() {
    	return entity;
    }
    public void setEntity(RecruitmentEO entity) {
    	this.entity = entity;
    }
	public List getViewList1() {
		return viewList1;
	}
	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}   
}