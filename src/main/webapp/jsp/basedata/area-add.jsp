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
					var areaId=document.getElementById('areaId').value;
					if(areaId.length<1)
					{
						alert("小区编号不能为空，请填写");
						document.getElementById('areaId').focus();
						return false;
					}else{
						if(isNaN(areaId))
						{
							alert("请输入正确的小区编号，必须为数字");
							document.getElementById('areaId').select();
							return false;
						}
					}
					if(frm.areaName.value=='')
					{
						alert("小区名称不能为空，请填写");
						document.getElementById('areaName').focus();
						return false;
					}
					if(document.getElementById('seatNum').value!=''){
						if(isNaN(document.getElementById('seatNum').value))
						{
							alert("总幢数为数字！");
							document.getElementById('seatNum').select();
							return false;
						}
					}
					if(document.getElementById('flatletNum').value!=''){
						if(isNaN(document.getElementById('flatletNum').value))
						{
							alert("总套数为数字！");
							document.getElementById('flatletNum').select();
							return false;
						}
					}

					
					if(document.getElementById('occupyNum').value!=''){
						if(isNaN(document.getElementById('occupyNum').value))
						{
							alert("占地面积为数字！");
							document.getElementById('occupyNum').select();
							return false;
						}
					}
					if(document.getElementById('buildNum').value!=''){
						if(isNaN(document.getElementById('buildNum').value))
						{
							alert("建筑面积为数字！");
							document.getElementById('buildNum').select();
							return false;
						}
					}
					if(document.getElementById('useNum').value!=''){
						if(isNaN(document.getElementById('useNum').value))
						{
							alert("使用面积为数字！");
							document.getElementById('useNum').select();
							return false;
						}
					}
					if(document.getElementById('populationNum').value!=''){
						if(isNaN(document.getElementById('populationNum').value))
						{
							alert("总人口数为数字！");
							document.getElementById('populationNum').select();
							return false;
						}
					}
					
					var poolRatio=document.getElementById('poolRatio').value;
					if(document.getElementById('poolRatio').value!=''){
						if(isNaN(poolRatio))
						{
							alert("公摊比例为数字");
							document.getElementById('poolRatio').select();
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
               <p>小区</p> 
            </div>
            <form id="ff" method="post" action="area!save.action" name="frmAdd" >
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">小区编号<font color="red">*</font></td>
                        <td><input type="text" name="areaId" id="areaId" size="25" maxlength="20" /></td>
                        <td height="35" align="center">小区名称<font color="red">*</font></td>
                        <td><s:textfield name="entity.areaName" id="areaName" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">小区位置</td>
                        <td><s:textfield name="entity.areaplace" id="areaplace" size="25" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">开发商</td>
                        <td><s:textfield name="entity.developer" id="developer" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">建筑商</td>
                        <td><s:textfield name="entity.build" id="build" size="25" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">总幢数</td>
                        <td><s:textfield name="entity.seatNum" id="seatNum" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">总套数</td>
                        <td><s:textfield name="entity.flatletNum" id="flatletNum" size="25" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">占地面积</td>
                        <td><s:textfield name="entity.occupyNum" id="occupyNum" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">建筑面积</td>
                        <td><s:textfield name="entity.buildNum" id="buildNum" size="25" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">使用面积</td>
                        <td><s:textfield name="entity.useNum" id="useNum" size="25" maxlength="20" theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">接盘时间</td>
                        <td><s:textfield name="entity.diskTime" id="diskTime" readonly="true" size="25" maxlength="20" theme="simple" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
                        <td height="35" align="center">管理处负责人</td>
                        <td><s:textfield name="entity.areaManager" id="areaManager" size="25" maxlength="20"  theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">窗口电话</td>
                        <td><s:textfield name="entity.mobile" id="mobile" size="25" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">公摊比例</td>
                        <td><s:textfield name="poolRatio" id="poolRatio" size="25" maxlength="20"  theme="simple" /></td>
                    </tr>
                    <tr>
                        <td height="35" align="center" >总人口数</td>
                        <td ><s:textfield name="entity.populationNum" id="populationNum" size="25" maxlength="20" theme="simple" /></td>
                        <td height="35" align="center">&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：小区编号为4位数字<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                          带<font color="red">*</font>为必须填写
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