package com.acec.wgt.models.chargemanager;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.ChargeType;

@Repository
public class OwnerMeterRecordDAO extends HibernateEntityDao<OwnerMeterRecordEO> {

	/**
	 * 查看用户表某月份是否有数据
	 * 
	 * @param recordMoth
	 * @return  有数据为true 
	 */
	@Transactional(readOnly = true)
	public boolean isRecord(int areaId,String recordMonth)
	{
		String hql="select count(*) from OwnerMeterRecordEO where areaId="+areaId+" and date_format(recordMonth,'%Y-%m')='"+recordMonth.substring(0,7)+"' ";
		List<String> l = find(hql);
		if(l.isEmpty())
			return false;
		else
			return true;
	}
	public PaginatorTag getListByPage(String areaIds,String where,int no , int i){
		
		return pagedQuery("from OwnerMeterRecordEO where areaId in ("+areaIds+") "+where+ " order by houseId", no, i);
	}
	
	/**
	 * fu at 2011-5-26
	 * 设置当前表记录为结束
	 * @param areaId
	 * @param meterType
	 */
	public void updateCurrentRecordOver(int areaId,String meterType){
		//更新业户基础表底数
		Iterator iter = createQuery("select meterCode,nowRecord from OwnerMeterRecordEO where isNow=1 and areaId=? and meterType=?", areaId,meterType).iterate();
		while(iter.hasNext()){
			Object[] objs = (Object[])iter.next();
			int meterCode= Integer.parseInt(objs[0].toString());
			int nowRecord = Integer.parseInt(objs[1].toString());
			createQuery("update HouseMeterEO set nowRecord =? where id=?",nowRecord,meterCode).executeUpdate();
		}
		
		
		createQuery("update OwnerMeterRecordEO set isNow =0 where areaId=? and meterType=?", areaId,meterType).executeUpdate();
	
	
	}
	
	
	public List<OwnerMeterRecordEO> findList(String meterType,String recordMonth,int areaId){
		String hql="";
		if(meterType.equals("水表"))
			hql="from OwnerMeterRecordEO where (meterType='"+meterType+"' or meterType='热水表') and  recordMonth='"+recordMonth+"' and areaId="+areaId;
		else
			hql="from OwnerMeterRecordEO where meterType='"+meterType+"' and  recordMonth='"+recordMonth+"' and areaId="+areaId;
		return find(hql);
		
	}
		
	
}