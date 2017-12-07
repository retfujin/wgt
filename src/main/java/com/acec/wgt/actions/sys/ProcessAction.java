package com.acec.wgt.actions.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.sys.ProcessTemplateEO;
import com.acec.wgt.models.sys.ProcessTemplateModuleEO;
import com.acec.wgt.service.sys.ProcessManager;
import com.acec.wgt.service.administration.EmployeeManager;
import com.acec.wgt.service.administration.DepartmentManager;


public class ProcessAction extends BaseAction {


	@Autowired
	private ProcessManager processManger;
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private DepartmentManager departmentManager;
	
	//分页
	private Page page = new Page(20);//每页20条记录

	
	private List<ProcessTemplateModuleEO> templateModules = new ArrayList<ProcessTemplateModuleEO>();

	private ProcessTemplateEO processTemplateEO = new ProcessTemplateEO();

	private ProcessTemplateModuleEO processTemplateModuleEO = new ProcessTemplateModuleEO();

	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<ProcessTemplateModuleEO> getTemplateModules() {
		return templateModules;
	}

	public void setTemplateModules(List<ProcessTemplateModuleEO> templateModules) {
		this.templateModules = templateModules;
	}


	public ProcessTemplateEO getProcessTemplateEO() {
		return processTemplateEO;
	}

	public void setProcessTemplateVO(ProcessTemplateEO processTemplateEO) {
		this.processTemplateEO = processTemplateEO;
	}

	public ProcessTemplateModuleEO getProcessTemplateModuleEO() {
		return processTemplateModuleEO;
	}

	public void setProcessTemplateModuleVO(ProcessTemplateModuleEO processTemplateModuleVO) {
		this.processTemplateModuleEO = processTemplateModuleVO;
	}

	public String templatelist() {
		String where = " and employeeId='"+getEmployeeId()+"'";
		page = processManger.getProcessTemplatesForPage(page,where);
		page.setUrl("process!templatelist.action"+getParamString(""));
		
		return "templatelist";
	}

	public String templateadd() {

		return "templateadd";
	}

	public void templatesave() {
		try {
			List<ProcessTemplateModuleEO> modules = new ArrayList<ProcessTemplateModuleEO>();

			String[] temp_id = getRequest().getParameterValues("moduleStep");
			String[] temp_name = getRequest().getParameterValues("moduleName");
			String[] temp_executor_id = getRequest().getParameterValues("moduleExecutorId");
			String[] temp_executor_name = getRequest().getParameterValues("moduleExecutorName");
			String[] temp_description = getRequest().getParameterValues("moduleDescription");
			

			for (int i = 0; i < temp_id.length; i++) {
				
				ProcessTemplateModuleEO tempModule = new ProcessTemplateModuleEO();
				tempModule.setTemplateId(processTemplateEO.getId());
				tempModule.setModuleStep(Integer.parseInt(temp_id[i]));
				tempModule.setModuleName(temp_name[i]);
				tempModule.setModuleExecutorId(temp_executor_id[i]);
				tempModule.setModuleExecutorName(temp_executor_name[i]);
				tempModule.setModuleDescription(temp_description[i]);
				modules.add(tempModule);
			}

			String employeeId = getEmployeeId();
			if(StringUtils.isEmpty(employeeId)){
				
				write("{success:false,msg:'您没有关联员工信息，不能创建，请先关联员工信息'}");
				return;
				
			}
			processTemplateEO.setEmployeeId(employeeId);//流程创建人员工id
			processTemplateEO.setEmployeeName(getEmployeeName());//流程创建人员工姓名
			processTemplateEO.setModules(modules);
			

			if (processTemplateEO.getId() != null) {
				processManger.updateProcessTemplte(processTemplateEO);
			} else {
				//是否已经存在该流程的定义
				if(processManger.checkIsExistTeamplate(processTemplateEO)){
					
					write("{success:false,msg:'您已经有了该流程的设置，不能新增。您可以进行重新编辑'}");
					return;
				}
				
				
				processManger.insertProcessTemplate(processTemplateEO);
			}

			write("{success:true,msg:''}");

		} catch (Exception e) {
			e.printStackTrace();
			write("{success:false,msg:'"+praseExceptionMessage(e)+"'}");
		}
	}

	public void templatedel() {
		try {
			int id = getRequestValue("id", 0);
			processManger.deleteProcessTemplate(id);
			write("{success:true,msg:''}");
			
		} catch (Exception e) {
			e.printStackTrace();
			write("{success:false,msg:'"+praseExceptionMessage(e)+"'}");

		}
	}

	public String templateedit() {
		int id = getRequestValue("id", 0);
		ProcessTemplateEO temp = new ProcessTemplateEO();
		temp.setId(id);
		processTemplateEO = processManger.getProcessTemplate(temp);

		templateModules = processTemplateEO.getModules();

		return "templateedit";
	}

	
	public String waitapprovallist(){
		String processType = getRequestValue("processType","");
		page = processManger.waitApprovalList(page,getEmployeeId(),processType);
		List<Map> ls = page.getResult();
		if(ls!=null){
			for(Map map : ls){
				
				String process_code = map.get("process_code").toString();
				if(process_code.equals("qjlc")){
					map.put("process_name", "请假流程");
					map.put("url", "/office/leave!approval.action?processId="+map.get("id"));
				}else if(process_code.equals("jhlc")){
					map.put("process_name", "工作计划流程");
					map.put("url", "/office/plan!approval.action?processId="+map.get("id"));
					
				}
				
			}
			
			
		}
		
		return "waitapprovallist";
	}
	
	public String endapprovallist(){
		String processType = getRequestValue("processType","");
		page = processManger.endApprovalList(page,getEmployeeId(),processType);
		List<Map> ls = page.getResult();
		if(ls!=null){
			for(Map map : ls){
				
				String process_code = map.get("process_code").toString();
				if(process_code.equals("qjlc")){
					map.put("process_name", "请假流程");
					map.put("url", "/office/leave!approvalview.action?processId="+map.get("id"));
				}else if(process_code.equals("jhlc")){
					map.put("process_name", "工作计划流程");
					map.put("url", "/office/plan!approvalview.action?processId="+map.get("id"));
					
				}
				
			}
		}
		return "endapprovallist";
	}
	
	
	/**
	 * 选择人员
	 * @return
	 */
	public String choose(){
		String departmentId = getRequest().getParameter("departmentId")!=null ? getRequest().getParameter("departmentId") :"" ;
		retList = departmentManager.getDepartments("");
//		viewList = employeeManager.getEmployeeList(departmentId);
		return "choose";
	}
}
