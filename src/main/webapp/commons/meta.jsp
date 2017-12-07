<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
<link href="/styles/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/styles/basic.css" />
<link rel="stylesheet" type="text/css" href="/styles/new_add.css" />

<script type="text/javascript">
</script>
<%
try{request.setCharacterEncoding("UTF-8");}catch(Exception ex){} 
if(session.getAttribute("userName")==null)
{
%>
<script type="text/javascript">
top.window.location.href="/login.jsp";
</script>
<%}%>