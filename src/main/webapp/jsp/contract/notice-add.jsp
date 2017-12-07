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
					if(frm.title.value=='')
					{
						alert("主题不能为空");
						return false;
					}
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
               <p>培训通知</p> 
            </div>
            <form id="ff" method="post" action="notice!save.action" name="frmAdd" > 
          <s:hidden name="entity.isdel"></s:hidden>
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td >培训主题 </td>
		    <td ><s:textfield name="entity.title" id="title" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >时间 </td>
		    <td ><s:textfield name="entity.begintime" id="begintime" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >地点 </td>
		    <td ><s:textfield name="entity.address" id="address" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >教师 </td>
		    <td ><s:textfield name="entity.teacher" id="teacher" theme="simple"></s:textfield> </td> 
		  </tr>
		  <tr>
		  	<td >教材 </td>
		    <td ><s:textarea name="entity.material" id="material" cols="40" rows="3" theme="simple"></s:textarea> </td> 
		  </tr>
		  <tr>
		  	<td >考试/考核方法 </td>
		    <td ><s:textarea name="entity.method" id="method" cols="40" rows="3" theme="simple"></s:textarea> </td> 
		  </tr>
		  <tr>
		  	<td >参加人员</td>
		    <td ><s:textarea name="entity.usernames" id="usernames" cols="40" rows="3" theme="simple"></s:textarea> </td> 
		  </tr>
		  
		  <tr class="footer">
                        <td colspan="2">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
			</table>
            </form>
        </div>
    </div>
    <!----add结束---> 
<script type="text/javascript">
//设置当前日期
function getNowDate(contorl) {
	var v_contorl = document.getElementById(contorl);
	if(v_contorl.value==''){
		var date = new Date();
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var mi= date.getMinutes();
		var s = date.getSeconds();
		v_contorl.value = y + "-" + (m > 9 ? m : ('0' + m)) + "-" + (d > 9 ? d : ('0' + d))+ " " + (h > 9 ? h : ('0' + h))+ ":" + (mi > 9 ? mi : ('0' + mi))+ ":" + (s > 9 ? s : ('0' + s));
	}
}

</script>
</body>
</html>
