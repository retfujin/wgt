package com.acec.wgt.service.wuliao;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.commons.persist.HibernateEntityDao;
import com.acec.wgt.models.wuliao.WLInOutboundCopyDAO;
import com.acec.wgt.models.wuliao.WLInOutboundDetailItemDAO;
import com.acec.wgt.models.wuliao.WLInOutboundDetailItemEO;
import com.acec.wgt.models.wuliao.WLInOutboundEO;
import com.acec.wgt.models.wuliao.WLStocksDAO;
import com.acec.wgt.models.wuliao.WLStocksEO;
import com.acec.wgt.models.wuliao.WLStorehouseDAO;
import com.acec.wgt.models.wuliao.WLStorehouseEO;
import com.acec.wgt.models.wuliao.WLInOutboundDAO;

@Service
@Transactional
public class WlInOutboundManager {
	@Autowired
	private WLInOutboundDAO wlinoutboundDAO;
	@Autowired
	private WLInOutboundDetailItemDAO wlinOutboundDetailItemDAO;
	@Autowired
	private WLStocksDAO wlstocksDAO; 
	@Autowired
	private WLStorehouseDAO wlstorehouseDAO;
	 

	/**
	 * 得到新的入出库编号
	 * string prefix 编号前缀，如建账入库RJ 
	 */
	public String getNewBh(String prefix) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String rq = sdf.format(new Date());
		String bhlike=prefix+rq;
		
		List _ls = wlinoutboundDAO.find("select max(bh) from WLInOutboundEO where bh like ?",bhlike+"%");
		
		if(_ls.get(0)==null||"".equals(_ls.get(0).toString())){
			return bhlike+"0001";
		}else{
			String _str=_ls.get(0).toString();
			int nextIncrease = Integer.parseInt(_str.substring(10))+1;
			if(nextIncrease<10)
				return bhlike+"000"+nextIncrease;
			else if(nextIncrease<100)
				return bhlike+"00"+nextIncrease;
			else if(nextIncrease<1000)
				return bhlike+"0"+nextIncrease;
			else
				return bhlike+nextIncrease;
		}
	}

	public void saveEntity(WLInOutboundEO e) {
		//检查并更新当前库存量
		checkAndUpdateStocks(e);
		for(WLInOutboundDetailItemEO item :e.getDetailItemEO())
			wlinOutboundDetailItemDAO.save(item);
		wlinoutboundDAO.save(e);
	}
	

	//检查并更新当前库存量
	private void checkAndUpdateStocks(WLInOutboundEO e) {
		float amount=0F;//入出库单的金额
		for(WLInOutboundDetailItemEO wlid:e.getDetailItemEO()){
			
			wlid.setWlInOutboundEO(e);
			wlid.setRq(e.getRq());
			amount+=wlid.getAmount();//每笔金额合计
			
			WLStocksEO stocksEO = null;
			List _ls =  wlstocksDAO.find("from WLStocksEO where wlCatalogEO.id=? and storeHouseid=?", wlid.getWlCatalogEO().getId(),e.getWlStoreHouseEO().getId());
			if(!_ls.isEmpty()){
				stocksEO = (WLStocksEO)_ls.get(0);
				if(e.getBh().startsWith("C")){//出库,先检查库存
					if(stocksEO.getNum()<wlid.getNum()){
						throw new RuntimeException("物料编号"+wlid.getWlCatalogEO().getId()+"库存量为"+stocksEO.getNum()+",出库量大于当前库存");
					}
					else{
						stocksEO.setNum(stocksEO.getNum()-wlid.getNum());
						stocksEO.setAmount(stocksEO.getNum()*stocksEO.getPrice());
						stocksEO.setGoodsAllocation(wlid.getGoodsAllocation());
                                                
                       //检查库存下限，然后发送短信
                        checkStocksAndSendSMS(stocksEO);
                        }
				}else{//入库，不需检查库存
					stocksEO.setNum(stocksEO.getNum()+wlid.getNum());
					stocksEO.setAmount(stocksEO.getNum()*stocksEO.getPrice());
					stocksEO.setGoodsAllocation(wlid.getGoodsAllocation());
                                        
                                      
				}
				
			}else{
				if(e.getBh().startsWith("C")){//出库,抛出异常
					throw new RuntimeException("物料编号"+wlid.getWlCatalogEO().getId()+"没有库存");
				}else{//入库，新增一条记录
					stocksEO = new WLStocksEO();
					stocksEO.setWlCatalogEO(wlid.getWlCatalogEO());
					stocksEO.setAmount(wlid.getAmount());
					stocksEO.setGoodsAllocation(wlid.getGoodsAllocation());
					stocksEO.setLastRq(wlid.getRq());
					stocksEO.setNum(wlid.getNum());
					stocksEO.setPrice(wlid.getPrice());
					
					stocksEO.setStoreHouseid(e.getWlStoreHouseEO().getId());
			
					
				}
			}
			wlstocksDAO.save(stocksEO);
			
			wlid.setStocksNum(stocksEO.getNum());
			wlid.setStocksAmount(stocksEO.getAmount());
			e.setAmount(amount);
                        
                        
		}
	}
	/**
	 * 库存查询结果
	 * @param parameter 仓库id
	 * @param parameter2 物料id
	 * @return
	 */
	public List getStocksR(String parameter, String parameter2) {
		String where=" 1=1 ";
		if(!"-1".equals(parameter))
			where+="  and storeHouseid="+parameter;
		
		
		if(!"-1".equals(parameter2))
			where+=" and wlCatalogEO.id="+parameter2;
		
		List<WLStorehouseEO> _lsHouse = wlstorehouseDAO.createQuery("from WLStorehouseEO").list();
		
		List<WLStocksEO> _ls = wlstocksDAO.createQuery("from WLStocksEO where"+where).list();
		for(WLStocksEO stocks:_ls){
			for(WLStorehouseEO house:_lsHouse){
				if(house.getId()==stocks.getStoreHouseid())
					stocks.setStoreHouseName(house.getName());
			}
		}
		return _ls;
		
		
	}
	/**
	 * 入出库单查询
	 * @param storeHouseId
	 * @param inOutType
	 * @param beginRq
	 * @param endRq
	 * @return
	 */
	public List getInOutR(String storeHouseId, String inOutType,
			String beginRq, String endRq) {
		String where="where rq>='"+beginRq+"' and rq<='"+endRq+" 23:59:59'";
		if(!"-1".equals(storeHouseId))
			where+="  and wlStoreHouseEO.id="+storeHouseId;
		
		if(!"-1".equals(inOutType))
			where+=" and type='"+inOutType+"'";
		
		
		return wlinoutboundDAO.find("from WLInOutboundEO "+where);
	}
	
	
	/**
	 * 出入库单明细查询结果
	 * @param storeHouseId
	 * @param inOutType
	 * @param wlName
	 * @param beginRq
	 * @param endRq
	 * @return
	 */
	public List getInOutDetailR(String storeHouseId, String inOutType,
			String wlName, String beginRq, String endRq , String name) {
		String where="where rq>='"+beginRq+"' and rq<='"+endRq+" 23:59:59'";
		if(!"-1".equals(storeHouseId))//仓库
			where+="  and wlInOutboundEO.wlStoreHouseEO.id="+storeHouseId;
		
		if(!"-1".equals(inOutType))//入出库单类型
			where+=" and wlInOutboundEO.type='"+inOutType+"'";
		if(!"".equals(wlName))//物料名称
			where+=" and wlCatalogEO.name like '"+wlName+"%'";
		
		
		return wlinoutboundDAO.find("from WLInOutboundDetailItemEO "+where+" order by wlCatalogEO.name");
		
	}
	//低于下限量了,短信通知
        public void checkStocksAndSendSMS(WLStocksEO stock){
            //库存量
            int num = stock.getNum();
            //下限量
            int lowerLimit = stock.getWlCatalogEO().getLowerLimit();
            //低于下限量了,短信通知
//            if(num<=lowerLimit){
//            	String name = stock.getWlCatalogEO().getName();
//            	smsSendManager.sendTingDx("库存",name+"的库存量"+num+"少于下限"+lowerLimit);
//     	       
//            }
        }

		 
	 

//		public void setSmsSendManager(SMSSendManager smsSendManager) {
//			this.smsSendManager = smsSendManager;
//		}

}
