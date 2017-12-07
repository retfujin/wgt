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
<script type="text/javascript">
	//选择人
	function goEmployee(id){
	    var rtnValue = window.showModalDialog("process!choose.action","","dialogHeight:400px;dialogWidth:700px");
	    if(rtnValue!=undefined){
	        var userIdList = "";
	        var userNameList = "";
	        if(rtnValue!=null){
	            for(i=0;i<rtnValue.length;i++){
	                if(userIdList=="") userIdList = rtnValue[i].uid;
	                if(userNameList=="") userNameList = rtnValue[i].uname;
	       
	            }
	        }
	        document.getElementById('moduleExecutorId'+id).value = userIdList; 
	        document.getElementById('moduleExecutorName'+id).value = userNameList;
	    }
	}
</script>
</head>
<body>
<form id="myForm" method="post" action="process!templatesave.action">
<table width="60%" border="0" align="center" class="table_general form_show">
	<tr>
		<th colspan="3">编辑模板</th>
	</tr>
	<tr>
		<td>
		<div align="right">应用模块</div>
		</td>
		<td><s:select id="processCode" list="#{'qjlc':'请假流程','jhlc':'工作计划流程'}" name="processTemplateEO.processCode"></s:select><font
			color="red">*</font>
			<input type="hidden" id="processName" name="processTemplateEO.processName" />
		</td>
	</tr>
	<tr>
		<td>
		<div align="right">描述：</div>
		</td>
		<td><s:textfield id="description"	name="processTemplateEO.description"></s:textfield></td>
	</tr>
</table>
<table id="Active_Table" width="70%" border="0" align="center"	class="table_general form_show">

	<tr>
		<td>
		<div align="center">步骤号</div>
		</td>
		<td>
		<div align="center">步骤名</div>
		</td>
		<td>
		<div align="center">经办人</div>
		</td>
		<td>
		<div align="center">描述</div>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td><input type="button" onClick="addRow(Active_Table)"			value="增加" class="con_button" /></td>
		<td><input type="button" onClick="delRow(Active_Table)"			value="删除" class="con_button" /></td>
	</tr>
</table>
<table>
	<tr>
		<td></td>
		<td colspan="2"><input type="submit" id="signup" name="submit1"	value="保存" class="a"> <input type="reset" value="重填"
			class="a"></td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
$(function(){
	$('#myForm').form({
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
				newCell.innerHTML = "<div><input type='hidden' name='moduleExecutorId' id='moduleExecutorId"+tableRowSum+"'/><input type='text' name='moduleExecutorName'  readonly='readonly'  id='moduleExecutorName"+tableRowSum+"'/><input type='button' onclick='javascript:goEmployee("+tableRowSum+")' value='选择' /></div>";
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