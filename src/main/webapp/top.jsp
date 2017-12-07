<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<script type="text/javascript" src="js/jquery.js"></script>
<title></title>
</head>
<body scroll="no">
<div class="top">
<div class="nav_area">
    <div class="logo fl">
        <img src="images/logo1.png" width="282" height="90" />
    </div>
     <div class="nav_content fl">
            <ul id="nav_ul">
                <li><a href="login!loginTree.action?pid=11" target="leftFrame"><img src="images/nav_icon_r2_c1.png" />物业资源</a></li>
                <li><a href="login!loginTree.action?pid=12" target="leftFrame"><img src="images/nav_icon_r2_c3.png" />收款管理</a></li>
                <li><a href="login!loginTree.action?pid=13" target="leftFrame"><img src="images/user_service.png" />业主服务</a></li>
                <li><a href="login!loginTree.action?pid=23" target="leftFrame"><img src="images/nav_icon_r2_c5.png" />投诉管理</a></li>
                <li><a href="login!loginTree.action?pid=21" target="leftFrame"><img src="images/nav_icon_r1_c7.png" />报修管理</a></li>     
                <li><a href="login!loginTree.action?pid=99" target="leftFrame"><img src="images/nav_icon_r3_c15.png" />系统设置</a></li>
            </ul>
        </div>
       <div class="quick_nav fr">
                <ul>
                    <li class="home"><a href="javascript:void(0);" id="setHomePage" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://60.173.216.247:8090/');return(false);" style="behavior:url(#default#homepage);">设为首页</a></li>
                    <li class="collect"><a href="javascript:void(0);"  onclick="window.external.AddFavorite('http://60.173.216.247:8090/','物管通系统');">加入收藏</a></li>
                    <li class="close"><a href="logout.jsp" target="_top">退出系统</a></li>
                </ul>	
             </div>		
        <!---quick_nav结束---->
</div>
<!---nav_area结束--->
</div>
<!-----top结束-->
<div class="clear"></div>
<div class="weclome">
	<p><img src="images/admin.png" /><%=(String)session.getAttribute("userName")%>&nbsp;&nbsp; 欢迎您登录物业管理系统！&nbsp;&nbsp;<a href="login!tips.action" target="mainFrame">返回首页</a></p>
</div>
<script type="text/javascript">
    $('#nav_ul').children('li').each(function(){
        var $this =  $(this),
            _href = $this.find('a').attr('href').split('?')[0] || $this.attr('href');

        if(_href == String(window.location)){
            $this.addClass('active');
        }
    });
</script>

</body>
</html>