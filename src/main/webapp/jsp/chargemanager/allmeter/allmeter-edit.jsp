<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<base target="_self"/>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<script language="javascript">
function checkMoney(pObj,errMsg){

 var obj = eval(pObj);

 var patrn=/^(\d)*(\.(\d){0,4})?$/;
 if(patrn.exec(obj.value)==null){
 
 	alert(errMsg+"必须是数值，可包括4位小数");
 	if(obj.type="text")
 		obj.focus();
 	return false;
 }

 return true;
}
function frmOnAdd(frm){
	var v_areaId = document.getElementById('areaId');
	if(v_areaId.value==-1||v_areaId.value=='')
	{
		alert("您还没有选择小区！");
	//	document.getElementById('areaId').focus();
		return false;
	}
	var v_meterCode = document.getElementById('meterCode');
	if(v_meterCode.value=='')
	{
		alert("请输入表编号！");
		v_meterCode.focus();
		return false;
	}
	var v_meterName = document.getElementById('meterName');
	if(v_meterName.value=='')
	{
		alert("请输入表名称！");
		v_meterName.focus();
		return false;
	}
	var v_lastRecord=document.getElementById('lastRecord');
	if(v_lastRecord.value=='')
	{
		alert("请输入该表的末次表数");
		v_lastRecord.focus();
		return false;
	}else{
		if(isNaN(v_lastRecord.value))
		{
			alert("末次表数必须为数字!");
			v_lastRecord.select();
			return false;
		}			
	}
	var v_priceValue = document.getElementById('priceValue');
	if(v_priceValue.value=='')
	{
		alert("请输入该表的收费单价！");
		v_priceValue.focus();
		return false;
	}
		if(!checkMoney(v_priceValue,"单价"))
		{
			v_priceValue.select();
			return false;
		}
			
	var v_unit = document.getElementById('unit');
	if(v_unit.value.length<1)
	{
		alert("请输入该表的收费单位！");
		v_unit.focus();
		return false;
	}
	var v_times = document.getElementById('times');
	if(v_times.value=='')
	{
		alert("请输入倍率!");
		v_times.focus();
		return false;
	}else{
		if(isNaN(v_times.value)){
			alert("倍率必须是数字!");
			v_times.value.focus();
			return false;
		}
	}
	var v_para = document.getElementById('assignAreaName');
	var v_paravalue = document.getElementById('assignArea');
	var value="";
	var value2="";
	for (i=0;i<frm.receivers.options.length;i++)
	{
		value = value + frm.receivers.options(i).value+",";		
		value2 = value2 + frm.receivers.options(i).innerText+",";		
	}
	
	var v_isAll= document.getElementById('isAll');
	var v_chargebasedataId= document.getElementById('chargebasedataId');
	if(v_isAll.value=='公摊'){
		//alert(frm.receivers.options.length);
		if(v_chargebasedataId.value==''){
			alert("请选择收费项目");
			return false;
		}
		if(frm.receivers.options.length<1){
			alert('请选择公摊范围');
			return false;
		}else{
			v_para.value = value2;
			v_paravalue.value = value;
		}
	}
	return true;
}
function mayAdd(ctrl,value){
	for (var n= 0;n < ctrl.length;n++)
	{
		if(value==ctrl.options[n].value)
		{
			return false;
		}
	}
	return true;
}
function selectListG(setControls,theJSP,width,height)
{
	var sArray,vArray;
//	var retValue,text,value;
//	var retParam="";
	var value,text;
	

	var retValue;
	//var ctrl=eval("document."+setControls);
	var ctrl = setControls;
	retValue=window.showModalDialog(theJSP,"","help:0;resizable:0;status=0;scroll=1;dialogWidth="+width+";dialogHeight="+height+";center=true");
//	alert(retValue);
	if(retValue==null)
	{
		retValue="";
	}
//	alert(String.fromCharCode(9));
	if(retValue!=""&&retValue!=String.fromCharCode(9)) 
	{
		//开始赋新值
		sArray=retValue.split(",");
		for(var i=0;i<sArray.length-1;i++)
		{
			vArray=sArray[i].split("/");
			text=vArray[0];
			value=vArray[1];
			if(mayAdd(ctrl,value))
			{
				var newOption = new Option(text, value, false, false);
				ctrl.add(newOption);
			}
		}
	}
	return retValue;
}
function delReceiver()
{
	var destItem;
	var item, itemValue;
	var i;
	srcItem = frmAdd.receivers;
	for (i=srcItem.length-1;i>=0;i--)
	{
		item = srcItem.item(i);
		if ( item.selected )
		{
			itemValue = item.value.split("\0");
			item.value = itemValue[1];				
			srcItem.remove(i);
		}
	}
}
function show1()
{

	if(disSel.style.display=="none")
	{	
		disSel.style.display="block";
	}else
		disSel.style.display="none";
}
</script>
<%@ include file="/commons/meta.jsp" %>
<body>





<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>总表设置</p> 
            </div>
            <form method="post" action="allmeter!saveAllmeter.action" name="frmAdd" onSubmit="return frmOnAdd(this)">    
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区</td>
                        <td><s:textfield id="areaName" name="allmeter.areaName" size="20" maxlength="20" readonly="true" theme="simple" /></td>
                        <td height="35" align="center">表编号</td>
                        <td><s:textfield id="meterCode" name="allmeter.meterCode" size="20" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">表名称</td>
                        <td><s:textfield id="meterName" name="allmeter.meterName" size="20" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">期初度数</td>
                        <td><input type="text" name="allmeter.initRecord" value="0">度</td>
                    </tr>
                    <tr>
						<td height="35" align="center">末次表数</td>
						<td ><s:textfield name="allmeter.lastRecord" id="lastRecord" size="20" theme="simple"/>度</td>
						
						<td height="35" align="center">单价</td>
				    	<td ><s:textfield id="priceValue" name="allmeter.priceValue" size="20" maxlength="20" theme="simple" /></td>
					</tr>
				  	<tr>
						<td height="35" align="center">单位<font color="red">*</font></td>
				    	<td ><s:textfield id="unit" name="allmeter.unit" size="20" value="元/度" maxlength="20" theme="simple" />
				    	
				    	</td>
				    	
				    	<td height="35" align="center">启用日期</td>
				    	<td>
				    		<s:textfield name="allmeter.beginTime" theme="simple" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"> 
				    			<s:param name="value"><s:date name="allmeter.beginTime" format="yyyy-MM-dd"/></s:param>
				    		</s:textfield>
						</td>
				    </tr>
				    
				  	<tr>
				    	<td height="35" align="center">倍率<font color="red">*</font></td>
				    	<td >
							<s:textfield id="times" onkeypress="return inputOnlyNumber()" value="1" name="allmeter.times" size="20" maxlength="20" theme="simple" />
				    	</td>
				    	<td height="35" align="center">表 类 型<font color="red">*</font></td>
				    	<td >
							<s:select id="meterType"  name="allmeter.meterType" list="#{'电表':'电表','水表':'水表','热水表':'热水表','暖气表':'暖气表','蒸汽表':'蒸汽表'}" theme="simple"></s:select>
						</td>
				    </tr>
				    <tr>
				    	<td height="35" align="center">用表类型</td>
				    	<td>
				    		<s:textfield id="useMeterType" name="allmeter.useMeterType"  theme="simple"/>
						</td>
						<td height="35" align="center">公司表编号</td>
				    	<td >
							<s:textfield id="meterCodelocal" name="allmeter.meterCodelocal" size="20" maxlength="20" theme="simple" />							
				    	</td>
				    </tr>
				     <tr>
				    	<td height="35" align="center">表位置</td>
				   		<td>
				   			<s:textfield name="allmeter.local" size="30" theme="simple"></s:textfield>
				   		</td>
				   		<td height="35" align="center">是否公摊</td>
				   		<td><s:select id="isAll" name="allmeter.isAll" list="#{'不公摊':'不公摊','公摊':'公摊'}" theme="simple" onchange="show1()"></s:select></td>
				    </tr>    
				    <tr>
						<td colspan="4">
							<div id ="disSel" <s:if test="allmeter.isAll=='不公摊'">style="display:none;"</s:if> >
			<table border="1" bordercolor="#98bac4" align="center">
				<tr bordercolor="#F5F1D6">
					<td><div align="center">公摊范围：</div></td>
					<td>
						<select name="receivers" id="receivers" size="6"  style="width: 198;height: 80; background-color:#FFFFFF; color:#006CAD; " multiple="multiple">
							<%
							com.acec.wgt.models.chargemanager.AllmeterEO allmeter = (com.acec.wgt.models.chargemanager.AllmeterEO)request.getAttribute("allmeter");
							String[] assignAreaName = allmeter.getAssignAreaName().split(",");
							String[] assignArea = allmeter.getAssignArea().split(",");
							for(int i=0;i<assignAreaName.length;i++){
								if(assignArea[i]!=null&&assignArea[i].length()>0)
									out.println("<option value='"+assignArea[i]+"'>"+assignAreaName[i]+"</option>");
							}
							%>
							</select>
							<input type="hidden" name="allmeter.assignAreaName" id="assignAreaName"/>
							<input type="hidden" name="allmeter.assignArea" id="assignArea"/>
						</td>
				    	<td align="right">所属收费项目
					    	<div id="target">
				    		<s:select id="chargebasedataId" name="allmeter.chargebasedataId" listKey="id" listValue="chargeName" list="retList" theme="simple"/>
				    		</div>
				    	</td>
				    	<td align="right">分摊方式
	    					<s:select id="assignType" name="allmeter.assignType" list="#{'1':'按面积','2':'按户数'}" theme="simple"></s:select>
	    				</td>
						</tr>
						<tr bordercolor="#F5F1D6">
							<td></td>
							<td><input type="button"  class="a" value="添加" onClick="javascript:selectListG(document.getElementById('receivers'),'allmeter!choiceFrame.action',35,28)">
									<input value="删除" name="addGroupSelf" type="button" id="addGroupSelf2" onClick="delReceiver()" class="a" />		
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						</table>
						</div>		
						</td>
				    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：用来表示公摊计算时使用的费用名称，非管理员请不要随便更改等<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							用表类型：非居民照明用电、蒸汽、消防用水、经营用水、照明用电等<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							公司表编号：公司固定表编号或供电局编号<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        <s:hidden name="allmeter.changeId" />
			   			<s:hidden name="allmeter.changeTime" />
			   			<s:hidden name="allmeter.changeName" />
			   			<s:hidden name="allmeter.changeReason" />
			   			<s:hidden id="areaId" name="allmeter.areaId" />
			   			<s:hidden name="allmeter.id" />
			   			<s:hidden name="allmeter.state" />
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
