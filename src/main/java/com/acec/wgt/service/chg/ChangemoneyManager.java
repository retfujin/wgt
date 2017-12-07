package com.acec.wgt.service.chg;


import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.StringUtil;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;


//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ChangemoneyManager {
	
	private final Logger logger = LoggerFactory.getLogger(ChangemoneyManager.class);

	private HibernateDao<ChargeHouseDetailEO, Integer> payDao;
	private HibernateDao<ChargeUserPayRecordEO, Integer> userPayRecordDao;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		payDao = new HibernateDao<ChargeHouseDetailEO, Integer>(sessionFactory, ChargeHouseDetailEO.class);
		userPayRecordDao = new HibernateDao<ChargeUserPayRecordEO, Integer>(sessionFactory, ChargeUserPayRecordEO.class);
	}

	/**
	 * 调整应收  取列表(不包括已经有收费记录的)
	 * @param page
	 * @param areaId
	 * @param houseId
	 * @param chargeId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Page getInfo(Page page, String areaId, String houseId,
			String chargeId, String beginTime, String endTime,String ownerName,String id) {
		
		String where = " where areaId in ("+Struts2Utils.getSession().getAttribute("areaIds")+") and arrearageMoney=oughtMoney";
		if(!StringUtils.isEmpty(id))
			where +=" and id="+id;
		if(!"".equals(areaId))
			where += " and areaId="+areaId;
		if(!"".equals(houseId))
			where += " and house.id like '"+houseId+"%'";
		if(!"".equals(chargeId))
			where += " and chargeId="+chargeId;
		if(null != beginTime && !"".equals(beginTime))
			where +=" and date_format(recordMonth,'%Y-%m')>='"+beginTime.substring(0,7)+"'";
		if(null != endTime && !"".equals(endTime))
			where +=" and date_format(recordMonth,'%Y-%m')<= '"+endTime.substring(0,7)+"'";
		if(null != ownerName && !"".equals(ownerName))
			where +=" and ownerName like '%"+ownerName+"%' ";
		page = payDao.find(page, "from ChargeHouseDetailEO "+where+" order by house.id,recordMonth", null);
		
		
		return page;
	}

	/**
	 * 根据小区取下面所有的收费项目
	 * @param areaId
	 * @return
	 */
	public List getChargeId(int areaId) {
		return payDao.find("from ChargeBasedataEO where isUser='使用' and areaId = '"+areaId+"'");
	}
	
	
	/**
	 * 清除收费信息
	 * @param pid  ChargeUserPayRecordEO的id
	 */
	public void deleteMoney(Integer pid) {
	
		
		
		//删除paydetail表
		payDao.createQuery(" delete from ChargePayDetailEO  where chargeHouseDetailId in(select id from ChargeHouseDetailEO where payId="+pid+")").executeUpdate();
		//清除实收
		payDao.createQuery(" update ChargeHouseDetailEO set arrearageMoney=oughtMoney,gatheringDate=null,privilegeReason='',payId='',factMoney=0,privilegeMoney=0 where payId="+pid).executeUpdate();
		//删除多收的尾数
		payDao.createQuery(" delete from ChargeHouseDetailEO  where oughtMoney=0 and payId="+pid).executeUpdate();
		//删除缴费单表
		payDao.createQuery("delete from ChargeUserPayRecordEO where id=?", pid).executeUpdate();
	}

	/**
	 * 更新应收
	 * @param id
	 * @param oughtMoney
	 */
	public void updateChargeOughtMoney(String[] ids, String[] oughtMoneys,String[] ownerNames) {
		
		for(int i=0;i<ids.length;i++){
			String oughtMoney = oughtMoneys[i];
			if(Utils.isDecimal(oughtMoney) == false){
				throw new RuntimeException("应收金额不是数值");
			}
			//如果应收为0，删除只
			if(Float.valueOf(oughtMoney)<=0)
				payDao.createQuery("delete from  ChargeHouseDetailEO where id="+ids[i]).executeUpdate();
			else
				payDao.createQuery("update ChargeHouseDetailEO set oughtMoney = "+oughtMoney+",arrearageMoney="+oughtMoney+",ownerName='"+ownerNames[i]+"' where id="+ids[i]).executeUpdate();
		}
		
	}
	
	/**
	 * 更改缴费日期
	 * @param pid
	 * @param rq
	 * @throws Exception 
	 */
	public void chargeDate(int pid, String str_rq) throws Exception {
		
		java.util.Date rq = Utils.strToDate(str_rq);
		//缴费单表
		payDao.createQuery("update from ChargeUserPayRecordEO set gatheringTime=?  where id=?",rq,pid).executeUpdate();
		//每月缴费表
		payDao.createQuery(" update ChargeHouseDetailEO set gatheringDate=? where payId=?",rq,String.valueOf(pid)).executeUpdate();
		//缴费详情表
	//	payDao.createQuery(" update from ChargePayDetailEO set gathering_time=?  where chargeHouseDetailId in(select id from ChargeHouseDetailEO where payId=?)",rq,String.valueOf(pid)).executeUpdate();
		
		List ls = payDao.createQuery("select id from ChargeHouseDetailEO where payId=?", String.valueOf(pid)).list();
		String ids="";
		for(int i=0;i<ls.size();i++){
			ids+=","+ls.get(i).toString();
		}
		if(ids.length()>1){
			payDao.createQuery(" update from ChargePayDetailEO set gathering_time=?  where chargeHouseDetailId in("+ids.substring(1)+")",rq).executeUpdate();
			
			
		}
		
	}
	

}