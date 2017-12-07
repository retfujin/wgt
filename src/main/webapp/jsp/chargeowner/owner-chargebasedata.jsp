<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
<s:iterator value="retList" status="stuts">
	  <tr  height="25"  <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
	  	<td align="center" width="12%">
	  		<input type="checkBox" name="chargeId" value="${id}" id="chargeId">
	  	</td>
	   	<td width="18%">${chargeName}</td>
	   	<td width="10%">${priceValue}</td>
	   	<td width="30%">${chargeExpression }</td>
		<td>${expExplain }</td>
	  </tr>
	  </s:iterator>
</table>	
