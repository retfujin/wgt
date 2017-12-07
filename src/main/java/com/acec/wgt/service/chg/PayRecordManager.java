package com.acec.wgt.service.chg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.orm.hibernate.SimpleHibernateDao;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;

/**
 * 包括业户缴费记录单,催缴记录单
 * @author Administrator
 *
 */
//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class PayRecordManager {
	
	private final Logger logger = LoggerFactory.getLogger(PayRecordManager.class);
	
	//业户缴费记录单
	private HibernateDao<ChargeUserPayRecordEO, Integer> payRecordDao; 
	
	
	
		
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		payRecordDao = new HibernateDao<ChargeUserPayRecordEO, Integer>(sessionFactory, ChargeUserPayRecordEO.class);
	}
	
	/**
	 * 保存用户缴费记录单
	 * @param houseId
	 * @param bh  收据单编号
	 * @param payType 收款类型  收款:优惠等
	 * @param userName 收款人
	 * @param e  缴费细节类
	 * @param nowfactMoney
	 * @param reason 收款具体说明
	 * @return
	 */
	public ChargeUserPayRecordEO savePayRecord(String houseId, String bh, String payType,
			String userName, ChargeHouseDetailEO e,
			float nowfactMoney,String reason) {
		//支付流水帐
		ChargeUserPayRecordEO b = new ChargeUserPayRecordEO();
		b.setBh(bh);
		b.setUserName(userName);//录入人
		b.setChargeName(e.getChargeName());//缴费项目名称
		b.setPayType(payType);//支付类型 收款、优惠、预交、预扣,滞纳金
		b.setReason(reason);//具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
		b.setFactMoney(nowfactMoney);//实际缴费金额
		
		HouseEO houseEO = new HouseEO(houseId); 
		b.setHouse(houseEO);//关联房间
		
		b.setPayName(e.getOwnerName());//保存交款人
		b.setGatheringTime(e.getGatheringDate());
		b.setSubmitTime(new Date(System.currentTimeMillis()));//当前时间
		b.setChargeHouseDetailId(e.getId());
		b.setAreaId(e.getAreaId());//小区id
		b.setChargeId(e.getChargeId());//收费项id
		b.setRecordMonth(e.getRecordMonth());
		savePayRecordMoney(b);
		return b;
	}
	
	/**
	 * 收费查询
	 * 根据缴费时间查找该住户的所有缴费记录
	 * 
	 */	
	public Page getUserChargeRecordByPage(int houseid,String uprecordMonth,String downrecordMonth,Page page) {
		String where="";
		if(null!=uprecordMonth&&uprecordMonth.length()>8){
			where+=" and submitTime>='"+uprecordMonth+"'";
		}
		if(null!=downrecordMonth&&downrecordMonth.length()>8){
			where+=" and submitTime<='"+downrecordMonth+" 23:59:59'";
		}
		
		Page p =  payRecordDao.find(page,"from ChargeUserPayRecordEO where houseEO.id ="+houseid+where,null);
		return p;
	}	
	
	/**
	 * 收据单查询
	 * @param bh
	 * @return
	 */
	public List getReceiptForBh(String bh) {
		logger.debug("收据单号是："+bh);
		return payRecordDao.find("from ChargeUserPayRecordEO where bh =?",bh);
		
	}

	public List getReceiptForRq(String rqStr) {
		logger.debug("收据单号日期前缀是："+rqStr);
		return payRecordDao.find("select bh,sum(factMoney),payType,userName,house.id,house.houseAddress from ChargeUserPayRecordEO where bh like ? group by bh,payType,userName,house.id,house.houseAddress",rqStr+"%");
	}

	/**
	 * 得到新的收据单编号
	 * string prefix 编号前缀
	 */
	public String getNewBh(String prefix) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String rq = sdf.format(new Date());
		String bhlike=prefix+rq;
		
		List _ls = payRecordDao.find("select max(bh) from ChargeUserPayRecordEO where bh like ?",bhlike+"%");
		
		if(_ls.get(0)==null||"".equals(_ls.get(0).toString())){
			return bhlike+"00001";
		}else{
			String _str=_ls.get(0).toString();
			int nextIncrease = Integer.parseInt(_str.substring(9))+1;
			if(nextIncrease<10)
				return bhlike+"0000"+nextIncrease;
			else if(nextIncrease<100)
				return bhlike+"000"+nextIncrease;
			else if(nextIncrease<1000)
				return bhlike+"00"+nextIncrease;
			else if(nextIncrease<10000)
				return bhlike+"0"+nextIncrease;
			else
				return bhlike+nextIncrease;
		}
	}

	/**
	 * 保存交费记录，没有bh的情况
	 * 
	 * @param chargeUserPayRecord
	 */
	public void savePayRecordMoney(ChargeUserPayRecordEO chargeUserPayRecord) {
		
		String userName=(String)Struts2Utils.getSession().getAttribute("userName");
		
		if(null == chargeUserPayRecord.getUserName() || "".equals(chargeUserPayRecord.getUserName()))
			chargeUserPayRecord.setUserName(userName);
		if(chargeUserPayRecord.getBh()==null){
			String bh = getNewBh("C");//获得收据编号
			chargeUserPayRecord.setBh(bh);
		}
		payRecordDao.save(chargeUserPayRecord);
	}


	/**
	 * 取 零星 有偿服务 等不和业主关联的费用缴费记录
	 * 根据条件搜索相应的交费记录
	 * 
	 * @param areaId  默认为0
	 * @param chargeName  零星收费、有偿服务收费
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Page getPayRecord(Page page,int areaId,String chargeName,String beginTime,String endTime) {
		
		StringBuffer where = new StringBuffer("");
		if(areaId > 0)
			where.append(" and areaId="+areaId);
		if(!"".equals(chargeName))
			where.append("and chargeName='"+chargeName+"'");
		if(!"".equals(beginTime))
			where.append("and gatheringTime >='"+beginTime+"'");
		if(!"".equals(endTime))
			where.append("and gatheringTime <='"+endTime+" 23:59:59'");
		
		return getUserChargeRecord(page,where.toString());
		
	}
	
	/**
	 * 取所有交费记录
	 */
	public Page getUserChargeRecord(Page page,String where) {
		
		return payRecordDao.find(page,"from ChargeUserPayRecordEO where 1=1 "+where+ " order by id desc");
	}
	
	
	
	/**
	 * 得到上次催缴单的催缴截止日期
	 * @param adviceId
	 * @return
	 */
	public String getPrintPaymentAdviceDate(String adviceId){
		String hql="select oughtpayDate from PayAdviceRecordEO where id="+adviceId;
		Object obj = payRecordDao.findUnique(hql);
		if(obj!=null)
			return obj.toString();
		else
			return "";
	}
	
	/**
	 * 打印催缴单
	 * @param areaId
	 * @param edificeId
	 * @param cellId
	 * @param adviceId 催缴单记录表的id
	 * @return
	 */
	public Map getPrintPaymentAdvice(String areaId,String edificeId,String cellId,String adviceId,String oughtpayDate){
		//催缴单次数增加1
		String hql ="update PayAdviceRecordEO set advicecount=advicecount+1,oughtpayDate='"+oughtpayDate+"' where id="+adviceId;
		payRecordDao.createQuery(hql).executeUpdate();
		
		return getPrintPaymentAdvice(areaId,edificeId,cellId);
	}
	
	/**
	 * 打印费用交费单
	 * @param areaId
	 * @param edificeId
	 * @param cellId
	 * @return List集合元素objects[9]
	 */
	public Map getPrintPaymentAdvice(String areaId,String edificeId,String cellId){
		String where=" and areaId="+areaId+" and house.edificeId='"+edificeId+"'";
		if(cellId!=null&&!cellId.equals(""))
			where+=" and house.cell="+cellId;
		
		String hql=" select house.houseAddress,ownerName,chargeName,sum(userNum),max(chargePrice),sum(arrearageMoney)," +
				" min(recordMonth),max(recordMonth),min(owner.inDate) from ChargeHouseDetailEO where arrearageMoney>0 "+where+" group by house.houseAddress,ownerName,chargeName";
		
		List _ls = payRecordDao.find(hql);
		
		Map<String,List> map = new HashMap<String,List>();
		for(int i=0;i<_ls.size();i++){
			Object[] objs = (Object[])_ls.get(i);
			String houseAddress = (String)objs[0];
			if(map.get(houseAddress)==null){
				map.put(houseAddress, new ArrayList());
			}
			List ls = map.get(houseAddress);
			ls.add(objs);
		}
		return map;
	}

	/**
	 * 催缴次数记录表
	 * @param areaId
	 * @return
	 */
	public List getPrintPaymentAdviceInitList(String areaId) {
		String hql = "from PayAdviceRecordEO where areaId="+areaId;
		
		return payRecordDao.find(hql);
	}
}