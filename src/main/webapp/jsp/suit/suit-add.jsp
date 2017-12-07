<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head> 
</head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../../js/func.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
</head>
<body>
<%
	String currentTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
%>
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
			title:'投诉登记',
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
               <p>客户投诉受理登记表(<a href="suit!print.action?id=" target="_blank">打印</a>)</p> 
            </div>
            <form id="ff" method="post" action="suit!save.action" name="frmAdd" >
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区<font color="red">*</font></td>
                        <td><s:select name="entity.areaId"  id="areaId" list="retList" headerKey="" headerValue="==请选择小区==" listKey="id" cssClass="con_input_con"  listValue="areaName" theme="simple" onchange="getEdifice()"></s:select></td>
                        <td height="35" align="center">楼栋</td>
                        <td><div id="edifice"><select name="edificeId" id="edificeId" ></select></div></td>
                    </tr>
                    <tr>
					   <td height="35" align="center">房间</td>
					  <td><div id="house"><select name="entity.houseId" id="houseId"></select></div></td>
					  <td height="35" align="center">填表时间：</td>
					  <td><input type="text" name="entity.suitTime" id="suitTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<%=currentTime%>" readonly="readonly" size="20" class="con_input_con" /><font color="red">*</font></td>
					 </tr>
					  <tr>
					  	 <td height="35" align="center">客户姓名</td>
					  	<td><s:textfield name="entity.suitName" id="suitName"   size="27" cssClass="con_input_con" theme="simple"/><font color="red"></font></td>
					     <td height="35" align="center">联系电话</td>
					    <td><s:textfield name="entity.suittel" id="suittel"   size="27" cssClass="con_input_con" theme="simple"/><font color="red"></font></td>
					  </tr>
					  <tr>
					    <td height="35" align="center">客户地址</td>
					  	<td ><s:textfield name="entity.suitDepartment" id="suitDepartment"   size="27" cssClass="con_input_con" theme="simple"/>
					  	<font color="red"></font></td>
					     <td height="35" align="center">投诉方式</td>
					    <td><s:select name="entity.suitWay" id="suitWay"  list="#{'来访':'来访','电话':'电话','短信':'短信','信函':'信函','其它':'其它'}"   cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	 <td height="35" align="center">投诉时间</td>
					  	<td><s:textfield name="entity.suitDate" id="suitDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"  size="20" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					     <td height="35" align="center">投诉类型</td>
					    <td><s:select name="entity.suitType" id="suitType"   list="#{'有效':'有效','无效':'无效'}"  cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	 <td height="35" align="center">投诉内容</td>
					  	<td class="r" colspan="3"><s:textarea cols="68" rows="6" name="entity.suitMess"  id="suitMess" cssClass="con_input_con" theme="simple" /><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	 <td height="35" align="center" colspan="2">记录人：<input type="text" name="entity.name1"   id="name1" value="<%=session.getAttribute("userName") %>" size="12" class="con_input_con"/><font color="red">*</font></td>
					  	<td colspan="2">日期：<input type="text" name="entity.suitDate1" id="suitDate1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<%=currentTime%>" size="20" readonly="readonly"  class="con_input_con" /><font color="red">*</font></td>
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

<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					
					if(document.getElementById('suitName').value==''){
						alert("客户姓名不能为空 ！");
						document.getElementById('suitName').focus();
						return false;
					}
					if(document.getElementById('suitDate').value==''){
						alert("投诉时间不能为空 ！");
						document.getElementById('suitDate').focus();
						return false;
					}
					if(document.getElementById('suitTime').value==''){
						alert("填表时间不能为空 ！");
						document.getElementById('suitTime').focus();
						return false;
					}
					if(document.getElementById('suitMess').value==''){
						alert("投诉内容不能为空 ！");
						document.getElementById('suitMess').focus();
						return false;
					}
					if(document.getElementById('name1').value==''){
						alert("记录人不能为空 ！");
						document.getElementById('name1').focus();
						return false;
					}
					if(document.getElementById('suitDate1').value==''){
						alert("记录时间不能为空 ！");
						document.getElementById('suitDate1').focus();
						return false;
					}
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.location.href="suit!list1.action";
						
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
<script type="text/javascript">
function getEdifice(){
	$("#edifice").load("suit!getEdifice.action?areaId="+$("#areaId").val());
}
function getHouse(){
	var edificeId = frmAdd.edificeId.value;
	$("#house").load("suit!getHouseInfo.action?edificeId="+edificeId);
}
function getHouseDetail(){
	var houseId = frmAdd.houseId.value;
	$("#owner").load("suit!getHouseDetail.action?houseId="+houseId);
}
function send(){
	$.ajax({
		url: "suit!getHouseDetail.action",
		type: "GET",
		data: {houseId:document.all.houseId.value},
		dataType: "json",
		timeout: 1000,
		beforeSend: function(XMLHttpRequest){
			document.all.suitName.value="正在查找...";
			document.all.suitDepartment.value="正在查找...";
			document.all.suittel.value="正在查找...";
			
		},
		error: function(){
			alert("没有找到该房间");
			document.all.suitName.value="";
			document.all.suitDepartment.value="";
			document.all.suittel.value="";
		},
		success: function(data){
		// do something with xml
		//alert(data);
			document.all.suitDepartment.value=data.houseAddress;
			document.all.suitName.value=data.ownerName;
			document.all.suittel.value=data.mobTel;			
		}
		});
}

</script>
</body>
</html>