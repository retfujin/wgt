<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

<base target="_self" />
<style>
<!--
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
}

@page {
	margin: 1.0in .75in 1.0in .75in;
	mso-header-margin: .5in;
	mso-footer-margin: .5in;
	mso-page-orientation: landscape;
}

tr {
	mso-height-source: auto;
	mso-ruby-visibility: none;
}

col {
	mso-width-source: auto;
	mso-ruby-visibility: none;
}

br {
	mso-data-placement: same-cell;
}

.style0 {
	mso-number-format: General;
	text-align: general;
	vertical-align: middle;
	white-space: nowrap;
	mso-rotate: 0;
	mso-background-source: auto;
	mso-pattern: auto;
	color: windowtext;
	font-size: 12.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	border: none;
	mso-protection: locked visible;
	mso-style-name: 常规;
	mso-style-id: 0;
}

td {
	mso-style-parent: style0;
	padding-top: 1px;
	padding-right: 1px;
	padding-left: 1px;
	mso-ignore: padding;
	color: windowtext;
	font-size: 12.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-number-format: General;
	text-align: general;
	vertical-align: middle;
	border: none;
	mso-background-source: auto;
	mso-pattern: auto;
	mso-protection: locked visible;
	white-space: nowrap;
	mso-rotate: 0;
}

.xl24 {
	mso-style-parent: style0;
	font-size: 14.0pt;
	font-weight: 700;
	font-family: 楷体_GB2312, monospace;
	mso-font-charset: 134;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl25 {
	mso-style-parent: style0;
	font-size: 14.0pt;
	font-weight: 700;
	font-family: 楷体_GB2312, monospace;
	mso-font-charset: 134;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl26 {
	mso-style-parent: style0;
	font-size: 14.0pt;
	font-weight: 700;
	font-family: 楷体_GB2312, monospace;
	mso-font-charset: 134;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl27 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl28 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl29 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl30 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: center;
	border: .5pt solid windowtext;
}

.xl31 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	border: .5pt solid windowtext;
}

.xl32 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: center;
	border: .5pt solid windowtext;
	background: silver;
	mso-pattern: auto none;
}

.xl33 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	mso-number-format: "General Date";
	text-align: center;
	border: .5pt solid windowtext;
}

.xl34 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: right;
	border: .5pt solid windowtext;
}

.xl35 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	mso-number-format: "General Date";
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl36 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl37 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: none;
}

.xl38 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl39 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	text-align: left;
	border: .5pt solid windowtext;
}

ruby {
	ruby-align: left;
}

rt {
	color: windowtext;
	font-size: 9.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-char-type: none;
	display: none;
}

.xl271 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	border-top: .5pt solid windowtext;
	border-right: none;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}
-->
</style>
<STYLE media="print">
.Noprint {
	DISPLAY: none
}

.PageNext {
	PAGE-BREAK-AFTER: always
}
</STYLE>
</head>
<body link="blue" vlink="purple">
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		style='border-collapse: collapse; table-layout: fixed; width: 100%'>
		<col
			style='mso-width-source: userset; mso-width-alt: 100%; width: 10%'>
		<col span="3"
			style='mso-width-source: userset; mso-width-alt: 100%; width: 10%'>
		<col
			style='mso-width-source: userset; mso-width-alt: 100%; width: 20%'>
		<col
			style='mso-width-source: userset; mso-width-alt: 100%; width: 40%'>
		<tr height="25" style='height: 18.75pt'>
			<td colspan="6" height="25" class="xl24" width="100%"
				style='border-right: .5pt solid black; height: 18.75pt; width: 722pt'>收据单</td>
		</tr>
		<tr>
			<td colspan="2" class="xl34"
				style='height: 14.25pt; border-top: none'><div align="left">
					收据单号：
					<s:property value="#parameters.recordBh" />
				</div></td>
			<td colspan="2" class="xl34"
				style='height: 14.25pt; border-top: none'><div align="left">
					房号：
					<s:property value="viewList[0][1]" />
				</div></td>
			<td colspan="2" class="xl34"
				style='height: 14.25pt; border-top: none'><div align="left">
					业主姓名：
					<s:property value="viewList[0][2]" />
				</div></td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		style='border-collapse: collapse; table-layout: fixed; width: 100%'>
		<tr height="25">
			<td class="xl34" style='height: 14.25pt; border-top: none'>缴费项目</td>
			<td class="xl34" style='height: 14.25pt; border-top: none'>实收款</td>
			<td class="xl34" style='height: 14.25pt; border-top: none'>收款人</td>
			<td class="xl34" style='height: 14.25pt; border-top: none'>缴费日期</td>
			<td class="xl34" style='height: 14.25pt; border-top: none'>查看详细</td>
		</tr>
		<s:iterator value="viewList" status="stuts">
			<tr <s:if test="#stuts.odd==false"> bgcolor="#d9d9d9" </s:if>
				<s:else>bgcolor="#eeeeee"</s:else>>
				<td class="xl34" style='height: 14.25pt; border-top: none'><s:property
						value="viewList[#stuts.index][3]" />&nbsp;</td>
				<td class="xl34" style='height: 14.25pt; border-top: none'><s:property
						value="viewList[#stuts.index][4]" />&nbsp;</td>
				<td class="xl34" style='height: 14.25pt; border-top: none'><s:property
						value="viewList[#stuts.index][5]" />&nbsp;</td>
				<td class="xl34" style='height: 15.25pt; border-top: none'>
					<%
						//是从查询统计过来的，只有查看，不能编辑
						String type = request.getParameter("type");
						if("view".equals(type)){
					%>
					
						<s:property value="viewList[#stuts.index][6].toString().substring(0,10)" />
					<%}else{%>
						<input type="text" id="rq" name="rq" size="10" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="viewList[#stuts.index][6].toString().substring(0,10)" />" />
						<input	type="button" value="调整" onclick="chargeDate('<s:property	value="viewList[#stuts.index][0]" />')" />&nbsp;
					<%} %>
				</td>
				<td class="xl34" style='height: 14.25pt; border-top: none'>
					<input	type="button" value="明细" onclick="checkDetail('<s:property	value="viewList[#stuts.index][0]" />')" />&nbsp;
					<input	type="button" value="打印" onclick="gotoprint()" />&nbsp;
					<%
						String toType = request.getParameter("toType");
						if("del".equals(toType)){
					%>
						<input	type="button" value="删除"	onclick="del('<s:property value="viewList[#stuts.index][0]" />')" />
					<%} %>
				</td>
			</tr>
			<tr><td>
				
					
			</td></tr>
		</s:iterator>
	</table>
	<br/>
	
	<div id="result"></div>
</body>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<script type="text/javascript">
function del(pid){
	if (confirm('确定要删除吗')) {
		$.post("../../chg/changemoney!deleteMoney.action", {'pid': pid},     
				   function (data, textStatus){
						var responseText =  eval('(' + data + ')');
						alert(responseText.msg);
						if(responseText.success)
							window.location.reload();
		});
	}
}

	function checkDetail(pid) {
		
		url = "../../chg/pay!checkDetail.action?pid=" + pid+"&t="+Math.floor(Math.random()*10+1);
	//	window.showModalDialog(encodeURI(url), "费用明细",	"dialogWidth=800px;dialogHeight=600px");
		$("#result").load(url);
	}
	
function chargeDate(pid) {
	if (confirm('确定要改变日期吗')) {
		var v_rq = document.getElementById("rq").value;
		//alert(pid);
		if(v_rq==''){
			alert('请填写缴费日期');
			return ;
		}
			
		$.post("../../chg/changemoney!chargeDate.action", {'pid': pid,'rq':v_rq},     
				   function (data, textStatus){
						var responseText =  eval('(' + data + ')');
						alert(responseText.msg);
						if(responseText.success)
							window.location.reload();
		});
	}
}

function gotoprint(){
	var url='pay!printview.action?recordBh=<s:property value="#parameters.recordBh" />';
	//var ret = window.showModalDialog(encodeURI(url), WebBrowser, "dialogWidth=600px;dialogHeight=400px;resizable=yes");
	window.open(url,"缴费单","height=320px,top=0,left=0,width=420px,status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes");
	//window.showModalDialog(encodeURI(url),"","dialogWidth=800px;dialogHeight=600px");
}

</script>
</html>