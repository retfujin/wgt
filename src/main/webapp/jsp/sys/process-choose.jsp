<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<base target="_self"/>
<title>人员选择</title>
<style type="text/css">
.table{
	WIDTH: 98%; BORDER: #98bac4 1px solid; BACKGROUND-COLOR: #FFFFFF
}
</style>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script language="javascript">
	function user(uid,uname){
	     this.uid = uid;
	     this.uname = uname;
	}
	var userArray = new Array();
	//选择值
	function chooseUser(obj){
		var uid,uname;
	    uid = obj.uid;
	    uname = obj.uname;
	    var userO = new user(uid,uname);
	    userArray.length=0;
	    userArray.push(userO);
	    window.returnValue = userArray;
	    window.close();
	}
</script>
</head>
<body>
&nbsp;
<form id="myForm" method="post" action="process!choose.action" name="myForm">
<table width="90%" border="0" align="center" class="table" >
	<tr>
		<th colspan="6" height="28" align="left" style="border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #98bac4">&nbsp;&nbsp; 
			部门：<s:select id="departmentId" name="departmentId" list="retList" cssStyle="width:80px" listKey="id" listValue="name" theme="simple"></s:select>
			<input type="submit" id="sub1" name="submit" value="查询" class="a"> 
		</th>
	</tr>
	
	<s:iterator value="viewList" status="stuts">
		<s:if test="#stuts.index%6==0"><tr></s:if>
		<td width="15%"><input type="radio" id="s_employee<s:property value='viewList[#stuts.index][0]'/>" name="s_employee" uid="<s:property value="viewList[#stuts.index][0]"/>" uname="<s:property value="viewList[#stuts.index][1]"/>" onclick="chooseUser(this)"><s:property value="viewList[#stuts.index][1]"/></td>
		<s:if test="(#stuts.index+1)%6==0"></tr></s:if>
	</s:iterator>
	<s:if test="viewList.size%6!=0">
		<s:set name="td_space" value="viewList.size%6"></s:set>
		<s:if test="#td_space==1"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></s:if>
		<s:elseif test="#td_space==2"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></s:elseif>
		<s:elseif test="#td_space==3"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></s:elseif>
		<s:elseif test="#td_space==4"><td>&nbsp;</td><td>&nbsp;</td></tr></s:elseif>
		<s:elseif test="#td_space==5"><td>&nbsp;</td></tr></s:elseif>
	</s:if>
</table>
</form>
</body>
</html>