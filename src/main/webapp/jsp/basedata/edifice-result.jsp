<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<%
	String[] menuModel = { "menuModel2.addItem(203,'列表','','edifice!listquery.action',false);" };
%>
<body>
<br />
<br />
<br />
<br />
<br />
<div align="center">操作结果:</div>
<br />
<br />
<br />
<br />
<br />
<div align="center">${requestScope['forwardStr']}</div>
</body>
</html>