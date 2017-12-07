<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
</head>
<body>
<% 	
	String areaId=request.getParameter("areaId")!=null?request.getParameter("areaId"):"";
	String[] menuModel={"menuModel2.addItem(203,'列表','','advance!advancelist.action',false);"
            			+"menuModel2.addItem(204,'新增','','advance!advanceInput.action',false);"	
					};
	
	//单据号自动生成
	//	String bh = session.getAttribute("userId")+"E"+System.currentTimeMillis();
	String bh="";
%>
<%@ include file="/menubar/simple/aa.jsp" %>


<!-- 隐藏的筛选条件 -->
<div id ="disSel">
<form name="form1" action="advance!advancelist.action"  method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
	<td>管理处：</td>
	<td><s:select id="areaId" name="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"/></td>
	<td>房间编号：<s:textfield name="houseId" theme="simple"></s:textfield> </td>
	<td>业主姓名：<s:textfield name="ownerName" theme="simple"></s:textfield> </td>
	<td><input type="submit" value="查询"  class="a"/>
	</td>
</tr>
</table>
</form>
</div>

<table width="98%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="25" colspan="20" class="tableHeaderText">客户预交</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30" >序号</td>
    	<td class="forumRow" >收据单号</td>
    	<td class="forumRow" >房间编号</td>
    	<td class="forumRow" >客户名称</td>
    	<td class="forumRow" >名称</td>
    	<td class="forumRow" >预缴金额</td>
    	<td class="forumRow" >已用金额</td>
    	<td class="forumRow" >剩余金额</td>
    	<td class="forumRow" >类型</td>
    	<td class="forumRow" >缴费日期</td>
    	<td class="forumRow" >收款人</td>
    	<td class="forumRow" >备注</td>
    	<td class="forumRow" >操作</td>
   </tr>
  <s:iterator value="page.result" status="stuts">
  <tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>   
   	<td height="22">&nbsp;${stuts.index+1}</td>
   	 <td>${bh}</td>
    <td>${houseId}</td>
    <td>${ownerName }</td>
    <td>${chargeName }</td>
    <td >${antimoney}</td>
    <td >${useMoney}</td>
    <td >${money}</td>
    <s:if test="antimoney<0">
    	<td>退费</td>
    </s:if>
    <s:else>
    	<td>${bigType}</td>
    </s:else>
    <td><s:property value="recordTime.toString().substring(0,10)"/></td>
    <td>${userName}</td>
    <td>${remark}</td>
    <td>
     <s:if test="!\"Y\".equals(type)&&money>0">
      <a href="#"  onclick="gotoRefund('${id}','${money}')">退费</a>
     </s:if>
   
    </td>
  </tr>
  </s:iterator>
  </table>
   <div style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="advance!advancelist?areaId=<%=areaId %>&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="advance!advancelist?areaId=<%=areaId %>&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>
</body>
<script type="text/javascript">
function gotoRefund(id,r){
	var url ="advance!refund.action?id="+id+"&bh=<%=bh%>"; 
	 if (confirm("确定要退还"+r+"金额吗?")){
		window.location.href=url;
	 }
	
}
</script>
</html>