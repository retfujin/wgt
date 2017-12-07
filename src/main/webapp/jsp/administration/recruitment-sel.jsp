<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>招聘申请</p> 
            </div>
         
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处</td>
		    <td ><s:property value="entity.areaId"></s:property></td> 
		  </tr>
		  <tr>
		    <td >用人部门 <font color="red">*</font></td>
		    <td ><s:property value="entity.departmentname"></s:property></td> 
		  </tr>
		  <tr>
		    <td >招聘职位 </td>
		    <td ><s:property value="entity.position"></s:property></td>
		  </tr>
		  <tr>
		    <td >人数 </td>
		    <td ><s:property value="entity.number"></s:property></td>
		  </tr>
		  <tr>
		    <td >面试负责人 </td>
		    <td ><s:property value="entity.pheader"></s:property></td>
		  </tr>
		  <tr>
		    <td >工作地点 </td>
		    <td ><s:property value="entity.address"></s:property></td>
		  </tr>
		  <tr>
		    <td >预定聘用日期 </td>
		    <td ><s:property value="entity.begindate.toString().substring(0,10)"></s:property></td>
		  </tr>
		  <tr>
		    <td >职位描述 </td>
		    <td ><s:property value="entity.remark"></s:property></td>
		  </tr>
		  <tr>
		    <td >此职位发展计划 </td>
		    <td ><s:property value="entity.developplan"></s:property></td>
		  </tr>
		  <tr>
		    <td >教育背景（专业 年限） </td>
		    <td ><s:property value="entity.education"></s:property></td>
		  </tr>
		  <tr>
		    <td >所需经验 技能 </td>
		    <td ><s:property value="entity.experience"></s:property></td>
		  </tr>
		  <tr>
		    <td >其它要求（英语 计算机等） </td>
		    <td ><s:property value="entity.other"></s:property></td>
		  </tr>
		  <tr>
		  <td >用人部门签字 </td>
		    <td ><s:property value="entity.departname"></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >日期 </td>
		    <td ><s:property value="entity.departdate.toString().substring(0,10)"></s:property></td>
		  </tr> 
			</table> 
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
