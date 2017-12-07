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
import com.acec.wgt.models.administration.ApplyEO;
import com.acec.wgt.models.administration.EmployedEO;
import com.acec.wgt.models.administration.PositiveEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.EmployedManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.administration.PositiveManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.sys.AdminRoleManager;
import com.acec.wgt.service.sys.AdminUserManager;
import com.acec.wgt.models.sys.SysRoleEO;
import com.acec.wgt.models.sys.SysUserEO;

public class PositiveAction extends BaseAction {
    @Autowired
    private PositiveManager positiveManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private EmployedManager employedManager;
    @Autowired
    private PositionManager positionManager;
    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private AdminUserManager adminUserManager;
    @Autowired
    private AdminRoleManager adminRoleManager;
    // 分页
    private Page<PositiveEO> page = new Page<PositiveEO>(20);// 每页20条记录

    private PositiveEO entity;
    private List viewList1;
    private List employedList;
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = positiveManager.getPage(page, sb.toString());
		page.setUrl("apply!list.action");	
		return "list";
    }
    
    public String add() {
    	viewList = areaManager.getAreaALL();
    	retList = positionManager.getPositions("");
    	viewList1 = departmentManager.getDepartments("");
    	employedList = employedManager.getEmployedList(" and managerdate is not null order by id desc");
		return "add";
    }

    public String edit() {
    	viewList = areaManager.getAreaALL();
		entity = positiveManager.getById(entity.getId());
		retList = positionManager.getPositions("");
    	viewList1 = departmentManager.getDepartments("");
		return "edit";
    }
    
    public String sel() {
    	entity = positiveManager.getById(entity.getId());
		return "sel";
    }

    public void del() {
		try {
		    entity = positiveManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    positiveManager.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public void save() {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String msg = "保存成功";
    	try {
			SysUserEO sysuser =null;
		    if (entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());
		    	entity.setRecordtime(sf.format(new Date()));
		    	
		    	boolean param = adminUserManager.checkRepeat(entity.getName());
		    	if(param){
		    		msg = "保存成功。系统中用户名已存在，请添加用户后再分配";
		    	}else{
		    		SysRoleEO role = new SysRoleEO();
			    	role = adminRoleManager.getRole(13);
			    	sysuser = new SysUserEO(); 	
			    	sysuser.setUserName(entity.getName());
			    	sysuser.setPassword("000000");
			    	sysuser.setRole(role);
			    	sysuser.setManagerarea(""+entity.getAreaId());
			    	adminUserManager.saveUser(sysuser);
			    	entity.setWorksummaryid(String.valueOf(sysuser.getId()));
		    	}
		    }
		    if(entity.getWorksummarydate()==null)
		    	entity.setState("待试用期工作总结");
		    else if(entity.getDepartdate()==null)
		    	entity.setState("待部门审核");
		    else if(!"1030".equals(String.valueOf(entity.getAreaId())) && entity.getDepartonedate()==null && (entity.getOfficeid()==null || "".equals(entity.getOfficeid())))
		    	entity.setState("待物业部审核");
		    else if(entity.getOfficedate()==null)
		    	entity.setState("待综合部审核");
		    else if(entity.getVicedate()==null)
		    	entity.setState("待分管副总审核");
		    else if(entity.getManagerdate()==null)
		    	entity.setState("待总经理审核");
		    else 
		    	entity.setState("审核完成");
		    
		    positiveManager.save(entity);
		    write("{success:true,msg:'"+msg+"'}");	
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
		page = positiveManager.getPage(page, sb.toString());
		page.setUrl("apply!listone.action");	
		return "listone";
    }
    public String editone() {
    	viewList = areaManager.getAreaALL();
		entity = positiveManager.getById(entity.getId());
		return "editone";
    }
    
    public String listtwo() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (worksummaryid='").append(user.getId()).append("' or departid='").append(user.getId()).append("' or departoneid='");
 		sb.append(user.getId()).append("' or officeid='").append(user.getId()).append("' or viceid='");
 		sb.append(user.getId()).append("' or managerid='").append(user.getId()).append("' )");
		page = positiveManager.getPage(page, sb.toString());
		page.setUrl("apply!listtwo.action");	
		return "listtwo";
    }
    public void getInfo() {
		try {
		    entity = positiveManager.getById(entity.getId());
		    String param = "Y";//Y可以审核   N不可以审核
		    if(entity.getWorksummaryid()!=null && entity.getWorksummaryid().equals(user.getId().toString()))
		    	if(null!=entity.getWorksummaryname() && !"".equals(entity.getWorksummaryname()))
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
		    if(entity.getViceid() !=null && entity.getViceid().equals(user.getId().toString()))
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
		entity = positiveManager.getById(entity.getId());
		return "edittwo";
    }

    
    
    public Page<PositiveEO> getPage() {
    	return page;
    }
    public void setPage(Page<PositiveEO> page) {
    	this.page = page;
    }
    public PositiveEO getEntity() {
    	return entity;
    }
    public void setEntity(PositiveEO entity) {
    	this.entity = entity;
    }
	public List getViewList1() {
		return viewList1;
	}
	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}
	public List getEmployedList() {
		return employedList;
	}
	public void setEmployedList(List employedList) {
		this.employedList = employedList;
	}
}