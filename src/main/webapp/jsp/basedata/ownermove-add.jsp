<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
</head>
<script type="text/javascript">
	function checkVaue() {
		houseId = document.frmAdd.houseId.value;
		if (houseId == '') {
			alert("房间编号没有录入，无法查询");
			return;
		}
		window.open("/chargemanager/pay!pay.action?houseId=" + houseId);

	}
</script>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if (frm.ownerName.value == "") {
						alert("业主姓名必须要填写！！！");
						document.frmAdd.ownerName.focus();
						return false;
					}
					if (frm.carryDate.value == "") {
						alert("拟搬时间必须要填写！！！");
						document.frmAdd.carryDate.focus();
						return false;
					}
					if (frm.carryName.value == "") {
						alert("搬运人必须要填写！！！");
						document.frmAdd.carryName.focus();
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
<%@ include file="/commons/meta.jsp"%>
<body>


 <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>出入登记</p> 
            </div>
            <form id="ff" method="post" action="ownermove!save.action" name="frmAdd" >
            <s:hidden name="entity.id"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区名称<font color="red">*</font></td>
                        <td>&nbsp;<s:select name="entity.areaId" id="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"></s:select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">业主姓名<font color="red">*</font></td>
                        <td>&nbsp;<s:textfield name="entity.ownerName" id="ownerName" theme="simple" /></td>
                    </tr>
                    <tr>
						<td height="35" align="center">住址</td>
						<td>&nbsp;<s:textfield name="entity.houseAddress" id="houseAddress" theme="simple"/></td>
					</tr>
					<tr>
						<td height="35" align="center">房间编号</td>
						<td>&nbsp;<s:textfield name="entity.houseId" id="houseId" theme="simple"/></td>
					</tr>
					<tr>
						<td height="35" align="center">拟搬时间<font color="red">*</font></td>
						<td>&nbsp;<s:textfield name="entity.carryDate" id="carryDate" readonly="true" theme="simple" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
					</tr>
					<tr>
						<td height="35" align="center">搬运人<font color="red">*</font></td>
						<td>&nbsp;<s:textfield name="entity.carryName" id="carryName" theme="simple" /></td>
					</tr>
					<tr>
						<td height="35" align="center">搬运人证件号</td>
						<td>&nbsp;<s:textfield name="entity.carryNumber" id="carryNumber" theme="simple" /></td>
					</tr>
					<tr>
						<td height="35" align="center">搬运人联系电话</td>
						<td>&nbsp;<s:textfield name="entity.carryPhone" id="carryPhone"	theme="simple" /></td>
					</tr>
					<tr>
						<td height="35" align="center">有无欠费情况</td>
						<td>&nbsp;<s:textfield name="entity.overdue" id="overdue" theme="simple"/>
							<!-- <a id="openlink" href="#" onclick="checkVaue()">查看欠费情况</a>--></td>
					</tr>
					<tr>
						<td height="35" align="center">业主意见</td>
						<td>&nbsp;<s:textfield name="entity.ownerOpinion" id="ownerOpinion" theme="simple" /></td>
					</tr>
					<tr>
						<td height="35" align="center">放行条号</td>
						<td>&nbsp;<s:textfield name="entity.releasePass" id="releasePass" theme="simple" /></td>
					</tr>
			        <tr>
                        <td height="35" align="left" colspan="2">
                                                                注： 带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="2">
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
