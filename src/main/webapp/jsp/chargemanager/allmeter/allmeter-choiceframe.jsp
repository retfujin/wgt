<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%String type=request.getParameter("type")!= null ? request.getParameter("type") :"other";%>
<html>
<head>
<title>无标题文档</title>
</head>
<frameset cols="130,*" frameborder="no" border="0" framespacing="0">
  <frame src="allmeter!trees.action?url=/chargemanager/allmeter/allmeter!findHouseAll.action" name="leftFrame" scrolling="yes" noresize="noresize" id="leftFrame"  />
  <frame src="#" name="mainFrame1" id="mainFrame1" title="mainFrame1" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>