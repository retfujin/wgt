<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/func.js"></script>
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
          text : '新增公告',
          bodyStyle : 'new',
          useable : 'T',
          handler : function(){
        	  var vReturnValue = window
				.showModalDialog(
						"bulletin-bulletin.action",
						"",
						"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=35;center=true");
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
<%@ include file="/commons/meta.jsp" %>
<body>
<div id="toolbar" style="width:100%;"></div>
<div id ="disSel">
<form name="form1" action="bulletin!listbull.action"  method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
    
    <td>手机号码：<s:textfield name="phone" theme="simple"/></td>
    <td>开始日期：<s:textfield name="beginDate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/></td>
	<td>截止日期：<s:textfield name="endDate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" theme="simple"/> </td>
	<td >
	<input type="hidden" value="-1"  name="hidd"/>
	<input type="submit" value="查询"  class="a"/>
	<input type="button" value="导出"  class="a" onclick="getNowCarportDown();"/>
	</td>
</tr>
</table>
</form>
</div>
  

<form name="formList"  action="bulletin!delMultBull.action">
  <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder" id="bullTable">
  <tr>
      <th height="25" colspan="9" class="tableHeaderText">小区已发公告列表</th>
    </tr>	
	<tr align="center">
		<td class="forumRow" >选择</td>
		<td class="forumRow" >发送对象</td>	
		<td class="forumRow" >发送内容</td>
		<td class="forumRow" >发送日期</td>
	</tr>
	<s:iterator value="viewList" status="stuts">
	<tr <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
  		<td align="center" width="50"><input type="checkbox" name="checkbox" value="<s:property value="id" />" onClick="checkItem(this, 'mmAll')">&nbsp;<s:property value="#stuts.index+1" /></td>
		<td><s:if test="sendAddress.toString().length()> 23"><s:property value="sendAddress.toString().substring(0,23)" />...&nbsp;</s:if><s:else><s:property value="sendAddress" />&nbsp;</s:else> </td>
		<td title="<s:property value='content'/>"><s:if test="content.toString().length() > 60"> <s:property value="content.toString().substring(0,60)" />...&nbsp;</s:if><s:else><s:property value="content" /></s:else>  </td>
		<td align="center"><s:property value="sendTime.toString().substring(0,16)" />&nbsp;</td>
	</tr>
	</s:iterator>
	<tr align="right">
		<td colspan="6" bgcolor="#FFF5EC">
			全选<input type="checkbox" name="mmAll" onClick="checkAll(this, 'checkbox')">
			<input type="button" value="删除" onClick='javascript:delAll()' class="a">
			<input type="hidden" name='checkList' id='checkList' value=''>
			<s:if test="pageNo!=''"><s:hidden name="pageNo" value="%{#parameters.pageNo}"/></s:if><s:else><s:hidden name="pageNo" value="1"/></s:else>
		</td>
	</tr>
</table>
</form>
<div id="pageBar" ></div>
<script type="text/javascript">
	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
</script>
<SCRIPT LANGUAGE="JavaScript">
function delAll()
{


	 //检查是否选择了
     var gvTb = document.getElementById("bullTable");
     var selectCount = 0;
     for (var i = 2; i < gvTb.rows.length-1; i++) {
         if (gvTb.rows[i].cells[0].getElementsByTagName("input")[0].checked) {
           selectCount++;
           break;
         }
     }
     if (selectCount == 0) {
    	 alert("请选择要删除的信息");
 		return;
     }else{
    	if(!confirm("确认删除所选公告？")){
	         return;
	    }
    	formList.submit();
     }
	
}

function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}
function checkItem(e, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
     if(!aa[i].checked) return;
    all.checked = true;
  }
}
</SCRIPT>
</body>
</html>