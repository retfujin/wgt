<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
					var frm = document.getElementById("ff");
					if(frm.name.value=='')
					{
						alert("姓名不能为空");
						return false;
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
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>内部岗位调动登记</p> 
            </div>
          <form id="ff" method="post" action="mobilize!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.areaId"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <s:hidden name="entity.newareaId"></s:hidden>
          <s:hidden name="entity.newareaName"></s:hidden>
          <table class="add_table" cellpadding="2" cellspacing="1" border="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td>管理处</td>
		  	<td><s:property value="entity.areaName"/> </td>
		  	<td>调任管理处</td>
		  	<td><s:property value="entity.newareaName"/> </td>
		  </tr>
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield> </td>
		    <td >调任时间 </td>
		    <td ><s:textfield name="entity.mobilizedate" id="mobilizedate" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >部门 </td>
		    <td ><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield> </td>
		    <td >岗位</td>
		    <td ><s:textfield name="entity.position" id="position" readonly="true" theme="simple"></s:textfield> </td>		   
		  </tr>
		  <tr>
		    <td >调任部门</td>
		    <td ><s:textfield name="entity.newdepartname" id="newdepartname" readonly="true" theme="simple"></s:textfield> </td>
		    <td >调任岗位 </td>
		    <td ><s:textfield name="entity.newposition" id="newposition" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >调岗原因</td>
		    <td colspan="3">
		    	<s:textarea name="entity.reason" cols="60" rows="6" readonly="true" id="reason" theme="simple"></s:textarea>
		     </td>
		  </tr>
		  <tr>
		    <td >工作交接<br>内容事项</td>
		    <td ><s:textarea name="entity.content" cols="30" rows="6" readonly="true" id="content" theme="simple"></s:textarea></td>
		    <td >接收人<br>签字</td>
		    <td ><s:textfield name="entity.recipient" id="recipient"  readonly="true" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.recipientdate" id="recipientdate" size="10" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		  <s:if test="entity.applyid!=null && entity.applyid!='' && entity.applyname==null">
		  <tr>
		    <td >原部门<br>意见</td>
		    <td colspan="3"><s:textarea name="entity.applyopinion" cols="60" rows="3" id="applyopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.applyid"></s:hidden>
		    	审核人<s:textfield name="entity.applyname" id="applyname" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.applydate" id="applydate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    </td>
		  </tr>
		  </s:if><s:else>
		  <tr>
		    <td >原部门<br>意见</td>
		    <td colspan="3"><s:textarea name="entity.applyopinion" cols="60" rows="3" readonly="true" id="applyopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.applyid"></s:hidden>
		    	审核人<s:textfield name="entity.applyname" id="applyname" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.applydate" id="applydate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		  </tr>
		  </s:else>		  
		  
		  <tr>
		  	<s:if test="entity.departid!=null && entity.departid!='' && entity.departname==null">
		    <td >调入部<br>门意见</td>
		    <td ><s:textarea name="entity.departopinion" cols="20" rows="3" id="departopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departid"></s:hidden>
		    	审核人<s:textfield name="entity.departname" id="departname" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departdate" id="departdate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td>
		    </s:if><s:else>
		    <td >调入部<br>门意见</td>
		    <td ><s:textarea name="entity.departopinion" cols="20" rows="3" readonly="true" id="departopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departid"></s:hidden>
		    	审核人<s:textfield name="entity.departname" id="departname" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departdate" id="departdate" size="10" readonly="true"   theme="simple"></s:textfield>
		    </td></s:else>
		    
		    <s:if test="entity.departoneid!=null && entity.departoneid!='' && entity.departonename==null">
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" cols="20" rows="3" id="departoneopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departoneid"></s:hidden>
		    	审核人<s:textfield name="entity.departonename" id="departonename" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departonedate" id="departonedate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"></s:textfield>
		    </td>
		    </s:if><s:else>
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" cols="20" rows="3" readonly="true" id="departoneopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departoneid"></s:hidden>
		    	审核人<s:textfield name="entity.departonename" id="departonename" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.departonedate" id="departonedate" size="10" readonly="true"   theme="simple"></s:textfield>
		    </td></s:else>
		  </tr>
		   
		  <s:if test="entity.officeid!=null && entity.officeid!='' && entity.officename==null">
		  <tr>
		    <td >综合部<br>意见</td>
		    <td colspan="3"><s:textarea name="entity.officeopinion" cols="60" rows="3" id="officeopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.officeid"></s:hidden>
		    	审核人<s:textfield name="entity.officename" id="officename" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    </td>
		  </tr>
		  </s:if><s:else>
		  <tr>
		    <td >综合部<br>意见</td>
		    <td colspan="3"><s:textarea name="entity.officeopinion" cols="60" rows="3" readonly="true" id="officeopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.officeid"></s:hidden>
		    	审核人<s:textfield name="entity.officename" id="officename" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		  </tr>
		  </s:else>	
		  <tr>
		  	<s:if test="entity.viceid!=null && entity.viceid!='' && entity.vicename==null">
		    <td >分管副总</td>
		    <td ><s:textarea name="entity.viceopinion" cols="20" rows="3" id="viceopinion" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid"></s:hidden>
		    	审核人<s:textfield name="entity.vicename" id="vicename" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.vicedate" id="vicedate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		    </td>
		    </s:if><s:else>
		  	<td >分管副总</td>
		    <td ><s:textarea name="entity.viceopinion" cols="20" rows="3" id="viceopinion" readonly="true" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid"></s:hidden>
		    	审核人<s:textfield name="entity.vicename" id="vicename" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.vicedate" id="vicedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		  </s:else>	
		    <s:if test="entity.managerid!=null && entity.managerid!='' && entity.managername==null">
		  	<td >总经理</td>
		  	<td ><s:textarea name="entity.manageropinion" cols="20" rows="3" id="manageropinion" theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.managerid"></s:hidden>
		    	审核人<s:textfield name="entity.managername" id="managername" size="10" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.managerdate" id="managerdate" size="10" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>
		  	</td>
		  	</s:if><s:else>
		  	<td >总经理</td>
		  	<td ><s:textarea name="entity.manageropinion" cols="20" rows="3" readonly="true" id="manageropinion" theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.managerid"></s:hidden>
		    	审核人<s:textfield name="entity.managername" id="managername" size="10" readonly="true" theme="simple"></s:textfield>
		    	审核日期<s:textfield name="entity.managerdate" id="managerdate" size="10" readonly="true" theme="simple"></s:textfield>
		  	</td>
		  	</s:else>	
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
