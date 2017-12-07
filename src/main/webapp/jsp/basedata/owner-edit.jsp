<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function frmOnAdd(frm){
	
	if(frm.ownerName.value=="")
	{
		alert("业主姓名必须要填写！！！");
		document.frmAdd.ownerName.focus();
		return false;
	}
	if(!checkOnlyNum(frm.age,"年龄"))//只能是数字的验证
	{
			return false;
	}
	if(!checkOnlyNum(frm.phone,"业主固话号码"))
	{
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

</script>
<script type="text/javascript" src="/js/func.js">
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
<%
	String[] menuModel = { "menuModel2.addItem(203,'列表','','owner!list.action',false);"
			+ "menuModel2.addItem(204,'新增','','owner!add.action',false);" };
%>
<%@ include file="/menubar/simple/aa.jsp"%>
<table width="85%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tableBorder1">
	<TBODY>
		<TR>
			<TH height="25" align="left">编辑客户<span class="样式1"><font
				style="font-size: 9pt"></font></span></TH>
		</TR>
	</tbody>
</table>
<form method="post" action="owner!save.action" name="frmAdd"
	onSubmit="return frmOnAdd(this)">


<table width="85%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="tablegray" id="edittable">
	<tr>
		<td width="100">
		<div align="right">业主姓名：</div>
		</td>
		<td width="225"><s:textfield name="entity.ownerName"
			id="ownerName" size="15" theme="simple" /><font color="red">*</font></td>
		<td height="25">
		<div align="right">性别：</div>
		</td>
		<td width="271"><s:select name="entity.sex" id="sex"
			list="#{'男':'男','女':'女'}" theme="simple"></s:select></td>
	</tr>
	<tr>
		<td>
		<div align="right">工作单位：</div>
		</td>
		<td><s:textfield name="entity.job" id="job" size="35"
			theme="simple" />*</td>
		<td height="25">
		<div align="right">年龄：</div>
		</td>
		<td><s:textfield name="entity.age" id="age" size="5"
			theme="simple" /><font color="red">*</font></td>
	</tr>
	<tr>
		<td>
		<div align="right">证件类型：</div>
		</td>
		<td><s:select name="entity.paperType" id="paperType"
			list="#{'身份证':'身份证','驾照':'驾照'}" theme="simple" /></td>
		<td height="25">
		<div align="right">证件号码：</div>
		</td>
		<td><s:textfield name="entity.paperNum" id="paperNum" size="25"
			maxlength="20" theme="simple" />*</td>
	</tr>
	<tr>
		<td>
		<div align="right">固定电话：</div>
		</td>
		<td><s:textfield name="entity.phone" id="phone" size="25"
			maxlength="20" theme="simple" /><font color="red">*</font></td>
		<td height="25">
		<div align="right">移动电话：</div>
		</td>
		<td><s:textfield name="entity.mobTel" id="mobTel" size="25"
			maxlength="20" theme="simple" />*</td>
	</tr>
	<tr>
		<td>
		<div align="right">房屋当前状态：</div>
		</td>
		<td><s:select name="entity.housetypeSub2" id="housetypeSub2"
			list="#{'自住':'自住','出租':'出租'}" theme="simple" /></td>

		<td width="24%" height="25">
		<div align="right">住房情况：</div>
		</td>

		<td><s:select name="entity.occupancyType" id="occupancyType"
			list="#{'入住':'入住','未入住':'未入住'}" theme="simple" /></td>

	</tr>
	<tr>
		<td height="25">
		<div align="right">入住时间：</div>
		</td>
		<td><input name="entity.inDate" type="text" id="inDate"
			readonly="true"
			value="<s:if test="entity.inDate!=null"><s:property value="entity.inDate.toString().substring(0, 10)"/></s:if>"
			size="12" maxlength="12" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font
			color="red">*</font></td>
	</tr>
	<tr>
		<td height="25">
		<div align="right">房间地址：</div>
		</td>
		<td><s:property value="entity.house.houseAddress" /></td>
		<td>
		<div align="right">生日：</div>
		</td>
		<td colspan="3"><s:textfield name="entity.birthday" id="birthday"
			readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple" />*</td>
	</tr>
	<tr>
		<td height="25">
		<div align="right">家庭成员：</div>
		</td>
		<td colspan="3"><s:textarea name="entity.familymember"
			theme="simple" rows="3" cols="50" /></td>

	</tr>
	<tr>
		<td>
		<div align="right">备注：</div>
		</td>
		<td colspan="3"><s:textarea name="entity.remark" id="remark"
			cols="40" rows="5" theme="simple" />*</td>
	</tr>
	<tr>
		<td><s:hidden name="entity.house.id" id="houseId"></s:hidden> <s:hidden
			name="entity.id"></s:hidden> &nbsp;</td>
		<td><input type="submit" name="submit" value="更新" class="a">
		<input type="button" value="过户"
			onclick="window.location.href='owner!outaddowner.action?oldOwnerId=${entity.id}'"
			class="a"></td>
		<td height="30">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</body>
</html>
