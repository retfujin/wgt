package com.acec.wgt.service.chargemanger;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.chargemanager.AllMeterRecordEO;
import com.acec.wgt.models.chargemanager.AllMeterRecordImplDAO;
import com.acec.wgt.models.chargemanager.AllmeterEO;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AllmeterRecordManager {

	private final Logger logger = LoggerFactory.getLogger(AllmeterRecordManager.class);

	private HibernateDao<AllMeterRecordEO, Integer> managerDao;	
	private HibernateDao<AllmeterEO, Integer> allmeterDao;
	
	@Autowired
	private AllMeterRecordImplDAO allMeterRecordImplDAO;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		managerDao = new HibernateDao<AllMeterRecordEO, Integer>(sessionFactory, AllMeterRecordEO.class);
		allmeterDao =new HibernateDao<AllmeterEO, Integer>(sessionFactory, AllmeterEO.class);
	}

	
	/**
	 * 权限下 所有总表抄表记录记录 分页(最新)
	 * @param page
	 * @return
	 */
	@Transactional(readOnly = true)
	public PaginatorTag getAllMeterRecord(String where,int no , int i){
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		return allMeterRecordImplDAO.getAllMeterRecord(areaIds, where, no, i);
	}
	
	/**
	 * 查看录入的数据是否已生成
	 * 
	 * @param recordMoth
	 * @param areaId 
	 * @return  全部审核通过为true 
	 */
	@Transactional(readOnly = true)
	public boolean isCheck(int areaId,String meterType)
	{
		String hql="select count(*) from AllMeterRecordEO where isNow=1 and areaId=? and meterType=?";
	
		List<Object> l = managerDao.find(hql,areaId,meterType);
		Long ret = (Long) l.get(0);
		if(ret == 0)
			return true;
		else
			return false;
	}
	
	@Transactional(readOnly = true)
	public List getCurrentRecord(int areaId,String recordMonth)
	{
		String hql="from AllMeterRecordEO where checkStatus<>'数据锁定' and areaId="+areaId;
	
		List<AllMeterRecordEO> l = managerDao.find(hql);
		return l;
	}
	

	/**
	 * 新增或保存一条总表抄表记录
	 * @param allmeter
	 */
	public void saveOrUpdateAllmeter(AllMeterRecordEO allMeterRecord) {
		// TODO Auto-generated method stub
		allMeterRecord.setSubmitTime(new Date());
		managerDao.save(allMeterRecord);
	}


	/**
	 * 批量保存总表抄表数据
	 * 
	 * @param l2<AllMeterRecordEO>
	 */
	public void multAddRecord(List<AllMeterRecordEO> l) {
		for(AllMeterRecordEO r : l)
			saveOrUpdateAllmeter(r);
		
	}

	/**
	 * 单个更新总表记录的状态
	 * @param id 当前总表记录的流水id
	 * @param str 审核的状态  
	 * @see AllMeterRecordEO
	 */
	public void checkData(int id, String str) {
		// TODO Auto-generated method stub
		if(str.equals("数据锁定"))
			managerDao.createQuery("update AllMeterRecordEO set checkStatus='数据锁定',checkName='"+(String)Struts2Utils.getSession().getAttribute("userName")+"',checkTime=now() where id="+id).executeUpdate();
		else if(str.equals("审核否决"))
			managerDao.createQuery("update AllMeterRecordEO set checkStatus='审核否决'  where id="+id).executeUpdate();
	}


	/**
	 * 取总表的总金额
	 * 总表的总费用
	 * @param para 
	 * @param areaId
	 * @return
	 */
	public float getAllAmmterMoney(String para,int areaId) {
		
		List l = managerDao.find("select sum(totalMoney) from AllMeterRecordEO where isNow=1 and para=? and area.id=?",para,areaId);
		float a = 0;
		
		
		if(null!=l.get(0))
		{
			Double d = (Double) l.get(0);
			a = d.floatValue();
			return a;
		}
		else
			throw new RuntimeException("总表的总费用为0");
	}



	/**
	 * 计算所有para=para的总表的  总用量
	 * @param para  参数代码
	 * @param areaId 小区id
	 * @param meterType  表类型
	 * @return
	 */
	public Float getAllSecondWaterNums(String para, Integer areaId,String meterType) {
		
		Long d = 0L;
		List l = managerDao.find("select sum(useNums) from AllMeterRecordEO where para='"+para+"' and meterType='"+meterType+"' and area.id="+areaId);
		
		if(null !=(Long) l.get(0))
			d = (Long) l.get(0);
		
		return d.floatValue();
		
	}

	/**
	 * 把当总表抄表记录的isNow改为0
	 */
	public void updateIsNow(int areaId,String recordMonth) {
		// TODO Auto-generated method stub
		managerDao.createQuery("update AllMeterRecordEO set isNow=0 where recordMonth='"+recordMonth+"' and areaId="+areaId).executeUpdate();
	}

	
	/**
	 * 取总表行度记录
	 * @param string
	 * @return
	 */
	public List getAll(String where) {
		return managerDao.find("from AllMeterRecordEO where 1=1 "+where);
	}


	/**
	 * 生成月份是否比数据库中的最新月份大
	 * 最少大一个月
	 * 是大 的话return true
	 * 
	 * @param areaId
	 * @param recordMonth
	 * @return 
	 * @throws Exception 
	 */
	public boolean isBegin(int areaId, String recordMonth) throws Exception {

		List l = managerDao.find("select max(recordMonth) from AllMeterRecordEO where areaId="+areaId);
		
		if(l.isEmpty() || null == l.get(0))
			return true;

		String recordMonth_1 = l.get(0).toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date hisMon = df.parse(recordMonth_1);
		Date nowMon = df.parse(recordMonth);
		
		
		if(nowMon.getYear() >= hisMon.getYear() && nowMon.getMonth() > hisMon.getMonth())		
			return true;
		else
			throw new RuntimeException("生成月份要比历史月份大");
		
	}

	/**
	 * 通过id取总表抄表记录
	 * @param recordId
	 */
	public AllMeterRecordEO get(int id) {

		return managerDao.get(id);
	}


	/**
	 * 生成总表抄表数据
	 * 检查总表数据是不是都审核通过<br>
	 * 生成月份要比历史月份大<br>
	 * 生成所有正在使用的总表数据<br>
	 * 
	 * @param areaId
	 * @param recordMonth
	 * @throws Exception
	 */
	public void resultMeterData(int areaId,String meterType,String recordMonth) throws Exception
	{
		List _ls = managerDao.createQuery("select recordMonth from AllMeterRecordEO where recordMonth>='"+recordMonth+"-01' and areaId=? and meterType=?",areaId,meterType).list();
		if(!_ls.isEmpty())
			throw new RuntimeException(meterType+"已生成"+recordMonth+"月抄表记录");
			
		
		List<AllmeterEO> l = managerDao.find("from AllmeterEO where state='使用' and areaId=? and meterType=?", areaId,meterType);
		
		if(l.isEmpty()){
			throw new RuntimeException("没有找到相关"+meterType+"基础设置资料");
		}
		
		
		Date _recordMonth = Utils.strToDate(recordMonth+"-01");
		
		List l2 = new ArrayList();
		for (AllmeterEO a : l) {
			AllMeterRecordEO r = new AllMeterRecordEO();
			// 类属性对拷
			BeanUtils.copyProperties(a, r);
			r.setId(null);
			r.setRecordMonth(_recordMonth);
			r.setNowRecord(r.getLastRecord());
			r.setUseNums(0);
			r.setCheckStatus("正在录入");
			r.setIsNow(1);
			// r.setRecordMonth(Utils.strToDate(recordMonth.substring(0,7)+"-01"));
			r.setAreaId(a.getAreaId());
			r.setAllmeterId(a.getId());// 表编号
			l2.add(r);
		}
		multAddRecord(l2);
	}
	

	/**
	 * 保存总表抄表数据(第一次以后)<br>
	 * 
	 * @param recordId[]
	 * @param nowRecord[]
	 * @param recordMonth
	 * @param employee
	 * @param id 
	 */
	public void saveMeterData(String[] nowRecord,String recordTime, String employee, String[] ids,String[] changeNums,String[] meterType) throws Exception {
	
		String userName = (String) Struts2Utils.getSession().getAttribute("userName");	
			
		for(int i=0;i<nowRecord.length;i++)
		{
			int id_ = Integer.parseInt(ids[i]);
			int nowRecord_ = Integer.parseInt(nowRecord[i]);
			int changeNums_ = Integer.parseInt(changeNums[i]);
			managerDao.createQuery("update AllMeterRecordEO set nowRecord=?,changeNums=?"
					+",recordTime=?,recordName=?,submitName=?,submitTime=? where id=?",
					nowRecord_,changeNums_,Utils.strToDate(recordTime),employee,
					userName,new Date(),id_).executeUpdate();
			//saveOrUpdateAllmeter(re);
		}
	}

	/**
	 * 通过用量，倍率计算总金额
	 * @param re
	 */
	private void setMeterTotalMoney(AllMeterRecordEO re) {
		int nowNums = (re.getNowRecord() - re.getLastRecord())*re.getTimes().intValue();
		re.setUseNums(nowNums+re.getChangeNums());
		re.setTotalMoney(re.getPriceValue() * re.getUseNums());
	}

	
	/**
	 * 更换总表
	 * 必须等抄表记录生成以后才能换
	 * 否则老表的记录不能生成
	 * 
	 * @param allmeter
	 * @param oldMeterNum  老表期末指数
	 * @throws Exception 
	 */
	public void saveAllmeterChange(AllmeterEO allmeter,String oldMeterNum) throws Exception {
		
		//老表有没有生成抄表记录
		List<AllMeterRecordEO> l0 = managerDao.find("from AllMeterRecordEO where isNow = 1 and allmeterId="+allmeter.getId());
		if(null == l0 || l0.isEmpty())
			throw new RuntimeException("必须要生成抄表数据后才能换表，否则此表数据无法记录");
		
//		System.out.println("老表id"+allmeter.getId());
		int now = Integer.parseInt(oldMeterNum);//老表期末指数
		
		
		
		AllmeterEO temp = allmeterDao.get(allmeter.getId());
		//保存新表
		AllmeterEO newmeter = new AllmeterEO();
		BeanUtils.copyProperties(temp, newmeter);
		newmeter.setId(null);
		newmeter.setInitRecord(allmeter.getInitRecord());
		newmeter.setLastRecord(allmeter.getLastRecord());
		newmeter.setTimes(allmeter.getTimes());
		allmeterDao.save(newmeter);
		
		//保存老表
		temp.setLastRecord(now);
		temp.setChangeName(allmeter.getChangeName());
		temp.setChangeReason(allmeter.getChangeReason());
		temp.setChangeTime(allmeter.getChangeTime());
		temp.setState("停用");
		allmeterDao.save(temp);
		
		
		
		//重新变更抄表记录
		AllMeterRecordEO r = l0.get(0);
		int  last= r.getLastRecord();		
		r.setChangeNums((now-last)*r.getTimes().intValue());
		r.setLastRecord(newmeter.getInitRecord());
		r.setNowRecord(newmeter.getLastRecord());
		//r.setCheckStatus("正在录入");
		//重新生成金额
		setMeterTotalMoney(r);
		saveOrUpdateAllmeter(r);

	}

	/**
	 * 删除当前总表抄表记录
	 * @param areaId
	 * @param meterType
	 */
	public void deleteMeterData(Integer areaId, String meterType) {
		managerDao.createQuery("delete AllMeterRecordEO where isNow =1 and meterType=? and areaId=?",meterType,areaId).executeUpdate();
		
	}
	
	
}
