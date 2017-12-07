<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>劳动合同续签考核结果</p> 
            </div>
            
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >姓名 </td>
		    <td ><s:property value="entity.name"></s:property>&nbsp;</td> 
		    <td >年龄 </td>
		    <td ><s:property value="entity.age"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		  	<td >性别</td>
		    <td ><s:property value="entity.sex"></s:property>&nbsp;</td>
		  	<td >学历 </td>
		    <td ><s:property value="entity.education"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >部门 </td>
		    <td ><s:property value="entity.departmentname"></s:property>&nbsp;</td> 
		  	<td >岗位 </td>
		    <td ><s:property value="entity.position"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >部门意见</td>
		    <td ><s:property value="entity.departopinion" ></s:property>&nbsp;
		    	<br>签名：<s:property value="entity.departname"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.departdate"/>
		    </td>
		  	<td >部门意见</td>
		    <td ><s:property value="entity.departoneopinion" ></s:property>&nbsp;
		    	<br>签名：<s:property value="entity.departonename"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.departonedate"/></td> 
		  </tr>
		  <tr>
		  	<td >综合部考核意见</td>
		    <td colspan="3"><s:property value="entity.officeopinion"></s:property>&nbsp;
			<br>签名：<s:property value="entity.offficename"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.officedate"/></td> 
		  </tr>
		  <tr>
		  	<td >分管领导评价</td>
		    <td colspan="3"><s:property value="entity.viceopinion" ></s:property>&nbsp;
			<br>签名：<s:property value="entity.vicename"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.vicedate"/></td> 
		  </tr>
		  <tr>
		  	<td >总经理办公会意见</td>
		    <td colspan="3"><s:property value="entity.manageropinion" ></s:property>&nbsp;
			<br>签名：<s:property value="entity.managername"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.managerdate"/></td>  
		  </tr>
		   
		  <tr>
		  	<td >考核结果</td>
		    <td colspan="3"><s:checkbox name="entity.schedule" id="schedule" disabled="true" theme="simple">按期续签合同</s:checkbox>
		    	&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="entity.termination" id="termination" disabled="true" theme="simple">终止劳动合同</s:checkbox></td> 
		  </tr>
		  <tr>
		  	<td >总经理批准</td>
		    <td colspan="3"><s:property value="entity.manager_approval" ></s:property>
			<br>签名：<s:property value="entity.manager_name"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.manager_date"/></td>  
		  </tr>
			</table> 
        </div>
    </div>
  
</body>
</html>
