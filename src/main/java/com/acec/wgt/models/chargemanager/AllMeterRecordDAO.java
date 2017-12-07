package com.acec.wgt.models.chargemanager;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class AllMeterRecordDAO extends HibernateDao<AllMeterRecordEO, Integer> {

	/**
	 * 返回符合条件的总表抄表记录集合
	 * @param beginRecordMonth 收费月份
	 * @param chargebasedataId 收费项目编号
	 * @return
	 */
	public List findList(int chargebasedataId, String recordMonth) {
		return find("from AllMeterRecordEO where chargebasedataId='"+chargebasedataId+"' and recordMonth='"+recordMonth+"'");
	}
	
	public double findAreaList(int chargebasedataId ,String recordMonth){
		double a=findUnique("select sum(totalMoney) as totalMoney from AllMeterRecordEO where useNums>0  and chargebasedataId='"+chargebasedataId+"' and recordMonth='"+recordMonth+"' group by areaId");
		return a;
	}
	
	/**
	 * 符合条件的总表抄表记录集合
	 * @param subchargeId
	 * @param recordMonth
	 * @return
	 */
	public List findAllList(int subchargeId, String recordMonth) {
		return find("from AllMeterRecordEO where useNums>0  and (chargebasedataId='1004"+subchargeId+"' or chargebasedataId='1005"+subchargeId+"') and recordMonth='"+recordMonth+"'");
	}
	
	/**
	 * 取监控时费用总和
	 * @param recordMonth
	 * @return
	 */
	public double findAllList(String recordMonth,int subChargeId) {
		double totalMoney=findUnique("select sum(totalMoney) as totalMoney from AllMeterRecordEO where useNums>0  and (chargebasedataId='1004"+subChargeId+"' or chargebasedataId='1005"+subChargeId+"') and recordMonth='"+recordMonth+"'");
		return totalMoney;
	}
	
	/**
	 *  返回符合条件的总表抄表记录分组合计
	 * @param areaId 
	 * @param chargebasedataId 收费项目编号
	 * 	如 1019-GD-001中的GD
	 * @param recordMonth
	 * @return
	 */
	public Object[] getAllMoney(int subChargeId, String recordMonth) {
	
		return findUnique("select sum(totalMoney),avg(priceValue),sum(useNums) from AllMeterRecordEO where useNums>0  and (areaId=1004 or areaId=1005) and recordMonth='"+recordMonth+"' and (chargebasedataId='1004"+subChargeId+"' or chargebasedataId='1005"+subChargeId+"') ");
	}
	
	
	
	/**
	 * 得到总表下面的的分表量和
	 * @param metercode
	 * @param recordMonth
	 * @return
	 */
	public Double getMoneyBySubMeter(String metercode,String recordMonth){
		return findUnique("select sum(totalMoney) from AllMeterRecordEO where useNums>0 and meterCode like '"+metercode+"%' and len(meterCode)=14 and recordMonth='"+recordMonth+"'");
		
	}
	
	
	/**
	 * 查看总表某月份是否有数据
	 * 
	 * @param recordMoth
	 * @return  有数据为true 
	 */
	@Transactional(readOnly = true)
	public boolean isRecordAndLock(int areaId,String recordMonth,String subChargeId)
	{
		long n =findUnique("select count(*) from AllMeterRecordEO where areaId="+areaId+" and date_format(recordMonth,'%Y-%m')='"+recordMonth.substring(0,7)+"' and checkStatus<>'数据锁定' and chargebasedataId like '%"+subChargeId+"%'");

		if(n>0)
			return false;
		else
			return true;			
	}
	
	

	public void updateCurrentRecordOver(int areaId) {
		
		//更新总表的基础表底数
		Iterator iter = createQuery("select allmeterId,nowRecord from AllMeterRecordEO where isNow=1 and areaId=?", areaId).iterate();
		while(iter.hasNext()){
			Object[] objs = (Object[])iter.next();
			int allmeterId= Integer.parseInt(objs[0].toString());
			int nowRecord = Integer.parseInt(objs[1].toString());
			createQuery("update AllmeterEO set lastRecord =? where id=?",nowRecord,allmeterId).executeUpdate();
		}
		
		//更新当前记录表
		createQuery("update AllMeterRecordEO set isNow =0 where areaId=?", areaId).executeUpdate();
		
	}
	
}
