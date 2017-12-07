<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select name="carportLease.chargeId" id="chargeId">
<s:iterator value="retList" status="stuts">
	<option value="<s:property value="id"/>"><s:property value="chargeName"/></option>
</s:iterator>
</select>