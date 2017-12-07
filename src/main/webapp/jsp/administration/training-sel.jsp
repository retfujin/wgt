<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>员工培训档案</p> 
            </div>
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td height="28px">管理处</td>
		  	<td><s:property value="entity.areaName"></s:property></td>
		  	<td>&nbsp;</td>
		  	<td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td height="28px">员工姓名 <font color="red">*</font></td>
		    <td ><s:property value="entity.name"></s:property></td>
		    <td >性别 </td>
		    <td ><s:property value="entity.sex" ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		  <td height="28px">出生年月 </td>
		    <td ><s:property value="entity.brithday"></s:property>&nbsp;</td>
		    <td >岗位 </td>
		    <td ><s:property value="entity.position"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td height="28px">学历 </td>
		    <td ><s:property value="entity.education"></s:property>&nbsp;</td>
		    <td >职称 </td>
		    <td ><s:property value="entity.title"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td height="28px">培训日期</td>
		    <td >参加培训内容 </td>
		    <td >主办单位 </td>
		    <td >考核成绩 </td>
		  </tr>
		  <s:iterator value="viewList" status="stuts">
		  <tr>
		    <td ><s:property value="trainingdate.toString().substring(0,10)"/>&nbsp;</td>
		    <td ><s:property value="content"/>&nbsp;</td>
		    <td ><s:property value="unit"/>&nbsp;</td>
		    <td ><s:property value="results"/>&nbsp;</td>
		  </tr> 
		   </s:iterator>
		  
			</table>
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
