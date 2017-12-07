<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function checkYear(){
	if(document.getElementById('beginMonth').value==''||document.getElementById('endMonth').value==''){
		alert("请选择统计时间段");
		return false;
	}
	return true;
}
</script>

<% 	
    
    String beginMonth = (new Date().getYear()+1900)+"-01";
	String endMonth = (new Date().getYear()+1900)+"-12";
%> 

<form name="form1" action="/birtViewer/frameset"  method="post" target="_blank">

<table width="100%" border="0" align="center" class="tablegray" >
  <tr>
      <th height="25" class="tableHeaderText">物管费统计</th>
  </tr>
  <tr align="center">
  	<td height="30">统计日期：
    <input type="text" id="beginMonth" name="beginMonth" onclick="WdatePicker({dateFmt:'yyyy-MM'})" value="<%=beginMonth%>" size="10" />
    &nbsp;&nbsp;&nbsp;&nbsp;至
    <input type="text" id="endMonth" name="endMonth" onclick="WdatePicker({dateFmt:'yyyy-MM'})" value="<%=endMonth%>" size="10" />
    
    <input type="submit" value="查询" class="a">
    <input type="hidden" name="__report" value="report/charge/wgf_report.rptdesign" />
    </td>
  </tr>
</table>
</form>
</body>
</html>