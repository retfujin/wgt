<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>

<% 	
	String areaId=request.getParameter("areaId")!=null ? request.getParameter("areaId"):"0";
	String edificeId=request.getParameter("edificeId")!=null ? request.getParameter("edificeId"):"";
%>

 

<form class="search_form" name="form1" action="owner!list.action" method="post" target="ifr">
	小区名称 <s:select id="areaId" name="areaId" list="viewList" listKey="id" listValue="areaName" theme="simple" />
	房间编号<input type="text" name="houseId">
	<input type="submit" value="查询" class="search_btn" />
</form>

<iframe id="ifr" name="ifr" width="100%" height="100%" frameborder="0" src=""></iframe>
</body>
<script type="text/javascript">
var v_areaId = document.getElementById('areaId').value;
if(v_areaId!=''){
	document.getElementById('ifr').src='owner!list.action?areaId='+v_areaId;
}
</script>
</html>
