<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>劳动合同续签申请</p> 
            </div>
             
		  <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >姓名 </td>
		    <td ><s:property value="entity.name"></s:property> </td> 
		    <td >年龄 </td>
		    <td ><s:property value="entity.age"></s:property> </td>
		  </tr>
		  <tr>
		  	<td >性别</td>
		    <td ><s:property value="entity.sex"></s:property> </td>
		  	<td >学历 </td>
		    <td ><s:property value="entity.education"></s:property> </td> 
		  </tr>
		  <tr>
		  	<td >部门 </td>
		    <td ><s:property value="entity.departmentname"></s:property> </td> 
		  	<td >岗位 </td>
		    <td ><s:property value="entity.position"></s:property> </td> 
		  </tr>
		  <tr>
		  	<td rowspan="2">个人续签申请</td>
		  	<td colspan="3">我与公司<s:property value="entity.begindate"></s:property>
		  	所签的《劳动合同》，将于<s:property value="entity.enddate"></s:property>
		  	到期，现本人申请与公司续订固定期限劳动合同。
 			</td> 
		  </tr>
		  <tr>
		  	<td colspan="3">申请日期<s:property value="entity.applydate" /></td>
		  </tr>
		    
			</table>
        </div>
    </div>
  
</body>
</html>
