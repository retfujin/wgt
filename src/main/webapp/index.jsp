<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>机电设备管理系统</title>
	    <link rel="stylesheet" href="easyui/themes/default/easyui.css" type="text/css" media="screen" />
	     <link rel="stylesheet" href="easyui/themes/default/style.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="css/main.css" type="text/css" media="screen" />
	    <script src="easyui/jquery-1.3.2.min.js"></script>
	    <script src="easyui/jquery.easyui.min.js"></script>
	    <script>
	    	function addTab(title, href){
		    	var tt = $('#main-center');
		    	if (tt.tabs('exists', title)){
			    	tt.tabs('select', title);
		    	} else {
			    	if (href){
				    	var content = '<iframe scrolling="no" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';
			    	} else {
				    	var content = '未实现';
			    	}
			    	tt.tabs('add',{
				    	title:title,
				    	closable:true,
				    	content:content
			    	});
		    	}
	    	}
	    </script>
		<style>
			.nav-item{
				text-align:center;
				background:#fff;
				height:80px;
			}
			.nav-item img{
				border:0;
			}
		</style>
    </head>
    <body class="easyui-layout">
		<div region="north" style="background:#fafafa;color:#2d5593;height:40px;">
		    <div style="font-size:16px;font-weight:bold;width:400px;padding:10px 0 0 10px;">机电设备管理系统</div>
		</div>
		<div region="west" title="导航菜单" split="true" style="width:150px;">
			<div class="easyui-accordion" fit="true" border="false">
			 <s:iterator value="viewList" status="stuts">
			 	<s:if test="grade==0">
			 		<div title="<s:property value="name"/>" selected="true" style="overflow:auto;">
			 	</s:if>
			 		
			 	
			 	
			 </s:iterator>
			 
			
				<div title="业务操作" selected="true" style="overflow:auto;">
					<div class="nav-item">
						<a href="javascript:addTab('设备档案','device/index')">
							<img src="images/print_class.png"></img><br/>
							<span>设备档案</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:addTab('设备申购')">
							<img src="images/kdmconfig.png"></img><br/>
							<span>设备申购</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:addTab('设备维修')">
							<img src="images/package_settings.png"></img><br/>
							<span>设备维修</span>
						</a>
					</div>
				</div>
				<div title="数据设置" style="overflow:auto;">
					<div class="nav-item">
						<a href="javascript:addTab('区域设置')">
							<img src="images/package.png"></img><br/>
							<span>区域设置</span>
						</a>
					</div>
					<div class="nav-item">
						<a href="javascript:addTab('设备类别')">
							<img src="images/kontact.png"></img><br/>
							<span>设备类别</span>
						</a>
					</div>
				</div>
				<div title="数据设置11" style="overflow:auto;">
					<ul>
						<li>
							<ul><li>dddd<li></ul>
						</li>
						<li><a href="http://www.baidu.com" target="navTab" rel="page1">页面一(外部页面)</a></li>
						<li><a href="demo_page2.html" target="navTab" rel="external" external="true">iframe navTab页面</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div region="center">
			<div id="main-center" class="easyui-tabs" fit="true" border="false">
				<div title="首页" style="padding:20px;">
					<img src="images/banner.gif"></img>
					<div style="margin-top:20px;">
					<p>该系统是一个由etmvc和jquery-easyui技术构建的应用示例，如果您对本系统所使用的技术感兴趣，请与我们联系。</p>
					<p>&nbsp;</p>
					<p>设备管理是一款记录设备使用流程的软件，其中有设备信息录入，设备类别设置，区域信息的录制，设备的使用流程，设备使用流程中出错的处理，以及报表查询等功能。</p>
					<p>&nbsp;</p>
					<p>我们对系统进行简化，旨在说明一般功能的开发方法。</p>
					</div>
				</div>
			</div>
		</div>
    </body>
</html>
