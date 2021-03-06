<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<base target="_self"></base>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
				
					if($('input[name="processModel.state"]:checked').length==0){
						alert("请选择审批结果");
						return false;
					}
					if($('#executorOpinion').val()==''){
						alert("请填写审批意见");
						return false;
					}
					
					return true;
				},
				success:function(data){
					$("input[type='submit']").each(function(){
						$(this).attr("disabled",false);
					});
					
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
<body>
  

<table width="90%" border="1" align="center" cellpadding="2" cellspacing="1" class="tablegray">
	<tr>
      <td width="100">姓名：  </td>
      <td><s:property value="entity.employeeName" />&nbsp;</td>
    <td width="100">部门：  </td>
      <td><s:property value="entity.departmentName" />&nbsp;</td>
    </tr>
   <tr>
      <td>工作计划标题</td>
    <td colspan="3">
    	<s:property value="entity.planTitle"/>
    	</td>
    </tr>
     <tr>
      	<td>工作计划内容：</td>
    	<td colspan="3"><s:property value="entity.planContent"/></td>
    </tr>  
    </table>
<br/>
<table width="90%" border="1" align="center" cellpadding="2" cellspacing="1" class="tablegray">

<s:iterator value="entity.process.modules" var="modules">

<s:if test="#modules.state==1">
<tr><td colspan="8">审批情况</td></tr>
<tr>
	<td width="80">审批环节</td><td><s:property value="#modules.moduleName"/></td>
	<td width="80">审批人</td><td><s:property value="#modules.executorName"/></td>
</tr>
<tr>
	<td width="80">审批结果</td><td>审批通过</td>
	<td width="80">审批日期</td><td><s:property value="#modules.executorTime"/>&nbsp;</td>
</tr>
<tr>
<td width="80">审批意见</td><td colspan="3"><s:property value="#modules.executorOpinion"/>&nbsp;</td>
</tr>
</s:if>
<s:elseif test="#modules.state==0">
	<form id="ff" action="plan!approvalSave.action" method="post">
		<input type="hidden" name="processModel.id" value="<s:property value="#modules.id"/>" />	
		<tr>
			<td>审批结果</td><td><s:radio name="processModel.state" list="#{'1':'通过','2':'不通过'}" /></td>
		</tr>
		<tr>
			<td>审批意见</td><td><input id="executorOpinion" name="processModel.executorOpinion"></td>
		</tr>
		<tr>
			<td colspan="8"><input id="submit1" type="submit" value="保存" class="a" ></td>
		</tr>
	</form>
</s:elseif>
</s:iterator>

</table>

  </body>
</html>
