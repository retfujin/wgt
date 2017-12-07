<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<% 	
	String areaId = request.getParameter("areaId") != null ? request.getParameter("areaId") : "";
	String houseId = request.getParameter("houseId") != null ? request.getParameter("houseId") : "";
	String chargeId = request.getParameter("chargeId") != null ? request.getParameter("chargeId") : "";
	String beginTime = request.getParameter("beginTime") != null ? request.getParameter("beginTime") : "";
	String endTime = request.getParameter("endTime") != null ? request.getParameter("endTime") : "";
	String ownerName = request.getParameter("ownerName") !=null ? request.getParameter("ownerName"):"";
%>

&nbsp;&nbsp;&nbsp;&nbsp;
<font color="red">
如果某月某项费用的应收款设置为0,则删除该条记录
</font>





<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>费用详情表</p> 
           </div>
           <form name="car_add" method="post">
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
		    	<td class="td_header"  width="10">&nbsp;</td>
		    	<td class="td_header" >序号</td>
		    	<td class="td_header" >房间</td>
		    	<td class="td_header" >面积</td>
		    	<td class="td_header" >姓名</td>
		    	<td class="td_header" >收费名称</td>
		    	<td class="td_header" >收费月份</td>
		    	<td class="td_header">应收</td>
		    	<td class="td_header">实收</td>
		    	<td class="td_header">优惠</td>
		    	<td class="td_header">欠收</td>
		    	<td class="td_header">备注</td>
            </tr>
             <s:iterator value="page.result" status="stuts">
			  <tr >    	
			  	<td class="td_c" align="right"><input type="checkbox" name="ids" value="${id}"/></td>
			  	<td class="td_c" >${stuts.index+1}&nbsp;</td>
			   	<td class="td_c" align="left">${house.id}&nbsp;</td>
			   	<td  class="td_c" align="left">${house.buildnum}&nbsp;</td>
			   	<td  class="td_c" align="left"><input type="text" size="10" name="ownerName" id="ownerName" onfocus="this.select()" value="${ownerName}">&nbsp;</td>
			   	<td  class="td_c" align="left">${chargeName}&nbsp;</td>
			   	<td  class="td_c" align="left">${recordMonth}&nbsp;</td>
			   	<td  class="td_c" align="left">
			   	<input type="text" size="6" name="oughtMoney" id="oughtMoney" onfocus="this.select()" value="${oughtMoney}">&nbsp;</td>
			   	<td class="td_c" align="left"> ${factMoney}&nbsp;</td>
			   	<td class="td_c" align="left">${privilegeMoney}&nbsp;</td>
			   	<td class="td_c" align="left">${arrearageMoney}&nbsp;</td>
			   	<td class="td_c" align="left">${arreargeReason}&nbsp;</td>
			  </tr>
			  </s:iterator>
			  <tr>
			  	<td colspan="12"><input type="button" class="save" value="保存" onclick="update()"></td>
			  </tr>
      		</table>
           <div class="table_footer">
            	<p>
            	第${page.pageNo}页, 共${page.totalPages}页
				
				<s:if test="page.totalPages>1">
					<a href="changemoney!getInfo.action?areaId=<%=areaId %>&houseId=<%=houseId %>&chargeId=<%=chargeId %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>&ownerName=<%=ownerName %>&page.pageNo=1&page.orderBy=${page.orderBy}&page.order=${page.order}">首页</a>
				</s:if><s:else><a href="#">首页</a></s:else>
				<s:if test="page.hasPre">
					<a href="changemoney!getInfo.action?areaId=<%=areaId %>&houseId=<%=houseId %>&chargeId=<%=chargeId %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>&ownerName=<%=ownerName %>&page.pageNo=${page.prePage}&page.orderBy=${page.orderBy}&page.order=${page.order}">上一页</a>
				</s:if><s:else><a href="#">上一页</a></s:else>
				<s:if test="page.hasNext">
					<a href="changemoney!getInfo.action?areaId=<%=areaId %>&houseId=<%=houseId %>&chargeId=<%=chargeId %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>&ownerName=<%=ownerName %>&page.pageNo=${page.nextPage}&page.orderBy=${page.orderBy}&page.order=${page.order}">下一页</a>
				</s:if><s:else><a href="#">下一页</a></s:else>
				<s:if test="page.totalPages>1">
					<a href="changemoney!getInfo.action?areaId=<%=areaId %>&houseId=<%=houseId %>&chargeId=<%=chargeId %>&beginTime=<%=beginTime %>&endTime=<%=endTime %>&ownerName=<%=ownerName %>&page.pageNo=${page.totalPages}&page.orderBy=${page.orderBy}&page.order=${page.order}">尾页</a>
				</s:if><s:else><a href="#">尾页</a></s:else>
            	
             
				</p>
           </div>
           </form>
       </div>
       <!---table_area结束--->
   </div>
  
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
function update(){
	var v_ids =new Array();
	var v_oughtMoney = new Array();
	var v_ownerName = new Array();
	$("input[name='ids']:checkbox").each(function(index){
		if ($(this).attr("checked")){
			v_ids.push($(this).val());
			var oughtMoney = document.getElementsByName("oughtMoney")[index].value;
			var ownerName = document.getElementsByName("ownerName")[index].value;
			v_oughtMoney.push(oughtMoney);
			v_ownerName.push(ownerName);
		}
	});
	//alert(v_ids);
	//alert(v_oughtMoney);
	//alert(v_ownerName);
	if(v_ids==null||v_ids.length<1){
		alert("请勾选需要修改了记录");
		return;
	}
		
	$.post("changemoney!updateChargeMoney.action",jQuery.param({'ids':v_ids,'oughtMoneys':v_oughtMoney,'ownerNames':v_ownerName},true),     
			   function (data, textStatus){
					var responseText =  eval('(' + data + ')');
					alert(responseText.msg);
					if(responseText.success){
						<%if("pay".equals(request.getParameter("urltype")))
							out.println("window.close();");
						else
							out.println("window.location.reload();");
						%>
					}
						
	});

}

</script>
</body>
</html>
