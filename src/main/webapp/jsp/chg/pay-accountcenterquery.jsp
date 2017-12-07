<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<html>
<%@ include file="/commons/meta.jsp" %>
<head>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<sd:head parseContent="true"/>
<style type="text/css">
<!--
body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,table,td,th,form,fieldset,img,dl,dt,d

d{margin:0;padding:0;}
textarea,input,select,body,table{font-size:12px;}
select,input{vertical-align:middle;}
fieldset,img{border:0;}
ul,ol{list-style:none;}

a{text-decoration: underline;color: #009;}
a:visited{color:#800080;}
a:hover{color: #f00;}



.Main{width:950px;margin:10px auto 

0;overflow:hidden;zoom:1;background:url

(http://i3.sinaimg.cn/IT/deco/2008/0515/notebook/images/nbk_mj_021.gif) 

repeat-y 100% 0;}
.Main .M_Left{width:750px;float:left;overflow:hidden;}
.Main .M_Right{width:190px;float:left;overflow:hidden;margin-

left:10px;padding-bottom:12px;background:url

(http://i2.sinaimg.cn/IT/deco/2008/0515/notebook/images/nbk_mj_006.gif) 

no-repeat 0 100%;margin-bottom:-12px;}
.Main .M_Bottom

{height:1px;overflow:hidden;clear:both;background:#ddd;width:190px;marg

in-left:760px;margin-top:-1px;}

.block_01{background:#fff;}
.block_01 .td_1{width:320px;border:1px solid #cdced3;vertical-

align:top;padding-bottom:5px;}
.block_01 .td_2{width:132px;border:1px solid #cdced3;vertical-

align:top;padding-bottom:5px;}
.block_01 .td_2 p{padding-left:12px;height:22px;}
.block_01 .td_3{border:1px solid #cdced3;vertical-align:top;}
.block_01 .SP_W10{width:15px;background:url

(http://i2.sinaimg.cn/IT/mobilecpk/images2/mobi1_mj_056.gif) repeat-y 0 

0;}
.block_01 h3{background:#ebe6f1;font-size:12px;font-

weight:normal;padding:6px 0 4px 15px;line-

height:14px;color:#009;margin-bottom:5px;}
#Layer1 {
	position:absolute;
	left:308px;
	top:41px;
	width:202px;
	height:86px;
	z-index:1;
}
#Layer4 {
	position:absolute;
	left:543px;
	top:42px;
	width:180px;
	height:127px;
	z-index:4;
}
#Layer5 {
	position:absolute;
	left:60px;
	top:41px;
	width:240px;
	height:100px;
	z-index:5;
}

-->
</style>
</head>
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

<body>

<form name="filterForm" action="pay!accountcenter.action" method="post">
<div id="Layer1">
<table width="100%" border="0">
  <tr><th colspan="2" scope="col">查询起止时间段</th></tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr><td>起始月</td><td ><input type="text" name="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/></td></tr>
  <tr><td>截止月</td><td ><input type="text" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" /></td></tr>
</table>
  <h3>&nbsp;</h3>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
<div id="Layer4">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><th colspan="2"  scope="col">收费项目</th></tr>
  <s:iterator value="viewList" status="stuts">
   <tr>
   	   <td>&nbsp;</td>
       <td> <input type="checkbox" name="chargeId" value="${id}">${chargeName}</td>
   </tr>
  </s:iterator>
</table>
</div>
<div id="Layer5">
<table width="100%" border="0">
  <tr><th colspan="2" scope="col">区域范围&nbsp;</th></tr>
     <tr><td colspan="2">管理处名称：<s:select name="areaId" list="retList" listKey="id" listValue="areaName" headerKey="" headerValue="--请选择管理处名称--" theme="simple"></s:select>&nbsp;</td></tr>
     <tr>
    	<td colspan="2">支持模糊查询&nbsp;</td>
  	</tr>
  <tr>
  <td>房间编号：</td><td><sd:autocompleter keyName="houseId" name="houseId" searchType="substring" id="houseId" list="viewList1"  forceValidOption="false" listValue="id" listKey="id"  autoComplete="true" showDownArrow="false" value="%{id}"></sd:autocompleter></td>
  </tr>
</table>
</div>
<div style="position:absolute;
	left:310px;
	top:261px;
	width:113px;
	height:66px;
	z-index:6;">
	<input type="submit" name="submit" value="确定" class="a"/>
</div>
</form>
</body>

</html>