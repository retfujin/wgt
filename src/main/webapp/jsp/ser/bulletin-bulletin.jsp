<%@ page import="java.util.Date"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html><base target="_self"/>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script language="javascript" src="/js/func.js"></script>
<script language="javascript">
	function addNumber(srcItem)
	{
		if(srcItem.value.length==11){
			var item = document.createElement("OPTION");
			if (!isTelphoneNumber(srcItem))
			{
				return false;
			}
			for (i=0;i<myForm.receivers.length;i++)
			{
				if (myForm.receivers.options(i).value == myForm.destAddr.value)
				{
				   alert("您输入的手机号码重复！");
				   return;
				}
			}
			myForm.receivers.options.add(item);
			item.value = srcItem.value;
			item.innerText = srcItem.value;
			myForm.destAddr.value="";
		}
	}

	function delReceiver(){
		var destItem;
		var item, itemValue;
		var i;
		srcItem = myForm.receivers;
		for (i=srcItem.length-1;i>=0;i--)
		{
			item = srcItem.item(i);
			if ( item.selected )
			{
				itemValue = item.value.split("\0");
				item.value = itemValue[1];				
				srcItem.remove(i);
			}
		}
	}
	
	//统计字数
	function checkoverflow(){
		var str = new String();
		str = myForm.content.value;
		myForm.counter.value = 200 - str.length;
		if (str.length>200){
			myForm.content.value = str.substr(0,200);
			myForm.counter.value = 0;
			alert("您输入的信息的长度应不超过200！");
			return false;
		}	
		return true;
	}
</script>

<body> 
<%@ include file="/commons/meta.jsp" %>
<table width="88%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
<TR>
    <TH height="25" align="left">添加公告</TH>
</TR>
</table>  
<form id="myForm" name="myForm"  method="post" onsubmit="checkData()">
<s:hidden name="entity.id"></s:hidden>
<table width="88%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablegray" >
	<tr>
	  <td width="100" height="35"><div align="center">手机号码：</div></td>
		<td>
			<input type="text" name="destAddr" id="destAddr" onkeyup="addNumber(destAddr);">
		</td>
	</tr>
	<tr>
		<td ><div align="center">公告范围：</div></td>
		<td height="100">
			<select id="select2" name="receivers" id="receivers" size="6"  style="width: 298; background-color:#FFFFFF; color:#006CAD; " multiple="multiple"></select>
			<input value="删除对象" name="addGroupSelf" type="button" id="addGroupSelf2" onClick="delReceiver()" class="a" />		</td>
	</tr>
	<tr>
		<td><div align="center">信息内容：</div></td>
		<td height="100">
			<textarea rows="5" cols="40" name="entity.content" id="content" onKeyUp="return checkoverflow()"></textarea>
			<input type="text" name="subname" value="【长江物业】" readonly="readonly" size="10">
			还剩 <font color="#FF0000"><input id="counter" name="counter" style="border:0;color:red;background-color:#FFFFFF" value="200" size="2" type='text' readonly></font> 字		</td>		
	</tr>
	<tr>
	  <td height="30"></td>
		<td>
			  <input type="hidden" name="destPhoneList" id="destPhoneList">
			  <input type="submit" id="signup"  value="发送" class="a">
	    </td>
	</tr>
</table>
</form>

</body>
<script type="text/javascript">
$(document).ready(
	function() {
	 var options = { 
			  beforeSubmit:  showRequest,
			  success:       showResponse,
			  url:       "bulletin!addbull.action",
			  dataType:  'json' 
		};
		$('#myForm').ajaxForm(options); 
	}
	
);
function showRequest(formData, jqForm, options) {
	if(document.getElementById("content").value==''){
		alert("信息内容不能为空");
		return false;
	}
	document.getElementById("signup").disabled=true;
    return true;
}
function showResponse(responseText, statusText, xhr, $form)  {
		if(responseText.success){
			alert(responseText.msg);
			window.returnValue = "success";
			window.close();
		}else{
			if (responseText.msg == "10"){
				window.showModalDialog("../jsp/ser/sms-alert.jsp", "提示", "dialogWidth=800px;dialogHeight=480px");
			}else{
				alert("保存失败！ "+responseText.msg);
			}
		}
		document.getElementById("signup").disabled=false;
} 

function checkData()
{
	//生成手机号码列表
	var value ="";
	for (var i=0;i<myForm.receivers.options.length;i++)
	{
		value = value + myForm.receivers.options(i).value+",";	
	}
	myForm.destPhoneList.value = value;
	return true;
}
</script>
</html>