package com.acec.wgt.service.chargemanger.generate;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.acec.core.utils.ExcelInOut;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;

import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO;

import com.acec.wgt.models.chargemanager.ChargeBasedataDAO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordDAO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;

//Spring Bean???.
@Service
//????????????????.
@Transactional
public class ManualGenerateManager {

	private final Logger logger = LoggerFactory.getLogger(ManualGenerateManager.class);


	@Autowired
	private ChargeBasedataDAO chargeBasedataDao;
	@Autowired
	private HouseDAO houseDao;

	@Autowired
	private ChargeHouseDetailDAO chdDao;
	@Autowired
	private ChargeUserPayRecordDAO chgPayRecordDao;

	
	public void writeExc(String areaId) {
		List<String[]> retList =new ArrayList<String[]>();
		String[] temp={"????(?1001)","????","????","????(??/??)","?????(?2010-04)","???","??"};
		retList.add(temp);
		
		List<HouseEO> _ls = houseDao.getAllByHouseId(areaId,areaId+"-");
		for(int i=0;i<_ls.size();i++){
			HouseEO e = _ls.get(i);
			temp= new String[6];
			temp[0] = areaId;
			temp[2] = e.getId();
			temp[3] = e.getHouseType();
			temp[4]="";
			temp[5]="";
			retList.add(temp);
		}
	
		ExcelInOut eIO=new ExcelInOut();
		Boolean ret=eIO.writeExc(retList);
		if(!ret)
			throw new RuntimeException("????");
	}
	
	public void saveForExc(File theFile) {
		ExcelInOut eIO = new ExcelInOut();
		List<String[]> list = eIO.readExc(theFile);
//		String[] temp={"????(?1001)","????","????","????(??/??)","?????(?2010-04)","???","??"};
	
		for (int i = 0; i < list.size(); i++) {
			
			ChargeHouseDetailEO ch=new ChargeHouseDetailEO();
			ChargeBasedataEO chargeBasedata=null;
			
			
			//????
			if (null != list.get(i)[0] && !"".equals(list.get(i)[0]))	
				ch.setAreaId(Integer.parseInt(list.get(i)[0]));
			else
				throw new RuntimeException("?"+(i+1)+"??????id???");
			//????
			if (null != list.get(i)[1] && !"".equals(list.get(i)[1])) {	
				chargeBasedata=(ChargeBasedataEO)chargeBasedataDao.getChargeBasedataByChargeId(Integer.parseInt(list.get(i)[1]));
				if(chargeBasedata==null)
					throw new RuntimeException("?"+(i+2)+"????????"+list.get(i)[1].toString()+"??????????");
				else
					ch.setChargeId(Integer.parseInt(list.get(i)[1]));
			}else
				throw new RuntimeException("?"+(i+2)+"?????????");
			//????
			HouseEO house=null;
			if(null != list.get(i)[2] && !"".equals(list.get(i)[2])){
				house=houseDao.getHouse(list.get(i)[2].toString());
				if(house==null)
					throw new RuntimeException("?"+(i+3)+"????????"+list.get(i)[2].toString()+"??????????");
				else
					ch.setHouse(house);
			}
			
			//????
			if(house.getOwnerName()!=null)
				ch.setOwnerName(house.getOwnerName());
			else
				ch.setOwnerName("");
			
			
			//????
			if (null != list.get(i)[3] && !"".equals(list.get(i)[3]))	
				ch.setHouseType(list.get(i)[3].toString());
			else
				throw new RuntimeException("?"+(i+5)+"???????????");
			//?????
			try{
				if(null != list.get(i)[4] && !"".equals(list.get(i)[4]))
					ch.setRecordMonth(java.sql.Date.valueOf(list.get(i)[4].toString()+"-01"));
				else
					throw new RuntimeException("?"+(i+5)+"????????????");
			}catch (Exception e) {
				logger.debug("????????");
			}
			//???
			try{
				if(null != list.get(i)[5] && !"".equals(list.get(i)[5]))
					ch.setOughtMoney(Float.valueOf(list.get(i)[5]));
				else
					throw new RuntimeException("?"+(i+6)+"??????????");
			}catch (Exception e) {
				logger.debug("??????");
			}
			ch.setChargeName(chargeBasedata.getChargeName());

			ch.setChargeUnit("0");
			ch.setChargeType(chargeBasedata.getChargeType());
			ch.setChargeUnit("?");
			ch.setLastRecord(0);
			ch.setNowRecord(0);

			ch.setFactMoney(Float.valueOf("0"));
			ch.setArrearageMoney(Float.valueOf(list.get(i)[5].toString()));
			ch.setUserNum(0);
			
			ch.setPrivilegeReason(list.get(i)[5]==null?"":list.get(i)[5]);
			
			chdDao.save(ch);
			if(i%20==0){
				chdDao.getSession().flush();
				chdDao.getSession().clear();
			}
		}
	}

	/**
	 * ??????????
	 * @param areaId
	 */
	public void writeExcOwn(String areaId) {
		List<String[]> retList =new ArrayList<String[]>();
		String[] temp={"????(?1001)","????","????","???????","????????(??2008-01)","????????(??2008-12)","??????(??2008-03-11)","????????(??2008-03)"};
		retList.add(temp);
		
		List<HouseEO> _ls = houseDao.getAllByHouseId(areaId,areaId+"-");
		for(int i=0;i<_ls.size();i++){
			HouseEO e = _ls.get(i);
			temp= new String[9];
			temp[0] = areaId;
			temp[1] = e.getId();
			retList.add(temp);
		}
		ExcelInOut eIO=new ExcelInOut();
		Boolean ret=eIO.writeExc(retList);
		if(!ret)
			throw new RuntimeException("????");
	}
	
	/**
	 * ????????
	 * @param theFile
	 */
	public String saveForExcOwn(File theFile) {
				
		ExcelInOut eIO = new ExcelInOut();
		List<String[]> list = eIO.readExc(theFile);
//		String[] temp={"????(?1001)","????","????","???????","????????(??2008-01)","????????(??2008-12)","??????(??2008-03-11)","????????(??2008-03)"};
		ChargeBasedataEO chargeBasedata=new ChargeBasedataEO();
		
		for (int i = 0; i < list.size(); i++) {
			ChargeHouseDetailEO ch=new ChargeHouseDetailEO();
		//	if(i==0){
				if (null != list.get(i)[2] && !"".equals(list.get(i)[2])) {	
					chargeBasedata=chargeBasedataDao.getChargeBasedataByChargeId(Integer.parseInt(list.get(i)[2]));
				}
				else{
					throw new RuntimeException("?"+(i+2)+"????????????");
				}
		//	}
			//????
			if (null != list.get(i)[0] && !"".equals(list.get(i)[0]))	
				ch.setAreaId(Integer.parseInt(list.get(i)[0]));
			else
				throw new RuntimeException("?"+(i+2)+"???????????");
			//????
			
			ch.setChargeId(Integer.parseInt(list.get(i)[2]));
				
			//????
			HouseEO house=null;
			if(null != list.get(i)[1] && !"".equals(list.get(i)[1])){
				house=houseDao.getHouse(list.get(i)[1].toString());
				if(house==null)
					throw new RuntimeException("?"+(i+2)+"????????"+list.get(i)[1].toString()+"??????????");
				else{
					ch.setHouse(house);
					ch.setHouseType(house.getHouseType());
				}
			}
			//????
			if(house.getOwnerName()==null)
				ch.setOwnerName("");
			else
				ch.setOwnerName(house.getOwnerName());
			
			
			ch.setChargePrice(chargeBasedata.getPriceValue());
			ch.setChargeName(chargeBasedata.getChargeName());
			ch.setChargeUnit(chargeBasedata.getPriceUnit());
			ch.setChargeType(chargeBasedata.getChargeType());
			ch.setLastRecord(0);
			ch.setNowRecord(0);
			
			Float money = 0f;
			
			ch.setArrearageMoney(0f);
			ch.setUserNum(0);
			
			//???+???
			try{
				if(null != list.get(i)[3] && Utils.isDecimal(list.get(i)[3])){
					money = Float.valueOf(list.get(i)[3].toString());
					ch.setOughtMoney(money);
					ch.setFactMoney(money);
				}else
					throw new RuntimeException("?"+(i+2)+"??????????");
			}catch (Exception e) {
				logger.debug("??????");
			}
			
			Date beginRecordMonth ;//
			Date beginRecordMonth1 = null;//
			Date endRecordMonth;//
			Date gatheringDate=null;//
			Date endedDate;//
			Date endedDate1 = null;
			try{
				if(null != list.get(i)[4] && !"".equals(list.get(i)[4])){
					String[] beginRecordMonth_spit = list.get(i)[4].toString().split("-");
					if(beginRecordMonth_spit.length==3){
						beginRecordMonth = Utils.strToDate(beginRecordMonth_spit[0]+"-"+beginRecordMonth_spit[1]+"-01");
						beginRecordMonth1 = Utils.strToDate(list.get(i)[4].toString());
					}else
						beginRecordMonth = Utils.strToDate(list.get(i)[4].toString()+"-01");
					
				}else
					throw new RuntimeException("?"+(i+2)+"???????????????");
				
				if(null != list.get(i)[5] && !"".equals(list.get(i)[5]))
				    endRecordMonth = Utils.strToDate(list.get(i)[5].toString()+"-01");
				else
					throw new RuntimeException("?"+(i+2)+"???????????????");
				
				if(null != list.get(i)[6] && !"".equals(list.get(i)[6])&&list.get(i)[6].length()>7)
					gatheringDate = Utils.strToDate(list.get(i)[6].toString());
				//else
				//	gatheringDate = endRecordMonth;
				
				
				if(null != list.get(i)[7] && !"".equals(list.get(i)[7])){
					if(list.get(i)[7].toString().split("-").length==3){
						endedDate =  Utils.strToDate(list.get(i)[7].toString()+"-01");
						endedDate1 = Utils.strToDate(list.get(i)[7]);
					}else
						endedDate = Utils.strToDate(list.get(i)[7].toString()+"-01");
				}else
					throw new RuntimeException("?"+(i+2)+"???????????????");
				
			}catch(Exception ex){
				ex.printStackTrace();
				throw new RuntimeException("?"+(i+2)+"????"+ex.getMessage());
			}
			
			

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(beginRecordMonth);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(endRecordMonth);
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(endedDate);
			
			int m2 = Utils.getMonth(beginRecordMonth, endRecordMonth);

			for(int k= 0;k<=m2;k++){
				ChargeHouseDetailEO chgDetail = new ChargeHouseDetailEO();
				chgDetail.setAreaId(ch.getAreaId());
				chgDetail.setChargeId(ch.getChargeId());
				chgDetail.setHouse(ch.getHouse());
				chgDetail.setHouseType(ch.getHouseType());
				chgDetail.setChargePrice(ch.getChargePrice());
				chgDetail.setOwnerName(ch.getOwnerName());
				chgDetail.setChargeName(ch.getChargeName());
				chgDetail.setChargeUnit(ch.getChargeUnit());
				chgDetail.setChargeType(ch.getChargeType());
				chgDetail.setNowRecord(0);
				chgDetail.setLastRecord(0);
				chgDetail.setArrearageMoney(0f);
				chgDetail.setUserNum(0);
				
				if(k==0&&beginRecordMonth1!=null){//?????????????,???????
					
					Calendar cal1_1 = Calendar.getInstance();
					cal1_1.setTime(beginRecordMonth1);
					int days = cal1_1.get(Calendar.DAY_OF_MONTH);
					float outghtMoney = ((30-days+1)*ch.getOughtMoney())/30;
					chgDetail.setOughtMoney(outghtMoney);
					chgDetail.setArrearageMoney(outghtMoney);
					
					chgDetail.setPrivilegeReason(days+"?--"+"??");
					chgDetail.setRecordMonth(new java.sql.Date(cal1.getTimeInMillis()));
					chdDao.save(chgDetail);
					
					
					
				}else if(k==m2&&endedDate1!=null){//??????????,???????
					
					Calendar cal1_1 = Calendar.getInstance();
					cal1_1.setTime(endedDate1);
					int days = cal1_1.get(Calendar.DAY_OF_MONTH);
					float oughtMoney = ((30-days+1)*ch.getOughtMoney())/30;
					float oughtMoney1 = ch.getOughtMoney()-oughtMoney;//????
					chgDetail.setOughtMoney(oughtMoney);
					chgDetail.setArrearageMoney(oughtMoney);
					chgDetail.setPrivilegeReason(days+"?--"+"??");
					chgDetail.setRecordMonth(new java.sql.Date(cal1.getTimeInMillis()));
					
					
					ChargeHouseDetailEO target = new ChargeHouseDetailEO();
					BeanUtils.copyProperties(chgDetail, target);
					target.setOughtMoney(oughtMoney1);
					target.setFactMoney(oughtMoney1);
					target.setArrearageMoney(0F);
					target.setPrivilegeReason("1?--"+(days-1)+"?");
					
					chdDao.save(target);
					chdDao.save(chgDetail);
					
				}
				else{
					chgDetail.setOughtMoney(ch.getOughtMoney());
					chgDetail.setArrearageMoney(ch.getOughtMoney());
					
					chgDetail.setRecordMonth(new java.sql.Date(cal1.getTimeInMillis()));
					if(cal1.compareTo(cal3)<0){
						chgDetail.setFactMoney(chgDetail.getOughtMoney());
						chgDetail.setArrearageMoney(0);
						if(gatheringDate!=null)
							chgDetail.setGatheringDate(new java.sql.Date(gatheringDate.getTime()));
						
					}
					chdDao.save(chgDetail);
					
				}
				
				
				
				
				
				
				
				
				cal1.add(Calendar.MONTH, 1);
				
				if(i%20==0){
					chdDao.getSession().flush();
					chdDao.getSession().clear();
				}
			}
		}

		return "";
	}
	
	
	/**
	 * ?????
	 * ???????????
	 * @param id
	 */
	@Deprecated
	public void insertPayRecord(String id){
		String userName = (String)Struts2Utils.getSession().getAttribute("userName");
		String hql ="";
		if(!"".equals(id)){
			hql ="from ChargeHouseDetailEO where id>"+id+" and factMoney>0";
		}else{
			hql ="from ChargeHouseDetailEO where factMoney>0";
			
		}
		List<ChargeHouseDetailEO> list = chdDao.find(hql);
		int i=0;
		if(!list.isEmpty())
			for(ChargeHouseDetailEO ch : list){
				ChargeUserPayRecordEO pr = new ChargeUserPayRecordEO();
				pr.setAreaId(ch.getAreaId());
				pr.setBanci("?");
				pr.setBh("");
				pr.setChargeHouseDetailId(ch.getId());
				pr.setChargeName(ch.getChargeName());
				pr.setFactMoney(ch.getFactMoney());
				pr.setGatheringTime(ch.getGatheringDate());
				pr.setHouse(ch.getHouse());
				pr.setIsBack(false);
				pr.setPayName(ch.getOwnerName());
				pr.setPayType("??");
				pr.setReason("??");
				pr.setRecordMonth(ch.getRecordMonth());
				pr.setReturnMoney(0f);
				pr.setSubmitTime(ch.getGatheringDate());
				pr.setTaxNum("");
				pr.setType("??");
				pr.setUserName(userName);
				pr.setChargeId(ch.getChargeId());
				chgPayRecordDao.save(pr);
				i++;
				if(i%20==0){
					chgPayRecordDao.getSession().flush();
					chgPayRecordDao.getSession().clear();
				}
			}
	}


}