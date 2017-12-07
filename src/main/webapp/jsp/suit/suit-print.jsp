<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head> 
<style   media="print">   
  .Noprint{display:none;}   
  .PageNext{page-break-after:   always;} 
</style>
<script type="text/javascript">
function p(){
	document.all.WebBrowser1.ExecWB(7,1);
	
}
</script>
</head>
<%@ include file="/commons/meta.jsp" %>
<OBJECT id="WebBrowser1" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" VIEWASTEXT></OBJECT>
<body>

<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" >
 <tr><td align="center" height="100" colspan="3"><font style="font-size: 22px;font-weight: bolder;">客户投诉受理登记表</font></td></tr>
 <tr>
  <td align="left" height="30" width="60%">项目部：<s:property value="entity.areaName"/></td>
  <td align="right" width="15%">填表时间：</td>
  <td>&nbsp;&nbsp;<s:property value="entity.suitTime" /></td>
 </tr> 
</table>
<table width="90%" border="0" id="Active_Table" class="table_border"  align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td class="tdh" height="50px" align="center" width="22%">客户姓名</td>
  	<td class="tdr" width="30%">&nbsp;&nbsp;<s:property value="entity.suitName" /></td>
    <td class="tdr"  align="center" width="15%">联系电话</td>
    <td class="tdr">&nbsp;&nbsp;<s:property value="entity.suittel" /></td>
  </tr>
  <tr>
  	<td class="tds" height="50px"  align="center">客户地址</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:property value="entity.suitDepartment" /></td>
    <td class="tdbr" width="10%" align="center">投诉方式</td>
    <td class="tdbr" >&nbsp;&nbsp;<s:property value="entity.suitWay" /></td>
  </tr>
   <tr>
  	<td class="tds" height="50px" align="center">投诉时间</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:property value="entity.suitDate" /></td>
    <td class="tdbr"  align="center">投诉类型</td>
    <td class="tdbr" >&nbsp;&nbsp;<s:property value="entity.suitType" /></td>
  </tr>
   <tr>
  	<td class="tds" height="140px" rowspan="2" align="center" >投诉内容</td>
  	<td class="r" colspan="3">&nbsp;&nbsp;<s:property value="entity.suitMess" /></td>
  </tr>
  <tr>
  	<td class="b" height="26px" colspan="2" align="right">&nbsp;&nbsp;记录人：<s:property value="entity.name1" /></td>
  	<td class="tdbr" align="center" width="50%">&nbsp;&nbsp;日期：<s:property value="entity.suitDate1" /></td>
  </tr>
  <tr>
  	<td class="tds" height="140px" rowspan="2" align="center" >处理过程</td>
  	<td class="r" colspan="3">&nbsp;&nbsp;<s:property value="entity.investigationState"/></td>
  </tr>
  <tr>
  	<td class="b" height="26px" colspan="2" align="right">&nbsp;&nbsp;处理人：<s:property value="entity.name2"/></td>
  	<td class="tdbr" align="center">&nbsp;&nbsp;日期：<s:property value="entity.suitDate2"/></td>
  </tr>
  <tr>
  	<td class="tds" height="140px" rowspan="2" align="center" >回访情况</td>
  	<td class="r" colspan="3">&nbsp;&nbsp;<s:property value="entity.processState"/></td>
  </tr>
  <tr>
  	<td class="b" colspan="2" height="26px"  align="right">&nbsp;&nbsp;回访人：<s:property value="entity.name3"/></td>
  	<td class="tdbr" align="center">&nbsp;&nbsp;日期：<s:property value="entity.suitDate3"/></td>
  </tr>
  </table>
  <script type="text/javascript">p();</script>
</body>
</html>
