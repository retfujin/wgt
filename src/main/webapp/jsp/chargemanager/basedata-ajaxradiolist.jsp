<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator value="retList" status="stuts">
	<input type="radio" name="chargeName" id="chargeName" value="<s:property value="retList[#stuts.index]"/>"><s:property value="retList[#stuts.index]"/>
</s:iterator>
