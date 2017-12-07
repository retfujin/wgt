<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html><base target="_self"/>
<head> 
</head>
<%@ include file="/commons/meta.jsp" %>
<body>
<form method="post" action="repair!savecustomerrepair.action" name="formadd" onsubmit="return checkTable();">
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" >
 <tr ><td align="center" colspan="2" height="45"><font style="font-size: 18px;font-weight: bolder;">客户请修登记表</font></td></tr>
</table>
<table width="80%" border="0" id="Active_Table" class="table_border"  align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td class="tdh" height="42px" align="center" width="22%">管理处</td>
  	<td class="tdr" >&nbsp;&nbsp;
  	<s:select name="customerrepair.areaId"  id="areaId" list="retList" headerKey="-1" headerValue="==请选择小区==" listKey="id" cssClass="con_input_con"  listValue="areaName" theme="simple"></s:select>&nbsp;&nbsp;<font color="red">*</font></td>
  	<td class="tdr" align="center">房间编号</td>
  	<td class="tdr" colspan="3">&nbsp;&nbsp;<s:textfield name="customerrepair.houseId" readonly="true" id="houseId" theme="simple"></s:textfield> </td>
  
  </tr>
  <tr>
  	<td class="tds" height="42px" align="center" width="22%">填表日期</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:textfield name="customerrepair.recordMonthString" id="recordMonth" readonly="true" size="30" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red">*</font></td>
  	<td class="tdbr" align="center">请修地址：</td><td class="tdbr" colspan="3">&nbsp;&nbsp;<s:textfield name="customerrepair.comaddress" readonly="true" cssClass="con_input_con" theme="simple"/></td>
  </tr>
  <tr>
  	<td class="tds" height="42px"  align="center">受理日期</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textfield name="customerrepair.acceptedDate" id="acceptedDate" readonly="true" size="20" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;
  	
  	<font color="red">*</font>
  	</td>
  </tr>
  <tr>
  	<td class="tds" height="42px" align="center">客户姓名、联系电话及地址</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textarea rows="2" cols="50" name="customerrepair.content" id="content" readonly="true" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red">*</font></td>
  </tr>
  <tr>
  	<td class="tds" height="48px"  align="center">请修内容</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textarea name="customerrepair.repairContext" id="repairContext" rows="2" cols="50" readonly="true" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red">*</font></td>
  </tr>
  <tr>
  	<td class="tds" height="42px" align="center">预约时间</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textfield name="customerrepair.reserveDate" id="reserveDate" readonly="true"  size="20" cssClass="con_input_con" theme="simple"/>
  	  
  	</td>
  </tr>
  <tr>
  	<td class="tds" height="42px" align="center">派工单号</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textfield name="customerrepair.dispatcherNum" id="dispatcherNum" size="30" readonly="true" cssClass="con_input_con" theme="simple"/></td>
  </tr>
   <tr>
  	<td class="tds" height="42px" align="center">维修人员</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textfield name="customerrepair.wxname" id="wxname" size="30" readonly="true" cssClass="con_input_con" theme="simple"/></td>
  </tr>
  <tr>
  	<td class="tds" height="48px"  align="center">维修过程</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textarea name="customerrepair.repairprocess" readonly="true" id="repairprocess" rows="2" cols="50" cssClass="con_input_con" theme="simple"/></td>
  </tr>
 <tr>
  	<td class="tds" height="48px"  align="center">完成时间</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textfield name="customerrepair.achieveDate" id="achieveDate" readonly="true" size="20" cssClass="con_input_con" theme="simple"/>
 
  	</td>
  </tr>
   <tr>
  	<td class="tds" height="48px"  align="center">维修状态</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:radio name="customerrepair.repairStatus" id="repairStatus" list="#{'未维修':'未维修','已维修':'已维修'}" cssClass="con_input_con" theme="simple"/></td>
  	<td class="tdbr" align="center">服务状态</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:radio name="customerrepair.paidService" id="paidService" list="#{'有偿服务':'有偿服务','无偿服务':'无偿服务'}" cssClass="con_input_con" theme="simple"/></td>
  </tr>
   <tr>
  	<td class="tds" height="48px"  align="center">维修结果</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textarea name="customerrepair.repairResult" readonly="true" id="repairResult" rows="2" cols="50" cssClass="con_input_con" theme="simple"/></td>
  </tr>
  <tr>
  	<td class="tds" height="48px"  align="center">回访时间</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textarea name="customerrepair.reciprocal" readonly="true" id="reciprocal" rows="2" cols="50" cssClass="con_input_con" theme="simple"/></td>
  </tr>
  <tr>
  	<td class="tds" height="48px"  align="center">回访结果</td>
  	<td class="tdbr" colspan="5">&nbsp;&nbsp;<s:textarea name="customerrepair.visitResult" readonly="true" id="visitResult" rows="2" cols="50" cssClass="con_input_con" theme="simple"/><br>&nbsp;&nbsp;<font color="red">回访时间和回访结果填入后,此请修登记表登记结束！</font> </td>
  </tr>
  </table>
</form>
</body>
</html>
