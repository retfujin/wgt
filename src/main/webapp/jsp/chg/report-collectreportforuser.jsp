<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<link href="/styles/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
  <tr>
	 <th height="25" colspan="20" class="tableHeaderText">收费项目统计表</th>
  </tr>
  <tr align="center">
    	<td class="forumRow" height="30" >序号</td>
    	<td class="forumRow" >收费项目</td>
    	<td class="forumRow" >收款金额</td>
   </tr>
  <s:iterator value="viewList" status="stuts">
  <tr  align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>   
   	<td height="22">&nbsp;${stuts.index+1}</td>
    <td><s:property value="viewList[#stuts.index][0]"/></td>
    <td><s:property value="viewList[#stuts.index][1]"/></td>
  </tr>
  </s:iterator>
  </table>
  
</body>
</html>