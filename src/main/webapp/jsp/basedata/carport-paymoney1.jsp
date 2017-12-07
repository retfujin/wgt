<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/hanx.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">

function getOug(local)
{
	var inDate = car_add.inDate.value;
	var outDate = car_add.outDate.value;
	if(inDate=="" || outDate=="")
	{	
		alert("请选择开始、截止日期");
		return false;
	}
	
	var areaId = car_add.areaId.value;
	if(local =="地下")
	{
		//地下管理费
		$("#div1").load("carport!getOughtMoney.action?type=1&chargeId="+areaId+"110202&endTime="+$("#outDate").val()+"&beginTime="+$("#inDate").val());
		//地下租赁费
//		$("#div2").load("carport!getOughtMoney?type=2&chargeId="+areaId+"310201&endTime="+$("#outDate").val()+"&beginTime="+$("#inDate").val());
	}
	else
	{
		//地面管理费
		$("#div1").load("carport!getOughtMoney.action?type=1&chargeId="+areaId+"110203&endTime="+$("#outDate").val()+"&beginTime="+$("#inDate").val());
	}
}
</script>
</head>
<body>
<% 	
String[] menuModel={"menuModel2.addItem(203,'返回','','',false,'','history.back()');"
};
%>
<%@ include file="/menubar/simple/aa.jsp" %>
  <table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" class="tableBorder1">
	<TR><TH height="25" align="center">机动车交费</TH></TR>
</table>
<form method="post" action="carport!savePayMoney1.action" name="car_add" onsubmit="return checkcar()">  
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0" class="tablegray">
    <tr>
     	<td width="100" align="right">房间：</td>
  	 	<td>
  	 	${carportLease.houseId}
  	 	</td>
    	<td height="40"  align="right">业主：</td>
    	<td>${carportLease.ownerName}</td>
    </tr>

    <tr>
     	<td width="100" align="right">车牌号码：</td>
  	 	<td>
  	 	${carportLease.carNums}
  	 	</td>
    	<td height="40"  align="right">车位编号：</td>
    	<td>${carportLease.carCode}</td>
    </tr>
    
    <tr>
     	<td width="100" align="right">车位编号：</td>
  	 	<td>
  	 	${carportLease.carCode}
  	 	</td>
    	<td height="40"  align="right">位置：</td>
    	<td>${carportLease.local}</td>
    </tr>
    <tr>
     	<td width="100" align="right">交费开始日期：</td>
  	 	<td>
  	 	<input type="text" readonly="readonly" id="inDate" name="inDate" value="<s:property value="carportLease.inDate.toString().substring(0,10)"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
  	 	</td>
    	<td height="40"  align="right">交费截止日期：</td>
    	<td><input type="text" readonly="readonly" id="outDate" name="outDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
    </tr>
    <tr>
     	<td width="100" align="right">应交管理费用：</td>
  	 	<td>
  	 	<div id="div1"></div>
  	 	</td>
    	<td height="40"  align="right">实际交费：</td>
    	<td><input type="text" name="factMoney1" onkeypress="return inputOnlyNumber()"></td>
    </tr>
<!-- 
    <s:if test="carportLease.local.equals('地下')">
    <tr>
     	<td width="100" align="right">应交租赁费用：</td>
  	 	<td>
  	 	<div id="div2"></div>
  	 	</td>
    	<td height="40"  align="right">实际交费：</td>
    	<td><input type="text" name="factMoney2" onkeypress="return inputOnlyNumber()"></td>
    </tr>
    </s:if>
 -->    

    
    <tr>
    <td align="right">
    
    </td>
    </tr>
    <tr>
   		<td width="100" height="30">
   			<input type="hidden" name="carportLease.local" value="${carportLease.local}" />
   			<input type="hidden" id="carportLeaseId" name="carportLease.id" value="${carportLease.id}" />
   			<input type="hidden" name="areaId" value="${carportLease.areaId}" />
   		</td>
   		<td>
   		<input type="submit" value="保存" class="a"/>
   		</td><td></td><td align="right">
   		<input type="button" value="计算应交" onclick="return getOug('${carportLease.local}')"/>
   		</td>
    </tr>
    </table>
  </form>
</body>


</html>
