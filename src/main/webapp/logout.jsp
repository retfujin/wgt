<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<% 
if(session.getAttribute("userName")!=null)
{
	session.invalidate();
}
%>
<script type="text/javascript">
top.window.location.href="login.jsp";
</script>
