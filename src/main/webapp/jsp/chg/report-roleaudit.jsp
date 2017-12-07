<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function getDetail(userName,beginDate,endDate){
	window.open("report!auditdetail.action?userName="+userName+"&beginDate="+beginDate+"&endDate="+endDate);
}
</script>
</head>

<body>
<% 	
	String areaId = request.getParameter("areaId") !=null ? request.getParameter("areaId"):"";	
	String beginTime = request.getParameter("beginTime") !=null ? request.getParameter("beginTime"):"";	
	String endTime = request.getParameter("endTime") !=null ? request.getParameter("endTime"):"";
	String[] menuModel={};
%> 
<%@ include file="/menubar/simple/aa.jsp" %>
 
<table width="98%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="25" colspan="20" class="tableHeaderText">报账明细表</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30" >序号</td>
    	<td class="forumRow" >报账人员</td>
    	<td class="forumRow" >开始日期</td>
    	<td class="forumRow" >截止日期</td>
    	<td class="forumRow" >报账费用</td>
    	<td class="forumRow" >报账状态</td>
    	<td class="forumRow" >账目明细</td>
   </tr>
  <s:iterator value="page.result" status="stuts">
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>   
   	<td height="22">&nbsp;${stuts.index+1}</td>
    <td>${userName}</td>
    <td><s:property value="beginDate.toString().substring(0,10)"/> </td>
    <td><s:property value="endDate.toString().substring(0,10)"/></td>
    <td>${sumMoney}</td>
    <td>${auditStatus}</td>
    <td><a href="#" onclick="getDetail('${userName}','<s:property value="beginDate.toString().substring(0,10)"/>','<s:property value="endDate.toString().substring(0,10)"/>');">查询</a> </td>
  </tr>
  </s:iterator>
  </table>
</body>
</html>
