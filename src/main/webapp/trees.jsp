<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<link rel="stylesheet" type="text/css" href="styles/menu.css" />
<script type="text/javascript" src="/js/jquery.min.js" ></script>
<script type="text/javascript" src="/js/menu_min.js"></script>
<title>列表</title>
<script type="text/javascript">
$(document).ready(function (){ 
  $(".menu ul li").menu();  
}); 
</script>
</head>
<body>

<div id="content">
<s:iterator value="viewList" status="stuts">
	<s:if test="grade==0">
	   	<div class="menu_top">
	       	<s:property value="name"/>
	    </div>
    </s:if>
    <s:elseif test="grade==1">
    <s:set name="modelId" value="id"></s:set>
    	<s:if test="url!=null && url!=''">
    		<div class="menu">
	        <ul>
	             <li class="menu_home"><a class="active" href="<s:property value="url"/>" target='mainFrame'><s:property value="name"/></a>			
	            </li>       
	        </ul>
	    	</div>
        </s:if>
        <s:else>
        	<div class="menu">
	        <ul>
	             <li class="menu_home"><a class="active" href="#"><s:property value="name"/></a>	
	             	<ul>
	                   <s:iterator value="viewList" status="stuts1">
                			<s:set name="belongId" value="belongId"></s:set>
                			<s:if test="grade==2 && #modelId==belongId">
                    			<li><a href="<s:property value="url"/>"  target='mainFrame'><s:property value="name"/></a></li>
	                    	</s:if>
                    	</s:iterator>   	                     
	                </ul>			
	            </li>       
	        </ul>
	    	</div>
        </s:else>
    </s:elseif>
</s:iterator>
	<%
		if("99".equals(request.getParameter("pid"))){
			out.println("<div class='menu_top'>系统管理</div>");
			
			String adminType = (String)session.getAttribute("adminType");
			if("0".equals(adminType)){
				out.println("<div class='menu'><ul><li><a class='active' href='sys/role!list.action' target='mainFrame'>权限组管理</a></li></ul></div>"+
						"<div class='menu'><ul><li><a class='active' href='sys/user!list.action' target='mainFrame'>用户管理</a></li></ul></div>"+
						"<div class='menu'><ul><li><a class='active' href='sys/user!editPass.action' target='mainFrame'>密码修改</a></li></ul></div>");
			}else{
				out.println("<div class='menu'><ul><li><a class='active' href='sys/user!editPass.action' target='mainFrame'>密码修改</a></li></ul></div>");
			}
		}
	%>
</div>
 
</body>
</html>