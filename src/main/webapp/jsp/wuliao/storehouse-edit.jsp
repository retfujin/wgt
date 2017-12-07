<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<title>编缉</title>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.id.value==''){
						alert("仓库编号不能为空，请填写");
						return false;
					}
					if(checkOnlyNum(frm.id,"编号")==false)
						return false;
					if(frm.name1.value==''){
						alert("仓库名称不能为空，请填写");
						return false;
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
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>新增仓库</p> 
            </div>
            <form id="ff" method="post" action="storehouse!save.action" name="frmAdd">
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">仓库编号<font color="red">*</font></td>
                        <td>
							<s:textfield name="e.id" id="id" size="15" theme="simple" readonly="true" cssClass="locked"/>(只限数字)
						</td>
                        <td height="35" align="center">仓库名称<font color="red">*</font></td>
                        <td><s:textfield  name="e.name" id="name1" size="25" maxlength="20" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">管理员</td>
                        <td colspan="3"><s:textfield  name="e.adminUser" id="adminUser" size="10" maxlength="10" theme="simple"/></td>
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