<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.returnValue = "success";
						window.close();
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>物品采购申请</p> 
            </div>
          <form id="ff" method="post" action="procurement!editsave.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <s:hidden name="entity.areaId"></s:hidden>
          <s:hidden name="entity.areaName"></s:hidden>
          <table id="act_table" class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td>管理处</td>
		  	<td><s:property value="entity.areaName"></s:property> </td>
		  	<td>&nbsp;</td><td>&nbsp;</td>
		  	<td>申请日期</td><td><s:textfield name="entity.applydate" id="applydate" size="10" readonly="true" theme="simple"></s:textfield></td>
		  </tr>
		  <tr>
		  	<td>部门<font color="red">*</font></td>
		  	<td><s:textfield name="entity.departmentname" id="departmentname" readonly="true" theme="simple"></s:textfield> </td>
		    <td >申请人<font color="red">*</font></td>
		    <td ><s:textfield name="entity.name" id="name" readonly="true" theme="simple"></s:textfield></td>
		    <td >部门负责人 </td>
		    <td >
		    <s:hidden name="entity.departid"></s:hidden>
		    <s:if test="entity.departid!=null && entity.departid!='' && entity.departname==null">
		    <s:textfield name="entity.departname" id="departname" theme="simple"></s:textfield>
			<br><s:textfield name="entity.departdate" id="departdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
			</s:if><s:else>
			<s:textfield name="entity.departname" id="departname" readonly="true" theme="simple"></s:textfield>
			<br><s:textfield name="entity.departdate" id="departdate" readonly="true" theme="simple"></s:textfield>
			</s:else>
			</td>
		  </tr>
		  <tr>
		  <tr>
		    <td ><font color="red">物品名称</font></td>
		    <td >单位</td>
		    <td >数量 </td>
		    <td >品牌/型号 </td>
		    <td >参考价格(元) </td>
		    <td >用途 </td>
		  </tr>
		  <s:iterator value="viewList" status="stuts">
		  <tr>
		    <td ><input type="text" name="goodsname" value="${goodsname}" readonly="readonly" /> </td>
		    <td ><input type="text"  name="unit" value="${unit}" size="10" readonly="readonly"/> </td>
		    <td ><input type="text"  name="num" value="${num}" size="10" readonly="readonly"/> </td>
		    <td ><input type="text"  name="brand" value="${brand}" size="10" readonly="readonly"/> </td>
		    <td ><input type="text"  name="price" value="${price}" size="8" readonly="readonly"/> </td>
		    <td ><input type="text"  name="inuse" value="${inuse}" size="30" readonly="readonly"/> </td>
		  </tr>
		   </s:iterator>
		   <tr>
		   	<td>物业部</td>
		   	<td><s:hidden name="entity.departoneid"></s:hidden>
		   	<s:if test="entity.departoneid!=null && entity.departoneid!='' && entity.departonename==null">
		   	<s:textfield name="entity.departonename" id="departonename" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.departonedate" id="departonedate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="entity.departonename" id="departonename" readonly="true" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.departonedate" id="departonedate" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	</td>
		   	<td>综合部</td>
		   	<td><s:hidden name="entity.officeid"></s:hidden>
		   	<s:if test="entity.officeid!=null && entity.officeid!='' && entity.officename==null">
		   	<s:textfield name="entity.officename" id="officename" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.officedate" id="officedate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="entity.officename" id="officename" readonly="true" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.officedate" id="officedate" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	</td>
		   	<td>&nbsp;</td>
		   	<td>&nbsp;</td>
		   </tr>
		   <tr>
		   	<td>分管副总</td>
		   	<td><s:hidden name="entity.viceid"></s:hidden>
		   	<s:if test="entity.viceid!=null && entity.viceid!='' && entity.vicename==null">
		   	<s:textfield name="entity.vicename" id="vicename" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.vicedate" id="vicedate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="entity.vicename" id="vicename" readonly="true" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.vicedate" id="vicedate" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	</td>
		   	<td>总经理</td>
		   	<td><s:hidden name="entity.managerid"></s:hidden>
		   	<s:if test="entity.managerid!=null && entity.managerid!='' && entity.managername==null">
		   	<s:textfield name="entity.managername" id="managername" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.managerdate" id="managerdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="entity.managername" id="managername" readonly="true" theme="simple"></s:textfield>
		   	<br><s:textfield name="entity.managerdate" id="managerdate" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	</td>
		   	<td>&nbsp;</td>
		   	<td>&nbsp;</td>
		   </tr>
		  <tr class="footer">
                        <td colspan="6">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
			</table>
            </form>
        </div>
    </div>
    <!----add结束---> 
  
		  
</body>
</html>
