package com.acec.wgt.actions.wuliao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.wuliao.ProcurementEO;
import com.acec.wgt.models.wuliao.ProcurementdetailEO;
import com.acec.wgt.service.sys.AdminUserManager;
import com.acec.wgt.service.wuliao.ProcurementdetailManager;
import com.acec.wgt.service.wuliao.ProcurementManager;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.models.sys.SysUserEO;

public class ProcurementAction extends BaseAction {
    
	@Autowired
    private ProcurementManager procurementManager;
    @Autowired
    private ProcurementdetailManager procurementdetailManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private DepartmentManager departmentManager;
    @Autowired
    private AdminUserManager userManager;
    
    // 分页
    private Page<ProcurementEO> page = new Page<ProcurementEO>(20);// 每页20条记录

    private ProcurementEO entity;
    private ProcurementdetailEO dentity;
    private List viewList1;
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = procurementManager.getPage(page, sb.toString());
		page.setUrl("procurement!list.action");	
		return "list";
    }
    
    public String add() {
    	retList = areaManager.getAreaALL();
    	viewList1 = departmentManager.getDepartments("");
		return "add";
    }
    
    public String sel() {
		entity = procurementManager.getById(entity.getId());
		StringBuffer sb = new StringBuffer();
		sb.append(" where procurementid = ").append(entity.getId());
		viewList = procurementdetailManager.getTrainingdetail(sb.toString());
		return "sel";
    }
    
    public String edit() {
    	retList = areaManager.getAreaALL();
		entity = procurementManager.getById(entity.getId());
		StringBuffer sb = new StringBuffer();
		sb.append(" where procurementid = ").append(entity.getId());
		viewList = procurementdetailManager.getTrainingdetail(sb.toString());
		viewList1 = departmentManager.getDepartments("");
		return "edit";
    }

    public void del() {
		try {
		    entity = procurementManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    procurementManager.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public void save() {
    	String[] goodsname = getRequest().getParameterValues("goodsname");
    	String[] unit = getRequest().getParameterValues("unit");
    	String[] num = getRequest().getParameterValues("num");
    	String[] brand = getRequest().getParameterValues("brand");
    	String[] price = getRequest().getParameterValues("price");
    	String[] inuse = getRequest().getParameterValues("inuse");
    	String[] detailid = getRequest().getParameterValues("detailid");
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
		    if (entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());
		    	entity.setRecordtime(sf.format(new Date()));
		    }
		    entity.setState("待部门审核");
		    procurementManager.save(entity);

		    if(goodsname!=null && goodsname.length>0){
		    	for(int i = 0; i < goodsname.length;i++){
		    		ProcurementdetailEO d = new ProcurementdetailEO();
		    		if(null!=goodsname && null!=goodsname[i] && !"".equals(goodsname[i]))
		    			d.setGoodsname(goodsname[i]);		    		
		    		if(null!=unit && null!=unit[i] && !"".equals(unit[i]))
		    			d.setUnit(unit[i]);
		    		if(null!=num && null!=num[i] && !"".equals(num[i]))
		    			d.setNum(num[i]);
		    		if(null!=brand && null!=brand[i] && !"".equals(brand[i]))
		    			d.setBrand(brand[i]);
		    		if(null!=price && null!=price[i] && !"".equals(price[i]))
		    			d.setPrice(price[i]);
		    		if(null!=inuse && null!=inuse[i] && !"".equals(inuse[i]))
		    			d.setInuse(inuse[i]);
		    		if(null!=detailid && i<detailid.length){
		    			if(null!=detailid[i] && !"".equals(detailid[i]))
		    				d.setId(Integer.parseInt(detailid[i]));
		    		}		    			
		    		d.setProcurementid(entity.getId());
		    		procurementdetailManager.save(d);
		    	}
		    }
		    
		    write("{success:true,msg:'保存成功'}");	
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public void editsave() {
		try {
		    if(entity.getDepartdate()==null)
		    	entity.setState("待部门审核");
		    else if(entity.getDepartonedate()==null)
		    	entity.setState("待物业部审核");
		    else if(entity.getOfficedate()==null)
		    	entity.setState("待综合部审核");
		    else if(entity.getVicedate()==null)
		    	entity.setState("待分管副总审核");
		    else if(entity.getManagerdate()==null)
		    	entity.setState("待总经理审核");
		    else 
		    	entity.setState("审核完成");
		    procurementManager.save(entity);
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
		page = procurementManager.getPage(page, sb.toString());
		page.setUrl("procurement!listone.action");	
		return "listone";
    }
    public String editone() {
    	entity = procurementManager.getById(entity.getId());
		StringBuffer sb = new StringBuffer();
		sb.append(" where procurementid = ").append(entity.getId());
		viewList = procurementdetailManager.getTrainingdetail(sb.toString());
		return "editone";
    }
    public String listtwo() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (departid='").append(user.getId()).append("' or departoneid='");
 		sb.append(user.getId()).append("' or officeid='").append(user.getId()).append("' or viceid='");
 		sb.append(user.getId()).append("' or managerid='").append(user.getId()).append("' )");
		page = procurementManager.getPage(page, sb.toString());
		page.setUrl("procurement!listtwo.action");	
		return "listtwo";
    }
    public void getInfo() {
		try {
		    String param = "Y";//Y可以审核   N不可以审核
		    entity = procurementManager.getById(entity.getId());
		    
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
    	entity = procurementManager.getById(entity.getId());
		StringBuffer sb = new StringBuffer();
		sb.append(" where procurementid = ").append(entity.getId());
		viewList = procurementdetailManager.getTrainingdetail(sb.toString());
		return "edittwo";
    }
    
    
    public String choose(){
    	viewList = userManager.getUserList();
    	return "choose";
    }
    
    public List getViewList1() {
		return viewList1;
	}

	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}

	public Page<ProcurementEO> getPage() {
    	return page;
    }
    public void setPage(Page<ProcurementEO> page) {
    	this.page = page;
    }
    public ProcurementEO getEntity() {
    	return entity;
    }
    public void setEntity(ProcurementEO entity) {
    	this.entity = entity;
    }
	public ProcurementdetailEO getDentity() {
		return dentity;
	}
	public void setDentity(ProcurementdetailEO dentity) {
		this.dentity = dentity;
	}
   
}