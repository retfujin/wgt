<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML>
<%@ include file="/commons/meta.jsp"%>
<body>
<%
	String[] menuModel = {"menuModel2.addItem(203,'已审批列表','','approval!decidedlist.action',false);" 
		  				 +"menuModel2.addItem(204,'未审批列表','','approval!undecidedlist.action',false);"
				         };
%>
<%@ include file="/menubar/simple/aa.jsp"%>
<table width="99%" border="0" cellpadding="0" cellspacing="0"
	class="tableBorder" align="center">
	<tr>
      <th class="tableHeaderText" height="25" colspan="8">已审批列表</th>
    </tr>
	<tr align="center">
		<td class="forumRow">发送人</td>
		<td class="forumRow">发送地址</td>
		<td class="forumRow">消息内容</td>
		<td class="forumRow">提交时间</td>
		<td class="forumRow">信息类型</td>
		<td class="forumRow">审批状态</td>
		<td class="forumRow">审批人</td>
		<td class="forumRow">审批时间</td>
	</tr>
	<s:iterator value="viewList" status="stuts">
		<tr <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	

			<td height="22" align="center"><s:property value="userName" />&nbsp;</td>
			<td title="<s:property value="destAddString"/>"><s:if test="destAddString.length()>30"><s:property value="destAddString.toString().substring(0,30)"/></s:if><s:else><s:property value="destAddString" /></s:else>&nbsp;</td>
			<td title="<s:property value="smContent" />"><s:if test="smContent.length()>15"><s:property value="smContent.toString().substring(0,15)"/></s:if><s:else><s:property value="smContent" /></s:else></td>
			<td align="center"><s:property value="subTime.toString().substring(0,10)" /></td>
			<td align="center"><s:if test="smsType==1">
				公告发布
			</s:if>
			<s:elseif test="smsType==2">
				用户咨询
			</s:elseif>
			<s:elseif test="smsType==3">
				节日祝福
			</s:elseif>
			<s:elseif test="smsType==23">
				调查问卷
			</s:elseif>
			<s:elseif test="smsType==24">
				短信催费
			</s:elseif>
			<s:elseif test="smsType==25">
				批量发送
			</s:elseif>
			<s:else>
			其他
			
			</s:else>
			
			</td><td align="center">
			<s:if test="fsStatus==2">
				审批通过
			</s:if>
			<s:elseif test="fsStatus==3">
				审批被否决
			</s:elseif>
			</td>
			<td align="center"><s:property value="approvalPerson" /></td>
			<td align="center"><s:property value="approvalTime.toString().substring(0,10)" /></td>

		</tr>
	</s:iterator>
</table>

<div id="pageBar"></div>
<script type="text/javascript">
            document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
</script>
</body>
</html>
