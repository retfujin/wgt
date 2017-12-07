<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.Random"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					var v_chargeId=document.getElementById('chargeId');
					if(v_chargeId.value=='')
					{
						alert("请输入收费项目编号!");
						v_chargeId.focus();
						return false;
					}
					if(isNaN(v_chargeId.value))
					{
						alert("收费项目编号必须为数字");
						v_chargeId.select();
						return false;
					}
					if(v_chargeId.value.length!=10)
					{
						alert("收费项目编号的长度必须为10位");
						v_chargeId.select();
						return false;
					}
					var v_chargeareaid=document.getElementById('chargeareaid');
					if(v_chargeId.value.substr(0,4)!=v_chargeareaid.value)
					{
						alert("收费项目编号的前4位应该是小区编号");
						v_chargeId.select();
						return false;
					}
					
					var v_chargeName=document.getElementById('chargeName');
					if(v_chargeName.value=='')
					{
						alert("请输入收费项目!");
						v_chargeName.focus();
						return false;
					}
					
					var v_price=document.getElementById('priceValue');
					if(v_price.value!='')
					{			
						if(isNaN(v_price.value))
						{
							alert("请输入单价，必须为数字!");
							v_price.select();
							return false;
						}
					}else{
						alert("请输入单价!");
						v_price.focus();
						return false;
					}
					
					var v_priceUnit=document.getElementById('priceUnit');
					if(v_priceUnit.value.length<1)
					{
						alert("请输入单位!");
						v_priceUnit.focus();
						return false;
					}
					
					var v_chargeType=document.getElementById('chargeType');
					if(v_chargeType.value!='公摊类')
					{
						var v_chargeExpression=document.getElementById('chargeExpression');
						if(v_chargeExpression.value==''){
							alert("请输入计算公式!");
							v_chargeExpression.focus();
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

</head>
<body>
<% 	
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	String str_date1 = formatter.format(currentTime); //将日期时间格式化 
	String[] menuModel={
			"menuModel2.addItem(203,'列表','','basedata!list.action',false);"
			+"menuModel2.addItem(205,'返回','','',false,'','window.history.back();');"
	};
%>

<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>收费项目设置</p> 
            </div>
            <form id="ff" method="post" action="basedata!save.action" name="frm" >
               <s:token></s:token>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区</td>
                        <td><select id="chargeareaid" name="chargeBasedata.area.id" onchange="changeareaid()">
							<s:iterator value="areaList">
								<option value="${id}">${areaName}</option>
							</s:iterator>
							</select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收费编号</td>
                        <td><s:textfield id="chargeId" name="chargeBasedata.id" size="15" theme="simple" /><span id="span1"></span></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收费项目</td>
                        <td><s:textfield id="chargeName" name="chargeBasedata.chargeName" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收费类别</td>
                        <td><s:select id="chargeType" name="chargeBasedata.chargeType" list="#{'固定类':'固定类','走表类':'走表类','车位类':'车位类','公摊类':'公摊类','其他类':'其他类'}" theme="simple"></s:select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">单 价</td>
                        <td><input type="text" id="priceValue" name="priceValue" ></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">单位</td>
                        <td><s:textfield id="priceUnit"	name="chargeBasedata.priceUnit" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">计算公式</td>
                        <td><s:textarea id="chargeExpression" name="chargeBasedata.chargeExpression" cols="35" rows="2"	theme="simple"></s:textarea></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">公式说明</td>
                        <td><s:textarea id="chargeExplain" name="chargeBasedata.expExplain" cols="35" rows="2"	theme="simple"></s:textarea></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">收费说明</td>
                        <td><s:textarea id="chargeExplain" name="chargeBasedata.chargeExplain" cols="35" rows="2" theme="simple"></s:textarea></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">启用日期</td>
                        <td><input type="text" name="chargeBasedata.beginTime" value="<%=str_date1%>"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：收费编号为10位数字,前4位小区编号+中间4位收费类型编号+最后2位随机号<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                          收费项目的名称。如：水费、电费、物管费等<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							计算公式说明：#单价#  ,#房间收费面积#  ,#业户电表用量#,#业户水表用量#  ,#业户热水表用量#  ,#业户暖气表用量#  ,#小区总房间数#<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							收费类型编号：物管费：1101&nbsp;&nbsp;&nbsp;&nbsp;车位综合服务费：1102&nbsp;&nbsp;&nbsp;&nbsp;水费：2102&nbsp;&nbsp;&nbsp;&nbsp;
										   热水费：2103&nbsp;&nbsp;&nbsp;&nbsp;	暖气费：2104&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电费：2105&nbsp;&nbsp;&nbsp;&nbsp;公摊费：3101 <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										   垃圾清运费：6101&nbsp;&nbsp;&nbsp;&nbsp;广告费：6102&nbsp;&nbsp;&nbsp;&nbsp;                                           
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
function changeareaid()
{
	var v_chargeareaid=document.getElementById('chargeareaid');
	var v_span1 = document.getElementById('span1');
	v_span1.innerText = '小区编号'+v_chargeareaid.value;
}
changeareaid();
</script>
</html>
