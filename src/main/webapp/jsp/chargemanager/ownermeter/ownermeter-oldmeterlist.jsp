<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<%@ include file="/commons/meta.jsp" %>
</head>
<body>



<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>业户表更换历史</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header">客户编号</td>
		    	<td class="td_header">客户名称</td>
		    	<td class="td_header">表类型</td>
		    	<td class="td_header">期初表数</td>
		    	<td class="td_header">起用日期</td>
		    	<td class="td_header">期末表数</td>
		    	<td class="td_header">更换人员</td>
		    	<td class="td_header">更换日期</td>
		    	<td class="td_header">更换原因</td>
            </tr>
            <s:iterator value="page.result" status="stuts">
            <tr>
                    <td class="td_c">${stuts.index+1}</td>
				<td class="td_c">&nbsp;${house.id}</td>
			   	<td class="td_c">&nbsp;${ownerName}</td>
			   	<td class="td_c">&nbsp;${meterType}</td>
			   	<td class="td_c">&nbsp;${initNum}</td>
			   	<td class="td_c">&nbsp;${beginTime}</td>
			   	<td class="td_c">&nbsp;${nowRecord}</td>
			   	<td class="td_c">&nbsp;${changeName}</td>
			   	<td class="td_c">&nbsp;${changeTime}</td>
			   	<td class="td_c">&nbsp;${changeReason}</td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer"> 
            	
				<p>第${page.pageNo}页, 共${page.totalPages}页
				
				<s:if test="page.totalPages>1">
					<a href="ownermeter!oldmeterlist.action${requestScope.paramString}&page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="ownermeter!oldmeterlist.action${requestScope.paramString}&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="ownermeter!oldmeterlist.action${requestScope.paramString}&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="ownermeter!oldmeterlist.action${requestScope.paramString}&page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
				</p>

           </div>
       </div>
       <!---table_area结束--->
   </div>

  
</body>
</html>
