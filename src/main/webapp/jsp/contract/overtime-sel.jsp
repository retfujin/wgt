<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>加班单</p> 
            </div>
            
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >部门 </td>
		    <td ><s:property value="entity.departmentname"></s:property>&nbsp;</td> 
		  	<td >填表人 </td>
		    <td ><s:property value="entity.name"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		  	<td >加班事由 </td>
		    <td ><s:property value="entity.reason"></s:property>&nbsp;</td> 
		    <td>填表时间</td>
		    <td><s:property value="entity.filldate.toString().substring(0,10)"/> </td> 
		  </tr>
		  <tr>
		  	<td >加班时限 </td>
		    <td ><s:property value="entity.begintime.toString().substring(0,10)"></s:property>至<s:property value="entity.endtime.toString().substring(0,10)"></s:property>&nbsp;</td> 
		  	<td >加班总天数</td>
		    <td ><s:property value="entity.num"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		  	<td >加班人姓名 </td>
		    <td colspan="2">具体加班时间（自月日时至月日时）</td>
		    <td >具体工作</td>
		  </tr>
		  <tr>
		  	<td ><s:property value="entity.worknameone"/> </td>
		    <td colspan="2"><s:property value="entity.worktimeone"/> </td>
		    <td ><s:property value="entity.workone"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:property value="entity.worknametwo"/> </td>
		    <td colspan="2"><s:property value="entity.worktimetwo"/> </td>
		    <td ><s:property value="entity.worktwo"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:property value="entity.worknamethree"/> </td>
		    <td colspan="2"><s:property value="entity.worktimethree"/> </td>
		    <td ><s:property value="entity.workthree"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:property value="entity.worknamefour"/> </td>
		    <td colspan="2"><s:property value="entity.worktimefour"/> </td>
		    <td ><s:property value="entity.workfour"/> </td>
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
		  	<td >分管领导意见</td>
		    <td><s:property value="entity.viceopinion" ></s:property>&nbsp;
			<br>签名：<s:property value="entity.vicename"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.vicedate"/></td> 
		  	<td >总经理意见</td>
		    <td colspan="3"><s:property value="entity.manageropinion" ></s:property>&nbsp;
			<br>签名：<s:property value="entity.managername"/> &nbsp;&nbsp;&nbsp;&nbsp;日期：<s:property value="entity.managerdate"/></td>  
		  </tr>
		</table> 
        </div>
    </div>
  
</body>
</html>
