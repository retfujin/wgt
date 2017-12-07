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
			   		cell.innerHTML="<input type='text' name='goodsname' />";
			   	if(i==1)
			   		cell.innerHTML="<input type='text'  name='unit' size='10' />";
		   		if(i==2)
			   		cell.innerHTML="<input type='text'  name='num' size='10' />";
		   		if(i==3)
			   		cell.innerHTML="<input type='text'  name='brand' size='10' />";
		   		if(i==4)
			   		cell.innerHTML="<input type='text'  name='price' size='8' />";
		   		if(i==5)
			   		cell.innerHTML="<input type='text'  name='inuse' size='30' />";
				
			 }
		}
		 
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>物品采购申请</p> 
            </div>
          <form id="ff" method="post" action="procurement!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName" />
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td>管理处</td>
		  	<td ><s:select list="retList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
		  	<td>&nbsp;</td><td>&nbsp;</td><td>申请日期</td><td><s:textfield name="entity.applydate" id="applydate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		  	<td>部门<font color="red">*</font></td>
		  	<td><s:select list="viewList1" name="entity.departmentname" id="departmentname" listKey="name" listValue="name" theme="simple"></s:select> </td>
		    <td >申请人<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" size="10" theme="simple"></s:textfield></td>
		    <td >部门负责人 </td>
		    <td ><s:textfield name="entity.departname" id="departname" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		  <tr>
		    <td onclick="add();"><font color="red" title="点击'物品名称'可增加多个物品">物品名称</font></td>
		    <td >单位</td>
		    <td >数量 </td>
		    <td >品牌/型号 </td>
		    <td >参考价格(元) </td>
		    <td >用途 </td>
		  </tr>
		  <tr>
		    <td ><input type="text" name="goodsname"/> </td>
		    <td ><input type="text"  name="unit" size="10" /> </td>
		    <td ><input type="text"  name="num" size="10"/> </td>
		    <td ><input type="text"  name="brand" size="10" /> </td>
		    <td ><input type="text"  name="price" size="8"/> </td>
		    <td ><input type="text"  name="inuse" size="30"/> </td>
		  </tr> 
		  <tr class="footer">
                        <td colspan="6">
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
