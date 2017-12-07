<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/commons/meta.jsp"%>
<!--
	 用户快速缴费页面 
-->
<html>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<body>
	<form action="../../chg/pay!pay.action" method="get">
		<table width="50%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
			<tr>
				<td align="right" height="40px">房间编号:</td>
				<td>
					<input type="text" name="houseId" size="15"	value="<s:property value="#request.houseId"/>" />
				</td>
			</tr>
			<tr>
				<td align="right" height="40px">开始日期 </td>
				<td>
				<input type="text" name="beginDate" size="15" value="${param.beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" />
			</tr>
			<tr>
				<td align="right" height="40px">截至日期 </td>
				<td>
					<input	type="text" name="endDate" size="15" value="${param.endDate}"	onclick="WdatePicker({dateFmt:'yyyy-MM'})" />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="查找" class="a" /></td>
			</tr>
		</table>
	</form>

</body>
</html>