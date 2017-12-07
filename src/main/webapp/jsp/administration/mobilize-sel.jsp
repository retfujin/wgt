<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>内部岗位调动登记</p> 
            </div>
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td >姓名<font color="red">*</font></td>
		    <td ><s:property value="entity.name"></s:property> </td>
		    <td >部门 </td>
		    <td ><s:property value="entity.departmentname"></s:property> </td>
		  </tr>	
		  <tr>
		    <td >岗位</td>
		    <td ><s:property value="entity.position"></s:property> </td>
		    <td >调任时间 </td>
		    <td ><s:property value="entity.mobilizedate.toString().substring(0,10)"></s:property> </td>
		  </tr>
		  <tr>
		    <td >调任部门</td>
		    <td ><s:property value="entity.newdepartname"></s:property> </td>
		    <td >调任岗位 </td>
		    <td ><s:property value="entity.newposition"></s:property> </td>
		  </tr>
		  <tr>
		    <td >调岗原因</td>
		    <td colspan="3">
		    	<s:property value="entity.reason"></s:property>
		     </td>
		  </tr>
		  <tr>
		    <td >工作交接<br>内容事项</td>
		    <td ><s:property value="entity.content"></s:property></td>
		    <td >接收人<br>签字</td>
		    <td ><s:property value="entity.recipient"></s:property>
		    	日期<s:property value="entity.recipientdate.toString().substring(0,10)"></s:property></td>
		  </tr>
		  <tr>
		    <td >原部门<br>意见</td>
		    <td colspan="3"><s:property value="entity.applyopinion"></s:property>
		    <br>
		    	审核人<s:property value="entity.applyname"></s:property>
		    	审核日期<s:property value="entity.applydate.toString().substring(0,10)"></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >调入部<br>门意见</td>
		    <td ><s:property value="entity.departopinion"></s:property>
		    <br>
		    	审核人<s:property value="entity.departname"></s:property>
		    	审核日期<s:property value="entity.departdate.toString().substring(0,10)"></s:property>
		    </td>
		    <td >部门意见</td>
		    <td ><s:property value="entity.departoneopinion"></s:property>
		    <br>
		    	审核人<s:property value="entity.departonename"></s:property>
		    	审核日期<s:property value="entity.departonedate.toString().substring(0,10)"></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >综合部<br>意见</td>
		    <td colspan="3"><s:property value="entity.officeopinion"></s:property>
		    <br>
		    	审核人<s:property value="entity.officename"></s:property>
		    	审核日期<s:property value="entity.officedate.toString().substring(0,10)"></s:property>
		    </td>
		  </tr>
		  <tr>
		    <td >分管副总</td>
		    <td ><s:property value="entity.viceopinion"></s:property>
		    <br>
		    	审核人<s:property value="entity.vicename"></s:property>
		    	审核日期<s:property value="entity.vicedate.toString().substring(0,10)"></s:property>
		    </td>
		  	<td >总经理</td>
		  	<td ><s:property value="entity.manageropinion"></s:property>
		  	<br>
		    	审核人<s:property value="entity.managername"></s:property>
		    	审核日期<s:property value="entity.managerdate.toString().substring(0,10)"></s:property>
		  	</td>
		  </tr>
			</table>
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
