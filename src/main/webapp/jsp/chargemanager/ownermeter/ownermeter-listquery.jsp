<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/Toolbar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/Toolbar.js"></script>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<body> 


<form class="search_form" name="form1" action="ownermeter!list.action" method="post" target="ifr">
	小区<s:select name="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"></s:select>
	房间编号<input type="text" name="houseId"/>
	表类型<select name="meterType">
		    <option value=""></option>
		     <option value="DB">电表</option>
		    <option value="SB">水表</option>
		    <option value="RSB">热水表</option>
		    <option value="NQB">暖气表</option>
		    </select>
	<input type="submit" value="查询" class="search_btn" />
</form>

 
<iframe id="ifr" name="ifr" width="100%" height="470" frameborder="0" src=""></iframe>

</body>
<script type="text/javascript">
var v_areaId = document.getElementById('areaId').value;
if(v_areaId!=''){
	document.getElementById('ifr').src='ownermeter!list.action?areaId='+v_areaId;
}

</script>
</html>
