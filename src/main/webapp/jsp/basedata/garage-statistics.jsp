<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<base target="_self">
<%@ include file="/commons/meta.jsp"%>
</head>
<body>
<%
	com.acec.core.utils.CharTools charTools = new com.acec.core.utils.CharTools();
	String areaId = request.getParameter("areaId") != null && !request.getParameter("areaId").equals("") ? request.getParameter("areaId") : "0";
	String local = request.getParameter("local") != null ? request.getParameter("local") : "";
	String hidd = request.getParameter("hidd");
	if (null == hidd)
		local = new String(local.getBytes("ISO8859-1"), "UTF-8");
	String addPram = "&areaId=" + areaId + "&local="+ charTools.Utf8URLencode(local);
%>


<form class="search_form" name="form1" action="garage!statistics.action" method="post">
	小区名称<s:select id="areaId" name="areaId" list="viewList"	 listKey="id" listValue="areaName"		theme="simple" />
	车位位置<select name="local">
			<option value="">全部</option>
			<s:iterator value="retList_1" status="stuts">
				<option value="<s:property value="retList_1[#stuts.index]"/>"><s:property
					value="retList_1[#stuts.index]" /></option>
			</s:iterator>
		</select>
	<input type="hidden" value="-1" name="hidd" />
	<input type="submit" value="查询" class="search_btn" />
</form>



<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>车位统计</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header">管理处</td>
					<td class="td_header">车位位置</td>
					<td class="td_header">车位面积</td>
					<td class="td_header">车位数量</td>
					<td class="td_header">已售数量</td>
					<td class="td_header">出租数量</td>
					<td class="td_header">空置数量</td>
					<td class="td_header">备注</td>
            </tr>
            <s:iterator value="retList" status="stuts">
            	<tr>
		            <td class="td_c">${stuts.index+1}</td>
					<td class="td_c">&nbsp;<s:property value="retList[#stuts.index][0]" /></td>
					<td class="td_c">&nbsp;<s:property value="retList[#stuts.index][1]" /></td>
					<td class="td_c" id="a"><s:if test="retList[#stuts.index][3] == null">0</s:if>
					<s:else>
						<s:property value="retList[#stuts.index][3]" />
					</s:else></td>
					<td class="td_c" id="b"><s:if test="retList[#stuts.index][2] == null">0</s:if>
					<s:else>
						<s:property value="retList[#stuts.index][2]" />
					</s:else></td>
					<td class="td_c" id="c"><s:if test="retList[#stuts.index][4] == null">0</s:if>
					<s:else>
						<s:property value="retList[#stuts.index][4]" />
					</s:else></td>
					<td class="td_c" id="d"><s:if test="retList[#stuts.index][5] == null">0</s:if>
					<s:else>
						<s:property value="retList[#stuts.index][5]" />
					</s:else></td>
					<td class="td_c" id="e"><s:if test="retList[#stuts.index][6] == null">0</s:if>
					<s:else>
						<s:property value="retList[#stuts.index][6]" />
					</s:else></td>
					<td class="td_c"><s:property value="retList[#stuts.index][7]" /></td>
                </tr>	
              </s:iterator>
              <tr>
					<td class="td_header">合计</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
					<td class="td_header" id="asum">&nbsp;</td>
					<td class="td_header" id="bsum">&nbsp;</td>
					<td class="td_header" id="csum">&nbsp;</td>
					<td class="td_header" id="dsum">&nbsp;</td>
					<td class="td_header" id="esum">&nbsp;</td>
					<td class="td_header">&nbsp;</td>
				</tr>
		</table>
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
 
</body>
<script type="text/javascript">
	function doSum(tt, ttToal) {

		var sumrow = 0.0;

		if (tt.length == undefined) {
			ttToal.innerHTML = tt.innerHTML;
		} else {
			for ( var i = 0; i < tt.length; i++) {
				var tmpVal = tt[i].innerHTML;
				//	if(isNaN(tmpVal)==true)
				//	  	alert("ta:"+tmpVal);
				sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);
			}
			ttToal.innerHTML = sumrow.toFixed(0);
		}
	}
	function doSum1(tt, ttToal) {
		var sumrow = 0.0;
		if (tt.length == undefined) {
			ttToal.innerHTML = tt.innerHTML;
		} else {
			for ( var i = 0; i < tt.length; i++) {
				var tmpVal = tt[i].innerHTML;

				//	if(isNaN(tmpVal)==true)
				//	  	alert("ta:"+tmpVal);
				sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);
			}
			ttToal.innerHTML = sumrow.toFixed(2);
		}
	}
	var a=document.getElementById("a");
	var b=document.getElementById("b");
	var c=document.getElementById("c");
	var d=document.getElementById("d");
	var e=document.getElementById("e");
	if(a)doSum1(a, asum);
	if(b)doSum(b, bsum);
	if(c)doSum(c, csum);
	if(d)doSum(d, dsum);
	if(e)doSum(e, esum);
</script>
</html>
