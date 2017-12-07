<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#ff').form({
			onSubmit : function() {
				var frm = document.getElementById("ff");
				if (frm.name.value == '') {
					alert("名称不能为空，请填写");
					document.getElementById('cell').focus();
					return false;
				}
				return true;
			},
			success : function(data) {
				var responseText = eval('(' + data + ')');
				if (responseText.success) {
					alert('保存成功');
					window.returnValue = "success";
					window.close();
				} else {
					alert(responseText.msg);
				}

			}
		});
	});
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
	<table width="80%" border="0" cellspacing="0" cellpadding="0"
		align="center" class="tableBorder1">
		<TBODY>
			<TR>
				<TH height="25" align="left">岗级信息<span class="样式1"><font
						style="font-size: 9pt"></font> </span>
				</TH>
			</TR>
		</tbody>
	</table>
	<form id="ff" method="post" action="rank!save.action" name="frmAdd">
		<s:hidden name="entity.id"></s:hidden>
		<s:hidden name="entity.isdel"></s:hidden>
		<table width="80%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="tablegray" id="edittable">
			<tr>
				<td width="24%" height="25"><div align="right">所属部门：</div>
				</td>
				<td><s:select name="entity.department.id" id="department"
						list="departments" headerKey="-1" headerValue="==请选择部门=="
						listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td width="24%" height="30"><div align="right">岗位名称：</div>
				</td>
				<td><s:textfield name="entity.name" id="name" theme="simple" /><font
					color="red">*</font></td>
			</tr>
			<tr>
				<td width="24%" height="25"><div align="right">薪资：</div>
				</td>
				<td><s:textfield name="entity.salary" id="description"
						theme="simple" /></td>
			</tr>
			<tr>
				<td width="24%" height="25"><div align="right">描述：</div>
				</td>
				<td><s:textfield name="entity.description" id="description"
						theme="simple" /></td>
			</tr>
			<tr>
				<td width="24%" height="25"><div align="right">备注：</div>
				</td>
				<td colspan="4"><s:textarea name="entity.remarks" id="remarks"
						cols="35" rows="5" theme="simple" />*</td>
			</tr>
			<tr>
				<td width="24%" height="30"></td>
				<td colspan="3"><input type="submit" name="submit11" value="保存"
					class="a">
				</td>
				<td width="51%">&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
