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
               <p>终止劳动合同书</p> 
            </div>
            <form id="ff" method="post" action="termination!save.action" name="frmAdd" > 
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
		    <td ><s:textfield name="entity.departmentname" id="departmentname"  readonly="true" theme="simple"></s:textfield> </td> 
		  	<td >岗位 </td>
		    <td ><s:textfield name="entity.position" id="position"  readonly="true" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td colspan="4">你与公司<s:textfield name="entity.begindate" id="begindate" size="10" readonly="true" theme="simple"></s:textfield>
		  	所签的《劳动合同》，将于<s:textfield name="entity.enddate" id="enddate" size="10" readonly="true" theme="simple"></s:textfield>
		  	到期，现公司决定不再与你续订劳动合同。
				请在接到本通知后，至综合部填写《员工离职审批单》，并于<s:textfield name="entity.endworkdate" id="endworkdate" size="10" readonly="true" theme="simple"></s:textfield>
				前完成相关工作交接。
 			</td> 
		  </tr>
		   <tr>
		   <s:if test="entity.departid!=null && entity.departid!='' && entity.departname==null">	
		  	<td >部门意见</td>
		    <td ><s:textarea name="entity.departopinion" id="departopinion" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departid"></s:hidden>
		    	部门负责人签字<s:textfield name="entity.departname" id="departname" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.departdate" id="departdate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		   </s:if><s:else>
		   <td >部门意见</td>
		    <td ><s:textarea name="entity.departopinion" id="departopinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departid"></s:hidden>
		    	部门负责人签字<s:textfield name="entity.departname" id="departname" readonly="true" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.departdate" id="departdate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td> 
		   </s:else>
		   <s:if test="entity.departoneid!=null && entity.departoneid!='' && entity.departonename==null">	
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" id="departoneopinion" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departoneid"></s:hidden>
		    	部门负责人签字<s:textfield name="entity.departonename" id="departonename" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.departonedate" id="departonedate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td> 
		 </s:if><s:else>
		   <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" id="departoneopinion"  readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departoneid"></s:hidden>
		    	部门负责人签字<s:textfield name="entity.departonename" id="departonename" readonly="true" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.departonedate" id="departonedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		   </s:else>
		  </tr>
		 
		 <s:if test="entity.officeid!=null && entity.officeid!='' && entity.officename==null">	
		  <tr>
		  	<td >综合部意见</td>
		    <td colspan="3"><s:textarea name="entity.officeopinion" id="officeopinion" cols="40" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.officeid"></s:hidden>
		    	部门负责人签字<s:textfield name="entity.officename" id="officename" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  </s:if><s:else>
		   <tr>
		  	<td >综合部意见</td>
		    <td colspan="3"><s:textarea name="entity.officeopinion" id="officeopinion" readonly="true" cols="40" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.officeid"></s:hidden>
		    	部门负责人签字<s:textfield name="entity.officename" id="officename" readonly="true" size="10" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" theme="simple"></s:textfield>
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