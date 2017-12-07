<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<html>
<base target="_self"/> 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>员工转正登记</p> 
            </div>
          
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td colspan="3"><s:property value="entity.areaName"/> </td>
     	  </tr>
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:property value="entity.name" ></s:property> </td>
		    <td >部门 </td>
		    <td ><s:property value="entity.departmentname" ></s:property> </td>
		  </tr>	
		  <tr>
		    <td >岗位</td>
		    <td ><s:property value="entity.position"></s:property> </td>
		    <td >试用期 </td>
		    <td ><s:property value="entity.probationmonth"></s:property> </td>
		  </tr>
		  <tr>
		    <td >试用期工作小结</td>
		    <td colspan="3">
		    	<s:property value="entity.worksummary"></s:property>
		    	<br><br>
		    	审核人：<s:property value="entity.worksummaryname"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.worksummarydate.toString().substring(0,10)"></s:property>
		     </td>
		  </tr>
		  <tr>
		    <td >部门意见</td>
		    <td colspan="3"><s:property value="entity.departopinion"></s:property>
		    <br><br>
		    	审核人：<s:property value="entity.departname"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.departdate.toString().substring(0,10)"></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >综合部意见</td>
		    <td colspan="3">工资标准及福利待遇<br>
		    <s:property value="entity.officeopinion"></s:property>
		    <br><br>
		    	审核人：<s:property value="entity.officename"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.officedate.toString().substring(0,10)"></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >分管副总</td>
		    <td ><s:property value="entity.viceopinion"></s:property>
		    <br><br>
		    	审核人：<s:property value="entity.vicename"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.vicedate.toString().substring(0,10)"></s:property>
		    </td>
		  	<td >总经理</td>
		  	<td ><s:property value="entity.manageropinion"></s:property>
		  	<br><br>
		    	审核人：<s:property value="entity.managername" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.managerdate.toString().substring(0,10)"></s:property>
		  	</td>
		  </tr>
		  
			</table> 
        </div>
    </div> 
</body>
</html>
