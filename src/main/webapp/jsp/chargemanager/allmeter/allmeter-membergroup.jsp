<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<%
String areaId=request.getParameter("aid"); 
String edificeId=request.getParameter("bid"); 
//	if(areaId!=null&&!areaId.equals(""))
//		out.print("您目前选择的是针对楼栋的业主");
//	if()
		out.print("请选择以下房间");
%>
<br>
<%
if(areaId!=null&&!areaId.equals(""))
{
%>
<form name="listEdificeForm">
<table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="edittable">
<tr align="center">
	<td width="24%">
		<input type="checkBox" name="mmAll" onClick="checkAll(this, 'edificeCheckBox')" value="<s:property value="viewList[#stuts.index][2]"/>">全选
	</td>
	<td width="44%">
		楼栋	</td>
	<td width="32%">
		所属小区	</td>
</tr>
<s:if test="viewList.size()>0">
<s:iterator value="viewList" status="stuts">
<tr align="center">
	<td>
		<input type="checkBox" name="edificeCheckBox" id="edificeCheckBox" value="<s:property value="viewList[#stuts.index][0]"/>"  onClick="checkItem(this, 'mmAll')">
		
	</td>
	<td>		
		<input type="hidden" name="retEdificeName"  value="<s:property value="viewList[#stuts.index][1]"/>">
		<s:property value="viewList[#stuts.index][1]"/>
	</td>
	<td>
		<s:property value="viewList[#stuts.index][2]"/>
	</td>
</tr>
</s:iterator>
<tr>
	<td height="30" colspan="3">
		<div align="center">
		  <input type="hidden" name="retAreaName"  value="<s:property value="viewList[0][2]"/>
		  ">
	      <input type="hidden" name="retValue">
	      <input type="button"  value="确认" onClick="button()" class="a">
          </div></td>
</tr>
</s:if>
<s:else>
<tr>
	<td colspan="3">请将小区资料完善后再选择</td>
</tr>
<tr>
	<td colspan="3">
	<input type="button"  value="返回" onClick="ret()">
	</td>
</tr>
</s:else>
</table>
</form>
<%
}else
{
%>
<form name="listHouseForm">
<table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" class="tablegray" id="edittable">
<tr align="center">
	<td width="20%">
		<input type="checkBox" name="mmAll2" onClick="checkAll(this, 'houseCheckBox')">全选
	</td>
	<td width="25%">房间编号	</td>
	<td width="30%">房间名称</td>
	<td>房间收费面积</td>
</tr>
<s:if test="viewList.size()>0">
<s:iterator value="viewList" status="stuts">
<tr align="center">
	<td>
		<input type="checkBox" name="houseCheckBox" value="<s:property value="viewList[#stuts.index][0]"/>"  onClick="checkItem(this, 'mmAll2')">
		
	</td>
	<td>
		<s:property value="viewList[#stuts.index][0]"/>
	</td>
	<td>
		<s:property value="viewList[#stuts.index][1]"/>	
		<input type="hidden" name="retOwerName2"  value="<s:property value="viewList[#stuts.index][1]"/>">
	</td>
	<td>
		<s:property value="viewList[#stuts.index][2]"/>
	</td>
	
</tr>
</s:iterator>
<tr>
	<td height="30" colspan="4">
		<div align="center">
		  <input type="hidden" name="retEdificeName2" id="retEdificeName2"  value="<s:property value="viewList[0][3]"/>">
	      <input type="hidden" name="retValue2">
	      <input type="button"  value="选择" onClick="button2()" class="a">
          </div></td>
</tr>
</s:if>
<s:else>
<tr>
	<td colspan="3">请将业主资料完善后再选择</td>
</tr>
<tr>
	<td colspan="3"><input type="button"  value="返回" onClick="ret()">
	</td>
</tr>
</s:else>
</table>

</form>

<%}%>
<input type="hidden" name="hidden"/>
<script language="javascript">
function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}
function checkItem(e, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
     if(!aa[i].checked) return;
    all.checked = true;
  }
}



function button()//选中某个小区
{
	var aa=document.getElementsByName("mmAll");
//	var ptextid = window.dialogArguments;
	var retValue="";
	if(aa[0].checked)//选中全选，即对整个小区（楼栋）发送信息
	{
//		document.listEdificeForm.retValue.value=<%=areaId%>+"\002";
	//	alert(document.getElementsByName("mmAll")[0].value);
		
		retValue=document.listEdificeForm.retAreaName.value+"/"+<%=areaId%>+"a,";
		alert(retValue);	
//		alert("要添加的值："+retValue);
//		if(ptextid!=undefined)
//		{
//			alert("父窗口多选框值："+ptextid.options);
//			ptextid.value+=document.listEdificeForm.retValue.value;
			//父窗口 select 中加值
//			var item = opener.document.createElement("OPTION");
//			ptextid.options.add(retValue);			
//			alert("改变后的值："+ptextid.value);
//		}
//		alert(window.opener.document.getElementById("destAddrList").value+"ss");
		
//		opener.document.getElementsByName("destAddrList").value+=document.listEdificeForm.retValue.value;
//		alert("sss"+opener.document.form1.destAddrList.value);
		
	}		
	else
	{
		var bb=document.getElementsByName("edificeCheckBox");
		var cc=document.getElementsByName("retEdificeName");
		for (var i=0; i<bb.length; i++)
		{
//			alert("aaa"+bb[i].value);		
			if(bb[i].checked)
				retValue+=cc[i].value+"/"+bb[i].value+"b,";
		}			
	}
	document.listEdificeForm.retValue.value=retValue;
	top.returnValue=retValue;
	top.close();
}

function button2()//选中某个楼栋
{

	var aa=document.getElementsByName("mmAll2");
	var retValue2="";
	
	if(aa[0].checked)//对整个楼栋发送信息
	{
		//string 
		retValue2=document.getElementById('retEdificeName2').value+"/"+'<%=edificeId%>'+"b,";	
		
	}		
	else
	{		
		var bb=document.getElementsByName("houseCheckBox");
		var cc=document.getElementsByName("retOwerName2");
		for (var i=0; i<bb.length; i++)
		{
			if(bb[i].checked)
				retValue2+=cc[i].value+"/"+bb[i].value+"c,";
//			alert(bb[i].value);	
		}	

	}
	document.listHouseForm.retValue2.value=retValue2;
	top.returnValue=retValue2;
	top.close();	
}

function ret(){
	
	window.close();
}

</script>
</body>
</html>
