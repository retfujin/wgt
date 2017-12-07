<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<head>
<title>修改密码</title>
</head>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(checkField(frm.oldPW,"原有密码")==false)
						return false;
					if(checkField(frm.newPW,"新密码")==false)
						return false;
						
					if(frm.newPW.value!=frm.validatenewPW.value)
					{
						alert("两次输入的新密码不一致，请重新输入！");
					    return false;
					}
					if(!checkNoChinese(frm.newPW,"密码"))//密码不能输入中文
					{
						return false;
					}
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

<form id="ff" method="post" action="user!savePW.action" name="frmAdd">
<br>
   <table width="60%" border="0" align="center" cellpadding="2" cellspacing="1" class="tablegray">
  <tr>
      <th height="25" colspan="7" class="tableHeaderText">密码修改</th>
    </tr>
  <tr>
  <td height="30"><div align="right">原密码：</div></td>
    <td>
    	<input type="password" name="oldPW" id="oldPW" size="25" maxlength="20" /></td>
	</tr>
	<tr>
    <td height="30"><div align="right">新密码：</div></td>
    <td>
    	<input type="password" name="newPW" id="newPW" size="25" maxlength="20" /></td>
	</tr>
	<tr>
    <td height="30"><div align="right">确认新密码：</div></td>
    <td>
    	<input type="password" name="validatenewPW" id="validatenewPW" size="25" maxlength="20"/></td>
  </tr>
  <tr>
    <td height="30">&nbsp;</td>
    <td><input type="submit" name="submit11" value="保存" class="a"> <input type="reset" value="重填" class="a"></td>
  </tr>
</table>
</form>

<br/>
<br/>
</body>
</html>
