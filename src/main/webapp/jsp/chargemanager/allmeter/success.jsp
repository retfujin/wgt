<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
</head>
<body>
<% 	
	String[] menuModel={
		"menuModel2.addItem(203,'列表','','allmeter!list.action',false);"
		+"menuModel2.addItem(205,'返回','','',false,'','window.history.back();');"

		};
%>
<%@ include file="/menubar/simple/aa.jsp" %>
<div align="center">操作成功！
<s:if test="forwardUrl != null && !forwardUrl.equals('')"><a href="${forwardUrl}">返回</a></s:if>
</div>
</body>
</html>
