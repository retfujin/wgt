<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<script src="/js/hanx.js" type="text/javascript"></script>
<body>

<%
	String areaId = request.getParameter("areaId") != null
			&& !request.getParameter("areaId").equals("") ? request
			.getParameter("areaId") : "0";
	String[] menuModel = {"menuModel2.addItem(209,'返回','','carport!payList.action',false);"};
%>
<%@ include file="/menubar/simple/aa.jsp"%>
<!-- 隐藏的筛选条件 -->
<div id="disSel">
<form name="form1" action="carport!getHistoryCharge.action" method="post">
<table width="100%" border="0" align="center" cellpadding="2"
	cellspacing="1" class="tableBorder">
	<tr>
		<td width="7%">&nbsp;&nbsp;小区：</td>
		<td width="15%"><s:select id="areaId" name="areaId"
			list="retList" headerKey="" headerValue="=请选择=" listKey="id"
			listValue="areaName" theme="simple" /></td>
		<td>房间：</td>
		<td><input type="text" name="houseId"></td>
		<td><input type="submit" value="查询" class="a" /></td>
	</tr>
</table>
</form>
</div>
<form name="car_add" method="post" action="carport!savePayMoney.action"
	onsubmit="return subchk()">
<table width="100%" border="0" align="center" cellpadding="2"
	cellspacing="1" class="tableBorder1">
	<tr>
		<th height="25" colspan="13" class="tableHeaderText">车位交费列表</th>
	</tr>
	<tr align="center">
		<td class="forumRow">房间</td>
		<td class="forumRow">业主</td>
		<td class="forumRow">费用名称</td>
		<td class="forumRow">总计应收</td>
		<td class="forumRow">总计已收</td>
		<td class="forumRow">总计欠收</td>
	</tr>
	<s:iterator value="viewList" status="stuts">
		<tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if>
			<s:else>class="td_t"</s:else>>
			<td><s:property value="viewList[#stuts.index][0]" /></td>
			<td><s:property value="viewList[#stuts.index][1]" /></td>
			<td><s:property value="viewList[#stuts.index][2]" /></td>
			<td><s:property value="viewList[#stuts.index][3]" /></td>
			<td><s:property value="viewList[#stuts.index][4]" /></td>
			<td><s:property value="viewList[#stuts.index][5]" /></td>
		</tr>
	</s:iterator>

	<s:if test="page.totalCount>0">
		<tr>
			<td colspan="4">&nbsp;</td>
			<td align="center" id="asum"></td>
			<td align="center" id="csum"></td>
			<td align="center" id="bsum"></td>
		</tr>
	</s:if>
	<s:else>
		<tr>
			<td colspan="5">没有查询到交费记录</td>
		</tr>
	</s:else>
</table>
</form>
<div id="pageBar"></div>
<script type="text/javascript">
	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
</script>
<script type="text/javascript">
function show1()
{
	if(disSel.style.display=="none")
	{	
		disSel.style.display="block";
	}else
		disSel.style.display="none";
}

function  doSum(tt,ttToal){
	if(tt.length!=undefined){
	  var   sumrow=0.0;  	
	  for(var i=0;i< tt.length;i++)
	  {
		var tmpVal= tt[i].innerHTML;
//	  	if(isNaN(tmpVal)==true)
//	  	  	alert("ta:"+tmpVal);
	  	sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);   
	  }
	  ttToal.innerHTML = sumrow.toFixed(2);
	}else{
		ttToal.innerHTML=tt.innerHTML;
	}  
}
doSum(a,asum);
doSum(b,bsum);
doSum(c,csum);

</script>
</body>
</html>
