<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<base target="_self">
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					if(document.getElementById('areaId').value=='')
					{
						alert("请选择小区");
						return false;
					}
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功,请重新点击左侧菜单查看');
						window.returnValue = "success";
						window.close();
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
</head>
<body>


<form class="search_form" id="ff" name="form1" method="post" action="owner!saveChargeAll.action">
	小区名称<s:select id="areaId" name="areaId" list="retList" headerKey="" headerValue="=请选择=" listKey="id" listValue="areaName" theme="simple" onchange="doRequest();"/>
	楼栋<span id="target"></span>
	单元<input name="cell" size="5"/>
	层数<select name="symbol"><option value="">-请选择-</option>
									<option value="大于等于">大于等于</option>
									<option value="小于">小于</option> 
			   </select>&nbsp;&nbsp;<input name="layer" size="5"/>
	 
	 
	 <div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>设置客户收费项目</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
		    	<td class="td_header">序号</td>
		    	<td class="td_header">收费项目</td>
		    	<td class="td_header">单价</td>
		    	<td class="td_header">计算公式</td>
		    	<td class="td_header">公式说明</td>
		   </tr>
            
            <tr>
                <td class="td_c" colspan="5">
                	<div id="edifcharge"></div>
                </td>
            </tr>
            <tr class="footer">
            	<td colspan="5">
                <input type="submit" class="save" value="保存" name="submit111" /> 
                </td>
            </tr>
      		</table>
      		
       </div>
       <!---table_area结束--->
   </div>
	  
</form>

<script type="text/javascript">
function doRequest(){
	$("#target").load("owner!getEdifice.action?areaId="+$("#areaId").val());
	$("#edifcharge").load("owner!getChargeBasedataAll.action?areaId="+$("#areaId").val()+"&k="+new Date().getTime());
}
</script>
</body>
</html>
