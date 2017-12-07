<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>员工离职登记</p> 
            </div>
          
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		    <td>姓名</td>
		    <td ><s:property value="entity.name"></s:property> </td>
		    <td >部门 </td>
		    <td ><s:property value="entity.departmentname"></s:property> </td>
		  </tr>	
		  <tr>
		    <td >到职日</td>
		    <td ><s:property value="entity.workdate" ></s:property> </td>
		    <td >离职日 </td>
		    <td ><s:property value="entity.enddate" ></s:property> </td>
		  </tr>
		  <tr>
		    <td >离职原因</td>
		    <td colspan="3">
		    	<s:property value="entity.reason"></s:property>
		     </td>
		  </tr>
		  <tr>
		    <td >约谈内容</td>
		    <td >部门负责人</td>
		    <td colspan="2">
		    	<s:property value="entity.content"></s:property>&nbsp;
		     </td>
		  </tr>
		  <tr>
		    <td >部门意见</td>
		    <td ><s:property value="entity.departopinion" ></s:property>
		    <br><br>
		    	审核人：<s:property value="entity.departname"></s:property>
		    	<br>审核日期：<s:property value="entity.departdate.toString().substring(0,10)" ></s:property>
		    </td>
		    <td colspan="2"><s:property value="entity.departoneopinion" ></s:property>
		    <br><br>
		    	审核人：<s:property value="entity.departonename"></s:property>
		    	<br>审核日期：<s:property value="entity.departonedate.toString().substring(0,10)" ></s:property>
		    </td>
		  </tr>
		  <tr>
		  <td >综合部意见</td>
		  	<td colspan="3"><s:property value="entity.officeopinion"></s:property>
		  	<br><br>
		    	审核人：<s:property value="entity.officename"></s:property>
		    	<br>审核日期：<s:property value="entity.officedate.toString().substring(0,10)"></s:property>
		  	</td>
		  </tr>
		  <tr>
		    <td >分管副总</td>
		    <td ><s:property value="entity.viceopinion"></s:property>
		    <br><br>
		    	审核人：<s:property value="entity.vicename" ></s:property>
		    	<br>审核日期：<s:property value="entity.vicedate.toString().substring(0,10)"></s:property>
		    </td>
		  	<td >总经理</td>
		  	<td ><s:property value="entity.manageropinion"></s:property>
		  	<br><br>
		    	审核人：<s:property value="entity.managername"></s:property>
		    	<br>审核日期：<s:property value="entity.managerdate.toString().substring(0,10)"></s:property>
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
		    <td ><s:property value="entity.worknameone"></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workmanagerone"></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workremarkone"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >1、公用物品归还及证件缴回。<br>
				2、薪资结算至   年   月   日 止。 </td>
		    <td ><s:property value="entity.worknametwo"></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workmanagertwo"></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workremarkone"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >1、应领款项。<br>
				2、有无金钱责任。 </td>
			<td ><s:property value="entity.worknamethree"></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workmanagerthree"></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workremarkone"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td colspan="4">
		    	记录日期<s:property value="entity.recordtime.toString().substring(0,10)"></s:property>
		  	</td>
		  </tr>
		</table> 
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
