<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					var bh=document.getElementById('bh').value;
					if(bh==null || bh==""){
						alert("请输入票据号");
						document.getElementById('bh').focus();
						return false;
					}

					
					var fact_money=document.getElementById('factMoney').value;
					if(fact_money==""){
						alert("缴费金额不能为空");
						return false;
					}else if(isNaN(fact_money)){
						alert("缴费金额只能是数字");
						return false;
					}
					
					var v_gatheringTime=document.getElementById('gatheringTime').value;
					if(v_gatheringTime==""){
						alert("请输入缴费日期");
						document.getElementById('gatheringTime').focus();
						return false;
					}
					
					var pay_name= document.getElementById('payName').value;
					if(pay_name==""){
						alert("交款人不能为空");
						document.getElementById('payName').focus();
						return false;
					}
					var chargeId=document.getElementById('chargeId').value;
					var chargeName=document.getElementById('chargeName').value;
					
					if(chargeId=='')
					{
						if(chargeName=='')
						{
							alert("收款类型输入有误");
							return false;
						}
					}
					
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						$("input[type=text]").val(""); 
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
<body>
<% 	
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间 	
	String str_date1 = formatter.format(currentTime); //将日期时间格式化 
	
%>

<form id="ff" method="post" action="assign!savearea.action" name="formadd">
<table width="80%" border="0" cellspacing="0" cellpadding="0"
	align="center" class="tableBorder1">
	<TR>
		<th height="25" colspan="9" class="tableHeaderText">指定费用收取</th>
	</TR>	
	<tr height="25">
		<td align="right">小区：</td>
		<td><s:select name="entity.areaId"  id="areaId" list="viewList"  listKey="id" listValue="areaName" theme="simple"/></td>
	</tr>
	<tr height="25">
		<td align="right">票据号：</td>
		<td>
			<input type="text" name="entity.bh" id="bh"/>&nbsp;&nbsp;<font color="red">*</font>
		</td>
	</tr>
	<tr height="25">
		<td align="right">缴费金额：</td>
		<td>
			<input type="text" name="entity.factMoney" id="factMoney"/>
		</td>
	</tr>
	<tr height="25">
		<td align="right">应缴费日期：</td>
		<td>
			<input type="text" name="recordMonth" id="recordMonth" size="10" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
		</td>
	</tr>
	<tr height="25">
		<td align="right">交款人：</td>
		<td>
			<input type="text" name="entity.payName" id="payName"/>
		</td>
	</tr>
	<tr height="25">
		<td align="right">收款名称：</td>
		<td>
			<select name="entity.chargeName" id="chargeId" >
			<option value="">--请选择收款名称--</option>
			<s:iterator value="retList" status="stuts">
			<option value="<s:property value="retList[#stuts.index]"/>"><s:property value="retList[#stuts.index]"/></option>
			</s:iterator></select>&nbsp;&nbsp;<a href="#" onclick="showType()">新增</a>
		</td>
	</tr>
	<tr height="25">
		<td>&nbsp;</td>
		<td><div id="newType" style="display: none;">
		<input type="text" name="chargeName" id="chargeName">&nbsp;&nbsp;<a href="#" onclick="closeType()">关闭</a>&nbsp;&nbsp;<font color="red">选择此项时,收款类型必须为'--请选择收款类型--' </font> </div></td>
	</tr>
	<tr height="25">
		<td align="right">缴费日期：</td>
		<td >
			<input type="text" id="gatheringTime" name="entity.gatheringTime" value="<%=str_date1 %>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
		</td>
	</tr>
	<tr>
		<td align="right">说明：</td>
		<td width="30%"><textarea rows="5" cols="40" name="entity.reason"></textarea></td>
	</tr>
	<tr>
		<td width="20%" height="30">&nbsp;</td>
		<td width="80%">
			<s:hidden name="entity.isBack" value="false"></s:hidden>
			<input type="hidden" name="entity.payType" value="收款">
			<input type="submit" name="submit11" id="submit11" value="保存" class="a" >
			</td>
	</tr>
	<tr>
		<td colspan="2" ><font color="blue">说明：如果应缴费日期为空，默认和缴费日期相同</font></td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
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

</script>
</html>