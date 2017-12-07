<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">

		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.userName.value=='')
					{
						alert("用户登陆名不能为空");
						return false;
					}
					if(frm.password.value=='')
					{
						alert("密码不能为空");
						return false;
					}
					if(frm.password.value!=frm.repassword.value)
					{
						alert("输入的2次密码不相同");
						return false;
					}
					
					var a = document.getElementsByName("managerarea");
					var b=false;
					for(i=0;i<a.length;i++){
						if(a[i].checked==true)
						{
							b=true;
							break;
						}
					}
					if(b==false){
						alert("必须选择至少一个小区");
						return false;
					}
					
					if(frm.roleId.value=='')
					{
						alert("角色不能为空");
						return false;
					}
					var pass=frm.password.value;
					   for(var o=0;o<pass.length;o++){
					   	  if(pass.charCodeAt(o)>255){
							alert('密码中不能有中文字符！');
							return false;
						  }
					   }
					
					
					
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.returnValue = "success";
						window.close();
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
<script type="text/javascript">
	//选择人
	function goEmployee(){
		document.getElementById('employeeId').value="";
		document.getElementById('employeeName').value="";
		
	    var rtnValue = window.showModalDialog("process!choose.action","","dialogHeight:400px;dialogWidth:700px");
	    if(rtnValue!=undefined){
	        var userIdList = "";
	        var userNameList = "";
	        if(rtnValue!=null){
	            for(var i=0;i<rtnValue.length;i++){
	                if(userIdList=="") userIdList = rtnValue[i].uid;
	                if(userNameList=="") userNameList = rtnValue[i].uname;
	       
	            }
	        }
	        document.getElementById('employeeId').value = userIdList; 
	        document.getElementById('employeeName').value = userNameList;
	    }
	}
</script>
<%@ include file="/commons/meta.jsp" %>
<body>




<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>添加系统登录用户</p> 
            </div>
            <form id="ff" method="post" action="user!save.action" name="frmAdd" >
               <s:hidden name="entity.id"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" width="160" align="center">用户名</td>
                        <td><s:textfield name="entity.userName" id="userName" size="25"  maxlength="20" theme="simple"/></td>
                    </tr>
                    <!-- 
                    <tr>
                     <td height="35" align="center">员工</td>
                     <td><s:hidden name="entity.employeeId" id="employeeId"/>
				      	<s:textfield name="entity.employeeName" id="employeeName" size="25" readonly="true" maxlength="20" theme="simple"/>
				      	<input type='button' onclick='javascript:goEmployee()' value='选择' /></td>
                    </tr>
                     -->
                    <tr>
                        <td height="35" align="center">密码</td>
                        <td><s:password name="entity.password" id="password" size="25" maxlength="20" theme="simple" showPassword="true"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">密码确认</td>
                        <td><s:password name="repassword" id="repassword"  value="%{entity.password}" size="25" maxlength="20" theme="simple" showPassword="true"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">管理范围</td>
                        <td> <s:iterator value="viewList1" status="stus">
						      	<input type="checkBox" name="managerarea" value="<s:property value="id"/>" <s:if test="isChecked==1">checked</s:if>  /><s:property value="areaName"/>
						      </s:iterator></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">所属角色</td>
                        <td> <s:select id="roleId" name="entity.role.id" list="viewList" listKey="id" listValue="name" theme="simple">
     						 </s:select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">用户描述</td>
                        <td> <s:textarea name="entity.userDescription" id="description" cols="30" rows="3" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!----add结束---> 
   
    
  </body>
</html>
