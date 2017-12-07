<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
</head>
<body>
<% 	
	String[] menuModel={"menuModel2.addItem(204,'返回','','',false,'','window.history.back()');"
	};
%>

<%@ include file="/menubar/simple/aa.jsp"%>
<div align="center">操作失败，请联系管理员。<s:actionerror cssStyle="color:red" /></div>
</body>
</html>
