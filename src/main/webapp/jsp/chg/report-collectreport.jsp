<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript">
	function getauditgrouptype(userName,beginDate,endDate){
		window.open("report!collectreportforuser.action?userName="+userName+"&beginDate="+beginDate+"&endDate="+endDate);
	}
</script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
</head>
<body>
<script type="text/javascript">
var tabpanel;
$(document).ready(function(){
	  tabpanel = new TabPanel({
		renderTo:'tab',
		autoResizable:true,
	//	border:'none',
		active : 0,
		maxLength : 10,
		items : [{
			id:'tab1',
			title:'收费人员统计',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<% 	
	String beginDate = request.getParameter("beginDate") !=null ? request.getParameter("beginDate"):"";	
	String endDate = request.getParameter("endDate") !=null ? request.getParameter("endDate"):"";
%> 
<div id="tab" style="width:400px;"></div>

<div id ="disSel">
<form name="form1" action="report!collectreport.action"  method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
	<td>小区名称：<s:select list="retList" name="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
	<td>开始日期：<input type="text" name="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="readonly" value="${param.beginDate}"> </td>
	<td>截止日期：<input type="text" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="readonly" value="${param.endDate}"> </td>
	<td><input type="submit" value="查询"  class="a"/></td>
</tr>
</table>
</form>

<table width="98%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="25" colspan="20" class="tableHeaderText">收费人员统计表</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30" >序号</td>
    	<td class="forumRow" >收费人员</td>
    	<td class="forumRow" >收款金额</td>
    	<td class="forumRow" >收款项目统计</td>
   </tr>
  <s:iterator value="viewList" status="stuts">
  <tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>   
   	<td height="22">&nbsp;${stuts.index+1}</td>
    <td><s:property value="viewList[#stuts.index][0]"/></td>
    <td><s:property value="viewList[#stuts.index][1]"/></td>
	<td><a href="#" onclick="getauditgrouptype('<s:property value="viewList[#stuts.index][0]"/>','<%=beginDate%>','<%=endDate%>');">查询</a> </td>
  </tr>
  </s:iterator>
  </table>
  
  </div>
</body>
</html>