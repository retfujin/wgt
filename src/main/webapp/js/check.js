function  checkCharNum(obj)
{
    document.all.CharNum.innerText="共"+obj.value.length+"个字"
}
function del(url)
{
	if(confirm("您确定要删除吗？"))
		window.location.href=url;
}

function isNotNull(obj,name)
{
	if(obj.value=='')
	{
		alert("您输入的"+name+"不能为空");
		return false;
	}
	else
		return true;
}


function  doSum(tt,ttToal){
	 
	if(tt.length!=undefined){
		  var   sumrow=0.0;  	
		  for(var i=0;i< tt.length;i++)
		  {
			var tmpVal= tt[i].innerHTML;
			if(tmpVal=='')
				tmpVal=0;
			
		  	if(!isNaN(tmpVal)==true)
		  		sumrow += isNaN(tmpVal) ? 0 : parseFloat(tmpVal);   
		  }
		  ttToal.innerHTML = sumrow.toFixed(2);
		}else{
			ttToal.innerHTML=tt.innerHTML;
		}
	  
}