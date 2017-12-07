<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="../../js/check.js"></script>
<script type="text/javascript">
	function add(){
		gradCode='<s:property value="#parameters.gradCode"/>';
  		url="catalog!add.action?upperGradCode="+gradCode;
  		var vReturnValue = window.showModalDialog(url,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=700px;dialogHeight=506px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function edit(id) {
		var vReturnValue = window.showModalDialog("catalog!edit.action?id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=700px;dialogHeight=506px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function del(id) {
		if (confirm('确定要删除吗')) {
			$.post("catalog!del.action", {'e.id': id},
				   function (data, textStatus){
						var responseText =  eval('(' + data + ')');
						alert(responseText.msg);
						if(responseText.success)
							window.location.reload();
				   });
		}
	}
</script>
  <body>
  
  
  <div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>物料目录一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
			    <td class="td_header">物料编号</td>
			    <td class="td_header">助记码</td>
			    <td class="td_header">物料名称</td>
			    <td class="td_header">规格</td>
			    <td class="td_header">所属类别</td>
			    <td class="td_header">单位</td>
			    <td class="td_header">最小包装</td>
			    <td class="td_header">包装数量</td>
			    <td class="td_header">上限</td>
			    <td class="td_header">下限</td>
			    <td class="td_header">保质期</td>
			    <td class="td_header">体积</td>
			    <td class="td_header">产地</td>
			    <td class="td_header">品牌</td>
			    <td class="td_header">厂家</td>
			    <td class="td_header">计划单价</td>
			    <td class="td_header">销售单价</td>
			    <td class="td_header">备注</td>
			    <td class="td_header"></td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c" ><s:property value="id"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="mnemonicCode"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="name"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="norm"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="WLAssortmentEO.name"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="unit"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="minPackage"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="NumPackage"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="upperLimit"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="lowerLimit"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="lifeOfQualityAssurance"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="volume"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="placeOfProduction"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="brand"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="factory"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="planPrice"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="salePrice"/>&nbsp;</td>
			   	<td class="td_c" ><s:property value="remarks"/>&nbsp;</td>
			 	<td class="td_c">
                   	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png"></a>
                    &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png"></a>
                </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>	 
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
 
  </body>  
</html>