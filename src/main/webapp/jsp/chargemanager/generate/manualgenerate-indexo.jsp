<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script language="javascript">
function formCheck()
{

if(document.form1.theFile.value == "")
	{
		alert("请选择要导入的文件！");
		return false;
	}
	return true;
}
</script>
<body>

 <div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>期初费用导入</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr>
       			<td align="left" class="td_c">批量导入注意事项</td>
                <td class="td_c">
                	<br>
                	1.上传文件前请先下载系统提供的"信息模板"！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                	2.请注意不要重复导入收费信息。请按照格式添加。<br>
                	3.上传文件类型只能为Excel文件！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
                	
                	<form action="manualgenerate!down.action" method="post" name="form2"> 
					请选择小区<s:select list="viewList" name="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> 
  					请下载收费信息模版，填写完毕后上传。  
					<input name="cc" value="下载模板" type="submit" />	
					</form> 
                </td>
            </tr>
            <tr  class="footer">
            	<td height="40" align="center" colspan="3">
				    <form action="manualgenerate!upload.action" method ="post" enctype="multipart/form-data" name="form1" onSubmit="return formCheck()">
				          &nbsp;资料导入：<input type="file" name="theFile" id="file" />
				          <input name="dd" class="save"  type="submit" value="导入"/><br/>
					</form>
				</td>
            </tr>
      		</table>
      		
       </div>
       <!---table_area结束--->
   </div>

 
</body>
</html>