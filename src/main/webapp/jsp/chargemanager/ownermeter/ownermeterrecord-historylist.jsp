<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<SCRIPT type="text/javascript" src="/js/hanx.js"></SCRIPT>
<body>
<s:set id="df" name="df" value="new java.text.DecimalFormat('##0.0')"/>
 
 <div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>业户表历史记录</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header" >房间</td>
		    	<td class="td_header" >客户</td>
		    	<td class="td_header" >面积</td>
		    	<td class="td_header" >表类型</td>
		    	<td class="td_header" >上期指数</td>
		    	<td class="td_header" >本期指数</td>
		    	<td class="td_header" >用量</td>
		    	<td class="td_header" >单价</td>
		    	<td class="td_header" >月份</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c" height="28px">${houseAddress}</td>
			  	<td class="td_c">${ownerName}</td>
			  	<td class="td_c">${buildnum}</td>
			   	<td class="td_c">
					<s:if test="meterType.trim()=='电表'"><font color="#9467F1">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='水表'"><font color="#993333">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='暖汽表'"><font color="#5FBA73">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='蒸汽表'"><font color="red">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='热水表'">${meterType}</s:if>
				</td>
			   	<td class="td_c">${lastRecord}</td>
			   	<td class="td_c">${nowRecord}</td>
			   	<td class="td_c">${useNums}</td>
			   	<td class="td_c">${priceValue}</td>
			   	<td class="td_c"><s:if test="recordMonth!=null"><s:property value="recordMonth.toString().substring(0,7)"/></s:if></td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer">
            	<p>
            	<div id="pageBar" ></div>
            	<script type="text/javascript">
            	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")==null?"":request.getAttribute("pageBar")%>";
				</script>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>

</body>
</html>