<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<%@ include file="/commons/meta.jsp"%>
<script type="text/javascript" src="/tabpanel/js/jquery.js"></script>
<body>
<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>物料库存一览表</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
			   <td class="td_header">入出库单编号</td>
			    <td class="td_header">入出库单类型</td>
			    <td class="td_header">金额</td>
			    <td class="td_header">仓库</td>
			    <td class="td_header">日期</td>
			    <td class="td_header">发票</td>
			    <td class="td_header">供应商</td>
			    <td class="td_header">采购人</td>
			    <td class="td_header">客户名称</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
			  	<td class="td_c"><s:property value="bh"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="type"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="amount"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlStoreHouseEO.name"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="rq.toString().substring(0,10)"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="InvNum"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="supplier"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="purchaseP"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="customName"/>&nbsp;</td>
		   	</tr>
		   	</s:iterator>
   	 
              <tr><td bgcolor="#FFF5EC" colspan="11" align="center"><input type="button" value="返回" onclick="javascript:window.history.go(-1)" class="a"/></td></tr>
      		</table>
            
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->
</body>
</html>