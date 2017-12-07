package com.acec.commons.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import com.acec.wgt.models.ser.TreesVO;


public class Utils {
	
	/**
	 * 对多层菜单进行排序，排序后list集合保存顺序是
	 * -1级菜单
	 * ----2级菜单
	 * -1级菜单
	 * ----2级菜单
	 * -------3级菜单
	 * @param ls List集合是要按菜单等级进行排序后的
	 * @param Level 记录的ID
	 * @param belongId 记录的上级记录的ID
	 * @return 
	 */
	public static List getSortList(List ls,String level,String belongId){
		
		LinkedList lls = new LinkedList();
		try{
			for(int ii=0;ii<ls.size();ii++){
				Object e = ls.get(ii);
				Method m1 = e.getClass().getMethod(level,null);
				Method m2 = e.getClass().getMethod(belongId,null);
				Integer e_sup_top = -1;//保存上级id
				Integer e_level = (Integer)m1.invoke(e, null);// id
				
				Integer e_supId = (Integer)m2.invoke(e, null);//上级 id
				if(ii==0){
					e_sup_top=e_supId;
				}
				
				if(e_supId==e_sup_top){//一级菜单
					lls.add(e);
				}else{
					ListIterator li= lls.listIterator();
					for(int i = lls.size()-1;i>=0;i--){
						Object e1 = lls.get(i);
						Method m3 = e1.getClass().getMethod(level,null);
						Integer e1_level = (Integer)m3.invoke(e1, null);//已保存到集合记录的id
						
						Method m4 = e1.getClass().getMethod(belongId,null);
						Integer e1_supId = (Integer)m4.invoke(e1, null);//已保存到集合记录的上级id
						

						
						if(e_supId.intValue()==e1_level.intValue()){
							lls.add(i+1, e);
							break;
						}
//						else if(e1_supId!=e_sup_top){
//							if(e1_supId.intValue()==e_supId.intValue())
//							{
//								lls.add(i+1, e);
//								break;
//							}
//							
//						}
						else if(e_supId.intValue()==e1_supId.intValue()){
							lls.add(i+1,e);
							break;
						}
					}
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		
		return lls;
	}
	public static List getSortList(List<TreesVO> ls){
		LinkedList<TreesVO> lls = new LinkedList<TreesVO>();
		
		for(int i=0;i<ls.size();i++){
			TreesVO t = ls.get(i);
			if(t.getGread().equals("0"))
				lls.add(t);
			else if(t.getGread().equals("1")){
				for(int j = lls.size()-1;j>=0;j--){
					TreesVO t1 = lls.get(j);
					if(t1.getGread().equals("0"))
					{
						if(t.getSupId().toString().equals(t1.getId())){
							lls.add(j+1,t);
							break;
						}
							
					}
						
				}
			}else if(t.getGread().equals("2")){
				for(int j=lls.size()-1;j>=0;j--){
					TreesVO t1 = lls.get(j);
					if(t1.getGread().equals("1")&& t.getSupId().toString().equals(t1.getId())){
						lls.add(j+1,t);
						break;
					}
				}
			}
			
			
		}
//		for(Object o:lls){
//			com.acec.wgt.model.TreesVO t = (com.acec.wgt.model.TreesVO)o;
//			System.out.println(t);
//		}
		return lls;
	}
	
	
	
	
	public static float getPrometer(String per)
	{
		float ret=0;
		
		if(per.equals("3"))
		{
			ret=(float) 0.5;
		}else if(per.equals("2"))
		{
			ret=2;
		}
		else
		{
			ret=1;
		}
		
		
		return ret;
	}
	
	
	public static String getMeterTypeById(String id)
	{
		if(null == id || "".equals(id))
			return "";
		else if("SB".equals(id))
			return "水表";
		else if("DB".equals(id))
			return "电表";
		else if("RSB".equals(id))
			return "热水表";
		else if("NQB".equals(id))
			return "暖气表";
		else //if("ZQB".equals(id))
			return "蒸汽表";
	}
	
	/**
	 * 
	 * @param filePath  D:/sjh.txt
	 * @return
	 */
	public static List getSjh(String filePath)
	{
		List retList = new ArrayList();
		try {
				FileReader read = new FileReader(filePath); 
				BufferedReader br = new BufferedReader(read); 
				String row; 
				while((row = br.readLine())!=null){ 
					retList.add(row);
					System.out.println(row); 
				  }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return retList;
	}
	
	/** 
	 * 正则表达式  判断是否为小数  金额   百分比
	 * @param str
	 * @return true是
	 */
	public static Boolean isShuzi(String str)
	{
		  if(str==null || "".equals(str))
			   return false;  
			  Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
			  return pattern.matcher(str).matches();
	}
	
	public static void main(String args[])
	{
		String a = "123456789";
		System.out.print(a.substring(4, 8));
	}
	
	
}
