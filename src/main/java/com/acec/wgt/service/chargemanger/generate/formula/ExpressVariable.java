package com.acec.wgt.service.chargemanger.generate.formula;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;

public class ExpressVariable {
	
	
	private final Map<String,ExpressManager> map = new HashMap<String,ExpressManager>();
//	static ApplicationContext context = new ClassPathXmlApplicationContext(
//	        new String[] {"spring/applicationContext.xml", "spring/dataAccessContext-hibernate.xml", "spring/serviceContext.xml"});
//	static ServletContext sc;
//	static WebApplicationContext ctx;
	
	private void initMap()
	{
		
		map.put("单价", new VarPriceValue());
		map.put("房间收费面积", new VarChargeHouseArea());
		map.put("业户电表用量", new VarOwnerMeter_DDPrice());
		map.put("业户水表用量", new VarOwnerMeter_LSPrice());
		map.put("业户热水表用量", new VarOwnerMeter_RSPrice());
		map.put("业户暖气表用量", new VarOwnerMeter_NQPrice());
		
		map.put("小区总房间数", new VarCountHouseArea());
	
		
	//	map.put("二次供水分摊电单价", new VarDoubleWatePrice());
		
		
		
		
		//栋公摊表费用  之前为 栋公摊电费
//		map.put("房间入住系数", new VarHouseHabitationTypeEOManager());
		
	
	}
	
	/**
	 * 
	 * @param var 变量名称
	 * @param chb ChargeBaseDataEO
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public float findVariable(String var, ChargeBasedataEO chb,HouseEO houseEO,Session session,String recordMonth,ChargeHouseDetailEO chargeHouseDetail){
		if(map.isEmpty())
			initMap();
		
		if(map.containsKey(var)){
			ExpressManager em = (ExpressManager)map.get(var);
			em.setSession(session);
			em.setRecordMonth(recordMonth);
			return em.getValue(chb,houseEO,chargeHouseDetail);
			
		}
		else{
			throw new RuntimeException("表达式变量不合法,没有找到"+var);
		}
		
	}
//	public static Object getBean(String beanName){
//		if(sc==null)
//			sc = ServletActionContext.getServletContext();
//		if(ctx==null)
//			ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//		return ctx.getBean(beanName);
//	}
//	
//	public static Object getBeanXml(String beanName){
//		
//		return context.getBean(beanName);
//		
//	}
}
