﻿<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>

<script type="text/javascript" src="/js/check.js"></script>

<script type="text/javascript" src="/easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/exportTableToExcel.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/Toolbar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/Toolbar.js"></script>
<script type="text/javascript">
function exportExl(){
	  exportExl('11');
}
</script>
</head>
<body>
<% 	
    
    int nowYear = request.getParameter("nowYear") !=null ? Integer.parseInt(request.getParameter("nowYear")) :new Date().getYear()+1900;
	int _nowYear = nowYear;
%>


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><U><s:property value="%{#parameters.year}"/></U>&nbsp;&nbsp;&nbsp;&nbsp;物业费年度统计表</p> 
           </div>
           <table class="table_border"  id="exportTable"  width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >楼栋名称</td>
    	<td class="td_header" >应收款</td>
    	<td class="td_header" >已收款</td>
    	<td class="td_header" >优惠金额</td>
    	<td class="td_header" >欠费金额</td>
    	<td class="td_header" >补交<%=--nowYear %>年金额</td>
    	<td class="td_header" >补交<%=--nowYear %>年金额</td>
    	<td class="td_header" >补交<%=--nowYear %>年金额</td>
    	<td class="td_header" >补交<%=nowYear %>年前金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><a href="report!yearhousereport.action?edificeId=<s:property value="viewList[#stuts.index][10]"/>&nowYear=<%=_nowYear%>"><s:property value="viewList[#stuts.index][0]"/></a></td>
    <td class="td_c" id="a1"><s:property value="viewList[#stuts.index][1]"/></td>
    <td class="td_c" id="a2"><s:property value="viewList[#stuts.index][2]"/></td>
    <td class="td_c" id="a3"><s:property value="viewList[#stuts.index][3]"/></td>
    <td class="td_c" id="a4"><s:property value="viewList[#stuts.index][4]"/></td>
    <td class="td_c" id="a5"><s:property value="viewList[#stuts.index][5]"/></td>
    <td class="td_c" id="a6"><s:property value="viewList[#stuts.index][6]"/></td>
    <td class="td_c" id="a7"><s:property value="viewList[#stuts.index][7]"/></td>
    <td class="td_c" id="a8"><s:property value="viewList[#stuts.index][8]"/></td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header">合计</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="a1sum" >&nbsp;</td>
					<td class="td_header" id="a2sum" >&nbsp;</td>
					<td class="td_header" id="a3sum" >&nbsp;</td>
					<td class="td_header" id="a4sum" align="center">&nbsp;</td>
					<td class="td_header" id="a5sum" align="center">&nbsp;</td>
					<td class="td_header" id="a6sum" align="center">&nbsp;</td>
					<td class="td_header" id="a7sum" align="center">&nbsp;</td>
					<td class="td_header" id="a8sum" >&nbsp;</td>
				</tr>
      		</table>
           <div class="table_footer">
           	<a href="###" class="add fl" onclick="exportExl()">导出</a>
           </div>
       </div>
       <!---table_area结束--->
   </div>

 
</body>
</html>
<script type="text/javascript">
	doSum(a1,a1sum);
	doSum(a2,a2sum);
	doSum(a3,a3sum);
	doSum(a4,a4sum);
	doSum(a5,a5sum);
	doSum(a6,a6sum);
	doSum(a7,a7sum);
	doSum(a8,a8sum);
</script>