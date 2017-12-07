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
               <p>员工离职登记</p> 
            </div>
          <form id="ff" method="post" action="departure!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.areaId"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td width="15%">管理处 </td>
		    <td colspan="3"><s:property value="entity.areaName"/> </td>
     	  </tr>
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield> </td>
		    <td >部门 </td>
		    <td ><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>	
		  <tr>
		    <td >到职日</td>
		    <td ><s:textfield name="entity.workdate" id="workdate" readonly="true" theme="simple"></s:textfield> </td>
		    <td >离职日 </td>
		    <td ><s:textfield name="entity.enddate" id="enddate" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >离职原因</td>
		    <td colspan="3">
		    	<s:textarea name="entity.reason" cols="60" rows="3" id="reason" readonly="true" theme="simple"></s:textarea>
		     </td>
		  </tr>
		  <tr>
		    <td >约谈内容</td>
		    <td >部门负责人</td>
		    <td colspan="2">
		    	<s:hidden name="entity.contentid" id="contentid"></s:hidden>
		     	<s:if test="entity.content!=null && entity.content!=''">
		     	<s:textarea name="entity.content" cols="30" rows="3" id="content" readonly="true" theme="simple"></s:textarea>
		     	</s:if><s:else>
		     	<s:textarea name="content" cols="30" rows="3" id="content" onclick="goSetUser('contentid','content')" readonly="true" theme="simple"></s:textarea>
		     	</s:else>
		     </td>
		  </tr>
		  <tr>
		    <td >部门意见</td>
		    <td ><s:textarea name="entity.departopinion" cols="20" rows="3" id="departopinion" readonly="true"  theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departid" id="departid"></s:hidden>
		    	审核人
		    	<s:if test="entity.departname!=null && entity.departname!=''">
		    	<s:textfield name="entity.departname" id="departname" size="10" readonly="true"  theme="simple"></s:textfield>
		    	</s:if><s:else>
		     	<s:textfield name="departname" id="departname" size="10" onclick="goSetUser('departid','departname')" readonly="true" theme="simple"></s:textfield>
		     	</s:else>
		    	<br>审核日期<s:textfield name="entity.departdate" id="departdate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		  	<td colspan="2"><s:textarea name="entity.departoneopinion" cols="20" rows="3" id="departoneopinion" readonly="true"  theme="simple"></s:textarea>
		    <br><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		    	审核人
		    	<s:if test="entity.departonename!=null && entity.departonename!=''">
		    	<s:textfield name="entity.departonename" id="departonename" size="10" readonly="true"  theme="simple"></s:textfield>
		    	</s:if><s:else>
		     	<s:textfield name="departonename" id="departonename" size="10" onclick="goSetUser('departoneid','departonename')" readonly="true" theme="simple"></s:textfield>
		     	</s:else>
		    	<br>审核日期<s:textfield name="entity.departonedate" id="departonedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		  </tr>
		  <tr>
		  <td >综合部意见</td>
		  	<td colspan="3"><s:textarea name="entity.officeopinion" cols="20" rows="3" id="officeopinion" readonly="true"  theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.officeid" id="officeid"></s:hidden>
		    	审核人
		    	<s:if test="entity.officename!=null && entity.officename!=''">
		    	<s:textfield name="entity.officename" id="officename" size="10" readonly="true"  theme="simple"></s:textfield>
		    	</s:if><s:else>
		     	<s:textfield name="officename" id="officename" size="10" onclick="goSetUser('officeid','officename')" readonly="true"  theme="simple"></s:textfield>
		     	</s:else>
		    	<br>审核日期<s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" theme="simple"></s:textfield>
		  	</td>
		  </tr>
		  <tr>
		    <td >分管副总</td>
		    <td ><s:textarea name="entity.viceopinion" cols="20" rows="3" id="viceopinion" readonly="true"  theme="simple"></s:textarea>
		    <br><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		    	审核人<s:if test="entity.vicename!=null && entity.vicename!=''">
		    	<s:textfield name="entity.vicename" id="vicename" size="10" readonly="true"  theme="simple"></s:textfield>
		    	</s:if><s:else>
		    	<s:textfield name="vicename" id="vicename" onclick="goSetUser('viceid','vicename')" size="10" readonly="true" theme="simple"></s:textfield>
		    	</s:else>
		    	<br>审核日期<s:textfield name="entity.vicedate" id="vicedate" size="10" readonly="true" theme="simple"></s:textfield>
		    </td>
		  	<td >总经理</td>
		  	<td ><s:textarea name="entity.manageropinion" cols="20" rows="3" id="manageropinion" readonly="true"  theme="simple"></s:textarea>
		  	<br><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		    	审核人<s:if test="entity.managername!=null && entity.managername!=''">
		    	<s:textfield name="entity.managername" id="managername" size="10" readonly="true"  theme="simple"></s:textfield>
		    	</s:if><s:else>
		    	<s:textfield name="managername" id="managername" onclick="goSetUser('managerid','managername')" size="10" readonly="true"  theme="simple"></s:textfield>
		    	</s:else>
		    	<br>审核日期<s:textfield name="entity.managerdate" id="managerdate" size="10" readonly="true" theme="simple"></s:textfield>
		  	</td>
		  </tr>
		  <tr>
		    <td >经营事项</td>
		    <td >接管人审核 </td>
		    <td >主管审核 </td>
		    <td >备注</td>
		  </tr>
		  <tr>
		    <td >交接工作业务</td>
		    <td >
		    <s:hidden name="entity.workoneid" id="workoneid"></s:hidden>
		    <s:textfield name="entity.worknameone" id="worknameone" readonly="true" size="10" theme="simple"></s:textfield> </td>
		    <td ><s:if test="entity.workmanagerone!=null && entity.workmanagerone!=''">
		    <s:textfield name="entity.workmanagerone" id="workmanagerone" readonly="true" size="10" theme="simple"></s:textfield> 
		    </s:if><s:else>
		    	 <s:textfield name="workmanagerone" id="workmanagerone"  onclick="goSetUser('workoneid','workmanagerone')" readonly="true" size="10" theme="simple"></s:textfield> 
		    	</s:else>
		    </td>
		    <td ><s:textfield name="entity.workremarkone" id="workremarkone" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >1、公用物品归还及证件缴回。<br>
				2、薪资结算至   年   月   日 止。 </td>
		    <td ><s:hidden name="entity.worktwoid" id="worktwoid"></s:hidden>
		    <s:textfield name="entity.worknametwo" id="worknametwo"  readonly="true" size="10" theme="simple"></s:textfield> </td>
		    <td ><s:if test="entity.workmanagertwo!=null && entity.workmanagertwo!=''">
		    <s:textfield name="entity.workmanagertwo" id="workmanagertwo" readonly="true" size="10" theme="simple"></s:textfield>
		    </s:if><s:else>
		    	<s:textfield name="workmanagertwo" id="workmanagertwo" onclick="goSetUser('worktwoid','workmanagertwo')" readonly="true" size="10" theme="simple"></s:textfield>
		    	</s:else>
		     </td>
		    <td ><s:textfield name="entity.workremarktwo" id="workremarktwo" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td >1、应领款项。<br>
				2、有无金钱责任。 </td>
			<td ><s:hidden name="entity.workthreeid" id="workthreeid"></s:hidden>
			<s:textfield name="entity.worknamethree" id="worknamethree" readonly="true" size="10" theme="simple"></s:textfield> </td>
		    <td ><s:if test="entity.workmanagerthree!=null && entity.workmanagerthree!=''">
		    <s:textfield name="entity.workmanagerthree" id="workmanagerthree" readonly="true" size="10" theme="simple"></s:textfield>
		    </s:if><s:else>
		    	<s:textfield name="workmanagerthree" id="workmanagerthree" onclick="goSetUser('workthreeid','workmanagerthree')" readonly="true" size="10" theme="simple"></s:textfield>
		    	</s:else>
		     </td>
		    <td ><s:textfield name="entity.workremarkthree" id="workremarkthree" readonly="true" theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		    <td colspan="4">
		    	记录日期<s:textfield name="entity.recordtime" id="recordtime" size="10" readonly="true" theme="simple"></s:textfield>
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