<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<html>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/hanx.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.name.value=='')
					{
						alert("姓名不能为空");
						return false;
					}
					var text=$("#areaId").find("option:selected").text();
					$("#areaName").val(text);
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
               <p>管理人员</p> 
            </div>
          <form id="ff" method="post" action="employee!save.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.employeetype"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName">
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td ><s:select list="retList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select></td>
		    <td >部门</td>
		    <td ><s:select list="viewList" name="entity.departname" id="departname" listKey="name" listValue="name" theme="simple"></s:select></td>
     	  </tr>	
     	  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" theme="simple"></s:textfield> </td>
		    <td >性别 </td>
		    <td ><s:radio name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" theme="simple"></s:radio> </td>
		  </tr>	
		  <tr>
		    <td >职位</td>
		    <td ><s:select list="viewList1" name="entity.position" id="position" listKey="name" listValue="name" theme="simple"></s:select></td>
		    <td >职称</td>
		    <td ><s:textfield name="entity.thetitle" id="thetitle" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		    <td >出生日期 </td>
		    <td ><s:textfield name="entity.brithday" id="brithday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="true" theme="simple"></s:textfield> </td>
		    <td >年龄</td>
		    <td ><s:textfield name="entity.age" id="age" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		  	<td >学历 </td>
		    <td ><s:select name="entity.education" id="education" list="#{'1':'初中及以下','2':'高中','3':'中专/技校','4':'大专','5':'本科','6':'硕士及以上'}" theme="simple"></s:select> </td>
		    <td >身份证号</td>
		    <td ><s:textfield name="entity.cardid" id="cardid"  onblur="CodeValid();" theme="simple"></s:textfield> </td>
		  </tr>		 
		  <tr>
		  	<td >联系方式 </td>
		    <td ><s:textfield name="entity.phone" id="phone" theme="simple"></s:textfield> </td>
		    <td >入职时间</td>
		    <td ><s:textfield name="entity.entrydate" id="entrydate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		  	<td >劳动合同签订日期 </td>
		    <td colspan="3">开始时间<s:textfield name="entity.beginlabordate" id="beginlabordate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="true" theme="simple"></s:textfield> 
		    			 	到截止时间<s:textfield name="entity.endlabordate" id="endlabordate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="true" theme="simple"></s:textfield></td>
		  </tr>	
		  <tr>
		  	<td >签订劳动合同期限 </td>
		    <td ><s:select name="entity.endyear" id="endyear" list="#{'一':'一','二':'二','三':'三','四':'四','五':'五'}" theme="simple"></s:select>年</td>
		    <td >离职时间</td>
		    <td ><s:textfield name="entity.enddate" id="enddate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		  	<td >备注 </td>
		    <td colspan="3">
		    	<s:textarea name="entity.remarks" id="remarks" cols="60" rows="3" theme="simple"></s:textarea>
			</td>
		  </tr>	
		  <tr class="footer">
                        <td colspan="8">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
			</table>
            </form>
        </div>
    </div>
    
  <script type="text/javascript">
  function CodeValid(){
	var cardid = document.getElementById('cardid').value;
	if(!IdentityCodeValid(cardid)){
		document.getElementById('cardid').select();
		return;
	}else{
		if(maleOrFemalByIdCard(cardid)=='male')
			document.getElementsByName("entity.sex")[0].checked=true; 	
		else
			document.getElementsByName("entity.sex")[1].checked=true;
		IdCardValidate(cardid);
	}	
}
   
function IdCardValidate(idCard) { 
	if (idCard.length == 15)   
        isValidityBrithBy15IdCard(idCard);    
    else   
    	isValidityBrithBy18IdCard(idCard);
}
/**  
 * 验证18位数身份证号码中的生日是否是有效生日  
 * @param idCard 18位书身份证字符串  
 * @return  
 */  
function isValidityBrithBy18IdCard(idCard18){   
   var year =  idCard18.substring(6,10);   
   var month = idCard18.substring(10,12);   
   var day = idCard18.substring(12,14);   
   document.getElementById('brithday').value = year+"-"+month+"-"+day;  
   var date = new Date();
   var y = date.getFullYear();
   document.getElementById('age').value = y-year;
}   
 /**  
  * 验证15位数身份证号码中的生日是否是有效生日  
  * @param idCard15 15位书身份证字符串  
  * @return  
  */  
 function isValidityBrithBy15IdCard(idCard15){   
     var year =  idCard15.substring(6,8);   
     var month = idCard15.substring(8,10);   
     var day = idCard15.substring(10,12);   
     document.getElementById('brithday').value = year+"-"+month+"-"+day; 
 }   
</script>  
</body>
</html>
