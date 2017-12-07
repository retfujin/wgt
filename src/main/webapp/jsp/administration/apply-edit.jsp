<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
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
               <p>应聘人员登记</p> 
            </div>
            <form id="ff" method="post" action="apply!save.action" name="frmAdd" >
                <s:hidden name="entity.id"></s:hidden>
				<s:hidden name="entity.isdel"></s:hidden>
				<s:hidden name="entity.recordid"></s:hidden>
				<s:hidden name="entity.recordname"></s:hidden>
				<input type="hidden" name="entity.areaName" id="areaName">
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >管理处 </td>
		    <td ><s:select list="retList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select> </td>
		  	<td>应聘职位</td>
		  	<td ><s:select list="viewList" name="entity.position" id="position" listKey="name" listValue="name" theme="simple"></s:select></td>
		  	<td>登记日期</td>
		  	<td colspan="3"><s:textfield name="entity.recordtime" id="recordtime" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" size="10" theme="simple"></s:textfield> </td>
		    <td >性别 </td>
		    <td >
		    <s:if test="entity.id!=null">
		    	<s:radio name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" theme="simple"></s:radio> 
		    </s:if>
		    <s:else>
		    	<s:radio name="entity.sex" id="sex" list="#{'男':'男','女':'女'}" value="'男'" theme="simple"></s:radio>
		    </s:else>
		    </td>
		    <td >年龄 </td>
		    <td ><s:textfield name="entity.age" id="age" size="10" theme="simple"></s:textfield></td>
		    <td >民族 </td>
		    <td ><s:textfield name="entity.national" id="national" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >籍贯 </td>
		    <td ><s:textfield name="entity.nativeplace" id="nativeplace" size="10" theme="simple"></s:textfield></td>
		    <td >职称</td>
		    <td ><s:textfield name="entity.title" id="title" size="10" theme="simple"></s:textfield></td>
		    <td >出生年月 </td>
		    <td ><s:textfield name="entity.brithday" id="brithday" size="10" theme="simple"></s:textfield></td>
		    <td >期望薪金 </td>
		    <td ><s:textfield name="entity.salaryexpectation" id="salaryexpectation" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >政治面貌 </td>
		    <td  colspan="2"><s:textfield name="entity.politicallandscape" id="politicallandscape" size="10" theme="simple"></s:textfield></td>
		    <td >身高 </td>
		    <td ><s:textfield name="entity.height" id="height" size="10" theme="simple"></s:textfield></td>
		    <td >体重 </td>
		    <td  colspan="2"><s:textfield name="entity.weight" id="weight" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >毕业时间 </td>
		    <td  colspan="2"><s:textfield name="entity.graduationtime" id="graduationtime" size="12" theme="simple"></s:textfield></td>
		    <td >专业 </td>
		    <td ><s:textfield name="entity.professional" id="professional" size="15" theme="simple"></s:textfield></td>
		    <td >工作年限 </td>
		    <td colspan="2"><s:textfield name="entity.workyear" id="workyear" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >最高学历 </td>
		    <td colspan="2"><s:textfield name="entity.education" id="education" size="12" theme="simple"></s:textfield></td>
		    <td >户口所在地 </td>
		    <td ><s:textfield name="entity.residence" id="residence" size="15" theme="simple"></s:textfield></td>
		    <td >婚姻状况 </td>
		    <td colspan="2"><s:textfield name="entity.marriage" id="marriage" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >家庭住址 </td>
		    <td colspan="4"><s:textfield name="entity.address" id="address" size="20" theme="simple"></s:textfield></td>
		    <td >身份证号 </td>
		    <td colspan="2"><s:textfield name="entity.cardid" id="cardid" onblur="CodeValid();" size="30" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >健康状况 </td>
		    <td colspan="2"><s:textfield name="entity.health" id="health" size="12" theme="simple"></s:textfield></td>
		    <td >有无病史 </td>
		    <td colspan="2"><s:textfield name="entity.medical" id="medical" size="15" theme="simple"></s:textfield></td>
		    <td >联系电话 </td>
		    <td ><s:textfield name="entity.phone" id="phone" size="15" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td rowspan="4">教育<br>与<br>培训 </td>
		    <td >起止时间 </td>
		    <td colspan="2">学校或其它教育机构 </td>
		    <td >专业 </td>
		    <td >学历/证书 </td>
		    <td colspan="2">证明人 </td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.educationtimeone" id="educationtimeone" size="10" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.educationschoolone" id="educationschoolone" size="20" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.educationprofessionalone" id="educationprofessionalone" size="15" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.educationrecordone" id="educationrecordone" size="15" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.educationproveone" id="educationproveone" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.educationtimetwo" id="educationtimetwo" size="10" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.educationschooltwo" id="educationschooltwo" size="20" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.educationprofessionaltwo" id="educationprofessionaltwo" size="15" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.educationrecordtwo" id="educationrecordtwo" size="15" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.educationprovetwo" id="educationprovetwo" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.educationtimethree" id="educationtimethree" size="10" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.educationschoolthree" id="educationschoolthree" size="20" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.educationprofessionalthree" id="educationprofessionalthree" size="15" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.educationrecordthree" id="educationrecordthree" size="15" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.educationprovethree" id="educationprovethree" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td rowspan="4">主要<br>工作<br>经历 </td>
		    <td >起止时间 </td>
		    <td >工作单位 </td>
		    <td >职务 </td>
		    <td >月薪 </td>
		    <td >离职原因 </td>
		    <td >证明人 </td>
		    <td >联系电话 </td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.worktimeone" id="worktimeone" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workunitone" id="workunitone" size="15" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workpositionone" id="workpositionone" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.worksalaryone" id="worksalaryone" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workdepartureone" id="workdepartureone" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workrecordone" id="workrecordone" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workphoneone" id="workphoneone" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.worktimetwo" id="worktimetwo" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workunittwo" id="workunittwo" size="15" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workpositiontwo" id="workpositiontwo" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.worksalarytwo" id="worksalarytwo" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workdeparturetwo" id="workdeparturetwo" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workrecordtwo" id="workrecordtwo" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workphonetwo" id="workphonetwo" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.worktimethree" id="worktimethree" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workunitthree" id="workunitthree" size="15" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workpositionthree" id="workpositionthree" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.worksalarythree" id="worksalarythree" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workdeparturethree" id="workdeparturethree" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workrecordthree" id="workrecordthree" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.workphonethree" id="workphonethree" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >个人<br>能力<br>描述 </td>
		    <td colspan="7">
		    <s:textarea name="entity.remark" id="remark" rows="3" cols="60" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td rowspan="4">家庭<br>成员<br>情况 </td>
		    <td >姓名</td>
		    <td >与本人关系</td>
		    <td colspan="3">工作单位及职务</td>
		    <td colspan="2">联系电话 </td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.familynameone" id="familynameone" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.familyrelationshipone" id="familyrelationshipone" size="15" theme="simple"></s:textfield></td>
		    <td colspan="3"><s:textfield name="entity.familyunitone" id="familyunitone" size="20" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.familyphoneone" id="familyphoneone" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.familynametwo" id="familynametwo" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.familyrelationshiptwo" id="familyrelationshiptwo" size="15" theme="simple"></s:textfield></td>
		    <td colspan="3"><s:textfield name="entity.familyunittwo" id="familyunittwo" size="20" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.familyphonetwo" id="familyphonetwo" size="10" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td ><s:textfield name="entity.familynamethree" id="familynamethree" size="10" theme="simple"></s:textfield></td>
		    <td ><s:textfield name="entity.familyrelationshipthree" id="familyrelationshipthree" size="15" theme="simple"></s:textfield></td>
		    <td colspan="3"><s:textfield name="entity.familyunitthree" id="familyunitthree" size="20" theme="simple"></s:textfield></td>
		    <td colspan="2"><s:textfield name="entity.familyphonethree" id="familyphonethree" size="10" theme="simple"></s:textfield></td>
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
    <!----add结束---> 
<script type="text/javascript">
//设置当前日期
function getNowDate(contorl) {
	var v_contorl = document.getElementById(contorl);
	if(v_contorl.value==''){
		var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mi= date.getMinutes();
		var s = date.getSeconds();
		v_contorl.value = y + "-" + (m > 9 ? m : ('0' + m)) + "-" + (d > 9 ? d : ('0' + d))+ " " + (h > 9 ? h : ('0' + h))+ ":" + (mi > 9 ? mi : ('0' + mi))+ ":" + (s > 9 ? s : ('0' + s));
	}
}
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
<s:if test="entity==null">
<script type="text/javascript">
getNowDate("recordtime");
</script>
</s:if>
</body>
</html>
