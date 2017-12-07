/** 
浏览器判断 
 */  
var Sys = {};  
var ua = navigator.userAgent.toLowerCase();  
if (window.ActiveXObject)  
	Sys.ie = ua.match(/msie ([\d.]+)/)[1]; 
else if (document.getBoxObjectFor)  
	Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1];  
     
function containsArray(array, obj) {  
   for (var i = 0; i < array.length; i++) {  
     if (array[i] == obj) {  
         return i;  
         break;  
     }  
 }  
 return -1;  
}  

Array.prototype.contains = function(obj) {  
  return containsArray(this, obj);  
}  

function exportTableToJxlExcel(tableId) {  
   
  var offsetLeftArray = new Array();  
  var cell;// 单元格Dom  
 var col;// 单元格实际所在列  
   var cellStr;// 每个cell以row,col,rowSpan,colSpan,value形式  
 var cellStrArray = [];  
  var objTab = document.getElementById(tableId);  
//alert(objTab);
 // 遍历第一次取出offsetLeft集合  
  for (var i = 0; i < objTab.rows.length; i++) {  
    for (var j = 0; j < objTab.rows[i].cells.length; j++) {  
         cell = objTab.rows[i].cells[j];  
           if (offsetLeftArray.contains(cell.offsetLeft) == -1)  
              offsetLeftArray.push(cell.offsetLeft);  
     }  
  }  

  offsetLeftArray.sort(function(x, y) { return parseInt(x) - parseInt(y); });  
 //alert("offsetLeft集合:" + offsetLeftArray.join(','));  

// 遍历第二次生成cellStrArray  
 for (var i = 0; i < objTab.rows.length; i++) {  
       for (var j = 0; j < objTab.rows[i].cells.length; j++) {  
        cell = objTab.rows[i].cells[j];  
         col = offsetLeftArray.contains(cell.offsetLeft);  
         cellStr = i + ',' + col + ',' + cell.rowSpan + ',' + cell.colSpan + "," + (Sys.firefox?cell.textContent:cell.innerText);  
    	
          cellStrArray.push(cellStr);  
     }  
  }  

 // 显示  
//  var str = "行，列，rowSpan,colSpan,值/n";  
//  str += cellStrArray.join('/n');  
//  alert(str);
  //把cellStrArray传到后台   
  return cellStrArray;
}
function exportExl(sheetName){
    var  arrayStr=exportTableToJxlExcel('exportTable');
    var form = $("<form>");  
    form.attr('style','display:none');  
    form.attr('target','');  
    form.attr('method','post');  
    form.attr('action','/common/export-table-to-excel.action');  
  
    $('body').append(form);
   
    
    for(i=0;i<arrayStr.length;i++){
    	
    	var input1 = $('<input>');  
    	input1.attr('type','hidden');  
    	input1.attr('name','arrayStr');  
    	input1.attr('value',arrayStr[i]); 
    	form.append(input1);  
    }
    
      
    var input2 = $('<input>');  
    input2.attr('type','hidden');  
    input2.attr('name','sheetName');  
    input2.attr('value',sheetName);  
      
   
      
    form.submit();  
    form.remove();

};