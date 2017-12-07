<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%><html>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function getEdifice(){
	$("#edifice").load("ownerdecorate!getEdifice.action?areaId="+$("#areaId").val());
}
function getHouse(){
	var edificeId = formadd.edificeId.value;
	$("#house").load("ownerdecorate!getHouseInfo.action?edificeId="+edificeId);
}
</script>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.houseId=="" || frm.houseId.value==""){
						alert("房间编号不能为空 ！");
						return false;
					}
					else 
						return true ;
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
               <p>装修记录</p> 
            </div>
            <form id="ff" method="post" action="ownerdecorate!save.action" name="formadd" >
                <s:hidden name="entity2.id"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区名称&nbsp;&nbsp;<font color="red">*</font></td>
                        <td ><s:select name="entity2.areaId"  id="areaId" list="viewList" headerKey="-1" headerValue="==请选择小区==" listKey="id" listValue="areaName" theme="simple" onchange="getEdifice()"></s:select></td>
				    </tr>
					<tr>
				      <td height="35" align="center">楼栋&nbsp;&nbsp;<font color="red">*</font></td>
				      <td ><div id="edifice">&nbsp;&nbsp;<select name="entity2.edificeId" id="edificeId" ></select></div></td>
				    </tr>
				    <tr>
				    	<td height="35" align="center">房间&nbsp;&nbsp;<font color="red">*</font></td>
				        <td><div id="house">&nbsp;&nbsp;<select name="entity2.houseId" id="houseId"></select></div></td>
				    </tr>
				    <tr>
				      <td height="35" align="center">手续办理</td>
				      <td ><s:textfield  name="entity2.decoratesxbl" id="decoratesxbl" size="25" theme="simple"/></td>
				    </tr>    
				    <tr>
				      <td height="35" align="center">装修起始时间</td>
				      <td ><s:textfield  name="entity2.decorateindate" id="decorateindate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="true" theme="simple"/></td>
				    </tr>
				      <tr>
				      <td height="35" align="center">验收时间</td>
				      <td ><s:textfield  name="entity2.decorateoutdate" id="decorateoutdate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="true" theme="simple"/></td>
				    </tr>
				    <tr>
				      <td height="35" align="center">交费情况</td>
				      <td ><s:textfield  name="entity2.decoratecontributions" id="decoratecontributions" size="25" theme="simple"/></td>
				    </tr>
				     <tr>
				      <td height="35" align="center">押金</td>
				      <td ><s:textfield  name="entity2.deposit" id="deposit" size="25" theme="simple"/>&nbsp;元</td>
				    </tr>
				     <tr>
				      <td height="35" align="center">出入证费用</td>
				      <td ><s:textfield  name="entity2.passFee" id="passFee" size="25" theme="simple"/>&nbsp;元</td>
				    </tr>
				     <tr>
				      <td height="35" align="center">状态</td>
				      <td colspan="4"><s:select name="entity2.status" id="status" list="#{'施工':'施工','完成':'完成'}" onchange="disstatu();" theme="simple"></s:select></td>
				    </tr>
				     <tr>
				     <td height="35" align="center">押金是否退还</td>
				      <td ><s:select name="entity2.isReturn" id="isReturn" list="#{'未退':'未退','已退':'已退'}" theme="simple"></s:select></td>
				    </tr>
				     <tr>
				     <td height="35" align="center">设备是否损坏</td>
				      <td ><s:select  name="entity2.isDamage" id="isDamage" onchange="disreturn();" list="#{'未损坏':'未损坏','损坏':'损坏'}" theme="simple"/></td>
				    </tr>
				     <tr>
				     <td height="35" align="center">损坏赔偿</td>
				      <td ><s:textfield  name="entity2.compensation" id="compensation" size="25" theme="simple"/>&nbsp;元</td>
				    </tr>
				     <tr>
				      <td height="35" align="center">备注</td>
				      <td ><s:textfield  name="entity2.decoratenotes" id="decoratenotes" size="25" theme="simple"/></td>
				    </tr> 
                    <tr>
                        <td height="35" align="left" colspan="2">
                                                                注：带<font color="red">*</font>为必须填写<br>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态为完成时，请标明押金是否退还，设备是否损坏，是否进行赔偿。
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

 
<SCRIPT type="text/javascript">
if(document.getElementById('status').value=='施工'){
	document.getElementById('isReturn').disabled=true;
	document.getElementById('isDamage').disabled=true;
	document.getElementById('compensation').disabled=true;
}
function disstatu(){
	if(document.getElementById('status').value=='完成'){
		document.getElementById('isReturn').disabled=false;
		document.getElementById('isDamage').disabled=false;
	}else if(document.getElementById('status').value=='施工'){
		document.getElementById('isReturn').disabled=true;
		document.getElementById('isDamage').disabled=true;
		document.getElementById('compensation').disabled=true;
	}
}
function disreturn(){
	if(document.getElementById('isDamage').value=='损坏'){
		document.getElementById('compensation').disabled=false;
	}else if(document.getElementById('isDamage').value=='未损坏'){
		document.getElementById('compensation').value="";
		document.getElementById('compensation').disabled=true;
	}	
}
</SCRIPT>  
</body>
</html>