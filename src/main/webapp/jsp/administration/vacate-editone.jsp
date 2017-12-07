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
function goSetUser(param_a,param_b){
   var rtnValue = window.showModalDialog("apply!choose.action","","dialogHeight:800px;dialogWidth:300px");
   if(rtnValue!=undefined){
       var userIdList = "";
       var userNameList = "";
       if(rtnValue!=null){
           for(var i=0;i<rtnValue.length;i++){
          	   if(userIdList=="") userIdList = rtnValue[i].uid;
               else userIdList += "," + rtnValue[i].uid;
               if(userNameList=="") userNameList = rtnValue[i].uname;
               else userNameList += "," + rtnValue[i].uname;
           }
       }
       document.getElementById(param_a).value = userIdList; 
       document.getElementById(param_b).value = userNameList;
   }
}
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>请假申请</p> 
            </div>
          <form id="ff" method="post" action="vacate!save.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.areaId"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 <font color="red">*</font></td>
		    <td ><s:property value="entity.areaName"></s:property></td> 
		  </tr>
		  <tr>
		    <td >姓名 <font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield></td> 
		  </tr>
		  <tr>
		    <td >岗位职位 </td>
		    <td ><s:textfield name="entity.position" id="position" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >所在单位或部门 </td>
		    <td ><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >假别 </td>
		    <td >
		    <s:hidden name="entity.typeone"></s:hidden><s:hidden name="entity.typetwo"></s:hidden><s:hidden name="entity.typethree"></s:hidden><s:hidden name="entity.typefour"></s:hidden>
		    <s:hidden name="entity.typefive"></s:hidden><s:hidden name="entity.typesix"></s:hidden><s:hidden name="entity.typeseven"></s:hidden><s:hidden name="entity.typeeight"></s:hidden>
		    <s:checkbox name="entity.typeone" id="typeone" disabled="true" theme="simple">事假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typetwo" id="typetwo" disabled="true" theme="simple">婚假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typethree" id="typethree" disabled="true" theme="simple">病假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typefour" id="typefour" disabled="true" theme="simple">丧假</s:checkbox>&nbsp;&nbsp;
		    <br>
		    <s:checkbox name="entity.typefive" id="typefive" disabled="true" theme="simple">产假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typesix" id="typesix" disabled="true" theme="simple">年休假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typeseven" id="typeseven" disabled="true" theme="simple">工伤假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typeeight" id="typeeight" disabled="true" theme="simple">调休</s:checkbox>&nbsp;&nbsp;</td>
		  </tr>
		  <tr>
		    <td >时间 </td>
		    <td ><s:textfield name="entity.begindate" id="begindate" readonly="true" theme="simple"></s:textfield>
		    	至<s:textfield name="entity.enddate" id="enddate" readonly="true" theme="simple"></s:textfield>
		    	<br>
		    	共计<s:textfield name="entity.number" id="number" readonly="true" size="10" theme="simple"></s:textfield>天
		    	</td>
		  </tr>
		  <tr>
		    <td >原因 </td>
		    <td ><s:textarea name="entity.reason" id="reason" readonly="true" cols="40" rows="3" theme="simple"></s:textarea></td>
		  </tr>
		  <tr>
		    <td >紧急联络电话 </td>
		    <td ><s:textfield name="entity.phone" id="phone" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >教育背景（专业 年限） </td>
		    <td ><s:textfield name="entity.address" id="address" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		   <tr>
		    <td >申请人 </td>
		    <td ><s:textfield name="entity.applyname" id="applyname" readonly="true" theme="simple"></s:textfield>
		    	日期<s:textfield name="entity.applydate" id="applydate" readonly="readonly" size="10"  theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >审核人 </td>
		    <td >
		    <s:hidden name="entity.auditid" id="auditid"></s:hidden>
		    <s:if test="entity.auditname!=null && entity.auditname!=''">
		    <s:textfield name="entity.auditname" id="auditname" readonly="true" theme="simple"></s:textfield>
		    </s:if><s:else>
		    <s:textfield name="auditname" id="auditname" size="10" onclick="goSetUser('auditid','auditname')" readonly="true" theme="simple"></s:textfield>
		    </s:else>
		    	日期<s:textfield name="entity.auditdate" id="auditdate" readonly="true" size="10"  theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >批准人 </td>
		    <td ><s:hidden name="entity.approvalid" id="approvalid"></s:hidden>
		    <s:if test="entity.approvalname!=null && entity.approvalname!=''">
		    <s:textfield name="entity.approvalname" id="approvalname" readonly="true" theme="simple"></s:textfield>
		    </s:if><s:else>
		    <s:textfield name="approvalname" id="approvalname" onclick="goSetUser('approvalid','approvalname')" readonly="true" theme="simple"></s:textfield>
		    </s:else>
		    	日期<s:textfield name="entity.approvaldate" id="approvaldate" readonly="true" size="10"  theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		    <td >工作项目交接</td>
		    <td ><s:textarea name="entity.content" id="content" cols="40" readonly="true" rows="3" theme="simple"></s:textarea></td>
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
  
		  
</body>
</html>
