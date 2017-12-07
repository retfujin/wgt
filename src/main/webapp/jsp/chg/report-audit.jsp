<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>
<% 	
	String _username = request.getParameter("_username") !=null ? request.getParameter("_username"):"";	
	String beginTime = request.getParameter("beginTime") !=null ? request.getParameter("beginTime"):"";	
	String endTime = request.getParameter("endTime") !=null ? request.getParameter("endTime"):"";
	String[] menuModel={};
%> 
<%@ include file="/menubar/simple/aa.jsp" %>


<form name="form1" action="report!audit.action"  method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
	<td>操作人员：<select name="_username" >
	<s:iterator value="retList" status="stuts">
		<option value="<s:property value='retList[#stuts.index][1]'/>">
			<s:property value="retList[#stuts.index][1]"/>
		</option>
	</s:iterator>
	</select> </td>
	<td>开始日期：<input type="text" name="beginTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="16" readonly="readonly" value="${param.beginTime}"> </td>
	<td>截止日期：<input type="text" name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="16" readonly="readonly" value="${param.endTime}"> </td>
	<td><input type="submit" value="查询"  class="a"/></td>
</tr>
</table>
</form>

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
    <td><s:if test="auditStatus=='正在审核'"><a href="#" onclick="checkaudit('${id}')">${auditStatus}</a> </s:if><s:else>${auditStatus}</s:else></td>
    <td><a href="#" onclick="getDetail('${userName}','<s:property value="beginDate.toString().substring(0,10)"/>','<s:property value="endDate.toString().substring(0,10)"/>');">查询</a> </td>
  </tr>
  </s:iterator>
  </table>
  <script type="text/javascript">
  	function checkaudit(id){
  		 if (confirm("确定要审核此费用")){ 
  			window.location.href="report!updateaudit.action?id="+id;
  			return true;
  		}else{   
  			return false;
  		}
  	}
  	function getDetail(userName,beginDate,endDate){
  		window.open("report!auditdetail.action?userName="+userName+"&beginDate="+beginDate+"&endDate="+endDate);
  	}
  </script>
</body>
</html>
