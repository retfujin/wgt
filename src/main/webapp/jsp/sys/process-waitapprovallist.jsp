<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>列表</title>
</head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>

<body>


<table width="90%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
	<tr>
		<th>审批项目</th>
		<th>申请环节</th>
		<th>申请人</th>
		<th>申请部门</th>
		<th>审批</th>
		<th></th>
	</tr>
	<s:iterator id="templates" var="datas" value="page.result" status="stuts">
		<tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>
			<td><s:property value="#datas.get('process_code')" /></td>
			<td><s:property value="#datas.get('module_name')" /></td>
			<td><s:property value="#datas.get('employee_name')"/></td>
			<td><s:property value="#datas.get('department_name')"/></td>
			<td>
				<a href="javascript:void(0)"><img alt="审批" border="false" src="/images/edit.gif" onclick="approval('<s:property value="#datas.get('url')"/>')" /></a>
			</td>
		</tr>
	</s:iterator>
</table>
<br/>
<br/>
<div id="pageBar" >${page.showPage}</div>


<script language="javascript">

function approval(url){

	var vReturnValue = window.showModalDialog(url,"",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=35;center=true");
	if ("success" == vReturnValue) {
		window.location.reload();
	}
}

</script>
  
  
</body>
</html>
