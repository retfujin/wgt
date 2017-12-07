<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
function add(){
	var vReturnValue = window.showModalDialog("ownerdecorate!add.action","",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=620px;center=true");
	if ("success" == vReturnValue)
		window.location.reload();
}
function edit(id) {
	var vReturnValue = window.showModalDialog("ownerdecorate!edit.action?id=" + id,	"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=692px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
}
function del(id) {
	if (confirm('确定要删除吗')) {
		$.post("ownerdecorate!del.action", {'id': id},     
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

<form class="search_form" name="form1" action="ownerdecorate!list.action" method="post">
	小区名称<s:select name="areaId" list="retList" listKey="id"	listValue="areaName" theme="simple"></s:select>
	房间编号<s:textfield name="houseId" id="houseId"/>
	<input type="submit" value="查询" class="search_btn" />
</form>
 
 
 
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>装修记录一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">房间编号</td>
				<td class="td_header">房间地址</td>
				<td class="td_header">姓名</td>
				<td class="td_header">联系电话</td>
				<td class="td_header">手续办理</td>
				<td class="td_header">装修起始时间</td>
				<td class="td_header">验收时间</td>
				<td class="td_header">交费情况</td>
				<td class="td_header">备注</td>
				<td class="td_header">装修人员和验收</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><s:property value="houseId" />&nbsp;</td>
				<td class="td_c"><s:property value="housedz" />&nbsp;</td>
				<td class="td_c"><s:property value="ownerName" />&nbsp;</td>
				<td class="td_c"><s:property value="decoratetel" />&nbsp;</td>
				<td class="td_c"><s:property value="decoratesxbl" />&nbsp;</td>
				<td class="td_c"><s:if test="decorateindate!=null">
						<s:property value="decorateindate.toString().substring(0,10)" />
					</s:if>&nbsp;</td>
				<td class="td_c"><s:if test="decorateoutdate!=null">
						<s:property value="decorateoutdate.toString().substring(0,10)" />
					</s:if>&nbsp;</td>
				<td class="td_c"><s:property value="decoratecontributions" /></td>
				<td class="td_c"><s:property value="decoratenotes" />&nbsp;</td>
                <td class="td_c">
                   	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a> 
                       &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                </td>
                </tr>	
              </s:iterator>
              </table>
           <div class="table_footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
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