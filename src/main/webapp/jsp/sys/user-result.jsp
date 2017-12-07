<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>
<body>
<%
	String[] menuModel={
		"menuModel2.addItem(203,'列表','','user!list.action',false);"	
		+"menuModel2.addItem(204,'新增','','user!add.action',false);"	};
%>
<%@ include file="/menubar/simple/aa.jsp" %>
<div align="center">操作结果：<s:property value="forwardStr"/></div>
</body>
</html>
