<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
</head>
<body style="overflow:hidden">



<!-- 隐藏的筛选条件 -->
<div id ="disSel">
<form name="form1" action="allmeter!oldmeterlist.action"  method="post" target="ifr">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
    <td>&nbsp;&nbsp;小区：</td>
    <td>
    <s:select id="areaId" name="areaId" list="viewList" headerKey="0" headerValue="=全部小区=" listKey="id" listValue="areaName" theme="simple" />
    </td>
    <td>&nbsp;&nbsp;表类型：</td>
    <td>
    <s:select theme="simple" name="meterType" list="#{'':'全部','电表':'电表','水表':'水表','热水表':'热水表','暖汽表':'暖汽表','蒸汽表':'蒸汽表'}"></s:select>
    </td>
    <td>
    <input type="hidden" value="-1"  name="hidd"/>
	<input type="submit" value="查询"  class="a"/>
	</td>
</tr>
</table>
</form>
</div>

<iframe id="ifr" name="ifr" width="100%" height="470" frameborder="0" src=""></iframe>

</body>
</html>
