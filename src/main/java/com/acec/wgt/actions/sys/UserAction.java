package com.acec.wgt.actions.sys;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.administration.EmployeeEO;
import com.acec.wgt.models.baseData.AreaEO;

import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.administration.EmployeeManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.sys.AdminUserManager;
import com.opensymphony.xwork2.ActionContext;


public class UserAction extends BaseAction {


	private List viewList1;
	
	private Page page = new Page(10);
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	private SysUserEO entity;
	
	@Autowired
	private AdminUserManager adminUserManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private EmployeeManager employeeManager;
	//列表
	public String list() {
		page  = adminUserManager.findUserAll(page);
		return "list";
	}
	
	//新增用户
	public String add(){
		viewList = adminUserManager.getAllRole((String)getSession().get("companyId"));
		viewList1 = areaManager.getALL((String)getSession().get("companyId"));
		return "add";
	}
	
	//保存用户
	public void save(){
		String[] managerarea = getRequest().getParameterValues("managerarea");
		String managerareaIds = "";
		for(String str:managerarea){
			managerareaIds+=str+",";
		}
		entity.setManagerarea(managerareaIds.substring(0,managerareaIds.length()-1));

		if(entity.getId()!=null){//是编辑
			adminUserManager.saveUser(entity);
			write("{success:true,msg:'保存成功'}");
			return ;
		}
		else{//新增
				
			//判断库中是否已有重复的用户名或用户编码
			boolean isRepreat = adminUserManager.checkRepeat(entity.getUserName());
			if(isRepreat)
				write("{success:false,msg:'数据库中已存在相同的用户名'}");
			else{
				adminUserManager.saveUser(entity);
				write("{success:true,msg:'保存成功'}");
			}
		}
		
		return;
	}
	
	//编辑用户
	public String edit(){
		
		String id = ServletActionContext.getRequest().getParameter("id");
		if (id != null) {	
			entity = adminUserManager.getUser(Integer.valueOf(id));
			//如果关联了员工，将员工信息取出
//			List<EmployeeEO> employeels = employeeManager.getEmployees(" and userId="+entity.getId());
//			if(!employeels.isEmpty()){
//				entity.setEmployeeId(employeels.get(0).getId());
//				entity.setEmployeeName(employeels.get(0).getName());
//			}
			
			
			
			String[] managerarea = entity.getManagerarea().split(",");//所管理小区

			viewList1 = areaManager.getALL((String)getSession().get("companyId"));
			
			//将已经被选择的分公司设置isChecked的字段置1
			for(int i=0;i<viewList1.size();i++){
				AreaEO company = (AreaEO)viewList1.get(i);
				for(String str:managerarea){
					if(Integer.parseInt(str)==company.getId()){
						company.setIsChecked(1);
						break;
					}
				}
				
			}
			
			viewList = adminUserManager.getAllRole((String)getSession().get("companyId"));
		}
		return "add";
	}
	//删除用户
	public void del(){
		String id = ServletActionContext.getRequest().getParameter("id");
		if (id != null) {
			try{
				adminUserManager.delUser(Integer.parseInt(id));
				write("{success:true,msg:'删除成功'}");
			}catch(Exception ex){
				write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
				logger.error("删除用户失败", ex);
			}
			
		}
		return;
	}
	
	public String editPass(){
		
		return "editpass";
	}
	
	
	//修改密码
	public void savePW(){
		//旧密码
		String oldPW = ServletActionContext.getRequest().getParameter("oldPW");
		Map map = ActionContext.getContext().getSession();
		String userName = (String)map.get("userName");
		if(null!=userName&&null!=oldPW){
		
			SysUserEO user = adminUserManager.checkUser(userName,oldPW);
			//验证通过
			if(user!=null){
				//新密码isRepreat
				String newPW = ServletActionContext.getRequest().getParameter("newPW");
				user.setPassword(newPW);
				adminUserManager.updatePass(newPW,user.getId());
				write("{success:true,msg:'成功更新'}");
			}else{
				write("{success:false,msg:'您输入的原始密码不正确，请重新输入'}");
			}
		}else{
			write("{success:false,msg:'您输入的原始密码为空，请重新输入'}");
		}
		return;
	}
	
	
	
	
	
	

	public SysUserEO getEntity() {
		return entity;
	}

	public void setEntity(SysUserEO entity) {
		this.entity = entity;
	}

	public List getViewList1() {
		return viewList1;
	}

	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}



}
