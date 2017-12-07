<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TabPanel demo</title>
<style>
html, body {
	width : 100%;
	height : 100%;
	padding : 0;
	margin : 0;
	overflow : hidden;
}


</style>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>

</head>

<body>

<script type="text/javascript">

/////////////////////////////////////////////////////////////////
var tabpanel;
$(document).ready(function(){

	  tabpanel = new TabPanel({
		renderTo:'tab',
		autoResizable:true,
	//	border:'none',
		active : 0,
		maxLength : 10,
		items : [{
			id:'toolbarPlugin',
			title:'出入登记',
			icon:'/tabpanel/image/read-y.gif',
			html:'<iframe id="callIframe" src="/basedata/ownermove!list.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: false
		},{
			id:'toolbarPlugin1',
			title:'装修记录',
			icon:'/tabpanel/image/read-y.gif',
			html:'<iframe id="callIframe1" src="/basedata/ownerdecorate!list.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: false,
			lazyload : true
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>
</body>
</html>