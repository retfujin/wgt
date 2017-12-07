<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
    <title>列表</title>
</head>
<%@ include file="/commons/meta.jsp" %>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
<script type="text/javascript">
 function add(){
        	  var vReturnValue = window
				.showModalDialog(
						"role!add.action",
						"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=35;center=true");
			if ("success" == vReturnValue) {
				window.location.reload();
			}
          }
        
</script>
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
			title:'权限组管理',
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
              <p>权限组列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
   				<td class="td_header">权限组名</td>
   				<td class="td_header">权限组描述</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
            	<td class="td_c"><s:property value="#stuts.count+10*(page.pageNo-1)"/></td>
		    	<td class="td_c"><s:property value="name"/></td>
		    	<td class="td_c"><s:property value="description"/>&nbsp;</td>
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
					<a href="role!list.action?page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="role!list.action?page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="role!list.action?page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="role!list.action?page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   
</div>
<script language="javascript">
function edit(id){

	var vReturnValue = window.showModalDialog("role!edit.action?id="+id,"",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=35;center=true");
	if ("success" == vReturnValue) {
		window.location.reload();
	}
}
function del(id){

	if (confirm('确定要删除吗')) {
		$.post("role!del.action", {'id': id},     
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
</body>
</html>