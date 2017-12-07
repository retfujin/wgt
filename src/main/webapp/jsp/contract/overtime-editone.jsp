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
<script type="text/javascript">
function goSetUser(param_a,param_b){
   var rtnValue = window.showModalDialog("demand!choose.action","","dialogHeight:800px;dialogWidth:300px");
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
               <p>劳动合同续签考核结果</p> 
            </div>
            <form id="ff" method="post" action="overtime!save.action" name="frmAdd" > 
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
		  	<td >部门 </td>
		    <td ><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield> </td> 
		  	<td >填表人 </td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield> </td> 
		  </tr>	
		  <tr>
		  	<td >加班事由 </td>
		    <td ><s:textfield name="entity.reason" id="reason" readonly="true" size="35" theme="simple"></s:textfield> </td> 
		    <td >填表时间</td>
		    <td ><s:textfield name="entity.filldate" id="filldate" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		  	<td >加班时限</td>
		    <td ><s:textfield name="entity.begintime" id="begintime" readonly="true" size="10" theme="simple"></s:textfield>至
		    <s:textfield name="entity.endtime" id="endtime" readonly="true" size="10" theme="simple"></s:textfield> </td>
		  	<td >加班总天数 </td>
		    <td ><s:textfield name="entity.num" id="num" readonly="true" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >加班人姓名 </td>
		    <td colspan="2">具体加班时间（自月日时至月日时）</td>
		    <td >具体工作</td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknameone" id="worknameone" readonly="true" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimeone" size="40" readonly="true" id="worktimeone" theme="simple"/> </td>
		    <td ><s:textfield name="entity.workone" id="workone" readonly="true" theme="simple"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknametwo" id="worknametwo" readonly="true" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimetwo" size="40" readonly="true" id="worktimetwo" theme="simple"/> </td>
		    <td ><s:textfield name="entity.worktwo" id="worktwo" readonly="true" theme="simple"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknamethree" id="worknamethree" readonly="true" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimethree" size="40" id="worktimethree" readonly="true" theme="simple"/> </td>
		    <td ><s:textfield name="entity.workthree" id="workthree" readonly="true" theme="simple"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknamefour" id="worknamefour" readonly="true" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimefour" size="40" id="worktimefour" readonly="true" theme="simple"/> </td>
		    <td ><s:textfield name="entity.workfour" id="workfour" readonly="true" theme="simple"/> </td>
		  </tr>	 
		  <tr>
		  	<td >部门意见</td>
		    <td ><s:textarea name="entity.departopinion" id="departopinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departid" id="departid"></s:hidden>
		    	签名：
		    		<s:if test="entity.departname!=null && entity.departname!=''">
		    		<s:textfield name="entity.departname" id="departname" readonly="true" theme="simple"></s:textfield>
		    		</s:if><s:else>
		    		<s:textfield name="departname" id="departname" readonly="true" onclick="goSetUser('departid','departname')"  theme="simple"></s:textfield>
		    		</s:else>
		    		<br>
		    	日期：<s:textfield name="entity.departdate" id="departdate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departoneopinion" id="departoneopinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    	<br><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		    	签名：
		    	<s:if test="entity.departonename!=null && entity.departonename!=''">
		    	<s:textfield name="entity.departonename" id="departonename" readonly="true" theme="simple"></s:textfield>
		    	</s:if><s:else>
		    	<s:textfield name="departonename" id="departonename" readonly="true" onclick="goSetUser('departoneid','departonename')" theme="simple"></s:textfield>
		    	</s:else>
		    	<br>
		    	日期：<s:textfield name="entity.departonedate" id="departonedate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		  </tr>
		  <tr>
		  	<td >分管领导意见</td>
		    <td ><s:textarea name="entity.viceopinion" id="viceopinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		    	签名：<s:if test="entity.vicename!=null && entity.vicename!=''">
		    	<s:textfield name="entity.vicename" id="vicename" readonly="true" theme="simple"></s:textfield>
		    	</s:if><s:else>
		    	<s:textfield name="vicename" id="vicename" readonly="true" onclick="goSetUser('viceid','vicename')" theme="simple"></s:textfield>
		    	</s:else><br>
		    	日期：<s:textfield name="entity.vicedate" id="vicedate" readonly="true" theme="simple"></s:textfield>
		    </td> 
		  	<td >总经理意见</td>
		    <td ><s:textarea name="entity.manageropinion" id="manageropinion" readonly="true" cols="20" rows="3" theme="simple"></s:textarea>
		    <br><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		    	签名：<s:if test="entity.managername!=null && entity.managername!=''">
		    	<s:textfield name="entity.managername" id="managername" readonly="true" theme="simple"></s:textfield>
		    	</s:if><s:else>
		    	<s:textfield name="managername" id="managername" readonly="true" onclick="goSetUser('managerid','managername')" theme="simple"></s:textfield>
		    	</s:else><br>
		    	日期：<s:textfield name="entity.managerdate" id="managerdate" readonly="true" theme="simple"></s:textfield>
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
    
</body>
</html>
