<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="x-ua-compatible" content="ie=7" />
	<%@ include file="/commons/meta.jsp"%>
	<base target="_self"/>
	 
	<title>选择审核人</title>
    <style>
table{
	border-top:1px solid #ddd;
	border-left:1px solid #ddd;
	vertical-align:middle;
	background:#f0f1f1;
}
tr{
	line-height:40px;
	vertical-align:middle;
}
td{
	border-bottom:1px solid #ddd;
	border-right:1px solid #ddd;
}
</style>
    
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

<body><br>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<s:iterator value="viewList" status="stuts">
		<tr>
		<td width="15%" >&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="s_user<s:property value='id'/>" name="s_user" uid="<s:property value="id"/>" uname="<s:property value="userName"/>" onclick="chooseUser(this)"><s:property value="userName"/></td>
		</tr>
	</s:iterator>
</table>
 
</body>
</html>