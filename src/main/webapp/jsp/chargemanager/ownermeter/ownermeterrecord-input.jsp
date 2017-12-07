<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<SCRIPT type="text/javascript" src="/js/hanx.js"></SCRIPT>
<body>


<s:if test="viewList.size> 0">
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p><s:property value="#request.recordMonth"/>月份业户表数据录入</p> 
           </div>
           <form action="ownermeterrecord!saveMeter.action" name="form_input" method="post" onsubmit="return sub()">
           
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
		    	<td class="td_header" >房间</td>
		    	<td class="td_header" >客户</td>
		    	<td class="td_header" >面积</td>
		    	<td class="td_header" >表类型</td>
		    	<td class="td_header" >单价</td>
		    	<td class="td_header" >上期表数</td>
		    	<td class="td_header" >本期表数</td>
		    	<!--<td class="td_header" >换表数</td>-->
		    	<td class="td_header" >用量</td>
		    	<!-- <td class="td_header" >换表</td> -->
            </tr>
            <s:iterator value="viewList" status="stuts">
            <%String meterType_="";%>
			<s:set name="meterTypeId"></s:set>
			<s:if test="meterType.equals('水表')">
			<%meterType_="SB";  %>
			</s:if>
			<s:elseif test="meterType=='电表'">
			<%meterType_="DB";  %>
			</s:elseif>
			<s:elseif test="meterType=='热水表'">
			<%meterType_="RSB";  %>
			</s:elseif>
			<s:elseif test="meterType=='暖气表'">
			<%meterType_="NQB";  %>
			</s:elseif>
			  <tr >    	
			  	<td class="td_c">${houseAddress}</td>
			  	<td class="td_c">${ownerName}</td>
			  	<td class="td_c">${buildnum}</td>
			   	<td class="td_c">
					<s:if test="meterType.trim()=='电表'"><font color="#9467F1">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='水表'"><font color="#993333">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='暖汽表'"><font color="#5FBA73">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='蒸汽表'"><font color="red">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='热水表'">${meterType}</s:if>
			   			<input type="hidden" name="meterType" value="${meterType}" />
				</td>
				<td class="td_c">${priceValue}</td>
			   	<td class="td_c">
			   		${lastRecord}
				   	<input type="hidden" name="lastRecord" value="${lastRecord}" />
				   	<input type="hidden" name="backRecord" value="${backRecord}" />
			   	</td>
			   	<td class="td_c">
				   	<input type="text" name="nowRecord" onfocus="this.select()" onblur="changeNum(this,${id},${nowRecord})" id="nowRecord" value="${nowRecord}"  size="8"/>
				   	<input type="hidden" name="id" value="${id}" />
			   	</td>
			   <!--	<td class="td_c">
			   	<input type="text" size="5" value="${changeNum }" name="changeNum" onfocus="this.select()">
			   	</td>-->
			   	<td class="td_c">${useNums}</td>
				<!-- <td class="td_c">
			   		<input type="button" value="换表" class="a" onclick="return chg('${houseId}','<%=meterType_ %>')">
			   	</td> -->
			  </tr>
			  </s:iterator>
			 <!-- <tr>
			  	<td colspan="10" align="right">抄表员<input type="text" name="employee" value="<s:property value="viewList[0].recordName"/>" />&nbsp;	
  				抄表时间<input type="text"  id = "recordTime" name="recordTime"  value="<s:property value="viewList[0].recordTime.toString().substring(0,10)"/>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" size="12"/>
  				<input type="submit" name="submit" value="保存" class="save">
  				</td>			  	
			  </tr>-->
      		</table>
           <div class="table_footer">
            	<p>
            	<div id="pageBar" ></div>
            	<script type="text/javascript">
            	document.all.pageBar.innerHTML="<%=request.getAttribute("pageBar")==null?"":request.getAttribute("pageBar")%>";
				</script>
				</p>
           </div>
           </form>
       </div>
       <!---table_area结束--->
   </div>
</s:if>
<s:else>
	<div align="center">无数据</div>
</s:else> 


 
 
<script type="text/javascript"> 
function chg(houseId,meterType)
{
	var url="ownermeterrecord!changeMeter.action?houseId="+houseId+"&meterType="+meterType;
	var vReturnValue  =window.showModalDialog(url,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=405px;center=true");
	if(undefined == vReturnValue)
	{
		window.location.reload();
	}
}

function changeNum(obj,id,lastRecord){
	if(obj.value != lastRecord)
		$.post("ownermeterrecord!changeNum.action",{num:obj.value,id:id},
			function(data,textStatus){
				alert(data);
				window.location.reload();
			}
		);
}

function sub()
{
	var v_recordTime = document.getElementById("recordTime");
	if(v_recordTime.value==''){
		alert("请录入抄表时间");
		return false;
	}
	return true;
}

function frmquery(){
	if($("#edificeId")==null||$("#edificeId").val()=='')
	{
		alert("请选择楼栋");
		return false;
	}
	return true;
	
}

</script>
<script type="text/javascript" src="/styles/divDialog/modelDialog.js"></script>
<script src="/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">

function doRequest(){
	$("#target").load("../../basedata/edifice!getajaxedifice.action?areaId="+$("#areaId").val());
}
doRequest();

</script>
</body>
</html>
