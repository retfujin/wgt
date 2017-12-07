<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<title>新增</title>
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
					if($("#areaId").val()=='-1'){
						alert("小区必须选择");
						document.getElementById('areaId').focus();
						return false;
					}
					if(document.getElementById('edificeId').value=='-1'||document.getElementById('edificeId').value=='')
					{
						alert("楼栋必须选择");
						document.getElementById('edificeId').focus();
						return false;
					}
					if(frm.cell.value=='')
					{
						alert("单元不能为空，请填写");
						document.getElementById('cell').focus();
						return false;
					}	
					if(!checkOnlyNum(frm.cell,"单元"))//只能是数字的验证
					{
						document.getElementById('cell').select();
						return false;
					}
					if(frm.houseName.value=='')
					{
						alert("房号不能为空，请填写");
						document.getElementById('houseName').focus();
						return false;
					}
					if(!checkField(frm.buildnum,"建筑面积"))
					{
						document.getElementById('buildnum').focus();
						return false;
					}
					if(!checkField(frm.buildnum,"建筑面积"))//只能是数字的验证
					{
						document.getElementById('buildnum').select();
						return false;
					} 
					if(new Number(document.getElementById('buildNum').value)==0)
				    {
				        alert("建筑面积不能为0，请重新填写");
				        document.getElementById('buildNum').select();
				        return false;
				    }
					if(isNaN(document.getElementById('layer').value))
					{
						alert("请填写正确的层数");
						document.getElementById('layer').focus();
						return false;
					}
					var re = new RegExp(/^(-|\+)?\d+$/); 
					if(re.test(document.getElementById('layer').value)==false)
					{
						alert("层数必须为整数");
						return false;
					}	
					 
					if($("#issale").val()=="入伙"){
						if($("#ownerName").val()==""){
							alert("业主姓名不能为空");
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
<script type="text/javascript">
function doRequest(){
	$("#target").load("edifice!getajaxedifice.action?areaId="+$("#areaId").val());
}
function showOwner(){
	if(document.getElementById('issale').value=='入伙'){	
		owner.style.display="block";
	}else {
		owner.style.display="none";
	}
}
</script>
<%@ include file="/commons/meta.jsp"%>
<body>

<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>房间</p> 
            </div>
            <form id="ff" method="post" action="cell!save.action" name="frmAdd">
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区名称<font color="red">*</font></td>
                        <td><s:select id="areaId" name="entity.areaId" list="viewList" headerKey="-1" headerValue="= 选择小区=" listKey="id" listValue="areaName" theme="simple" onchange="doRequest();" /></td>
                        <td height="35" align="center">楼栋名称<font color="red">*</font></td>
                        <td><div id="target"></div></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">单元<font color="red">*</font></td>
                        <td><s:textfield name="entity.cell" id="cell" value="1" size="10" maxlength="10" theme="simple" /></td>
                        <td height="35" align="center">房号<font color="red">*</font></td>
                        <td><s:textfield name="entity.houseName" id="houseName" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">建筑面积<font color="red">*</font></td>
                        <td><s:textfield name="entity.buildnum"	id="buildnum" size="15" maxlength="15" theme="simple" /></td>
                        <td height="35" align="center">所在层数<font color="red">*</font></td>
                        <td><s:textfield name="entity.layer" id="layer" size="15" maxlength="15" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">房间类型</td>
                        <td><s:select name="entity.houseType" id="houseType" list="#{'住宅':'住宅','商铺':'商铺'}" theme="simple" /></td>
                        <td height="35" align="center">楼层类型</td>
                        <td><s:select name="entity.layerType" id="layerType" list="#{'多层':'多层','高层':'高层','小高层':'小高层'}"	theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">房间状态</td>
                        <td><s:select name="entity.isSale" id="issale" list="#{'未入伙':'未入伙','入伙':'入伙','空置':'空置'}" theme="simple" onchange="showOwner()" /></td>
                         <td height="35" align="center">总楼层</td>
                         <td ><s:textfield name="entity.layercount" id="layercount" size="15" maxlength="15" theme="simple" /></td>
                    </tr>
                    <tr>
                    	<td height="35" align="center">备注</td>
                    	<td colspan="3"><s:textarea name="entity.remark" id="remark" cols="35" rows="3" theme="simple" value="无" /></td>
                    </tr>
                    <tr>
                    	<td colspan="4">
                    		<div id="owner" style="display: none;">
			                    <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
									<tr>
										<td height="35" align="center">业主姓名<font color="red">*</font></td>
										<td ><s:textfield name="entity.ownerName" id="ownerName" theme="simple" /></td>
										<td height="35" align="center">性别</td>
										<td ><s:textfield name="entity.sex" id="sex" theme="simple" /></td>
									</tr>
									<tr>
										<td height="35" align="center">年龄</td>
										<td ><s:textfield name="entity.age" id="age" theme="simple" /></td>
										<td height="35" align="center">职业</td>
										<td ><s:textfield name="entity.job" id="job" theme="simple" /></td>
									</tr>
									<tr>
										<td height="35" align="center">生日</td>
										<td ><s:textfield name="entity.birthday" id="birthday" theme="simple" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
										<td height="35" align="center">证件类型</td>
										<td ><s:textfield name="entity.paperType" id="paperType" theme="simple" /></td>
									</tr>
									<tr>
										<td height="35" align="center">证件号码1</td>
										<td ><s:textfield name="entity.paperNum" onblur="CodeValid();" id="paperNum" theme="simple" /></td>
										<td height="35" align="center">证件号码2</td>
										<td ><s:textfield name="entity.paperNumtwo" id="paperNumtwo" theme="simple" /></td>
									</tr>
									<tr>
										<td height="35" align="center">移动电话</td>
										<td ><s:textfield name="entity.mobTel" id="mobTel" theme="simple" /></td>
										<td height="35" align="center">固定电话</td>
										<td ><s:textfield name="entity.phone" id="phone" theme="simple" /></td>
									</tr>
									<tr>
										<td height="35" align="center">家庭成员</td>
										<td ><s:textfield name="entity.familymember" id="familymember" theme="simple" /></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td height="35" align="center">是否入住</td>
										<td><s:select name="entity.occupancyType" id="occupancyType" list="#{'入住':'入住','未入住':'未入住','出租':'出租'}" theme="simple" />
										</td>
										<td height="35" align="center">入住日期</td>							 
										<td><s:textfield name="entity.inDate" id="inDate" theme="simple" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
									</tr>
									</table>
								</div>
                    	
                    	</td>
                    </tr>
                     <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：楼层为整数<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							带<font color="red">*</font>为必须填写
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
<script type="text/javascript">

function CodeValid(){
	var paperNum = document.getElementById('paperNum').value;
	if(!IdentityCodeValid(paperNum)){
		return;
	}else{
		if(maleOrFemalByIdCard(paperNum)=='male')
			document.getElementById("sex").value='男'; 	
		else
			document.getElementById("sex").value='女';
		IdCardValidate(paperNum);
	}	
}
   
function IdCardValidate(paperNum) { 
	if (paperNum.length == 15)   
        isValidityBrithBy15IdCard(paperNum);    
    else   
    	isValidityBrithBy18IdCard(paperNum);
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
   document.getElementById('birthday').value = year+"-"+month+"-"+day;  
   var myDate = new Date();
   var nowyear = myDate.getFullYear();
   document.getElementById('age').value = parseInt(nowyear)-parseInt(year);
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
     document.getElementById('birthday').value = year+"-"+month+"-"+day; 
     var myDate = new Date();
     var nowyear = myDate.getFullYear();
     document.getElementById('age').value = parseInt(nowyear)-parseInt('19'+year);
 }   
</script>  
    
    
</body>
</html>
