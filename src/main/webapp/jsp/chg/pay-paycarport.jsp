<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!--
	 用户缴费页面 
-->
<html>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>


<%@ include file="/commons/meta.jsp"%>

<body>
	<%
	   
				//当前日期
				java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
						"yyyy-MM-dd");
				String currentDate = df.format(new java.util.Date());
				//单据号自动生成
				//String bh = session.getAttribute("userId")+"E"+System.currentTimeMillis();
				String bh = (String) request.getAttribute("bh");
	%>

	<form action="pay!payCarport.action" method="post">
		<table align="center">
			<tr>
				<td>房间编号:<input type="text" name="houseId" size="15"
					value="<s:property value="#request.houseId"/>" />
				</td>
				<td>时间段 <input type="text" name="beginDate"
					value="${param.beginDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" />至<input
					type="text" name="endDate" value="${param.endDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM'})" />
				</td>
				<td>费用名称<s:select name="chargeId" list="viewList2" listKey="id"
						listValue="chargeName" headerKey="" headerValue="==费用名称=="
						theme="simple" />
				</td>
				<td><input type="submit" value="查找" class="a" /></td>
			</tr>
		</table>
	</form>
	<br />
	<s:if test="forwardStr!=null && !forwardStr.equals('')">

		<div align="center">
			<s:property value="forwardStr" />
		</div>

	</s:if>
	<s:else>
		<table width="95%" border="0" align="center" cellpadding="2"
			cellspacing="1" class="tableBorder1">
			<tr>
				<td colspan="12">房间编号：<s:property value="viewList[0][8]" />
					&nbsp;&nbsp;&nbsp;&nbsp;房间面积：<s:property value="viewList[0][9]" />
					&nbsp;&nbsp;&nbsp;&nbsp; 业主姓名：<s:property value="viewList[0][10]" />
					&nbsp;&nbsp;&nbsp;&nbsp;收款人：<s:property value="#session.userName" />
				</td>
			</tr>
<!-- 
			<tr>
				<td align="left">客户预存款：<input id="advanceSrc" type="hidden"
					value="${requestScope.advanceMoney}" /><input id="advanceAll"
					name="advanceAll" readonly="readonly"
					value="${requestScope.advanceMoney}" />&nbsp;</td>
			</tr>
 -->
			<tr>
				<td class="Forumrow">缴费月份</td>
				<td class="Forumrow">费用名称</td>
				<td class="Forumrow">应缴款</td>
				<td class="Forumrow">实收款</td>
<!-- 
				<td class="Forumrow">使用预存</td>
 -->
				<td class="Forumrow">优惠金额</td>
				<td class="Forumrow">备注说明</td>
			</tr>
			<s:form method="post" action="pay!payCarportSave.action" name="frmAdd"
				onsubmit="return frmOnAdd(this)" theme="simple">
				<s:hidden name="houseId" value="%{#parameters.houseId}"></s:hidden>
				<!-- <input type="hidden" id="arrearType" name="arrearType" /> -->
				<s:iterator value="viewList" status="stuts">
					<tr align="left" <s:if test="#stuts.odd==false">class="td_f"</s:if>
						<s:else>class="td_t"</s:else>>
						<s:hidden name="id" value="%{viewList[#stuts.index][7]}" />
						<td><s:property value="viewList[#stuts.index][1]" />
						</td>
						<td><s:property value="viewList[#stuts.index][2]" />
						</td>
						<td id="a" align="left"><s:property
								value="viewList[#stuts.index][3]" />
						</td>
						<td align="left"><s:textfield id="monthFactMoney"
								name="monthFactMoney" value="%{viewList[#stuts.index][3]}"
								size="6" onchange="changeMonthFactMoney()" /></td>
<!-- 
						<s:if test="#request.advanceMoney>0">
							<td align="right"><input name="advanceMoney" size="6"
								onchange="changeAdvanceMoney()" value="0" />&nbsp;</td>
						</s:if>
						<s:else>
							<td align="left">&nbsp;</td>
						</s:else>
 -->
						<td align="left"><input name="privilegeMoney" size="6"
							onchange="changeFactTotalMoney()" value="0" />&nbsp;</td>
						<td align="left"><input name="privilegeReason" size="15" />&nbsp;</td>
					</tr>
				</s:iterator>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="left"><input id="arrearMoney" name="arrearMoney"
						value="0" readonly="readonly" size="6" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="left">尾数优惠</td>
				</tr>
				<tr bgcolor="#d9d9d9">
					<td>&nbsp;</td>
					<td align="left">合计：&nbsp;</td>
					<td align="left"><input id="oughtTotalMoney"
						name="oughtTotalMoney" readonly="readonly" size="6" />&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr bgcolor="#d9d9d9">
					<td>&nbsp;</td>
					<td align="left">实收金额：&nbsp;</td>
					<td align="left"><input id="factTotalMoney"
						name="factTotalMoney" readonly="readonly" size="6" />&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th class="d" colspan="12">缴费日期<input type="text"
						name="gatheringDate" id="gatheringDate" size="10"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"
						value="<%=currentDate%>"></input> 票据号<input type="text" name="bh"
						id="bh" value="<%=bh%>"></input> 尾数优惠<input type="radio"
						name="mantissa" value="0" checked="checked"
						onclick="validateMantissa()" />无 <input type="radio"
						name="mantissa" value="1" onclick="validateMantissa()" />四舍五入 <input
						type="radio" name="mantissa" value="2"
						onclick="validateMantissa()" />尾数截取
						<div id="dispay">
							收款方式:<select name="reason1"><option value="现金">现金</option>
								<option value="转账">转账</option>
								<option value="托收">托收</option>
							</select>&nbsp;&nbsp;<input type="submit" name="sub1" value="收款" class="a" />
						</div></th>
				</tr>
			</s:form>
		</table>
		<script type="text/javascript">
			function frmOnAdd(frm){
				
				var bh=document.getElementById('bh').value;
				if(bh==null || bh==""){
					alert("请输入票据号");
					return false;
				}
				 var r = document.getElementById("factTotalMoney").value  
				 if (confirm("确定要收取"+r+"金额吗?")){   
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

			function validateMantissa()
			{
			   var New=document.getElementsByName("mantissa");			   
			   var strNew;
			   
			   var tempPrivilege = document.getElementsByName("privilegeMoney");
			   var privilegeMoney = 0;
				for (var i=0;i<tempPrivilege.length;i++){
					var tt = tempPrivilege[i].value;
					if (tt==""){
						tt =0;
					}
					if (isNaN(tt)){
						tt=0;
					}	
					privilegeMoney += parseFloat(tt); 
				}
				
				var tempAdvance = document.getElementsByName("advanceMoney");
				var advanceMoney = 0;
				for (var i=0;i<tempAdvance.length;i++){
					var tt = tempAdvance[i].value;
					if (tt==""){
						tt =0;
					}
					if (isNaN(tt)){
						tt=0;
					}
					advanceMoney += parseFloat(tt); 	
				}
				
				var tempMon = document.getElementsByName("monthFactMoney");
				var _monthFactMoney = 0;
				for (var k=0;k<tempMon.length;k++){
					var kk = tempMon[k].value;
					if (kk==""){
						kk =0;
					}
					if (isNaN(kk)){
						kk=0;
					}
					_monthFactMoney+= parseFloat(kk);
				}
				
			   for(var i=0;i<New.length;i++)
			   {
			     if(New.item(i).checked){
			         strNew=New.item(i).getAttribute("value"); 
			         var  tmpVal = _monthFactMoney - privilegeMoney - advanceMoney;
			         var sumrow = isNaN(tmpVal) ? 0 : parseFloat(tmpVal);
			         if(strNew=='0'){
			        	 document.getElementById("factTotalMoney").value=sumrow;
			        	// document.getElementById("arrearType").value="0";
			         }else if(strNew=='1'){
			        	 document.getElementById("factTotalMoney").value=sumrow.toFixed(0);
			        	 var tempArrear = sumrow.toFixed(0)-sumrow;
			        	 tempArrear = Math.round(parseFloat(tempArrear) * 100) / 100;
			        	 document.getElementById("arrearMoney").value=tempArrear;
			        	// document.getElementById("arrearType").value="1";
			         }else if(strNew=='2'){
			        	 document.getElementById("factTotalMoney").value=Math.floor(sumrow);
			        	 var tempArrear = Math.floor(sumrow)-sumrow
			        	 tempArrear = Math.round(parseFloat(tempArrear) * 100) / 100;
			        	 document.getElementById("arrearMoney").value=tempArrear;
			        	// document.getElementById("arrearType").value="2";
			         }
			     }
			  }
			}
			
			function changeMonthFactMoney(){	
				var tempArr = document.getElementById("arrearMoney").value;

				var tempAdvance = document.getElementsByName("advanceMoney");				
				var advanceMoney = 0;
				for (var i=0;i<tempAdvance.length;i++){
					var ii = tempAdvance[i].value;
					if (ii==""){
						ii =0;
					}
					if (isNaN(ii)){
						ii=0;
					}
					advanceMoney += parseFloat(ii); 	
				}
				
				var tempPri = document.getElementsByName("privilegeMoney");
				var privilegeMoney = 0;
				for (var j=0;j<tempPri.length;j++){
					var jj = tempPri[j].value;
					if (jj==""){
						jj =0;
					}
					if (isNaN(jj)){
						jj=0;
					}					
					privilegeMoney += parseFloat(jj); 
				}
				
				var tempMon = document.getElementsByName("monthFactMoney");
				var tempTt = document.all.a;
				var _monthFactMoney = 0;
				for (var k=0;k<tempMon.length;k++){
					var kk = tempMon[k].value;
					if (kk==""){
						kk =0;
					}
					if (isNaN(kk)){
						kk=0;
					}
					var tempOug = 0;
					if (tempTt[k]==undefined)
						tempOug = tempTt.innerHTML;
					else
						tempOug = tempTt[k].innerHTML;
					if (kk>parseFloat(tempOug)){						
						tempMon[k].value = tempOug;
						alert("当月实收金额不能大于应缴款！");
					}
					_monthFactMoney += parseFloat(kk);
				} 
				
				var factTotalMoney = _monthFactMoney - privilegeMoney - advanceMoney + parseFloat(tempArr);	
				document.getElementById("factTotalMoney").value=factTotalMoney;
			}
			
			function changeFactTotalMoney(){							
				var tempAdvance = document.getElementsByName("advanceMoney");
				var tempArr = document.getElementById("arrearMoney").value;
				var advanceMoney = 0;
				for (var i=0;i<tempAdvance.length;i++){
					var ii = tempAdvance[i].value;
					if (ii==""){
						ii =0;
					}
					if (isNaN(ii)){
						ii=0;
					}
					advanceMoney += parseFloat(ii); 	
				}
				
				var tempVar = document.getElementsByName("privilegeMoney");
				var tempTt = document.all.a;
				var privilegeMoney = 0;
				for (var j=0;j<tempVar.length;j++){
					var jj = tempVar[j].value;
					if (jj==""){
						jj =0;
					}
					if (isNaN(jj)){
						jj=0;
					}
					var tempOug = 0;
					if (tempTt[j]==undefined)
						tempOug = tempTt.innerHTML;
					else
						tempOug = tempTt[j].innerHTML;
					if (jj>parseFloat(tempOug)){
						tempVar[j].value = 0;
						alert("当月优惠金额不能大于应缴款！");
					}
					
					privilegeMoney += parseFloat(jj); 
				}
				
				var tempMon = document.getElementsByName("monthFactMoney");
				var _monthFactMoney = 0;
				for (var k=0;k<tempMon.length;k++){
					var kk = tempMon[k].value;
					if (kk==""){
						kk =0;
					}
					if (isNaN(kk)){
						kk=0;
					}
					_monthFactMoney+= parseFloat(kk);
				} 
				
				var factTotalMoney = _monthFactMoney - privilegeMoney - advanceMoney + parseFloat(tempArr);	
				document.getElementById("factTotalMoney").value=factTotalMoney;
			}
			
			function changeAdvanceMoney(){
				var tempPrivilege = document.getElementsByName("privilegeMoney");
				var tempArr = document.getElementById("arrearMoney").value;
				var privilegeMoney = 0;
				for (var i=0;i<tempPrivilege.length;i++){
					var ii = tempPrivilege[i].value;
					if (ii==""){
						ii =0;
					}
					if (isNaN(ii)){
						ii=0;
					}	
					privilegeMoney += parseFloat(ii); 
				}
				
				var tempVar = document.getElementsByName("advanceMoney");
				var tempTt = document.all.a;
				var advanceAll = document.getElementsByName("advanceAll");
				var advanceMoney = 0;
				for (var j=0;j<tempVar.length;j++){
					var jj = tempVar[j].value;
					if (jj==""){
						jj =0;
					}
					if (isNaN(jj)){
						jj=0;
					}
				
					var tempOug = 0;
					if (tempTt[j]==undefined)
						tempOug = tempTt.innerHTML;
					else
						tempOug = tempTt[j].innerHTML;
					if (jj>parseFloat(tempOug)){
						tempVar[j].value=0;
						alert("使用预存金额不能大于应缴款！");
					}else if (jj>parseFloat(advanceAll.value)){
						tempVar[j].value=0;
						alert("使用预存金额不能大于预存总额！");
					}else{
						advanceMoney += parseFloat(jj); 
					}		
				}
				
				var tempMon = document.getElementsByName("monthFactMoney");
				var _monthFactMoney = 0;
				for (var k=0;k<tempMon.length;k++){
					var kk = tempMon[k].value;
					if (kk==""){
						kk =0;
					}
					if (isNaN(kk)){
						kk=0;
					}
					_monthFactMoney+= parseFloat(kk);
				}
				
				var factTotalMoney = _monthFactMoney - advanceMoney - privilegeMoney + parseFloat(tempArr);	
				document.getElementById("factTotalMoney").value=factTotalMoney;
				document.getElementById("advanceAll").value=document.getElementById("advanceSrc").value - advanceMoney;
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
				  ttToal.value = sumrow.toFixed(2);
				}else{
					ttToal.value=tt.innerHTML;
				}  
			}
			doSum(a,document.getElementById('oughtTotalMoney'));
			document.getElementById("factTotalMoney").value=document.getElementById("oughtTotalMoney").value;
		</script>
	</s:else>

</body>
</html>