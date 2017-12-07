<%@ page contentType="text/html; charset=UTF-8" %>
<% 	
String[] menuModel={
		"menuModel2.addItem(203,'返回','','generate!resultFixedMoneyIndex.action',false);"
		
};
%>
<%@ include file="/menubar/simple/aa.jsp" %>
<div align="center">${requestScope['forwardStr']}</div>