package com.acec.wgt.service.chargemanger;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.hibernate.SimpleHibernateDao;
import com.acec.core.utils.ExcelInOut;
import com.acec.wgt.models.chargemanager.ChargeBeginDataEO;
import com.opensymphony.xwork2.ActionContext;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BeginPeriodManager {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SimpleHibernateDao<ChargeBeginDataEO, Integer> chargeBeginDao;
	

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		chargeBeginDao = new SimpleHibernateDao<ChargeBeginDataEO, Integer>(sessionFactory, ChargeBeginDataEO.class);
			
	}
	
	/**
	 * 导出收费期初数据模板
	 * @param areaId
	 * @return
	 */
	public Boolean writeExc(String areaId){
		
		List retList =new ArrayList();
		String[] temp={"小区编号","房间类型","房间编号","业主姓名","收费编号","收费名称","单价",
		"结算起始月","结算截止月","相差月数","应交款日期","欠款金额"};
		retList.add(temp);
		retList.add(new String[]{" "});
		retList.add(new String[]{" "});
		retList.add(new String[]{" 上传时请将下列说明删除"});
		retList.add(new String[]{" 说明：","房间类型分为住宅、商铺"});
		retList.add(new String[]{"收费编号、收费类型、收费名称和收费单价请参照收费基础项目"});
		retList.add(new String[]{"结算起止月填写格式如2009-01"});
		retList.add(new String[]{"相差月数值的是截止月-起始月相差的月数"});
		retList.add(new String[]{"应交款日期的单元格格式请选择文本"});
		com.acec.core.utils.ExcelInOut eIO=new com.acec.core.utils.ExcelInOut();
		Boolean ret=eIO.writeExc(retList);
		if(ret)
			return true;
		else
			return false;
	}
		
	//保存批量导入
	public void saveForExc(File theFile) throws Exception {
		//取得插入前的最大id 暂时不要了
	//	String id = getChgUserPayRecord();
		
		
		ExcelInOut eIO = new ExcelInOut();
		List<String[]> list = eIO.readExc(theFile);
		
		//String[] temp={"小区编号","房间类型","房间编号","业主姓名","收费编号","收费名称","单价",
		//		"费用季度","结算起始月","结算截止月","相差月数","应交款日期","应收款","已收款","欠款金额"};
		
		//先清空期初数据表
		chargeBeginDao.createQuery("delete from ChargeBeginDataEO").executeUpdate();
		
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = list.get(i);
			
			for(int j=0;j<objs.length;j++){
				if(objs[j]==null||"".equals(objs[j]))
					throw new RuntimeException("第"+(i+2)+"行记录的有空值存在。");
			}
			
			ChargeBeginDataEO  b = new ChargeBeginDataEO();
			
			b.setAreaId(list.get(i)[0]);
			b.setHouseType(list.get(i)[1]);
			b.setHouseId(list.get(i)[2]);
			b.setOwnerName(list.get(i)[3]);
			b.setChargeId(list.get(i)[4]);
			b.setChargeName(list.get(i)[5]);
			b.setChargePrice(Float.parseFloat(list.get(i)[6]));
			
			b.setRecordMonthBegin(Date.valueOf(list.get(i)[7].toString().trim()+"-01"));
			b.setRecordMonthEnd(Date.valueOf(list.get(i)[8].toString().trim()+"-01"));
			b.setMonths(Integer.parseInt(list.get(i)[9]));
			b.setOughtPayDate(Date.valueOf(list.get(i)[10].toString().trim()+"-01"));
			
			float oughtMoney = Float.parseFloat(list.get(i)[11]); 
			b.setOughtMoney(oughtMoney);
//			float payMoney = Float.parseFloat(list.get(i)[12]); 
//			b.setPayMoney(payMoney);
			b.setPayMoney(0);
			float arrearageMoney = Float.parseFloat(list.get(i)[11]); 
			b.setArrearageMoney(arrearageMoney);
			
			chargeBeginDao.save(b);
		}		
		//执行存储过程，把费用导入到缴费季度表和缴费每月表
		Session session = chargeBeginDao.getSession();
		Connection con=session.connection();
		String procedure = "{call grt_beginData() }";
		CallableStatement cstmt = con.prepareCall(procedure);
		cstmt.executeUpdate();
		
		cstmt.close();
	//	session.disconnect();
    //	session.clear();
	//	session.close();
		
		//将每月缴费表中的数据再保存到用户缴费表中,暂时不要了
	//	saveChgUserPayRecord(id);
	}

}
