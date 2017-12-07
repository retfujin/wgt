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
               <p>员工录用登记</p> 
            </div>
          <form id="ff" method="post" name="frmAdd" > 
          
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 </td>
		    <td ><s:property value="entity.areaName" /></td>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:property value="entity.name" /> </td>
     	  </tr>	
		 <tr>
		    <td >性别 </td>
		    <td ><s:property value="entity.sex"></s:property>&nbsp;</td>
		    <td >年龄</td>
		    <td ><s:property value="entity.age" ></s:property>&nbsp;</td>
		  </tr>	
		  <tr>
		  	<td >学历 </td>
		    <td ><s:property value="entity.education" ></s:property>&nbsp;</td>
		    <td >专业</td>
		    <td ><s:property value="entity.professional" ></s:property>&nbsp;</td>
		  </tr>		 
		  <tr>
		    <td >职称</td>
		    <td ><s:property value="entity.title" ></s:property>&nbsp;</td>
		    <td >应聘岗位</td>
		    <td ><s:property value="entity.position" ></s:property>&nbsp;</td>
		  </tr> 
		  <tr>
		    <td >入职资格审核</td>
		    <td colspan="3">
		    	<s:checkbox name="entity.resume" id="resume" disabled="true" theme="simple">个人简历</s:checkbox>
		    	<s:checkbox name="entity.graduation" id="graduation" disabled="true" theme="simple">毕业证</s:checkbox>
		    	<s:checkbox name="entity.protitle" id="protitle" disabled="true" theme="simple">专业资格/职称</s:checkbox>
		    	<s:checkbox name="entity.cardid" id="cardid" disabled="true" theme="simple">身份证</s:checkbox><br>
		    	<s:checkbox name="entity.guaranty" id="guaranty" disabled="true" theme="simple">担保书</s:checkbox>
		    	<s:checkbox name="entity.medical" id="medical" disabled="true" theme="simple">体检报告</s:checkbox><br>
		    	<s:checkbox name="entity.beginmoney" id="beginmoney" disabled="true" theme="simple">起薪时间</s:checkbox>
		    	<s:property value="entity.begintime.toString().substring(0,10)" ></s:property><br>
		    	<s:checkbox name="entity.probationperiod" id="probationperiod" disabled="true" theme="simple">试用期</s:checkbox>
		    	<s:property value="entity.probationmonth" ></s:property>个月
		    	<br>
		    	<s:property value="entity.applyopinion" />
		    	<br><br>
		    	审核人：<s:property value="entity.applyname" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.applydate.toString().substring(0,10)" ></s:property>
		     </td>
		  </tr>
		  <tr>
		    <td >用人部门意见</td>
		    <td colspan="3"><s:property value="entity.departopinion"  ></s:property>
		    <br><br>
		    	签字：<s:property value="entity.departname" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.departdate.toString().substring(0,10)" ></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >综合部意见</td>
		    <td colspan="3">工资标准及福利待遇<br>
		    <s:property value="entity.officeopinion"></s:property>
		    <br><br>
		    	签字：<s:property value="entity.officename" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.officedate.toString().substring(0,10)" ></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >分管副总</td>
		    <td ><s:property value="entity.viceopinion"  ></s:property>
		    <br><br>
		    	签字：<s:property value="entity.vicename" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.vicedate.toString().substring(0,10)"  ></s:property>
		    </td>
		  	<td >总经理</td>
		  	<td ><s:property value="entity.manageropinion" ></s:property>
		  	<br><br>
		    	签字：<s:property value="entity.managername" />&nbsp;&nbsp;&nbsp;&nbsp;
		    	审核日期：<s:property value="entity.managerdate.toString().substring(0,10)"></s:property>
		  	</td>
		  </tr>
		 
			</table>
            </form>
        </div>
    </div>	  
</body>
</html>
