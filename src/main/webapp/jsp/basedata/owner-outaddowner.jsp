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
		document.frmAdd.ownerName.focus();
		return false;
	}
	
	if(frm.age.value!='')
	{
		if(!checkOnlyNum(frm.age,"年龄"))//只能是数字的验证
		{
			return false;
		}
	}
	
	if(!checkOnlyNum(frm.phone,"业主固话号码"))
	{
		return false;
	}
	
	if(frm.mobTel.value=="")
	{
		alert("业主手机号码必须要填写！！！");
		document.frmAdd.mobTel.focus();
		return false;
	}
	
	if(!isTelphoneNumber(frm.mobTel))
	{
		return false;
	}

	
	if(frm.houseId.value=='-1')
	{
		alert("请选择房间");
		return false;
	}
	
	
	var pattern=/^(19|20)\d{2}-(0\d|1[012])-(0\d|[12]\d|3[01])$/;
	var str=frm.birthday.value
	if(str!='' && !pattern.test(str))
	{
		alert("输入的出生日期格式不正式！");
		return false; 
	}
	

	return true;
}

</script>

  <body>
 <%          
   String[] menuModel = {"menuModel2.addItem(203,'返回','','',false,'','window.history.back();');"
            };
 %>
 <%@ include file="/menubar/simple/aa.jsp" %>    
      <table width="85%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
  <TBODY>
<TR>
    <TH height="25" align="left">新增需要过户的业主<span class="样式1"><font style="font-size: 9pt"></font></span></TH>
</TR></tbody>
</table>
  <form method="post" action="owner!outOwner.action" name="frmAdd" onSubmit="return frmOnAdd(this)">

  <table width="85%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="edittable">
  <tr>
    <td width="100"><div align="right">业主姓名：</div></td>
    <td width="225"><s:textfield  name="entity.ownerName" id="ownerName" size="25" maxlength="20" theme="simple"/>*</td>
    <td width="100" height="25"><div align="right">性别：</div></td>
    <td width="271"><s:select name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" theme="simple"></s:select></td>
  </tr>
  <tr>
    <td><div align="right">工作单位：</div></td>
    <td><s:textfield  name="entity.job" id="job" size="25" maxlength="20" theme="simple"/></td>
    <td height="25"><div align="right">年龄：</div></td>
    <td><s:textfield  name="entity.age" id="age" size="25" maxlength="20" theme="simple"/></td>
  </tr>
  <tr>
    <td><div align="right">证件类型：</div></td>
    <td><s:textfield  name="entity.paperType" id="paperType" size="25" maxlength="20" theme="simple"/></td>
    <td height="25"><div align="right">证件号码：</div></td>
    <td><s:textfield  name="entity.paperNum" id="paperNum" size="25" maxlength="20" theme="simple"/></td>
  </tr>
  <tr>
    <td><div align="right">固定电话：</div></td>
    <td><s:textfield  name="entity.phone" id="phone" size="25" maxlength="20" theme="simple"/></td>
    <td height="25"><div align="right">移动电话：</div></td>
    <td><s:textfield  name="entity.mobTel" id="mobTel" size="25" maxlength="20" theme="simple"/>*</td>
  </tr>
   <tr>
    <td height="25"><div align="right">入住时间：</div></td>
    <td>
    <input name="entity.inDate" type="text" id="inDate" readonly="readonly"
	value="<s:if test="entity.inDate!=null"><s:property value="entity.inDate.toString().substring(0, 10)"/></s:if>" size="12"
	maxlength="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
	<td height="25"><div align="right">费用结算日：</div></td>
    <td>
    <input name="entity.feeDate" type="text" id="feeDate" readonly="readonly"
	value="<s:if test="entity.feeDate!=null"><s:property value="entity.feeDate.toString().substring(0, 10)"/></s:if>" size="12"
	maxlength="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
   </tr>
   <tr>
    <td><div align="right">生日：</div></td>
    <td colspan="3">
    <s:textfield  name="entity.birthday" id="birthday" size="25" maxlength="20" theme="simple"/>格式如: 1976-08-15</td>
  </tr>
  <tr>
    <td><div align="right">备注：</div></td>
    <td colspan="3"><s:textarea  name="entity.remark" id="remark" cols="40" rows="5" theme="simple"/></td>
  </tr>
  <tr>
    <td><s:hidden name="oldOwnerId" value="%{#parameters.oldOwnerId}"></s:hidden>&nbsp;</td>
    <td><input type="submit" name="submit" value="保存" class="a"> <input type="reset" value="重填" class="a"></td>
    <td height="30">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
  </form>
  </body>
</html>
