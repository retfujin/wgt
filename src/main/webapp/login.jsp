<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录物管通系统</title>
<link rel="stylesheet" type="text/css" href="styles/basic.css" />
<link rel="stylesheet" type="text/css" href="styles/login.css" />
</head>
<OBJECT id=closes type=application/x-oleobject height=0   width=0  classid=clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11>
<PARAM NAME="Command" VALUE="Close">
</OBJECT>
<!--[if IE 6]>
<script type="text/javascript" src="png-ie.js"></script>
<script type="text/javascript">
	 DD_belatedPNG.fix();
</script>
<![endif]-->   
<body >
<div class="login_main">
	<div class="login_content">
    	<div class="l_content_left fl">
        	<img src="/images/circle.png" width="410" height="367" />
        </div>
        <!---l_content_left结束---->
        <div class="l_content_right fl">
        	<div class="l_form_content">
                <div class="l_logo">
                    <img src="/images/logo.png" width="229" height="49" />
                </div>
                <form class="l_form" name="form1" method="post" action="login!loginCheck.action" onsubmit="return checkForm();">
                	 <div class="login_c fl">
    	                <div class="user">
 	                        <div class="user_input"> 
                            用户名：<input type="text" maxlength="18" name="userName" id="userName" class="reg_ipt ml-80">   
                        	</div>
                        </div>
                        <!---user结束--->
                         <div class="key">
                            <div class="key_input">
                              密　码：<input type="password" maxlength="18" id="password" name="password" class="reg_ipt ml-80">
                            </div>
                       </div>
                        <!---key结束-->
                         <div class="check">
                            <div class="check_input">
                                验证码：<input type="text" id="validateCode" name="validateCode"> 
                                <img id="checkCode" src="image.jsp" onclick="change()"/>
                            </div>
                         </div>
                       
                  	</div>
                	<!---login_c结束---->	
                    <div class="login_button fl">	
                    	<input type="submit" name="Submit" value="" class="loginBtn">
                    </div>
                    <!---login_button结束--->
                     <!----check结束-->
                     	<div class="clear"></div>
                        <div class="remind"><s:fielderror fieldName="user.userName"></s:fielderror></div>
                    <s:token name="token" />
                    </form>
                    <!---l_form结束--->
                    
                    </div>
                    <!----l_form_content结束--->
                </div>
                <!---l_content_right结束--->
    </div>
    <!---login_c结束--->	
</div>
<!---login_main结束--->
<script language="Javascript">
  	function checkForm(){
		var userName=document.getElementById('userName').value;
		var passWord=document.getElementById('password').value;
		var validateCode=document.getElementById('validateCode').value;
		if(userName.length<1){
			alert("请输入用户名!");
			document.getElementById('userName').focus();
			return false;
		}
		if(passWord.length<1){
			alert("请输入密码!");
			document.getElementById('password').focus();
			return false;
		}	
		if(validateCode=='' || validateCode.length!=4){
			alert("请输入正确的验证码!");
			document.getElementById('validateCode').focus();
			return false;
		}
		return true;
  	  }
  	function change(){
  		document.getElementById("checkCode").src = 'image.jsp?' + new Date().getTime();
  	}
</script>
</body>
</html>