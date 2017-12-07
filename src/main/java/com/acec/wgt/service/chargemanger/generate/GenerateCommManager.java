package com.acec.wgt.service.chargemanger.generate;



import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.wgt.models.chargemanager.ChargeBasedataDAO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailDAO;



public abstract class GenerateCommManager {

	@Autowired
	protected ChargeBasedataDAO chargeBasedataDao;
	
	@Autowired 
	protected ChargeHouseDetailDAO chargeHouseDetailDao;//操作每月详情表的dao
	
	public GenerateCommManager() {
		super();
	}

	public String resultMoney(int areaId,String houseId, String beginRecordMonth, String endRecordMonth,
			String chargeName,String userName) throws Exception {
		
		List<ChargeBasedataEO> a = chargeBasedataDao.findListByNameAareaId(chargeName,areaId);
		
		if(a.isEmpty())
			throw new RuntimeException("没有找到该小区"+chargeName+"收费项目");
		
		
		//检查是否重复生成记录(暂时不要判断了，如果重复由用户自己删除,因为用户可能提前单独导入了部分业主的后期物管费的费用)

		long k =1;
		String chargeIds="";
		for(int i=0;i<a.size();i++){
			ChargeBasedataEO e = a.get(i);
			chargeIds +=","+e.getId();
		}
/*
		if(!StringUtils.isEmpty(houseId))
			k = chargeHouseDetailDao.findCount(beginRecordMonth,chargeIds.substring(1),houseId);
		else
			k = chargeHouseDetailDao.findCount(beginRecordMonth,chargeIds.substring(1));
		if(k>0){
			throw new RuntimeException("数据库中已有" + chargeName + "费用并且费用月份大于等于"+beginRecordMonth.substring(0,7)+"月");
		}
*/		
		//调用由子类实现的具体方法
		result1(areaId,houseId,beginRecordMonth,endRecordMonth,a,userName);
		return chargeIds;
	}
	
	abstract void result1(int areaId,String houseId,String beginRecordMonth, String endRecordMonth,List<ChargeBasedataEO> a,String userName) throws Exception;

}