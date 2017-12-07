<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/exportTableToExcel.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
function checkYear(){
	if(document.getElementById('year').value==''){
		alert("请选择查询时间");
		return false;
	}
	return true;
}
</script>

<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/Toolbar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/Toolbar.js"></script>
<script type="text/javascript">
 function exportExl(){
        	  exportExl('11');
          }
         
</script>
<% 	
    int year = request.getParameter("year") !=null ? Integer.parseInt(request.getParameter("year")) :new Date().getYear()+1900;
	int nowYear = year;
%>  
  
<form class="search_form" name="form1" action="report!yearreport.action" method="post" onsubmit="checkYear()">
	请选择查询时间<input type="text" name="year" value="${param.year}" onclick="WdatePicker({dateFmt:'yyyy'})" size="12" />
	<input type="submit" value="查询" class="search_btn" />
</form>


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><U><s:property value="%{#parameters.year}"/></U>&nbsp;&nbsp;&nbsp;&nbsp;物业费年度统计表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header" >管理处</td>
		    	<td class="td_header" >应收款</td>
		    	<td class="td_header" >已收款</td>
		    	<td class="td_header" >优惠金额</td>
		    	<td class="td_header" >欠费金额</td>
		    	<td class="td_header" >补交<%=--year %>年金额</td>
		    	<td class="td_header" >补交<%=--year %>年金额</td>
		    	<td class="td_header" >补交<%=--year %>年金额</td>
		    	<td class="td_header" >补交<%=year %>年前金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                    <td class="td_c">${stuts.index+1}</td>
				 <td class="td_c"><a href="report!yearedificereport.action?areaId=<s:property value="viewList[#stuts.index][10]"/>&nowYear=<%=nowYear%>"><s:property value="viewList[#stuts.index][0]"/></a></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][1]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][3]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][4]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][5]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][6]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][7]"/></td>
                </tr>	
              </s:iterator> 
      		</table> 
      		<div class="table_footer">
           	<a href="###" class="add fl" onclick="exportExl()">导出</a>
           </div>
       </div>
       <!---table_area结束--->
   </div>
 

</body>
</html>