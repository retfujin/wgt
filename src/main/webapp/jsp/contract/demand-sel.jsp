<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
 
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>培训需求申请</p> 
            </div>
            
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td width="20%">填表日期 </td>
		    <td ><s:property value="entity.filldate.toString().substring(0,10)"></s:property> &nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >申请部门 </td>
		    <td ><s:property value="entity.departname" ></s:property>&nbsp; </td> 
		  </tr>
		  <tr>
		  	<td >申请人 </td>
		    <td ><s:property value="entity.applyname"></s:property>&nbsp; </td> 
		  </tr>
		  <tr>
		  	<td >培训方式 </td>
		    <td ><s:property value="entity.way" ></s:property>&nbsp; </td> 
		  </tr>
		  <tr>
		  	<td >时间安排</td>
		    <td ><s:property value="entity.arrange" ></s:property>&nbsp; </td> 
		  </tr>
		  <tr>
		  	<td >培训主要对象 </td>
		    <td ><s:property value="entity.objectname" ></s:property> &nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >申请原因 </td>
		    <td ><s:property value="entity.reason" ></s:property> &nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >申请培训内容 </td>
		    <td height="80px"><s:property value="entity.content" ></s:property> &nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >综合管理部意见</td>
		    <td height="100px"><s:property value="entity.officeopinion" ></s:property>&nbsp;
		    	<br>
		    	签名<s:property value="entity.officename" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	日期<s:property value="entity.officedate" ></s:property>
		    </td> 
		  </tr>
		  <tr>
		  	<td >领导意见</td>
		    <td height="100px"><s:property value="entity.leadershipopinion" ></s:property>&nbsp;
			<br>
				签名<s:property value="entity.leadershipname" ></s:property>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	日期<s:property value="entity.leadershipdate" ></s:property>
			</td> 
		  </tr> 
			</table> 
        </div>
    </div>
  
</body>
</html>
