<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<STYLE type="text/css">
td {
	font-size: 12
}
</STYLE>
<style   media="print">   
  .Noprint{display:none;}   
  .PageNext{page-break-after:   always;} 
</style>
<script type="text/javascript">
function p(){
	document.all.WebBrowser1.ExecWB(7,1);
	
}
</script>
</head>
<OBJECT id="WebBrowser1" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" VIEWASTEXT></OBJECT> 
<body link="blue" vlink="purple">
<s:iterator value="retList" status="stuts">
<table width="99%" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="5" align="center" height="22" style="font-size: 14">物业管理有限责任公司</td>
	</tr>
	<tr>
		<td colspan="5" align="center" height="22" style="font-size: 14">收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;据</td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">NO.<s:property value="retList[#stuts.index][0]"/></td>
	</tr>
	<tr>
		<td colspan="2" align="left" height="22">户名：<s:property value="retList[#stuts.index][1]"/></td><td colspan="3" align="left">房号：<s:property value="retList[#stuts.index][2]"/></td>
	</tr>
	<tr>
		<td height="22">名称</td><td>数量</td><td>单价</td><td>缴费期限</td><td>缴费金额</td>
	</tr>
	<s:iterator value="viewList" status="stus">
	<tr>
		<td height="16"><s:property value="viewList[#stus.index][0]"/></td>
		<td><s:property value="viewList[#stus.index][1]"/></td>
		<td><s:property value="viewList[#stus.index][2]"/></td>
		<td><s:if test="viewList[#stus.index][6]!=''">
				<s:property value="viewList[#stus.index][3].toString().substring(0,10)"/>~<s:property value="viewList[#stus.index][4].toString().substring(0,10)"/>
			</s:if>
			<s:else>
				<s:if test="viewList[#stus.index][0]!='能耗费'">
					<s:property value="viewList[#stus.index][3].toString().substring(0,7)"/>~<s:property value="viewList[#stus.index][4].toString().substring(0,7)"/>
				</s:if>
				<s:else>
					<s:property value="viewList[#stus.index][3].toString().substring(0,10)"/>~<s:property value="viewList[#stus.index][4].toString().substring(0,10)"/>
				</s:else>
			</s:else>
		</td>
		<td><s:property value="viewList[#stus.index][5]"/></td>
	</tr>
	<s:if test="viewList[#stus.index][6]!=''">
		<tr>
			<td colspan="2">上期度数&nbsp;<s:property value="viewList[#stus.index][6]"/></td>
			<td colspan="2">本期度数&nbsp;<s:property value="viewList[#stus.index][7]"/></td>
			<td>&nbsp;<s:property value="viewList[#stus.index][8]"/></td>
		</tr>
	</s:if>
	</s:iterator>
	<tr>
		<td colspan="5" align="left" height="22">合计金额：<s:property value="retList[#stuts.index][3]"/></td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">实收金额：<s:property value="retList[#stuts.index][3]"/></td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">(大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;写)：<s:property value="retList[#stuts.index][4]"/></td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">收&nbsp;&nbsp;款&nbsp;&nbsp;人：<s:property value="retList[#stuts.index][5]"/></td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">收款日期：<s:property value="retList[#stuts.index][6].toString().substring(0,10)"/></td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
	</tr>
	<tr>
		<td colspan="5" align="left" height="22">单位盖章：</td>
	</tr>
</table>
</s:iterator>
<script type="text/javascript">p();</script>
</body>
</html>