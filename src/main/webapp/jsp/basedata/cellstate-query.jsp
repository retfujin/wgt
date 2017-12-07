<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript">
function sub1(){
	var v_edificeId = document.getElementById('edificeId');
	if(v_edificeId==null || v_edificeId.value==''){
		alert("请选择楼栋");
		return;
	}
	var obj = document.getElementById('areaId');
	var strsel = obj.options[obj.selectedIndex].text;
	document.getElementById("areaName").value=strsel;
	
	document.getElementById("form1").action="cellstate!list.action";   
	document.getElementById("form1").submit();   
}

</script>
<body style="overflow:hidden">

<!-- 隐藏的筛选条件 -->
<div id ="disSel">
<form name="form1" action=""  method="get" target="ifr">
<input type="hidden" name="areaName" value=""/>
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
    <td width="7%">&nbsp;&nbsp;小区：</td>
    <td width="15%">
    <s:select id="areaId" name="areaId"  list="retList" listKey="id" listValue="areaName" theme="simple" onblur="doRequest();"/>
    </td>
    <td width="15%"><div id="target"></div></td>
	<td>	
		<input type="button" value="查询"  class="a" onclick="sub1()"/>
	</td>
</tr>
</table>
</form>
</div>
<iframe id="ifr" name="ifr" width="100%" height="100%" frameborder="0" src=""></iframe>

<script type="text/javascript" src="/styles/divDialog/modelDialog.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function doRequest(){
	$("#target").load("../../basedata/edifice!getajaxedifice.action?areaId="+$("#areaId").val());
}
doRequest();

</script>
</body>
</html>
