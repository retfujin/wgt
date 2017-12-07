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
      <td >姓名：
      <font color="#FF0000">*</font>
      </td>
      <td><s:textfield name="entity.employeeName" value="%{#session.employeeName}" id="name" size="10"  maxlength="20" theme="simple" readonly="true"/></td>
    </tr>
     <tr>
      <td>职位：</td>
      <td><s:textfield name="entity.duty" /> </td>
    </tr>
    <tr>
      <td>请假类型：</td>
      <td><font color="#FF0000">*</font><s:radio name="entity.leaveType" list="#{'事假':'事假','病假':'病假','婚假':'婚假','丧假':'丧假' }" /></td>
    </tr>
   <tr>
      <td>请假原因：<font color="#FF0000">*</font></td>
    <td >
    	<s:textarea id="leaveReason" name="entity.leaveReason" rows="5" cols="60" />
    	</td>
    </tr>
     <tr>
      	<td>请假开始日期：<font color="#FF0000">*</font></td>
    	<td><s:textfield id="beginDate" name="entity.beginDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/></td>
    </tr>
     <tr>
      	<td>请假截至日期：<font color="#FF0000">*</font></td>
    	<td><s:textfield id="endDate" name="entity.endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/></td>
    </tr>
    <tr>
      <td colspan="8" align="center"><input id="submit1" type="button" value="保存" class="a" >&nbsp;&nbsp;&nbsp;&nbsp;<input id="submit2" type="button" value="提交" class="a"></td>
  
    </tr>
    <tr>
    	<td colspan="8">说明：提交后，信息将不能修改</td>
    </tr>
    </table>
</form>
  </body>
</html>
