<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/commons/meta.jsp" %>
<html>
<head>

<script type="text/javascript" src="/js/check.js"></script>

<script type="text/javascript" src="/easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/exportTableToExcel.js"></script>
<script type="text/javascript">
function exportExl(){
	  exportExl('11');
}
</script>
</head>
<body>
<% 	
    
    int nowYear = request.getParameter("nowYear") !=null ? Integer.parseInt(request.getParameter("nowYear")) :new Date().getYear()+1900;
    String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
%> 


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><U><s:property value="%{#parameters.startDate}"/>到<s:property value="%{#parameters.endDate}"/></U>&nbsp;&nbsp;&nbsp;&nbsp;物业费月度统计表</p> 
           </div>
           <table class="table_border"  id="exportTable"  width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >房间名称</td>
			    	<td class="td_header" >应收款</td>
			    	<td class="td_header" >已收款</td>
			    	<td class="td_header" >优惠金额</td>
			    	<td class="td_header" >欠费金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"> <s:property value="viewList[#stuts.index][5]"/></td>
			    <td class="td_c" id="a1"><s:property value="viewList[#stuts.index][1]"/></td>
			    <td class="td_c" id="a2"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c" id="a3"><s:property value="viewList[#stuts.index][3]"/></td>
			    <td class="td_c" id="a4"><s:property value="viewList[#stuts.index][4]"/></td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header">合计</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="a1sum" >&nbsp;</td>
					<td class="td_header" id="a2sum" >&nbsp;</td>
					<td class="td_header" id="a3sum" >&nbsp;</td>
					<td class="td_header" id="a4sum" align="center">&nbsp;</td>
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
</script>