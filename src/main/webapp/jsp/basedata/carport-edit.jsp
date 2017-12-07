<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%
	String type = request.getParameter("type");
	String beginD = (String)request.getAttribute("atherInDate");
%>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/hanx.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					if(document.getElementById('inDate').value==""){
						alert("请设置起始时间!");
						return false;
					}
					if(document.getElementById('outDate').value==""){
						alert("请设置结束时间!");
						return false;
					}
					
					if(document.getElementById('inDate').value>document.getElementById('outDate').value)
					{
						alert("起始时间不能大于结束时间!");return false;
					}
					
					if(document.getElementById('bh').value==""){
						alert("请填写缴费单号!");
						return false;
					}					
					if(document.getElementById('recordMonth').value==""){
						alert("请填写缴费日期!");
						return false;
					}
					if(checkMoney(document.getElementById("factMoney"),"缴费金额")==false){
						return false;
					}
					if(!confirm("确定要保存吗？"))
					{
						return false;
					}		
					return true;					
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.returnValue = "success";
						window.close();
					}else{
						alert(responseText.msg);
					}
					
				}
			});
		});
</script>
</head>
<body>

 <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>车位分配</p> 
            </div>
            <form method="post" action="carport!saveather.action" name="car_add" id="ff" >  
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区</td>
                        <td><s:textfield id="areaId" name="carportLease.areaId" readonly="true" theme="simple"/></td>
                        <td height="35" align="center">楼栋</td>
                        <td><s:textfield id="edificeId" name="carportLease.edificeId" readonly="true" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">房间</td>
                        <td><s:textfield id="houseId" name="carportLease.houseId" readonly="true" theme="simple"/></td>
                        <td height="35" align="center">位置</td>
                        <td><s:textfield id="local" name="carportLease.local" readonly="true" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车位编号</td>
                        <td><s:textfield id="carCode" name="carportLease.carCode" readonly="true" theme="simple"/></td>
                        <td height="35" align="center">联系方式</td>
                        <td><s:textfield id="phone" name="carportLease.phone" readonly="true" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车主姓名</td>
                        <td><s:textfield id="ownerName" name="carportLease.ownerName" readonly="true" theme="simple"/></td>    		
                        <td height="35" align="center">车牌号码</td>
                        <td><s:textfield id="carNums" name="carportLease.carNums" readonly="true" theme="simple"/></td>	
                    </tr>
                    <tr>
                        <td height="35" align="center">卡号</td>
                        <td><s:textfield name="carportLease.cardNum" id="cardNum" readonly="true" theme="simple"/></td>	
			    	    <td height="35" align="center">车名/颜色</td>
			    		<td><s:textfield name="carportLease.carColor" id="carColor" readonly="true" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车位属性</td>
                        <td><s:textfield name="carportLease.state" id="state" readonly="true" theme="simple"/></td>
                        <td height="35" align="center">车辆类型</td>
                        <td><s:textfield name="carportLease.bigType" id="bigType" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收费项目</td>
                        <td><s:textfield name="carportLease.chargeId" id="chargeId" readonly="true" theme="simple"/></td>
                        <td height="35" align="center">缴费日期</td>
                        <td><input id="recordMonth" name="recordMonth" value="${atherInDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center" >开始日期</td>
                        <td ><input id="inDate" name="inDate" value="${atherInDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
                        <td height="35" align="center">截止日期</td>
                        <td><input id="outDate" name="outDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><input type="button" value="生成费用" class="a" onclick="setFee()"/></td>
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
                        	<s:hidden type="hidden" name="carportLease.id" />
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
