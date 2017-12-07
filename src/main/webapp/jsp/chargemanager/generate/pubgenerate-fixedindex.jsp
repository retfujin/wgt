<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
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
			title:'公摊费用',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<body>
<div id="tab" style="width:400px;"></div>
<div id="disSel">
<form action="chargegenerate!resultMoney.action" method="post" id="form1">
<table width="80%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
<tr>
<th height="25" colspan="2" class="tableHeaderText">生成费用</th>
</tr>
<tr>
	<td align="right" width="20%">选择小区：</td>
	<td><s:select list="viewList" name="areaId" listKey="id" listValue="areaName" theme="simple" onchange="doRequest1()" /></td>
</tr>
<tr>
	<td align="right">生成月份：</td>
	<td>
	<s:textfield size="7" name="beginRecordMonth" id="beginRecordMonth" readonly="true"  onclick="WdatePicker({dateFmt:'yyyy-MM'})" theme="simple"/>
	</td>
</tr>
<tr>
<td align="right">费用：</td>
	<td>
		<div id="target"></div>
	</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><font color="red">(只能单一生成费用)</font></td>
</tr>	
<tr>
<td>&nbsp;</td>
<td><input type="button" id="btn1" name="btn1" value="生成" onclick="doRequest();"/></td>
</tr>
</table>
</form>
<p></p>
<div id="target1" align="center">
</div>
<script src="/js/jquery.js" type="text/javascript"></script>
<SCRIPT type="text/javascript">
function checkret(){
	if(document.getElementById('beginRecordMonth').value.length<1)
	{
		alert("请选择生成月份!");
		return false;
	}
	return true;
}



function doRequest(){

	var areaId = encodeURI($("#areaId").val());
	var beginRecordMonth = document.getElementById('beginRecordMonth').value;
	var frm1 = document.getElementById('form1');
	
	var v_chargeName="";
	var v_tmp = document.getElementsByName("chargeName");
	for(var i=0;i<v_tmp.length;i++){
		if(v_tmp[i].checked){
			v_chargeName=v_tmp[i].value;
		}
	}
	if(beginRecordMonth==''){
		alert("请选择月份");
		return;
	}

	if(v_chargeName==''){
		alert("请选择收费项目");
		return;
	}
	
	document.getElementById('btn1').disabled=true;
	
	$("#target1").html("<div align='center'><image src='/images/wait.gif' /><br/>正在生成应收费用，请等待</div>");
	
	$.post("pubgenerate!resultMoney.action",{areaId:areaId,beginRecordMonth:beginRecordMonth
		,chargeName:v_chargeName},
		function(data,textStatus){
			//alert(data);
			var strArray=new Array(); 
  			strArray=data.split(";");
			$("#target1").html(decodeURI(strArray[0]));
			document.getElementById('btn1').disabled=false;
			if(strArray[0]=='生成成功'){
				window.location.href="pubgenerate!preview.action?chargeIds="+strArray[1];
			}
		}
	);
}
</SCRIPT>
<script type="text/javascript">
function doRequest1(){
	$("#target").load("../basedata!ajaxRadioList_pub.action?areaId="+$("#areaId").val());
}
doRequest1();
</script>

</div>
</body>
</html>
