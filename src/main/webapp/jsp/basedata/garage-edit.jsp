<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.carCode.value=='')
					{
						alert("车位编号不能为空，请填写");
						return false;
					}
					var v_mianji = document.getElementById('mianji');
					
					if(v_mianji!=null){
						if(!checkMoney(v_mianji,"车位面积"))//只能是数字的验证
					    {
							v_mianji.select();
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
<%@ include file="/commons/meta.jsp"%>
<body>
<div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>车位资料</p> 
            </div>
            <form id="ff" method="post" action="garage!save.action" name="frmAdd" >
               <s:hidden name="entity.id" id="id"></s:hidden>
               <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区编号<font color="red">*</font></td>
                        <td><s:textfield name="entity.area.areaName" theme="simple" readonly="true" />
							<s:hidden name="entity.area.id"></s:hidden></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车位编号<font color="red">*</font></td>
                        <td><s:textfield name="entity.carCode" id="carCode" size="25" maxlength="20" theme="simple" readonly="true" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">车位种类</td>
                        <td><s:select name="entity.type" id="type"	list="#{'1':'机动车','2':'非机动车'}" theme="simple" /></td>
                     </tr>
                    <tr>
                        <td height="35" align="center">位置</td>
                        <td>
                        	<s:select name="entity.local" list="#{'地面':'地面','地下':'地下','车库':'车库'}" id="local" theme="simple"></s:select>
                        </td>
                     </tr>
                    <tr>
                        <td height="35" align="center">面积</td>
                        <td>
                        	<input type="text" name="mianji" id="mianji" value="${entity.mianji }"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="35" align="center">状态</td>
                        <td><s:textfield name="entity.state" id="state" value="空置"	readonly="true" cssClass="locked" size="15" maxlength="10" theme="simple" /></td>
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
