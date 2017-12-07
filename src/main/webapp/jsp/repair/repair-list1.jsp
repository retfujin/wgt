﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/commons/meta.jsp" %>
<html>
<head>
<script type="text/javascript"> 
	function _select(id) {
		var vReturnValue = window
				.showModalDialog(
						"repair!view.action?id="+id,
						"查看客户请修登记表",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=40;center=true");
	}
	function edit(id) {
		var vReturnValue = window
				.showModalDialog(
						"repair!edit1.action?id="+id,
						"编辑客户请修登记表",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=40;center=true");
	
		if ("success" == vReturnValue) {
			window.location.reload();
		}
	}
	function del(id){

		if (confirm('确定要删除吗')) {
			$.post("repair!del.action", {'customerrepair.id': id},     
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
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../../js/func.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
</head>
<body>
<script type="text/javascript">
var tabpanel;
$(document).ready(function(){
	  tabpanel = new TabPanel({
		renderTo:'tab',
		autoResizable:true,
	//	border:'none',
		active : 0,
		maxLength : 10,
		items : [{
			id:'tab1',
			title:'待保修处理',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>

<div id="disSel">



<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>待处理客户请修一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header" >序号</td>
		   		<td class="td_header" >受理日期</td>
		   		<td class="td_header" >客户姓名、联系<br>电话及地址</td>
		   		<td class="td_header" >请修<br>内容</td>
		   		<td class="td_header" >派工<br>单号</td>
		   		<td class="td_header" >完成<br>时间</td>
		   		<td class="td_header" >维修<br>人员</td>
		   		<td class="td_header" >维修<br>结果</td>
		   		<td class="td_header" >操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr> 
				<td class="td_c">${stuts.index+1 }</td>
			   	<td class="td_c"><s:property value="acceptedDate"/>&nbsp;</td>
			   	<td class="td_c"><s:if test="content==null || content==''"><s:property value="comaddress"/></s:if><s:else><s:property value="content" /></s:else>&nbsp;</td>
			   	<td class="td_c"><s:property value="repairContext" />&nbsp;</td>
			 	<td class="td_c"><s:property value="dispatcherNum" />&nbsp;</td>
			 	<td class="td_c"><s:property value="achieveDate" />&nbsp;</td>
			 	<td class="td_c"><s:property value="wxname" />&nbsp;</td>
			 	<td class="td_c"><s:property value="repairResult" />&nbsp;</td>
			 	<td class="td_c">
			 		<a href="repair!print.action?id=${id}" target="_blank"><img src="/images/print.gif" width="14" height="14" alt="打印" border="0" /></a>
			 		&nbsp;&nbsp;
			 		<a href="#" onclick="edit('${id}')"><img src="/images/change.png" alt="编辑" border="0" /></a>&nbsp;
			 		<a href="#" onclick="_select('${id}')"><img src="/images/search.gif" alt="查看" border="0" /></a>
			 		&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>	
			 	</td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer">
            	<p>第${page.pageNo}页, 共${page.totalPages}页
				
				<s:if test="page.totalPages>1">
					<a href="repair!list1.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="repair!list1.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="repair!list1.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="repair!list1.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div> 
   
</div>
</body>
</html>
