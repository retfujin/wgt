<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<%@ include file="/commons/meta.jsp" %>
</head>
<body>
<% 	
	com.acec.core.orm.Page page1 = (com.acec.core.orm.Page)request.getAttribute("page");
	int pageNo =1;
	if(page1!=null)
		pageNo = page1.getPageNo();
	String[] menuModel={};
%>
<%@ include file="/menubar/simple/aa.jsp" %>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="tableBorder1">
  <tr>
	 <th height="28" colspan="20" class="tableHeaderText">物业费管理处收费台账</th>
  </tr>
  <tr align="center">
   		<td class="forumRow" height="22">序号</td>
   		<td class="forumRow" >业主姓名</td>
   		<td class="forumRow" >楼房号</td>   		
   		<td class="forumRow" >发票编号</td>   		
   		<td class="forumRow" >收款日期</td>
   		<td class="forumRow" >收费月份</td>
   		<td class="forumRow" >应收款</td>
   		<td class="forumRow" >已收款</td>
   		<td class="forumRow" >优惠金额</td>
   		<td class="forumRow" >欠收款</td>
   </tr>
  <s:iterator value="viewList" status="stuts">
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
   	<td height="22">${stuts.index+1}</td>
   	<td ><s:property value="viewList[#stuts.index][0]"/></td>
   	<td ><s:property value="viewList[#stuts.index][1]"/></td>
   	<td ><s:property value="viewList[#stuts.index][6]"/></td>
   	<td ><s:property value="viewList[#stuts.index][7]"/></td>
   	<td ><s:property value="viewList[#stuts.index][2]"/></td>
   	<td ><s:property value="viewList[#stuts.index][3]"/></td>
   	<td ><s:property value="viewList[#stuts.index][4]"/></td>
   	<td ><s:if test="viewList[#stuts.index][8]>0"><s:property value="viewList[#stuts.index][8]"/></s:if></td>
   	<td ><s:property value="viewList[#stuts.index][5]"/></td>
  </tr>
  </s:iterator>
</table>
   <div style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="report!chargehouseid.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="report!chargehouseid.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>
</body>
</html>