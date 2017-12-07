<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<title>房间状态详情</title>
</head>
<%
	String payRecordMonth = (String)request.getAttribute("payRecordMonth");
	if(null != payRecordMonth && !"".equals(payRecordMonth) && !"[null]".equals(payRecordMonth))
		payRecordMonth=payRecordMonth.substring(0,9);
	
	String repairCount = (String)request.getAttribute("repairCount");
	
	String suitCount = (String)request.getAttribute("suitCount");
%>
<body>
<table wnewsidth="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
	<tr>
		<th height="25" colspan="2" class="tableHeaderText">${param.houseId} 房间状态详情</th>
	</tr>
	<tr align="center">
		<td class="td_f" height="22">物业费已缴费至<font color="red"><%if(null != payRecordMonth && !"".equals(payRecordMonth) && !"[null]".equals(payRecordMonth))out.print(payRecordMonth); %></font></td><td class="td_f"><a href="/chg/pay!pay.action?houseId=${param.houseId}" target="_ablank">缴费</a></td>
	</tr>
	<tr align="center">
		<td class="td_t" height="22">报修 <font color="red"><%=repairCount %></font>起未处理</td><td class="td_t"><a href="/repair/repair!list1.action?houseId=${param.houseId}" target="_ablank">查看</a></td>
	</tr>
	<tr align="center">
		<td class="td_f" height="22">投诉 <font color="red"><%=suitCount%></font>起未处理</td><td class="td_f"><a href="/suit/suit!list1.action?houseId=${param.houseId}" target="_ablank">查看</a></td>
	</tr>
</table>
</body>
</html>
