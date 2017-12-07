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
		function samename(){
			$("#applyname").val($("#name").val());
		}
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>请假申请</p> 
            </div>
          <form id="ff" method="post" action="vacate!save.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName">
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 <font color="red">*</font></td>
		    <td ><s:select name="entity.areaId" id="areaId" list="viewList" listKey="id" listValue="areaName" theme="simple"></s:select></td> 
		  </tr>
		  
		  <tr>
		    <td >姓名 <font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" onkeyup="samename();" theme="simple"></s:textfield></td> 
		  </tr>
		  <tr>
		    <td >岗位职位 </td>
		    <td ><s:select name="entity.position" id="position" list="viewList1" listKey="name" listValue="name" theme="simple"></s:select></td>
		  </tr>
		  <tr>
		    <td >所在单位或部门 </td>
		    <td ><s:select name="entity.departmentname" id="departmentname" list="retList" listKey="name" listValue="name" theme="simple"></s:select></td>
		  </tr>
		  <tr>
		    <td >假别 </td>
		    <td ><s:checkbox name="entity.typeone" id="typeone" theme="simple">事假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typetwo" id="typetwo" theme="simple">婚假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typethree" id="typethree" theme="simple">病假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typefour" id="typefour" theme="simple">丧假</s:checkbox>&nbsp;&nbsp;
		    <br>
		    <s:checkbox name="entity.typefive" id="typefive" theme="simple">产假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typesix" id="typesix" theme="simple">年休假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typeseven" id="typeseven" theme="simple">工伤假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typeeight" id="typeeight" theme="simple">调休</s:checkbox>&nbsp;&nbsp;</td>
		  </tr>
		  <tr>
		    <td >时间 </td>
		    <td ><s:textfield name="entity.begindate" id="begindate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    	至<s:textfield name="entity.enddate" id="enddate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    	<br>
		    	共计<s:textfield name="entity.number" id="number" size="10" theme="simple"></s:textfield>天
		    	</td>
		  </tr>
		  <tr>
		    <td >原因 </td>
		    <td ><s:textarea name="entity.reason" id="reason" cols="40" rows="3" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td >紧急联络电话 </td>
		    <td ><s:textfield name="entity.phone" id="phone" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >教育背景（专业 年限） </td>
		    <td ><s:textfield name="entity.address" id="address" theme="simple"></s:textfield></td>
		  </tr>
		   <tr>
		    <td >申请人 </td>
		    <td ><s:textfield name="entity.applyname" id="applyname" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.applydate" id="applydate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10"  theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >工作项目交接</td>
		    <td ><s:textarea name="entity.content" id="content" cols="40" rows="3" theme="simple"></s:textarea></td>
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
