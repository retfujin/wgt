<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小区信息</title>
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
			title:'到项目收费',
			icon:'',
			html:'<iframe id="callIframe1" src="/chg/assign!add.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true
		}
		/*
		,{
			id:'tab2',
			title:'到户临时收费',
			icon:'',
			html:'<iframe id="callIframe2" src="/chg/assign!add2.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true,
			lazyload : true
		}
		*/
		,{
			id:'tab3',
			title:'到户常规收费',
			icon:'',
			html:'<iframe id="callIframe3" src="/chg/assign!add3.action" width="100%" height="100%" frameborder="0"></iframe>',
			closable: true,
			lazyload : true
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>
</body>
</html>