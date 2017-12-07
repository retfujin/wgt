<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
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
			title:'收费项目设定',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});

</script>
<body>
<div id="tab" style="width:400px;"></div>

<div id ="disSel">
<form class="search_form" name="form1" action="basedata!list.action" method="post"  target="ifr">
	管理处<s:select id="areaId" name="areaId" list="areaList" listKey="id" listValue="areaName"  theme="simple"/>
	<input type="submit" value="查询" class="search_btn" />
</form>
 

<iframe id="ifr" name="ifr" width="100%" height="470" frameborder="0" src=""></iframe>

	

</div>

</body>
<script type="text/javascript">
var v_areaId = document.getElementById('areaId').value;
if(v_areaId!=''){
	document.getElementById('ifr').src='basedata!list.action?areaId='+v_areaId;
}
</script>
</html>
