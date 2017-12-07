<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/meta.jsp" %>
<html>
<body>
<script language="javascript">
alert("${requestScope['forwardStr']}");
window.location.href="${requestScope['forwardUrl']}";
</script>
</body>
</html>