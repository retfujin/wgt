<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<div align="center">操作结果:</div>
<div align="center">${requestScope['forwardStr']}&nbsp;<a href="#" onclick="gotoprint()">打印缴费收据单</a></div>
</body>
<script type="text/javascript">
function gotoprint(){
	var url="pay!printview.action?recordBh=<s:property value="#request.recordBh"/>";
	window.open(url,"缴费单","height=320px,top=0,left=0,width=420px,status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes");  
}
</script>

</html>