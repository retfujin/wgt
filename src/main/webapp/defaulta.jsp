<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<title>欢迎登录物业管理系统</title>
</head>
<frameset name="main" rows="114,*" frameborder="no" border="0" framespacing="0" cols="*" id="framesetMain"> 
	<frame name="top" id="top" title="top" noresize scrolling="no" src="top.jsp">
	<frameset cols="230,*" frameborder="no" border="0" framespacing="0" rows="*"> 
	<frame name="leftFrame" id="leftFrame" title="leftFrame" noresize scrolling="yes" src="login!loginTree.action?pid=11">
	<frame name="mainFrame" id="mainFrame" title="mainFrame" noresize scrolling="yes" src="login!tips.action">
	</frameset>
</frameset>
<noframes></noframes>
</html>
