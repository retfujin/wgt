<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<%@ include file="/commons/meta.jsp"%>
<title>列表</title>
<style type="text/css">
.headersize{
	font-size: 15px;
}
</style>
</head>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
function disStateHouse(houseId){
	$("#divadvance").load("cellstate!getStateHouse.action?houseId="+houseId);
	document.getElementById('divadvance').style.display="block";
	popup_show();
}
function noStateHouse(){
	document.getElementById('divadvance').style.display="none";
}
</script>
<body>

<div id="popup" style="visibility: hidden; display: none;">
	<div id="divadvance" style="Z-INDEX: 2; LEFT: 15px; WIDTH: 288px; POSITION: absolute; TOP: 200px; HEIGHT: 300px; visibility: visible;"></div>
</div>

<table>
<tr>
	<td>&nbsp;&nbsp;</td><td width="10" bgcolor="#FF7575"></td><td class="headersize">空置</td>
	<td>&nbsp;&nbsp;</td><td width="10" bgcolor="#A5EDB5"></td><td class="headersize">入伙</td>
	<td>&nbsp;&nbsp;</td><td width="10" bgcolor="orange"></td><td class="headersize">未入伙</td>
</tr>
</table>

<table wnewsidth="100%" border="0" align="center" cellpadding="2" cellspacing="5" class="tableBorder">

	<s:iterator value="viewList" status="stuts">
		<s:if test="#stuts.index==0">
			<tr>
		</s:if>
			<td class="headersize" onclick="disStateHouse('${id}');" width="150px" bgcolor="<s:if test="viewList[#stuts.index].isSale=='空置'">#FF7575</s:if><s:elseif test="viewList[#stuts.index].isSale=='入伙'">#A5EDB5</s:elseif><s:else>orange</s:else>">
				${houseAddress}
			</td>
			<s:if test="#stuts.index!=0&&#stuts.index%10==9">
				</tr><tr>
			</s:if>
	</s:iterator>
	</tr>
</table>
<script type="text/javascript" language="javascript"> 
	var popup_dragging = false;
	var popup_target;
	var popup_mouseX;
	var popup_mouseY;
	var popup_mouseposX;
	var popup_mouseposY;
	var popup_oldfunction;
	function popup_display(x)
	{
	  var win = window.open();
	  for (var i in x) win.document.write(i+' = '+x[i]+'<br>');
	}
	function popup_mousedown(e)
	{
	  var ie = navigator.appName == "Microsoft Internet Explorer";
	  if ( ie  &&  window.event.button != 1) return;
	  if (!ie  &&  e.button            != 0) return;
	  popup_dragging = true;
	  popup_target   = this['target'];
	  popup_mouseX   = ie ? window.event.clientX : e.clientX;
	  popup_mouseY   = ie ? window.event.clientY : e.clientY;
	  if (ie)
	       popup_oldfunction = document.onselectstart;
	  else popup_oldfunction  = document.onmousedown;
	  if (ie)
	       document.onselectstart = new Function("return false;");
	  else document.onmousedown   = new Function("return false;");
	}
	function popup_mousemove(e)
	{
	  if (!popup_dragging) return;
	  var ie      = navigator.appName == "Microsoft Internet Explorer";
	  var element = document.getElementById(popup_target);
	  var mouseX = ie ? window.event.clientX : e.clientX;
	  var mouseY = ie ? window.event.clientY : e.clientY;
	  element.style.left = (element.offsetLeft+mouseX-popup_mouseX)+'px';
	  element.style.top  = (element.offsetTop +mouseY-popup_mouseY)+'px';
	  popup_mouseX = ie ? window.event.clientX : e.clientX;
	  popup_mouseY = ie ? window.event.clientY : e.clientY;
	}
	 
	function popup_mouseup(e)
	{
	  if (!popup_dragging) return;
	  popup_dragging = false;
	  var ie = navigator.appName == "Microsoft Internet Explorer";
	  var element = document.getElementById(popup_target);
	  if (ie)
	       document.onselectstart = popup_oldfunction;
	  else document.onmousedown   = popup_oldfunction;
	}
	
	
	function popup_show(){
		var e = e||window.event;
		  element = document.getElementById('popup');
		  element.style.position = "absolute";
		  element.style.visibility = "visible";
		  element.style.display    = "block";
		  element.style.left = (document.documentElement.scrollLeft+popup_mouseposX-100)+'px';
		  //element.style.top  = (document.documentElement.scrollTop +popup_mouseposY-190)+'px';
		  element.style.top  = (e.clientY+document.body.scrollTop+document.documentElement.scrollTop-190)+'px';
		  drag_element['target']   = 'popup';
		  drag_element.onmousedown = popup_mousedown;
	}
	
	
	
	function popup_mousepos(e)
	{
	  var ie = navigator.appName == "Microsoft Internet Explorer";
	  popup_mouseposX = ie ? window.event.clientX : e.clientX;
	  popup_mouseposY = ie ? window.event.clientY : e.clientY;
	}
	if (navigator.appName == "Microsoft Internet Explorer")
	     document.attachEvent('onmousedown', popup_mousepos);
	else document.addEventListener('mousedown', popup_mousepos, false);
	if (navigator.appName == "Microsoft Internet Explorer")
	     document.attachEvent('onmousemove', popup_mousemove);
	else document.addEventListener('mousemove', popup_mousemove, false);
	if (navigator.appName == "Microsoft Internet Explorer")
	     document.attachEvent('onmouseup', popup_mouseup);
	else document.addEventListener('mouseup', popup_mouseup, false);
</script>

</body>
</html>
