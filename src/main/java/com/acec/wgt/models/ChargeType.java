package com.acec.wgt.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

public class ChargeType {
	
	public static final String MeterTypeD="电表";
	public static final String MeterTypeS1="水表";
	public static final String MeterTypeS2="热水表";
	
	private static final String[] a = {"物管费","车位管理费"};
	private static final String[] b = {"电梯公摊费","照明公摊费","景观公摊费","监控公摊费","加压电费","水费"};
	private static final String[] c = {"商铺租赁费","车位租赁费"};
	public static Map<String, String[]> getType()
	{
		Map<String, String[]> m = new HashMap<String, String[]>();
		m.put("管理费用",a);
		m.put("代支代收费用",b);
		m.put("租赁费用",c);

		return m;
	}
	
	public static List getTypeList(){
		List ls = new ArrayList();
		
		for(int i=0;i<a.length;i++)
			ls.add(a[i]);
		for(int i=0;i<b.length;i++)
			ls.add(b[i]);
		for(int i=0;i<c.length;i++)
			ls.add(c[i]);
		
		return ls;
	}

}
