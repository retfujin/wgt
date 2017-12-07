<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
<body>
<% 	
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
java.util.Date currentTime = new java.util.Date();//得到当前系统时间 	
String str_date1 = formatter.format(currentTime); //将日期时间格式化 

%>

<form method="post" action="assign!savehou.action" name="formadd" onsubmit="return checkform()">
<table width="80%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tableBorder1">
	<TR><th height="25" colspan="9" class="tableHeaderText">指定费用收取</th></TR>	
	<tr>
		<td height="25"><div align="right">小区：</div></td>
   	 	<td><s:select name="entity.areaId" id="areaId" list="viewList" headerKey="" headerValue="==请选择小区==" listKey="id" listValue="areaName" theme="simple" onchange="getEdifice()"></s:select></td>
	</tr>
	<tr>
		<td><div align="right">楼栋：</div></td>
    	<td><div id="edifice"><select name="entity.house.edificeId" id="edificeId" ></select></div></td>
	</tr>
	<tr>
		<td align="right">房间：</td>
  	 	<td><div id="house"><select name="entity.house.id" id="houseId"></select></div></td>
	</tr>
	<tr height="25">
		<td align="right">票据号：</td>
		<td><input type="text" name="entity.bh" id="bh"/>&nbsp;&nbsp;<font color="red">*</font></td>
	</tr>
	<tr height="25">
		<td align="right">交费金额：</td>
		<td><input type="text" name="entity.factMoney" id="factMoney"/></td>
	</tr>
	<tr height="25">
		<td align="right">交费开始/截止时间：</td>
		<td><input type="text" name="beginTime" size="8" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>&nbsp;&nbsp;--&nbsp;&nbsp;<input type="text" size="8" name="endTime"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
	</tr>
	<tr height="25">
		<td align="right">收款方式：</td>
		<td>
			<select name="entity.reason">
				<option value="现金">现金</option>
				<option value="转账">转账</option>
				<option value="托收">托收</option>
			</select>
		</td>
	</tr>
	<tr height="25">
		<td align="right">收款类型：</td>
		<td>
			<select name="entity.chargeName" id="chargeId" >
			<option value="--请选择收款类型--">--请选择收款类型--</option>
			<s:iterator value="retList" status="stuts">
			<option value="<s:property value="retList[#stuts.index]"/>"><s:property value="retList[#stuts.index]"/></option>
			</s:iterator></select>&nbsp;&nbsp;
			<a href="#" onclick="showType()"> 新增</a>
		</td>
	</tr>
	<tr height="25">
		<td>&nbsp;</td>
		<td><div id="newType" style="display: none;"><input type="text" name="chargeName" id="chargeName">&nbsp;&nbsp;<a href="#" onclick="closeType()">关闭</a>&nbsp;&nbsp;<font color="red">选择此项时,收款类型必须为'--请选择收款类型--' </font> </div></td>
	</tr>
		
	<tr>
		<td align="right">说明：</td>
		<td width="30%"><textarea rows="5" cols="40" name="entity.explain"></textarea></td>
	</tr>
	<tr height="25">
		<td align="right">收款员：</td>
		<td ><input type="text" name="entity.userName" value="<s:property value="#session.userName" />" readonly="readonly"/></td>
	</tr>
	<tr height="25">
		<td align="right">交费时间：</td>
		<td ><input type="text" name="entity.gatheringTime" value="<%=str_date1 %>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
	</tr>
	<tr>
		<td width="20%" height="30">&nbsp;</td>
		<td width="80%">
			<s:hidden name="entity.isBack" value="false"></s:hidden>
			<input type="hidden" name="entity.payType" value="收款">
			<input type="hidden" name="entity.chargeBigType" value="户收费" >
			<input type="submit" name="submit"	value="保存" class="a" >
			</td>
		<td></td>
		<td></td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
function getEdifice(){
	$("#edifice").load("assign!getEdifice.action?areaId="+$("#areaId").val());
}
function getHouse(){

	var edificeId = formadd.edificeId.value;
	$("#house").load("assign!getHouseInfo.action?edificeId="+edificeId);
}

function showType()
{
	document.getElementById('chargeId').selectedIndex='';
	document.getElementById('chargeId').disabled=true;
	newType.style.display="block";
}
function closeType(){
	document.getElementById("chargeName").value="";
	document.getElementById('chargeId').disabled=false;
	newType.style.display="none";
}
function checkform(){
	var areaId= document.getElementById('areaId').value;
	if(areaId==""){
		alert("请选择小区");
		return false;
	}
	
	var bh=document.getElementById('bh').value;
	if(bh==null || bh==""){
		alert("请输入票据号");
		document.getElementById('bh').focus();
		return false;
	}

	var chargeId=document.getElementById('chargeId').value;
	var chargeName=document.getElementById('chargeName').value;
	
	if(chargeId=='--请选择收款类型--')
	{
		if(chargeName=='')
		{
			alert("收款类型输入有误");
			return false;
		}
	}else{
		if(chargeName!=''){
			alert("收款类型输入有误，请重新输入!");
			return false;
		}
	}

	var fact_money=document.getElementById('factMoney').value;
	if(fact_money==""){
		alert("缴费金额不能为空");
		return false;
	}else if(isNaN(fact_money)){
		alert("缴费金额只能是数字");
		return false;
	}
	return true;
}
</script>
</html>