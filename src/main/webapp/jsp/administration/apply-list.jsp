<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
	function add(){
		var vReturnValue = window.showModalDialog("apply!add.action","",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=1000px;dialogHeight=800px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function edit(id,approvalid) {
		if(approvalid!=""){
			alert("该人员状态已发生变化，不可以修改。");
			return;
		}
		$.post("apply!getApplyeoid.action", {'entity.id': id},     
		   function (data, textStatus){
				var responseText =  eval('(' + data + ')');
				if(!responseText.success){
					alert(responseText.msg);
					return;
				}else{
					var vReturnValue = window.showModalDialog("apply!edit.action?entity.id=" + id,"",
					"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=1000px;dialogHeight=800px;center=true");
					if ("success" == vReturnValue)
						window.location.reload();
				}
		   });
		
	}
	function sel(id) {
		window.showModalDialog("apply!sel.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=1000px;dialogHeight=800px;center=true");
	}
	 
	function del(id,approvalid,recordid) {
		if(approvalid!=""){
			alert("该人员状态已发生变化，不可以删除。");
			return;
		}		
		var param_recordid = <%=((SysUserEO)session.getAttribute("user")).getId()%>;
		var param_admintype = <%=((SysUserEO)session.getAttribute("user")).getAdminType()%>;
		if(param_admintype=='0'){
		}else if(param_recordid!=recordid){
			alert("您没有此权限");
			return;
		}else{}
		if (confirm('确定要删除吗')) {
			$.post("apply!del.action", {'entity.id': id},     
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
<form class="search_form" name="form1" action="apply!list.action" method="post">
	姓名<s:textfield name="name" id="name"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>应聘人员一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">姓名</td>
				<td class="td_header">应聘岗位</td>
				<td class="td_header">性别</td>
				<td class="td_header">年龄</td>
				<td class="td_header">应聘日期</td>
				<td class="td_header">审核人</td>
				<td class="td_header">状态</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                	<td class="td_c">${stuts.index+1}</td>
                    <td class="td_c">${name}</td>
                    <td class="td_c">${position}&nbsp;</td>
                    <td class="td_c">${sex}&nbsp;</td>
                    <td class="td_c">${age}&nbsp;</td>                    
                    <td class="td_c"><s:property value="recordtime.toString().substring(0,10)"/>&nbsp;</td>
                    <td class="td_c">${approval}&nbsp;</td>
                    <td class="td_c">${state}&nbsp;</td>
                    <td class="td_c">
                    	<a href="#" onclick="edit('${id}','${approvalid}')"><img border="0" alt="编辑" src="/images/change.png" ></a>
                    	&nbsp;&nbsp;<a href="#" onclick="sel('${id}')"><img border="0" alt="查看" src="/images/sel.png" ></a>
                        &nbsp;&nbsp;<a href="#" onclick="del('${id}','${approvalid}','${recordid}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                    </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				<s:if test="page.totalPages>1">
					<a href="apply!list.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="apply!list.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="apply!list.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="apply!list.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>
</html>
