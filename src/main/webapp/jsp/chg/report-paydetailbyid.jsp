<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
</head>
<body>
  <table width="300"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr align="center">
    	<td class="forumRow" height="30" >类型</td>
    	<td class="forumRow" >金额</td>
    	<td class="forumRow" >原因</td>
   </tr>
  <s:iterator value="elist" status="stuts">
  <tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>   
   	<td height="28"><s:property value="elist[#stuts.index][0]"/></td>
    <td><s:property value="elist[#stuts.index][1]"/></td>
    <td><s:property value="elist[#stuts.index][2]"/></td>
  </tr>
  </s:iterator>
  </table>
</body>
</html>
