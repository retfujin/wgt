<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<base target="_self"/>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/func.js"></script>
</head>
<script language="javascript">
function inputOnlyNumber() 
{ 
  var k = window.event.keyCode; 
  
  if (k  < 46 || k > 57) 
    return false; 
  else 
    return true; 
}
function frmOnAdd(frm){
	
	if(frm.meterCode.value=='')
	{
		alert("请输入表编号！");
		frm.meterCode.focus();
		return false;
	}

	if(frm.oldMeterNum.value==''){
		alert("请输入原表末次表数");
		frm.oldMeterNum.focus();
		return false;
	}else{
		if(checkOnlyNum(frm.oldMeterNum,"原表末次表数")==false){
			frm.oldMeterNum.focus();
			return false;
		}
	}
	if(frm.initRecord.value==''){
		alert("请输入新表期初表数");
		frm.initRecord.focus();
		return false;
	}else{
		if(checkOnlyNum(frm.initRecord,"新表期初表数")==false){
			frm.initRecord.focus();
			return false;
		}
	}
	if(frm.lastRecord.value==''){
		alert("请输入新表末次表数");
		frm.lastRecord.focus();
		return false;
	}else{
		if(checkOnlyNum(frm.lastRecord,"新表末次表数")==false){
			frm.lastRecord.focus();
			return false;
		}
	}
	
	if(frm.times.value.length<1)
	{
		alert("请输入倍率!");
		frm.times.focus();
		return false;
	}else{
		var times=frm.times.value;
		if(isNaN(times)){
			alert("倍率必须是数字!");
			frm.times.select();
			return false;
		}
	}
	if(frm.changeReason.value=='')
	{
		alert("请输入换表原因");
		frm.changeReason.focus();
		return false;
	}

	
	return true;
}

</script>
<body>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
String str_date1 = formatter.format(currentTime); //将日期时间格式化 
%>




 <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>更换总表设置</p> 
            </div>
            <form method="post" action="allmeterrecord!saveAllmeterChange.action" name="frmAdd" onSubmit="return frmOnAdd(this)">
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">表编号</td>
                        <td><input id="meterCode" name="allmeter.meterCode" value="${allmeter.meterCode}" size="20" class="locked" value="" maxlength="20" /></td>                       
                    </tr>
                    <tr>
                        <td height="35" align="center">原表末次表数</td>
                        <td><input type="text" name="oldMeterNum" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">新表期初表数</td>
                        <td><input type="text" name="allmeter.initRecord" id="initRecord" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">新表末次表数</td>
                        <td><input type="text" name="allmeter.lastRecord" id="lastRecord" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">表倍率</td>
                        <td><input type="text" name="allmeter.times" id="times" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
						<td width="" height="40" align="right">更换人：&nbsp;&nbsp;</td>
						<td width=""><s:textfield name="allmeter.changeName" theme="simple" /></td>
					</tr>
					<tr>
						<td width="" height="40" align="right">更换日期：&nbsp;&nbsp;</td>
						<td><input name="allmeter.changeTime" value="<%=str_date1 %>" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					</tr>
					<tr>
						<td width="" height="40" align="right">更换原因：&nbsp;&nbsp;</td>
						<td><s:textfield name="allmeter.changeReason" id="changeReason" size="40" theme="simple"></s:textfield>
						</td>
					</tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                       		<s:hidden name="allmeter.id" />
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!----add结束--->  
  
</body>
</html>