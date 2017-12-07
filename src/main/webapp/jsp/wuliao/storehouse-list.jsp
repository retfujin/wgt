<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
	function add(){
		var vReturnValue = window.showModalDialog("storehouse!add.action","",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=580px;dialogHeight=406px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function edit(id) {
		var vReturnValue = window.showModalDialog("storehouse!edit.action?id=" + id,"",
				"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=580px;dialogHeight=406px;center=true");
		if ("success" == vReturnValue)
			window.location.reload();
	}
	function del(id) {
		if (confirm('确定要删除吗')) {
			$.post("storehouse!del.action", {'e.id': id},
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
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>仓库一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
               <td class="td_header">仓库编号</td>
			   <td class="td_header">仓库名称</td>
			   <td class="td_header">管理员</td>
			   <td class="td_header">操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${id}&nbsp;</td>
			   	<td class="td_c">${name}&nbsp;</td>
			   	<td class="td_c">${adminUser}&nbsp;</td>
			 	<td class="td_c">
                   	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png"></a>
                    &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png"></a>
                </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer" id="footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>	 
           </div>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
</body>
</html>