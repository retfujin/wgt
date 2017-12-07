<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<title>列表</title>
</head>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
function add(){
  	var vReturnValue = window.showModalDialog("cell!add.action","","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=605px;dialogHeight=620px;center=true");
	if ("success" == vReturnValue)
		window.location.reload();		
}
function addall(){
	 var vReturnValue = window.showModalDialog("cell!init.action","","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=605px;dialogHeight=270px;center=true");
}
</script>
<body>
<div>
<%
	String building = (String)request.getAttribute("building");
%>
<form class="search_form" name="form1" action="cell!list.action" method="post">
	<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
	<tr>
	<td>小区名称<s:select name="areaId" id="areaId" list="retList" listKey="id" listValue="areaName" theme="simple"  onblur="doRequest();"></s:select></td>
	<td><div id="target"></div></td>
	<td>楼栋类型<s:select list="#{'':'--全部类型--','住宅':'住宅','商铺':'商铺'}" name="houseType" id="houseType" theme="simple"></s:select></td>
	<td>房间编号<s:textfield name="houseId" id="houseId"/></td>
	<td><input type="submit" value="查询" class="search_btn" /></td>
</tr></table>
</form>

</div>
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>住户单元一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header">房号</td>
					<td class="td_header">房间地址</td>
					<td class="td_header">建筑面积</td>
					<td class="td_header">类型</td>
					<td class="td_header">楼层</td>
					<td class="td_header">状态</td>
					<td class="td_header">业主姓名</td>
					<td class="td_header">联系电话</td>
					<td class="td_header">是否入住</td>
					<td class="td_header">备注</td>
					<td class="td_header">操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                    <td class="td_c">${stuts.index+1}</td>
					<td class="td_c">${id}</td>
					<td class="td_c">${houseAddress}</td>
					<td class="td_c" id="a">${buildnum}</td>
					<td class="td_c">${houseType}</td>
					<td class="td_c">${layerType}</td>
					<td class="td_c">${isSale}</td>
					<td class="td_c">${ownerName}&nbsp;</td>
					<td class="td_c">${mobTel}&nbsp;</td>
					<td class="td_c">${occupancyType}&nbsp;</td>
					<td class="td_c"><s:if test="remark!=null&&remark.length()>10">
						<s:property value="remark.substring(0,10)" />...</s:if>&nbsp; <s:else>
						<s:property value="remark" />&nbsp;</s:else></td>
                    <td class="td_c">
                    	<a href="#" onclick="edit('${id}')"><img border="0" alt="编辑" src="/images/change.png" ></a> 
                        &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                    </td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header" align="center">合计</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c" id="asum" align="right">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
				</tr>
				<tr>
					<td class="td_header" align="center">累计</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c" align="right">&nbsp;${requestScope.building}</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
					<td class="td_c">&nbsp;</td>
				</tr>
      		</table>
           <div class="table_footer">
           	<a href="###" class="add fl" onclick="add()">新增</a>
           	<a href="###" class="add fl" onclick="addall()">上传</a>
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
   <!----main_right结束--->
<script type="text/javascript">
function edit(id){	
	var vReturnValue = window.showModalDialog("cell!edit.action?id="+id,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=620px;center=true");
	if ("success" == vReturnValue) 
		window.location.reload();
}
function del(id){
	if (confirm('确定要删除吗')) {
		$.post("cell!del.action", {'id': id},     
				function (data, textStatus){
					var responseText =  eval('(' + data + ')');
					alert(responseText.msg);
					if(responseText.success)
						window.location.reload();		   		
				});
	}
}
function  doSum(tt,ttToal){
		if(tt.length!=undefined){
		  var sumrow=0.0;
		  for(var i=0;i< tt.length;i++){
			var tmpVal= tt[i].innerHTML;
		  	if(!isNaN(tmpVal)==true)
		  		sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);   
		  }
		  ttToal.innerHTML = sumrow.toFixed(2);
		}else
			ttToal.innerHTML=tt.innerHTML;
}
var s=document.getElementById("a");
if(s)
	doSum(a,asum);
		 
</script>

<script type="text/javascript">
function doRequest(){
	$("#target").load("../../basedata/edifice!getajaxedifice.action?areaId="+$("#areaId").val());
}
doRequest();

</script>
</body>
</html>
