package com.acec.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.regex.Pattern;

import com.acec.wgt.models.baseData.AreaEO;

public class Utils {
	
	/** 
	 * 正则表达式  判断是否为小数  金额   百分比
	 * @param str
	 * @return true是
	 */
	public static Boolean isDecimal(String str)
	{
		if(str==null || "".equals(str))
			 return false;  
		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	public static boolean isInteger(String str){
		
		if(str==null||"".equals(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	
	/**
	 * 把String格式的日期 转换 为Date格式yyyy-MM-dd	
	 * @param dateTime
	 * @return Date
	 * @throws ParseException
	 */
	public static Date strToDate(String dateTime) throws Exception 
	{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if(null == dateTime)
			throw new RuntimeException("转换错误！被转换日期不能为null");
		else
			return s.parse(dateTime);
	}
	
	public static Date strToMinterDate(String dateTime) throws Exception{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == dateTime)
			throw new RuntimeException("转换错误！被转换日期不能为null");
		else
			return s.parse(dateTime);
	}
	
	public static String dateToString(Date dateTime)throws Exception 
	{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if(null == dateTime)
			throw new RuntimeException("转换错误！被转换日期不能为null");
		return s.format(dateTime);
	}
	
	public static String sqldateToString(java.sql.Date date){
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		if(null==date)
			throw new RuntimeException("转换错误！被转换日期不能为null");
		return s.format(date);
	}
	
	public static Date getNowDate() throws Exception 
	{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		return s.parse(s.format(new Date()));
	}
	public static String getNowDateToString() throws Exception 
	{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		return s.format(new Date());
	}
	
	
	/**
	 * 根据截止时间,和月份，取出收费的开始时间和截止时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static String[] getDateAther(Date endTime,int month){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String [] strPar=new String[2];
		int endDate = 1;
		try{
			Calendar calendar = Calendar.getInstance();//日历对象
			String [] str=Utils.dateToString(endTime).split("-");
			String newbeg="";//记录的开始时间
			
			
			if((Integer.parseInt(str[1]) == 3 || Integer.parseInt(str[1])==5 || Integer.parseInt(str[1])==7 || Integer.parseInt(str[1])==8 || Integer.parseInt(str[1])==10) &&  Integer.parseInt(str[2])==31)
				calendar.set(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2])-1,0,0,0);
			else
				calendar.set(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]),0,0,0);
			
			
			Calendar cDay = Calendar.getInstance();   
		    cDay.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)-1,1);   
		    int firstDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);		    
		    if(firstDay==Integer.parseInt(str[2])){
		    	if(Integer.parseInt(str[1])==12){
		    		newbeg=calendar.get(Calendar.YEAR)+"-1-1";
		    	}else{
		    		newbeg=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-1";
		    	}
		    	
		    }else{			
				calendar.add(Calendar.DATE, 1);
				if(Integer.parseInt(str[1]) == 1 &&  Integer.parseInt(str[2])==31){
					calendar.set(Integer.parseInt(str[0]),calendar.get(Calendar.MONTH),1,0,0,0);
					newbeg=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-1";			
				}else{
					newbeg=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);	
					endDate = calendar.get(Calendar.DATE);
				}
		    }
			
			String newend="";//month个月的最后一天，（截止时间）
			
			calendar.add(Calendar.MONTH, month);	
			calendar.add(Calendar.DATE, -1);
			
			if(endDate==1){
				 Calendar cDay1 = Calendar.getInstance();   
			     cDay1.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)-1,1);   
			     int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);   
			     newend=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+lastDay;
			}else
				newend=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			
			strPar[0]=newbeg;
			strPar[1]=newend;

		}catch(Exception e){
			throw new RuntimeException("得到的日期不正确");
		}
		return strPar;
	}
	
	
	/**
	 * 出租 出售
	 * @param state
	 * @return
	 */
	public static String getCarportState(String state)
	{
		if(null == state || "".equals(state))
			return "";
		else if(state.equals("CZ"))
			return "出租";
		else if(state.equals("CS"))
			return "出售";
		else //KZ  
			return "空置";
	}
	
	/**
	 * 机动车   非机动车
	 * @param bigType
	 * @return
	 */
	public static String getCarportBigType(String bigType)
	{
		if(null == bigType || "".equals(bigType))
			return "";
		else if(bigType.equals("JDC"))
			return "机动车";
		else //FJDC
			return "非机动车";
	}
	
	/**
	 * 自行车  电瓶车  摩托车  小型车
	 * @param type
	 * @return
	 */
	public static String getCarportType(String type)
	{
		if(null == type || "".equals(type))
			return "";
		else if(type.equals("ZXC"))
			return "自行车";
		else if(type.equals("DPC"))
			return "电瓶车";
		else if(type.equals("MTC"))
			return "摩托车";
		else if(type.equals("XXC"))
			return "小型车";
		else 
			return "";
	}
	
	/**
	 * 零星收费类型
	 * @param type
	 * @return
	 */
	public static String getLingXingType(String type)
	{
		if(null == type || "".equals(type))
			return "";
		else if(type.equals("BZ"))
			return "办证";
		else if(type.equals("XC"))
			return "小吃";
		else 
			return "";
	}
	
	public static List getSubChargeId(){
		List list =new ArrayList();
		for(int i = 0 ;i < 6 ; i++){
			String [] str =new String[2];
			if(i==0){
				str[0] = "1102";
				str[1] ="车位费";
			}
			if(i==1){
				str[0] = "2102";
				str[1] ="水费";
			}
			if(i==2){
				str[0] = "2103";
				str[1] ="热水费";
			}
			if(i==3){
				str[0] = "2104";
				str[1] ="暖气费";
			}
			if(i==4){
				str[0] = "2105";
				str[1] ="电费";
			}
			if(i==5){
				str[0] = "3101";
				str[1] ="公摊费";
			}
			list.add(str);
		}
		
		return list;
		
	}
	
	/**
	 * 
	 * @param areaList
	 * @return key areaId:Value areaName
	 */
	public static Map listToMap(List<AreaEO> areaList){
		Map m = new HashMap();
		for(AreaEO area : areaList)
			m.put(area.getId(), area.getAreaName());
		return m;
	}
	
	/**
	 * 通过当前日期  返回本季度的起止日期
	 * @param nowDate
	 * @return
	 */
	public static String[] getBeginEndTime(String nowDate)
	{
		return null;
	}
	
	/**
	 * 根据开始时间，结束时间取当前月的开始时间和结束时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static List getMonthDate(Date beginTime,Date endTime){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		if(beginTime.after(endTime)){
			throw new RuntimeException("开始时间不能小于结束时间");
		}
		
		List list=new ArrayList();
		
		try{
			list.add(sf.format(beginTime));//第一次 开始时间
			
			//通过循环设置日期
			for(int i=0;i<200;i++){
				Calendar calendar = Calendar.getInstance();//日历对象
				String [] str=Utils.dateToString(beginTime).split("-");
			
				calendar.set(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
				calendar.add(Calendar.MONTH, 1);
				
				String newbeg="";//每个月的第一天，（开始时间）
				String newend="";//每个月的最后一天，（截止时间）
				
				newbeg=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
				
				if(calendar.get(Calendar.DATE)==1){
					Calendar cDay1 = Calendar.getInstance();   
				    cDay1.set(Integer.parseInt(str[0]),Integer.parseInt(str[1])-1,1);   
				    int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);   
				    newend=Integer.parseInt(str[0])+"-"+Integer.parseInt(str[1])+"-"+lastDay;
				}else{
					calendar.add(Calendar.DATE, -1);
					newend=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);	
				}
				
				//判断结束时间是否在（开始时间月的最后一天）之后
				if(endTime.after(Utils.strToDate(newbeg)))
				{
					list.add(newend);/////---------开始时间月的最后一天				
					list.add(newbeg);/////----------添加开始时间月的第一天
					beginTime=Utils.strToDate(newbeg);
				}else{
					list.add(sf.format(endTime));
					break;
				}
			}
		}catch(Exception e){
			throw new RuntimeException("得到的日期不正确");
		}
		return list;
	}
	
	
	/**
	 * 计算相差月数
	 * @param beginTime
	 * @param endTime
	 * @return  int
	 * @throws ParseException 
	 */
	 public static int getMonth(Date start, Date end) {

	        if (start.after(end)) {
	            Date t = start;
	            start = end;
	            end = t;
	        }
	        Calendar startCalendar = Calendar.getInstance();
	        startCalendar.setTime(start);
	        Calendar endCalendar = Calendar.getInstance();
	        endCalendar.setTime(end);
	        Calendar temp = Calendar.getInstance();
	        temp.setTime(end);
	        temp.add(Calendar.DATE, 1);

	        int year = endCalendar.get(Calendar.YEAR)
	                - startCalendar.get(Calendar.YEAR);
	        int month = endCalendar.get(Calendar.MONTH)
	                - startCalendar.get(Calendar.MONTH);

	        if ((startCalendar.get(Calendar.DATE) == 1)
	                && (temp.get(Calendar.DATE) == 1)) {
	            return year * 12 + month + 1;
	        } else if ((startCalendar.get(Calendar.DATE) != 1)
	                && (temp.get(Calendar.DATE) == 1)) {
	            return year * 12 + month;
	        } else if ((startCalendar.get(Calendar.DATE) == 1)
	                && (temp.get(Calendar.DATE) != 1)) {
	            return year * 12 + month;
	        } else {
	            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
	        }

	}
	
	
		
	public static String [] getFirstEndMonth(String recordMonth){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ret[]=new String [2];
		try{
			ret[0]=sdf.format(Utils.strToDate(recordMonth+"-01"));
			Calendar calendar = Calendar.getInstance();//日历对象
			String [] str=recordMonth.split("-");
			calendar.set(Integer.parseInt(str[0]),Integer.parseInt(str[1]),1);
			calendar.add(Calendar.MONTH, 1);//加一月
			calendar.add(calendar.DATE, -1);//加一月后减一天
			String newend="";//每个月的最后一天，（截止时间）
			newend=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			ret[1]=sdf.format(Utils.strToDate(newend));
		}catch (Exception e) {
			throw new RuntimeException("时间装换错误，请检查输入日期");
		}
		return ret;
	}
	
	
	/**
	 * 得到前一天的日期
	 * @param date
	 * @return
	 */
	public static String beforeDay(){
		try{
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String beforeDay =sf.format(new Date());
			Calendar calendar = Calendar.getInstance();//日历对象
			String ret[]=new String[3];
			ret=beforeDay.split("-");			  
			calendar.set(Integer.parseInt(ret[0]),Integer.parseInt(ret[1]),Integer.parseInt(ret[2]));
			calendar.add(Calendar.DATE, -1);//减一天
			
			String beginTime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			
			return dateToString(strToDate(beginTime));
		}catch(Exception e){
			return "";
	   }
	}
	
	
	/**
	 * 得到一到十号的日期，并返回是否在其中（一到十号之间）
	 * @param date
	 * @return
	 */
	public static boolean isInDate(String date)
	{
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();//日历对象
			String ret[]=new String[3];
			ret=date.split("-");
			  
			calendar.set(Integer.parseInt(ret[0]),Integer.parseInt(ret[1]),1);
			//得到当前月份的第一天
			String beginTime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			
			calendar.set(Integer.parseInt(ret[0]),Integer.parseInt(ret[1]),10);
			//得到当前月份的第十天
			String endTime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			
			
			if(Utils.strToDate(date).after(Utils.strToDate(beginTime)) && Utils.strToDate(date).before(Utils.strToDate(endTime)))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
	   }
	}

	
	
	/**
	 * 得到十一到二十号的日期，并返回是否在其中（十一到号二十之间）
	 * @param date
	 * @return
	 */
	public static boolean isInDateTwenty(String date)
	{
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();//日历对象
			String ret[]=new String[3];
			ret=date.split("-");
			  
			calendar.set(Integer.parseInt(ret[0]),Integer.parseInt(ret[1]),11);
			//得到当前月份的第一天
			String beginTime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			
			calendar.set(Integer.parseInt(ret[0]),Integer.parseInt(ret[1]),20);
			//得到当前月份的第十天
			String endTime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
			if(Utils.strToDate(date).after(Utils.strToDate(beginTime)) && Utils.strToDate(date).before(Utils.strToDate(endTime)))
				return true;
			else
				return false;
		}catch(Exception e){
			return false;
	   }
	}
	
	/**
	 * 根据日期年月日单个对象，添加i天后返回单个日期
	 * @param year
	 * @param month
	 * @param day
	 * @param i
	 * @return
	 */
	public static String getDateString(int year,int month,int day,int i){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();//日历对象
		calendar.set(year,month,day);
		calendar.add(Calendar.DATE, i);
		String dateParam=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
		return dateParam;
	}
	
	
	/**
	 * 比较两个日期的相差天数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long countDate(String beginDate,String endDate){
		
		long day=0l;
		try{
			Calendar cala = Calendar.getInstance();
			Calendar calb = Calendar.getInstance();
			cala.setTime(Utils.strToDate(beginDate));
			calb.setTime(Utils.strToDate(endDate));
			long cha = calb.getTimeInMillis() - cala.getTimeInMillis();
			day = cha / (24 * 60 * 60 * 1000);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return day;
	}	
	
	public static void main(String args[]) throws Exception
	{
		
		System.out.println(getMonth(strToDate("2002-01-01"), strToDate("2002-03-01")));
		
	}
	
}
