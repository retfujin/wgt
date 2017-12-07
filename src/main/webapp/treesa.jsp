<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>列表</title>
<link href="styles/_MenuTree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/_MenuTree.js"></script>
</head>
<body style="margin: 0px; background-color:#e6edf1;;">
<table width="100%" border="0" cellpadding="0" cellspacing="2" style="background:url(images/td_list_bg3.gif) repeat-x 1px; line-height:27px">
<tr><td width="20" height="20"></td><td><img src="images/home_icon.gif"></td><td><b><font color="#ffffff">操作菜单</font></b></td></tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr><td height="5"></td></tr>
</table>
<script type="text/javascript">
	// 自定义获取网页参数函数
	function GetQuery(str)
	{
		var reg = new RegExp("[\?&]" + str + "=([^&]*)&*", "ig");
		if (reg.test(location.search)) return RegExp.$1;
		return "";
	}
	var tree = GetQuery("tree");
	var urlPrefix = "?tree=" + tree + "&";
	// 父菜单树同步
	try
	{
		parent.direct.Mark(location.pathname + "?tree=" + GetQuery("tree"));
	}
	catch(e)
	{
	}

	// 格式[层,菜单文字,链接,Title,图标,Target]
	// WINXP 菜单树数据源
	var arrCSDN = new Array(
			
			 <s:iterator value="viewList" status="stuts">
			 	[<s:property value="grade"/>,"<s:property value="name"/>","<s:property value="url"/>","","","mainFrame"],
			 </s:iterator>
			<% 
				String adminType = (String)session.getAttribute("adminType");
				if("0".equals(adminType)){
					out.println("[0,'系统管理','','','','mainFrame'],"+
						 	"[1,'权限组管理','sys/role!list.action','','','mainFrame'],"+
						 	"[1,'用户管理','sys/user!list.action','','','mainFrame'],"+
						 	"[1,'密码修改','sys/user!editPass.action','','','mainFrame']");
				}else{
					out.println("[0,'系统管理','','','','mainFrame'],"+
								"[1,'密码修改','sys/user!editPass.action','','','mainFrame']");
				}
			
			%>
	);
		
	var CSDN = new _MenuTree("CSDN", arrCSDN, "images/CSDN/");
	CSDN.LastIconName = "last";
	CSDN.Draw();
	CSDN.Mark(location.pathname + location.search);
</script>
</body>
</html>