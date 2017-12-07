package com.acec.wgt.service.chargemanger;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.chargemanager.AllMeterRecordEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordDAO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordImplDAO;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class OwnermeterRecordManager {

	private final Logger logger = LoggerFactory.getLogger(OwnermeterRecordManager.class);

	private HibernateDao<OwnerMeterRecordEO, Integer> managerDao;	
	
	@Autowired
	private OwnerMeterRecordDAO ownermeterDao;
	
	@Autowired
	private OwnerMeterRecordImplDAO OwnerMeterRecordImplDAO;
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		managerDao = new HibernateDao<OwnerMeterRecordEO, Integer>(sessionFactory, OwnerMeterRecordEO.class);
	}


	/**
	 * 权限下 所有业户表记录 分页
	 * @param page
	 * @param where   查询条件
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page getListByPage(Page page,String where) {

		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from OwnerMeterRecordEO where areaId in ("+areaIds+") "+where+ " order by houseId";
		
		return managerDao.find(page, hql, null);
	}
	/**
	 * 权限下 所有业户表记录 分页(最新)
	 * @param page
	 * @param where   查询条件
	 * @return
	 */
	@Transactional(readOnly = true)
	 public PaginatorTag getListByPageImpl(String where , int no , int i) {

		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from OwnerMeterRecordEO where areaId in ("+areaIds+") "+where+ " order by houseId";
		
		return OwnerMeterRecordImplDAO.getOwnerMeterRecordImpl(areaIds, where, no, i);
	}
	
	
	
	
	@Transactional(readOnly = true)
	public PaginatorTag getListOwnerMeterRecord(String where , int no , int i){
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		
		return ownermeterDao.getListByPage(areaIds, where, no, i);
	}
	
	
	/**
	 * 默认根据权限取小区的所有表<br>
	 * 可以根据条件筛选
	 * @param page 分页
	 * @param areaId 小区id 默认为0 必须有值
	 * @param edificeId 楼栋id默认为0 必须有值
	 * @param cell  单元 默认为""
	 * @param houseId  房间id 默认为0 必须有值
	 * @param meterType 表类型 默认为""
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page getListByPage(Page page,int areaId,int edificeId,String cell,int houseId,String meterType,String managerMen) {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		
		String where="";
		if(0 < areaId)
		{
			where += " and areaId="+areaId;
		}
		if(0 < edificeId)
		{
			where += " and edificeId='"+edificeId+"'";
		}
		if(0 < houseId)
		{
			where += " and houseId='"+houseId+"'";
		}
		if(!"".equals(cell))
		{
			where += " and cell='"+cell+"'";
		}
		if(!"".equals(meterType))
		{
			where += " and meterType='"+meterType+"'";
		}
		if(!"".equals(managerMen))
		{
			where += " and managerMen='"+managerMen+"'";
		}
		
		
		String hql="from OwnerMeterRecordEO where areaId in ("+areaIds+") "+where+ " order by houseId";
		
		return managerDao.find(page, hql, null);
	}
	
	
	
	


	/**
	 * 查看某月份业主表的数据是否都通过审核
	 * 
	 * @param recordMoth
	 * @return  全部审核通过为true 
	 */
	@Transactional(readOnly = true)
	public boolean isCheck(int areaId,String recordMonth)
	{
		//判断是否有数据未审核
		String hql="select count(*) from OwnerMeterRecordEO where checkStatus<>'数据锁定' and areaId="+areaId;
	
		List<Object> l = managerDao.find(hql);
		Long ret = (Long) l.get(0);
		if(ret == 0)
			return true;
		else
			throw new RuntimeException("有数据未审核");
		
		
	}
	
	/**
	 * 查看业主表某月份是否有数据
	 * 
	 * @param recordMoth
	 * @return  有数据为true 
	 */
	@Transactional(readOnly = true)
	public boolean isRecord(int areaId,String recordMonth)
	{
		String hql="select count(*) from OwnerMeterRecordEO where areaId="+areaId+" and date_format(recordMonth,'%Y-%m')='"+recordMonth.substring(0,7)+"'";
		
		List<Object> l = managerDao.find(hql);
		Long ret = (Long) l.get(0);
		if(ret == 0)
		{
			return true;
		}
		else
			throw new RuntimeException("该月份已经生成数据");
	}
	
	/**
	 * 批量保存业户表抄表数据
	 * 
	 * @param l2<ownerMeterRecord>
	 */
	public void multAddRecord(List<OwnerMeterRecordEO> l) {
		for(OwnerMeterRecordEO r : l)
			saveOrUpdateOwnerMeter(r);
		
	}
	/**
	 * 新增或保存一条业户表抄表记录
	 * @param allmeter
	 */
	public void saveOrUpdateOwnerMeter(OwnerMeterRecordEO ownerMeterRecord) {
		// TODO Auto-generated method stub
		managerDao.save(ownerMeterRecord);
	}


	/**
	 * 取所有当前月份的抄表记录
	 * @return
	 */
	public List<OwnerMeterRecordEO> getNowRecordAll(String where) {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from OwnerMeterRecordEO where isNow=1 and areaId in ("+areaIds+") "+where+" order by houseId";
		
		
		return managerDao.find(hql);
	}
	/**
	 * 取当前小区的总表抄表记录
	 * @param areaId
	 * @return List<OwnerMeterRecordEO>
	 */
	public List<OwnerMeterRecordEO> getNowRecordAll(int areaId,String recordMonth) {
		return managerDao.find("from OwnerMeterRecordEO where isNow=1 and recordMonth='"+recordMonth+"' and areaId="+areaId);
	}

	

	/**
	 * 取单个小区所有业主的用水总量
	 * @param areaId
	 * @return
	 */
	public float getAllOwnerWater(int areaId) {
		
		logger.debug("取单个小区所有业主的用水总量");
		
		List l = managerDao.find("select sum(useNums) from OwnerMeterRecordEO where meterType='水表' and areaId="+areaId);
		
		
		if(null != l.get(0))
			return (Long) l.get(0);
		else
			return 0;
	}


	/**
	 * 把当前业主表抄表记录的isNow改为0
	 */
	public void updateIsNow(int areaId,String recordMonth) {
		// TODO Auto-generated method stub
		managerDao.createQuery("update OwnerMeterRecordEO set isNow=0 where recordMonth='"+recordMonth+"' and areaId="+areaId).executeUpdate();
	}

	/**
	 * 比较与总表中的月份是否一致
	 * @param areaId
	 * @param recordMonth
	 * @throws Exception 
	 */
	public Boolean isEqualsAllMeter(int areaId, String recordMonth) throws Exception {

		List l = managerDao.find("select max(recordMonth) from AllMeterRecordEO where areaId="+areaId);
		
		if(l.isEmpty())
			return true;
		
		if(null == l.get(0))
			throw new RuntimeException("总表没有抄表记录，请先生成总表记录！");
			
		String recordMonth_1 = l.get(0).toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date hisMon = df.parse(recordMonth_1);
		Date nowMon = df.parse(recordMonth);
		
		
		if(nowMon.getYear() == hisMon.getYear() && nowMon.getMonth() == hisMon.getMonth())		
			return true;
		else
			throw new RuntimeException("总表的抄表月份为"+recordMonth_1.substring(0,7)+",客户表生成月份要与它一致");
		
		
	}

	
}
