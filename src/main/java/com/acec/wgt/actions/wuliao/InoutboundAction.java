package com.acec.wgt.actions.wuliao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.wuliao.WLInOutboundDetailItemEO;
import com.acec.wgt.models.wuliao.WLInOutboundEO;
import com.acec.wgt.service.wuliao.WLAssortmentManager;
import com.acec.wgt.service.wuliao.WLCatalogManager;
import com.acec.wgt.service.wuliao.WLInOutboundDetailItemManager;
import com.acec.wgt.service.wuliao.WLStorehouseManager;
import com.acec.wgt.service.wuliao.WLSupplierManager;
import com.acec.wgt.service.wuliao.WlInOutboundManager;

public class InoutboundAction extends BaseAction {
 
	private static final long serialVersionUID = -2621721299115843546L;
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WlInOutboundManager manager;
	@Autowired
	private WLStorehouseManager wlStorehouseManager;
	@Autowired
	private WLSupplierManager wlSupplierManager;
	@Autowired
	private WLCatalogManager wlCatalogManager; 
	@Autowired
	private WLAssortmentManager wlAssortmentManager;
	private WLInOutboundEO e;
	 
	private List viewList1;
	private List<WLInOutboundDetailItemEO> detailList; 
	
	private Integer id;
	private String newBh;
 
	
	//新增建账入库
	public String addrj(){
		newBh = manager.getNewBh("R");
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlAssortmentManager.getAll();//物料类别
		log.debug("建账入库，新增的单号:"+newBh);
		return "addrj";
	}
	
	//保存建账入库
	public void saverj(){
//		log.debug(detailList);
//		if(!detailList.isEmpty()){
//			for(WLInOutboundDetailItemEO e:detailList){
//				log.debug(e.getWlCatalogEO().getId());
//				log.debug(e.getPrice());
//			}
//		}
		e.setType("建账入库");
		e.setDetailItemEO(detailList);
 		try{
			manager.saveEntity(e);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}
		
		return ;
	}
	 
	//新增采购入库
	public String addrc(){
		newBh = manager.getNewBh("R");
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlSupplierManager.getAll();
		retList= wlAssortmentManager.getAll();//物料类别
		log.debug("采购入库，新增的单号:"+newBh);
		return "addrc";
	}
	
	//保存采购入库
	public void saverc(){
		e.setType("采购入库");
		e.setDetailItemEO(detailList);
		try{
			manager.saveEntity(e);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}
		return ;
	}
   
	//新增退货出库
	public String addct(){
		newBh = manager.getNewBh("C");
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlSupplierManager.getAll();
		retList= wlAssortmentManager.getAll();//物料类别
		log.debug("退货出库，新增的单号:"+newBh);
		return "addct";
	}
	
	//保存退货出库
	public void savect(){
		e.setType("退货出库");
		e.setDetailItemEO(detailList);
		try{
			manager.saveEntity(e);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}
		return ;
	}
	
	//新增领料出库
	public String addck(){
		newBh = manager.getNewBh("C");
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlSupplierManager.getAll();
		retList= wlAssortmentManager.getAll();//物料类别
		log.debug("退货出库，新增的单号:"+newBh);
		return "addck";
	}
	
	//保存领料出库
	public void saveck(){
		e.setType("领料出库");
		e.setDetailItemEO(detailList);
		try{
			manager.saveEntity(e);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}		
		return ;
	}
	
	//新增领料退库
	public String addce(){
		newBh = manager.getNewBh("C");
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlSupplierManager.getAll();
		retList= wlAssortmentManager.getAll();//物料类别
		log.debug("领料退库，新增的单号:"+newBh);
		return "addce";
	}
	
	//保存领料退库
	public void savece(){
		e.setType("领料退库");
		e.setDetailItemEO(detailList);
		try{
			manager.saveEntity(e);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}
		
		return ;
	}
	
	//新增报损出库
	public String addcs(){
		newBh = manager.getNewBh("C");
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlSupplierManager.getAll();
		retList= wlAssortmentManager.getAll();//物料类别
		log.debug("领料退库，新增的单号:"+newBh);
		return "addcs";
	}
	
	//保存报损出库
	public void savecs(){
		e.setType("报损出库");
		e.setDetailItemEO(detailList);
		try{
			manager.saveEntity(e);
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}
		return;
	}
	
	
	//库存查询
	public String queryStocksC(){
		viewList = wlStorehouseManager.getAll();
		viewList1 = wlCatalogManager.getAll();		
		return "querystocks";		
	}
	
	//库存查询结果
	public String queryStocksR(){		
		viewList = manager.getStocksR(getRequest().getParameter("storeHouseId"),getRequest().getParameter("itemId"));		
		return "querystocksr";
		
	}
	
	//入出库单查询
	public String queryInOutC(){
		viewList = wlStorehouseManager.getAll();
		retList= wlAssortmentManager.getAll();//物料类别
		return "queryinoutc";
		
	}
	//入出库单查询
	public String queryInOutR(){
		viewList = manager.getInOutR(getRequest().getParameter("storeHouseId"),
				getRequest().getParameter("inOutType"),getRequest().getParameter("beginRq"),
				getRequest().getParameter("endRq"));
	
		return "queryinoutr";
		
	}
	
	//入出库单明细查询
	public String queryInOutDetailC(){
		viewList = wlStorehouseManager.getAll();	
		return "queryinoutdetailc";
		
	}
	//入出库单明细查询结果
	public String queryInOutDetailR(){
		viewList = manager.getInOutDetailR(getRequest().getParameter("storeHouseId"),
				getRequest().getParameter("inOutType"),getRequest().getParameter("wlName"),
				getRequest().getParameter("beginRq"),getRequest().getParameter("endRq"),
			    getRequest().getParameter("name")	
		     );
	
		return "queryinoutdetailr";
		
	}
	
	
	
	
	 
 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WLInOutboundEO getE() {
		return e;
	}
	public void setE(WLInOutboundEO e) {
		this.e = e;
	}
	public String getNewBh() {
		return newBh;
	}
	public void setNewBh(String newBh) {
		this.newBh = newBh;
	}
	public List<WLInOutboundDetailItemEO> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<WLInOutboundDetailItemEO> detailList) {
		this.detailList = detailList;
	}
	public List getViewList1() {
		return viewList1;
	}
	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}	 
	 
}