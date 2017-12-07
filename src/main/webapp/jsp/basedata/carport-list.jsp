<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	function showModel(houseId,chargeId){
		//window.open("/chg/pay!pay.action?houseId="+houseId+"&chargeId="+chargeId,"车位","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;width=400;height=200;center=true");
		window.open("/chg/pay!pay.action?houseId="+houseId,"收费情况","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;width=400;height=200;center=true");
	}
</script>
<body>
<% 	
	com.acec.core.utils.CharTools charTools = new com.acec.core.utils.CharTools();
	String areaId = request.getParameter("areaId") != null && !request.getParameter("areaId").equals("") ? request.getParameter("areaId") : "";
	String state = request.getParameter("state") != null ? request.getParameter("state") : "";
	String bigType = request.getParameter("bigType") != null ? request.getParameter("bigType") : "";
	String hidd = request.getParameter("hidd");
	if(null == hidd)
	{
		state = new String(state.getBytes("ISO8859-1"),"UTF-8");
		bigType = new String(bigType.getBytes("ISO8859-1"),"UTF-8");
	}
%>

<form class="search_form" name="form1" action="carport!list.action" method="post">
	小区名称<s:select id="areaId" name="areaId" list="retList" listKey="id" listValue="areaName" theme="simple" />
	属性<s:select list="#{'':'','出租':'出租','出售':'出售','空置':'空置'}" name="state" id="state" cssStyle="width:50px;" theme="simple"></s:select>
	类型<s:select list="#{'':'','1':'机动车','2':'非机动车'}" name="bigType" id="bigType"  cssStyle="width:80px;"  theme="simple"></s:select>
	房间编号<s:textfield name="houseId" theme="simple" cssStyle="width:100px;" />
	车位编号<s:textfield name="carCode" theme="simple" cssStyle="width:100px;" />
	将要到期 <s:select list="#{'':'','7':'一周内','15':'半个月内','30':'一个月内','180':'半年内'}" name="enddays" id="enddays"  cssStyle="width:80px;"  theme="simple"></s:select>
	<input type="hidden" value="-1"  name="hidd"/>
	<input type="submit" value="查询" class="search_btn" />
	<input type="button" value="导出"  class="search_btn" onclick="getNowCarportDown();"/>
</form>
 


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>车位列表</p> 
           </div>
           <form name="car_add" method="post">
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
				<td class="td_header" >房间</td>
		    	<td class="td_header" >车位编号</td>
		    	<td class="td_header" >位置</td>
		    	<td class="td_header" >属性</td>
		    	<td class="td_header" >类型</td>
		    	<td class="td_header" >车牌号</td>
		    	<td class="td_header" >开始日期</td>
		    	<td class="td_header" >截止日期</td>
		    	<td class="td_header" >车位分配</td>
		    	<td class="td_header" >停止使用</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
			  <tr >    	
			  	<td class="td_c"><a href="#" onclick="showModel('<s:property value="viewList[#stuts.index][1]"/>','<s:property value="viewList[#stuts.index][10]"/>')"><s:property value="viewList[#stuts.index][1]"/></a>&nbsp;</td>
			   	<td class="td_c"> <s:property value="viewList[#stuts.index][3]"/>&nbsp;</td>
			   	<td class="td_c"> <s:property value="viewList[#stuts.index][4]"/>&nbsp;</td>
			   	<td class="td_c"> 
				   	<s:if test="viewList[#stuts.index][5].equals('出售')">
				   		<font color="red">出售</font>
				   	</s:if>
				   	<s:elseif test="viewList[#stuts.index][5].equals('出租')">
				   		<font color="blue">出租</font>
				   	</s:elseif>
				   	<s:else>
				   		<s:property value="viewList[#stuts.index][5]"/>
				   	</s:else>
			   	</td>
			   	<td class="td_c"> 
			   		<s:if test="viewList[#stuts.index][9].equals('1')">机动车</s:if><s:else>非机动车</s:else>
			   	</td>
			   	<td class="td_c"> 
			   		<s:property value="viewList[#stuts.index][6]"/>&nbsp;
			   	</td>
			   	<td class="td_c"><s:if test="viewList[#stuts.index][7]!=null"><s:property value="viewList[#stuts.index][7].toString().substring(0,10)"/></s:if>&nbsp; </td>
			   	<td class="td_c">
			   		<s:if test="viewList[#stuts.index][8]!=null"> 
			   		<s:property value="viewList[#stuts.index][8].toString().substring(0,10)"/></s:if> &nbsp;
			   	</td>   	
			   	<td class="td_c">
					<s:if test="!viewList[#stuts.index][7].toString().equals('')">
			   			<input type="button" name="btn" value="续交" class="a" onclick="return payAther(<s:property value="viewList[#stuts.index][0]"/>, <s:property value="viewList[#stuts.index][9]"/>)"/>
			   		</s:if>
			   		<s:else>
			   			<input type="button" name="car" value="车位分配" class="a" onclick="return assign(<s:property value="viewList[#stuts.index][0]"/>, <s:property value="viewList[#stuts.index][9]"/>,'<s:property value="viewList[#stuts.index][7]"/>')" />
			   		</s:else>
			   	</td>
			   	<td class="td_c">
				   	<s:if test="!viewList[#stuts.index][7].toString().equals('')">
				   	<input type="button" name="car" value="停止使用" class="a" onclick="return stop(<s:property value="viewList[#stuts.index][0]"/>)" />
				   	</s:if>&nbsp;
			   	</td>
			  </tr>
			  </s:iterator>
      		</table>
           <div class="table_footer">
            	<p>
            	<div id="pageBar" ></div>
            	<script type="text/javascript">
				document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")%>";
				</script>
				</p>
           </div>
           </form>
       </div>
       <!---table_area结束--->
   </div>
 

<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function temporary(){
	window.open("carport!temporary.action","","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;width=400;height=200;center=true");
}
function assign(id,type,beginTime)
{
	if(beginTime == "")
	{
		
	}	
	else
	{
		if(!confirm("该车位已经分配，是否重新分配？"))
			return false;		
	}
	var ret = window.showModalDialog("carport!add.action?carportId="+id+"&type="+type,"",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=440px;center=true");
	if(ret=="success")
		form1.submit();
}
function payMoney(id)
{
//	window.location.href="carport!payMoney.action?carportId="+id;
	window.showModalDialog("carport!payMoney.action?carportId="+id,"",
	"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=440px;center=true");
//	car_add.action="carport!payMoney.action?carportId="+id;
//	car_add.submit();
}

//停止使用车位
function stop(id)
{
	if(!confirm("是否停止使用该车位？"))
		return false;
	$.ajaxSetup({
	    cache: false //关闭AJAX相应的缓存
	});
	$.ajax({
		url: "carport!stopLease.action",
		type: "GET",
		data: {carportId:id},
		dataType: "json",
		timeout: 1000,
		beforeSend: function(XMLHttpRequest){
			
		},
		error: function(){
			alert("网络连接错误");
		},
		success: function(data){
			alert(data.msg);
			if(data.success){
				form1.submit();
			}
		}
	});
	
}

//导出
function getNowCarportDown(){
	var areaId = document.getElementById("areaId").value;
	window.location.href="carport!downNowCarport.action?areaId="+areaId;
}

//续交
function payAther(id,type){
	//window.open("carport!edit.action?carportId="+id+"&type="+type,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;width=400;height=200;center=true");
	var ret = window.showModalDialog("carport!edit.action?carportId="+id+"&type="+type,"",
	"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=440px;center=true");
	if(ret=="success")
		form1.submit();
}
</script>
</body>	
</html>
