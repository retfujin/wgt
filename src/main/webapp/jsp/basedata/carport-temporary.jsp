<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/hanx.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>

<script type="text/javascript">
function getEdifice(){
	$("#edifice").load("carport!getEdifice.action?areaId="+$("#areaId").val());
	$("#databaseId").load("carport!getCarBasedate.action?areaId="+$("#areaId").val());
}
function getHouse(){
	var edificeId = car_add.edificeId.value;
	$("#house").load("carport!getHouseInfo.action?edificeId="+edificeId);
}
</script>
<script type="text/javascript">
function checkcar(){
	if(document.getElementById('areaId').value=="-1")
	{
		alert("请选择小区!");
		return false;
	}
	if(document.getElementById('houseId').value=="")
	{
		alert("请选择房间!");
		return false;	
	}
	
	if(document.getElementById('chargeId').value==null || document.getElementById('chargeId').value=='' ){
		alert("请在基础资料-收费项目设置中添加关于车位的收费项目!");
		return false;
	}
	if(document.getElementById('recordMonth').value==""){
		alert("请填写缴费日期!");
		return false;
	}
	if(document.getElementById('inDate').value=="")
	{
		alert("请设置起始时间!");
	}
	if(document.getElementById('outDate').value=="")
	{
		alert("请设置结束时间!");	
	}
	if(document.getElementById('bh').value==""){
		alert("请填写缴费单号!");
		return false;
	}
	if(checkMoney(document.getElementById("factMoney"),"缴费金额")==false){
		return false;
	}
	if(parseFloat(document.getElementById("factMoney").value)<=0){
		alert("缴费金额不能小于0");
		return false;
	}
	if(!confirm("确定要保存吗？"))
	{
		return false;
	}
	return true;
}
</script>
</head>

<body>



 <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>临时车位租赁</p> 
            </div>
            <form method="post" action="carport!saveTeamporary.action" name="car_add" onsubmit="return checkcar();">  
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区<font color="red">*</font></td>
                        <td><s:select name="carportLease.areaId" id="areaId" list="viewList" headerKey="-1" headerValue="==请选择小区==" listKey="id" listValue="areaName" theme="simple" onchange="getEdifice()"></s:select></td>
                        <td height="35" align="center">楼栋<font color="red">*</font></td>
                        <td><div id="edifice"><select name="carportLease.edificeId" id="edificeId" ></select></div></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">房间</td>
                        <td><div id="house"><select name="carportLease.houseId" id="houseId"></select></div></td>
                        <td height="35" align="center">位置</td>
                        <td><input type="text" id="local" name="carportLease.local" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车位编号</td>
                        <td><input type="text" name="carportLease.carCode" value="临时车位" readonly="readonly" id="carCode"/></td>
                        <td height="35" align="center">联系方式</td>
                        <td><s:textfield name="carportLease.phone" id="phone" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车主姓名</td>
                        <td><s:textfield name="carportLease.ownerName" id="ownerName" /></td>
                        <td height="35" align="center">车牌号码</td>
                        <td><input type="text" name="carportLease.carNums" id="carNums"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">卡号</td>
                        <td><input type="text" name="carportLease.cardNum" id="cardNum"/></td>
                        <td height="35" align="center">车名/颜色</td>
                        <td><input type="text" name="carportLease.carColor" id="carColor"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车位属性</td>
                        <td><input type="text" name="carportLease.state" id="state" value="出租" readonly="readonly"/></td>
                        <td height="35" align="center">车辆类型</td>
                        <td><s:select name="carportLease.bigType" id="bigType" list="#{'机动车':'机动车','非机动车':'非机动车' }" theme="simple"></s:select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收费项目</td>
                        <td><div id="databaseId"><select name="carportLease.chargeId" id="chargeId"><option value="">-请选择收费项目-</option> </select></div></td>
                        <td height="35" align="center">缴费日期</td>
                        <td><s:textfield id="recordMonth" name="carportLease.recordMonth" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center" >开始日期</td>
                        <td ><s:textfield id="inDate" name="carportLease.inDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
                        <td height="35" align="center">截止日期</td>
                        <td><s:textfield id="outDate" name="carportLease.outDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />*
                        <input type="button" value="生成费用" class="a" onclick="setFee()"/></td>
                    </tr>
                     <tr>
                        <td height="35" align="center" >票据号</td>
                        <td ><input id="bh" name="bh" /></td>
                        <td height="35" align="center">缴费金额</td>
                        <td><input id="factMoney" name="factMoney" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缴费金额填写0的话，则表示不收取费用
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        	<input type="hidden" name="carportLease.carport.id" value="${carport.id}" />
   							<input type="hidden" name="mianji" value="${carport.mianji}" />
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!----add结束---> 
 
</body>
<script type="text/javascript">
function send(){
	$.ajax({
		url: "carport!getHouseDetail.action",
		type: "GET",
		data: {houseId:document.all.houseId.value},
		dataType: "json",
		timeout: 1000,
		beforeSend: function(XMLHttpRequest){
			document.all.ownerName.value="正在查找...";
			document.all.phone.value="正在查找...";
			
		},
		error: function(){
			alert("没有找到该房间");
			document.all.ownerName.value="";
			document.all.phone.value="";
		},
		success: function(data){
			document.all.ownerName.value=data.ownerName;
			document.all.phone.value=data.mobTel;			
		}
		});
}
function setFee(){
	if(document.getElementById('inDate').value=="")
	{
		alert("请设置起始时间!");
	}
	else if(document.getElementById('outDate').value=="")
	{
		alert("请设置结束时间!");	
	}
	else if(document.getElementById('inDate').value>document.getElementById('outDate').value)
	{
		alert("起始时间不能大于结束时间!");	
	}
	else if(document.getElementById('chargeId').value==null || document.getElementById('chargeId').value=='' )
	{
		alert("未确定收费项目!");
	}
	else
	{
	$.ajax({
		url: "carport!getFee.action",
		type: "GET",
		data: {inDate:document.all.inDate.value,
				outDate:document.all.outDate.value,
				chargeId:document.all.chargeId.value},
		dataType: "json",
		timeout: 1000,
		beforeSend: function(XMLHttpRequest){
			document.all.factMoney.value="正在生成...";
			
		},
		error: function(){
			alert("费用生成失败");
			document.all.factMoney.value="";
		},
		success: function(data){
			document.all.factMoney.value=data.factMoney;			
		}
		});
	}
}
</script>
</html>
