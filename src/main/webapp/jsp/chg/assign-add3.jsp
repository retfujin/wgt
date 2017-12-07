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
					var houseId= document.getElementById('houseId').value;
					if(houseId==""){
						alert("请选择房间");
						return false;
					}
					
					var bh=document.getElementById('bh').value;
					if(bh==null || bh==""){
						alert("请输入票据号");
						document.getElementById('bh').focus();
						return false;
					}
					
					var v_gatheringTime=document.getElementById('gatheringTime').value;
					if(v_gatheringTime==""){
						alert("请输入缴费日期");
						document.getElementById('gatheringTime').focus();
						return false;
					}

					var chargeId=document.getElementById('chargeId').value;
					if(chargeId=='')
					{
						alert("收款类型输入有误，请重新输入!");
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

<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>指定费用收取</p> 
            </div>
           <form id="ff" method="post" action="assign!save3.action" name="formadd" >
               <s:token></s:token>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区<font color="red">*</font></td>
                        <td><s:select name="areaId" id="areaId" list="viewList" headerKey="" headerValue="==请选择小区==" listKey="id" listValue="areaName" theme="simple" onchange="getEdifice()"></s:select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">楼栋<font color="red">*</font></td>
                        <td><div id="edifice"><select name="edificeId" id="edificeId" ></select></div></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">房间<font color="red">*</font></td>
                        <td><div id="house"><select name="houseId" id="houseId"></select></div></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">票据号<font color="red">*</font></td>
                        <td><input type="text" name="bh" id="bh"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">交费金额<font color="red">*</font></td>
                        <td><input type="text" name="factMoney" id="factMoney"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">应缴费日期</td>
                        <td><input type="text" name="recordMonth" size="12" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收款项目</td>
                        <td><div id="databaseId"><select name="chargeId" id="chargeId"><option value="">-请选择收费项目-</option> </select> </div> </td>
                    </tr>
                    <tr>
                        <td height="35" align="center">说明</td>
                        <td><textarea rows="5" cols="40" name="remark"></textarea></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">交费日期</td>
                        <td><input type="text" id="gatheringTime" name="gatheringTime" value="<%=str_date1%>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：应缴费日期为空，默认和缴费日期相同<Br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                            
                                                                          带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        	<s:hidden name="action" value="add"></s:hidden>
							<s:hidden name="chargeBasedata.isUser" value="使用"></s:hidden>
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
function getEdifice(){
	$("#edifice").load("assign!getEdifice.action?areaId="+$("#areaId").val());
	$("#databaseId").load("pay!getChargeBasedata1.action?areaId="+$("#areaId").val());
}
function getHouse(){

	var edificeId = formadd.edificeId.value;
	$("#house").load("assign!getHouseInfo.action?edificeId="+edificeId);
}

</script>
</html>