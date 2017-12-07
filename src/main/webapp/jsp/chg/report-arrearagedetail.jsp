<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
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
			title:'欠费统计',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>
<% 	
	String houseId = request.getParameter("houseId");
%>
<div id ="disSel">

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><U><s:property value="%{#parameters.houseId}"/></U>&nbsp;&nbsp;&nbsp;&nbsp;物业费欠费明细表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >客户名称</td>
    	<td class="td_header" >房间号</td>
    	<td class="td_header" >房间面积</td>
    	<td class="td_header" >欠费月份</td>
    	<td class="td_header" >欠费金额</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><s:property value="viewList[#stuts.index][0]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][1]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][2]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][3]"/></td>
			    <td class="td_c"><s:property value="viewList[#stuts.index][4]"/></td>
                </tr>	
              </s:iterator> 
      		</table>
       </div>
       <!---table_area结束--->
   </div>
   
  </div>  
</body>
</html>