<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>

<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
function add(){
	var vReturnValue  =window.showModalDialog("basedata!add.action","","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=590px;center=true");
	if ("success" == vReturnValue)
		window.location.reload();
}
function edit(id){
	var vReturnValue  =window.showModalDialog("basedata!edit.action?chargeBasedata.id="+id,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=590px;center=true");
	if ("success" == vReturnValue)
		window.location.reload();
}
function del(id)
{
	if (confirm('确定要删除吗')) {
		$.post("basedata!delete.action", {'chargeBasedata.id': id},     
				function (data, textStatus){
					var responseText =  eval('(' + data + ')');
					alert(responseText.msg);
					if(responseText.success){
						window.location.reload();
					}					   		
				});
	}
}
</script>
<body>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>收费项目列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header" >小区</td> 
		    	<td class="td_header" >收费编号</td> 
		    	<td class="td_header" >收费名称</td>
		    	<td class="td_header">单价</td>
		    	<td class="td_header" >收费说明</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c">${area.areaName}</td>
			  	<td class="td_c">${id }</td>
			   	<td class="td_c" align="left">${chargeName}&nbsp;</td>
			   	<td class="td_c"  align="left">
				   	<s:if test="priceValue == 0">
				   	</s:if>
				   	<s:else>
				   		${priceValue}&nbsp;${priceUnit}
				   	</s:else>
			   	</td>
			   	<td class="td_c" align="left">${chargeExplain}&nbsp;</td>
			   	
                <td class="td_c">
                	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a> 
                    &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer">
           		<a href="###" class="add fl" onclick="add()">新增</a>
            	
            	<p>第${page.pageNo}页, 共${page.totalPages}页				
				<s:if test="page.totalPages>1">
					<a href="basedata!list.action?areaId=${param.areaId}&page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="basedata!list.action?areaId=${param.areaId}&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="basedata!list.action?areaId=${param.areaId}&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="basedata!list.action?areaId=${param.areaId}&page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
  

</body>
</html>
