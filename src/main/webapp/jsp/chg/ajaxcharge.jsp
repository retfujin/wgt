<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select name="chargeId">
<option value="">=选择收费项目=</option>
	<s:iterator value="viewList">
	<option value="${id}">${chargeName}</option>
	</s:iterator>
</select>
	
