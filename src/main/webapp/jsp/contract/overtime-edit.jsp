<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<script type="text/javascript" src="/js/func.js"></script>
<script type="text/javascript" src="/js/hanx.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.name.value=='')
					{
						alert("申请人不能为空");
						return false;
					}
					var text=$("#areaId").find("option:selected").text();
					$("#areaName").val(text);
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
               <p>加班单</p> 
            </div>
            <form id="ff" method="post" action="overtime!save.action" name="frmAdd" > 
          <s:hidden name="entity.id"></s:hidden>
          <s:hidden name="entity.isdel"></s:hidden>
          <s:hidden name="entity.recordtime"></s:hidden>
          <s:hidden name="entity.recordid"></s:hidden>
          <s:hidden name="entity.recordname"></s:hidden>
          <input type="hidden" name="entity.areaName" id="areaName">
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >管理处 </td>
		    <td colspan="3"><s:select list="retList" name="entity.areaId" id="areaId" listKey="id" listValue="areaName" theme="simple"></s:select>  </td>
		  </tr>
		  <tr>
		  	<td >部门 </td>
		    <td ><s:select name="entity.departmentname" id="departmentname" list="viewList" listKey="name" listValue="name" theme="simple"></s:select> </td> 
		  	<td >填表人 </td>
		    <td ><s:textfield name="entity.name" id="name" theme="simple"></s:textfield> </td> 
		  </tr>	
		  <tr>
		  	<td >加班事由 </td>
		    <td ><s:textfield name="entity.reason" id="reason" size="35" theme="simple"></s:textfield> </td> 
		    <td >填表时间</td>
		    <td ><s:textfield name="entity.filldate" id="filldate" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield> </td>
		  </tr>
		  <tr>
		  	<td >加班时限</td>
		    <td ><s:textfield name="entity.begintime" id="begintime" readonly="true" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield>至
		    <s:textfield name="entity.endtime" id="endtime" readonly="true" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  theme="simple"></s:textfield> </td>
		  	<td >加班总天数 </td>
		    <td ><s:textfield name="entity.num" id="num" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >加班人姓名 </td>
		    <td colspan="2">具体加班时间（自月日时至月日时）</td>
		    <td >具体工作</td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknameone" id="worknameone" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimeone" size="40" id="worktimeone" theme="simple"/> </td>
		    <td ><s:textfield name="entity.workone" id="workone" theme="simple"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknametwo" id="worknametwo" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimetwo" size="40" id="worktimetwo" theme="simple"/> </td>
		    <td ><s:textfield name="entity.worktwo" id="worktwo" theme="simple"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknamethree" id="worknamethree" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimethree" size="40" id="worktimethree" theme="simple"/> </td>
		    <td ><s:textfield name="entity.workthree" id="workthree" theme="simple"/> </td>
		  </tr>
		  <tr>
		  	<td ><s:textfield name="entity.worknamefour" id="worknamefour" theme="simple"/> </td>
		    <td colspan="2"><s:textfield name="entity.worktimefour" size="40" id="worktimefour" theme="simple"/> </td>
		    <td ><s:textfield name="entity.workfour" id="workfour" theme="simple"/> </td>
		  </tr>	  
		  <tr class="footer">
                        <td colspan="4">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
			</table>
            </form>
        </div>
    </div>
  
</body>
</html>