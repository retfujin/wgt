<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<% 	
	//增加菜单
	String[] menuModel={"menuModel2.addItem(203,'账务中心','','pay!accountcenter.action','',false);"
	};
%>
<%@ include file="/menubar/simple/aa.jsp" %>

<div align="center">操作结果:</div>
<%
	long tt = new java.util.Date().getTime();
%>
<div align="center">${requestScope['forwardStr']} <a href='pay!pay.action?houseId=<s:property 

value="@java.net.URLEncoder@encode(#parameters.houseId,'UTF-8')"/>&asdfe=<%=tt%>'>请点击转入到缴

费页面</a></div>
</body>
</html>