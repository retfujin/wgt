<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<title>列表</title>
</head>
<body>
<%
	String areaId = request.getParameter("areaId") != null ? request
			.getParameter("areaId") : "";
	String edificeId = request.getParameter("edificeId") != null
			? request.getParameter("edificeId")
			: "";
%>
<table width="100%" border="0" align="center" cellspacing="1"
	class="tableBorder">
	<tr>
		<th height="25" colspan="10" class="tableHeaderText">楼栋列表</th>
	</tr>
	<tr align="center">
		<td class="forumRow">楼栋编号</td>
		<td class="forumRow">楼栋名称</td>
		<td class="forumRow">所属小区</td>
		<td class="forumRow">单元数</td>
		<td class="forumRow">建筑面积</td>
		<td class="forumRow">层数</td>
		<td class="forumRow">用途</td>
		<td class="forumRow">概要</td>
		<td class="forumRow">操作</td>
	</tr>
	<s:iterator value="page.result" status="stuts">
		<tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if>
			<s:else>class="td_t"</s:else>>
			<s:set var="url" value="@java.net.URLEncoder@encode(id)" />
			<td>${id}&nbsp;</td>
			<td>${edificeName}&nbsp;</td>
			<td>${area.areaName}&nbsp;</td>
			<td id="b">${cellNum}</td>
			<td id="a">${buildNum}</td>
			<td>${layerNum}&nbsp;</td>
			<td>${edificeUse}</td>
			<td><s:if
				test="generalsituation!=null&&generalsituation.length()>6">
				<s:property value="generalsituation.substring(0,6)" />...</s:if>&nbsp; <s:else>
				<s:property value="generalsituation" />&nbsp;</s:else></td>
			<td><a href="edifice!edit.action?entity.id=${url}"><img
				src="/images/edit.gif" alt="编辑" border="0" /></a>&nbsp;&nbsp; <a
				href="edifice!del.action?entity.id=${url}"
				onclick="javascript:return confirm('确定要删除吗')"><img
				src="/images/del.gif" alt="删除" border="0" /></a></td>

		</tr>
	</s:iterator>
	<tr bgcolor="#d9d9d9">
		<td>合计</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td id="bsum" align="center">&nbsp;</td>
		<td id="asum" align="center">&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
<div id="footer" style="margin-top: 10px" align="center">
第${page.pageNo}页, 共${page.totalPages}页 <s:if test="page.hasPre">
	<a
		href="edifice!list.action?areaId=<%=areaId%>&edificeId=<%=edificeId%>&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
</s:if> <s:if test="page.hasNext">
	<a
		href="edifice!list.action?areaId=<%=areaId%>&edificeId=<%=edificeId%>&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
</s:if></div>
</body>

<script type="text/javascript">
	function doSum(tt, ttToal) {
		if (tt.length != undefined) {
			var sumrow = 0.0;
			for ( var i = 0; i < tt.length; i++) {
				var tmpVal = tt[i].innerHTML;
				if (!isNaN(tmpVal) == true)
					sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);
			}
			ttToal.innerHTML = sumrow.toFixed(2);
		} else {
			ttToal.innerHTML = tt.innerHTML;
		}
	}
	doSum(a, asum);
	doSum(b, bsum);
</script>
</html>
