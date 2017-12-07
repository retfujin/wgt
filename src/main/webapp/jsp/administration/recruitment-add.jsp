<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
					if(frm.departmentname.value=='')
					{
						alert("部门名称不能为空");
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
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>招聘申请</p> 
            </div>
          <form id="ff" method="post" action="recruitment!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName">
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处</td>
		    <td ><s:select name="entity.areaId" list="retList" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select></td> 
		  </tr>
		  <tr>
		    <td >用人部门 <font color="red">*</font></td>
		    <td ><s:select name="entity.departmentname" list="viewList" id="departmentname" listKey="name" listValue="name" theme="simple"></s:select></td> 
		  </tr>
		  <tr>
		    <td >招聘职位 </td>
		    <td ><s:select name="entity.position" list="viewList1" id="position" listKey="name" listValue="name" theme="simple"></s:select></td>
		  </tr>
		  <tr>
		    <td >人数 </td>
		    <td ><s:textfield name="entity.number" id="number" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >面试负责人 </td>
		    <td ><s:textfield name="entity.pheader" id="pheader" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >工作地点 </td>
		    <td ><s:textfield name="entity.address" id="address" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >预定聘用日期 </td>
		    <td ><s:textfield name="entity.begindate" id="begindate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >职位描述 </td>
		    <td ><s:textarea name="entity.remark" id="remark" cols="40" rows="3" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td >此职位发展计划 </td>
		    <td ><s:textarea name="entity.developplan" id="redevelopplanmark" cols="40" rows="3" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td >教育背景（专业 年限） </td>
		    <td ><s:textarea name="entity.education" id="education" cols="40" rows="2" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td >所需经验 技能 </td>
		    <td ><s:textarea name="entity.experience" id="experience" cols="40" rows="2" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td >其它要求（英语 计算机等） </td>
		    <td ><s:textarea name="entity.other" id="other" cols="40" rows="2" theme="simple"></s:textarea></td>
		  </tr>
		  <tr class="footer">
                        <td colspan="2">
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
