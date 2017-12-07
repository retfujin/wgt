<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<title>列表</title>
</head>
<script type="text/javascript" src="/js/check.js"></script>
<body>
<%
	com.acec.core.utils.CharTools charTools = new com.acec.core.utils.CharTools();
	String areaId = request.getParameter("areaId") != null ? request
			.getParameter("areaId") : "";
	String houseId = request.getParameter("houseId") != null ? request
			.getParameter("houseId") : "";
	String ownerName = request.getParameter("ownerName") != null ? request
			.getParameter("ownerName") : "";
	String hidd = request.getParameter("hidd");
	if (null == hidd)
		ownerName = new String(ownerName.getBytes("ISO8859-1"), "UTF-8");
	String[] menuModel = { "menuModel2.addItem(204,'新增','','owner!add.action',false);"
			+ "menuModel2.addItem(206,'客户批量导入','','owner!init.action',false);" 
			+ "menuModel2.addItem(208,'入住率统计','','owner!ratelist.action',false);" };
%>
<%@ include file="/menubar/simple/aa.jsp"%>

<div id="disSel">
<form name="form1" action="owner!list.action" method="post">
<table width="100%" border="0" align="center" cellpadding="2"
	cellspacing="1" class="tableBorder">
	<tr>
		<td>管理处名称：<s:select name="areaId" list="retList" listKey="id"
			listValue="areaName" headerKey="" headerValue="--请选择管理处--"
			theme="simple" /></td>
		<td>房间名称：<s:textfield name="houseId" id="houseId" theme="simple"/></td>
		<td>客户姓名：<s:textfield name="ownerName" id="ownerName" theme="simple"/></td>
		<td><input type="submit" value="查询" class="a" /></td>
	</tr>
</table>
</form>
</div>
<table wnewsidth="100%" border="0" align="center" cellpadding="2"
	cellspacing="1" class="tableBorder">
	<tr>
		<th height="25" colspan="13" class="tableHeaderText">小区客户一览表</th>
	</tr>
	<tr align="center">
		<td class="forumRow">序号</td>
		<td class="forumRow">家庭住址</td>
		<td class="forumRow">姓名</td>
		<td class="forumRow">性别</td>
		<td class="forumRow">年龄</td>
		<td class="forumRow">工作单位</td>
		<td class="forumRow">固定电话</td>
		<td class="forumRow">移动电话</td>
		<td class="forumRow">当前状态</td>
		<td class="forumRow">房间状态</td>
		<td class="forumRow">入伙日期</td>
		<td class="forumRow">操作</td>

	</tr>
	<s:iterator value="viewList" status="stuts">
		<tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if>
			<s:else>class="td_t"</s:else>>
			<td>${stuts.index+1 }</td>
			<td><s:property value="house.id" /></td>
			<td><s:property value="ownerName" /></td>
			<td><s:property value="sex" /></td>
			<td><s:property value="age" /></td>
			<td><s:property value="job" /></td>
			<td><s:property value="phone" /></td>
			<td><s:property value="mobTel" /></td>
			<td><s:property value="occupancyType" /></td>
			<td><s:property value="house.isSale" /></td>
			<td><s:if test="inDate!=null">
				<s:property value="inDate.toString().substring(0,10)" />
			</s:if></td>


			<td><a href="owner!edit.action?id=${id}"><img
				src="/images/edit.gif" alt="编辑" border="0" /></a>&nbsp;&nbsp;<a
				href="#" onclick="javascript:del('owner!del.action?id=${id}')"><img
				src="/images/del.gif" alt="删除" border="0" /></a>&nbsp;&nbsp;</td>
		</tr>
	</s:iterator>
</table>
<div id="pageBar"></div>
<script type="text/javascript">
	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
</script>
<!-- 
<div id="footer" style="margin-top:10px" align="center">
	第${page.pageNo}页, 共${page.totalPages}页 
	<s:if test="page.hasPre">
		<a href="owner!listowner?areaId=<%=areaId%>&houseId=<%=houseId%>&ownerName=<%=ownerName%>&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
	</s:if>
	<s:if test="page.hasNext">
		<a href="owner!listowner?areaId=<%=areaId%>&houseId=<%=houseId%>&ownerName=<%=ownerName%>&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
	</s:if>
</div>-->
</body>
</html>
