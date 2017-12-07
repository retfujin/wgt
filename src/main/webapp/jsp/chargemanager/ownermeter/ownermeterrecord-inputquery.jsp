<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
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
			title:'业户表行度录入',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>

<script type="text/javascript">
function frmquery(){
	var v_edificeId = document.getElementById('edificeId');
	if(v_edificeId==null || v_edificeId.value==''){
		alert("请选择楼栋");
		return false;
	}
	var month = document.getElementById('month');
	if(month==null || month.value==''){
		alert("请选择抄表月");
		return false;
	}
}
function sub1(a){
	var v_edificeId = document.getElementById('edificeId');
	if(v_edificeId==null || v_edificeId.value==''){
		alert("请选择楼栋");
		return;
	}
	var month = document.getElementById('month');
	if(month==null || month.value==''){
		alert("请选择抄表月");
		return ;
	}
	var obj = document.getElementById('areaId');
	var strsel = obj.options[obj.selectedIndex].text;
	document.getElementById("areaName").value=strsel;
	
	if(a==1)
		document.getElementById("form1").action="ownermeterrecord!downExcel.action";
	else
		document.getElementById("form1").action="ownermeterrecord!getOwnerMeterInput.action";
	document.getElementById("form1").submit();   
}
function sub2(){
	var obj = document.getElementById('areaId');
	var strsel = obj.options[obj.selectedIndex].text;
	var v_meterType = document.getElementById('meterType').value;
	
	if(confirm("确定要清除"+strsel+"业户"+v_meterType+"当前所有抄表记录吗?")){
		document.getElementById("form1").action="ownermeterrecord!delMeterData.action";   
		document.getElementById("form1").submit();
	}
	   
}
</script>
<script language="javascript">
function formCheck()
{

if(document.form2.theFile.value == "")
	{
		alert("请选择要导入的文件！");
		return false;
	}
	return true;
}
</script>
<div id="tab" style="width:100%"></div>

<!-- 隐藏的筛选条件 -->
<div id ="disSel">

 <form  class="search_form" name="form1" action=""  method="get" onSubmit="return frmquery()" target="ifr">
	<input type="hidden" name="areaName" value=""/> 
	小区<s:select id="areaId" name="areaId"  list="retList" listKey="id" listValue="areaName" theme="simple" onchange="doRequest();"/>
	楼栋<div id="target" style="display: inline;"></div>
	表类型<select id="meterType" name="meterType">
				<option value="电表">电表</option>
				<option value="水表">水表</option>
				<option value="热水表">热水表</option>
				<option value="暖气表">暖气表</option>
				</select>
	抄表月：<input type="text" name="month" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="12" readonly="readonly"/>			
	<input type="button" value="查询" class="search_btn" onclick="sub1(0)"/>
	<input type="button" value="下载" class="search_btn" onclick="sub1(1)"/>
	<br>
	<br><br>
</form>
	<form class="search_form" action="ownermeterrecord!uploadExcel.action" method ="post" enctype="multipart/form-data" name="form2" onSubmit="return formCheck()">
          &nbsp;基础资料信息导入：<input type="file" name="theFile" id="file" />
          <input name="dd" type="submit" class="search_btn" value="导入"/><br />
        
	</form>

<iframe id="ifr" name="ifr" width="90%" height="100%" frameborder="0" src=""></iframe>
</div>
<script type="text/javascript" src="/styles/divDialog/modelDialog.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function doRequest(){
	$("#target").load("../../basedata/edifice!getajaxedifice.action?areaId="+$("#areaId").val());
}
doRequest();

</script>
</body>
</html>
