<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>请假申请</p> 
            </div>
          
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >管理处 <font color="red">*</font></td>
		    <td ><s:property value="entity.areaName"></s:property></td> 
		  </tr>
		  <tr>
		    <td >姓名 <font color="red">*</font></td>
		    <td ><s:property value="entity.name"></s:property></td> 
		  </tr>
		  <tr>
		    <td >岗位职位 </td>
		    <td ><s:property value="entity.position"></s:property></td>
		  </tr>
		  <tr>
		    <td >所在单位或部门 </td>
		    <td ><s:property value="entity.departmentname"></s:property></td>
		  </tr>
		  <tr>
		    <td >假别 </td>
		    <td ><s:checkbox name="entity.typeone" id="typeone" disabled="true" >事假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typetwo" id="typetwo" disabled="true" >婚假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typethree" id="typethree" disabled="true" >病假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typefour" id="typefour" disabled="true" >丧假</s:checkbox>&nbsp;&nbsp;
		    <br>
		    <s:checkbox name="entity.typefive" id="typefive" disabled="true" >产假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typesix" id="typesix" disabled="true" >年休假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typeseven" id="typeseven" disabled="true" >工伤假</s:checkbox>&nbsp;&nbsp;
		    <s:checkbox name="entity.typeeight" id="typeeight" disabled="true" >调休</s:checkbox>&nbsp;&nbsp;</td>
		  </tr>
		  <tr>
		    <td >时间： </td>
		    <td ><s:property value="entity.begindate.toString().substring(0,10)"></s:property>
		    	至<s:property value="entity.enddate.toString().substring(0,10)"></s:property>
		    	<br><br>
		    	共计<s:property value="entity.number"></s:property>天
		    	</td>
		  </tr>
		  <tr>
		    <td >原因 </td>
		    <td ><s:property value="entity.reason"></s:property></td>
		  </tr>
		  <tr>
		    <td >紧急联络电话 </td>
		    <td ><s:property value="entity.phone"></s:property></td>
		  </tr>
		  <tr>
		    <td >教育背景（专业 年限） </td>
		    <td ><s:property value="entity.address"></s:property></td>
		  </tr>
		   <tr>
		    <td >申请人： </td>
		    <td ><s:property value="entity.applyname"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	日期：<s:property value="entity.applydate.toString().substring(0,10)"></s:property></td>
		  </tr>
		  <tr>
		    <td >审核人： </td>
		    <td ><s:property value="entity.auditname"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	日期：<s:property value="entity.auditdate.toString().substring(0,10)"></s:property></td>
		  </tr>
		  <tr>
		    <td >批准人 ：</td>
		    <td ><s:property value="entity.approvalname"></s:property>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	日期：<s:property value="entity.approvaldate.toString().substring(0,10)"></s:property></td>
		  </tr>
		  <tr>
		    <td >工作项目交接</td>
		    <td ><s:property value="entity.content"></s:property></td>
		  </tr>
 
			</table>
      
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
