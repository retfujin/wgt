<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<select id="chargebasedataId" name="allmeter.chargebasedataId" >
		<s:iterator value="viewList" status="stuts">
			<option value="<s:property value="id"/>">
				<s:property value="chargeName"/>
			</option>
		</s:iterator>
	</select>

