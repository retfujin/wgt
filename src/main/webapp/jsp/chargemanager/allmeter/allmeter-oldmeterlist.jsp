<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
</head>
<body>

  <table width="100%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
     	<th height="25" colspan="20" class="tableHeaderText">总表更换历史</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30">序号</td>
    	<td class="forumRow">表编号</td>
    	<td class="forumRow">表类型</td>
    	<td class="forumRow">表位置</td>
    	<td class="forumRow">用途</td>
    	<td class="forumRow">倍率</td>
    	<td class="forumRow">期初数据</td>
    	<td class="forumRow">启用日期</td>
    	<td class="forumRow">期末表数</td>
    	<td class="forumRow">更换人员</td>
    	<td class="forumRow">更换日期</td>
    	<td class="forumRow">更换原因</td>
   </tr>
  <s:iterator value="page.result" status="stuts">
  <tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
  	<td height="22">${stuts.index+1}</td>
   	<td >${meterCode}</td>
   	<td >${meterType}</td>
   	<td >${local}</td>
   	<td >${useFor}</td>
   	<td >${times}</td>
   	<td >${initRecord}</td>
   	<td ><s:if test="beginTime!=null"><s:property value="beginTime.toString().substring(0,10)" /></s:if></td>
   	<td >${lastRecord}</td>
   	<td >${changeName}</td>
   	<td ><s:if test="changeTime!=null"><s:property value="changeTime.toString().substring(0,10)"/></s:if></td>
   	<td >${changeReason}</td>
  </tr>
  </s:iterator>
  </table>
<div style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="allmeter!oldmeterlist.action${requestScope.paramString}&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="allmeter!oldmeterlist.action${requestScope.paramString}&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>
</body>
</html>
