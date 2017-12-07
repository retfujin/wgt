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
			title:'业户表行度审核',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>

<script type="text/javascript">

function initaudit(){
	var month = document.getElementById('month');
	if(month==null || month.value==''){
		alert("请选择抄表月");
		return;
	}
	
	if(confirm("确认要审核此数据吗?")){
		var areaId = document.getElementById('areaId');
		var meterType = document.getElementById('meterType');
		var month = document.getElementById('month');
		
		document.getElementById('btn1').disabled=true;
		document.getElementById('btn1').value='正在审核';
		
		$.post("ownermeterrecord!initsave.action",{areaId:areaId.value,meterType:meterType.value,month:month.value},
			function(data,textStatus){
				alert(data);
				document.getElementById('btn1').disabled=false;
				document.getElementById('btn1').value='审核';
		});
	}
}
</script>

<div id="tab" style="width:100%"></div>

<!-- 隐藏的筛选条件 -->
<div id ="disSel">

 <form  class="search_form" name="form1" action=""  method="get" target="ifr">
	小区<s:select id="areaId" name="areaId"  list="viewList" listKey="id" listValue="areaName" theme="simple" />
	表类型<select id="meterType" name="meterType">
				<option value="电表">电表</option>
				<option value="水表">水表</option>
				<option value="热水表">热水表</option>
				<option value="暖气表">暖气表</option>
				</select>
	抄表月：<input type="text" name="month" id="month" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="12" readonly="readonly"/>			
	<input type="button" id="btn1"  value="审核" class="search_btn" onclick="initaudit()"/>
	<br>
	<br><br>
</form>
	  
</div>
<script type="text/javascript" src="/styles/divDialog/modelDialog.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
</body>
</html>
