package com.acec.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelInOut {
	
	/**
	 * @return true成功 or false失败
	 * @param List 不为空 
	 * List中存放 String[]数组 包括列名称和内容
	 * Excel 格式首行为列名称，下面是内容
	 * 
	 * 已经设置了 setContentType("application/vnd.ms-excel")
	 * 
	 */
	public Boolean writeExc(List<String[]> content) {
			
	//	ServletActionContext.getResponse().setContentType("application/vnd.ms-excel");
		ServletActionContext.getResponse().setContentType("application/x-download");
		ServletActionContext.getResponse().addHeader("Content-Disposition","attachment;filename=templete.xls");    
		if(content.isEmpty())
			return false;
		
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(ServletActionContext.getResponse().getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WritableSheet ws = wwb.createSheet("工作区", 0);//创建sheet名称
		try 
		{
			for(int i=0;i<content.size();i++)
			{
				Object[]temp=content.get(i);
				
				//添加 列 名称	、内容		
				for (int j=0;j<temp.length;j++)
				{
					
					Label l = new Label(j, i, temp[j]!=null?temp[j].toString():"");
					ws.addCell(l);
				}
			}
			//添加内容 
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		try {
			wwb.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			wwb.close();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 此方法只负责读取 excel文件内容 不负责错误判断、处理
	 * 要求excFile不为null 和 contenttype.equals("application/vnd.ms-excel")
	 * 
	 * @param excFile
	 * excFile
	 * 
	 * @return List<String[]>
	 */
	public List<String[]> readExc(File excFile)
	{
		System.out.println("开始读取文件。。");
		List retList=null;
		Workbook workbook = null;
		try {
			System.out.println("获取文件。。。");
			workbook = Workbook.getWorkbook(excFile);			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = workbook.getSheet(0);
		int rows= sheet.getRows(); //Excel行数
		int columns = sheet.getColumns();//Excel列数
		
		retList=new ArrayList();
		for(int i=1;i<rows;i++)
		{
			String[]temp=new String[columns];
			for(int j=0;j<columns;j++)
			{
				temp[j]=sheet.getCell(j, i).getContents();
			}
			retList.add(temp);
		}
		return retList;
	}
	
	public static void main(String[]arg)
	{
		ExcelInOut eIO=new ExcelInOut();
		String[] temp={"id","姓名","密码"};
		String[] temp1={"1","aa1","aa1a"};
		String[] temp2={"2","aa2","aa2a"};
		List l=new ArrayList();
		l.add(temp);
		l.add(temp1);
		l.add(temp2);
		eIO.writeExc(l);
	}
	
	
}