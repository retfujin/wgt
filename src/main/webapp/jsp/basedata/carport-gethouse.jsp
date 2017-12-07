<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select id="houseId" name="carportLease.houseId" list="viewList"
	headerKey="-1" headerValue="==请选择房间==" listKey="id"
	listValue="houseName" theme="simple" onchange="send()"></s:select>