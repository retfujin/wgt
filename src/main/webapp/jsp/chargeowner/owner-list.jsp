<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript">
function edit(id) {
	var vReturnValue = window
			.showModalDialog(
					"owner!editChargeHouseById.action?house.id=" + id,
					"",
					"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=48;dialogHeight=35;center=true");
	if ("success" == vReturnValue) {
		window.location.reload();
	}
}
</script>
<body>



<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>收费客户档案</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header" >房间编号</td>
			    	<td class="td_header" >房间地址</td>
			    	<td class="td_header" >客户</td>
			    	<td class="td_header" >面积</td>
			    	<td class="td_header" >收费项目</td>
			    	<td class="td_header" >操作</td>
            </tr>
            <s:iterator value="retList" status="stuts">
            <tr>
					<td class="td_c"><input type="hidden" name="speci" id="speci" value="1"/>${stuts.index+1}</td>
				  	<td class="td_c"><s:property value="retList[#stuts.index][0]"/> </td>
				   	<td class="td_c"><s:property value="retList[#stuts.index][1]"/> </td>
				   	<td class="td_c"><s:property value="retList[#stuts.index][3]"/> </td>
				   	<td class="td_c"id="a" ><s:property value="retList[#stuts.index][2]"/> </td>
				   	<td class="td_c"><s:property value="retList[#stuts.index][5]"/> </td>
                    <td class="td_c">
                    	<a href="#" onclick="edit('<s:property value="retList[#stuts.index][0]"/>')"><img border="0" alt="编辑" src="/images/change.png" ></a>  
                    </td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header">合计</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="asum" align="center">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
				</tr>
      		</table>
           <div class="table_footer">
            	<p>
            	<div id="pageBar" ></div>
            	<script type="text/javascript">
				document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
				</script>
				</p>
           </div>
       </div>
       <!---table_area结束--->
   </div>

  
</body>
<script type="text/javascript">
function  doSum(tt,ttToal){
	if(tt.length!=undefined){
	  var   sumrow=0.0;  	
	  for(var i=0;i< tt.length;i++)
	  {
		var tmpVal= tt[i].innerHTML;
	  	if(!isNaN(tmpVal)==true)
	  		sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);   
	  }
	  ttToal.innerHTML = sumrow.toFixed(2);
	}else{
		ttToal.innerHTML=tt.innerHTML;
	}
}
doSum(a,asum);
//doSum(b,bsum);
</script>

</html>
