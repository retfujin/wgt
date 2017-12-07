package com.acec.wgt.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.sys.ProcessEO;
import com.acec.wgt.models.sys.ProcessModuleEO;
import com.acec.wgt.models.sys.ProcessTemplateEO;
import com.acec.wgt.models.sys.ProcessTemplateModuleEO;
import com.acec.wgt.models.sys.SysBackUpEO;


/**
 * 流程管理方法
 * 
 * @author lau
 * 
 */
@Service
@Transactional
public class ProcessManager {

	
	private HibernateDao<ProcessTemplateEO, Integer> templateDAO;
	private HibernateDao<ProcessTemplateModuleEO, Integer> templateModuleDAO;
	private HibernateDao<ProcessEO,Integer> processDAO;
	private HibernateDao<ProcessModuleEO,Integer> processModuleDAO;
	
	@Autowired
	private JdbcTemplate jdbcTeamplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		templateDAO = new HibernateDao<ProcessTemplateEO, Integer>(sessionFactory, ProcessTemplateEO.class);
		templateModuleDAO = new HibernateDao<ProcessTemplateModuleEO, Integer>(sessionFactory, ProcessTemplateModuleEO.class);
		processDAO = new HibernateDao<ProcessEO, Integer>(sessionFactory, ProcessEO.class);
		processModuleDAO = new HibernateDao<ProcessModuleEO, Integer>(sessionFactory, ProcessModuleEO.class);
	}
	
	/**
	 * 获取指定条件模板分页列表 ， 不包括模板详细模块
	 * 
	 * @param processTemplateVO
	 * @param pageNo
	 * @param pageSize
	 * @return PageForm
	 */
	public Page getProcessTemplatesForPage(Page page,String where) {
		String sql="from ProcessTemplateEO where 1=1 "+where;
		return templateDAO.findPage(page, sql);
	}

	public boolean checkIsExistTeamplate(ProcessTemplateEO processTemplateEO){
		List ls = templateDAO.createQuery("from ProcessTemplateEO where employeeId=? and processCode=?", processTemplateEO.getEmployeeId(),processTemplateEO.getProcessCode()).list();
		
		if(!ls.isEmpty())
			return true;
		else
			return false;
		
	}
	
	/**
	 * 新增流程
	 * @param processTemplateEO
	 */
	public void insertProcessTemplate(ProcessTemplateEO processTemplateEO) {
		
		List<ProcessTemplateModuleEO> temp = processTemplateEO.getModules();

		// 先插入主模板
		templateDAO.save(processTemplateEO);
		// 插入模板组成模块
		for (int i = 0; i < temp.size(); i++) {
		
				temp.get(i).setTemplateId(processTemplateEO.getId());// 设置模板模块对应主模板编号
				templateModuleDAO.save(temp.get(i));
		}
	}

	//删除流程
	public void deleteProcessTemplate(int id) {
		// 删除已有模块集合
		templateModuleDAO.batchExecute("delete from ProcessTemplateModuleEO where templateId=?", id);
		templateDAO.delete(id);
		
	}
	
	/**
	 * 更新流程模板
	 * 
	 * @param processTemplateVO
	 * @return 1成功 0失败
	 */
	public void updateProcessTemplte(ProcessTemplateEO processTemplateEO) {
	
		//删除
		deleteProcessTemplate(processTemplateEO.getId());
		//新增
		processTemplateEO.setId(null);
		insertProcessTemplate(processTemplateEO);
		
	}

	public ProcessTemplateEO getProcessTemplate(ProcessTemplateEO temp) {
		ProcessTemplateEO template = templateDAO.get(temp.getId());
		List<ProcessTemplateModuleEO> templateModelLs = templateModuleDAO.find("from ProcessTemplateModuleEO where templateId=?", temp.getId());
		template.setModules(templateModelLs);
		
		return template;
	
	}
	
	public ProcessTemplateEO getProcessTemplateByEmployeeId(String employeeId,String processCode) {
		
		ProcessTemplateEO template = processDAO.findUnique("from ProcessTemplateEO where employeeId=? and processCode=?", employeeId,processCode);

		if(template==null){
				throw new RuntimeException("您还没有设置流程");
		}

		List<ProcessTemplateModuleEO> templateModelLs = templateModuleDAO.find("from ProcessTemplateModuleEO where templateId=? order by moduleStep", template.getId());
		template.setModules(templateModelLs);
		
		return template;
	
	}
	
	
	/**
	 * 启动审批流程
	 * 
	 * @param processTemplateVO
	 * @return List<ProcessTemplateVO>
	 * @throws Exception 
	 */
	public ProcessEO startProcess(String employeeId,String processCode) throws Exception {

		
//		ProcessEO  processEO = processDAO.findUnique("from ProcessEO where userId=? and processCode=?", userId,processCode);
		//还没有流程，新增流程
			ProcessTemplateEO template = getProcessTemplateByEmployeeId(employeeId,processCode);
			if(template==null)
				throw new RuntimeException("您还没有设置流程");
			
			ProcessEO process = new ProcessEO();
			process.setDescription(template.getDescription());
			process.setProcessCode(template.getProcessCode());
			process.setModuleName(template.getModules().get(0).getModuleName());
			process.setModuleExecutorId(template.getModules().get(0).getModuleExecutorId());
			process.setModuleExecutorName(template.getModules().get(0).getModuleExecutorName());
			process.setState(0);
			process.setStep(template.getModules().get(0).getModuleStep());
			process.setEmployeeId(BaseAction.getEmployeeId());
			process.setEmployeeName(BaseAction.getEmployeeName());
			process.setBeginDate(Utils.getNowDateToString());
			
			
			processDAO.save(process);
			
			//再保存到流程步骤表
			for(ProcessTemplateModuleEO templateModule: template.getModules()){
				ProcessModuleEO processModule = new ProcessModuleEO();
				processModule.setDescription(templateModule.getModuleDescription());
				processModule.setExecutorId(templateModule.getModuleExecutorId());
				processModule.setExecutorName(templateModule.getModuleExecutorName());
				processModule.setModuleName(templateModule.getModuleName());
				processModule.setModuleStep(templateModule.getModuleStep());
				
				processModule.setProcessId(process.getId());
				processModule.setState(0);
				processModuleDAO.save(processModule);
				process.getModules().add(processModule);
			}
		
			return process;
	}

	/**
	 * 得到整个流程信息
	 * @param processId
	 * @return
	 */
	public ProcessEO getProcess(int processId){
		ProcessEO process= processDAO.get(processId);
		List<ProcessModuleEO> processModelLs = processModuleDAO.find("from ProcessModuleEO where processId=? order by id", processId);
		process.setModules(processModelLs);
		return process;
	}
	
	
	
	
	/**
	 * 更新环节状态
	 * @param processId
	 * @param processModel
	 * @throws Exception 
	 */
	public void updateProcess(ProcessModuleEO p_processModel) throws Exception{
		
		ProcessModuleEO processModel = processModuleDAO.get(p_processModel.getId());
		processModel.setState(p_processModel.getState());
		processModel.setExecutorOpinion(p_processModel.getExecutorOpinion());
		processModel.setExecutorTime(Utils.getNowDate());
		
		ProcessEO process= processDAO.get(processModel.getProcessId());
		
		List<ProcessModuleEO> ls = processModuleDAO.find("from ProcessModuleEO where processId=? and moduleStep=?", processModel.getProcessId(),processModel.getModuleStep()+1);
		//没有下一步了，表示已到最后一步了,流程结束
		if(ls.isEmpty()){
			process.setModuleExecutorId("0");
			process.setModuleExecutorName("");
			process.setState(p_processModel.getState());
			process.setStep(99);
		//有下一步				
		}else{
			ProcessModuleEO module = ls.get(0);
			process.setModuleName(module.getModuleName());
			process.setModuleExecutorId(module.getExecutorId());
			process.setModuleExecutorName(module.getExecutorName());
			process.setState(0);
			process.setStep(module.getModuleStep());
		}
		
		//更新流程表
		processDAO.save(process);
		
		//更新流程步骤表
		processModuleDAO.save(processModel);
	}

	public Page waitApprovalList(Page page, String employeeId, String processType) {
		String where="";
		if(!StringUtils.isEmpty(processType))
			where +=" and process_code='"+processType+"'";
		List<String> ls = new ArrayList<String>();
		ls.add("id");
		ls.add("employee_id");
		ls.add("process_code");
		ls.add("module_name");
		ls.add("department_name");
		ls.add("employee_name");
		return processDAO.findSQLPageMap(page, "select t1.*,t2.name as employee_name,t3.name as department_name from tb_sys_process t1,tb_administration_employee t2,tb_administration_department t3 where t1.employee_id=t2.id and t2.department=t3.id and state='0' and module_executor_id=?"+where,ls, employeeId);
		
	}

	public Page endApprovalList(Page page, String employeeId, String processType) {
		String where="";
		if(!StringUtils.isEmpty(processType))
			where +=" and process_code='"+processType+"'";
		List<String> ls = new ArrayList<String>();
		ls.add("id");
		ls.add("employee_id");
		ls.add("process_code");
		ls.add("module_name");
		ls.add("department_name");
		ls.add("employee_name");
		return processDAO.findSQLPageMap(page, "select t1.*,t2.name as employee_name,t3.name as department_name from tb_sys_process t1,tb_administration_employee t2,tb_administration_department t3 where t1.employee_id=t2.id and t2.department=t3.id and t1.id in(select process_id from tb_sys_process_module t where state<>'0' and t.executor_id=?) "+where,ls, employeeId);
		
	}

	//删除某个进行中的流程
	public void deleteProcessById(Integer processId ){
		processModuleDAO.createQuery("delete from ProcessModuleEO where processId=?", processId).executeUpdate();
		processDAO.delete(processId);
		
	}
	
	
	
}
