<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select name="houseId">
<!-- <select name="houseId" onchange="send()"> -->
	<option value="">=请选择房间=</option>
	<s:iterator value="viewList" status="stuts">
		<option value="<s:property value="id"/>">
			<s:property value="houseName"/>
		</option>
	</s:iterator>
	</select>
	
