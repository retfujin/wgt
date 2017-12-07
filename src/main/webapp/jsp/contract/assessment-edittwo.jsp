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
               <p>劳动合同续签考核结果</p> 
            </div>
            <form id="ff" method="post" action="assessment!save.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.areaId"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		   <tr>
		  	<td >管理处 </td>
		    <td colspan="3"><s:property value="entity.areaName"/>  </td>
		  </tr>
		  <tr>
		  	<td >姓名 </td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield> </td> 
		    <td >年龄 </td>
		    <td ><s:textfield name="entity.age" id="age" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		  	<td >性别</td>
		    <td ><s:textfield name="entity.sex" id="sex" readonly="true" theme="simple"></s:textfield> </td>
		  	<td >学历 </td>
		    <td ><s:textfield name="entity.education" id="education" readonly="true" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >部门 </td>
		    <td ><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield> </td> 
		  	<td >岗位 </td>
		    <td ><s:textfield name="entity.position" id="position" readonly="true" theme="simple"></s:textfield> </td> 
		  </tr> 
		  <tr>
		  	<s:if test="entity.departid!=null && entity.departid!='' && entity.departname==null">	
		  	<td >部门意见</td>
		    <td ><s:textarea name="entity.departopinion" id="departopinion" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departid" id="departid"></s:hidden>
		    	签名：<s:textfield name="entity.departname" id="departname" theme="simple"></s:textfield><br>
		    	日期：<s:textfield name="entity.departdate" id="departdate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    </td> 
		    </s:if><s:else>
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departopinion" id="departopinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departid" id="departid"></s:hidden>
		    	签名：<s:textfield name="entity.departname" id="departname" readonly="true" theme="simple"></s:textfield><br>
		    	日期：<s:textfield name="entity.departdate" id="departdate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		    </s:else>
		    <s:if test="entity.departoneid!=null && entity.departoneid!='' && entity.departonename==null">	
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" id="departoneopinion" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		    	签名：<s:textfield name="entity.departonename" id="departonename" theme="simple"></s:textfield><br>
		    	日期：<s:textfield name="entity.departonedate" id="departonedate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		    </s:if><s:else>
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" id="departoneopinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		    	签名：<s:textfield name="entity.departonename" id="departonename" readonly="true" theme="simple"></s:textfield><br>
		    	日期：<s:textfield name="entity.departonedate" id="departonedate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		    </s:else>
		  </tr>
		  <s:if test="entity.officeid!=null && entity.officeid!='' && entity.officename==null">	
		  <tr>
		  	<td >综合部考核意见</td>
		    <td colspan="3"><s:textarea name="entity.officeopinion" id="officeopinion" cols="40" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.officeid" id="officeid"></s:hidden>
		    	签名：<s:textfield name="entity.officename" id="officename" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.officedate" id="officedate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:if><s:else>
		  <tr>
		  	<td >综合部考核意见</td>
		    <td colspan="3"><s:textarea name="entity.officeopinion" id="officeopinion" readonly="true" cols="40" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.officeid" id="officeid"></s:hidden>
		    	签名：<s:textfield name="entity.officename" id="officename" readonly="true" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.officedate" id="officedate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:else>
		  <s:if test="entity.viceid!=null && entity.viceid!='' && entity.vicename==null">	
		  <tr>
		  	<td >分管领导评价</td>
		    <td colspan="3"><s:textarea name="entity.viceopinion" id="viceopinion" cols="40" rows="3" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		    	签名：<s:textfield name="entity.vicename" id="vicename" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.vicedate" id="vicedate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:if><s:else>
		  <tr>
		  	<td >分管领导评价</td>
		    <td colspan="3"><s:textarea name="entity.viceopinion" id="viceopinion" readonly="true" cols="40" rows="3" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		    	签名：<s:textfield name="entity.vicename" id="vicename" readonly="true" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.vicedate" id="vicedate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:else>
		  <s:if test="entity.managerid!=null && entity.managerid!='' && entity.managername==null">	
		  <tr>
		  	<td >总经理办公会意见</td>
		    <td colspan="3"><s:textarea name="entity.manageropinion" id="manageropinion" cols="40" rows="3" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		    	签名：<s:textfield name="entity.managername" id="managername" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.managerdate" id="managerdate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:if><s:else>
		  <tr>
		  	<td >总经理办公会意见</td>
		    <td colspan="3"><s:textarea name="entity.manageropinion" id="manageropinion" readonly="true" cols="40" rows="3" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		    	签名：<s:textfield name="entity.managername" id="managername" readonly="true" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.managerdate" id="managerdate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:else>
		  <s:if test="entity.mid!=null && entity.mid!='' && entity.mname==null">	 
		  <tr>
		  	<td >考核结果</td>
		    <td colspan="3">
		    <s:checkbox name="entity.schedule" id="schedule" theme="simple">按期续签合同</s:checkbox>
		    	&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="entity.termination" id="termination" theme="simple">终止劳动合同</s:checkbox></td> 
		  </tr>
		  <tr>
		  	<td >总经理批准</td>
		    <td colspan="3"><s:textarea name="entity.mapproval" id="mapproval" cols="40" rows="3" theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.mid" id="mid"></s:hidden>
		    	签名：<s:textfield name="entity.mname" id="mname" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.mdate" id="mdate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:if><s:else>
		  <tr>
		  	<td >考核结果</td>
		    <td colspan="3">
		    <s:hidden name="entity.schedule"></s:hidden>
		    <s:hidden name="entity.termination"></s:hidden>
		    <s:checkbox name="entity.schedule" id="schedule" disabled="true" theme="simple">按期续签合同</s:checkbox>
		    	&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="entity.termination" disabled="true" id="termination" theme="simple">终止劳动合同</s:checkbox></td> 
		  </tr>
		  <tr>
		  	<td >总经理批准</td>
		    <td colspan="3"><s:textarea name="entity.mapproval" id="mapproval" readonly="true" cols="40" rows="3" theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.mid" id="mid"></s:hidden>
		    	签名：<s:textfield name="entity.mname" id="mname" readonly="true" theme="simple"></s:textfield>
		    	日期：<s:textfield name="entity.mdate" id="mdate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:else>
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
    
</body>
</html>
