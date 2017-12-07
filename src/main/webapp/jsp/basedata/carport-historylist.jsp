<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
	function delhistory(id) {		
		if (confirm('确定要删除吗')) {
			$.post("carport!delhistory.action", {'id': id},     
					function (data, textStatus){
						var responseText =  eval('(' + data + ')');
						alert(responseText.msg);
						if(responseText.success){
							window.location.reload();
						}					   		
					});
		}
	}
</script>
<body>

<form class="search_form" name="form1" action="carport!historylist.action" method="post">
	小区名称<s:select id="areaId" name="areaId" list="viewList" listKey="id" listValue="areaName" theme="simple" />
	房间编号<s:textfield name="houseId" theme="simple"/>
	车位编号<s:textfield name="carCode" theme="simple"/>
	<input type="submit" value="查询" class="search_btn" />
</form>


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>车位租售历史</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header" >车主姓名</td>
		    	<td class="td_header" >房间编号</td>
		    	<td class="td_header" >联系电话</td>
		    	<td class="td_header" >车位编号</td>
		    	<td class="td_header" >位置</td>
		    	<td class="td_header" >状态</td>
		    	<td class="td_header" >车牌号</td>
		    	<td class="td_header" >卡号</td>    	
		    	<td class="td_header" >车名/颜色</td>
		    	<td class="td_header" >开始日期</td>
		    	<td class="td_header" >截止日期</td>
				<td class="td_header">操作</td>
            </tr>
            <s:iterator value="retList" status="stuts">
            <tr>
				<td class="td_c">${ownerName}</td>
			   	<td class="td_c">${houseId}</td>
			   	<td class="td_c">${phone}</td>
			   	<td class="td_c">${carCode}</td>
			   	<td class="td_c">${local}</td>
			   	<td class="td_c">${state}</td>
			   	<td class="td_c">${carNums}</td>
			   	<td class="td_c">${cardNum}</td>
			   	<td class="td_c">${carColor}</td>
			   	<td class="td_c"><s:property value="inDate.toString().substring(0,10)"/> </td>
			   	<td class="td_c"><s:property value="outDate.toString().substring(0,10)"/> </td>
			    <td class="td_c">
                     <a href="#" onclick="delhistory('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                </td>
                </tr>	
              </s:iterator>
      		</table>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>	
</html>