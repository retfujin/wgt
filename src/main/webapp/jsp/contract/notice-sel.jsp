<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>培训通知</p> 
            </div>            
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td height="28px">培训主题 </td>
		    <td ><s:property value="entity.title"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td height="28px">时间 </td>
		    <td ><s:property value="entity.begintime"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td height="28px">地点 </td>
		    <td ><s:property value="entity.address"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td height="28px">教师 </td>
		    <td ><s:property value="entity.teacher"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td height="28px">教材 </td>
		    <td ><s:property value="entity.material"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td height="28px">考试/考核方法 </td>
		    <td ><s:property value="entity.method"></s:property>&nbsp;</td> 
		  </tr>
		  <tr>
		  	<td >参加人员</td>
		    <td ><s:property value="entity.usernames"></s:property>&nbsp;</td> 
		  </tr>
		</table>
 
        </div>
    </div>
  
</body>
</html>
