<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="/easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/js/exportTableToExcel.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/Toolbar.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/Toolbar.js"></script>
<script type="text/javascript">
 function export(){
        	  exportExl('11');
          }
      
</script>
</head>
<body>

<% 	
    String endYearMonth = (String)request.getAttribute("endYearMonth");
	String areaId = request.getParameter("areaId");
	String areaName= request.getParameter("areaName");
%> 

 
<form class="search_form" name="form1" action="report!arrearageEdifice.action" method="post">
	请选择查询时间<input type="text" name="endYearMonth" value="<%=endYearMonth%>" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="12" readonly="readonly"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>截止至<U><%=endYearMonth%></U>&nbsp;&nbsp;&nbsp;&nbsp;<%=areaName%>物业费欠费统计表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >楼栋名称</td>
		    	<td class="td_header" >欠费户数</td>
		    	<td class="td_header" >欠费金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><a href="report!arrearageHouse.action?edificeId=<s:property value="viewList[#stuts.index][0]"/>&endYearMonth=<%=endYearMonth%>"><s:property value="viewList[#stuts.index][1]"/></a></td></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][3]"/></td>
                </tr>	
              </s:iterator> 
      		</table> 
      		<div class="table_footer">
           	<a href="###" class="add fl" onclick="export()">导出</a>
           </div>
       </div>
       <!---table_area结束--->
   </div>
    

</body>
</html>