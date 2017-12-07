<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../js/func.js"></script>

<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
</head>
<body>

<script type="text/javascript">
var tabpanel;
$(document).ready(function(){
	  tabpanel = new TabPanel({
		renderTo:'tab',
		autoResizable:true,
	//	border:'none',
		active : 0,
		maxLength : 10,
		items : [{
			id:'tab1',
			title:'新保修记录',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>

<div id="disSel">

<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>客户请修登记表(<a href="repair!print.action?id=" target="_blank">打印</a>)</p> 
            </div>
            <form id="ff" method="post" action="repair!save.action" name="formadd" >
                <s:hidden id="areaName" name="customerrepair.areaName"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区编号</td>
                        <td><s:select name="customerrepair.areaId"  id="areaId" list="retList" headerKey="" headerValue="==请选择小区==" listKey="id" cssClass="con_input_con"  listValue="areaName" theme="simple" onchange="getEdifice()"></s:select><font color="red">*</font></td>
                        <td height="35" align="center">楼栋</td>
                        <td><div id="edifice"><select name="edificeId" id="edificeId" ></select></div><font color="red">*</font></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">房间</td>
                        <td><div id="house"><select name="customerrepair.houseId" id="houseId"></select></div><font color="red">*</font></td>
                        <td height="35" align="center">填表日期</td>
                        <td><input name="customerrepair.recordMonth" id="recordMonth" readonly="readonly"  size="20" class="con_input_con" /></td>
                    </tr>
                     <tr>
                        <td height="35" align="center">请修地址</td>
                        <td><s:textfield name="customerrepair.comaddress" id="comaddress"  cssClass="con_input_con" theme="simple"/><font color="red">公共区域请填写</font></td>
                        <td height="35" align="center">受理日期</td>
                        <td><s:textfield name="customerrepair.acceptedDate" id="acceptedDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true" size="20" cssClass="con_input_con" theme="simple"/></td>
                    </tr>
                    <tr>
                    <td height="35" align="center">请修内容</td>
				  	<td colspan="3"><s:textarea name="customerrepair.repairContext" id="repairContext" rows="8" cols="80" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
				  </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
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
  



</div>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					
					var text=$("#areaId").find("option:selected").text();
					$("#areaName").val(text);
					
					if(document.getElementById('areaId').value==''){
						alert("请选择管理处");
						document.getElementById('areaId').focus();
						return false;
					}
					if(document.getElementById('recordMonth').value==''){
						alert("请选择日期");
						document.getElementById('recordMonth').focus();
						return false;
					}
					
					if(document.getElementById('comaddress').value==''){
						if(document.getElementById('edificeId').value==''){
							alert("请选择楼栋与房间信息");
							return false;
						}
						if(document.getElementById('houseId').value==''){
							alert("请选择楼栋与房间信息");
							return false;
						}
					}
					
					if(document.getElementById('acceptedDate').value=='')
					{
						alert("请选择受理时间");
						document.getElementById('acceptedDate').focus();
						return false;
					}
					if(document.getElementById('repairContext').value=='')
					{
						alert("请输入请修内容");
						document.getElementById('repairContext').focus();
						return false;
					}
				return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.location.href="repair!list1.action";
						
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
<script type="text/javascript">
function getEdifice(){
	$("#edifice").load("repair!getEdifice.action?areaId="+$("#areaId").val());
}
function getHouse(){

	var edificeId = formadd.edificeId.value;
	$("#house").load("repair!getHouseInfo.action?edificeId="+edificeId);
}
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
getNowDate("recordMonth");
</script>
</body>
</html>
