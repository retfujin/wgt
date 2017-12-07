package com.acec.wgt.actions.repair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.repair.CustomerrepairEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.repair.CustomerrepairManager;

/**
 * 报修管理
 * 
 * @author hua
 * @version 2009-11-27
 */
public class RepairAction extends BaseAction {

	private static final long serialVersionUID = 1992078763723107932L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Autowired
	private CustomerrepairManager repairManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private EdificeManager edificeManager;
	@Autowired
	private CellManager cellManager;

	private CustomerrepairEO customerrepair;

	private Page page = new Page(20);// 每页20条记录
	private List retList;
	
	
	
	
	/**
	 * 新报修登记
	 * @return
	 */
	public String add(){
		retList = areaManager.getAreaALL();
		return "add";
	}
	
	/**
	 * 保存请修记录(第一次新增保修记录)
	 * @return
	 */
	public void save() {
		try {
			customerrepair.setFlowState("0");
			
			if (customerrepair.getHouseId() != null && !customerrepair.getHouseId().equals("")) {
				if (customerrepair.getContent() == null || customerrepair.getContent().equals("")) {
					try {
						Object[] str = cellManager.getHouseAddress(customerrepair.getHouseId());
						String content = "";
						content = str[2] + "  " + str[3] + "  " + str[0];
						customerrepair.setContent(content);
					} catch (Exception e) {
						e.printStackTrace();
						write("{success:false,msg:'保存失败.原因：业主资料不存在！" + e.getMessage()+"'}");
						return;
					}
				}
			}
			Date date = new Date();
			if (customerrepair.getEndMonth() == null || customerrepair.getEndMonth().equals(""))
				customerrepair.setEndMonth(date);
			if(StringUtils.isEmpty(customerrepair.getRepairStatus()))
				customerrepair.setRepairStatus("未维修");
			if(StringUtils.isEmpty(customerrepair.getPaidService()))
				customerrepair.setPaidService("无偿服务");
			
			repairManager.saveCustomerRepair(customerrepair);
			write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'保存失败.原因：" + ex.getMessage()+"'}");
		}
		return;
	}
	
	
	/**
	 * 待完成列表
	 * @return
	 */
	public String list1(){
		StringBuffer sb=new StringBuffer();
		sb.append(" and flowState=0 ");//完成时间（还未完成）
		page = repairManager.getCustomerRepair(page, sb.toString());
		return "list1";
	}

	
	public String print(){
		String id = getRequest().getParameter("id");
		if (id != null && !id.equals("")){
			customerrepair = repairManager.getCustomerrepair(Integer.parseInt(id));
			customerrepair.setAreaName(areaManager.getAreaById(customerrepair.getAreaId()).getAreaName()); 
		}
		return "print";
	}
	
	/**
	 * 编辑  （待完成列表）
	 * @return
	 */
	public String edit1() {
		String id = getRequest().getParameter("id");
		if (id != null && !id.equals(""))
			customerrepair = repairManager.getCustomerrepair(Integer.parseInt(id));
	//	retList = areaManager.getAreaALL();
		return "edit1";
	}
	
	
	/**
	 * 保存(待完成列表)
	 * @return
	 */
	public void save1() {
		try {
			if("2".equals(customerrepair.getFlowState()))
				write("{success:false,msg:'该报修已回访，不能再修改记录。'}");
			else{
				
				if(!StringUtils.isEmpty(customerrepair.getRepairResult()))//维修结果不为空
					customerrepair.setFlowState("1");
				else
					customerrepair.setFlowState("0");
				
				repairManager.saveCustomerRepair(customerrepair);
				write("{success:true,msg:'保存成功'}");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'保存失败.原因：" + ex.getMessage()+"'}");

		}
		return;
	}
	
	/**
	* 显示详情
	*/
	public String view() {
		String id = getRequest().getParameter("id");
		if (id != null && !id.equals(""))
			customerrepair = repairManager.getCustomerrepair(Integer.parseInt(id));
		retList = areaManager.getAreaALL();
		return "view";
	}

	

	/**
	 * 删除请修登记表记录
	 * 
	 * @return
	 */
	public void del() {
		try {
			repairManager.delCustomerrepair(customerrepair.getId());
		
			write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'删除失败.原因：" + ex.getMessage()+"'}");
		}
		return;
	}




	
	/**
	 * 已完成列表
	 * @return
	 */
	public String list2(){
		
		String areaId = getRequest().getParameter("areaId");
		String houseId = getRequest().getParameter("houseId");
		String beginDate = getRequest().getParameter("beginDate");//受理日期 起始
		String endDate = getRequest().getParameter("endDate"); //受理日期 截至
		String repairStatus = getRequest().getParameter("repairStatus");// 维修状态
		String paidService = getRequest().getParameter("paidService");// 服务状态
		String dayNum = getRequest().getParameter("dayNum");
		String dispatcherNum = getRequest().getParameter("dispatcherNum");//派工单号
		
		StringBuilder sb = new StringBuilder();
		sb.append(" and flowState>0 ");
		
		
		if (areaId != null && !areaId.equals(""))
			sb.append(" and areaId ='").append(areaId).append("' ");
		if (houseId != null && !houseId.equals(""))
			sb.append(" and houseId like '").append(houseId).append("%' ");
		if (beginDate != null && !beginDate.equals(""))
			sb.append(" and date_format(acceptedDate,'%Y-%m-%d')>='").append(beginDate).append("'");
		if (endDate != null && !endDate.equals(""))
			sb.append(" and date_format(acceptedDate,'%Y-%m-%d')<='").append(endDate).append("'");
		if (repairStatus != null && !repairStatus.equals("")) {
			sb.append(" and repairStatus ='").append(repairStatus).append("' ");
		}
		if (paidService != null && !paidService.equals(""))
			sb.append(" and paidService ='").append(paidService).append("' ");
		if(!StringUtils.isEmpty(dispatcherNum)){
			sb.append(" and dispatcherNum ='").append(dispatcherNum).append("' ");
		}
		
		page = repairManager.getCustomerRepair(page, sb.toString());
		retList = areaManager.getAreaALL();
		


		

		return "list2";
	}
	
	/**
	 * 报修完成，回访编缉(已完成列表)
	 * @return
	 */
	public String edit2(){
		String id = getRequest().getParameter("id");
		if (id != null && !id.equals(""))
			customerrepair = repairManager.getCustomerrepair(Integer.parseInt(id));
	//	retList = areaManager.getAreaALL();
		return "edit2";
	}
	
	/**
	 * 保存(已完成列表)
	 * 
	 * @return
	 */
	public void save2() {
		try {
			
//			if (customerrepair.getId() != null && !"".equals(customerrepair.getId())) {
//				if (!StringUtils.isEmpty(customerrepair.getReciprocal()) && customerrepair.getVisitResult() != null && !"".equals(customerrepair.getVisitResult())) {
//	
//					write("{success:false,msg:'回访记录已完成，请修记录完毕，不须更改!'}");
//					return;
//				}
//			}
//			if (customerrepair.getHouseId() != null && !customerrepair.getHouseId().equals("")) {
//				if (customerrepair.getContent() == null || customerrepair.getContent().equals("")) {
//					try {
//						Object[] str = cellManager.getHouseAddress(customerrepair.getHouseId());
//						String content = "";
//						content = str[2].toString() + "  " + str[3].toString() + "  " + str[0].toString();
//						customerrepair.setContent(content);
//					} catch (Exception e) {
//						e.printStackTrace();
//						write("{success:false,msg:'保存失败.原因：业主资料不存在！" + e.getMessage()+"'}");
//						return;
//					}
//				}
//			}
			Date date = new Date();
			if (customerrepair.getEndMonth() == null || customerrepair.getEndMonth().equals(""))
				customerrepair.setEndMonth(date);
			
			
			if(!StringUtils.isEmpty(customerrepair.getVisitResult()))//回访结果不为空
				customerrepair.setFlowState("2");
			else
				customerrepair.setFlowState("1");
			
			repairManager.saveCustomerRepair(customerrepair);
			write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'保存失败.原因：" + ex.getMessage()+"'}");
		}

		return;
	}

	
	/**
	 * 报修统计
	 * @return
	 */
	public String bxstat(){
		String beginTime = getRequest().getParameter("beginTime") != null ? getRequest().getParameter("beginTime") : "";
		String endTime = getRequest().getParameter("endTime") != null ? getRequest().getParameter("endTime") : "";
		viewList = repairManager.bxstat(beginTime, endTime);
		return "bxstat";
	}
	
	
	/*
	 * 根据小区id取楼栋
	 */
	public String getEdifice() {
		String areaId = getRequest().getParameter("areaId");
		viewList = edificeManager.getAllEdifice(Integer.parseInt(areaId));
		return "ajaxedifice";
	}

	/*
	 * 根据小区id取楼栋
	 */
	public String getHouseInfo() {
		String edificeId = getRequest().getParameter("edificeId");
		viewList = edificeManager.findHouseForEdifice(edificeId);
		return "ajaxhouse";
	}
	
	
	
	public CustomerrepairEO getCustomerrepair() {
		return customerrepair;
	}

	public void setCustomerrepair(CustomerrepairEO customerrepair) {
		this.customerrepair = customerrepair;
	}

	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}