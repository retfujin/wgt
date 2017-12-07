<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>   
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script  language="JavaScript">
function sub()
{
	if(form1.time1.value=="")
	{
		alert("请选择开始日期！");
		return false;
	}
	if(form1.time2.value=="")
	{
		alert("请选择结束日期！");
		return false;
	}
	if (document.form1.time1.value>document.form1.time2.value)
	  {
	    alert("起始日期不能大于结束日期");
	    document.form1.time2.focus();
	    return false;
	  }
}
</script>
<body>
<br>
<table width="99%" border="0" align="center" class="tablegray" >
</table>

<form id="form1" name="form1" method="post" action="recvinit!fscount.action" onSubmit="return sub()">
<table width="99%" border="0" align="center" class="tablegray" >
  <tr>
      <th height="25" class="tableHeaderText">短信发送统计</th>
  </tr>
  <tr align="center">
  	<td height="30">统计日期：
    <s:textfield name="time1" readonly="true" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/>  
    &nbsp;&nbsp;&nbsp;&nbsp;至
    <s:textfield name="time2" readonly="true" size="20" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/>
    <input type="submit" value="查询" class="a">
    </td>
  </tr>
</table>
<s:if test="time1!=null">
<% String count = (String)request.getAttribute("count");if(count!=null){%>
<br>
<table width="99%" border="0" align="center" class="tablegray" >
<tr><td height="28">${param.time1}至${param.time2}期间短信共发送<%=count%>条</td></tr>
</table><%} %>
</s:if>
</form>
</body>
</html>