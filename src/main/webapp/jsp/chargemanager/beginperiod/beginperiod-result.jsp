<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<%@ include file="/commons/meta.jsp" %>
  <body>
<% 	
	//增加菜单

	String[] menuModel={"menuModel2.addItem(206,'返回','','beginperiod!initDown.action',false);"

	};
%>

<%@ include file="/menubar/simple/aa.jsp" %>

<div align="center">操作结果:</div>
<div align="center">${requestScope['forwardStr']}</div>
</body>
</html>