<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../js/func.js"></script>
<body>
<s:set id="df" name="df" value="new java.text.DecimalFormat('##0.00')"/>
<s:set id="simple" name="simple" value="new java.text.SimpleDateFormat('yy/MM') "/>

<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>客户收费</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
       				<td class="td_header">序号</td>
					<td class="td_header">客户编号</td>
			  		<td class="td_header">姓名</td>
			  		<td class="td_header">面积</td> 	
			  		<td class="td_header">收费项</td>	
			  		<td class="td_header">费用起截日期</td>
			  		<td class="td_header">缴费日期</td>
					<td class="td_header">应收</td>
					<td class="td_header">已收</td>
					<td class="td_header">欠缴</td>
					<td class="td_header">收缴率</td>
					<td class="td_header">缴费</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
                    <td class="td_c" height="28px">${stuts.index+1}</td>
					<td class="td_c"><s:property value="viewList[#stuts.index][0]"/></td>
			  		<td class="td_c"><s:property value="viewList[#stuts.index][1]"/></td>
			  		<td class="td_c"id="a" ><s:property value="viewList[#stuts.index][2]"/></td>
					<td class="td_c"><s:property value="viewList[#stuts.index][3]"/></td>
					<td class="td_c"><s:property value="#simple.format(viewList[#stuts.index][4])"/>--<s:property value="#simple.format(viewList[#stuts.index][5])"/></td>
					<td class="td_c"><s:property value="viewList[#stuts.index][6]"/>&nbsp;</td>
					
					<td class="td_c"id="b"><s:property value="viewList[#stuts.index][7]"/></td>
					<td class="td_c"id="c" ><s:property value="viewList[#stuts.index][8]"/></td>
					<td class="td_c"id="d"><s:property value="viewList[#stuts.index][9]"/></td>
					<td class="td_c"><s:property value="#df.format(viewList[#stuts.index][8]/viewList[#stuts.index][7]*100)"/>%</td>
					<td class="td_c"><a href="pay!pay.action?houseId=<s:property value="viewList[#stuts.index][0]"/>">缴费</a></td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header">合计</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="asum" align="center">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="bsum" align="center">&nbsp;</td>
					<td class="td_header" id="csum" align="center">&nbsp;</td>
					<td class="td_header" id="dsum" align="center">&nbsp;</td>
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
	doSum(b,bsum);
	doSum(c,csum);
	doSum(d,dsum);

	
</script>
    
</body>
</html>