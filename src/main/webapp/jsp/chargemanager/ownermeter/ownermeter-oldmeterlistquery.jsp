<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
</head>
<body style="overflow:hidden">
<% 	
	com.acec.core.utils.CharTools charTools = new com.acec.core.utils.CharTools();
	String areaId = request.getParameter("areaId")  != null && !request.getParameter("areaId").equals("")? request.getParameter("areaId") : "0";
	String meterType = request.getParameter("meterType") != null ? request.getParameter("meterType") : "";
	String houseId=request.getParameter("houseId")!=null?request.getParameter("houseId"):"";

%>

<form class="search_form" name="form1" action="ownermeter!list.action" method="post" target="ifr">
	小区：<s:select name="areaId"  id="areaId" list="viewList" headerKey="0" headerValue="==全部小区==" listKey="id" listValue="areaName" theme="simple" ></s:select>
	房间编号<input name="houseId" /></td>
	表类型：<s:select theme="simple" name="meterType" id="meterType" list="#{'':'全部','电表':'电表','水表':'水表','热水表':'热水表','暖汽表':'暖汽表','蒸汽表':'蒸汽表'}"></s:select>
	<input type="submit" value="查询" class="search_btn" />
</form>

 
<iframe id="ifr" name="ifr" width="100%" height="470" frameborder="0" src=""></iframe>
</body>
</html>
