<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head> 
</head>
<style   media="print">   
  .Noprint{display:none;}   
  .PageNext{page-break-after:   always;} 
</style>
<script type="text/javascript">
function p(){
	document.all.WebBrowser1.ExecWB(7,1);
	
}
</script>
<%@ include file="/commons/meta.jsp" %>
<OBJECT id="WebBrowser1" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" VIEWASTEXT></OBJECT> 
<body>
&nbsp;&nbsp;<br></br>
<table width="90%" border="0" id="Active_Table" class="table_border"  align="center" cellpadding="0" cellspacing="0">
  <tr ><td align="center" class="tlr" colspan="4" height="100"><font style="font-size: 22px;font-weight: bolder;"><B>客户请修登记表</B></font></td></tr>
  <tr>
  	<td class="tdh" height="50px" align="center" width="22%">管理处</td>
  	<td class="tdr" >&nbsp;&nbsp;<s:property value="customerrepair.areaName"/> </td>  	
  	<td class="tdr" width="15%" align="center">房间编号</td>
  	<td class="tdr">&nbsp;&nbsp; <s:property value="customerrepair.houseId"/></td>
  
  </tr>
  <tr>
  	<td class="tds" height="50px" align="center" width="22%">填表日期</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:property value="customerrepair.recordMonthString"/></td>
  	<td class="tdbr" align="center">请修地址</td>
  	<td class="tdbr">&nbsp;&nbsp;<s:property value="customerrepair.comaddress"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px"  align="center">受理日期</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.acceptedDate"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px" align="center">客户姓名、联系电话及地址</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.content"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px"  align="center">请修内容</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.repairContext"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px" align="center">预约时间</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.reserveDate"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px" align="center">派工单号</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.dispatcherNum"/></td>
  </tr>
   <tr>
  	<td class="tds" height="50px" align="center">维修人员</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.wxname"/></td>
  </tr>
  <tr>
	<td class="tds" height="50px"  align="center">维修过程</td>
	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:property value="customerrepair.repairprocess"/></td>
 </tr>
 <tr>
  	<td class="tds" height="50px"  align="center">完成时间</td>
  	<td class="tdbr" colspan="3">&nbsp;&nbsp;<s:property value="customerrepair.achieveDate"/></td>
  </tr>
   <tr>
  	<td class="tds" height="50px"  align="center">维修状态</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:property value="customerrepair.repairStatus"/></td>
  	<td class="tdbr" align="center">服务状态</td>
  	<td class="tdbr">&nbsp;&nbsp;<s:property value="customerrepair.paidService"/></td>
  </tr>
   <tr>
  	<td class="tds" height="50px"  align="center">维修结果</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:property value="customerrepair.repairResult"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px"  align="center">回访时间</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:property value="customerrepair.reciprocal"/></td>
  </tr>
  <tr>
  	<td class="tds" height="50px"  align="center">回访结果</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:property value="customerrepair.visitResult"/></td>
  </tr>
  </table>
<script type="text/javascript">p();</script>
</body>
</html>
