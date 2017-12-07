<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function checkYear(){
	if(document.getElementById('endMonth').value==''){
		alert("请选择统计时间段");
		return false;
	}
	return true;
}
</script>

<% 	
	java.text.SimpleDateFormat smdf = new java.text.SimpleDateFormat("yyyy-MM");
	String endMonth = smdf.format(new Date());
%> 

<form name="form1" action="/birtViewer/frameset"  method="post" target="_blank">

<table width="100%" border="0" align="center" class="tablegray" >
  <tr>
      <th height="25" class="tableHeaderText">欠费情况统计</th>
  </tr>
  <tr align="center">
  	<td height="30">截止至：
    <input type="text" id="endMonth" name="endMonth" onclick="WdatePicker({dateFmt:'yyyy-MM'})" value="<%=endMonth%>" size="10" />
    
    <input type="submit" value="查询" class="a">
    <input type="hidden" name="__report" value="report/charge/arrearage_report.rptdesign" />
    </td>
  </tr>
</table>
</form>
</body>
</html>