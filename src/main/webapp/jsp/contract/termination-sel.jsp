<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>终止劳动合同书</p> 
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
		  	<td colspan="4">你与公司<s:property value="entity.begindate"></s:property>
		  	所签的《劳动合同》，将于<s:property value="entity.enddate"></s:property>
		  	到期，现公司决定不再与你续订劳动合同。
				请在接到本通知后，至综合部填写《员工离职审批单》，并于<s:property value="entity.endworkdate" ></s:property>
				前完成相关工作交接。
 			</td> 
		  </tr>
		   <tr>
		  	<td >部门意见</td>
		    <td ><s:property value="entity.departopinion" ></s:property>
		    	<br>
		    	部门负责人签字：<s:property value="entity.departname" ></s:property>
		    	日期：<s:property value="entity.departdate" ></s:property>
		    </td> 
		    <td >部门意见</td>
		    <td ><s:property value="entity.departoneopinion" ></s:property>
		    	<br>
		    	部门负责人签字：<s:property value="entity.departonename" ></s:property>
		    	日期：<s:property value="entity.departonedate" ></s:property>
		    </td> 
		  </tr>
		  <tr>
		  	<td >综合部意见</td>
		    <td colspan="3"><s:property value="entity.officeopinion"></s:property>
		    	<br>
		    	部门负责人签字：<s:property value="entity.officename" ></s:property>
		    	日期：<s:property value="entity.officedate"></s:property>
		    </td> 
		  </tr>
		  <tr>
		  	<td >领导意见</td>
		    <td colspan="3"><s:property value="entity.leadershipopinion"></s:property>
			<br>
				签名：<s:property value="entity.leadershipname" ></s:property>
		    	日期：<s:property value="entity.leadershipdate" ></s:property>
			</td> 
		  </tr>
			</table>
        </div>
    </div>
  
</body>
</html>
