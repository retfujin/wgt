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
	String userName= request.getParameter("userName") !=null ? request.getParameter("userName"):"";
	String beginDate= request.getParameter("beginDate") !=null ? request.getParameter("beginDate"):"";
	String endDate= request.getParameter("endDate") !=null ? request.getParameter("endDate"):"";
	
	String[] menuModel={};
%> 
<%@ include file="/menubar/simple/aa.jsp" %>
 
<table width="98%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="25" colspan="20" class="tableHeaderText">报账明细详情表</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30" >序号</td>
    	<td class="forumRow" >收费名称</td>
    	<td class="forumRow" >收费月份</td>
    	<td class="forumRow" >收费类型</td>
    	<td class="forumRow" >收费金额</td>
    	<td class="forumRow" >缴费日期</td>
    	<td class="forumRow" ></td>
   </tr>
  <s:iterator value="page.result" status="stuts">
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>   
   	<td height="22">&nbsp;${stuts.index+1}</td>
    <td>${chargeName}</td>
    <td><s:property value="recordMonth.toString().substring(0,7)"/> </td>
    <td><s:if test="factMoney<0">退费</s:if><s:else><s:property value="payType"/></s:else> </td>
    <td>${factMoney}</td>
    <td><s:property value="gatheringTime.toString().substring(0,10)"/></td>
    <td></td>
  </tr>
  </s:iterator>
  </table>
  
   <div style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="report!auditdetail.action?userName=<%=userName %>&beginDate=<%=beginDate %>&endDate=<%=endDate %>&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="report!auditdetail.action?userName=<%=userName %>&beginDate=<%=beginDate %>&endDate=<%=endDate %>&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>
</body>
</html>
