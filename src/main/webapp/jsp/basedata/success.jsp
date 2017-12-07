<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
</head>
<body>
<% 	
	String[] menuModel={"menuModel2.addItem(203,'返回','','carport!list.action',false);"
	};
%>
<%@ include file="/menubar/simple/aa.jsp" %>
<div align="center">操作成功！
<s:if test="forwardUrl != null && !forwardUrl.equals('')"><a href="${forwardUrl}">返回</a></s:if>
</div>
</body>
</html>
