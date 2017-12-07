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
		  	<td>&nbsp;</td><td>申请日期</td><td><s:property value="entity.applydate.toString().substring(0,10)"/> </td>
		  </tr>
		  <tr>
		    <td height="28px">部门 <font color="red">*</font></td>
		    <td ><s:property value="entity.departmentname"></s:property>&nbsp;</td>
		    <td >申请人</td>
		    <td ><s:property value="entity.name" ></s:property>&nbsp;</td>
		    <td>部门负责人</td><td><s:property value="entity.departname"/>&nbsp;</td>
		  </tr>
		  <tr class="add_name">
		    <td height="36px">物品名称</td>
		    <td >单位</td>
		    <td >数量</td>
		    <td >品牌/型号</td>
		    <td>参考价格(元)</td>
		    <td>用途</td>
		  </tr>
		  <s:iterator value="viewList" status="stuts">
		  <tr>
		    <td ><s:property value="goodsname"/>&nbsp;</td>
		    <td ><s:property value="unit"/>&nbsp;</td>
		    <td ><s:property value="num"/>&nbsp;</td>
		    <td ><s:property value="brand"/>&nbsp;</td>
		    <td ><s:property value="price"/>&nbsp;</td>
		    <td ><s:property value="inuse"/>&nbsp;</td>
		  </tr> 
		   </s:iterator>
		  <tr>
		   	<td>物业部</td>
		   	<td>
		   	<s:property value="entity.departonename"></s:property>
		   	<br><s:property value="entity.departonedate.toString().substring(0,10)"></s:property>
		   	</td>
		   	<td>综合部</td>
		   	<td>
		   	<s:property value="entity.officename"></s:property>
		   	<br><s:property value="entity.officedate.toString().substring(0,10)"></s:property>
		   	</td>
		   	<td>&nbsp;</td>
		   	<td>&nbsp;</td>
		   </tr>
		   <tr>
		   	<td>分管副总</td>
		   	<td>
		   	<s:property value="entity.vicename"></s:property>
		   	<br><s:property value="entity.vicedate.toString().substring(0,10)"></s:property>
		   	</td>
		   	<td>总经理</td>
		   	<td>
		   	<s:property value="entity.managername"></s:property>
		   	<br><s:property value="entity.managerdate.toString().substring(0,10)"></s:property>
		   	</td>
		   	<td>&nbsp;</td>
		   	<td>&nbsp;</td>
		   </tr>
			</table>
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
