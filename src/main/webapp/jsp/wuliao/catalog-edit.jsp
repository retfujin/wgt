<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<title>新增</title>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.id.value=='')
					{
						alert("编号不能为空，请填写");
						return false;
					}
					if(checkOnlyNum(frm.id,"编号")==false){
						return false;
					}
					if(frm.name1.value=='')
					{
						alert("名称不能为空，请填写");
						return false;
					}
					if(frm.mnemonicCode.value=='')
					{
						alert("助记码不能为空，请填写");
						return false;
					}
					if(frm.planPrice.value=='')
					{
						alert("计划单价不能为空，请填写");
						return false;
					}					
					if(checkOnlyNum(frm.numPackage,"包装数量")==false){
						return false;
					}
					if(checkOnlyNum(frm.upperLimit,"上限数量")==false){
						return false;
					}
					if(checkOnlyNum(frm.lowerLimit,"下限数量")==false){
						return false;
					}
					if(checkOnlyNum(frm.lifeOfQualityAssurance,"保质期")==false){
						return false;
					}
					if(checkOnlyNum(frm.volume,"体积")==false){
						return false;
					}
					
					if(checkMoney(frm.planPrice,"计划单价")==false)
						return false;
					if(checkMoney(frm.salePrice,"销售单价")==false)
						return false;
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
               <p>新增物料目录</p> 
            </div>
            <form id="ff" method="post" action="catalog!save.action" name="frmAdd">
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">物料编号(数字)<font color="red">*</font></td>
                    	<td><s:textfield  name="e.id" id="id" size="10" theme="simple" readonly="true" cssClass="locked"/></td>
					    <td height="35" align="center">物料名称<font color="red">*</font></td>
                        <td><s:textfield  name="e.name" id="name1" size="20" maxlength="20" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">助记码<font color="red">*</font></td>
                        <td><s:textfield  name="e.mnemonicCode" id="mnemonicCode" size="10" maxlength="10" theme="simple"/></td>
                        <td height="35" align="center">规格<font color="red">*</font></td>
                        <td><s:textfield  name="e.norm" id="norm" size="10" maxlength="10" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">所属类别</td>
                        <td colspan="3">
							<s:select name="e.wlAssortmentEO.id" id="wlAssortmentEOId" list="viewList" listKey="id" listValue="name" theme="simple"></s:select>
						</td>
                    </tr>
                    <tr>
                        <td height="35" align="center">单位</td>
                        <td><s:textfield  name="e.unit" id="unit" size="15" maxlength="15" theme="simple"/></td>
                        <td height="35" align="center">最小包装</td>
                        <td><s:textfield  name="e.minPackage" id="minPackage" size="15" maxlength="15" theme="simple"/></td>
                    </tr>
                    <tr>
					  	<td height="35" align="center" >包装数量</td>
					    <td ><s:textfield  name="e.NumPackage" id="numPackage" size="15" maxlength="15" theme="simple"/></td>					  	
					  	<td height="35" align="center" >上限</td>
					    <td ><s:textfield  name="e.upperLimit" id="upperLimit" size="15" maxlength="15" theme="simple"/></td>					  
					  </tr>
					  <tr>
					  	<td height="35" align="center" >下限</td>
					    <td ><s:textfield  name="e.lowerLimit" id="lowerLimit" size="15" maxlength="15" theme="simple"/></td>					  	
					  	<td height="35" align="center" >保质期</td>
					    <td ><s:textfield  name="e.lifeOfQualityAssurance" id="lifeOfQualityAssurance" size="15" maxlength="15" theme="simple"/></td>					  
					  </tr>
					  <tr>
					  	<td height="35" align="center" >体积</td>
					    <td  ><s:textfield  name="e.volume" id="volume" size="15" maxlength="15" theme="simple"/></td>					  	
					  	<td height="35" align="center" >产地</td>
					    <td ><s:textfield  name="e.placeOfProduction" id="placeOfProduction" size="15" maxlength="15" theme="simple"/></td>					  
					  </tr>
					  <tr>
					  	<td height="35" align="center" >品牌</td>
					    <td ><s:textfield  name="e.brand" id="brand" size="15" maxlength="15" theme="simple"/></td>					  	
					  	<td height="35" align="center" >厂家</td>
					    <td ><s:textfield  name="e.factory" id="factory" size="15" maxlength="15" theme="simple"/></td>					  
					  </tr>
					   <tr>
					  	<td height="35" align="center" >计划单价<font color="red">*</font></td>
					    <td ><s:textfield  name="e.planPrice" id="planPrice" size="15" maxlength="15" theme="simple"/></td>
					  	<td height="35" align="center" >销售单价</td>
					    <td ><s:textfield  name="e.salePrice" id="salePrice" size="15" maxlength="15" theme="simple"/></td>
					  </tr>
					   <tr>
					  	<td height="35" align="center" >备注</td>
					    <td colspan="3"><s:textfield  name="e.remarks" id="remarks" size="15" maxlength="15" theme="simple"/></td>
					  </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                        <s:hidden name="e.gradCode"></s:hidden>
      					<s:hidden name="upperGradCode" value="%{#parameters.upperGradCode}"></s:hidden>                                     
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
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