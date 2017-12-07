<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<body>
<div id="disSel">
<form class="search_form" name="form1" action="bulletin!list.action" method="post">
	手机号码<input type="text" name="mobile"  value="${param.mobile}">
	开始时间<input type="text" name="beginDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.beginDate}" readonly="readonly">
	截止时间<input type="text" name="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${param.endDate}" readonly="readonly">
	<input type="submit" value="查询" class="search_btn" />
</form>
</div>


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>信息发送列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header" width="10%">序号</td>
		    	<td class="td_header" width="20%">发送时间</td>
		    	<td class="td_header" width="20%">手机号</td>
		    	<td class="td_header">内容</td>
		    	<td class="td_header">发送人</td>
		    	<td class="td_header">状态</td>
		    	<td class="td_header">详情</td>   
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
			  	<td class="td_c"><s:property value="sendTime.toString()"/> </td>
			   	<td class="td_c" title="<s:property value="mobilePhones"/>"><s:if test="mobilePhones.length()>50"><s:property value="mobilePhones.substring(0,50)"/>… </s:if>
			   		<s:else><s:property value="mobilePhones"/></s:else></td>
			   	<td class="td_c"><s:if test="content.length()>30"><s:property value="content.substring(0,30)"/>… </s:if>
			   		<s:else><s:property value="content"/></s:else></td>
			   	<td class="td_c"><s:property value="sendName"/></td>
			   	<td class="td_c"><s:if test="state.substring(0,2)=='00'">发送成功</s:if>
			   		<s:elseif test="state.substring(0,2)=='10'">欠费错误</s:elseif>
			   		<s:elseif test="state.substring(0,2)=='11'">发送成功</s:elseif>
			   		<s:elseif test="state.substring(0,2)=='99'">发送失败</s:elseif></td>
			   <td class="td_c"><a href="bulletin!detaillist.action?msgid=<s:property value="msgid"/>" target="_blank" >查看</a></td>
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
