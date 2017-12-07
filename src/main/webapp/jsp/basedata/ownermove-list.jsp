<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function add(){
      	var vReturnValue = window.showModalDialog("ownermove!add.action",	"",
    			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=605px;dialogHeight=515px;center=true");
			if ("success" == vReturnValue)
				window.location.reload();
    }
	
	function edit(id) {
		var vReturnValue = window.showModalDialog("ownermove!edit.action?id=" + id,	"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=605px;dialogHeight=515px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function del(id) {
		if (confirm('确定要删除吗')) {
			$.post("ownermove!del.action", {'id': id},     
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
<form class="search_form" name="form1" action="ownermove!list.action" method="post">
	小区名称<s:select name="areaId" list="retList" listKey="id"	listValue="areaName" theme="simple"></s:select>
	业主姓名<s:textfield name="ownerName" theme="simple"/>
	拟搬时间<s:textfield name="carryDate" theme="simple"/>
	<input type="submit" value="查询" class="search_btn" />
</form>
 
 

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>出入登记一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class='td_header'>序号</td>
				<td class='td_header'>业主姓名</td>
				<td class='td_header'>住址</td>
				<td class='td_header'>拟搬时间</td>
				<td class='td_header'>搬运人</td>
				<td class='td_header'>搬运人证件号</td>
				<td class='td_header'>搬运人联系电话</td>
				<td class='td_header'>有无欠费情况</td>
				<td class='td_header'>业主意见</td>
				<td class='td_header'>放行条号</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c">${ownerName}&nbsp;</td>
				<td class="td_c">${houseAddress}&nbsp;</td>
				<td class="td_c">${carryDate}&nbsp;</td>
				<td class="td_c">${carryName}&nbsp;</td>
				<td class="td_c">${carryNumber}&nbsp;</td>
				<td class="td_c">${carryPhone}&nbsp;</td>
				<td class="td_c">${overdue}&nbsp;</td>
				<td class="td_c">${ownerOpinion}&nbsp;</td>
				<td class="td_c">${releasePass}&nbsp;</td>
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
