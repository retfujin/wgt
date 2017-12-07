<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
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
			title:'常规收费明细',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<script type="text/javascript">
function checkForm(){
	if(document.getElementById('houseId').value==''){
		alert("请选择房间编号，支持模糊查询如1001-");
		return false;
	}
	return true;
}
</script>
<div id="tab" style="width:400px;"></div>

<div id ="disSel">

<form class="search_form" name="form1" action="report!paydetail.action" method="post" onsubmit="return checkForm();">
	房间号<input type="text" name="houseId" value="${param.houseId}" />
	收费名称<input type="text" name="chargeName" value="${param.chargeName}" /> 
	客户名称<input type="text" name="ownerName" value="${param.ownerName}" />
	缴费开始时间<input type="text" name="startDate" value="${param.startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="12" readonly="readonly"/>
	缴费截止时间<input type="text" name="endDate" value="${param.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="12" readonly="readonly"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

 <div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>常规费用明细一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >收费名称</td>
		    	<td class="td_header" >客户名称</td>
		    	<td class="td_header" >房间号</td>
		    	<td class="td_header" >应收月份</td>
		    	<td class="td_header" >实收金额</td>
		    	<td class="td_header" >优惠金额</td>
		    	<td class="td_header" >缴费日期</td>
		    	<td class="td_header" >备注</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c"><s:property value="viewList[#stuts.index][0]"/></td>
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
            	<p>
            	<div id="pageBar" ></div>
            	<script type="text/javascript">
				document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
				</script>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>
 
 
   
</div>
</body>
</html>