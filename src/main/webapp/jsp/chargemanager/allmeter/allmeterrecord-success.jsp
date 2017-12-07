<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
</head>
<body>
<div align="center">操作成功！
<s:if test="forwardUrl != null && !forwardUrl.equals('')"><a href="${forwardUrl}">返回</a></s:if>
</div>
</body>
</html>
