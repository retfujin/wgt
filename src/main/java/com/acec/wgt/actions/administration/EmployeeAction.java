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
import com.acec.wgt.models.administration.DepartmentEO;
import com.acec.wgt.models.administration.EmployeeEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.EmployeeManager;
import com.acec.wgt.service.administration.PositionManager;
import com.acec.wgt.service.basedata.AreaManager;

public class EmployeeAction extends BaseAction {
    @Autowired
    private EmployeeManager employeeManger;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private DepartmentManager departmentManger;
    @Autowired
    private PositionManager positionManager;
    // 分页
    private Page<EmployeeEO> page = new Page<EmployeeEO>(20);// 每页20条记录

    private EmployeeEO entity;
    private List viewList1;
    private String departname;
    private String name;
    private String b_age;
    private String e_age;
    private String b_labordate;
    private String e_labordate;
    private String b_enddate;
    private String e_enddate;
    private String contracttype;
    private String insurance;
    SysUserEO user = AuthorityService.getUser();
    
    public String listone() {
    	DepartmentEO department = new DepartmentEO();
    	department.setName("");
    	viewList = departmentManger.getDepartments("");
    	viewList.add(0, department);
    	String name = getRequestValue("name", "");
    	String departname = getRequestValue("departname", "");
        String b_age = getRequestValue("b_age", "");
        String e_age = getRequestValue("e_age", "");
        String b_labordate = getRequestValue("b_labordate", "");
        String e_labordate = getRequestValue("e_labordate", "");
        String b_enddate = getRequestValue("b_enddate", "");
        String e_enddate = getRequestValue("e_enddate", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		if(!"".equals(departname))
 			sb.append(" and departname ='").append(departname).append("' ");
 		if(!"".equals(b_age))
 			sb.append(" and age>=").append(b_age);
 		if(!"".equals(e_age))
 			sb.append(" and age<=").append(e_age);
 		if(!"".equals(b_labordate))
 			sb.append(" and endlabordate >= '").append(b_labordate).append("' ");
 		if(!"".equals(e_labordate))
 			sb.append(" and endlabordate <= '").append(e_labordate).append("' ");
 		if(!"".equals(b_enddate))
 			sb.append(" and enddate >= '").append(b_enddate).append("' ");
 		if(!"".equals(e_enddate))
 			sb.append(" and enddate <= '").append(e_enddate).append("' ");
 		
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and employeetype = 'gl' ");
		page = employeeManger.getPage(page, sb.toString());
		page.setUrl("employee!listone.action");	
		return "listone";
    }
 
    public String addone() {
    	retList = areaManager.getAreaALL();
		viewList = departmentManger.getDepartments("");
		viewList1 = positionManager.getPositions("");
		return "addone";
    }

    public String editone() {
    	retList = areaManager.getAreaALL();
    	viewList = departmentManger.getDepartments("");
		viewList1 = positionManager.getPositions("");
		entity = employeeManger.getById(entity.getId());
		return "editone";
    }
    
    public String listtwo() {
    	DepartmentEO department = new DepartmentEO();
    	department.setName("");
    	viewList = departmentManger.getDepartments("");
    	viewList.add(0, department);
    	String name = getRequestValue("name", "");
    	String departname = getRequestValue("departname", "");
        String b_age = getRequestValue("b_age", "");
        String e_age = getRequestValue("e_age", "");
        String contracttype = getRequestValue("contracttype", "");
        String insurance = getRequestValue("insurance", "");
        String b_enddate = getRequestValue("b_enddate", "");
        String e_enddate = getRequestValue("e_enddate", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		if(!"".equals(departname))
 			sb.append(" and departname ='").append(departname).append("' ");
 		if(!"".equals(b_age))
 			sb.append(" and age>=").append(b_age);
 		if(!"".equals(e_age))
 			sb.append(" and age<=").append(e_age);
 		if(!"".equals(contracttype))
 			sb.append(" and contracttype =").append(contracttype);
 		if(!"".equals(insurance))
 			sb.append(" and insurance = ").append(insurance);
 		if(!"".equals(b_enddate))
 			sb.append(" and enddate >= '").append(b_enddate).append("' ");
 		if(!"".equals(e_enddate))
 			sb.append(" and enddate <= '").append(e_enddate).append("' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and employeetype = 'yx' ");
		page = employeeManger.getPage(page, sb.toString());
		page.setUrl("employee!listtwo.action");	
		return "listtwo";
    }
 
    public String addtwo() {
    	retList = areaManager.getAreaALL();
		viewList = departmentManger.getDepartments("");
		viewList1 = positionManager.getPositions("");
		return "addtwo";
    }

    public String edittwo() {
    	retList = areaManager.getAreaALL();
    	viewList = departmentManger.getDepartments("");
		viewList1 = positionManager.getPositions("");
		entity = employeeManger.getById(entity.getId());
		return "edittwo";
    }

    public void del() {
		try {
		    entity = employeeManger.getById(entity.getId());
		    entity.setIsdel("Y");
		    employeeManger.save(entity);
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
		    employeeManger.save(entity);
		    write("{success:true,msg:'保存成功'}");
	
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }
 
    
    
    public Page<EmployeeEO> getPage() {
    	return page;
    }
    public void setPage(Page<EmployeeEO> page) {
    	this.page = page;
    }
    public EmployeeEO getEntity() {
    	return entity;
    }
    public void setEntity(EmployeeEO entity) {
    	this.entity = entity;
    }
	public List getViewList1() {
		return viewList1;
	}
	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getB_age() {
		return b_age;
	}
	public void setB_age(String b_age) {
		this.b_age = b_age;
	}
	public String getE_age() {
		return e_age;
	}
	public void setE_age(String e_age) {
		this.e_age = e_age;
	}
	public String getB_labordate() {
		return b_labordate;
	}
	public void setB_labordate(String b_labordate) {
		this.b_labordate = b_labordate;
	}
	public String getE_labordate() {
		return e_labordate;
	}
	public void setE_labordate(String e_labordate) {
		this.e_labordate = e_labordate;
	}
	public String getB_enddate() {
		return b_enddate;
	}
	public void setB_enddate(String b_enddate) {
		this.b_enddate = b_enddate;
	}
	public String getE_enddate() {
		return e_enddate;
	}
	public void setE_enddate(String e_enddate) {
		this.e_enddate = e_enddate;
	}

	public String getContracttype() {
		return contracttype;
	}

	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}    
	
}