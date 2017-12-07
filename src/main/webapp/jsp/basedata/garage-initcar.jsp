<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<body>

<div class="add">
    <div class="add_area">
    	<div class="add_name">
        	<p>资料导入</p> 
        </div>
    </div>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="tablegray">
<tr>
<td><font color="red">批量导入注意事项：</font><br>
&nbsp;&nbsp;&nbsp;&nbsp;1.上传文件前请先下载系统提供的“基础信息模板”！<br>
&nbsp;&nbsp;&nbsp;&nbsp;2.上传只能添加新的内容，已有车位信息不会被覆盖。请注意不要重复导入车位信息。请按照格式添加。<br>
&nbsp;&nbsp;&nbsp;&nbsp;3.上传文件类型只能为Excel文件！<br><br>
</td>
</tr>
<tr><td height="40" align="center">
	<form action="garage!downExcel.action" method="post" name="form2"> 
	请选择小区<s:select list="viewList" name="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> 
  	
  请下载房间信息模版，填写完毕后上传。  
		<input name="cc" value="下载模板" class="save" type="submit" />	
</form>  </td></tr>

<tr><td height="40" align="center">
    <form id="my_form" action="garage!uploadExcel.action" method ="post" enctype="multipart/form-data" name="form1">
          &nbsp;基础资料信息导入：<input type="file" name="theFile" id="file" />
          <input name="dd" type="submit" class="save" value="导入"/><br />
	</form></td>
</tr>
</table>
<div id="target1" align="center"></div>
</body>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#my_form').form({
				onSubmit: function(){
					if(document.form1.theFile.value == "")
					{
						alert("请选择要导入的文件！");
						return false;
					}
					$("#target1").html("<div align='center'><image src='/images/wait.gif' /><br/>正在导入，请等待......</div>");
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					alert(responseText.msg);
					$("#target1").html("");
					
				}
			});
		});
</script>
</html>
