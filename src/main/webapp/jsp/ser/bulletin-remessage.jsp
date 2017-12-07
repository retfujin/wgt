<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" >
	$(function(){
			$('#ff').form({
				onSubmit: function(){
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.returnValue = "success";
						window.close();
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>

<%@ include file="/commons/meta.jsp"%>
<body>
<br>
<form id="ff" method="post" action="bulletin!resendmessage.action" name="frmAdd" >
<s:iterator value="viewList" status="stuts">
<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="edittable">
	<tr>
		<td height="32" align="center">房间地址</td>
		<td><s:property value="viewList[#stuts.index][2]"/></td>
	</tr>
	<tr>
		<td height="32" align="center">业主姓名</td>
		<td><s:property value="viewList[#stuts.index][3]"/></td>
	</tr>
	<tr>
		<td height="32" align="center">手机号码</td>
		<td><s:property value="viewList[#stuts.index][0]"/><input type="hidden" name="mobile" value="<s:property value="viewList[#stuts.index][0]"/>"/> </td>
	</tr>
	<tr>
		<td height="100" align="center">信息内容</td>
		<td><s:property value="viewList[#stuts.index][1]"/></td>
	</tr> 
	<tr>
		<td  height="100" align="center">回复内容</td>
		<td><s:textarea name="content" cols="48" rows="7"/> </td>
	</tr>
	<tr>
		<td width="20%" height="30">&nbsp;</td>
		<td width="80%" colspan="2"><input type="submit" name="submit111" value="发送" class="a"> </td>
		<td>&nbsp;</td>
	</tr>
</table>
</s:iterator>
</form>
</body>

</html>
