﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>   
<script language="javascript" src="/js/func.js"></script>
<script language="JavaScript" src="/js/PopupCalendar.js"></script>
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
<div align="center">
<br>
<form id="form1" name="form1" method="post"
	action="recvinit!showrecvstat.action" onSubmit="return sub()">
<table width="90%" border="0" align="center" class="tablegray">
	<tr>
		<th height="25" colspan="2" class="tableHeaderText">短信上行统计</th>
	</tr>
	<tr align="center">
		<td height="30" align="center">开始时间： <input type="text"
			name="time1" readOnly size="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
		&nbsp;&nbsp;&nbsp;&nbsp; 结束时间： <input type="text" name="time2"
			readOnly size="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> <input type="submit"
			value="查询" class="a" /></td>
	</tr>
</table>
</form>
</div>
</body>

</html>