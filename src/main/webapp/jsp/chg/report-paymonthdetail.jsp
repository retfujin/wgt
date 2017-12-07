<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function checkPayMonth(){
		if(document.getElementById('month').value==''){
			alert("请选择缴费月");
			return false;
		}
		return true;
	}
</script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%String month = request.getParameter("month");%> 
<form class="search_form" name="form1" action="report!paymonthdetail.action" method="post" onsubmit="return checkPayMonth();">
	管理处<s:select name="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"></s:select>
	缴费月份：<input type="text" name="month" value="${param.month}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" size="12" readonly="readonly"/>
	<input type="submit" value="查询" class="search_btn" />
</form>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><s:property value="%{#parameters.month}"/>缴费单明细</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >票据号</td>
					<td class="td_header" >客户名称</td>
			    	<td class="td_header" >房间号</td>
			    	<!--  
			    	<td class="td_header" >缴费月份</td>
			    	-->
			    	<td class="td_header" >实收款</td>
			    	<td class="td_header" >收款方式</td>
			    	<td class="td_header" >收款人</td>
			    	<td class="td_header" >缴费时间</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
                <td class="td_c"><a href="<%=request.getContextPath() %>/chg/pay!viewreceipt.action?recordBh=${bh}&type=view" title="查看缴费单明细" target="_blank">${bh}</a></td>
                <td class="td_c">${payName}</td>
                <td class="td_c">${house.id}</td>
                <!-- 
                <td class="td_c"><s:property value="recordMonth.toString().substring(0,7)"/></td>
                 -->
                <td class="td_c" id="a">${factMoney}</td>
                <td class="td_c">${payType}</td>
                <td class="td_c">${userName}</td>
                <td class="td_c"><s:property value="gatheringTime.toString().substring(0,10)"/></td>
                </tr>	
              </s:iterator>
              <tr>
              	<td class="td_header" align="center">合计</td>
              	<td class="td_header">&nbsp;</td>
              	<td class="td_header">&nbsp;</td>
              	<td class="td_header">&nbsp;</td>
              	<!-- 
              	<td class="td_header">&nbsp;</td>
              	 -->
              	<td class="td_header" align="right" id="asum">&nbsp;</td>
              	<td class="td_header">&nbsp;</td>
              	<td class="td_header">&nbsp;</td>
              	<td class="td_header">&nbsp;</td>
              </tr>
      		</table>
       </div>
       <!---table_area结束--->
   </div>
</body>
<script type="text/javascript">
function  doSum(tt,ttToal){
	if(tt.length!=undefined){
		  var   sumrow=0.0;  	
		  for(var i=0;i< tt.length;i++)
		  {
			var tmpVal= tt[i].innerHTML;
		  	if(isNaN(tmpVal)==true)
		  	  	alert("ta:"+tmpVal);
		  	sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);   
		  }
		  ttToal.innerHTML = sumrow.toFixed(2);
		}else{
			ttToal.innerHTML=tt.innerHTML;
		}  
}
doSum(a,asum);
</script>
</html>