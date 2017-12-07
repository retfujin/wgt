<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<%@ include file="/commons/meta.jsp"%>
<title>列表</title>
</head>
<script type="text/javascript" src="/js/check.js"></script>
<%
	String[] menuModel = { "menuModel2.addItem(203,'新增','','edifice!add.action',false);" 
	+ "menuModel2.addItem(204,'列表','','edifice!listquery.action',false);" };
%>
<%@ include file="/menubar/simple/aa.jsp"%>
<body style="overflow:hidden">
<div id="disSel">
<form name="form1" action="edifice!list.action" method="post" target="ifr">
<table width="100%" border="0" align="center" cellpadding="2"
	cellspacing="1" class="tableBorder">
	<tr>
		<td>管理处：<s:select name="areaId" list="viewList" listKey="id"
			listValue="areaName" headerKey="" headerValue="--请选择管理处--"
			theme="simple" /></td>
		<td>楼栋类型:<select id="edificeType" name="edificeType">
			<option value="">--全部类型--</option>
			<option value="住宅">住宅</option>
			<option value="商铺">商铺</option>
		</select></td>
		<td>楼栋：<input type="text" id="edificeId" name="edificeId" /></td>
		<td><input type="submit" value="查询" class="a" /></td>
	</tr>
</table>
</form>
</div>
<iframe id="ifr" name="ifr" width="100%" height="470" frameborder="0"
	src=""></iframe>
</body>

<script type="text/javascript">
	document.getElementById('ifr').src = 'edifice!list.action';
</script>
</html>
