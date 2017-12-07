<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<%@ include file="/commons/meta.jsp"%>
<title>列表</title>
</head>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="../js/check.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
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
			title:'已处理投诉',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>

<div id="disSel">

<form class="search_form" name="form1" action="suit!list2.action" method="post">
	小区名称<s:select name="areaId" list="retList" listKey="id" listValue="areaName" value="@java.lang.Integer@parseInt(#parameters.areaId)" theme="simple"></s:select>
	投诉类型<s:select name="type" id="type" headerKey="" headerValue="--请选择投诉类型--" list="#{'有效':'有效','无效':'无效'}" value="#parameters.type" theme="simple"></s:select>
	投诉日期<INPUT id="beginDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" name="beginDate" value="${param.beginDate}"/>
			至<INPUT id="endDate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="10" name="endDate"  value="${param.endDate}" />
	房间编号<INPUT id="houseId" size="12" name="houseId" value="${param.houseId}" />
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>未处理投诉一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header">编号</td>
				<td class="td_header">客户姓名</td>
				<td class="td_header">联系电话</td>
				<td class="td_header">客户地址</td>
				<td class="td_header">投诉方式</td>
				<td class="td_header">投诉时间</td>
				<td class="td_header">投诉类型</td>
				<td class="td_header">投诉内容</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
				<td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><s:property value="suitName" /></td>
				<td class="td_c"><s:property value="suittel" />&nbsp;</td>
				<td class="td_c"><s:property value="suitDepartment" />&nbsp;</td>
				<td class="td_c"><s:property value="suitWay" />&nbsp;</td>
				<td class="td_c"><s:property value="suitDate.toString().substring(0,10)" />&nbsp;</td>
				<td class="td_c"><s:property value="suitType" />&nbsp;</td>
				<td class="td_c"><s:property value="suitMess" />&nbsp;</td>
				<td class="td_c">
				<a href="suit!print.action?id=${id}" target="_blank"><img src="/images/print.gif" width="14" height="14" alt="打印" border="0" /></a>
	 			&nbsp;&nbsp;
				<s:if test="flowState<2">
	 				<a href="#" onclick="edit('${id}')"><img src="/images/edit.gif" alt="编辑" border="0" /></a>
	 			</s:if>
	 			<s:else>
	 				<a href="#" onclick="_select('${id}')"><img src="/images/search.gif" alt="查看" border="0" /></a>
	 			</s:else>
	 		&nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img src="/images/del.gif" alt="删除" border="0" /></a>	
			</td>
                </tr>	
              </s:iterator>
             </table>
           <div class="table_footer">
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
 
  
</div>
<script type="text/javascript"> 
function _select(id) {
	var vReturnValue = window
	.showModalDialog(
			"suit!view2.action?id="+id,
			"编辑客户请修登记表",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=820px;center=true");
}

	
	function edit(id) {
		var vReturnValue = window
				.showModalDialog(
						"suit!edit2.action?id="+id,
						"编辑客户请修登记表",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=650px;center=true");
	
		if ("success" == vReturnValue) {
			window.location.reload();
		}
	}
	
	function del(id){

		if (confirm('确定要删除吗')) {
			$.post("suit!del.action", {'id': id},
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
