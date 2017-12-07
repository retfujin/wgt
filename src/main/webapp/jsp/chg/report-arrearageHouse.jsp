<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
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

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>物业费欠费统计表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
				<td class="td_header" >房间号</td>
    	<td class="td_header" >房间地址</td>
    	<td class="td_header" >客户名称</td>
    	<td class="td_header" >欠收款</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c" >&nbsp;${stuts.index+1}</td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][0]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][1]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c"><a title="点击查看欠费明细" target="_blank" href="report!arrearagedetail.action?houseId=<s:property value="viewList[#stuts.index][0]"/>"><s:property value="viewList[#stuts.index][3]"/></a></td>
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