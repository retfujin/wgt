<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--
	 用户缴费页面 
-->
<html>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>


<%@ include file="/commons/meta.jsp" %>

<body>
<% 
	String[] menuModel={""
	};
	//当前日期
	java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd") ;
	String currentDate = df.format(new java.util.Date());
	//单据号自动生成
	//String bh = session.getAttribute("userId")+"E"+System.currentTimeMillis();
	String bh = (String)request.getAttribute("bh");
%>
<%@ include file="/menubar/simple/aa.jsp" %>
<form action="pay!pay.action?payType=privilege" method="post">
<table align="center">
<tr>
<td>房间编号:<input type="text" name="houseId" size="15" value="<s:property value="#request.houseId"/>"/></td>
<td>时间段 <input type="text" name="beginDate" value="${param.beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>至<input type="text" name="endDate" value="${param.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" /></td>
<td>费用名称<s:select name="chargeId" list="viewList2" listKey="id" listValue="chargeName" headerKey="" headerValue="==费用名称==" theme="simple"/></td>
<td><input type="submit" value="查找" class="a"/>
</td></tr>
</table>
</form>
<br/>
<s:if test="forwardStr!=null && !forwardStr.equals('')">

<div align="center"><s:property value="forwardStr"/></div>

</s:if>
<s:else>
<table width="95%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
    <tr>
    <td colspan="9">房间编号：<s:property value="viewList[0][8]"/>
    	&nbsp;&nbsp;&nbsp;&nbsp;房间面积：<s:property value="viewList[0][9]"/>
   		&nbsp;&nbsp;&nbsp;&nbsp; 业主姓名：<s:property value="viewList[0][10]"/>
     	&nbsp;&nbsp;&nbsp;&nbsp;收款人：<s:property value="#session.userName"/>
     </td>
    </tr>
    <tr>
      <td class="Forumrow" >缴费月份</td>
      <td class="Forumrow" >费用名称</td> 
      <td class="Forumrow" >应缴款</td>
    </tr>
    <s:form method="post" action="pay!paySave.action" name="frmAdd" onsubmit="return frmOnAdd(this)"  target="_blank" theme="simple">
  	<s:hidden name="houseId" value="%{#parameters.houseId}"></s:hidden>
  		
  	<s:iterator value="viewList" status="stuts">
  	<tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
  		<s:hidden name="id" value="%{viewList[#stuts.index][7]}" />
  		<td><s:property value="viewList[#stuts.index][1]"/></td>
  		<td ><s:property value="viewList[#stuts.index][2]"/></td>
  		<td id="a" align="right"><s:property value="viewList[#stuts.index][3]"/></td>
		</tr>
	</s:iterator>
	
	<tr bgcolor="#d9d9d9">
  		<td >&nbsp;</td>
  		<td align="right">合计：&nbsp;</td>
		<td align="right"><input id="oughtTotalMoney" name="oughtTotalMoney" readonly="readonly" size="6"/>&nbsp;</td>
	</tr>
	<tr bgcolor="#d9d9d9">
  		<td >&nbsp;</td>
  		<td align="right">优惠金额：&nbsp;</td>
		<td align="right"><input id="factTotalMoney" name="factTotalMoney" readonly="readonly" size="6"/>&nbsp;</td>
	</tr>
	<tr>
	  <th class="d" colspan="10">
	 	 记录日期<input type="text" name="gatheringDate" id="gatheringDate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="<%=currentDate%>"></input>
		票据号<input type="text" name="bh" id="bh" value="<%=bh%>"></input>
	
	  <div id="dispay">优惠方式:<select name="reason1"><option value="物业公司优惠">物业公司优惠</option><option value="房产公司优惠">房产公司优惠</option>&nbsp;&nbsp;<input type="submit" name="sub2" value="优惠"class="a"/></div>
	 </th>
	</tr>
	</s:form>
	</table>
 </s:else>
    
</body>
<script type="text/javascript">
function frmOnAdd(frm){
	
	var bh=document.getElementById('bh').value;
	if(bh==null || bh==""){
		alert("请输入票据号");
		return false;
	}
	 var r = document.getElementById("factTotalMoney").value  
	 if (confirm("确定要优惠"+r+"金额吗?")){   
		return true;
	}else{   
		return false;
	}
}

function doSubmit(houseId,xh,chargeId){
	var bh = document.getElementById('bh').value;
	frmAdd.action="pay!deductAdance.action?xh="+xh+"&bh="+bh+"&chargeId="+chargeId;
	frmAdd.submit();
}
</script>
<script type="text/javascript">
function validateMantissa()
{
    var New=document.getElementsByName("mantissa");
   var strNew;
   for(var i=0;i<New.length;i++)
   {
     if(New.item(i).checked){
         strNew=New.item(i).getAttribute("value"); 
         var  tmpVal = document.getElementById("oughtTotalMoney").value;
         var sumrow = isNaN(tmpVal) ? 0 : parseFloat(tmpVal);
         if(strNew=='0'){
        	 document.getElementById("factTotalMoney").value=sumrow;
         }else if(strNew=='1'){
        	 document.getElementById("factTotalMoney").value=sumrow.toFixed(0);
         }else if(strNew=='2'){
        	 document.getElementById("factTotalMoney").value=Math.floor(sumrow);
         }
     }
  
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
	  ttToal.value = sumrow.toFixed(1);
	}else{
		ttToal.value=tt.innerHTML;
	}  
}
doSum(a,document.getElementById('oughtTotalMoney'));
document.getElementById("factTotalMoney").value=document.getElementById("oughtTotalMoney").value;
</script>
</html>