<%@ page contentType="text/html; charset=UTF-8" %>
<HTML>
<HEAD>
<title></title>
<script type="text/javascript" src="styles/divDialog/modelDialog.js"></script>
<script type="text/javascript" src="/menubar/js/menu-for-applications.js"></script>
<script type="text/javascript" src="/js/check.js"></script>
<style type="text/css">
	body{
		margin:0px;
	}
	#otherMenu{
	width:100%;
 	<!--position:fixed;
    left:0px;
    top:0px;
    -->
	}

</style>
<style   media="print">   
  .Noprint{display:none;}   
  .PageNext{page-break-after:   always;} 
   
</style>
</head>
<body>
<OBJECT id="WebBrowser1" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" width="0" VIEWASTEXT> 
</OBJECT> 
<div id="otherMenu"></div>
<script type="text/javascript">

function pr(){
	//parent.document.all.leftFrame.style.display='none';
	
	document.all.WebBrowser1.ExecWB(7,1);
	//parent.document.all.leftFrame.style.display='visible';
}

function p(){
	document.all.WebBrowser1.ExecWB(6,1);
}
// Custom functions for the demo
function goBlank(url){
	//alert(url);
//	window.open();
  //  window.showModelessDialog('scopeQueryCondition.action?rUrl=qMenu',window,"help:0;resizable:1;status=0;scrollbars=0;dialogWidth=300;dialogHeight=200;center=true");
	//window.location.reload(true);
//	window.open ('scopeQueryCondition.action?rUrl=qMenu','newwindow','height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	window.open (url,'newwindow','height=800,width=1050,top=50,left=50,toolbar=no,menubar=no,location=no, status=no,scrollbars=yes, resizable=yes');

}
//隐藏显示区域
function show1()
{

	if(disSel.style.display=="none")
	{	
		disSel.style.display="block";
	}else
		disSel.style.display="none";
}
var myVar = 1;

</script>
<script type="text/javascript">


/* Another menu model */
var menuModel2 = new DHTMLSuite.menuModel();
/*
menuModel2.addItem(201,'抄表','','',false);
menuModel2.addItem(2011,'空','../images/disk.gif','',201);
menuModel2.addItem(2012,'空','../images/open.gif','',201);
menuModel2.addSeparator(201);
menuModel2.addItem(2013,'cc','','',201);
menuModel2.setSubMenuWidth(201,130);

menuModel2.addItem(202,'报表','','',false);
menuModel2.setSubMenuWidth(202,130);
menuModel2.addItem(2021,'空','','',202);
menuModel2.addItem(2022,'空','','',202);
*/
<%
	if(menuModel!=null){
		for(int i=0;i<menuModel.length;i++){
			out.println(menuModel[i]);
		}
	}
%>
menuModel2.init();

var menuBar2 = new DHTMLSuite.menuBar();
menuBar2.addMenuItems(menuModel2);
menuBar2.setTarget('otherMenu');
menuBar2.init();

//menuBar2.setMenuItemState(2013,'disabled');	// Disable menu item "Save As" 

</script>
</body>
</html>