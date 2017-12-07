<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select id="edificeId" name="carportLease.edificeId" list="viewList" headerKey="-1"
	headerValue="==请选择楼栋==" listKey="id" listValue="edificeName"
	theme="simple" onchange="getHouse()">
</s:select>

