package com.acec.wgt.actions.ser;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.service.sms.SMSRecvManager;

public class RecvsmsAction extends BaseAction{	
	@Autowired
	private SMSRecvManager smsRecvManager;
	
	private String pageBar;
	String pageNo="";
		
	public String list(){
		String mobile=getRequest().getParameter("mobile") !=null ? getRequest().getParameter("mobile"):"";
		String beginDate = getRequest().getParameter("beginDate") !=null ? getRequest().getParameter("beginDate"):"";
		String endDate = getRequest().getParameter("endDate") !=null ? getRequest().getParameter("endDate"):"";
		StringBuilder sb=new StringBuilder();
		if(!mobile.equals(""))
			sb.append(" and mobile like '%").append(mobile).append("%'");
		if(!"".equals(beginDate))
			sb.append(" and date_format(recvTime,'%Y-%m-%d')>='").append(beginDate).append("'");
		if(!"".equals(endDate))
			sb.append(" and date_format(recvTime,'%Y-%m-%d')<='").append(endDate).append("'");
		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);
		try{
		PaginatorTag pt = smsRecvManager.findListByPage(no, 20, sb.toString());
		pt.setUrl("recvsms!list.action");
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
 
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}	
}