package com.acec.wgt.actions.common;


import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.acec.core.utils.StringUtil;
import com.acec.core.web.struts2.BaseAction;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportTableToExcel extends BaseAction {
	
	@Override
	public String execute() throws Exception{
	//	String cellsStr = getRequest().getParameter("arrayStr");
	//	String[] cells = cellsStr.split(":");
		String[] cells = getRequest().getParameterValues("arrayStr");
		//System.out.println(cellsStr);
		String filename = Calendar.getInstance().getTimeInMillis() + ".xls";
		filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		getResponse().addHeader("Content-Disposition", (new StringBuilder()).append(
				"attachment;filename=").append(filename).toString());
		OutputStream toClient = new BufferedOutputStream(getResponse().getOutputStream());
		exportToJxlExcel(filename, "测试", cells, toClient);
		// toClient.write(cellsStr.toString().getBytes());
		toClient.flush();
		toClient.close();
		return null;
	}
	/**
	 * //将Excel对象直接写入到输出流,用户通过浏览器来访问Web服务器，
	 * 如果HTTP头设置正确的话，浏览器自动调用客户端的Excel应用程序，
	 * 来显示动态生成的Excel电子表格
	 * @param fileName
	 * @param sheetName
	 * @param cellStrArray
	 * @param os
	 * @return
	 */
	public String exportToJxlExcel(String fileName, String sheetName,
			String[] cellStrArray, OutputStream os) {
		try {
			
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");
			jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
			
			
			// FileOutputStream os = new FileOutputStream(fileName);
			if (cellStrArray != null && cellStrArray.length > 0) {
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet(sheetName, 0);
				for (int i = 0; i < 20; i++)
					ws.setColumnView(i, 20);
				WritableCell lbl = null;
				String[] objProps = null;
				int col;
				int row;
				int rowSpan;
				int colSpan;
				for (String objStr : cellStrArray) {// 每个对象以row,col,rowSpan,colSpan,value形式
					objProps = objStr.split(",");
					if (objProps.length > 4) {
						col = Integer.parseInt(objProps[1]);
						row = Integer.parseInt(objProps[0]);
						rowSpan = Integer.parseInt(objProps[2]);
						colSpan = Integer.parseInt(objProps[3]);
						if(StringUtil.isDouble(objProps[4])){
							lbl = new jxl.write.Number(col, row, Float.parseFloat(objProps[4]), wcfN);

						}else
							lbl = new Label(col, row, objProps[4]);
						ws.addCell(lbl);
						if (rowSpan > 1 || colSpan > 1)
							ws.mergeCells(col, row, col + colSpan - 1, row
									+ rowSpan - 1);
					}
				}
				wwb.write();
				wwb.close();
				return fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

