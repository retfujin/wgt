package com.acec.wgt.actions.contract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.contract.OvertimeEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.contract.OvertimeManager;

public class OvertimeAction extends BaseAction {
    @Autowired
    private OvertimeManager overtimeManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private DepartmentManager departmentManager;
    
    
    // 分页
    private Page<OvertimeEO> page = new Page<OvertimeEO>(20);// 每页20条记录
    private OvertimeEO entity;
    SysUserEO user = AuthorityService.getUser();

    public String list() {	
    	String departname = getRequestValue("departmentname", "");
 		StringBuffer sb = new StringBuffer();
// 		if(!"".equals(departname))
// 			sb.append(" and departmentname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = overtimeManager.getPage(page,sb.toString());
		page.setUrl("overtime!list.action");
		return "list";
    }

    public String add() {
    	retList = areaManager.getAreaALL();
    	viewList = departmentManager.getDepartments("");
    	return "add";
    }
    
    public String edit() {
    	viewList = departmentManager.getDepartments("");
		entity = overtimeManager.getById(entity.getId());
		retList = areaManager.getAreaALL();
		return "edit";
    }
    
    public String sel() {
    	entity = overtimeManager.getById(entity.getId());
    	return "sel";
    }

    public void del() {
		try {
		    entity = overtimeManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    overtimeManager.save(entity);
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
		    else if(entity.getDepartonedate()==null && (entity.getViceid()==null || "".equals(entity.getViceid())))
		    	entity.setState("待物业部审核");
		    else if(entity.getVicedate()==null)
		    	entity.setState("待分管副总审核");
		    else if(entity.getManagerdate()==null)
		    	entity.setState("待总经理审核");
		    else 
		    	entity.setState("审核完成");
		    overtimeManager.save(entity);
		    write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    
    
    
    public String listone() {	
    	String departname = getRequestValue("departmentname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departmentname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = overtimeManager.getPage(page,sb.toString());
		page.setUrl("overtime!listone.action");
		return "listone";
    }
    public String editone() {
		entity = overtimeManager.getById(entity.getId());
		return "editone";
    }
    public String listtwo() {	
    	String departname = getRequestValue("departmentname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departmentname like '%").append(departname).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (departid ='").append(user.getId()).append("' or departoneid ='").append(user.getId())
 		.append("' or viceid = '").append(user.getId()).append("' or managerid ='").append(user.getId()).append("') ");
 		page = overtimeManager.getPage(page,sb.toString());
		page.setUrl("overtime!listtwo.action");
		return "listtwo";
    }
    public void getInfo() {
		try {
		    entity = overtimeManager.getById(entity.getId());
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
		entity = overtimeManager.getById(entity.getId());
		return "edittwo";
    }
    
    
    public Page getPage() {
    	return page;
    }
    public void setPage(Page page) {
    	this.page = page;
    }

	public OvertimeEO getEntity() {
		return entity;
	}

	public void setEntity(OvertimeEO entity) {
		this.entity = entity;
	}
    
}