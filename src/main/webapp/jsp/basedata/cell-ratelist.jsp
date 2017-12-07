<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>
<link href="/styles/search.css" rel="stylesheet" type="text/css"/>
<title>入住率统计列表</title>
</head>
<body>
 

<form class="search_form" name="form1" action="cell!ratelist.action" method="post">
	房间类型：<s:select list="#{'':'','住宅':'住宅','商铺':'商铺'}"	name="houseType" theme="simple"></s:select>
	楼层类型：<s:select list="#{'':'','多层':'多层','小高层':'小高层','高层':'高层'}" name="layerType" theme="simple"></s:select>
	<s:hidden name="hid" value="-1"></s:hidden>
	<input type="submit" value="查询" class="search_btn" />
</form>


<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>入住率统计</p> 
           </div>
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
					<td class="td_header">序号</td>
					<td class="td_header">项目部名称</td>
					<td class="td_header">项目总户数</td>
					<td class="td_header">已入住户数</td>
					<td class="td_header">未入住户数</td>
					<td class="td_header">入住率</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
           		 <tr>
                    <td class="td_c">${stuts.index+1}</td>
					<td class="td_c"><s:property value="viewList[#stuts.index][0]" /></td>
					<td class="td_c"><s:property value="viewList[#stuts.index][1]" /></td>
					<td class="td_c"><s:property value="viewList[#stuts.index][2]" /></td>
					<td class="td_c"><s:property value="viewList[#stuts.index][3]" /></td>
					<td class="td_c"><s:property value="viewList[#stuts.index][4]" /></td>
                </tr>	
              </s:iterator>
      		</table>            
       </div>
       <!---table_area结束--->
   </div>
   <!----main_right结束--->

</body>
</html>
