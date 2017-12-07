<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<link href="../styles/style.css" rel="stylesheet" type="text/css">
<%@ include file="/commons/meta.jsp" %>   

<body>
<% 	


//集中式平台
	String[] menuModel={"menuModel2.addItem(206,'返回','','',false,'','window.history.back()');"
	};
%>

<%@ include file="/menubar/simple/aa.jsp" %>
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
    <tr>
      <th class="tableHeaderText" height=25 colspan="13">信息发送详情</th>
    </tr>
    <tr>
    <td class="forumRow">发送人</td>
    <td class="forumRow">手机号</td>
    <td class="forumRow">信息内容</td>
    <td class="forumRow">接收时间</td>
    <td class="forumRow">发送状态</td>
  </tr>
  <s:iterator value="viewList" status="stuts">
  <tr <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
    <td class=""><s:property value="viewList[#stuts.index][0]"/></td>
    <td class=""><s:property value="viewList[#stuts.index][1]"/></td>
    <td class=""><s:property value="viewList[#stuts.index][2]"/></td>
    <td class=""><s:property value="viewList[#stuts.index][3].toString().substring(5,16)"/></td>
    <td class="">
    	<s:if test="viewList[#stuts.index][4]==0">成功</s:if>
    	<s:else>失败</s:else>    	
    </td>
  </tr>
  </s:iterator>
</table>
</body>
</html>