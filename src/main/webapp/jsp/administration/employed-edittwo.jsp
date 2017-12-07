<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<html>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#ff').form({
		onSubmit: function(){					 
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
               <p>员工录用登记 </p> 
            </div>
          <form id="ff" method="post" action="employed!save.action" name="frmAdd" > 
           <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <s:hidden name="entity.areaId" />
          <s:hidden name="entity.applyeo" />
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td ><s:property value="entity.areaName" /></td>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield> </td>
     	  </tr>	
		 <tr>
		    <td >性别 </td>
		    <td ><s:radio name="entity.sex" id="sex" disabled="true" list="#{'男':'男','女':'女'}" theme="simple"></s:radio>
		    	<s:hidden name="entity.sex"></s:hidden>
		     </td>
		    <td >年龄</td>
		    <td ><s:textfield name="entity.age" id="age" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		  	<td >学历 </td>
		    <td ><s:textfield name="entity.education" id="education" readonly="true" theme="simple"></s:textfield> </td>
		    <td >专业</td>
		    <td ><s:textfield name="entity.professional" id="professional" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>		 
		  <tr>
		    <td >职称</td>
		    <td ><s:textfield name="entity.title" id="title" readonly="true" theme="simple"></s:textfield></td>
		    <td >应聘岗位</td>
		    <td colspan="3"><s:textfield name="entity.position" id="position" readonly="true" theme="simple"></s:textfield> </td>
		  </tr> 
		  <tr>
			<td >入职资格审核</td>
			<td colspan="3">
			<s:if test="entity.applyid!=null && entity.applyid!='' && entity.applyname==null">
				<s:checkbox name="entity.resume" id="resume" theme="simple">个人简历</s:checkbox>
			    <s:checkbox name="entity.graduation" id="graduation" theme="simple">毕业证</s:checkbox>
			    <s:checkbox name="entity.protitle" id="protitle" theme="simple">专业资格/职称</s:checkbox>
			    <s:checkbox name="entity.cardid" id="cardid" theme="simple">身份证</s:checkbox><br>
			    <s:checkbox name="entity.guaranty" id="guaranty" theme="simple">担保书</s:checkbox>
			    <s:checkbox name="entity.medical" id="medical" theme="simple">体检报告</s:checkbox><br>
			    <s:checkbox name="entity.beginmoney" id="beginmoney" theme="simple">起薪时间</s:checkbox>
			    <s:textfield name="entity.begintime" id="begintime" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield><br>
			    <s:checkbox name="entity.probationperiod" id="probationperiod" theme="simple">试用期</s:checkbox>
			    <s:textfield name="entity.probationmonth" id="probationmonth" size="10" theme="simple"></s:textfield>个月
			    <br>
			    <s:textarea name="entity.applyopinion" cols="60" rows="3" id="applyopinion" theme="simple"></s:textarea>
			    <br><s:hidden name="entity.applyid" id="applyid"></s:hidden>
			    审核人<s:textfield name="entity.applyname" id="applyname" size="10" theme="simple"></s:textfield>
			    审核日期<s:textfield name="entity.applydate" id="applydate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
			   </s:if><s:else>
			   <s:hidden name="entity.resume"></s:hidden><s:hidden name="entity.graduation"></s:hidden><s:hidden name="entity.protitle"></s:hidden> 
			    <s:hidden name="entity.cardid"></s:hidden><s:hidden name="entity.guaranty"></s:hidden><s:hidden name="entity.medical"></s:hidden> 
			    <s:hidden name="entity.beginmoney"></s:hidden><s:hidden name="entity.probationperiod"></s:hidden>
			    <s:checkbox name="entity.resume" id="resume" disabled="true" theme="simple">个人简历</s:checkbox>
			    <s:checkbox name="entity.graduation" id="graduation" disabled="true" theme="simple">毕业证</s:checkbox>
			    <s:checkbox name="entity.protitle" id="protitle" disabled="true" theme="simple">专业资格/职称</s:checkbox>
			    <s:checkbox name="entity.cardid" id="cardid" disabled="true" theme="simple">身份证</s:checkbox><br>
			    <s:checkbox name="entity.guaranty" id="guaranty" disabled="true" theme="simple">担保书</s:checkbox>
			    <s:checkbox name="entity.medical" id="medical" disabled="true" theme="simple">体检报告</s:checkbox><br>
			    <s:checkbox name="entity.beginmoney" id="beginmoney" disabled="true" theme="simple">起薪时间</s:checkbox>
			    <s:textfield name="entity.begintime" id="begintime"  readonly="true" theme="simple"></s:textfield><br>
			    <s:checkbox name="entity.probationperiod" id="probationperiod" disabled="true" theme="simple">试用期</s:checkbox>
			    <s:textfield name="entity.probationmonth" id="probationmonth" size="10" readonly="true" theme="simple"></s:textfield>个月
			    <br>
			    <s:textarea name="entity.applyopinion" cols="60" rows="3"  readonly="true" id="applyopinion" theme="simple"></s:textarea>
			    <br><s:hidden name="entity.applyid" id="applyid"></s:hidden>
			    审核人<s:textfield name="entity.applyname" id="applyname" readonly="true" size="10" theme="simple"></s:textfield>
			    审核日期<s:textfield name="entity.applydate" id="applydate" size="10" readonly="true" theme="simple"></s:textfield>
			   </s:else>	
			  </td>
			  </tr>
		  <tr>
		    <td rowspan="2">用人部门意见</td>
		  	<td colspan="3">
		  	<s:if test="entity.departid!=null && entity.departid!='' && entity.departname==null">
		  	<s:textarea name="entity.departopinion" cols="60" rows="3" id="departopinion" theme="simple"></s:textarea>
			<br><s:hidden name="entity.departid" id="departid"></s:hidden>
			  	签字<s:textfield name="entity.departname" id="departname" size="10"  theme="simple"></s:textfield>
			  	审核日期<s:textfield name="entity.departdate" id="departdate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		    </s:if><s:else>
		    <s:textarea name="entity.departopinion" cols="60" rows="3" id="departopinion" readonly="true" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departid" id="departid"></s:hidden>
		    	签字<s:textfield name="entity.departname" id="departname" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departdate" id="departdate" size="10"  readonly="true" theme="simple"></s:textfield>
		    </s:else>
		    </td>
		  </tr>
 		<tr>
		  	<td colspan="3">
		  	<s:if test="entity.departoneid!=null && entity.departoneid!='' && entity.departonename==null">	
		  	<s:textarea name="entity.departoneopinion" cols="60" rows="3" id="departoneopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		    	签字<s:textfield name="entity.departonename" id="departonename" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departonedate" id="departonedate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		    </s:if><s:else>
		    <s:textarea name="entity.departoneopinion" cols="60" rows="3" id="departoneopinion" readonly="true" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		    	签字<s:textfield name="entity.departonename" id="departonename" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departonedate" id="departonedate" size="10"  readonly="true" theme="simple"></s:textfield>		  
		  	</s:else>
		    </td>
		  </tr>
		  <tr>
		    <td >综合部意见</td>
		    <td colspan="3">工资标准及福利待遇<br>
		    <s:if test="entity.officeid!=null && entity.officeid!='' && entity.officename==null">
		    <s:textarea name="entity.officeopinion" cols="60" rows="3" id="officeopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.officeid" id="officeid"></s:hidden>
		    	签字<s:textfield name="entity.officename" id="officename" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.officedate" id="officedate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		    </s:if><s:else>
		    <s:textarea name="entity.officeopinion" cols="60" rows="3" id="officeopinion" readonly="true" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.officeid" id="officeid"></s:hidden>
		    	签字<s:textfield name="entity.officename" id="officename" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </s:else>
		    </td>
		  </tr>	  	
		  <tr>
		    <td >分管副总</td>
		    <td >
		    <s:if test="entity.viceid!=null && entity.viceid!='' && entity.vicename==null">
		    <s:textarea name="entity.viceopinion" cols="20" rows="3" id="viceopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		    	签字 <s:textfield name="entity.vicename" id="vicename" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.vicedate" id="vicedate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		    </s:if><s:else>
		    	<s:textarea name="entity.viceopinion" cols="20" rows="3" id="viceopinion" readonly="true" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		    	签字 <s:textfield name="entity.vicename" id="vicename" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.vicedate" id="vicedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </s:else>	
		    </td> 
		    <td >总经理</td>
		  	<td >
		  	<s:if test="entity.managerid!=null && entity.managerid!='' && entity.managername==null">
		  	<s:textarea name="entity.manageropinion" cols="20" rows="3" id="manageropinion" theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		    	签字<s:textfield  name="entity.managername" id="managername" size="10" />
		    	审核日期<s:textfield name="entity.managerdate" id="managerdate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>	
		  	</s:if>
		  	<s:else>
		  	<s:textarea name="entity.manageropinion" cols="20" rows="3" readonly="true" id="manageropinion" theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		    	签字<s:textfield  name="entity.managername" id="managername" readonly="true" size="10" />
		    	审核日期<s:textfield name="entity.managerdate" id="managerdate" size="10" readonly="true" theme="simple"></s:textfield>
		  	</s:else>
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
    <!----add结束---> 
</body>
</html>