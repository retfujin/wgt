<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
	function edit(id) {
		$.post("recruitment!getInfo.action", {'entity.id': id},     
				   function (data, textStatus){
						var responseText =  eval('(' + data + ')');
						if(!responseText.success){
							alert(responseText.msg);
							return;
						}else{
							var vReturnValue = window.showModalDialog("recruitment!edittwo.action?entity.id=" + id,"",
							"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=700px;center=true");
							if ("success" == vReturnValue)
								window.location.reload();
						}
				   });
	}
	
	function sel(id) {
		window.showModalDialog("recruitment!sel.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=600px;dialogHeight=600px;center=true");
	}
</script>
<body>
<form class="search_form" name="form1" action="recruitment!listtwo.action" method="post">
	部门<s:textfield name="departmentname" id="departmentname"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>招聘申请一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">用人部门</td>
				<td class="td_header">招聘职位</td>
				<td class="td_header">人数</td>
				<td class="td_header">面试负责人</td>
				<td class="td_header">工作地点</td>
				<td class="td_header">预定聘用日期</td>
				<td class="td_header">状态</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                	<td class="td_c">${stuts.index+1}</td>
                    <td class="td_c">${departmentname}</td>
                    <td class="td_c">${position}</td>
                    <td class="td_c">${number}&nbsp;</td>
                    <td class="td_c">${pheader}&nbsp;</td>
                    <td class="td_c">${address}&nbsp;</td>
                    <td class="td_c"><s:property value="begindate.toString().substring(0,10)"/> &nbsp;</td>
                    <td class="td_c">${state}&nbsp;</td>
                    <td class="td_c">
                    	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a> 
                    	&nbsp;&nbsp;<a href="#" onclick="sel('${id}')"><img border="0" alt="查看" src="/images/sel.png" ></a>
                    </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				<s:if test="page.totalPages>1">
					<a href="recruitment!listtwo.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="recruitment!listtwo.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="recruitment!listtwo.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="recruitment!listtwo.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>
</html>
