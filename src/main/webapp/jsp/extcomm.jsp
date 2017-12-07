<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
		<link href="/ExtJS/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
		<link href="/ExtJS/resources/css/xtheme-gray.css" rel="stylesheet" type="text/css" />
		<link href="/ExtJS/resources/css/updategrid.css" rel="stylesheet" type="text/css" />
		
		<!-- ../../ExtJS库 -->
		<script type="text/javascript" src="/ExtJS/adapter/jquery/jquery.js"></script> 
		<script type="text/javascript" src="/ExtJS/adapter/ext/ext-base.js"></script>
		
		
		<!-- ENDLIBS -->
		<script type="text/javascript" src="/ExtJS/ext-all.js"></script>
		<!-- 
		<script type="text/javascript" src="/ExtJS/ext-all-debug.js"></script>
		 -->
		<script type="text/javascript" src="/ExtJS/ext-lang-zh_CN.js"></script> 
		
		<script type="text/javascript" src="/ExtJS/pPageSize.js"></script> 
		
		
		<script type="text/javascript" src="/jsp/sessionstatus.js"></script>
		
		
		<script type="text/javascript" src="/ExtJS/gridToExcel.js"></script>
		
		<!-- ../../ExtJS库 -->
		<style type="text/css"> 
			.add {
					background-image:url(/ExtJS/resources/images/default/tree/drop-add.gif)!important;
			} 
			.update{
					background-image:url(/ExtJS/resources/images/default/dd/drop-edit.gif)!important;
			} 
			.delete{
					background-image:url(/ExtJS/resources/images/default/dd/drop-delete.gif)!important;
			}
			.query{
				background-image:url(/ExtJS/resources/images/default/dd/drop-search.gif)!important;
			}
			.xls{
				background-image:url(/ExtJS/resources/images/default/dd/xls.gif)!important;
			}
			.sms{
				background-image:url(/ExtJS/resources/images/default/tree/man.gif)!important;
			}
			.save{
				background-image:url(/ExtJS/resources/images/default/grid/drop-yes.gif)!important;
			}
			.allow-float {
				clear: none !important;
			}  /* 允许该元素浮动 */
			.stop-float {
				clear: both !important;			
			}  /* 阻止该元素浮动 */
			.float-left {
				float: left;
			} /*浮动到左边*/
			.float-right {
				float: right;
			} /*浮动到左边*/
			
</style>
<style type="text/css">  
     .x-selectable, .x-selectable * {   
         -moz-user-select: text!important; 
         -khtml-user-select: text!important;
     }
</style>
</head>
</html>