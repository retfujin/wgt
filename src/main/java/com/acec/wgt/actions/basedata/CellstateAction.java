package com.acec.wgt.actions.basedata;

import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseAction;


import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.chg.PayManager;
import com.acec.wgt.service.repair.CustomerrepairManager;
import com.acec.wgt.service.suit.SuitCheckManager;

/**
 * 房间状态图
 * 
 * @author Administrator
 *
 */
public class CellstateAction extends BaseAction{

	@Autowired
	private AreaManager areaManager;
	@Autowired
	private CellManager cellManager;
	@Autowired
	private CustomerrepairManager repairManager;
	 @Autowired
	private SuitCheckManager suitCheckManager;
//	@Autowired
//	private Suit
	@Autowired
	private PayManager payManager;
	private String houseId;
	
	public String query(){
		
		retList  = areaManager.getAreaALL();
		return "query";
	}
	
	public String list(){
		String edificeId = getRequest().getParameter("edificeId");
		viewList = cellManager.getHouseByEdificeId(edificeId);
		return "list";
	}
	
	
	
	public String getStateHouse(){
		String houseId = getRequestValue("houseId", "");
		
		String payRecordMonth = payManager.getStateHouse(houseId);
		getRequest().setAttribute("payRecordMonth", payRecordMonth);
		String repairCount = repairManager.findRepairList(houseId);
		getRequest().setAttribute("repairCount", repairCount);
		
		String suitCount = suitCheckManager.findSuitCount(houseId);
		getRequest().setAttribute("suitCount", suitCount);
		
		
		return "getstatehouse";
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
}