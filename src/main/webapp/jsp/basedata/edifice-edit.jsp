<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<script type="text/javascript">
function frmOnAdd(frm){	
    if(frm.edificeName.value=='')
    {
        alert("楼栋名称不能为空，请填写");
        document.getElementById('edificeName').focus();
        return false;
    }	

    if(!checkMoney(frm.buildNum,"建筑面积"))//只能是数字的验证
    {
    	document.getElementById('buildNum').select();
        return false;
    }
    if(new Number(document.getElementById('buildNum').value)==0)
    {
        alert("建筑面积不能为0，请重新添写");
        document.getElementById('buildNum').select();
        return false;
    }
    if(frm.cellNum.value=='')
    {
        alert("单元数不能为空，请填写");
        document.getElementById('cellNum').focus();
        return false;
    }

    if(!checkOnlyNum(frm.cellNum,"单元数"))//只能是数字的验证
    {
    	document.getElementById('cellNum').select();
        return false;
    }
    if(frm.layerNum.value=='')
    {
        alert("层数不能为空，请填写");
        document.getElementById('layerNum').focus();
        return false;
    }

    if(!checkOnlyNum(frm.layerNum,"总层数"))//只能是数字的验证
    {
    	document.getElementById('layerNum').select();
        return false;
    }
    return true;
}
</script>
<script type="text/javascript" src="/js/func.js">
</script>
<%@ include file="/commons/meta.jsp"%>

<body>
<table width="90%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tableBorder1">
	<TR>
		<TH height="25" align="left">编缉小区楼栋</TH>
	</TR>
</table>
<form method="post" action="edifice!save.action" name="frmAdd"
	onSubmit="return frmOnAdd(this)">
<table width="90%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tablegray" id="edittable">
	<tr>
		<td width="20%" height="30">
		<div align="right">楼栋编号：</div>
		</td>
		<td colspan="2"><s:textfield name="entity.id" id="id" size="25"
			maxlength="20" theme="simple" readonly="true" cssClass="locked" /><font
			color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="30">
		<div align="right">楼栋名称：</div>
		</td>
		<td colspan="2"><s:textfield name="entity.edificeName"
			id="edificeName" size="25" maxlength="20" theme="simple"
			required="true" /><font color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">所属小区：</div>
		</td>
		<td colspan="2"><s:hidden name="entity.area.id"></s:hidden>${entity.area.areaName}</td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">建筑面积：</div>
		</td>
		<td colspan="2"><s:textfield name="entity.buildNum" id="buildNum"
			size="25" maxlength="20" theme="simple" required="true" /><font
			color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">总单元数：</div>
		</td>
		<td colspan="2"><s:textfield name="entity.cellNum" id="cellNum"
			size="25" maxlength="20" theme="simple" /><font color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">总层数：</div>
		</td>
		<td colspan="2"><s:textfield name="entity.layerNum" id="layerNum"
			size="25" maxlength="20" theme="simple" /><font color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">楼栋用途：</div>
		</td>
		<td colspan="2"><s:select name="entity.edificeUse"
			id="edificeUse" theme="simple" list="#{'住宅':'住宅','商铺':'商铺'}" /><font
			color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">概要：</div>
		</td>
		<td colspan="2"><s:textarea name="entity.generalsituation"
			id="generalsituation" cols="50" rows="5" theme="simple" /></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">物管员：</div>
		</td>
		<td colspan="2"><s:textfield name="entity.managerName" id="managerName"
			size="25" maxlength="25" theme="simple"/></td>
	</tr>
	<tr>
		<td width="20%" height="25">
		<div align="right">备注：</div>
		</td>
		<td colspan="2"><s:textarea name="entity.remark" id="remark"
			cols="50" rows="5" theme="simple" /></td>
	</tr>
	<tr>
		<td width="20%" height="30"></td>
		<td width="46%"><input type="submit" name="submit" value="保存"
			class="a"> <input type="reset" value="重填" class="a">
		</td>
		<td width="34%">&nbsp;</td>
	</tr>
</table>
</form>
</body>
</html>