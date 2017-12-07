<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<body>

<% 	
    String areaId=request.getParameter("areaId") != null ? request.getParameter("areaId") : "";
    String houseId=request.getParameter("houseId")!=null ? request.getParameter("houseId"):"";
	String meterType=request.getParameter("meterType")!=null? request.getParameter("meterType"):"";

%>
<SCRIPT type="text/javascript" src="/js/hanx.js"></SCRIPT>
<script type="text/javascript"> 
function addall(){
	//var vReturnValue = window.showModalDialog("ownermeter!ownermeterall.action","","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=650px;dialogHeight=240px;center=true");
	window.open("ownermeter!ownermeterall.action");
}
function sub(id,rowId)
{
	var nowRec = document.getElementsByName("nowRecord")[rowId].value;
	var timesNow  = document.getElementsByName("times")[rowId].value; 
	if(isInteger(nowRec) == false)
	{
		alert("当前度数必须为整数！");
		document.getElementsByName("nowRecord")[rowId].focus();
		return false;
	}
	var url="ownermeter!updateHouseMeter.action?id="+id+"&nowRecord="+nowRec+"&timesNow="+timesNow;
	var vReturnValue = window
	.showModalDialog(
			url,
			"",
			"help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=10;dialogHeight=10;center=true");

	window.location.reload();
}
function del(id)
{
	if(confirm('确定要删除吗')){
		var vReturnValue  =window.showModalDialog("ownermeter!del.action?id="+id,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=10;dialogHeight=10;center=true");
		  if(undefined == vReturnValue)
		  {
			  window.location.reload();
		  }
	}

}
</script>



<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>业户表资料设定</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                <td class="td_header">序号</td>
				<td class="td_header">房间编号</td>
		    	<td class="td_header">房间地址</td>
		    	<td class="td_header">类型</td>
		    	<td class="td_header">当前度数</td>
		    <!-- 	<td class="td_header">回转度数</td> -->
		    	<td class="td_header">操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                <td class="td_c">${stuts.index+1}</td>
				<td class="td_c">${house.id}</td>
			   	<td class="td_c">${house.houseAddress}</td>
			   	<td class="td_c">${meterType}</td>
			  
			   	<td class="td_c"><input type="text" name="nowRecord" value="${nowRecord}">
			   	 	<input type="hidden" name="times" value="1">
			   	</td>
			   <!-- 	 	<td class="td_c">${backRecord}</td> -->
                    <td class="td_c">
                    	<input type="button" value="保存" class="a" onclick="return sub(${id},${stuts.index})">
                        &nbsp;&nbsp;<a href="#" onclick="del('${id}')"><img border="0" alt="删除" src="/images/delete.png" ></a>
                    </td>
                </tr>	
              </s:iterator>
      		</table>
           <div class="table_footer">
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

 
</body>
</html>
