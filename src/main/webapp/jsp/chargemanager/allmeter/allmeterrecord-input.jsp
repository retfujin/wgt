<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %> 
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<SCRIPT type="text/javascript" src="/js/hanx.js"></SCRIPT>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<body>



<s:if test="viewList.size> 0">
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p> <s:if test="#request.recordMonth!=''">
	      			<s:property value="#request.recordMonth"/><s:hidden id="recordMonth1" name="recordMonth1" value="%{#request.recordMonth}"></s:hidden>
			      </s:if>
			      <s:else>
			      	<input type="text" name="recordMonth" id="recordMonth" size="7" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM'})"/>
			      </s:else>
			     	月份总表数据录入
     		</p> 
           </div>
           <form action="allmeterrecord!saveMeterData.action" name="form_input" method="get" onsubmit="return sub()">
 	        <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
		    	<td class="td_header" >表编号</td>
		    	<td class="td_header" >总表名称</td>
		    	<td class="td_header" >类型</td>
		    	<td class="td_header" >倍率</td>
		    	<td class="td_header" >单价</td>
		    	<td class="td_header" >上期度数</td>
		    	<td class="td_header" >本期度数</td>
		    	<td class="td_header" >换表数</td>
		    	<td class="td_header" >操作</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
			  <tr >    	
			  	<td class="td_c" align="left">${meterCode}</td>
			   	<td class="td_c" align="left">${meterName}</td>
			   	<td class="td_c">
			   		<s:if test="meterType.trim()=='电表'"><font color="#9467F1">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='水表'"><font color="#993333">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='暖气表'"><font color="#5FBA73">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='蒸汽表'"><font color="red">${meterType}</font></s:if>
			   			<s:if test="meterType.trim()=='热水表'">${meterType}</s:if>
			   			<input type="hidden" name="meterType" value="${meterType}">
			   	</td>
			   	<td class="td_c">${times}</td>
			   	<td class="td_c">${priceValue}</td>
			   	<td class="td_c">
			   	${lastRecord}<input type="hidden" name="lastRecord" value="${lastRecord}">
			   	</td>
			   	<td class="td_c">
				   	<input type="text" onkeypress="return inputOnlyNumber()" name="nowRecord" id="nowRecord" onfocus="this.select()" value="${nowRecord}" size="8"/>
			   		<input type="hidden" name="id" value="${id}">
			   	</td>
			   	<td class="td_c">
				   	<input type="text" onkeypress="return inputOnlyNumber()" name="changeNums" id="changeNums" onfocus="this.select()" value="${changeNums}" size="8"/>
			   	</td>
			   	<td class="td_c">
			   		<input type="button" value="换表" onclick="change(${allmeterId},'${meterCode}')" class="a">
			   	</td>
			  </tr>
			  </s:iterator>
			  <tr>
			  	<td colspan="10" align="right">抄表员：<input type="text" name="employee" value="<s:property value="viewList[0].recordName"/>" size="13"/>&nbsp;
  	抄表时间：<input type="text" id="recordTime" name="recordTime"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value="<s:property value="viewList[0].recordTime.toString().substring(0,10)"/>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  size="12" />
  				<input type="submit" name="submit" value="保存" class="save">
  				</td>			  	
			  </tr>
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
function sub()
{
	var v_recordTime = document.getElementById("recordTime");
	if(v_recordTime.value==''){
		alert("请录入抄表时间");
		return false;
	}
	return true;
}

//换表
function change(id,meterCode)
{
	var url="allmeterrecord!changeIndex.action?allmeter.id="+id+"&allmeter.meterCode="+meterCode; 
	var vReturnValue  =window.showModalDialog(url,"","help:0;resizable:0;status=0;scroll=yes;scrollbars=yes;dialogWidth=602px;dialogHeight=420px;;center=true");
	if(undefined == vReturnValue)
	{
		  window.location.reload();
	}
}
</script>

</body>
</html>
