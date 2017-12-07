<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<body>
<br></br>
<form action="bulletin!batchSave.action" method ="post" enctype="multipart/form-data" id="myForm">
<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" class="tablegray">
<TR>
    <TH height="25" align="center" colspan="2">短信批量发送</TH>
</TR>
<tr>
	<td align="right"> 说明：</td>
	<td height="40">上传文件类型只能为txt文件！且每行为一个手机号码，系统会自动过滤掉错误的手机号码<br>
</tr>
<tr>
	<td height="40" align="right" width="20%">
	&nbsp;文件导入：
	</td>
	<td><input type="file" name="theFile" id="file" /></td>
</tr>
<tr>
	<td height="40" align="right">
	&nbsp;信息内容：
	</td>
	<td>
		<textarea rows="8" cols="52" name="entity.content" id="content"></textarea>
		<input type="text" name="subname" value="【长江物业】" readonly="readonly" size="10">
	</td>
</tr>
<tr>
	<td height="52" align="center">
	&nbsp;
	</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="发送" class="a"></td>
</tr>
</table>
</form>

</body>
<script type="text/javascript">
$(document).ready(
		function() {
			 $('#myForm').ajaxForm({
			        beforeSubmit: function(a,f,o) {
			        	if(document.getElementById("theFile").value==''){
			        		alert("请选择上传文件类型。");
			        		return false;
			        	}
			        	if(document.getElementById("content").value==''){
			        		alert("请输入发送信息内容。");
			        		return false;
			        	}
			        },
			        dataType:  'json' ,
			        success: function(responseText) {
			        	if(responseText.success){
			    			alert(responseText.msg);
			    			window.close();
			    		}else{
			    			alert("发送失败,"+responseText.msg);
			    		}
			        }
			    });
		}
		
	);
</script>
</html>