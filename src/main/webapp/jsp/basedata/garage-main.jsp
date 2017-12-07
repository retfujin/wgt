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
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
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
			id:'toolbarPlugin1',
			title:'车位资料',
			icon:'/tabpanel/image/read-y.gif',
			html:'<iframe id="callIframe1" src="/basedata/garage!list.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true
		},{
			id:'toolbarPlugin2',
			title:'车位租赁',
			icon:'/tabpanel/image/read-y.gif',
			html:'<iframe id="callIframe2" src="/basedata/carport!list.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true,
			lazyload : true
		},{
			id:'toolbarPlugin4',
			title:'临时租赁',
			icon:'/tabpanel/image/read-y.gif',
			html:'<iframe id="callIframe4" src="/basedata/carport!temporary.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true,
			lazyload : true
		},{
			id:'toolbarPlugin3',
			title:'租赁历史',
			icon:'/tabpanel/image/read-y.gif',
			html:'<iframe id="callIframe3" src="/basedata/carport!historylist.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true,
			lazyload : true
		}
		
		
		]
	});
});
</script>
<div id="tab" style="width:400px;"></div>
</body>
</html>