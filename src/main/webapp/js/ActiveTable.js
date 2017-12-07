//插入一行
function addRow(tableName) {	
	var newRow = tableName.insertRow(tableName.rows.length-1);//插入一行
	var nowLocationRow=tableName.rows.length-2;//插入行的当前位置(在什么位置插入一行)
	var tableRowSum=tableName.rows.length;  
	var inputName=""; 
	var inputName1="";
	var newCell;
	var newCellInputSize;
	for (i = 0; i <tableName.rows[0].cells.length; i++) {
		inputName="R"+tableRowSum.toString()+"C"+i.toString();
		inputName1=inputName;
		if(i==1)
			inputName1="detailList["+(nowLocationRow-1).toString()+"].wlCatalogEO.id";
		else if(i==6){
			inputName1="detailList["+(nowLocationRow-1).toString()+"].price";
		}
		else if(i==7){
			inputName1="detailList["+(nowLocationRow-1).toString()+"].num";
		}
		else if(i==8){
			inputName1="detailList["+(nowLocationRow-1).toString()+"].amount";
		}
		else if(i==9){
			inputName1="detailList["+(nowLocationRow-1).toString()+"].goodsAllocation";
		}
		else if(i==10){
			inputName1="detailList["+(nowLocationRow-1).toString()+"].lotNumber";
		}
		
		newCell=newRow.insertCell(-1);
		newCell.className="td_border";
		if(i==0)//如果是第一列：显示序列号
			//newCell.innerHTML=nowLocationRow;
		{	
			newCell.innerHTML="<input type=\"button\" onClick=\"gotoUrl('"+nowLocationRow+"')\" value=\"选择\" class=\"con_button\"/>";		
		}else{
			newCellInputSize=document.getElementById("addC"+i).size;
			newCell.innerHTML="<input type=\"text\" id=\""+inputName+"\" name=\""+inputName1+"\" size=\""+newCellInputSize+"\" class=\"con_input_con\"/>";
			if(i==1)
				newCell.innerHTML="<input type=\"text\" id=\""+inputName+"\" name=\""+inputName1+"\" size=\""+newCellInputSize+"\" readonly class=\"con_input_con\"/>";
			
			//alert(newCell.innerHTML);
		} 
	}  	
	document.getElementById("R"+tableRowSum.toString()+"C"+event.srcElement.id.toString().substring(4).toString()).focus();
}



//删除一行
function delRow(TableName){
	 if(TableName.rows.length<=2){
		 alert("对不起，最后一行不能删除！");
	 }else{
		TableName.deleteRow(TableName.rows.length-2)
	 }	 
}


//插入一列
function addCol(TableName) {
	var TableColSum=TableName.rows(0).cells.length
	for (i=0;i<TableName.rows.length;i++) {
		var inputName=i+TableColSum.toString();
		rowCount = TableName.rows[i].cells.length - 1
		newCell = TableName.rows[i].insertCell(TableName.rows(i).cells.length)
		newCell.innerHTML ="<input id='n"+inputName+"' "+InputText;
	}	
}


//删除一列
function delCol(TableName){
	var allRowSum=TableName.rows.length;
	if(TableName.rows(0).cells.length-1==0){
		alert("Sorry! the last one can't be deleted");
	}else{
		for (var i=0;i<allRowSum;i++) {
			TableName.rows[i].deleteCell(TableName.rows(i).cells.length-1)
		}
	}			
}


function checkTable(){
if(Active_Table.rows.length<3){
		alert("请添加物料");
		return false;
	}
	for (i = 1; i <Active_Table.rows.length-1; i++) {
		if(Active_Table.rows[i].cells[1].childNodes[0].value=='')
		{
			alert("请添加物料");
			return false;
		}else{//检测数量和单价和金额
		
			if(isMoney(Active_Table.rows[i].cells[6].childNodes[0],"单价")==false){
				Active_Table.rows[i].cells[6].childNodes[0].focus();
				return false;
			}
		
			if(Active_Table.rows[i].cells[7].childNodes[0].value==''){
				alert("数量不能为空");
				Active_Table.rows[i].cells[7].childNodes[0].focus();
				return false;
			}
			if(checkOnlyNum(Active_Table.rows[i].cells[7].childNodes[0],"数量")==false){
				Active_Table.rows[i].cells[7].childNodes[0].focus();
				return false;
			}
			if(isMoney(Active_Table.rows[i].cells[8].childNodes[0],"金额")==false){
				Active_Table.rows[i].cells[8].childNodes[0].focus();
				return false;
			}
			
		}
	}
	return true;
}