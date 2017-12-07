<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>

<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function checkYear(){
	if(document.getElementById('beginDate').value==''||document.getElementById('endDate').value==''){
		alert("请选择统计时间段");
		return false;
	}
	return true;
}
</script>

<% 	
    
    String beginDate = (new Date().getYear()+1900)+"-01-01";
	String endDate = (new Date().getYear()+1900)+"-12-31";
%> 

<form name="form1" action="/birtViewer/frameset"  method="post" target="_blank">

<table width="100%" border="0" align="center" class="tablegray" >
  <tr>
      <th height="25" class="tableHeaderText">报修处理情况统计</th>
  </tr>
  <tr align="center">
  	<td height="30">统计日期：
    <input type="text" name="beginDate" id="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<%=beginDate%>" size="10" />
    &nbsp;&nbsp;&nbsp;&nbsp;至
    <input type="text" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<%=endDate%>" size="10" />
    
    <input type="submit" value="查询" class="a">
    <input type="hidden" name="__report" value="report/repair/repair_report.rptdesign" />
    </td>
  </tr>
</table>
</form>
</body>
</html>