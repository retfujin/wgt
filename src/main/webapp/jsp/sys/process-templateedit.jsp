<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<title>添加模板</title>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>

</head>
<body>
<form id="myForm" method="post" action="process!templatesave.action">
<s:hidden	name="processTemplateEO.id" />
<table width="70%" border="0" align="center" class="table_general form_show">
	<tr>
		<th colspan="2">查看流程</th>
	</tr>
	<tr>
		<td width="100px">
			${processTemplateEO.processName}
		</td>
		<td>
			描述:${processTemplateEO.description}
		</td>
	</tr>
</table>
<br/>
<table id="Active_Table" width="70%" border="0" align="center"	class="table_general form_show">

	<tr>
		<th>
		<div align="center">步骤号</div>
		</th>
		<th>
		<div align="center">步骤名</div>
		</th>
		<th>
		<div align="center">经办人</div>
		</th>
		<th>
		<div align="center">描述</div>
		</th>
	</tr>
	<s:iterator id="modules" value="templateModules" status="stuts">
		<tr>
			<td align="center">${moduleStep}</td>
			<td align="center">${moduleName}</td>
			<td align="center"><s:property value="moduleExecutorName"/></td>
			<td align="center">${moduleDescription}</td>
		</tr>
	</s:iterator>
</table>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#myForm1').form({
		onSubmit: function(){
			if (document.getElementById("processCode").value == '') {
				alert("模块不能为空");
				return false;
			}
			var checkText=$("#processCode").find("option:selected").text();
			$('#processName').val(checkText);


			var names = document.getElementsByName("moduleName");
			if(names.length<1){
				alert("请填写步骤");
				return false;
			}
			for (i = 0; i < names.length; i++) {
				if (names[i].value == '') {
					alert("步骤名不能为空");
					return false;
				}
			}

			$("input[type='submit']").each(function(){
				$(this).attr("disabled",true);
			});
			
			return true;
		},
		success:function(data){
			alert(data);
			var responseText =  eval('(' + data + ')');
			
			$("input[type='submit']").each(function(){
				$(this).attr("disabled",false);
			});
			
			if (responseText.success) {
				alert('保存成功');
				window.returnValue = "success";
				window.close();
			} else{
				alert(responseText.msg);
			}
		}
	});

});
</script>
<script type="text/javascript">
	//插入一行
	function addRow(tableName) {
		var selectContent = $("#selectContent").innerHTML;

		var newRow = tableName.insertRow(tableName.rows.length);// 插入一行
		//var nowLocationRow = tableName.rows.length - 2;// 插入行的当前位置(在什么位置插入一行)
		var tableRowSum = tableName.rows.length - 1; //行号

		var newCell;

		for (i = 0; i < tableName.rows[0].cells.length; i++) {

			newCell = newRow.insertCell(-1);

			if (i == 0) {
				newCell.innerHTML = "<input size='6' type=\"text\" name=\"moduleStep\" value =\""+tableRowSum+"\" readonly='true'/>";
			} else if (i == 1) {
				newCell.innerHTML = "<input type=\"text\" name=\"moduleName\"/>";
			} else if (i == 2) {
				newCell.innerHTML = selectContent;
			} else if (i == 3) {
				newCell.innerHTML = "<input type=\"text\" name=\"moduleDescription\"/>";
			}
		}
	}

	// 删除一行
	function delRow(TableName) {
		if (TableName.rows.length <= 2) {
			alert("对不起，最后一行不能删除！");
		} else {
			TableName.deleteRow(TableName.rows.length - 1)
		}
	}
</script>
</html>

