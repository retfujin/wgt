<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
	<script src="/js/jquery.js" type="text/javascript"></script>
	<script type="text/javascript">
	function getEdifice(){
		$("#edifice").load("report!getEdifice.action?areaId="+$("#areaId").val());
	}
</script>
</head>
<body>
<% 	
	String areaId = request.getParameter("areaId") != null ? request.getParameter("areaId") : "";
	String edificeId = request.getParameter("edificeId") !=null ? request.getParameter("edificeId") : "";
	String year = request.getParameter("year") != null ? request.getParameter("year") : "";
	String[] menuModel={};
%>
<%@ include file="/menubar/simple/aa.jsp" %>

<form name="form1" action="report!chargelist.action"  method="post">
<table width="97%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
	<td>请选择管理处：<s:select list="retList" name="areaId" listKey="id" listValue="areaName" onchange="getEdifice()" theme="simple"></s:select> </td>
	<td>请选择楼栋：</td><td><div id="edifice"><s:select name="edificeId" id="edificeId" list="elist" listKey="id" listValue="edificeName" headerKey="" headerValue="-全部楼栋-" theme="simple"/> </div></td>
	<td>收费年度：<s:select list="#{2008:2008,2009:2009,2010:2010,2011:2011,2012:2012,2013:2013,2014:2014,2015:2015}" name="year" id="year" theme="simple"></s:select> </td>
    <td><input type="submit" value="查询"  class="a"/></td>
</tr>
</table>
</form>
<table width="1024"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="28" colspan="21" class="tableHeaderText">物业管理处收费台帐</th>
  </tr>
  <tr align="center">
   		<td class="forumRow" rowspan="2">序号</td>
   		<td class="forumRow" rowspan="2">业主<br>姓名</td>
   		<td class="forumRow" rowspan="2">楼房号</td>
   		<td class="forumRow" rowspan="2">面积</td>
   		<td class="forumRow" rowspan="2">收费标准<br>(元/m²)</td>
   		<td class="forumRow" rowspan="2">每月<br>应收</td>
   		<td class="forumRow" rowspan="2">&lt;&nbsp;<s:property value="year"/>年</td>
   		<td class="forumRow" colspan="12"><s:property value="year"/>年</td>
   		<td class="forumRow" rowspan="2">实收<br>合计</td>
   		<td class="forumRow" rowspan="2">优惠<br>金额</td>  		
  </tr>
  <tr align="center">
  		<td class="forumRow" >1月</td>
  		<td class="forumRow" >2月</td>
  		<td class="forumRow" >3月</td>
  		<td class="forumRow" >4月</td>
  		<td class="forumRow" >5月</td>
  		<td class="forumRow" >6月</td>
  		<td class="forumRow" >7月</td>
  		<td class="forumRow" >8月</td>
  		<td class="forumRow" >9月</td>
  		<td class="forumRow" >10月</td>
  		<td class="forumRow" >11月</td>
  		<td class="forumRow" >12月</td>
  </tr>
  <s:iterator value="viewList" status="stuts">
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
   	<td height="22">${stuts.index+1}</td>
   	<td ><s:property value="viewList[#stuts.index][0]"/></td>
   	<td ><a href="report!chargehouseid.action?houseId=<s:property value="viewList[#stuts.index][32]"/>&subHouseId=<s:property value="viewList[#stuts.index][1]"/>" target="_blank"><s:property value="viewList[#stuts.index][1]"/></a></td>
   	<td ><s:property value="viewList[#stuts.index][2]"/></td>
   	<td ><s:property value="viewList[#stuts.index][3]"/></td>
   	<td ><s:property value="viewList[#stuts.index][4]"/></td>
   	<td ><s:if test="viewList[#stuts.index][5]>0"><font color="red"><s:property value="viewList[#stuts.index][5]"/></font></s:if></td>
   	<td ><s:if test="viewList[#stuts.index][6]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][6]"/>"><s:property value="viewList[#stuts.index][20]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][20]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][7]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][7]"/>"><s:property value="viewList[#stuts.index][21]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][21]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][8]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][8]"/>"><s:property value="viewList[#stuts.index][22]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][22]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][9]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][9]"/>"><s:property value="viewList[#stuts.index][23]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][23]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][10]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][10]"/>"><s:property value="viewList[#stuts.index][24]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][24]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][11]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][11]"/>"><s:property value="viewList[#stuts.index][25]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][25]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][12]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][12]"/>"><s:property value="viewList[#stuts.index][26]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][26]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][13]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][13]"/>"><s:property value="viewList[#stuts.index][27]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][27]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][14]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][14]"/>"><s:property value="viewList[#stuts.index][28]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][28]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][15]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][15]"/>"><s:property value="viewList[#stuts.index][29]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][29]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][16]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][16]"/>"><s:property value="viewList[#stuts.index][30]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][30]"/></font></s:else> </td>
   	<td ><s:if test="viewList[#stuts.index][17]>0"><font color="red" title="欠费金额为<s:property value="viewList[#stuts.index][17]"/>"><s:property value="viewList[#stuts.index][31]"/></font></s:if><s:else><font color="green"><s:property value="viewList[#stuts.index][31]"/></font></s:else> </td>
   	<td ><s:property value="viewList[#stuts.index][18]"/></td>
   	<td ><s:property value="viewList[#stuts.index][19]"/></td>
  </tr>
  </s:iterator>
</table>
 <div style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="report!chargelist.action?page.pageNo=${page.prePage}&areaId=${areaId}&edificeId=${edificeId}&year=${year}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="report!chargelist.action?page.pageNo=${page.nextPage}&areaId=${areaId}&edificeId=${edificeId}&year=${year}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>
</body>
</html>