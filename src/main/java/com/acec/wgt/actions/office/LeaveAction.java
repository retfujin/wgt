package com.acec.wgt.actions.office;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.office.LeaveEO;
import com.acec.wgt.models.sys.ProcessModuleEO;
import com.acec.wgt.service.office.LeaveManager;
import com.acec.wgt.service.sys.ProcessManager;



public class LeaveAction extends BaseAction {



	@Autowired
	private LeaveManager leaveManger;
	
	private LeaveEO entity;
	
	private ProcessModuleEO processModel;
	//分页
	private Page page = new Page(20);//每页20条记录
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	

	public String list() {
		String where = " and employeeId='"+getEmployeeId()+"'";
		page = leaveManger.getLeaveListPage(page,where);
		page.setUrl("leave!list.action"+getParamString(""));
		
		return "list";
	}

	public String add() {

		return "add";
	}

	public void save() {
		try {
			
			entity.setEmployeeId(getEmployeeId());
			entity.setEmployeeName(getEmployeeName());
			entity.setDepartmentId(getDepartmentId());
			entity.setDepartmentName(getDepartmentName());
			//只是保存
			if("1".equals(getRequest().getParameter("saveType"))){
				leaveManger.onlySave(entity);
			}
			
			else{
				leaveManger.saveAndSubmit(entity);
			}
			
			write("{success:true,msg:''}");

		} catch (Exception e) {
			e.printStackTrace();
			write("{success:false,msg:'"+praseExceptionMessage(e)+"'}");
		}
	}

	public void del() {
		try {
			int id = getRequestValue("id", 0);
			leaveManger.delete(id);
			write("{success:true,msg:''}");
			
		} catch (Exception e) {
			e.printStackTrace();
			write("{success:false,msg:'"+praseExceptionMessage(e)+"'}");

		}
	}
	
	public String view(){
		int id = getRequestValue("id", 0);
		entity = leaveManger.getEntityAndProcess(id);
		return "view";
	}
	
	/**
	 * 只查看详情
	 * @return
	 */
	public String approvalview(){
		int processId = getRequestValue("processId", 0);
		entity = leaveManger.getEntityAndProcessByProcessId(processId);
		return "view";
	}
	
	/**
	 * 进入审批页面
	 * @return
	 */
	public String approval(){
		int processId = getRequestValue("processId", 0);
		entity = leaveManger.getEntityAndProcessByProcessId(processId);
		return "approval";
	}
	
	/**
	 * 审批保存
	 */
	public void approvalSave(){
		
		try {
			leaveManger.approvalSave(processModel);
			write("{success:true,msg:''}");
			
		} catch (Exception e) {
			e.printStackTrace();
			write("{success:false,msg:'"+praseExceptionMessage(e)+"'}");

		}
	}
	public String edit() {
		int id = getRequestValue("id", 0);	
		
		entity = leaveManger.getEntity(id);
		return "edit";
	}
	public LeaveEO getEntity() {
		return entity;
	}
	public void setEntity(LeaveEO entity) {
		this.entity = entity;
	}
	public ProcessModuleEO getProcessModel() {
		return processModel;
	}
	public void setProcessModel(ProcessModuleEO processModel) {
		this.processModel = processModel;
	}

	
	
}
