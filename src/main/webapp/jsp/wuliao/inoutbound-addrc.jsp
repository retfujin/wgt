<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/ActiveTable.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.rq.value==''){
						alert("日期不能为空");
						return false;
					}
					if(frm.purchaseP.value==''){
						alert("采购人不能为空");
						return false;
					}		
					return checkTable();
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.location.reload();
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
  <body>
  <%
            String[] menuModel = {
            "menuModel2.addItem(203,'建账入库','','inoutbound!addrj.action',false);"
            +"menuModel2.addItem(204,'采购入库','','inoutbound!addrc.action',false);"
            };
 %>
 <%@ include file="/menubar/simple/aa.jsp" %>  
  <table width="97%" border="0" cellspacing="0" cellpadding="0" align=center class="tableBorder1">
  <TBODY>
<TR>
    <TH height=25 align="left">添加采购入库单<span class="样式1"><font style="font-size: 9pt"></font></span></TH>
</TR></tbody>
</table>
<form id="ff" method="post" action="inoutbound!saverc.action" name="frmAdd">
<table width="97%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="edittable">
  <tr>	
  	<td height="28">入库单号：<s:textfield name="e.bh" id="bh" value="%{newBh}" size="20" theme="simple" readonly="true" /></td>
    <td>仓库名称：<s:select name="e.wlStoreHouseEO.id" list="viewList" listKey="id" listValue="name"  theme="simple"></s:select></td>
    <td>入库日期：<s:textfield  name="e.rq" id="rq" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="12" readonly="true" theme="simple"/><font color="red">*</font></td>
  </tr>
  <tr>
  	<td height="28">供&nbsp;&nbsp;应&nbsp;&nbsp;商：<s:select name="e.wlSupplierEO.id" list="viewList1" listKey="id" listValue="name"  theme="simple"></s:select></td>
    <td>发票类型：<s:select name="e.InvNum" list="#{'普通发票':'普通发票','增值税发票':'增值税发票'}" theme="simple"></s:select></td>
    <td>采购人：<s:textfield  name="e.purchaseP" id="purchaseP" size="25" maxlength="20" theme="simple"/><font color="red">*</font></td>
  </tr>
  <tr>
  	<td colspan="3" height="28">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：<s:textfield  name="e.remarks" id="remarks" size="56" maxlength="100" theme="simple"/></td>
 </tr>
  <tr>
  	<td colspan="3" height="32">&nbsp;&nbsp;<input type="submit" name="sub" value="保存" class="a"/></td>
 </tr>
</table>
<table width="97%" align=center class="table_border" id="Active_Table">
  <tr >
  <td class="tdh">&nbsp;</td>
    <td class="tdh" align="center">物料编号</td>
    <td class="tdh" align="center">物料名称</td>
    <td class="tdh" align="center">规格</td>
    <td class="tdh" align="center">产地</td>
    <td class="tdh" align="center">单位</td>
    <td class="tdh" align="center">单价</td>
    <td class="tdh" align="center">数量</td>
    <td class="tdh" align="center">金额</td>
    <td class="tdh" align="center">货位</td>
    <td class="tdh" align="center">批号</td>
  </tr>
  <tr>
    <td class="td_border"><input type="button" onClick="delRow(Active_Table)" value="删除" class="con_button"/>&nbsp;</td>
    <td class="td_border"><input type="text" id="addC1" onFocus="addRow(Active_Table)" size="7" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC2" onFocus="addRow(Active_Table)" size="15" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC3" onFocus="addRow(Active_Table)" size="9" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC4" onFocus="addRow(Active_Table)" size="9" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC5" onFocus="addRow(Active_Table)" size="5" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC6" onFocus="addRow(Active_Table)" size="5" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC7" onFocus="addRow(Active_Table)" size="5" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC8" onFocus="addRow(Active_Table)" size="10" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC9" onFocus="addRow(Active_Table)" size="9" class="con_input_con"/></td>
    <td class="td_border"><input type="text" id="addC10" onFocus="addRow(Active_Table)" size="9" class="con_input_con"/></td>
   
   </tr> 
</table>



  </form>
  </body> 
 
<script type="text/javascript">
function gotoUrl(ta)
{
	returnValue11 = window.showModalDialog("catalog-choiceframe.action","","help:0;resizable:1;status=0;scrollbars=0;dialogWidth=400;dialogHeight=200;center=true");
	
	sArray=returnValue11.split(",");
	for(var i=0;i<sArray.length-1;i++)
	{
		Active_Table.rows[ta].cells[i+1].childNodes[0].value=sArray[i];
	}
	
	//alert(returnValue11);
} 
function cal(ta)
{
	
	var i=5;

	//alert(Active_Table.rows[ta].cells[i+1].childNodes[0].value);

	var a = Active_Table.rows[ta].cells[i+1].childNodes[0].value;
	var b=Active_Table.rows[ta].cells[i+2].childNodes[0].value;
	if(a=='')
	  	a=0;
	if(b=='')
		b=0;
		
 	Active_Table.rows[ta].cells[i+3].childNodes[0].value	= a*b;
}
function   allPrpos ( obj ) { 
    // 用来保存所有的属性名称和值 
    var   props = "" ; 
    // 开始遍历 
    for ( var   p in obj ){   
        // 方法 
        if ( typeof ( obj [ p ]) == " function " ){   
            obj [ p ]() ; 
        } else {   
            // p 为属性名称，obj[p]为对应属性的值 
            props += p + " = " + obj [ p ] + " \t " ; 
        }   
    }   
    // 最后显示所有的属性 
    alert ( props ) ; 
} 
function frmOnAdd(frm){
	if(frm.rq.value==''){
		alert("日期不能为空");
		return false;
	}
	if(frm.purchaseP.value==''){
		alert("采购人不能为空");
		return false;
	}		
	return checkTable();
}
function IsFloat(inputStr){
	var patrn = patrn=/^(\d)*(\.(\d){0,2})?$/
	return patrn.exec(inputStr);
}
</script>
</html>
