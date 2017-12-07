<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>列表</title>
</head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
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
						"plan!add.action",
						"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=530px;center=true");
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
<body>
<div id="toolbar" style="width:100%;"></div>

<table width="90%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
	<tr>
		<th>工作计划标题</th>
		<th>录入日期</th>
		<th>审批环节</th>
		<th>审批状态</th>
		<th></th>
	</tr>
	<s:iterator id="templates" value="page.result" status="stuts">
		<tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>
			<td>${planTitle}</td>
			<td>${recordDate}</td>
			<td>${process.moduleName}</td>
			<td><s:if test="process.state==0">待审批</s:if><s:elseif test="process.state==1">审批通过</s:elseif><s:elseif test="process.state==2">不通过</s:elseif></td>
			<s:if test="process==null">
			<td>
				<a href="javascript:void(0)"><img alt="修改" border="false" src="/images/edit.gif" onclick="edit('${id}')" /></a>
				<a href="javascript:void(0)"><img alt="删除" border="false" src="/images/del.gif" onclick="del('${id}')" /></a>
			</td>
			</s:if>
			<s:else>
				<td>
					<a href="javascript:void(0)"><img alt="详情" border="false" src="/images/search.gif" onclick="view('${id}')" /></a>
					&nbsp;&nbsp;&nbsp;
				</td>
			</s:else>
		</tr>
	</s:iterator>
</table>
<br/>
<br/>
<div id="pageBar" >${page.showPage}</div>


<script language="javascript">

function edit(id){

	var vReturnValue = window.showModalDialog("plan!edit.action?id="+id,"",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=820px;dialogHeight=530px;center=true");
	if ("success" == vReturnValue) {
		window.location.reload();
	}
}
function view(id){

	var vReturnValue = window.showModalDialog("plan!view.action?id="+id,"",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=800px;dialogHeight=500px;center=true");
	if ("success" == vReturnValue) {
		window.location.reload();
	}
}
function del(id){

	if (confirm('确定要删除吗')) {
		$.post("plan!del.action", {'id': id},     
				function (data, textStatus){
					var responseText =  eval('(' + data + ')');
					
					if(responseText.success){
						alert("删除成功");
						window.location.reload();
					}else{
						alert(responseText.msg);
					}
				}
			);
	}
}

</script>
  
  
</body>
</html>
