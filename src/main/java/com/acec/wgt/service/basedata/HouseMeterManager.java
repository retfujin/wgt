package com.acec.wgt.service.basedata;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.ExcelInOut;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.baseData.HouseMeterDAO;
import com.acec.wgt.models.baseData.HouseMeterEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordEO;


//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class HouseMeterManager {
	

	@Autowired
	private HouseMeterDAO houseMeterDao;
	@Autowired
	private CellManager cellManager;
	
	
	private HibernateDao<OwnerMeterRecordEO, Integer> owDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		owDao = new HibernateDao<OwnerMeterRecordEO, Integer>(sessionFactory, OwnerMeterRecordEO.class);
	}

	public HouseMeterEO getHouseMeterByHouseId(String houseId, String meterType) {
		
		List<HouseMeterEO> l = houseMeterDao.getHouseMeterByHouseId(houseId, meterType);
		if(l.isEmpty())
			return null;
		else
		return l.get(0);
	}

	
	
	/**
	 * 客户表更换历史
	 * @param page
	 * @param parseInt
	 * @param parseInt2
	 * @param cell
	 * @param houseUID
	 * @param meterType
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Page oldmeterlist(Page page, int areaId,
			String houseId, String meterType) {

		StringBuffer where = new StringBuffer(" and isNow=false");
		if(areaId >0 )
			where.append(" and house.areaId in ("+areaId+")");
		if(!"".equals(houseId))
			where.append(" and house.id like '"+houseId+"%'");
		if(!"".equals(meterType))
			where.append(" and meterType='"+meterType+"'");
	
		return houseMeterDao.getAllHouseMeter(page,where.toString());
	}


	/**
	 * 删除房间表基础资料
	 * @param id
	 */
	public void delHouseMeter(String id){
		houseMeterDao.delete(Integer.parseInt(id));
	}
	
	
	/**
	 * 导出空白的房间表的基础资料模板
	 * @param areaId
	 */
	public void writehousemeterExcaa(String areaId){
		List<String[]> retList = new ArrayList<String[]>();
		
		String[] temp={"房间编号(如：1001-01-101)","表类型(如水表、热水表、电表)","期初度数","未次度数"};	
		retList.add(temp);
//		List tmpList = houseMeterDao.find("select house.id,meterType,initNum,nowRecord,ownerName from HouseMeterEO where house.id like '"+areaId+"-%'");
//		retList.addAll(tmpList);

		ExcelInOut eIO = new ExcelInOut();
		Boolean ret = eIO.writeExc(retList);
		if (!ret)
			throw new RuntimeException("下载失败");
	}
	
	/**
	 * 批量保存房间表基础资料
	 * @param theFile
	 */
	public void savehousemeterForExc(File theFile) {
		ExcelInOut eIO = new ExcelInOut();
		List<String[]> list = eIO.readExc(theFile);
//		String[] temp={"房间编号(如：1001-1-101)","表类型(如水表、电表)","期初度数","未次度数"};
		for(int i=0;i<list.size();i++){
			HouseMeterEO housemeter=new HouseMeterEO();
			HouseEO house = null;
			if (null != list.get(i)[0] && !"".equals(list.get(i)[0])){
				house=cellManager.getHouseByUID(list.get(i)[0]);
				if(house==null)
					throw new RuntimeException("第"+(i+1)+"行记录的房间编号不存在为空。");
				else{
					HouseMeterEO hmeter=getHouseMeter(list.get(i)[0],list.get(i)[1]);
					if(hmeter!=null)
						throw new RuntimeException("第"+(i+1)+"行"+house.getId()+"记录的业户表资料已存在。");
					housemeter.setHouse(house);
				}
			}else
				throw new RuntimeException("第"+(i+1)+"行记录的房间编号为空。");
			if (null != list.get(i)[1] && !"".equals(list.get(i)[1])){
				housemeter.setMeterType(list.get(i)[1]);
			}else
				throw new RuntimeException("第"+(i+2)+"行记录的表类型为空。");
			if (null != list.get(i)[2] && !"".equals(list.get(i)[2])){
				housemeter.setInitNum(Integer.parseInt(list.get(i)[2]));
			}else
				throw new RuntimeException("第"+(i+3)+"行记录的期初度数为空。");
			if (null != list.get(i)[3] && !"".equals(list.get(i)[3])){
				housemeter.setNowRecord(Integer.parseInt(list.get(i)[3]));
			}else
				throw new RuntimeException("第"+(i+4)+"行记录的未次度数为空。");
			
			//设置业主
			if(house.getOwnerName()!=null)
				housemeter.setOwnerName(house.getOwnerName());
			else
				housemeter.setOwnerName("");
			
			
			houseMeterDao.getSession().save(housemeter);
			if(i%30==0){
				houseMeterDao.getSession().flush();
				houseMeterDao.getSession().clear();
			}
		}		
	}	
	
	/**
	 * 更新房间水电表度数
	 * @param id
	 * @param nowRecord
	 * @param backRecord 
	 */
	public void updateHouseMeter(int id, int nowRecord, int backRecord,int timesNow) {

		HouseMeterEO hc = houseMeterDao.get(id);
		
		//设置业户记录表
		OwnerMeterRecordEO record =houseMeterDao.getMaxOwnerMeterByCode(String.valueOf(id));
		
		if(record!=null)
		{
			if(record.getCheckStatus().equals("数据锁定"))
			{
				throw new RuntimeException("当前数据已经审核通过不能修改，请下次生成数据后再更改或者联系管理员");
			}
			if(record.getCheckStatus().equals("正在审核"))
				throw new RuntimeException("当前数据正在审核不能修改");
			record.setLastRecord(nowRecord);
			record.setTimes(Integer.valueOf(String.valueOf(timesNow)));
			owDao.save(record);
			
		}
		//设置业户表
		if(backRecord>0)
			hc.setBackRecord(backRecord);
		
		hc.setNowRecord(nowRecord);
		hc.setTimes(timesNow);
		houseMeterDao.save(hc);	
	}
	
	
	/**
	 * 取业主房间表 表信息
	 * @param houseId
	 * @param meterType
	 * @return
	 */
	public HouseMeterEO getHouseMeter(String houseId, String meterType) {
		return  (HouseMeterEO) houseMeterDao.findUnique("from HouseMeterEO where isNow=true and meterType='"+meterType+"' and house.id='"+houseId+"'");
	}
	
	
	public HouseMeterEO getHouseMeter(int id){
		return houseMeterDao.get(id);
	}
	

}