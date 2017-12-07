<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<body>
<% 	
	com.acec.core.utils.CharTools charTools = new com.acec.core.utils.CharTools();
	String areaId = request.getParameter("areaId") != null && !request.getParameter("areaId").equals("") ? request.getParameter("areaId") : "0";
	String state = request.getParameter("state") != null ? request.getParameter("state") : "";
	String bigType = request.getParameter("bigType") != null ? request.getParameter("bigType") : "";
	
	String[] menuModel={"menuModel2.addItem(204,'历史交费','','carport!getHistoryCharge.action',false);"
				   		+"menuModel2.addItem(209,'返回','','carport!list.action',false);"

	};
	
%>
<%@ include file="/menubar/simple/aa.jsp" %>

<div id ="disSel">
<form name="form1" action="carport!payList.action"  method="post">
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
<tr>
    <td width="7%">&nbsp;&nbsp;小区:</td>
    <td width="15%">
    <s:select id="areaId" name="areaId" list="retList" headerKey="" headerValue="=请选择=" listKey="id" listValue="areaName" theme="simple" /></td>
    <td>房间编号:</td>
    <td><input type="text" name="houseId" size="12"/></td>
    <td>属性:</td>
    <td>
	    <select name="state">
	    <option value="">全部</option>
	    <option value="CZ">出租</option>
	    <option value="CS">出售</option>
	    <option value="KZ">空置</option>
	    </select> 
    </td>
    <td>位置:</td>
    <td><s:select name="local" list="#{'地面':'地面','地下':'地下','车库':'车库'}" headerKey="" headerValue="-请选择-" theme="simple"></s:select> </td>
    <td>类型:</td>
    <td>
	    <select name="bigType">
	    <option value="">全部</option>
	    <option value="JDC">机动车</option>
	    <option value="FJDC">非机动车</option>
	    </select> 
    </td>
    <td>车位编号:</td>
    <td><input type="text" name="carCode" size="12"/></td>
	<td >
	<input type="submit" value="查询"  class="a"/>
	</td>
</tr>
</table>
</form>
</div>


<form name="car_add" method="post">
<table width="100%"  border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder1">
     <tr>
      <th height="25" colspan="13" class="tableHeaderText">交费车位列表</th>
     </tr>
  <tr align="center">
        <td class="forumRow">序号</td>
    	<td class="forumRow" >房间</td>
    	<td class="forumRow" >车位编号</td>
    	<td class="forumRow" >位置</td>
    	<td class="forumRow" >属性</td>
    	<td class="forumRow" >类型</td>
    	<td class="forumRow" >车牌号码</td>
    	<td class="forumRow" >开始日期</td>
    	<td class="forumRow" >截止日期</td>
    	<td class="forumRow" >车位分配</td>
   </tr>
  <s:iterator value="viewList" status="stuts">
  <tr align="center" <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
    <td>${stuts.index+1}</td>
  	<td > <s:property value="houseId"/> </td>
   	<td > <s:property value="carCode"/> </td>
   	<td > <s:property value="local"/> </td>
   	<td > 
   	<s:if test="state.equals('出售')">
   	<font color="red">出售</font>
   	</s:if>
   	<s:elseif test="state.equals('出租')">
   	<font color="blue">出租</font>
   	</s:elseif>
   	<s:else>
   	<s:property value="state"/>
   	</s:else>
   	</td>
   	<td >   <s:property value="bigType"/> 	</td>
   	<td > <s:property value="carNums"/> </td>
   	<td>
	   	<s:if test="inDate!=null">
	   		<s:property value="inDate.toString().substring(0,10)"/>
	   	</s:if>  
   	</td>
   	<td >
   		<s:if test="outDate!=null">
   			<s:if test="state.equals('出租')">
   			<s:property value="outDate.toString().substring(0,10)"/>
   			</s:if>
	   	</s:if> 
   	</td>
   	<td>   	
   		<input type="button" name="car" value="交费" class="a" onclick="payMoney(${id})" />
   	</td>
  </tr>
  </s:iterator>
</table>
</form>
<div id="pageBar" ></div>
<script type="text/javascript">
	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
</script>
	
<script type="text/javascript">
function payMoney(id)
{
	car_add.action="carport!payMoney.action?id="+id;
	car_add.submit();
}
function show1()
{
	if(disSel.style.display=="none")
	{	
		disSel.style.display="block";
	}else
		disSel.style.display="none";
}

</script>
</body>	
</html>
