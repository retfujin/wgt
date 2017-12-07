<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function frmOnAdd(frm){

	if(frm.ownerName.value=="")
	{
		alert("业主姓名必须要填写！！！");
		document.all.frmAdd.ownerName.focus();
		return false;
	}
	if(!checkOnlyNum(frm.age,"年龄"))//只能是数字的验证
	{
		document.getElementById('age').select();
			return false;
	}
	if(!checkOnlyNum(frm.phone,"业主固话号码"))
	{
		return false;
	}
	if(!checkField(frm.houseId,"房间编号"))
	{
		document.getElementById('houseId').focus();
		return false;
	}
	if(frm.mobTel.value!='')
	{	if(!isTelphoneNumber(frm.mobTel))
		{
			return false;
		}
	}
	return true;
}

function selectListG(setControls,theJSP,width,height)
{
	var sArray,vArray;
	var value,text;
	var retValue;
	var ctrl=eval("document."+setControls);
	retValue=window.showModalDialog(theJSP,"","help:0;resizable:0;status=0;scrollbars=0;dialogWidth="+width+";dialogHeight="+height+";center=true");
	
	if(retValue!=null&&retValue!='undefined')
	{
		sArray=retValue.split(",");
		document.all.frmAdd.houseId.value=sArray[0];
	}
}
</script>

  <body>
<%   
      String[] menuModel = {"menuModel2.addItem(203,'列表','','owner!list.action',false);"
           };
%>
<%@ include file="/menubar/simple/aa.jsp" %>    
<table width="85%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
<TBODY>
<TR><TH height="25" align="left">业主<span class="样式1"><font style="font-size: 9pt"></font></span></TH></TR>
</tbody>
</table>
  <form method="post" action="owner!save.action" name="frmAdd" onSubmit="return frmOnAdd(this)">
  <s:hidden name="edificeId" value="%{#parameters.edificeId}"></s:hidden>
  <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="edittable">
   <tr>
     <td height="25"><div align="right">房间编号：</div></td>
    <td><input type="text" name="houseId"  id="houseId" />*<input type="button" value="查找" onclick="selectListG('frmAdd.houseId','edifice!choiceframe.action',35,28)"><font color="red">*</font></td>
   
  </tr>
  <tr>
    <td><div align="right">业主姓名：</div></td>
    <td><s:textfield  name="entity.ownerName" id="ownerName" size="25" maxlength="20" theme="simple"/><font color="red">*</font></td>
    <td><div align="right">性别：</div></td>
    <td><s:select name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" theme="simple"></s:select>*</td>
  </tr>
  <tr>
    <td><div align="right">工作单位：</div></td>
    <td><s:textfield  name="entity.job" id="job" size="25" maxlength="20" theme="simple" size="60"/></td>
    <td height="25"><div align="right">年龄：</div></td>
    <td><s:textfield  name="entity.age" id="age" size="5"  theme="simple"/></td>
  </tr>
  <tr>
    <td><div align="right">证件类型：</div></td>
    <td><s:select  name="entity.paperType" id="paperType" list="#{'身份证':'身份证','驾照':'驾照'}" theme="simple"/>*</td>
    <td height="25"><div align="right">证件号码：</div></td>
    <td><s:textfield  name="entity.paperNum" id="paperNum" size="25" maxlength="20" theme="simple"/><font color="red">*</font></td>
  </tr>
  <tr>
    <td><div align="right">固定电话：</div></td>
    <td><s:textfield  name="entity.phone" id="phone" size="25" maxlength="20" theme="simple"/></td>
    <td height="25"><div align="right">移动电话：</div></td>
    <td><s:textfield  name="entity.mobTel" id="mobTel" size="25" maxlength="20" theme="simple"/>*</td>
  </tr>
   <tr>
      <td width="24%" height="25"><div align="right">是否出租：</div></td>
      <td ><s:select name="entity.housetypeSub2" id="housetypeSub2" list="#{'自住':'自住','出租':'出租'}" theme="simple" />*</td>
    
      <td width="24%" height="25"><div align="right">入住情况：</div></td>

      <td ><s:select name="entity.occupancyType" id="occupancyType" list="#{'入住':'入住','未入住':'未入住'}" theme="simple" /><font color="red">*</font></td>

    </tr>
   <tr>
    <td height="25"><div align="right">入伙时间：</div></td>
    <td>
    <input name="entity.inDate" type="text" id="inDate" readonly="true" value="<s:if test="entity.inDate!=null"><s:property value="entity.inDate.toString().substring(0, 10)"/></s:if>" size="12"
	maxlength="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" ><font color="red">*</font></td>
	 <td><div align="right">生日：</div></td>
    <td colspan="3">
    <s:textfield  name="entity.birthday" id="birthday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/>*</td>
   </tr>
   <tr>
     <td height="25"><div align="right">家庭成员：</div></td>
    <td colspan="3"><s:textarea name="entity.familymember" theme="simple" rows="3" cols="50"/></td>
   
  </tr>
  <tr>
    <td><div align="right">备注：</div></td>
    <td colspan="3"><s:textarea  name="entity.remark" id="remark" cols="60" rows="5" theme="simple"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="submit" name="submit" value="保存" class="a"> <input type="reset" value="重填" class="a"></td>
    <td height="30">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
  </form>
  </body>
</html>
