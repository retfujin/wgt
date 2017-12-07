<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<base target="_self"></base>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.js"></script>
<script>
	var KE = KindEditor;
	KindEditor.ready(function(K) {
		KE = K.create('textarea[name="entity.planContent"]', {
			cssPath : '<%=request.getContextPath()%>/kindeditor/plugins/code/prettify.css',
			uploadJson : '<%=request.getContextPath()%>/kindeditor/jsp/upload_json.jsp',
			allowFileManager : false
		});

});
</script>

<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
				
					var v_contorl = document.getElementById("content1");
					v_contorl.value=KE.html();
					
					if($('#planTitle').val()==''){
						alert("请填写计划标题");
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
  
  <form id="ff" action="plan!save.action" name="frmAdd"  method="post">
  <s:hidden id="saveType" name="saveType"></s:hidden>
  <s:hidden name="entity.id"></s:hidden>
<table width="90%" border="1" align="center" cellpadding="2" cellspacing="1" class="tablegray">
	<tr>
      <td >计划填写人：
      </td>
      <td><s:textfield name="entity.employeeName" value="%{#session.employeeName}" id="name" size="10"  maxlength="20" theme="simple" readonly="true"/></td>
    </tr>
     <tr>
      <td>标题：</td>
      <td><s:textfield id="planTitle" name="entity.planTitle" size="80"/> </td>
    </tr>
   <tr>
      <td>内容：<font color="#FF0000">*</font></td>
    <td >
    	<textarea id="content1" name="entity.planContent" cols="100" rows="8" style="width: 100%;height:200px;visibility:hidden;">${entity.planContent}</textarea>
    	</td>
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
