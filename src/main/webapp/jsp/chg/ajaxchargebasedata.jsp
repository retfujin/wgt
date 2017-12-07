<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select name="chargeBasedataId">
	<s:iterator value="retList">
	<option value="${id }">${chargeName}</option>
	</s:iterator>
</select>
	
