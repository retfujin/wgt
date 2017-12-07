<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>

<%@ include file="/commons/meta.jsp" %>
  <body>
  <Br>
  <table width="97%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
  
<TR>
    <TH height="25" align="center">物料库存查询</TH>
</TR>
</table>
  <form method="post" action="inoutbound!queryStocksR.action" name="frmAdd">
    <table width="97%" border="0" align="center" cellspacing="0" cellpadding="0" id="edittable" class="tablegray"> 
    <tr>
    	<td width="20%" height="30"  align="right">选择仓库：&nbsp;</td>
    	<td width="30%">
			<s:select name="storeHouseId" id="storeHouseId" headerKey="-1" headerValue="全部"  listKey="id" listValue="name" list="viewList" theme="simple"></s:select><font color="red">*</font>
    	</td>
    </tr>
    <tr>
    	<td width="20%" height="30"  align="right">选择物料：&nbsp;</td>
    	<td width="30%">
    	  <s:select name="itemId" id="itemId" headerKey="-1" headerValue="全部"  listKey="id" listValue="name" list="viewList1" theme="simple"></s:select><font color="red">*</font>
    	</td>
    </tr>
   <tr>
   		<td height="30"></td>
   		<td colspan="3">
   			<input type="submit" name="submit" value="开始查询"class="a"/>
   		</td>
    </tr>
    </table>
  </form>
  </body>
  </html>