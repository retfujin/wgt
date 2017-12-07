<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
function add(){
	var vReturnValue = window.showModalDialog("garage!add.action","",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=330px;center=true");
	if ("success" == vReturnValue)
	window.location.reload();
}
function addall(){
	var vReturnValue = window.showModalDialog("garage!initcar.action","",
		"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=270px;center=true");
}
function caraccount(){
	var vReturnValue = window.showModalDialog("garage!statistics.action","",
		"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=550px;center=true");
	if ("success" == vReturnValue)
		window.location.reload();
}
function carinit(){
	var vReturnValue = window.showModalDialog("garage!initcarport.action","",
  		"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=270px;center=true");
}
	function edit(id) {
		var vReturnValue = window
				.showModalDialog(
						"garage!edit.action?id=" + id,
						"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=330px;center=true");
		if ("success" == vReturnValue) {
			window.location.reload();
		}
	}
	function del(id) {
		if (confirm('确定要删除吗')) {
			$.post("garage!del.action", {'entity.id': id},     
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
 
<form class="search_form" name="form1" action="garage!list.action" method="post">
	管理处名称<s:select name="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"></s:select>
	车位类型<s:select name="type" list="#{'':'全部','1':'机动车','2':'非机动车'}" theme="simple" />
	状态<s:select name="state" list="#{'':'全部','空置':'空置','出租':'出租','出售':'出售'}" theme="simple" />
	车位编号<s:textfield name="carCode" id="carCode" theme="simple"/>
	位置<s:textfield name="local" id="local" theme="simple"/>
	<input type="hidden" value="-1"  name="hidd"/>
	<input type="submit" value="查询" class="search_btn" />
</form> 
 
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>车位列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header">所属小区</td>
				<td class="td_header">车位编号</td>
				<td class="td_header">车位种类</td>
				<td class="td_header">位置</td>
				<td class="td_header">面积</td>
				<td class="td_header">状态</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
				<td class="td_c">${area.areaName}</td>
				<td class="td_c">${carCode}</td>
				<td class="td_c">
					<s:if test="type==1">机动车</s:if><s:else>非机动车</s:else>
				</td>
				<td class="td_c">${local}</td>
				<td class="td_c">${mianji}&nbsp;</td>
				<td class="td_c">${state}</td>
                   <td class="td_c">
                   	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a> 
                       &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                   </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
           	<a href="###" class="add fl" onclick="addall()">上传</a>
           	<a href="###" class="add fl" onclick="caraccount()">统计</a>
           	<a href="###" class="add fl" onclick="carinit()">期初费</a>
           		<p>
            	<div id="pageBar" ></div>
            	<script type="text/javascript">
				document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
				</script>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束---> 
 

</body>
</html>