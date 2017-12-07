<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<% 	
	String[] menuModel={"menuModel2.addItem(203,'账务中心','','pay!accountcenter.action',false);"
	};
%>

<%@ include file="/menubar/simple/aa.jsp" %>
<div align="center">操作结果:</div>
<div align="center">${requestScope['forwardStr']}&nbsp;<a href="#" onclick="gotoprint()">打印缴费收据单</a></div>

</body>
<script type="text/javascript">
function gotoprint(){
	var url="pay!viewreceipt.action?recordBh=<s:property value="#request.recordBh"/>";
	//var ret = window.showModalDialog(encodeURI(url), WebBrowser, "dialogWidth=600px;dialogHeight=400px;resizable=yes");
	window.open(url,"弹出窗口","height=300px,top=0,left=0,width=200px,status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes");  
}
</script>
</html>