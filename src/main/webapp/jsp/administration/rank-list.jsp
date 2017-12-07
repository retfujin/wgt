<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/Toolbar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/Toolbar.js"></script>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
var toolbar;
$(document).ready(function(){

	toolbar = new Toolbar({
        renderTo : 'toolbar',
		//border: 'top',
        items : [{
          type : 'button',
          text : '新增',
          bodyStyle : 'new',
          useable : 'T',
          handler : function(){
        	  var vReturnValue = window
				.showModalDialog(
						"rank!add.action",
						"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=26;dialogHeight=16;center=true");
			if ("success" == vReturnValue) {
				window.location.reload();
			}
          }
        },'-'
        ]
      });

	  toolbar.render();
});
</script>

<script type="text/javascript">
	
	function edit(id) {
		var vReturnValue = window
				.showModalDialog(
						"rank!edit.action?entity.id=" + id,
						"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=26;dialogHeight=16;center=true");
		if ("success" == vReturnValue) {
			window.location.reload();
		}
	}
	function del(id) {
		if (confirm('确定要删除吗')) {
			$.post("rank!del.action", {'entity.id': id},     
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
<div id="toolbar" style="width:100%;"></div>
<div id="disSel">
<form name="form1" action="rank!list.action" method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
	<tr>
		<td>
			部门名称：<s:select name="departmentId" list="departments" headerKey="" headerValue="显示全部" listKey="id" listValue="name" theme="simple"></s:select>
		</td>
		<td><input type="submit" value="查询" class="a" /></td>
	</tr>
</table>
</form>
</div>
<table id="orderedlist" wnewsidth="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
	<thead>
	<tr>
		<th height="25" colspan="10" class="tableHeaderText">部门一览表</th>
	</tr>

	<tr align="center">
		<td class="forumRow">名称</td>
		<td class="forumRow">所属部门</td>
		<td class="forumRow">薪资</td>
		<td class="forumRow">描述</td>
		<td class="forumRow">操作</td>
	</tr>
	<s:iterator value="page.result" status="stuts">
		<tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if>
			<s:else>class="td_t"</s:else>>
			<td>${name}</td>
			<td>${department.name}</td>
			<td>${salary}</td>
			<td align="center">${description}</td>
			<td><a href="#" onclick="edit('${id}')"><img
				src="/images/edit.gif" alt="编辑" border="0" /></a> &nbsp;&nbsp;<a
				href="#" onclick="del('${id}')"><img
				src="/images/del.gif" alt="删除" border="0" /></a></td>
		</tr>
	</s:iterator>
</table>

<div id="footer" style="margin-top: 10px" align="center">
第${page.pageNo}页, 共${page.totalPages}页 <s:if test="page.hasPre">
	<a
		href="rank!list?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
</s:if> <s:if test="page.hasNext">
	<a
		href="rank!list?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
</s:if></div>

</body>
</html>
