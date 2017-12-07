package com.acec.wgt.service.office;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
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
public class PlanManager {

	
	private HibernateDao<PlanEO, Integer> planDAO;

	@Autowired
	private ProcessManager processManger;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		planDAO = new HibernateDao<PlanEO, Integer>(sessionFactory, PlanEO.class);
	}
	
	/**
	 * 获取指定条件分页列表 
	 * 
	 * @param processTemplateVO
	 * @param pageNo
	 * @param pageSize
	 * @return PageForm
	 */
	public Page getLeaveListPage(Page page,String where) {
		String sql="from PlanEO where 1=1 "+where;
		return planDAO.findPage(page, sql);
	}

	public void onlySave(PlanEO entity) {
		planDAO.save(entity);
	}

	public void saveAndSubmit(PlanEO entity) throws Exception {
		

			ProcessEO press = processManger.startProcess(entity.getEmployeeId(), "jhlc");
			entity.setProcess(press);
			
			planDAO.save(entity);
	
		
		
	}

	public PlanEO getEntityAndProcess(int id) {
		PlanEO  plan =  planDAO.get(id);
		ProcessEO process = processManger.getProcess(plan.getProcess().getId());
		plan.setProcess(process);
		
		return plan;
	}
	
	public PlanEO getEntityAndProcessByProcessId(int processId) {
		
		PlanEO  plan =  planDAO.findUnique("from PlanEO where process.id=?", processId);
		ProcessEO process = processManger.getProcess(plan.getProcess().getId());
		plan.setProcess(process);
		
		return plan;
	}
	
	public PlanEO getEntity(int id) {
		return planDAO.get(id);
	}

	public void delete(int id) {
		PlanEO plan  = planDAO.get(id);
		if(plan!=null&&plan.getProcess()!=null){
			processManger.deleteProcessById(plan.getProcess().getId());
		}
		
		planDAO.delete(id);
		
	}

	public void approvalSave(ProcessModuleEO processModel) throws Exception {
		processManger.updateProcess(processModel);
		
	}

	
	
	
}
