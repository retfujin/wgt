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
			title:'历史查询',
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


<form class="search_form" name="form1" action="allmeterrecord!historyList.action" method="post" target="ifr">
	小区<s:select id="areaId" name="areaId" list="retList" listKey="id" listValue="areaName"  onchange="doRequest();" theme="simple"/>
	房间编号<input type="text" name="houseId" id="houseId" style="size: 16px"/>
	表类型<s:select name="meterType" list="#{'电表':'电表','水表':'水表','热水表':'热水表','暖气表':'暖气表'}" theme="simple" cssStyle="width:50px;" />  
	 起始月份<input type="text" name="beginMonth" id="beginMonth" size="7" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>
	截至月份<input type="text" name="endMonth" id="endMonth" size="7" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/> 
	<input type="submit" value="查询" class="search_btn" />
</form>


<iframe id="ifr" name="ifr" width="100%" height="100%" frameborder="0" src=""></iframe>
</div>
</body>
</html>
