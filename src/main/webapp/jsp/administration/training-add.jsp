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
		function add()
		{
			 var row=document.getElementById("act_table").insertRow(document.getElementById("act_table").rows.length-1);			    
			 var countCell=document.getElementById("act_table").rows.item(0).cells.length;
			 for(var i=0;i<countCell;i++){
			   var cell=row.insertCell(i);
			    if(i==0)
			   		cell.innerHTML="<input type='text' name='trainingdate' readonly='readonly' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" size='10' />";
			   	if(i==1)
			   		cell.innerHTML="<input type='text'  name='content' size='40' />";
		   		if(i==2)
			   		cell.innerHTML="<input type='text'  name='unit' />";
		   		if(i==3)
			   		cell.innerHTML="<input type='text'  name='results' size='10' />";
				
			 }
		}
		 
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>员工培训档案</p> 
            </div>
          <form id="ff" method="post" action="training!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName" />
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td>管理处</td>
		  	<td ><s:select list="retList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
		  	<td>&nbsp;</td>
		  	<td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >员工姓名 <font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" theme="simple"></s:textfield></td>
		    <td >性别 </td>
		    <td ><s:radio name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" value="'男'" theme="simple"></s:radio></td>
		  </tr>
		  <tr>
		  <td >出生年月 </td>
		    <td ><s:textfield name="entity.brithday" id="brithday" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield></td>
		    <td >岗位 </td>
		    <td ><s:textfield name="entity.position" id="position" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >学历 </td>
		    <td ><s:textfield name="entity.education" id="education" theme="simple"></s:textfield></td>
		    <td >职称 </td>
		    <td ><s:textfield name="entity.title" id="title" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td onclick="add();"><font color="red" title="点击'培训日期'可增加次数">培训日期</font></td>
		    <td >参加培训内容 </td>
		    <td >主办单位 </td>
		    <td >考核成绩 </td>
		  </tr>
		  <tr>
		    <td ><input type="text" name="trainingdate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" /> </td>
		    <td ><input type="text"  name="content" size="40" /> </td>
		    <td ><input type="text"  name="unit" /> </td>
		    <td ><input type="text"  name="results" size="10" /> </td>
		  </tr> 
		  <tr class="footer">
                        <td colspan="4">
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
