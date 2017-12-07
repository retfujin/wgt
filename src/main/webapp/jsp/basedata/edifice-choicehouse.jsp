<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<form name="listHouseForm">
<table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="tab1">
<tr align="center">
	<td width="20%">
	</td>
	<td width="25%">房间编号</td>
	<td width="30%">房间面积</td>
	<td>房间地址</td>
</tr>
<s:iterator value="viewList" status="stuts">
<tr align="center">
	<td>
		<input type="checkBox" name="houseCheckBox" value="<s:property value="listBull[#stuts.index][0]"/>"  onClick="hit(${stuts.index+1})">
		
	</td>
	<td><s:property value="id"/></td>
	<td><s:property value="buildnum"/></td>
	<td><s:property value="houseAddress"/></td>
	
</tr>
</s:iterator>
</table>
</form>
<script language="javascript">

function hit(xh)//选中某个楼栋
{

	var retvalue = tab1.rows[xh].cells[1].innerHTML+",";//编号
	retvalue +=tab1.rows[xh].cells[2].innerHTML+",";//面积
	retvalue +=tab1.rows[xh].cells[3].innerHTML;//地址 
	//alert(retvalue);
	top.returnValue=retvalue;
	top.close();
}

</script>
</body>
</html>
