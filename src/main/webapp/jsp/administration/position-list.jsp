<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function add(){
		var vReturnValue = window.showModalDialog("position!add.action","",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=580px;dialogHeight=406px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function edit(id) {
		var vReturnValue = window.showModalDialog("position!edit.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=580px;dialogHeight=406px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function del(id) {
		if (confirm('确定要删除吗')) {
			$.post("position!del.action", {'entity.id': id},     
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
              <p>岗位一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header">名称</td>
				<td class="td_header">描述</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                <td class="td_c">${name}</td>
				<td class="td_c">&nbsp;${description}</td>
				<td class="td_c"><a href="#" onclick="edit('${id}')"><img src="/images/edit.gif" alt="编辑" border="0" /></a> &nbsp;&nbsp;
				<a href="#" onclick="del('${id}')"><img	src="/images/del.gif" alt="删除" border="0" /></a></td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				
				<s:if test="page.totalPages>1">
					<a href="position!list.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="position!list.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="position!list.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="position!list.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->


 

</body>
</html>
