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
               <p>员工转正登记</p> 
            </div>
         <form id="ff" method="post" action="positive!save.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.areaId"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td colspan="3"><s:property value="entity.areaName"/> </td>
     	  </tr>
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield> </td>
		    <td >部门 </td>
		    <td ><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		    <td >岗位</td>
		    <td ><s:textfield name="entity.position" id="position" readonly="true" theme="simple"></s:textfield> </td>
		    <td >试用期 </td>
		    <td ><s:textfield name="entity.probationmonth" id="probationmonth" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >试用期工作小结</td>
		    <td colspan="3">
		    	<s:hidden name="entity.worksummaryid"></s:hidden>
		    	<s:if test="entity.worksummaryid!=null && entity.worksummaryid!='' && entity.worksummaryname==null">
		    	<s:textarea name="entity.worksummary" cols="60" rows="6" id="worksummary" theme="simple"></s:textarea>
		    	<br>
		    	签字<s:textfield name="entity.worksummaryname" id="worksummaryname" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.worksummarydate" id="worksummarydate" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		     	</s:if><s:else>
		     	<s:textarea name="entity.worksummary" cols="60" rows="6" id="worksummary" readonly="true" theme="simple"></s:textarea>
		    	<br>
		    	签字<s:textfield name="entity.worksummaryname" id="worksummaryname" size="10" readonly="true" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.worksummarydate" id="worksummarydate" size="10" readonly="true" theme="simple"></s:textfield>
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
</body>
</html>
