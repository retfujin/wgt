<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<body> 



<form class="search_form" name="form1" action="allmeter!list.action" method="post" target="ifr">
	小区<s:select id="areaId" name="areaId" list="retList"  listKey="id" listValue="areaName" theme="simple"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

 
<iframe id="ifr" name="ifr" width="100%" height="100%" frameborder="0" src=""></iframe>

</body>
<script type="text/javascript">
var v_areaId = document.getElementById('areaId').value;
if(v_areaId!=''){
	document.getElementById('ifr').src='allmeter!list.action?areaId='+v_areaId;
}
</script>
</html>