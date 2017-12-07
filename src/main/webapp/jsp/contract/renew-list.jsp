<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
	function add(){
		var vReturnValue = window.showModalDialog("renew!add.action","",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=820px;dialogHeight=600px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function sel(id) {
		window.showModalDialog("renew!sel.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=500px;center=true");
	} 
	function del(id,recordid) {
		var param_recordid = <%=((SysUserEO)session.getAttribute("user")).getId()%>;
		var param_admintype = <%=((SysUserEO)session.getAttribute("user")).getAdminType()%>;
		if(param_admintype=='0'){
		}else if(param_recordid!=recordid){
			alert("您没有此权限");
			return;
		}else{}
		if (confirm('确定要删除吗')) {
			$.post("renew!del.action", {'entity.id': id},     
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
<form class="search_form" name="form1" action="renew!list.action" method="post">
	部门<s:textfield name="departname" id="departname"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>劳动合同续签申请一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">姓名</td>
				<td class="td_header">年龄</td>
				<td class="td_header">性别</td>
				<td class="td_header">学历</td>
				<td class="td_header">部门</td>
				<td class="td_header">岗位</td>
				<td class="td_header">合同开始日期</td>
				<td class="td_header">合同截止日期</td>
				<td class="td_header">申请日期</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
                <td class="td_c">${name}&nbsp;</td>
                <td class="td_c">${age}&nbsp;</td>
                <td class="td_c">${sex}&nbsp;</td>
                <td class="td_c">${education}&nbsp;</td>
                <td class="td_c">${departmentname}&nbsp;</td>
                <td class="td_c">${position}&nbsp;</td>
                <td class="td_c"><s:property value="begindate.toString().substring(0,10)"/> &nbsp;</td>
                <td class="td_c"><s:property value="enddate.toString().substring(0,10)"/>&nbsp;</td>
                <td class="td_c"><s:property value="applydate.toString().substring(0,10)"/>&nbsp;</td>
                <td class="td_c">
                	&nbsp;&nbsp;<a href="#" onclick="sel('${id}')"><img border="0" alt="查看" src="/images/sel.png" ></a>
                    &nbsp;&nbsp;<a href="#" onclick="del('${id}','${recordid}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                </td>
             </tr>	
             </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				<s:if test="page.totalPages>1">
					<a href="renew!list.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="renew!list.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="renew!list.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="renew!list.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>
</html>
