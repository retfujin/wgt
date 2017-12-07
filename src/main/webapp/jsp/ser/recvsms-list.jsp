<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<body>
<div id="disSel">

<form class="search_form" name="form1" action="recvsms!list.action" method="post">
	手机号码<input type="text" name="mobile"  value="${param.mobile}">
	开始时间<input type="text" name="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.beginDate}" readonly="readonly">
	截止时间<input type="text" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.endDate}" readonly="readonly">
	<input type="submit" value="查询" class="search_btn" />
</form>
</div>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>信息接收列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header" width="10%">序号</td> 
		    	<td class="td_header" width="20%">接收时间</td>
		    	<td class="td_header" width="20%">房间号</td>
		    	<td class="td_header">手机号</td>
		    	<td class="td_header">消息内容</td>  
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
			  	<td class="td_c"><s:property value="recvTime.toString()"/> </td>
			   	<td class="td_c"><s:property value="userName"/></td>
			   	<td class="td_c"><s:property value="mobile"/></td>
			   	<td class="td_c"><s:if test="content.length()>30"><s:property value="content.substring(0,30)"/>… </s:if>
			   		<s:else><s:property value="content"/></s:else></td> 
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
   <!----main_right结束---> 
  
</body>
</html>
