<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<html>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.name.value=='')
					{
						alert("姓名不能为空");
						return false;
					}
					var text=$("#areaId").find("option:selected").text();
					$("#areaName").val(text);
					return true;
				},
				success:function(data){
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
<%@ include file="/commons/meta.jsp"%>
<%
	String applyid = request.getParameter("id")!=null ? request.getParameter("id") :"";
%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>员工录用登记</p> 
            </div>
          <form id="ff" method="post" action="employed!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName">
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td ><s:select list="viewList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" theme="simple"></s:textfield> </td>
     	  </tr>	
		  <tr>
		    <td >性别 </td>
		    <td ><s:radio name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" theme="simple"></s:radio> </td>
		    <td >年龄</td>
		    <td ><s:textfield name="entity.age" id="age" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		  	<td >学历 </td>
		    <td ><s:textfield name="entity.education" id="education" theme="simple"></s:textfield> </td>
		    <td >专业</td>
		    <td ><s:textfield name="entity.professional" id="professional" theme="simple"></s:textfield> </td>
		  </tr>		 
		  <tr>
		    <td >职称</td>
		    <td ><s:textfield name="entity.title" id="title" theme="simple"></s:textfield></td>
		    <td >应聘岗位</td>
		    <td ><s:select name="entity.position" id="position" list="retList" listKey="name" listValue="name" theme="simple"></s:select> </td>
		  </tr>
		  <tr class="footer">
                        <td colspan="8">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
			</table>
            </form>
        </div>
    </div>
  
</body>
</html>
