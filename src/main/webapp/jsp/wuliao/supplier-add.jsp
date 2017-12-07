<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<title>新增</title>
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
						alert("供应商编号不能为空，请填写");
						return false;
					}
					if(checkOnlyNum(frm.id,"供应商编号")==false)
						return false;
					if(frm.name1.value==''){
						alert("供应商名称不能为空，请填写");
						return false;
					}
					if(frm.mnemonicCode.value==''){
						alert("助记码不能为空，请填写");
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
               <p>添加供应商</p> 
            </div>
            <form id="ff" method="post" action="supplier!save.action" name="frmAdd">
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">供应商编号<font color="red">*</font></td>
                        <td>
							<s:hidden name="add" value="tadd"></s:hidden>
							<s:textfield  name="e.id" id="id" size="15" theme="simple" />(只限数字)
						</td>
                        <td height="35" align="center">供应商名称<font color="red">*</font></td>
                        <td><s:textfield  name="e.name" id="name1" size="25" maxlength="20" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">助记码<font color="red">*</font></td>
                        <td><s:textfield  name="e.mnemonicCode" id="mnemonicCode" size="25" maxlength="20" theme="simple"/></td>
                        <td height="35" align="center">公司性质</td>
                        <td><s:select name="e.companyNature" id="companyNature" list="{'私营','国营'}" theme="simple"></s:select></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">公司地址</td>
                        <td colspan="3"><s:textfield  name="e.address" id="address" size="50" maxlength="80" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">联系人</td>
                        <td><s:textfield  name="e.contact" id="contact" size="25" theme="simple"/></td>
                        <td height="35" align="center">联系方式</td>
                        <td><s:textfield name="e.contactphone"  size="25" id="contactphone"  theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">企业法人</td>
                        <td><s:textfield  name="e.legal" id="legal" size="25" theme="simple"/></td>
                        <td height="35" align="center">银行账号</td>
                        <td><s:textfield name="e.banknum"  size="25" id="banknum" theme="simple" /></td>
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