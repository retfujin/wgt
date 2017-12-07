<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<body>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>信息发送列表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header" width="10%">序号</td>
	    		<td class="td_header" width="20%">手机号</td>
	    		<td class="td_header" width="20%">状态</td>
	    		<td class="td_header">接收时间</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
			  	<td class="td_c"><s:property value="retList[#stuts.index][1]"/> </td>
			    <td class="td_c">
			    	<s:if test="retList[#stuts.index].length<=2">无状态</s:if>
			    	<s:elseif test="retList[#stuts.index][3]=='DELIVRD'">发送成功</s:elseif>
			    	<s:else>发送失败-<s:property value="retList[#stuts.index][3]"/></s:else>
			     </td>
			    <td class="td_c"><s:property value="retList[#stuts.index][4]"/> </td>   
           	</tr>	
            </s:iterator> 
      		</table>
           
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
 
</body>
</html>
