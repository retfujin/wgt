<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function add(){
		var vReturnValue = window.showModalDialog("employee!addtwo.action","",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=400px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function edit(id) {
		var vReturnValue = window.showModalDialog("employee!edittwo.action?entity.id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=400px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
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
			$.post("employee!del.action", {'entity.id': id},     
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
<form class="search_form" name="form1" action="employee!listtwo.action" method="post">
	部门<s:select name="departname" id="departname" list="viewList" listKey="name" listValue="name" theme="simple"></s:select>			 
	姓名<s:textfield name="name" id="name" size="10" theme="simple"/>
	年龄<s:textfield name="b_age" id="b_age" size="2"/>~<s:textfield name="e_age" id="e_age" size="2"/>
	合同类型<s:select list="#{'':'','1':'劳动合同','0':'劳务合同'}" name="contracttype" id="contracttype" cssStyle="width:80px;" theme="simple"></s:select>
	购买意外险<s:select list="#{'':'','1':'是','0':'否'}" name="insurance" id="insurance"  cssStyle="width:40px;" theme="simple"></s:select>
	离职时间<s:textfield name="b_enddate" id="b_enddate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="8" theme="simple"/>~<s:textfield name="e_enddate" id="e_enddate" readonly="true"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="8" theme="simple"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>管理人员一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">部门</td>
				<td class="td_header">姓名</td>
				<td class="td_header">性别</td>
				<td class="td_header">职位</td>
				<td class="td_header">出生年月</td>
				<td class="td_header">年龄</td>
				<td class="td_header">身份证号</td>
				<td class="td_header">联系方式</td>
				<td class="td_header">入职时间</td>
				<td class="td_header">签订合同类型<br>(劳动合同/劳务合同)</td>
				<td class="td_header">购买意外<br>伤害险</td>
				<td class="td_header">离职时间</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
               	<td class="td_c">${stuts.index+1}</td>
                <td class="td_c">${departname}&nbsp;</td>
                <td class="td_c" title="${remarks}">${name}&nbsp;</td>
                <td class="td_c">${sex}&nbsp;</td>
                <td class="td_c">${position}&nbsp;</td>
                <td class="td_c">${brithday}&nbsp;</td>
                <td class="td_c">${age}&nbsp;</td>
                <td class="td_c">${cardid}&nbsp;</td>
                <td class="td_c">${phone}&nbsp;</td>
                <td class="td_c">${entrydate}&nbsp;</td>
                <td class="td_c"><s:if test="contracttype.equals(\"1\")">劳动合同</s:if><s:else>劳务合同</s:else> &nbsp;</td>
                <td class="td_c"><s:if test="insurance.equals(\"1\")">是</s:if><s:else>否</s:else> &nbsp;</td>
                <td class="td_c">${enddate}&nbsp;</td>
                <td class="td_c">
                 <a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a>
                 &nbsp;&nbsp;<a href="#" onclick="del('${id}','${recordid}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                </td>
            </tr>	
            </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				<s:if test="page.totalPages>1">
					<a href="employee!listtwo.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="employee!listtwo.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="employee!listtwo.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="employee!listtwo.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>
</html>
