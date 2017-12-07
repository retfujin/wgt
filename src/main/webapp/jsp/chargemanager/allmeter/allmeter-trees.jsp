<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<title>列表</title>
<link href="/jxt.css" rel="stylesheet" type="text/css">
<link href="/styles/_MenuTree.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/_MenuTree.js"></script>
</head>

<body style="margin: 0px;background-color: #eeeeee;" >
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
	 
	
	 	[<s:property value="gread"/>,"<s:property value="name"/>","<s:property value="url"/>","","","mainFrame1"]
	 	<s:if test="#stuts.index<viewList.size()-1">
	 	,
	 	</s:if>
	 	
	 </s:iterator>	
	);
			var CSDN = new _MenuTree("CSDN", arrCSDN, "/images/CSDN/");
			CSDN.LastIconName = "last";
			CSDN.Draw();
			CSDN.Mark(location.pathname + location.search);
			



</script>


</body>

</html>