<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function checkAudit(){
	if(document.getElementById('endDate').value==''){
		alert("请选择查询截止日期");
		return false;
	}else
		return true;
		
}
</script>
</head>

<body>
<% 	
	String areaId = request.getParameter("areaId") !=null ? request.getParameter("areaId"):"";	
	String beginTime = request.getParameter("beginTime") !=null ? request.getParameter("beginTime"):"";	
	String endTime = request.getParameter("endTime") !=null ? request.getParameter("endTime"):"";
	String[] menuModel={"menuModel2.addItem(203,'账目进度','','report!roleaudit.action',false);"};
%> 
<%@ include file="/menubar/simple/aa.jsp" %>
<div id ="disSel">
<form name="form1" action="report!audit.action"  method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
	<td>小区名称：<s:select list="retList" name="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
	<td>开始日期：<input type="text" name="beginTime" size="16" readonly="readonly" <s:if test="startDate==''"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value='%{#parameters.beginTime}'/>"</s:if><s:else> value='${startDate}'</s:else>> </td>
	<td>截止日期：<input type="text" name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="16" readonly="readonly" value="${param.endTime}"> </td>
	<td><input type="submit" value="查询"  class="a"/></td>
</tr>
</table>
</form>
</div>
<s:iterator value="viewList" status="stuts">
<table width="98%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="25" colspan="20" class="tableHeaderText">操作人员收费明细表</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30" >操作人员</td>
    	<td class="forumRow" >收款</td>
    	<td class="forumRow" >预收</td>
    	<td class="forumRow" >优惠</td>
    	<td class="forumRow" >退款</td>
    	<td class="forumRow" >总计</td>
   </tr>
  
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>
    <td><s:property value="viewList[#stuts.index][0]"/></td>
    <td><s:property value="viewList[#stuts.index][1]"/></td>
    <td><s:property value="viewList[#stuts.index][2]"/></td>
    <td><s:property value="viewList[#stuts.index][3]"/></td>
    <td><s:property value="viewList[#stuts.index][4]"/></td>
    <td><s:property value="viewList[#stuts.index][5]"/></td>
  </tr>

  </table>
  
  <br><br>
<form name="form1" action="report!saveaudit.action"  method="post" onsubmit="return checkAudit();">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
	<tr>
		<td height="28">小区名称：<s:select list="retList" name="areaId" listKey="id" listValue="areaName" theme="simple"></s:select></td>
		<td>报账开始日期：<input type="text" name="beginDate" id="beginDate" readonly="readonly" <s:if test="startDate==''"> value="<s:property value='%{#parameters.beginTime}'/>" </s:if><s:else>value='${startDate}'</s:else> size="16" /></td>
		<td>报账截止日期：<s:textfield name="endDate" id="endDate" value="%{#parameters.endTime}"  readonly="true" size="16" theme="simple" /></td>
	</tr>
	<tr>
		<td height="28">报账金额：<input type="text" name="sumMoney" id="sumMoney" readonly="readonly" value="<s:property value="viewList[#stuts.index][5]"/>"></td>
		<td>报账人：<input type="text" name="userName" id="userName" value="<s:property value="#session.userName" />" readonly="readonly"></td>
	</tr>
	<tr>
		<td height="28"><input type="submit" value="上报账目" class="a"/></td>
	</tr>
</table>
</form>

  </s:iterator>
</body>
</html>