<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select name="edificeId" onchange="getHouse()">
		<option value="">=请选择楼栋=</option>
		<s:iterator value="viewList" status="stuts">
			<option value="<s:property value="id"/>">
				<s:property value="edificeName"/>
			</option>
		</s:iterator>
	</select>
