package com.acec.wgt.service.chg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.orm.hibernate.SimpleHibernateDao;
import com.acec.core.utils.ArithUtils;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.CarportLeaseEO;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataDAO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.AssignDAO;
import com.acec.wgt.models.chg.ChargeAssignEO;
import com.acec.wgt.models.chg.ChargeHouseDetailDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargePayDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;
import com.acec.wgt.service.basedata.CellManager;
//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AssignManager{
	
	private final Logger logger = LoggerFactory.getLogger(AssignManager.class);
	
	
	@Autowired
	private AssignDAO assignDao;
	@Autowired
	private ChargeHouseDetailDAO chargeHouseDetailDao;
	@Autowired
	private CellManager cellManager;

	@Autowired
	private HouseDAO houseDao;
	@Autowired
	private ChargeBasedataDAO chargeBasedataDao;
	@Autowired
	private PayRecordManager payRecordManager;
	
	private HibernateDao<ChargePayDetailEO, Integer> chargePayDetailDao;
	
	private HibernateDao<ChargeHouseDetailEO, Integer> payDao;
	private SimpleHibernateDao<ChargeUserPayRecordEO, Integer> payRecordDao;	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		payRecordDao = new SimpleHibernateDao<ChargeUserPayRecordEO, Integer>(sessionFactory, ChargeUserPayRecordEO.class);
		payDao = new HibernateDao<ChargeHouseDetailEO, Integer>(sessionFactory, ChargeHouseDetailEO.class);
		chargePayDetailDao = new HibernateDao<ChargePayDetailEO, Integer>(sessionFactory, ChargePayDetailEO.class);
	}
	
	public List getAssignList1(){
		List list=null;
		return list;
	}

	public List getAssignList(){
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		return assignDao.find("select distinct chargeName from ChargeAssignEO where areaId in ("+areaIds+")");
	}
	
	
	public void saveAssign(ChargeAssignEO entity){
		assignDao.save(entity);
	}
	
	
	
	
	/**
	 * 保存缴费记录
	 * @param entity
	 */
	public void saveAll(ChargeUserPayRecordEO entity){
		payRecordDao.save(entity);
	}
	
	
	/**
	 * 指定到项目收费
	 * @param ch
	 * @param beginTime
	 * @param endTime
	 */
	public void saveChargeHouseDetail(ChargeUserPayRecordEO ch,String recordMonth){
		try{
	
				
			ChargeHouseDetailEO chargeHouseDetail=new ChargeHouseDetailEO();
			
			chargeHouseDetail.setAreaId(ch.getAreaId());
			chargeHouseDetail.setLastRecord(0);
			chargeHouseDetail.setNowRecord(0);
			chargeHouseDetail.setUserNum(0);
			chargeHouseDetail.setChargeName(ch.getChargeName());
			chargeHouseDetail.setOughtMoney(ch.getFactMoney());
			chargeHouseDetail.setArrearageMoney(0);
			chargeHouseDetail.setFactMoney(ch.getFactMoney());
			chargeHouseDetail.setOwnerName(ch.getPayName());
			chargeHouseDetail.setGatheringDate(new java.sql.Date(ch.getGatheringTime().getTime()));
			
			if(StringUtils.isEmpty(recordMonth))
				chargeHouseDetail.setRecordMonth(chargeHouseDetail.getGatheringDate());
			else
				chargeHouseDetail.setRecordMonth(java.sql.Date.valueOf(recordMonth));
			
			chargeHouseDetail.setChargeType("其他类");
			chargeHouseDetail.setPrivilegeReason(ch.getReason());//备注说明

			saveAll(ch);
				
				
			chargeHouseDetail.setPayId(ch.getId().toString());
			chargeHouseDetailDao.save(chargeHouseDetail);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 指定到户收费
	 * @param ch
	 * @param beginTime
	 * @param endTime
	 */
	@Deprecated
	public void saveChargeHouseDetailvoid2(ChargeUserPayRecordEO ch,String beginTime,String endTime){
		try{
			//相差月数
			int mon=Utils.getMonth(Utils.strToDate(beginTime+"-01"), Utils.strToDate(endTime+"-01"));
			
			Calendar calendar = Calendar.getInstance();//日历对象
			String [] str=beginTime.split("-");
			
			//平均每月实收金额
			Float avgMoney=ArithUtils.div(ch.getFactMoney(), mon, 2);
			for(int i=0;i<mon;i++){
				calendar.set(Integer.parseInt(str[0]),Integer.parseInt(str[1]),1);
				calendar.add(Calendar.MONTH, i);
				String newRecordMonth=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);		
				
				ChargeHouseDetailEO chargeHouseDetail=new ChargeHouseDetailEO();
				chargeHouseDetail.setRecordMonth(new java.sql.Date(Utils.strToDate(newRecordMonth).getTime()));

				chargeHouseDetail.setAreaId(ch.getAreaId());

				HouseEO house =houseDao.get(ch.getHouse().getId());
				
				if(house.getOwnerName()!=null)
					chargeHouseDetail.setOwnerName(house.getOwnerName());
				else
					chargeHouseDetail.setOwnerName("");
				
				chargeHouseDetail.setHouse(house);
				chargeHouseDetail.setLastRecord(0);
				chargeHouseDetail.setNowRecord(0);	
				chargeHouseDetail.setUserNum(0);
				chargeHouseDetail.setChargeName(ch.getChargeName());
				chargeHouseDetail.setOughtMoney(avgMoney);
				chargeHouseDetail.setArrearageMoney(0);
				chargeHouseDetail.setFactMoney(avgMoney);
				chargeHouseDetail.setHouseType(house.getHouseType());
				chargeHouseDetail.setGatheringDate(new java.sql.Date(ch.getGatheringTime().getTime()));
				
				chargeHouseDetail.setChargeType("其他类");
				
				chargeHouseDetailDao.save(chargeHouseDetail);
				ch.setFactMoney(avgMoney);
				ch.setChargeHouseDetailId(chargeHouseDetail.getId());
				saveAll(ch);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 按房间指定项目收费
	 * @param houseId
	 * @param chargeId
	 * @param factMoney
	 * @param bh
	 * @param userName
	 * @param remark
	 * @param recordMonth
	 * @param gatheringTime
	 * @throws Exception
	 */
	public void saveChargeHouseDetailvoid3(String houseId,	Integer chargeId,String factMoney,
			String bh, String userName,String remark,String recordMonth,String gatheringTime) throws Exception {
		
		HouseEO house=cellManager.getHouse(houseId);
		if(house==null)
			throw new RuntimeException("您还没有选择房间！");
		
		ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(chargeId);
		if (null == ch)
		    throw new RuntimeException("您还没有设置收费项目！");
		

		ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

		chargeHouseDetail.setAreaId(ch.getAreaId());
		chargeHouseDetail.setHouseType(house.getHouseType());
		chargeHouseDetail.setHouse(house);
		chargeHouseDetail.setGatheringDate(java.sql.Date.valueOf(gatheringTime));
		if(StringUtils.isEmpty(recordMonth))
			chargeHouseDetail.setRecordMonth(chargeHouseDetail.getGatheringDate());
		else
			chargeHouseDetail.setRecordMonth(java.sql.Date.valueOf(recordMonth));
		
		chargeHouseDetail.setOwnerName(house.getOwnerName());
		chargeHouseDetail.setLastRecord(0);
		chargeHouseDetail.setNowRecord(0);
		chargeHouseDetail.setUserNum(0);
		chargeHouseDetail.setChargeUnit(ch.getPriceUnit());
		chargeHouseDetail.setChargeType(ch.getChargeType());
		chargeHouseDetail.setChargeName(ch.getChargeName());
		chargeHouseDetail.setChargePrice(ch.getPriceValue());
		chargeHouseDetail.setOughtMoney(Float.parseFloat(factMoney));
		chargeHouseDetail.setArrearageMoney(0);
		chargeHouseDetail.setFactMoney(Float.parseFloat(factMoney));
		chargeHouseDetail.setChargeId(ch.getId());
		chargeHouseDetail.setPrivilegeReason(remark);
		

	

		// 保存缴费单表
		ChargeUserPayRecordEO cupe = new ChargeUserPayRecordEO();

		cupe.setBh(bh);
		cupe.setUserName(userName);// 录入人
		cupe.setChargeName(chargeHouseDetail.getChargeName());// 缴费项目名称
		cupe.setPayType("收款");// 支付类型 收款、优惠、预交、预扣,滞纳金
		cupe.setReason("");// 具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
		cupe.setFactMoney(chargeHouseDetail.getFactMoney());// 实际缴费金额

		HouseEO houseEO = new HouseEO(houseId);
		cupe.setHouse(houseEO);// 关联房间
		cupe.setPayName(chargeHouseDetail.getOwnerName());// 保存交款人
		cupe.setGatheringTime(chargeHouseDetail.getGatheringDate());
		cupe.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
		cupe.setChargeHouseDetailId(chargeHouseDetail.getId());
		cupe.setAreaId(chargeHouseDetail.getAreaId());// 小区id
		cupe.setChargeId(chargeHouseDetail.getChargeId());// 收费项id
		cupe.setRecordMonth(chargeHouseDetail.getRecordMonth());

		payRecordManager.savePayRecordMoney(cupe);

		// 更新house_detail缴费单id
		int payId = cupe.getId();
		chargeHouseDetail.setPayId(String.valueOf(payId));
		payDao.save(chargeHouseDetail);// 保存每月费用表

		// 保存缴费明细表
		ChargePayDetailEO b = new ChargePayDetailEO();
		b.setArea_id(chargeHouseDetail.getAreaId());
		b.setCharge_id(chargeHouseDetail.getChargeId());
		b.setChargeHouseDetailId(chargeHouseDetail.getId());
		b.setGathering_time(chargeHouseDetail.getGatheringDate());
		b.setHouseId(houseId);
		b.setPay_money(chargeHouseDetail.getFactMoney());
		b.setPay_type("收款");
		b.setPrivilege_reason("");
		b.setRecord_month(chargeHouseDetail.getRecordMonth());

		chargePayDetailDao.save(b);
	}
	 
	/**
	 * 按房间指定项目收费
	 * @param houseId
	 * @param chargeId
	 * @param factMoney
	 * @param bh
	 * @param userName
	 * @param remark
	 * @param recordMonth
	 * @param gatheringTime
	 * @throws Exception
	 */
	public void saveChargeAreaDetailvoid3(Integer areaId,String ownerName,Integer chargeId,String factMoney,
			String bh, String userName,String remark,String recordMonth,String gatheringTime) throws Exception {
		
		
		ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(chargeId);
		if (null == ch)
		    throw new RuntimeException("您还没有设置收费项目！");
		

		ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

		chargeHouseDetail.setAreaId(ch.getAreaId());
		chargeHouseDetail.setHouseType("");
		chargeHouseDetail.setAreaId(areaId);
		chargeHouseDetail.setGatheringDate(java.sql.Date.valueOf(gatheringTime));
		if(StringUtils.isEmpty(recordMonth))
			chargeHouseDetail.setRecordMonth(chargeHouseDetail.getGatheringDate());
		else
			chargeHouseDetail.setRecordMonth(java.sql.Date.valueOf(recordMonth));
		
		chargeHouseDetail.setOwnerName(ownerName);
		chargeHouseDetail.setLastRecord(0);
		chargeHouseDetail.setNowRecord(0);
		chargeHouseDetail.setUserNum(0);
		chargeHouseDetail.setChargeUnit(ch.getPriceUnit());
		chargeHouseDetail.setChargeType(ch.getChargeType());
		chargeHouseDetail.setChargeName(ch.getChargeName());
		chargeHouseDetail.setChargePrice(ch.getPriceValue());
		chargeHouseDetail.setOughtMoney(Float.parseFloat(factMoney));
		chargeHouseDetail.setArrearageMoney(0);
		chargeHouseDetail.setFactMoney(Float.parseFloat(factMoney));
		chargeHouseDetail.setChargeId(ch.getId());
		chargeHouseDetail.setPrivilegeReason(remark);
		

	

		// 保存缴费单表
		ChargeUserPayRecordEO cupe = new ChargeUserPayRecordEO();

		cupe.setBh(bh);
		cupe.setUserName(userName);// 录入人
		cupe.setChargeName(chargeHouseDetail.getChargeName());// 缴费项目名称
		cupe.setPayType("收款");// 支付类型 收款、优惠、预交、预扣,滞纳金
		cupe.setReason("");// 具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
		cupe.setFactMoney(chargeHouseDetail.getFactMoney());// 实际缴费金额

		
		cupe.setAreaId(areaId);// 关联小区
		cupe.setPayName(chargeHouseDetail.getOwnerName());// 保存交款人
		cupe.setGatheringTime(chargeHouseDetail.getGatheringDate());
		cupe.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
		cupe.setChargeHouseDetailId(chargeHouseDetail.getId());
		cupe.setAreaId(chargeHouseDetail.getAreaId());// 小区id
		cupe.setChargeId(chargeHouseDetail.getChargeId());// 收费项id
		cupe.setRecordMonth(chargeHouseDetail.getRecordMonth());

		payRecordManager.savePayRecordMoney(cupe);

		// 更新house_detail缴费单id
		int payId = cupe.getId();
		chargeHouseDetail.setPayId(String.valueOf(payId));
		payDao.save(chargeHouseDetail);// 保存每月费用表

		// 保存缴费明细表
		ChargePayDetailEO b = new ChargePayDetailEO();
		b.setArea_id(chargeHouseDetail.getAreaId());
		b.setCharge_id(chargeHouseDetail.getChargeId());
		b.setChargeHouseDetailId(chargeHouseDetail.getId());
		b.setGathering_time(chargeHouseDetail.getGatheringDate());
		b.setArea_id(areaId);
		b.setPay_money(chargeHouseDetail.getFactMoney());
		b.setPay_type("收款");
		b.setPrivilege_reason("");
		b.setRecord_month(chargeHouseDetail.getRecordMonth());

		chargePayDetailDao.save(b);
	}
}