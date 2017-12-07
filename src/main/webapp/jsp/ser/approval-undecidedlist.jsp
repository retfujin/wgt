<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
<%@ include file="/commons/meta.jsp"%>
<body>
<%
	String[] menuModel = { "menuModel2.addItem(203,'已审批列表','','approval!decidedlist.action',false);" 
						  +"menuModel2.addItem(204,'未审批列表','','approval!undecidedlist.action',false);"
						};
%>
<%@ include file="/menubar/simple/aa.jsp"%>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableBorder" align="center" id="approvalTable">
	<tr>
      <th class="tableHeaderText" height="25" colspan="8">待审批列表</th>
    </tr>
	<tr>
		<td class="forumRow" height="22">&nbsp;</td>
		<td class="forumRow">发送人</td>
		<td class="forumRow" width="30%">发送地址</td>
		
		<td class="forumRow" width="30%">消息内容</td>
		<td class="forumRow" align="center">提交时间</td>
		<td class="forumRow" align="center">信息类型</td>
	</tr>

	<s:iterator value="viewList" status="stuts">
		<tr <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	

			<td><input type="checkbox" name="checkbox1"
				value="<s:property value="id" />" /></td>
			<td><s:property value="userName" />&nbsp;</td>
			<td><s:property value="destAddString" />&nbsp;</td>
			<td><s:property value="smContent" /></td>
			<td align="center"><s:property value="subTime.toString().substring(2,16)" /></td>
			<s:if test="smsType==1">
				<td align="center">公告发布</td>
			</s:if>
			<s:elseif test="smsType==2">
				<td align="center"> 用户咨询</td>
			</s:elseif>
			<s:elseif test="smsType==3">
				<td align="center">节日祝福</td>
			</s:elseif>
			<s:elseif test="smsType==23">
				<td align="center">调查问卷</td>
			</s:elseif>
			<s:elseif test="smsType==24">
				<td align="center">短信催费</td>
			</s:elseif>
			<s:elseif test="smsType==25">
				<td align="center">批量发送</td>
			</s:elseif>
			<s:else>
			<td align="center">其他</td>		
			</s:else>
		</tr>
	</s:iterator>
</table><br>
<s:form name="formList" action="approval!updateapproval.action" method="post"
	theme="simple">
	<div>全选 <input type="checkbox" name="mmAll"
		onClick="checkAll(this, 'checkbox1')"> <input type="button"
		value="通过" onClick='javascript:subAll(2)' class="a"> <input
		type="button" value="否决" onClick='javascript:subAll(3)' class="a">
	<input type="hidden" name='recordId' id='recordId' value=''> <input
		type="hidden" name='isApproval' id='isApproval' value=''></div>
</s:form>
</body>
<script type="text/javascript">
function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}
//删除所有
function subAll(atype)
{
	var j=0;
	var delItems="";
	var tt = document.getElementsByName("checkbox1");
    for(var i=0;i<tt.length;i++){
           if(tt[i].checked==true){
     		  j+=1;
     		  delItems =delItems+","+tt[i].value;
           }
    }
    if(j<1){
  	     alert("请选择要审批的项！");   
  		 location.href="#"; 
    }else{  
  	  	if(!confirm("要审批选中信息?")){
    	     return;
    	}	
    	document.all.recordId.value=delItems.substring(1,delItems.length);
    	document.all.isApproval.value=atype;
  		formList.submit();
    } 
}
</script>
</html>