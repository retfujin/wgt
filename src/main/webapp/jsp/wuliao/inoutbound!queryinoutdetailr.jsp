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
			   <td class="td_header">物料编号</td>
			  	<td class="td_header">物料名称</td>
			  	 <td class="td_header">规格</td>
			    <td class="td_header">产地</td>
			    <td class="td_header">单位</td>
			    <td class="td_header">单价</td>
			    <td class="td_header">数量</td>
			    <td class="td_header">金额</td>
			    <td class="td_header">货位</td>
			    <td class="td_header">批号</td>
			    <td class="td_header">入出库单编号</td>
			    <td class="td_header">入出库单类型</td>
			    <td class="td_header">库存数量</td>
			    <td class="td_header">库存金额</td>
			    <td class="td_header">仓库</td>
			    <td class="td_header">日期</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
			  	<td class="td_c"><s:property value="wlCatalogEO.id"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlCatalogEO.name"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlCatalogEO.norm"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlCatalogEO.placeOfProduction"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlCatalogEO.unit"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="price"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="num"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="amount"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="goodsAllocation"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="lotNumber"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlInOutboundEO.bh"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlInOutboundEO.type"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="stocksNum"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="stocksAmount"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="wlInOutboundEO.wlStoreHouseEO.name"/>&nbsp;</td>
			   	<td class="td_c"><s:property value="rq.toString().substring(0,10)"/>&nbsp;</td>
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