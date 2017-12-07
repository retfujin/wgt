<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head> 
</head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../../js/func.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<body>

<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>客户投诉受理登记表</p> 
            </div>
            <form id="ff" method="post" action="suit!save2.action" name="frmAdd" >
            <s:hidden name="entity.houseId"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区<font color="red">*</font></td>
                        <td><s:select list="retList" name="entity.areaId" id="areaId"  listKey="id" listValue="areaName" cssClass="con_input_con" theme="simple"/></td>
                        <td height="35" align="center">填表时间</td>
                        <td><s:textfield name="entity.suitTime" id="suitTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  cssClass="con_input_con" theme="simple"/></td>
                    </tr>
					  <tr>
					  	 <td height="35" align="center">客户姓名</td>
					  	<td><s:textfield name="entity.suitName" id="suitName"   size="27" cssClass="con_input_con"  theme="simple"/><font color="red"></font></td>
					     <td height="35" align="center">联系电话</td>
					    <td><s:textfield name="entity.suittel" id="suittel"   size="27" cssClass="con_input_con" theme="simple"/><font color="red"></font></td>
					  </tr>
					  <tr>
					    <td height="35" align="center">客户地址</td>
					  	<td ><s:textfield name="entity.suitDepartment" id="suitDepartment"   size="27" cssClass="con_input_con" theme="simple"/>
					  	<font color="red"></font></td>
					     <td height="35" align="center">投诉方式</td>
					    <td><s:select name="entity.suitWay" id="suitWay"  list="#{'来访':'来访','电话':'电话','短信':'短信','信函':'信函','其它':'其它'}"   cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	 <td height="35" align="center">投诉时间</td>
					  	<td><s:textfield name="entity.suitDate" id="suitDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="true"  size="20" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					     <td height="35" align="center">投诉类型</td>
					    <td><s:select name="entity.suitType" id="suitType"   list="#{'有效':'有效','无效':'无效'}"  cssClass="con_input_con" theme="simple"/></td>
					  </tr>
					   <tr>
					  	 <td height="35" align="center">投诉内容</td>
					  	<td class="r" colspan="3"><s:textarea cols="50" rows="4" name="entity.suitMess"  id="suitMess" cssClass="con_input_con" theme="simple" /><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	 <td height="35" align="center" colspan="2">记录人：<input type="text" name="entity.name1"   id="name1" value="<%=session.getAttribute("userName") %>" size="12" class="con_input_con"/><font color="red">*</font></td>
					  	<td colspan="2">日期：<input type="text" name="entity.suitDate1" id="suitDate1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="" size="20" readonly="readonly"  class="con_input_con" /><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	 <td height="35" align="center">处理过程</td>
					  	<td class="r" colspan="3"><s:textarea cols="50" rows="4" name="entity.investigationState"  id="investigationState" cssClass="con_input_con"  theme="simple"/><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	 <td height="35" align="center" colspan="2">处理人： <s:textfield name="entity.name2"   id="name2" size="12" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  	<td colspan="2">日期<s:textfield name="entity.suitDate2" id="suitDate2"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   size="12" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	 <td height="35" align="center">回访情况</td>
					  	<td class="r" colspan="3"><s:textarea cols="50" rows="4" name="entity.processState"   id="processState" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  </tr>
					  <tr>
					  	 <td height="35" align="center" colspan="2">回访人：  <s:textfield name="entity.name3"   id="name3" size="12" cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  	<td colspan="2">日期 <s:textfield name="entity.suitDate3" id="suitDate3"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   cssClass="con_input_con" theme="simple"/><font color="red">*</font></td>
					  </tr>
                     <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        <s:hidden name="entity.id"></s:hidden>
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!----add结束--->
   
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(document.getElementById('suitName').value==''){
						alert("客户姓名不能为空 ！");
						document.getElementById('suitName').focus();
						return false;
					}
					if(document.getElementById('suitDate').value==''){
						alert("投诉时间不能为空 ！");
						document.getElementById('suitDate').focus();
						return false;
					}
					if(document.getElementById('suitTime').value==''){
						alert("填表时间不能为空 ！");
						document.getElementById('suitTime').focus();
						return false;
					}
					if(document.getElementById('suitMess').value==''){
						alert("投诉内容不能为空 ！");
						document.getElementById('suitMess').focus();
						return false;
					}
					if(document.getElementById('name1').value==''){
						alert("记录人不能为空 ！");
						document.getElementById('name1').focus();
						return false;
					}
					if(document.getElementById('suitDate1').value==''){
						alert("记录时间不能为空 ！");
						document.getElementById('suitDate1').focus();
						return false;
					}
					if(document.getElementById('investigationState').value==''){
						alert("处理过程不能为空 ！");
						document.getElementById('investigationState').focus();
						return false;
					}
					if(document.getElementById('name2').value==''){
						alert("处理人不能为空 ！");
						document.getElementById('name2').focus();
						return false;
					}
					if(document.getElementById('suitDate2').value==''){
						alert("处理时间不能为空 ！");
						document.getElementById('suitDate2').focus();
						return false;
					}
					
					
					if(document.getElementById('processState').value!=''){
						if(document.getElementById('name3').value==''){
							alert("回访人不能为空 ！");
							document.getElementById('name3').focus();
							return false;
						}
						if(document.getElementById('suitDate3').value==''){
							alert("回访时间不能为空 ！");
							document.getElementById('suitDate3').focus();
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
</body>
</html>
