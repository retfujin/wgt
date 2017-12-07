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
<script type="text/javascript">
function goSetUser(param_a,param_b){
   var rtnValue = window.showModalDialog("procurement!choose.action","","dialogHeight:800px;dialogWidth:300px");
   if(rtnValue!=undefined){
       var userIdList = "";
       var userNameList = "";
       if(rtnValue!=null){
           for(var i=0;i<rtnValue.length;i++){
          	   if(userIdList=="") userIdList = rtnValue[i].uid;
               else userIdList += "," + rtnValue[i].uid;
               if(userNameList=="") userNameList = rtnValue[i].uname;
               else userNameList += "," + rtnValue[i].uname;
           }
       }
       document.getElementById(param_a).value = userIdList; 
       document.getElementById(param_b).value = userNameList;
   }
}
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
		    <s:hidden name="entity.departid" id="departid"></s:hidden>
		    <s:if test="entity.departname!=null && entity.departname!=''">
		    <s:textfield name="entity.departname" id="departname" size="10" readonly="true" theme="simple"></s:textfield>
			</s:if><s:else>
			<s:textfield name="departname" id="departname" size="10" onclick="goSetUser('departid','departname')" readonly="true" theme="simple"></s:textfield>
			</s:else>
			<br><s:textfield name="entity.departdate" id="departdate" size="10" readonly="true" theme="simple"></s:textfield>
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
		   	<td><s:hidden name="entity.departoneid" id="departoneid"></s:hidden>
		   	<s:if test="entity.departonename!=null && entity.departonename!=''">
		   	<s:textfield name="entity.departonename" id="departonename" size="10" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="departonename" id="departonename" size="10" onclick="goSetUser('departoneid','departonename')" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	<br><s:textfield name="entity.departonedate" id="departonedate" size="10" readonly="true" theme="simple"></s:textfield>
		   	</td>
		   	<td>综合部</td>
		   	<td><s:hidden name="entity.officeid" id="officeid"></s:hidden>
		   	<s:if test="entity.officename!=null && entity.officename!=''">
		   	<s:textfield name="entity.officename" id="officename" size="10" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="officename" id="officename" size="10" onclick="goSetUser('officeid','officename')" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	<br><s:textfield name="entity.officedate" id="officedate" size="10" readonly="true" theme="simple"></s:textfield>
		   	</td>
		   	<td>&nbsp;</td>
		   	<td>&nbsp;</td>
		   </tr>
		   <tr>
		   	<td>分管副总</td>
		   	<td><s:hidden name="entity.viceid" id="viceid"></s:hidden>
		   	<s:if test="entity.vicename!=null && entity.vicename!=''">
		   	<s:textfield name="entity.vicename" id="vicename" size="10" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="vicename" id="vicename" size="10" onclick="goSetUser('viceid','vicename')" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	<br><s:textfield name="entity.vicedate" id="vicedate" size="10" readonly="true" theme="simple"></s:textfield>
		   	</td>
		   	<td>总经理</td>
		   	<td><s:hidden name="entity.managerid" id="managerid"></s:hidden>
		   	<s:if test="entity.managername!=null && entity.managername!=''">
		   	<s:textfield name="entity.managername" id="managername" size="10" readonly="true" theme="simple"></s:textfield>
		   	</s:if><s:else>
		   	<s:textfield name="managername" id="managername" size="10" onclick="goSetUser('managerid','managername')" readonly="true" theme="simple"></s:textfield>
		   	</s:else>
		   	<br><s:textfield name="entity.managerdate" id="managerdate" size="10" readonly="true" theme="simple"></s:textfield>
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
