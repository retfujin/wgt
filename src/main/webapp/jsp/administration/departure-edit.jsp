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
					if(frm.name.value==''){
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
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>员工离职登记</p> 
            </div>
          <form id="ff" method="post" action="departure!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName" />
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td colspan="3"><s:select list="viewList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
     	  </tr>
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" theme="simple"></s:textfield> </td>
		    <td >部门 </td>
		    <td ><s:select name="entity.departmentname" list="retList" id="departmentname" listKey="name" listValue="name" theme="simple"></s:select> </td>
		  </tr>	
		  <tr>
		    <td >到职日</td>
		    <td ><s:textfield name="entity.workdate" id="workdate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield> </td>
		    <td >离职日 </td>
		    <td ><s:textfield name="entity.enddate" id="enddate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >离职原因</td>
		    <td colspan="3">
		    	<s:textarea name="entity.reason" cols="60" rows="6" id="reason" theme="simple"></s:textarea>
		     </td>
		  </tr>
		  <tr>
		   <td colspan="4">
		    	记录日期<s:textfield name="entity.recordtime" id="recordtime" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		  	</td>
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
    <!----add结束---> 
  
		  
</body>
</html>
