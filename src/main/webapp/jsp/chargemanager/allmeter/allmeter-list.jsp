<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript">
function add(){
 	var url="allmeter!add.action";
	window.showModalDialog(url,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=660px;dialogHeight=520px;center=true");
}
</script>
<script type="text/javascript">
function edit(id,areaId)
{
	var url="allmeter!edit.action?allmeter.id="+id+"&allmeter.areaId="+areaId;
	window.showModalDialog(url,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=660px;dialogHeight=530px;center=true");
	//window.location.href=url;
}
function del(id)
{
	var url="allmeter!delete.action?allmeter.id="+id;
	if(confirm('确定要删除吗')==true){
		var vReturnValue  =window.showModalDialog(url,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=10;dialogHeight=10;center=true");
		if(undefined == vReturnValue)
		{
			  window.location.reload();
		}
	}
	
}
</script>
<body>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>总表列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header">小区</td>
			    	<td class="td_header">编号</td>
			    	<td class="td_header">公司表号</td>
			    	<td class="td_header">名称</td>
			    	<td class="td_header">上期度数</td>
			    	<td class="td_header">单价</td>
			    	<td class="td_header">倍率</td>
			    	<td class="td_header">位置</td>
					<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                    <td class="td_c">${stuts.index+1}</td>
					<td class="td_c" >${areaName}&nbsp;</td>
				  	<td class="td_c" >${meterCode}&nbsp;</td>
				  	<td class="td_c" >${meterCodelocal}&nbsp;</td>
				   	<td class="td_c">${meterName}&nbsp;</td>
				   	<td class="td_c">${lastRecord}&nbsp;</td>
				   	<td class="td_c" >${priceValue}&nbsp;${unit}</td>
				   	<td class="td_c" >${times}&nbsp;</td>
				   	<td class="td_c">${local}&nbsp;</td>
                    <td class="td_c">
                    	<a href="#" onclick="edit('${id}','${areaId}')"><img border="0" alt="编辑" src="/images/change.png" ></a> 
                        &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                    </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				
				<s:if test="page.totalPages>1">
					<a href="allmeter!list.action${requestScope.parmString}&page.pageNo=1">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="allmeter!list.action${requestScope.parmString}&page.pageNo=${page.prePage}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="allmeter!list.action${requestScope.parmString}&page.pageNo=${page.nextPage}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="allmeter!list.action${requestScope.parmString}&page.pageNo=${page.totalPages}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
</body>
</html>