<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script src="/js/hanx.js" type="text/javascript"></script>
<body>
<% 	
	String[] menuModel={"menuModel2.addItem(203,'返回','','carport!payList.action',false);"};
%>
<%@ include file="/menubar/simple/aa.jsp" %>

<form name="car_add" method="post" action="carport!savePayMoney.action" onsubmit="return subchk()">
<table width="100%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
	<tr>
		<th height="25" colspan="13" class="tableHeaderText">车位交费列表</th>
	</tr>
	<tr align="center">
		<td class="forumRow" >房间</td>
		<td class="forumRow" >业主</td>
		<td class="forumRow" >收费月份</td>
		<td class="forumRow" >费用名称</td>
		<td class="forumRow" >应收</td>
		<td class="forumRow" >欠收</td>
		<td class="forumRow" >实收</td>
		<td class="forumRow" >操作</td>
	</tr>
  <s:iterator value="page.result" status="stuts">
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
  	<td >${houseId } </td>
   	<td >${ownerName}</td>
   	<td > <s:property value="recordMonth.toString().substring(0,7)"/> </td>
   	<td > ${otherName }</td>
   	<td id="a"> ${oughtMoney } </td>
   	<td id="b">${arrearageMoney }</td>
   	<td >
   	<input type="text" name="factMoney" onkeypress="return inputOnlyNumber()"  
   	onfocus="this.select()" value="${arrearageMoney }"> 
   	<input type="hidden" value="${chargeHouseDetailId }" name="detailId">
   	</td>
   	<td> <input type="button" value="优惠" class="a" onclick="youhui(${stuts.index},${chargeHouseDetailId},${chargeId})">  	</td>
  </tr>
  </s:iterator>
  
  <s:if test="page.totalCount>0">
    <tr>
  <td colspan="4">&nbsp;</td>
  <td align="center" id="asum"></td>
  <td align="center" id="bsum"></td>
  </tr>
    <tr>
  <td colspan="6">&nbsp;</td>
  <td align="center"><input type="submit" value="交费"  class="a" ></td>
  </tr>
  </s:if>
  <s:else>
    <tr>
  <td colspan="5">该车位没有费用要交</td>
  </tr>
  </s:else>
</table>
<s:token/>
</form>
<div id="pageBar" ></div>
<script type="text/javascript">
	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
</script>
<script type="text/javascript">
function subchk()
{
	var factMoney = document.all.factMoney;
	if(factMoney.length == undefined)
	{
		if(checkMoney(document.all.factMoney,"收款金额")==false)
			return false;
	}
	else	
	{
		for(var i=0;i<factMoney.length;i++)
		{	
			if(checkMoney(factMoney[i],"收款金额")==false)
				return false;
		}
	}
	return true;
}
	
function youhui(hangId,detailId,chargeId)
{
	var youhuiMoney = document.all.factMoney;
	alert(youhuiMoney);
	alert(detailId);
	if(youhuiMoney.length == undefined)
	{
		var aa = document.all.factMoney.value;
		alert(aa);
		window.location.href="carport!youhui.action?factMoney="+aa+"&detailId="+detailId+"&chargeId="+chargeId;
	}else	
	{
		var aa = youhuiMoney[hangId].value;
		alert(aa);
		window.location.href="carport!youhui.action?factMoney="+aa+"&detailId="+detailId+"&chargeId="+chargeId;
	}
}
function  doSum(tt,ttToal){
	if(tt.length!=undefined){
	  var   sumrow=0.0;  	
	  for(var i=0;i< tt.length;i++)
	  {
		var tmpVal= tt[i].innerHTML;
	  	if(isNaN(tmpVal)==true)
	  	  	alert("ta:"+tmpVal);
	  	sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);   
	  }
	  ttToal.innerHTML = sumrow.toFixed(2);
	}else{
		ttToal.innerHTML=tt.innerHTML;
	}  
}
doSum(a,asum);
doSum(b,bsum);

</script>
</body>	
</html>
