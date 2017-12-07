<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
<title>列表</title>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="../js/check.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/tabpanel/css/core.css" rel="stylesheet" type="text/css"/>
<link href="/tabpanel/css/TabPanel.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
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
			title:'投诉统计',
			icon:'/tabpanel/image/read-y.gif',
			html:disSel,
			closable: false
		}]
	});
});
</script>
<div id="tab" style="width:400px;"></div>

<div id="disSel">

<form class="search_form" name="form1" action="suit!tsstat.action" method="post">
	开始日期：<input type="text" name="beginTime" id="beginTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
	截止日期：<input type="text" name="endTime" id="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
	<input type="submit" value="查询" class="search_btn" />
</form>


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>投诉统计一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
          <tr align="center">
		    	<td class="td_header" rowspan="3">管理处</td>
		    	<td class="td_header" rowspan="3">住户总数</td>
		    	<td class="td_header" colspan="6">投诉处理</td>
		  </tr>
		  <tr align="center">
		    	<td class="td_header" colspan="3">有效</td>
		    	<td class="td_header" colspan="3">无效</td>
		   </tr>
		  <tr align="center">
		    	<td class="td_header" >总数量</td>
		    	<td class="td_header" >处理数量</td>
		    	<td class="td_header" >处理率</td>
		    	<td class="td_header" >总数量</td>
		    	<td class="td_header" >处理数量</td>
		    	<td class="td_header" >处理率</td>
		   </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
				<td class="td_c"><s:property value="viewList[#stuts.index][0]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][1]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][2]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][3]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][4]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][5]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][6]" /></td>
			  	<td class="td_c"><s:property value="viewList[#stuts.index][7]" /></td>
                </tr>	
              </s:iterator>
      		</table>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
</body>
</html>