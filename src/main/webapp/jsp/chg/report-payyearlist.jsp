<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function checkPayYear(){
		if(document.getElementById('startDate').value=='' || document.getElementById('endDate').value==''){
			alert("请选择缴费起始时间和截止时间");
			return false;
		}
		return true;
	}
</script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript" src="/tabpanel/js/Fader.js"></script>
<script type="text/javascript" src="/tabpanel/js/TabPanel.js"></script>
</head>
<body>
<script type="text/javascript">
var tabpanel;
$(document).ready(function(){
	  tabpanel = new TabPanel({
		renderTo:'tab',
		autoResizable:true,
	//	border:'none',
		active : 0,
		maxLength : 10,
		items : [{
			id:'tab1',
			title:'常规收费统计',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>
<% 	
    
    String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
%> 

<div id ="disSel">


<form class="search_form" name="form1" action="report!payyearlist.action" method="post" onsubmit="return checkPayYear();">
	缴费开始时间<input type="text" name="startDate" value="${param.startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="12" readonly="readonly"/>
	截止时间：<input type="text" name="endDate" value="${param.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="12" readonly="readonly"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><U><s:property value="%{#parameters.year}"/></U>&nbsp;&nbsp;&nbsp;&nbsp;常规费统计表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >管理处</td>
			    	<td class="td_header" >费用类型</td>
			    	<td class="td_header" >实收款</td>
			    	<td class="td_header" >优惠金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><s:property value="viewList[#stuts.index][0]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][1]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][3]"/></td>
                </tr>	
              </s:iterator>
      		</table>
       </div>
       <!---table_area结束--->
   </div>

 
  </div>
</body>
</html>