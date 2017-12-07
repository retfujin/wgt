<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<base target="_self"></base>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
				
					if($('input[name="entity.leaveType"]:checked').length==0){
						alert("请选择请假类型");
						return false;
					}
					if($('#leaveReason').val()==''){
						alert("请填写请假原因");
						return false;
					}
					if($('#beginDate').val()==''){
						alert("请填写请假开始日期");
						return false;
					}
					if($('#endDate').val()==''){
						alert("请填写请假截至日期");
						return false;
					}
					
					$("input[type='submit']").each(function(){
						$(this).attr("disabled",true);
					});
					
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
<script type="text/javascript">
$(function(){
	$('#submit1').click(function(){
		$('#saveType').val('1');
		$('#ff').submit();
	});
	$('#submit2').click(function(){
		$('#saveType').val('2');
		$('#ff').submit();
	});
});

</script>
<body>
  
  <form id="ff" action="leave!save.action" name="frmAdd"  method="post">
  <s:hidden id="saveType" name="saveType"></s:hidden>
  <s:hidden name="entity.id"></s:hidden>

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
    	<td><div><s:property value="entity.planContent" escape="false"/></div></td>
    </tr>  
    </table>
<br/>
<table width="90%" border="1" align="center" cellpadding="2" cellspacing="1" class="tablegray">
<tr><td colspan="8">审批情况</td></tr>
<s:iterator value="entity.process.modules" var="modules">
<tr>
	<td width="80">审批环节</td><td><s:property value="#modules.moduleName"/></td>
	<td width="80">审批人</td><td><s:property value="#modules.executorName"/></td>
</tr>
<tr>
	<td width="80">审批结果</td><td><s:if test="#modules.state==0">待审批</s:if><s:elseif test="#modules.state==1">审批通过</s:elseif><s:elseif test="#modules.state==2">不通过</s:elseif></td>
	<td width="80">审批日期</td><td><s:property value="#modules.executorTime"/>&nbsp;</td>
</tr>
<tr>
<td width="80">审批意见</td><td colspan="3"><s:property value="#modules.executorOpinion"/>&nbsp;</td>
</tr>

</s:iterator>

</table>
</form>
  </body>
</html>
