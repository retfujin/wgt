<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<%@ include file="/commons/meta.jsp" %>
  <body>
<div align="center">操作结果:</div>
<div align="center">${requestScope['forwardStr']}</div>
<br/>
<br/>
<div align="center"><a href="#" onclick="window.close();">关闭</a></div>
</body>
</html>