<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>   
<script language="javascript" src="../js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script  language="JavaScript">
function sub(srcItem)
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
	    alert("开始日期不能大于结束日期");
	    document.form1.time2.focus();
	    return false;
	  }
	  if (form1.orgAddr.value!=""&&!isTelphoneNumber(srcItem))
	{
		return false;
	}
}
</script>
<body>
 
<%
	String time1=request.getParameter("time1");
	String time2=request.getParameter("time2");

	if(time1==null)time1="";
	if(time2==null)time2="";
 %>
 &nbsp;
 <div align="center">
<form id="form1" name="form1" method="post"
	action="recvinit!showrecvstat.action" onSubmit="return sub()">
<table width="99%" border="0" class="tablegray">
	<tr >
		<td height="30" align="center">开始时间： <s:textfield name="time1" readonly="true" size="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/>
		&nbsp;&nbsp;&nbsp;&nbsp; 结束时间： <s:textfield name="time2" readonly="true" size="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/>
		<input type="submit" value="查询" class="a" /></td>
	</tr>
</table>
</form>
</div>
&nbsp;
<div align="center">
<table width="99%" border="0" align="center" class="tablegray" >
	<tr>
		<th height=25 class="tableHeaderText">短信上行统计</th>
	</tr>
	<tr>
		<td>
		<table>
			<tr>
				<td>统计时间：<%=time1 %>&nbsp;&nbsp;至&nbsp;&nbsp;<%=time2 %></td>
				<td></td>
			</tr>
		<tr>
			<td>接收日期</td>
			<td> &nbsp;</td>
			<td>手机号</td>
			<td> &nbsp;</td>
			<td>消息内容</td>
			<td> &nbsp;</td>
			<td>消息回复</td>
		</tr>
		<s:iterator value="viewList" status="stuts">
		<tr>
			<td><s:property value="viewList[#stuts.index][0]" /></td>
			<td> &nbsp;</td>
			<td><s:property value="viewList[#stuts.index][1]" /></td>
			<td> &nbsp;</td>
			<td><s:property value="viewList[#stuts.index][2]" /></td>
			<td> &nbsp;</td>
			<td><a href="#" onclick="showReMessage('<s:property value="viewList[#stuts.index][3]" />')">回复</a></td>
		</tr>
		</s:iterator>
		</table>
	</td>
</tr>
</table>
<div style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="recvinit!showrecvstat.action?page.pageNo=${page.prePage}&time1=${time1}&time2=${time2}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="recvinit!showrecvstat.action?page.pageNo=${page.nextPage}&time1=${time1}&time2=${time2}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>
</div>
<script type="text/javascript">
function showReMessage(id){
	window.showModalDialog("bulletin!remessage.action?id="+id,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=36;dialogHeight=30;center=true");
}
</script>
</body>
</html>