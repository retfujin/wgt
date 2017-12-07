<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<html>
<%@ include file="/commons/meta.jsp"  %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/hanx.js"></script>
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
<sd:head parseContent="true"/>
<body>
<% 	
	java.util.Date d = new java.util.Date();
	java.text.SimpleDateFormat sm = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String today = sm.format(d);
	
	//单据号自动生成
	//	String bh = session.getAttribute("userId")+"E"+System.currentTimeMillis();
	String bh = (String)request.getAttribute("bh");
	
	//增加菜单
	String[] menuModel={"menuModel2.addItem(203,'列表','','advance!advancelist.action',false);"
			            +"menuModel2.addItem(204,'新增','','advance!advanceInput.action',false);"
	};
%>
<%@ include file="/menubar/simple/aa.jsp"%>

<form method="post" action="advance!save.action" name="formadd" onsubmit="return checkadvance();">
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
	<TR>
		<th height="25" colspan="9" class="tableHeaderText">客户预交设置</th>
	</TR>
	<tr height="30" >
		<td><div align="center">项目分类：</div></td>
		<td><input type="radio" name="type1" value="1" checked="checked"/>预交收费项目</td>
	</tr>
	<tr height="30" >
		<td><div align="center">收费类型：</div></td>
		<td>
		<input type="radio" name="bigType" value="预交" checked="checked" />客户预交
		</td>
	</tr>
	
	<tr  height="30" >
		<td><div align="center">管&nbsp;&nbsp;理&nbsp;&nbsp;处：</div></td>
		<td><s:select name="chargeAdvance.areaId" id="areaId"
			list="viewList" headerKey="-1" headerValue="==请选择小区==" listKey="id"
			listValue="areaName" theme="simple" onblur="getChargeId()"></s:select></td>
		<td><font color="red">*</font></td>
	</tr>

	<tr  height="30" >
		<td align="center">房间编号：</td>
		<td><sd:autocompleter keyName="houseId" name="chargeAdvance.houseId" searchType="substring" id="houseId" list="retList"  forceValidOption="false" listValue="id" listKey="id"  autoComplete="true" showDownArrow="false" value="%{id}"></sd:autocompleter></td>
		<td><font color="red">*</font></td>
	</tr>
	
	<tr>
		<td width="20%" height="25" align="center">交费时间：</td>
		<td><input type="text" name="chargeAdvance.recordTime" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<%=today %>"/></td>
		<td><font color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25" align="center">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
		<td><input type="text" id="antimoney" onkeypress="return inputOnlyNumber()" name="chargeAdvance.antimoney" /></td>
		<td><font color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25" align="center">票&nbsp;&nbsp;据&nbsp;&nbsp;号：</td>
		<td><input type="text" id="bh" name="chargeAdvance.bh" /></td>
		<td><font color="red">*</font></td>
	</tr>
	<tr>
		<td width="20%" height="25" align="center">说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明：</td>
		<td><textarea name="chargeAdvance.remark"
			id="textarea" cols="23" rows="5"></textarea></td>
	</tr>
	<tr>
		<td width="20%" height="30">&nbsp;</td>
		<td colspan="2">
		<input	type="submit" value="保存" class="a"> 
		<input	type="reset" value="重填" class="a"></td>
	</tr>
</table>
</form>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function getChargeId(){

	$("#chargeId").load("advance!getChargeId.action?areaId="+$("#areaId").val());
//	if(document.all.type1[0].checked == true)
//		chargeId.style.display="block";
//	else
//		chargeId.style.display="none";
	
	//取收费单据号
//	String rand1 = Math.round(Math.random()*10000);
	
	 $.get("advance!ajaxBh.action",{areaId:$("#areaId").val()}, function(result){
		//	alert(result);
		 $("#bh").val(result);
 	 });

}
function checkadvance()
{
	if($("#areaId").val()=='-1'){
		alert("小区必须选择");
		document.getElementById('areaId').focus();
		return false;
	}
	if($("#houseId").val()==''){
		alert("请输入房间编号！");
		document.getElementById('houseId').focus();
		return false;
	}
	
	if($("#bh").val()==''){
		alert("请输入票据号！");
		document.getElementById('bh').focus();
		return false;
	}
	
	if(document.getElementById('antimoney').value=='')
	{
		alert("交费金额不能为空!");
		document.getElementById('antimoney').focus();
		return false;
	}
	else
	{
		if(isNaN(document.getElementById('antimoney').value))
		{
			alert("交费金额必须为数字");
			document.getElementById('antimoney').select();
			return false;
		}
	}
	return true;
}
</script>
</body>
</html>