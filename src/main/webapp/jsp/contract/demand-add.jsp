<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/hanx.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.departname.value=='')
					{
						alert("申请部门不能为空");
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
               <p>培训需求申请</p> 
            </div>
            <form id="ff" method="post" action="demand!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName">
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >管理处 </td>
		    <td ><s:select list="retList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
		  </tr>
		  <tr>
		  	<td >填表日期 </td>
		    <td ><s:textfield name="entity.filldate" id="filldate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >申请部门 </td>
		    <td ><s:select name="entity.departname" id="departname" list="viewList" listKey="name" listValue="name" theme="simple"></s:select> </td> 
		  </tr>
		  <tr>
		  	<td >申请人 </td>
		    <td ><s:textfield name="entity.applyname" id="applyname" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >培训方式 </td>
		    <td ><s:textfield name="entity.way" id="way" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >时间安排</td>
		    <td ><s:textfield name="entity.arrange" id="arrange" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >培训主要对象 </td>
		    <td ><s:textarea name="entity.objectname" id="objectname" cols="40" rows="3"  theme="simple"></s:textarea> </td> 
		  </tr>
		  <tr>
		  	<td >申请原因 </td>
		    <td ><s:textarea name="entity.reason" id="reason" cols="40" rows="3" theme="simple"></s:textarea> </td> 
		  </tr>
		  <tr>
		  	<td >申请培训内容 </td>
		    <td ><s:textarea name="entity.content" id="content" cols="40" rows="3" theme="simple"></s:textarea> </td> 
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
<script type="text/javascript">
//设置当前日期
function getNowDate(contorl) {
	var v_contorl = document.getElementById(contorl);
	if(v_contorl.value==''){
		var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mi= date.getMinutes();
		var s = date.getSeconds();
		v_contorl.value = y + "-" + (m > 9 ? m : ('0' + m)) + "-" + (d > 9 ? d : ('0' + d))+ " " + (h > 9 ? h : ('0' + h))+ ":" + (mi > 9 ? mi : ('0' + mi))+ ":" + (s > 9 ? s : ('0' + s));
	}
}
getNowDate("filldate");
</script>
</body>
</html>
