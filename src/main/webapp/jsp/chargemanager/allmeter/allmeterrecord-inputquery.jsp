<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %> 
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function sub1(){
	document.getElementById("form1").action="allmeterrecord!getAllMeterInput.action";   
	var obj = document.getElementById('areaId');
	var strsel = obj.options[obj.selectedIndex].text;
	document.getElementById("areaName").value=strsel;
	
	document.getElementById("form1").submit();   
}
function sub2(){
	
	var obj = document.getElementById('areaId');
	var strsel = obj.options[obj.selectedIndex].text;
	document.getElementById("areaName").value=strsel;
	
	var v_meterType = document.getElementById('meterType').value;
	
	if(confirm("确定要清除"+strsel+"总"+v_meterType+"当前所有抄表记录吗?")){
		document.getElementById("form1").action="allmeterrecord!delMeterData.action";   
		document.getElementById("form1").submit(); 
	}
	
	
	  
}
</script>
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
			title:'行度录入',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<body>
<div id="tab" style="width:400px;"></div>
<!-- 隐藏的筛选条件 -->
<div id ="disSel" >

 <form  class="search_form" name="form1" action=""  method="get" target="ifr">
	<input type="hidden" name="areaName" value=""/> 
	小区<s:select id="areaId" name="areaId"  list="retList" listKey="id" listValue="areaName" theme="simple" onchange="doRequest();"/>
	楼栋<div id="target" style="display: inline;"></div>
	表类型<select id="meterType" name="meterType">
				<option value="电表">电表</option>
				<option value="水表">水表</option>
				<option value="热水表">热水表</option>
				<option value="暖气表">暖气表</option>
				</select>
	<input type="button" value="查询" class="search_btn" onclick="sub1()"/>
	<input type="button" value="清除"  class="search_btn" onclick="sub2()"/>
</form>


<iframe id="ifr" name="ifr" width="100%" height="100%" frameborder="0" src=""></iframe>
</div>
</body>
</html>
