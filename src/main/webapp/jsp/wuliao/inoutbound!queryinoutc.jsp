<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>

<%@ include file="/commons/meta.jsp" %>
  <body>
  <table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
  
<TR>
    <TH height="25" align="left">出入库单查询</TH>
</TR>
</table>
  <form method="post" action="inoutbound!queryInOutR.action" name="frmAdd" onSubmit="return frmOnAdd(this)">
    <table width="80%" border="0" align="center" cellspacing="0" cellpadding="0" id="edittable" class="tablegray"> 
    <tr>
    	<td width="20%" height="30"  align="right">选择仓库：&nbsp;</td>
    	<td width="30%">
			<s:select name="storeHouseId" id="storeHouseId" headerKey="-1" headerValue="全部"  listKey="id" listValue="name" list="viewList" theme="simple"></s:select><font color="red">*</font>
    	</td>
    </tr>
    <tr>
    	<td width="20%" height="30"  align="right">入出类型：&nbsp;</td>
    	<td width="30%">
			<s:select name="inOutType" id="inOutType" headerKey="-1" headerValue="全部"  list="#{'采购入库':'采购入库','建账入库':'建账入库','领料入库':'领料入库','领料出库':'领料出库','退货出库':'退货出库','领料退库':'领料退库','领料退库':'领料退库','报损出库':'报损出库'}" theme="simple"></s:select><font color="red">*</font>
    	</td>
    </tr>
    <tr>
    	<td width="20%" height="30"  align="right">选择时间段：&nbsp;</td>
    	<td width="30%">
    	  <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="beginRq"/>至 
    	  <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="endRq"/>&nbsp;<font color="red">*</font>
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
 <script type="text/javascript">
 function frmOnAdd(frm){
 	if(frm.beginRq.value==''){
 		alert("起始时期不能为空");
 		return false;
 	}
 	if(frm.endRq.value==''){
 		alert("截至时期不能为空");
 		return false;
 	}
 	return true;
 
 }
 
 </script>
  </html>