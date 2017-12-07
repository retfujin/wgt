<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head> 
</head>
<%@ include file="/commons/meta.jsp" %>

<body>

<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" >
 <tr ><td align="center" height="45"><font style="font-size: 18px;font-weight: bolder;">客户投诉受理登记表</font></td></tr>
 
 <tr >
  <td align="left" height="20">项目部：<s:select list="retList" name="entity.areaId" id="areaId"  listKey="id" listValue="areaName" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red"></font></td>
  <td align="right" height="20">填表时间：<s:textfield name="entity.suitTime" id="suitTime"  cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red"></font></td></tr>
</table>
<table width="80%" border="0" id="Active_Table" class="table_border"  align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td class="tdh" height="42px" align="center">客户姓名</td>
  	<td class="tdr">&nbsp;&nbsp;<s:textfield name="entity.suitName" id="suitName"   size="27" cssClass="con_input_con"  theme="simple"/>&nbsp;&nbsp;<font color="red">*</font></td>
    <td class="tdr"  align="center">联系电话</td>
    <td class="tdr">&nbsp;&nbsp;<s:textfield name="entity.suittel" id="suittel"   size="27" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red"></font></td>
  </tr>
  <tr>
  	<td class="tds" height="42px"  align="center">客户地址</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:textfield name="entity.suitDepartment" id="suitDepartment"   size="27" cssClass="con_input_con" theme="simple"/>&nbsp;&nbsp;<font color="red"></font></td>
    <td class="tdbr" width="10%" align="center">投诉方式</td>
    <td class="tdbr" >&nbsp;&nbsp;<s:select name="entity.suitWay" id="suitWay"  list="#{'来访':'来访','电话':'电话','短信':'短信','信函':'信函','其它':'其它'}"   cssClass="con_input_con" theme="simple"/></td>
  </tr>
   <tr>
  	<td class="tds" height="42px" align="center">投诉时间</td>
  	<td class="tdbr" >&nbsp;&nbsp;<s:textfield name="entity.suitDate" id="suitDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   size="27" cssClass="con_input_con" theme="simple"/></td>
    <td class="tdbr"  align="center">投诉类型</td>
    <td class="tdbr" >&nbsp;&nbsp;<s:select name="entity.suitType" id="suitType"   list="#{'有效':'有效','无效':'无效'}"  cssClass="con_input_con" theme="simple"/></td>
  </tr>
   <tr>
  	<td class="tds" height="140px" rowspan="2" align="center" valign="top">投诉内容</td>
  	<td class="r" colspan="3">&nbsp;&nbsp;<s:textarea cols="68" rows="6" name="entity.suitMess"   id="suitMess" cssClass="con_input_con" theme="simple" /></td>
  </tr>
  <tr>
  	<td class="b" height="26px" colspan="2" align="right">&nbsp;&nbsp;记录人：<s:textfield name="entity.name1"   id="name1" size="12" cssClass="con_input_con" theme="simple"/></td>
  	<td class="tdbr"  align="right">&nbsp;&nbsp;日期：<s:textfield name="entity.suitDate1" id="suitDate1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="12"  cssClass="con_input_con" theme="simple"/></td>
  </tr>
  <tr>
  	<td class="tds" height="140px" rowspan="2" align="center" valign="top">处理过程</td>
  	<td class="r" colspan="3">&nbsp;&nbsp;<s:textarea cols="68" rows="6" name="entity.investigationState"  id="investigationState" cssClass="con_input_con"  theme="simple"/></td>
  </tr>
  <tr>
  <td class="b" height="26px" colspan="2" align="right">&nbsp;&nbsp;处理人：<s:textfield name="entity.name2"   id="name2" size="12" cssClass="con_input_con" theme="simple"/></td>
  	<td class="tdbr"  align="right">&nbsp;&nbsp;日期：<s:textfield name="entity.suitDate2" id="suitDate2"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  size="12" cssClass="con_input_con" theme="simple"/></td>
  </tr>
   <tr>
  	<td class="tds" height="140px" rowspan="2" align="center" valign="top">回访情况</td>
  	<td class="r" colspan="3">&nbsp;&nbsp;<s:textarea cols="68" rows="6" name="entity.processState"   id="processState" cssClass="con_input_con" theme="simple"/></td>
  </tr>
  <tr>
  	<td class="b" colspan="2" height="26px"  align="right">&nbsp;&nbsp;回访人：<s:textfield name="entity.name3"   id="name3" size="12" cssClass="con_input_con" theme="simple"/></td>
  	<td class="tdbr" align="right" >&nbsp;&nbsp;日期：<s:textfield name="entity.suitDate3" id="suitDate3"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   cssClass="con_input_con" theme="simple"/></td>
  </tr>
  </table>
</body>
</html>
