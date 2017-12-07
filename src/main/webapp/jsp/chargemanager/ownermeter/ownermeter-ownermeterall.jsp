<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<base target="_self"/>
<%@ include file="/commons/meta.jsp" %>
<script language="javascript">
function formCheck(){
	if(document.form1.theFile.value == ""){
		alert("请选择要导入的文件！");
		return false;
	}
	return true;
}
</script>
<body>
<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>资料导入</p> 
            </div>
             
		<table width="97%" border="0" cellspacing="0" cellpadding="0" align="center" class="tablegray"> 
		<tr>
		<td><font color="red">批量导入注意事项：</font><br>
		&nbsp;&nbsp;&nbsp;&nbsp;1.上传文件前请先下载系统提供的“基础信息模板”！<br>
		&nbsp;&nbsp;&nbsp;&nbsp;2.上传只能添加新的内容，已有业户表资料信息不会被覆盖。请注意按照格式添加 。<br>
		&nbsp;&nbsp;&nbsp;&nbsp;3.上传文件类型只能为Excel文件！<br><br>
		</td>
		</tr>
		<tr>
			<td height="40">
				<form action="ownermeter!down.action" method="post" name="form2"> 
				请选择小区 : <s:select list="viewList" name="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> 
			          <br> 请下载业户表资料信息模版，填写完毕后上传。  
					<input name="cc" value="下载模板" class="save" type="submit" />	
				</form>  
			</td></tr>
			<tr>
				<td height="40" align="center">
				<form action="ownermeter!uploadhousemeterExcel.action" method ="post" enctype="multipart/form-data" name="form1" onSubmit="return formCheck();">
		        &nbsp;基础资料信息导入：<input type="file" name="theFile" id="file" />
		        <input name="dd" type="submit" class="save" value="导入"/><br />
				</form>
				</td>
			</tr>
		</table>  

    </div>
    </div>
</body>
</html>