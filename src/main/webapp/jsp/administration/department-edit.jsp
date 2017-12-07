<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#ff').form({
			onSubmit : function() {
				var frm = document.getElementById("ff");
				if (frm.name.value == '') {
					alert("名称不能为空，请填写");
				//	document.getElementById('cell').focus();
					return false;
				}
				return true;
			},
			success : function(data) {
				var responseText = eval('(' + data + ')');
				if (responseText.success) {
					alert('保存成功');
					window.returnValue = "success";
					window.close();
				} else {
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
               <p>部门信息</p> 
            </div>
            <form id="ff" method="post" action="department!save.action" name="frmAdd" >
                <s:hidden name="entity.id"></s:hidden>
				<s:hidden name="entity.isdel"></s:hidden>
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">名称<font color="red">*</font></td>
                        <td><s:textfield name="entity.name" id="name" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">地址</td>
                        <td><s:textfield name="entity.address" id="address" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">电话</td>
                        <td><s:textfield name="entity.phone" id="phone" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">备注</td>
                        <td><s:textarea name="entity.remarks" id="remarks" cols="35" rows="5" theme="simple" /></td>
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
