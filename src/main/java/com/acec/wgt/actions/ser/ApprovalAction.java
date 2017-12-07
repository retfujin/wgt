package com.acec.wgt.actions.ser;

import org.springframework.beans.factory.annotation.Autowired;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.web.struts2.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class ApprovalAction extends BaseAction {

	private static final long serialVersionUID = -5976822696016750423L;

//	@Autowired
//	private SMSApprivalManager smsApprivalManager;
 
	private String pageBar; 

	/**
	 * 未审核列表
	 * @return
	 */
	public String undecidedlist() {
//		viewList = smsApprivalManager.getUndecidedList();
		return "undecidedlist";
	}

	/**
	 * 已审核列表
	 * @return
	 */
	public String decidedlist() {
		
//		String pageNo = getRequest().getParameter("pageNo");
//		if(pageNo==null)
//			pageNo="1";
//		int no = Integer.parseInt(pageNo);
//		PaginatorTag pt = smsApprivalManager.getdecidedListByPage(no,20);
//		pt.setUrl("approval!decidedlist.action");
//		pt.setShowTotal(true);
//		pt.setShowAllPages(true);
//		pt.setStrUnit("条");
//		viewList = pt.getData();
//		pageBar = pt.showPage();
//		
		return "decidedlist";
	}

	
	
	public String updateapproval() {
		String recordId = getRequest().getParameter("recordId");
		String isApproval = getRequest().getParameter("isApproval");
		String userName = (String) ActionContext.getContext().getSession().get(
				"userName");

//		try {
//			smsApprivalManager.updateApproval(recordId, isApproval, userName);
//			forwardStr = "审批成功";
//			forwardUrl = "approval!undecidedlist.action";
//		} catch (Exception e) {
//			forwardStr = "审批操作失败。原因：" + e.getMessage();
//			e.printStackTrace();
//		}
		return "forward";
	}



	
	
	
	
	
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
}