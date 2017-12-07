<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<link href="../../styles/style.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
function frmOnAdd(frm){
	if(frm.recordBh.value=='' && frm.houseId.value=='')
	{
		alert("请您填写查询条件!");
		return false;
	}
	
	return true;
}
</script>

<%@ include file="/commons/meta.jsp" %>
<body>

  <table width="97%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
  
<TR>
    <TH height="25" align="left">缴费收据单查询(只支持单一模式查询)</TH>
</TR>
</table>
  <form method="post" action="pay!viewreceipt.action" target="_blank" name="frmAdd" onSubmit="return frmOnAdd(this)">
  	<input type="hidden" name="toType" value="${param.toType}"/>
    <table width="97%" border="0" align="center" cellspacing="0" cellpadding="0" id="edittable" class="tablegray"> 
    <tr>
    	<td width="20%" height="30"  align="right">查询缴费收据单号：&nbsp;</td>
    	<td width="30%"><input type="text" id="recordBh" name="recordBh" /></td>
  
    </tr>
     <tr>
    	<td width="20%" height="30"  align="right">房间编号：&nbsp;</td>
    	<td width="30%"><input type="text" id="houseId" name="houseId" /></td>
    </tr>
    
    
   <tr>
   		<td height="30"></td>
   		<td colspan="3">
   			<input type="submit" name="submit" value="确定提交"class="a"/>
   		</td>
    </tr>
    </table>
  </form>
  </body>
  </html>