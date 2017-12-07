package com.acec.wgt.service.office;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.office.LeaveEO;
import com.acec.wgt.models.office.PlanEO;
import com.acec.wgt.models.sys.ProcessEO;
import com.acec.wgt.models.sys.ProcessModuleEO;
import com.acec.wgt.service.sys.ProcessManager;


/**
 * 流程管理方法
 * 
 * @author lau
 * 
 */
@Service
@Transactional
public class LeaveManager {

	
	private HibernateDao<LeaveEO, Integer> leaveDAO;

	@Autowired
	private ProcessManager processManger;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		leaveDAO = new HibernateDao<LeaveEO, Integer>(sessionFactory, LeaveEO.class);
	}
	
	/**
	 * 获取指定条件模板分页列表 ， 不包括模板详细模块
	 * 
	 * @param processTemplateVO
	 * @param pageNo
	 * @param pageSize
	 * @return PageForm
	 */
	public Page getLeaveListPage(Page page,String where) {
		String sql="from LeaveEO where 1=1 "+where;
		return leaveDAO.findPage(page, sql);
	}

	public void onlySave(LeaveEO entity) {
		leaveDAO.save(entity);
	}

	public void saveAndSubmit(LeaveEO entity) throws Exception {
		

			ProcessEO press = processManger.startProcess(entity.getEmployeeId(), "qjlc");
			entity.setProcess(press);
			
			leaveDAO.save(entity);
	
		
		
	}

	public LeaveEO getEntityAndProcess(int id) {
		LeaveEO  leave =  leaveDAO.get(id);
		ProcessEO process = processManger.getProcess(leave.getProcess().getId());
		leave.setProcess(process);
		
		return leave;
	}
	
	public LeaveEO getEntityAndProcessByProcessId(int processId) {
		LeaveEO  eo= new LeaveEO();
		eo.setId(processId);
		
		LeaveEO  leave =  leaveDAO.findUnique("from LeaveEO where process.id=?", processId);
		ProcessEO process = processManger.getProcess(leave.getProcess().getId());
		leave.setProcess(process);
		
		return leave;
	}
	
	public LeaveEO getEntity(int id) {
		return leaveDAO.get(id);
	}

	public void delete(int id) {
		
		LeaveEO leave  = leaveDAO.get(id);
		if(leave!=null&&leave.getProcess()!=null){
			processManger.deleteProcessById(leave.getProcess().getId());
		}
		
		
		leaveDAO.delete(id);
	}

	public void approvalSave(ProcessModuleEO processModel) throws Exception {
		processManger.updateProcess(processModel);
		
	}

	
	
	
}
