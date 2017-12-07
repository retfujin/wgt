<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
	function sel(id) {
		window.showModalDialog("overtime!sel.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=500px;center=true");
	} 
	function edit(id) {
		var vReturnValue = window.showModalDialog("overtime!editone.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=700px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	} 
</script>
<body><!--  
<form class="search_form" name="form1" action="overtime!listone.action" method="post">
	部门<s:textfield name="departname" id="departname"/>
	<input type="submit" value="查询" class="search_btn" />
</form>
-->
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>加班单一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">部门</td>
				<td class="td_header">填表人</td>
				<td class="td_header">填表时间</td>
				<td class="td_header">加班事由</td>
				<td class="td_header">加班时限</td>
				<td class="td_header">加班总天数</td>
				<td class="td_header">状态</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
               <td class="td_c">${stuts.index+1}</td>
                <td class="td_c">${departmentname}&nbsp;</td>
                <td class="td_c">${name}&nbsp;</td>
                <td class="td_c"><s:property value="filldate.toString().substring(0,10)"/> &nbsp;</td>
                <td class="td_c">${reason}&nbsp;</td>
                <td class="td_c"><s:property value="begintime.toString().substring(0,10)"/>/<s:property value="endtime.toString().substring(0,10)"/>&nbsp;</td>
                <td class="td_c">${num}&nbsp;</td>
                <td class="td_c">${state}&nbsp;</td>
                <td class="td_c">
                	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a>
                	&nbsp;&nbsp;<a href="#" onclick="sel('${id}')"><img border="0" alt="查看" src="/images/sel.png" ></a>
                </td>
             </tr>	
             </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           		<p>第${page.pageNo}页, 共${page.totalPages}页
				<s:if test="page.totalPages>1">
					<a href="overtime!listone.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="overtime!listone.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="overtime!listone.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="overtime!listone.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>
</html>
