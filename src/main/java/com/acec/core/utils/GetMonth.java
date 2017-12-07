package com.acec.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class GetMonth
{

	/**
	 * 两个日期相差的月份
	 * @author Administrator
	 * @param start  大一点的日期
	 * @param end   小一点的日期
	 *
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
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month) ;
		}
	}
	/**
	 * 取2个日期相差天数
	 * @param start 2009-01-01
	 * @param end 2009-08-01
	 * @return
	 */
	public static int getDay(Date start, Date end) {
		
		Calendar aCalendar = Calendar.getInstance();
	    aCalendar.setTime(start);
	    int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	    aCalendar.setTime(end);
	    int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

	    return (day2 - day1 +1);
	}
	
	/**
	 * 比较两个日期大小
	 * @param bigDate
	 * @param smallDate
	 * @return
	 */
	public static Boolean completeMonth(String beginDate ,String endDate)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(beginDate);
            Date dt2 = df.parse(endDate);
            if (dt1.getTime() > dt2.getTime()) {
                return false;
            } else if (dt1.getTime() < dt2.getTime()) {
                return true;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	
	/**
	 * 看 nowDate 是第几季度
	 * @param nowDate
	 * @return  1 \ 2  \3 \4
	 */
	public static int getQuarter(Date nowDate) 
	{
		
		if(nowDate.getMonth() >=0 && nowDate.getMonth() <= 2)
			return 1;
		else if(nowDate.getMonth()>=3 && nowDate.getMonth() <= 5)
			return 2;
		else if(nowDate.getMonth()>=6 && nowDate.getMonth() <= 8)
			return 3;
		else 
			return 4;
	}
	
	
	/**
	 * 看 nowDate 是第几季度
	 * @param Date nowDate
	 * @return  2009-01 \ 2009-02  \2009-03 \2009-04
	 */
	public static String getQuarterStr(Date nowDate) 
	{
		if(nowDate.getMonth() >=0 && nowDate.getMonth() <= 2)
			return String.valueOf(nowDate.getYear()+1900 )+"-01";
		else if(nowDate.getMonth() >=3 && nowDate.getMonth() <= 5)
			return String.valueOf(nowDate.getYear()+1900 )+"-02";
		else if(nowDate.getMonth() >=6 && nowDate.getMonth()  <= 8)
			return String.valueOf(nowDate.getYear()+1900 )+"-03";
		else 
			return String.valueOf(nowDate.getYear()+1900 )+"-04";
	}
	/**
	 * 查看nowDate的上季度 
	 * @param nowDate
	 * @return  2009-01 \ 2009-02  \2009-03 \2009-04
	 */
	public static String getLastQuarterStr(Date nowDate) 
	{
		
				
		if(nowDate.getMonth()>=0 && nowDate.getMonth()<= 2)
			return String.valueOf(nowDate.getYear() + 1899)+"-04";
		else if(nowDate.getMonth() >=3 && nowDate.getMonth()<= 5)
			return String.valueOf(nowDate.getYear()+1900)+"-01";
		else if(nowDate.getMonth() >=6 && nowDate.getMonth()<= 8)
			return String.valueOf(nowDate.getYear()+1900)+"-02";
		else 
			return String.valueOf(nowDate.getYear()+1900)+"-03";
	}
	
	/**
	 * 根据季度取起止日期
	 * 
	 * @param beginQuarter 2009-01
	 * @param endQuarter 2009-02
	 * @return  2009-01-01 ,2009-06-30
	 */
	public static String[] getBeginEndTimeByQuarter(String beginQuarter,String endQuarter) 
	{
		String ret[] = new String[2];
		
		if(!"".equals(beginQuarter))
		{
			String yy = beginQuarter.substring(0,4);
			if(beginQuarter.substring(6,7).equals("1"))
			{
				ret[0]=yy+"-01-01";
			}
			else if(beginQuarter.substring(6,7).equals("2"))
			{
				ret[0]=yy+"-04-01";
			}
			else if(beginQuarter.substring(6,7).equals("3"))
			{
				ret[0]=yy+"-07-01";
			}
			else
			{
				ret[0]=yy+"-10-01";
			}
		}
		else
		{
			ret[0]="1900-01-01";
		}
		
		if(!"".equals(endQuarter))
		{
			String yy_ = endQuarter.substring(0,4);
			if(endQuarter.substring(6,7).equals("1"))
			{
				ret[1]=yy_+"-03-31 23:59:59";
			}
			else if(endQuarter.substring(6,7).equals("2"))
			{
				ret[1]=yy_+"-06-30 23:59:59";
			}
			else if(endQuarter.substring(6,7).equals("3"))
			{
				ret[1]=yy_+"-09-30 23:59:59";
			}
			else
			{
				ret[1]=yy_+"-12-31 23:59:59";
			}
		}
		else
		{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			ret[1] = sf.format(new Date())+" 23:59:59";
		}
		return ret;
	}
	
	
	public static String getNextDay(Date date) {
 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(calendar.getTime());
		 
	}
	
	
	public static void main(String areas[]) throws ParseException
	{
		Date inDate  = new SimpleDateFormat("yyyy-MM-dd").parse("2008-02-29");
		Date outDate  = new SimpleDateFormat("yyyy-MM-dd").parse("2008-07-31");
//		System.out.println(getMonth(inDate, outDate));
		
	//	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		System.out.println(getNextDay(inDate));
		
	}
	
	
}

