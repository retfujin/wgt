<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="../../js/check.js"></script>
  <body>
  <table id="tab1" wnewsidth="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="tableBorder">
     <tr>
      <th height=25 colspan="19" class="tableHeaderText">物料目录一览表</th>
     </tr>

  <tr align="center">
  	<td class="forumRow"></td>
  	<td class="forumRow">物料编号</td>
    <td class="forumRow">助记码</td>
    <td class="forumRow">物料名称</td>
    <td class="forumRow">规格</td>
    <td class="forumRow">所属类别</td>
    <td class="forumRow">单位</td>
    <td class="forumRow">最小包装</td>
    <td class="forumRow">包装数量</td>
    <td class="forumRow">上限</td>
    <td class="forumRow">下限</td>
    <td class="forumRow">保质期</td>
    <td class="forumRow">体积</td>
    <td class="forumRow">产地</td>
    <td class="forumRow">品牌</td>
    <td class="forumRow">厂家</td>
    <td class="forumRow">计划单价</td>
    <td class="forumRow">销售单价</td>
    <td class="forumRow">备注</td>
  </tr>
  <s:iterator value="viewList" status="stuts">
  <tr <s:if test="#stuts.odd==false">class="td_f"</s:if><s:else>class="td_t"</s:else>>    	
  	<td ><input type="button" name="btn1" value="选择" onclick="go('<s:property value="#stuts.index+2"/>')">&nbsp;</td>
  	<td ><s:property value="id"/></td>
   	<td ><s:property value="mnemonicCode"/></td>
   	<td ><s:property value="name"/></td>
   	<td ><s:property value="norm"/></td>
   	<td ><s:property value="WLAssortmentEO.name"/></td>
   	<td ><s:property value="unit"/></td>
   	<td ><s:property value="minPackage"/></td>
   	<td ><s:property value="NumPackage"/></td>
   	<td ><s:property value="upperLimit"/></td>
   	<td ><s:property value="lowerLimit"/></td>
   	<td ><s:property value="lifeOfQualityAssurance"/></td>
   	<td ><s:property value="volume"/></td>
   	<td ><s:property value="placeOfProduction"/></td>
   	<td ><s:property value="brand"/></td>
   	<td ><s:property value="factory"/></td>
   	<td ><s:property value="planPrice"/></td>
   	<td ><s:property value="salePrice"/></td>
   	<td ><s:property value="remarks"/></td>
   	 </tr>
  </s:iterator>
</table>
  </body>
  <script type="text/javascript">
function go(xh){
 //编号
 var retV = tab1.rows[xh].cells[1].innerHTML+",";
 //名称
  retV += tab1.rows[xh].cells[3].innerHTML+",";
 //规格
 retV += tab1.rows[xh].cells[4].innerHTML+",";
 //产地
  retV += tab1.rows[xh].cells[13].innerHTML+",";
 //单位
 retV += tab1.rows[xh].cells[6].innerHTML+",";
 //单价
 retV += tab1.rows[xh].cells[16].innerHTML+",";
  
  
  top.returnValue=retV;

  //retValue="asdfasdf";
 // top.retValue=retValue;
 	
  top.close();
  	
  }
  function   allPrpos ( obj ) { 
    // 用来保存所有的属性名称和值 
    var   props = "" ; 
    // 开始遍历 
    for ( var   p in obj ){   
        // 方法 
        if ( typeof ( obj [ p ]) == " function " ){   
            obj [ p ]() ; 
        } else {   
            // p 为属性名称，obj[p]为对应属性的值 
            props += p + " = " + obj [ p ] + " \t " ; 
        }   
    }   
    // 最后显示所有的属性 
    alert ( props ) ; 
} 
  </script>
</html>
