<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<body>
<div align="center">
<form action="allmeterrecord!resultmeter.action">
${param.areaName}小区还没有生成新的${param.meterType}抄表记录，请选择生成月份，点击生成按钮。
<input type="text" id="recordMonth" name="recordMonth" id="recordMonth" size="7" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>抄表月份
<input type="hidden" name="areaId" value="${param.areaId}"/>
<input type="hidden" name="meterType" value="${param.meterType}"/>
<input type="submit" name="" value="生成" />
</form>
<br/>
<s:actionerror cssStyle="color:red" />
</div>
</body>
</html>
