<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<html>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function getChargeId(){
	$("#div1").load("changemoney!getchargeid.action?areaId="+$("#areaId").val());
}
</script>
<sd:head parseContent="true"/>
<style type="text/css">
/* 提示div的样式 */
#suggest {
    width:100px;
    border:1px solid black;
    width:184px;
    font-size:9pt;
    position: absolute;
}

/* 提示信息鼠标覆盖时信息 */
div.over {
    border:1px solid #999;
    background:#FFFFCC;
    width:184px;
    cursor:hand;
}

/* 提示信息鼠标移出时信息 */
div.out {
    border: 1px solid #FFFFFF;
    width:184px;
    background:#FFFFFF;
}
</style>
<script type="text/javascript" src="/js/_house_id.js"></script>
<%@ include file="/commons/meta.jsp" %>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
</head>
<body>
<div id="disSel">
<form action="changemoney!getInfo.action" method="post">
<table width="70%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tableBorder1">
	<tr height="40">
		<td align="right">选择小区：</td>
		<td>
		<div><s:select list="viewList" onchange="getChargeId()"
			theme="simple" name="areaId" headerKey="" headerValue="选择小区"
			listKey="id" listValue="areaName"></s:select></div>
		</td>
	</tr>
	<tr height="40">
		<td align="right">房间编号：</td>
		<td>
		<!--
		<sd:autocompleter keyName="houseId" name="houseId" searchType="substring" id="houseId" list="retList"  forceValidOption="false" listValue="id" listKey="id"  autoComplete="true" showDownArrow="false" value="%{id}"></sd:autocompleter>
		  -->
		  <input type="text" name="houseId" id="houseId" />
		</td>
	</tr>
	<tr height="40">
		<td align="right">收费项目：</td>
		<td>
		<div id="div1">
			<select name="chargeId">
				<option value="">=选择收费项目=</option>
			</select></div>
		</td>
	</tr>
	<tr height="40">
		<td align="right">开始日期：</td>
		<td><input name="beginTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
			readonly="readonly" /></td>
	</tr>
	<tr height="40">
		<td align="right">截止日期：</td>
		<td><input name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
			readonly="readonly" /></td>
	</tr>
	<tr height="40">
		<td align="right">缴费人姓名：</td>
		<td><input type="text" name="ownerName" id="ownerName" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" class="a" value="查询" /></td>
	</tr>
</table>
</form>
</div>

</body>
</html>