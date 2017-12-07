<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script>
	function checkTime(){
		
		var houseId = document.getElementById('houseId').value;
		if(houseId==''){
			alert("请选择房间编号。");
			return false;
		}
		

		var bDate = document.getElementById('beginDate').value;
		var eDate = document.getElementById('endDate').value;
		if(bDate=='' || eDate==''){
			alert("请选择开始与截止日期。");
			return false;
		}
		return true;
	}
	
	function disAdvance(detailId){
		$("#popup_drag").load("report!getPayDetailById.action?detailId="+detailId);
		document.getElementById('popup_drag').style.display="block";
		popup_show();
	}
	
	function noAdvance(){
		document.getElementById('popup_drag').innerHTML="";
		document.getElementById('popup_drag').style.display="none";
	}
</script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
<script type="text/javascript" src="/js/exportTableToExcel.js"></script>
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
			title:'明细查询',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
function exportExl(){
	  exportExl('11');
}
</script>
<div id="tab" style="width:400px;"></div>
<div id="disSel"> 
<form class="search_form" name="form1" action="report!perreport.action" method="post" onsubmit="return checkTime()">
	管理处<s:select name="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"></s:select>
	房间编号<input type="text" id="houseId" name="houseId" value="${param.houseId}">
	开始日期<input type="text" name="beginDate" id="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="10" readonly="readonly" value="${param.beginDate}">
	截止日期<input type="text" name="endDate" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="10" readonly="readonly" value="${param.endDate}">
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="sample_popup" id="popup" style="visibility: hidden; display: none;">
	<div class="menu_form_header" id="popup_drag">
	</div>
</div>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>物业费明细表</p> 
           </div>
           <table class="table_border"  id="exportTable"  width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >客户名称</td>
			    	<td class="td_header" >房间号</td>
			    	<td class="td_header" >面积</td>
			    	<td class="td_header" >缴费月份</td>
			    	<td class="td_header" >应收款</td>
			    	<td class="td_header" >实收款</td>
			     	<td class="td_header" >优惠金额</td> 
			    	<td class="td_header" >欠费金额</td>
			    	<td class="td_header" >备注</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><s:property value="viewList[#stuts.index][0]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][1]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c"><a href="#" onmouseout="javascript:noAdvance()" onmouseover="javascript:disAdvance('<s:property value="viewList[#stuts.index][8]"/>')"><s:property value="viewList[#stuts.index][3]"/></a></td>
			    <td class="td_c" id="a1"><s:property value="viewList[#stuts.index][4]"/></td>
			    <td class="td_c" id="a2"><s:property value="viewList[#stuts.index][5]"/></td>
			    <td class="td_c" id="a3"><s:property value="viewList[#stuts.index][7]"/></td>
			    <td class="td_c" id="a4"><s:property value="viewList[#stuts.index][6]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][9]"/></td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header">合计</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="a1sum" align="center">&nbsp;</td>
					<td class="td_header" id="a2sum" align="center">&nbsp;</td>
					<td class="td_header" id="a3sum" align="center">&nbsp;</td>
					<td class="td_header" id="a4sum" align="center">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
				</tr>
      		</table>
           <div class="table_footer">
           	<a href="###" class="add fl" onclick="exportExl()">导出</a>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   
</div>
 
<script language="javascript">
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
  var element = document.getElementByIdx_x(popup_target);
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
  var element = document.getElementByIdx_x(popup_target);
  if (ie)
       document.onselectstart = popup_oldfunction;
  else document.onmousedown   = popup_oldfunction;
}
function popup_show()
{
  element      = document.getElementById('popup');
  drag_element = document.getElementById('popup_drag');
  element.style.position   = "absolute";
  element.style.visibility = "visible";
  element.style.display    = "block";
  element.style.left = event.clientX+'px';
  element.style.top =  event.clientY+'px';
 // element.style.left = (document.documentElement.scrollLeft+popup_mouseposX-10)+'px';
 // element.style.top  = (document.documentElement.scrollTop +popup_mouseposY-10)+'px';  
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
<script type="text/javascript">
	doSum(a1,a1sum);
	doSum(a2,a2sum);
	doSum(a3,a3sum);
	doSum(a4,a4sum);
</script>