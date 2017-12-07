<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<link href="../styles/style.css" rel="stylesheet" type="text/css">
<body>
<%String type=request.getParameter("type");%>
<br>
<div align="center">请选择左侧小区名称进行发送信息！</div>
<form name="form3" >
<table align="center">	
<tr>
	<td><input type="hidden" name="retValue" value="allArea"></td>
</tr>
<tr>
	<td align="center"><input type="button" value="返回" class="a" onClick="top.close()"></td>
</tr>
</table>
</form>
</body>
</html>