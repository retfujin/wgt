<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head> 
</head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../js/func.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(document.getElementById('recordMonth').value=='')
					{
						alert("请选择日期");
						document.getElementById('recordMonth').focus();
						return false;
					}
					if(document.getElementById('acceptedDate').value=='')
					{
						alert("请选择受理时间");
						document.getElementById('acceptedDate').focus();
						return false;
					}
					if(document.getElementById('content').value=='')
					{
						alert("请输入客户姓名、联系电话或地址");
						document.getElementById('content').focus();
						return false;
					}
					if(document.getElementById('repairContext').value=='')
					{
						alert("请输入请修内容");
						document.getElementById('repairContext').focus();
						return false;
					}
					if(document.getElementById('repairResult').value=='')
					{
						alert("请输入维修结果");
						document.getElementById('repairResult').focus();
						return false;
					}
					if(document.getElementById('visitResult').value!='')
					{
			
						if(document.getElementById('reciprocal').value==''){
							alert("请输入回访日期");
							document.getElementById('reciprocal').focus();
							return false;
						}
						
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
<body>

<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>客户请修登记表</p> 
            </div>
            <form id="ff" method="post" action="repair!save2.action" name="formadd" >
                <s:hidden id="areaId" name="customerrepair.areaId"></s:hidden>
				<s:hidden id="areaName" name="customerrepair.areaName"></s:hidden>
				<s:hidden id="houseId" name="customerrepair.houseId"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
					  	<td height="35" align="center">小区</td>
					  	<td><s:property value="customerrepair.areaName"/></td>
						<td height="35" align="center">房间编号</td>
					  	<td ><s:property value="customerrepair.houseId" /></td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">填表日期</td>
					  	<td><s:textfield name="customerrepair.recordMonthString" id="recordMonth" readonly="true"  size="20" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  	<td height="35" align="center">请修地址：</td>
					  	<td ><s:textfield name="customerrepair.comaddress"  cssClass="con_input_con" theme="simple"/><font color="red">非公共区域可不填写</font> </td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">受理日期</td>
					  	<td colspan="3"><s:textfield name="customerrepair.acceptedDate" id="acceptedDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true" size="20" cssClass="con_input_con" theme="simple"/>
					
					  	<font color="red">*</font>
					  	</td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">客户姓名、联系电话及地址</td>
					  	<td colspan="3"><s:textarea rows="2" cols="50" name="customerrepair.content" id="content" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">请修内容</td>
					  	<td colspan="3"><s:textarea name="customerrepair.repairContext" id="repairContext" rows="2" cols="50" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">预约时间</td>
					  	<td colspan="3"><s:textfield name="customerrepair.reserveDate" id="reserveDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20" cssClass="con_input_con" theme="simple"/>
					  	</td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">派工单号</td>
					  	<td colspan="3"><s:textfield name="customerrepair.dispatcherNum" id="dispatcherNum" size="30" cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	<td height="35" align="center">维修人员</td>
					  	<td colspan="3"><s:textfield name="customerrepair.wxname" id="wxname" size="30" cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">维修过程</td>
					  	<td colspan="3"><s:textarea name="customerrepair.repairprocess" id="repairprocess" readonly="true" rows="2" cols="50" cssClass="con_input_con" theme="simple"/>可不填写</td>
					  </tr>
					 <tr>
					  	<td height="35" align="center">完成时间</td>
					  	<td colspan="3"><s:textfield name="customerrepair.achieveDate" id="achieveDate" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="20" cssClass="con_input_con" theme="simple"/>
					  	</td>
					  </tr>
					   <tr>
					  	<td height="35" align="center">维修状态</td>
					  	<td ><s:radio name="customerrepair.repairStatus"  list="#{'未维修':'未维修','已维修':'已维修'}" cssClass="con_input_con" theme="simple"/></td>
					  	<td height="35" align="center">服务状态</td>
					  	<td ><s:radio name="customerrepair.paidService" list="#{'有偿服务':'有偿服务','无偿服务':'无偿服务'}" cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	<td height="35" align="center">维修结果</td>
					  	<td colspan="3"><s:textarea name="customerrepair.repairResult" id="repairResult" rows="2" cols="50" cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	<td height="35" align="center">回访时间</td>
					  	<td colspan="3"><s:textfield name="customerrepair.reciprocal" id="reciprocal" cssClass="con_input_con" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" theme="simple"/></td>
					  </tr>
					  <tr>
					  	<td height="35" align="center">回访结果</td>
					  	<td colspan="3"><s:textarea name="customerrepair.visitResult" id="visitResult" rows="2" cols="50" cssClass="con_input_con" theme="simple"/><br><font color="red">回访时间和回访结果填入后,此请修登记表登记结束！</font> </td>
					  </tr>
					  <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        <s:hidden name="customerrepair.id"></s:hidden>
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
</html>
