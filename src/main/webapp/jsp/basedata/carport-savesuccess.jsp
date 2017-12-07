<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<div align="center">操作结果:</div>
<div align="center">保存成功&nbsp;<s:if test="#request.recordBh!=''"><a href="#" onclick="gotoprint()">打印缴费收据单</a></s:if></div>
<script type="text/javascript">
//window.open ('pay!printview.action?recordBh=<s:property value="#request.recordBh"/>', '缴费单', 'height=320, width=420, top=0, left=0, toolbar=no,menubar=no, scrollbars=yes, resizable=no,location=no, status=no'); 
</script>
</body>
<script type="text/javascript">
function gotoprint(){
	var url="../../chg/pay!printview.action?recordBh=<s:property value="#request.recordBh"/>";
	//var ret = window.showModalDialog(encodeURI(url), WebBrowser, "dialogWidth=600px;dialogHeight=400px;resizable=yes");
	window.open(url,"缴费单","height=320px,top=0,left=0,width=420px,status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes");  
}
</script>

</html>