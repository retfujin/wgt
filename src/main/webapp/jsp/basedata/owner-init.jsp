<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<body>
<script language="javascript">
function formCheck()
{

if(document.form1.theFile.value == "")
	{
		alert("请选择要导入的文件！");
		return false;
	}
	return true;
}
</script>
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
	String[] menuModel = { "menuModel2.addItem(203,'列表','','owner!list.action',false);" };
%>
<%@ include file="/menubar/simple/aa.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tablegray">
	<TR>
		<TH height="25" align="left">基础资料导入导出</TH>
	</TR>
	<tr>
		<td><font color="red">批量导入注意事项：</font><br>
		&nbsp;&nbsp;&nbsp;&nbsp;1.上传文件前请先下载系统提供的“基础信息模板”！<br>
		&nbsp;&nbsp;&nbsp;&nbsp;2.上传只能添加新的内容，请注意不要重复导入房间业主信息。请按照格式添加，已有的字段内容不要修改。<br>
		&nbsp;&nbsp;&nbsp;&nbsp;3.上传文件类型只能为Excel文件！<br>
		<br>
		</td>
	</tr>
	<tr>
		<td height="40" align="center">
		<form action="owner!downExcel.action" method="post" name="form2">
		请选择小区<s:select list="viewList" name="areaId" listKey="id"
			listValue="areaName" theme="simple"></s:select> 请下载信息模版，填写完毕后上传。 <input
			name="cc" value="下载基础信息模板" type="submit" /></form>
		</td>
	</tr>

	<tr>
		<td height="40" align="center">
		<form action="owner!uploadExcel.action" method="post"
			enctype="multipart/form-data" name="form1"
			onSubmit="return formCheck()">&nbsp;基础客户资料导入：<input
			type="file" name="theFile" id="file" /> <input name="dd"
			type="submit" value="导入" /><br />

		</form>
		</td>
	</tr>

</table>
</body>
</html>
