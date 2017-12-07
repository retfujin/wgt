<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
</head>
<body>
<% 	
	String chargeIds = request.getParameter("chargeIds");
String[] menuModel={
		"menuModel2.addItem(203,'确定生成','','generate!previewresult.action?chargeIds="+chargeIds+"',false);"
		
};
%>
<%@ include file="/menubar/simple/aa.jsp" %>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>生成费用预览</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
               	<td class="td_header" >序号</td>
		    	<td class="td_header" >房间编号</td>
		    	<td class="td_header" >客户名称</td>
		    	<td class="td_header" >收费名称</td>
		    	<td class="td_header" >应缴月份</td>
		    	<td class="td_header" >应缴金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">&nbsp;${stuts.index+1}</td>
			    <td class="td_c">${house.id}</td>
			    <td class="td_c">${ownerName}</td>
			    <td class="td_c">${chargeName}</td>
			    <td class="td_c"><s:date name="recordMonth" format="yyyy-MM"/></td>
			    <td class="td_c">${oughtMoney}</td>
            </tr>	
              </s:iterator>
      		</table> 
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
  
</body>
</html>