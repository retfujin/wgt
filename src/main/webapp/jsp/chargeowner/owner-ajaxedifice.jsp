<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- 去除 onchange="getHouse()" at 2011-10-21-->
	<select name="edificeId" >
		<option value="">=请选择楼栋=</option>
		<s:iterator value="viewList" status="stuts">
			<option value="<s:property value="id"/>">
				<s:property value="edificeName"/>
			</option>
		</s:iterator>
	</select>

