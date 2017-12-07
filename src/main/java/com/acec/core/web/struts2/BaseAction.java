package com.acec.core.web.struts2;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport{
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected final String CUSTOM = "custom"; 
	
	//通用的集合类
	protected List viewList;
	protected List retList;
	protected String isFilter = "0";//默认0为直接查询    2为打印
	//页面分页字符串显示
	protected PaginatorTag pageBar;
	//想要跳转的url
	protected String forwardUrl;
	////处理结果字符串
	protected String forwardStr="";	
	private String target; 
	
	public void write(String outStr) {
		getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = getResponse().getWriter();
			writer.write(outStr);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		
	}

	public void write(StringBuffer outBuffer) throws IOException {
		if (outBuffer != null) {
			write(outBuffer.toString());
		}
	}

	
	public void outString(String str) {
		try {
			PrintWriter out = getResponse().getWriter();
			out.write(str);
		} catch (IOException e) {
		}
	}

	public void outXMLString(String xmlStr) {
		getResponse().setContentType("application/xml;charset=UTF-8");
		outString(xmlStr);
	}
	
	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}
	public List getViewList() {
		return viewList;
	}
	public void setViewList(List viewList) {
		this.viewList = viewList;
	}
	/**
	 * request获取参数方法
	 * @param fieldname request请求parameter里面的参数名
	 * @param defaultvalue 如果值为null或 空，则以defaultvalue取代
	 * @return 
	 */
	public int getRequestValue(String fieldname,Integer defaultvalue){
		
		String tmp = getRequest().getParameter(fieldname);
		if(!StringUtil.isEmpty(tmp)&&StringUtil.isInteger(tmp))
			return Integer.parseInt(tmp);
		else{
			return defaultvalue;
		}
	}
	public String getRequestValue(String fieldname,String defaultvalue){
		
		String tmp = getRequest().getParameter(fieldname);
		if(!StringUtil.isEmpty(tmp))
			return tmp;
		else{
			return defaultvalue;
		}
	}
	
	protected String getParamString(String params) {
			Map map = getRequest().getParameterMap();
			StringBuffer sbPara = new StringBuffer();
			Iterator iterator = null;
			Set set = map.keySet();
			iterator = set.iterator();
			
			//不需要自动获取参数
			if (params == null) {
				return "";
			}
			
			//将参数字符串转换成list
			String[] strs = params.split(",");
			List ls = Arrays.asList(strs);
			
			while (iterator.hasNext()) {
				Object tmpKey = iterator.next();
//				// 下列参数不在这里添加到参数字符串中 -- Begin
//				if (this.noParamList.contains(tmpKey.toString())) {
//					continue;
//				}
				if("page.pageNo".equals(tmpKey)||"pageNo".equals(tmpKey)||"pageLinkCount".equals(tmpKey))
					continue;
				// 下列参数不在这里添加到参数字符串中 -- End
				
				
				
				// 如果params不为null，为空则包含所有参数，否则只有params中指定的参数才添加到查询字符串中 -- Begin
				if (!"".equals(params.trim())) {
					
					if (!ls.contains(tmpKey)) {
						continue;
					}
					
				}
				// 如果params不为null，为空则包含所有参数，否则只有params中指定的参数才添加到查询字符串中 -- End
				
				String[] tmpValue = (String[])map.get(tmpKey);
				if (tmpValue != null && tmpValue.length > 0 && 
					tmpValue[0] != null && tmpValue[0].length() > 0) {


					String tmp = tmpValue[0];
					try {
						tmp = URLEncoder.encode(tmp, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					sbPara.append("&" + (String)tmpKey + "=" + tmp);
		
				}
			}
			String rtnQueryString = sbPara.toString();
			if(rtnQueryString.equals(""))
				rtnQueryString = "?a=1";
			else if (rtnQueryString.startsWith("&")) {
				rtnQueryString = "?"+rtnQueryString.substring(1);
			}
		return rtnQueryString;
	}
		
	/**
	 * 将异常原因转化成json格式能够读取的字符串
	 * @param e
	 * @return
	 */
	public static String praseExceptionMessage(Exception e){
		String exceptionTrace = e.getMessage();
		if(exceptionTrace.indexOf("'")!=-1){    
		    //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的    
		    exceptionTrace = exceptionTrace.replaceAll("'", "\\’");    
		}    
		if(exceptionTrace.indexOf("\"")!=-1){    
		    //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的    
		    exceptionTrace = exceptionTrace.replaceAll("\"", "\\\"");    
		}    
		   
		if(exceptionTrace.indexOf("\r\n")!=-1){    
		    //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行    
		    exceptionTrace = exceptionTrace.replaceAll("\r\n", "\\u000d\\u000a");    
		}    
		if(exceptionTrace.indexOf("\n")!=-1){    
		    //将换行转换一下，因为JSON串中字符串不能出现显式的换行    
		    exceptionTrace = exceptionTrace.replaceAll("\n", "\\u000a");    
		}
		
		return exceptionTrace;
	}
	
	public String getTarget() { 
		return target; 
	} 
	
	public void setTarget(String target) { 
		this.target = target; 
	} 
	protected String render(String _target){ 
		setTarget(_target); 
		return CUSTOM; 
	}	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}	
	public static  Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	public static String getUserId(){
		return getSession().get("userId").toString();
	}
	
	public static String getEmployeeId(){
		if(getSession().get("employeeId")==null)
			return "";
		else
			return getSession().get("employeeId").toString();
	}
	
	public static String getEmployeeName(){
		if(getSession().get("employeeName")==null)
			return "";
		else
		return getSession().get("employeeName").toString();
	}
	
	public static String getDepartmentId(){
		if(getSession().get("departmentId")==null)
			return "";
		else
		return getSession().get("departmentId").toString();
	}
	
	public static String getDepartmentName(){
		if(getSession().get("departmentName")==null)
			return "";
		else
		return getSession().get("departmentName").toString();
	}
	
	public static String getUserName(){
		return getSession().get("userName").toString();
	}
	
	public String getForwardUrl() {
		return forwardUrl;
	}	
	public String getForwardStr() {
		return forwardStr;
	}
	public void addMessage(String mess){
		forwardStr+=mess;
	}
	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
}