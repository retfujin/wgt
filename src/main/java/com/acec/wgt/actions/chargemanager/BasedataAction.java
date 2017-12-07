package com.acec.wgt.actions.chargemanager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.annotations.Token;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.chargemanger.BasedataManager;

/**
 * ????--????
 * @author hua
 * @version 2009-12-26
 */
public class BasedataAction extends BaseAction{	

	@Autowired
	private BasedataManager manager;
	@Autowired
	private AreaManager areaManage;
	
	private List<AreaEO> areaList;
	private AreaEO areaEO;
	private String areaName;
	
	private ChargeBasedataEO chargeBasedata;
	private Page<ChargeBasedataEO> page = new Page<ChargeBasedataEO>(20);//??5???
	
	
	
	/**
	 * ??????
	 * @return
	 */
	public String add()
	{
		areaList = areaManage.getAreaALL();
		return "add";
	}
	
	/**
	 * ??????
	 * @return
	 */
	public String edit()
	{
		chargeBasedata = manager.getChargeBasedataById(chargeBasedata.getId());
		return "edit";
	}
	
	/**
	 * ?????????
	 * @return
	 */
	@Token
	public void save()
	{
		String priceValue = getRequest().getParameter("priceValue") != null ? getRequest().getParameter("priceValue") : "0";
		String action = getRequest().getParameter("action");
		try 
		{		
			//???
			if(null != action)
			{
				if(!manager.isNotChargeId(chargeBasedata.getId()))
				{
					write("{success:false,msg:'?????????????!'}");
					return ;
				}
			}
			if(null == chargeBasedata.getId() || "".equals(chargeBasedata.getId()))
			{
				write("{success:false,msg:'?????????????!'}");
				return ;
			}	
			
			if(chargeBasedata.getArea()==null)
			{
				write("{success:false,msg:'????????????????!'}");
				return ;
			}
			chargeBasedata.setPriceValue(Float.valueOf(priceValue));
			chargeBasedata.setAreaId(chargeBasedata.getArea().getId());
			manager.saveOrUpdateChargeBasedata(chargeBasedata);
			write("{success:true,msg:'????'}");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			write("{success:false,msg:'?????"+ex.getMessage()+"'}");
		}
		return ;
	}
	
	/*
	 * ??????
	 */
	public void delete()
	{
		try {
			manager.deleteChargeBasedataById(chargeBasedata.getId());
			write("{success:true,msg:'????'}");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			write("{success:false,msg:'?????"+ex.getMessage()+"'}");
		}
		return;
	}
	
	
	/**
	 * ???????
	 * @return
	 */
	public String listquery()
	{
		areaList=areaManage.getAreaALL();
		return "listquery";
	}
	/**
	 * ?????????
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list()
	{
		Integer areaId=getRequestValue("areaId", 0);
		page = manager.getListByPage(page,areaId,(String)getSession().get("areaIds"));
		return "list";
	}
	
	//??????????
	public String ajaxRadioList(){
		String areaId = getRequest().getParameter("areaId");
		retList = manager.getChargeNameAll(areaId, "'???','???'");
		return "ajaxradiolist";
	}
	//??????????
	public String ajaxRadioList_pub(){
		String areaId = getRequest().getParameter("areaId");
		retList = manager.getChargeNameAll(areaId, "'???'");
		return "ajaxradiolist";
	}
	
	public List<AreaEO> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<AreaEO> areaList) {
		this.areaList = areaList;
	}
	public ChargeBasedataEO getChargeBasedata() {
		return chargeBasedata;
	}
	public void setChargeBasedata(ChargeBasedataEO chargeBasedata) {
		this.chargeBasedata = chargeBasedata;
	}
	

	public AreaEO getAreaEO() {
		return areaEO;
	}
	public void setAreaEO(AreaEO areaEO) {
		this.areaEO = areaEO;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Page<ChargeBasedataEO> getPage() {
		return page;
	}
	public void setPage(Page<ChargeBasedataEO> page) {
		this.page = page;
	}
}
