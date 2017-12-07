//及时发送
function checknow()
{
	document.all.now.checked=true;
	document.all.sendtime1.checked=false;
	setTimeable("false");
//	document.all.selectStr.style.display="none";
	
}

/**循环的方式发送短信**/
function checkCyclesend(){
    document.all.cyclesend.checked=true;
    document.all.now.checked=false;
    document.all.sendtime1.checked=false;
    setTimeable("false");

    var retValue = window.showModalDialog("selectTimeModel.jsp","","help:0;resizable:0;status=0;scrollbars=0;dialogWidth=35;dialogHeight=21;center=true");
    var retStr;
     if (typeof(retValue)=='undefined'){
    	selectStr = "您取消了循环发送方式，请重新选择，如果提交，将是立即发送方式！";
    	document.all.selectStr.value=selectStr;
		document.all.now.checked=true;
     }
     else
     {     
		    document.all.cycleFlag.value="cycleFlag";
			 var dd = new Date();
		    	if(retValue != null && retValue != "") {
		        	retStr = retValue.split("\*");
				dd = new Date();
				var temp = dd.getYear()+"-"+(dd.getMonth()+1)+"-"+dd.getDate()+" "+retStr[0];
				document.all.waitTime.value=temp;
				dd = new Date(document.all.waitTime.value);
				document.all.waitType.value=retStr[1];
				document.all.waitContent.value=retStr[2];
				document.all.beginTime.value=retStr[3];
				document.all.endTime.value=retStr[4];
				selectStr = "您选择的是循环发送方式！触发时间："+retStr[0]+" 开始时间："+document.all.beginTime.value+" 结束时间："+document.all.endTime.value;
		  		document.all.selectStr.value=selectStr;
  		
    }
  }

}



/****定时发送*******/
function checksendtime()
{
	document.all.sendtime1.checked=true;
	document.all.now.checked=false;
	var intMonth = 1;
	for(var i=0;i<document.all.sMonth.value;i++){
	    intMonth++;
	}
	setTimeable("true");
}

//定时发送时间显示
function setTimeable(isable)
{
  if(isable=="true")
  {
	  document.all.sYear.disabled=false;
	  document.all.sMonth .disabled=false;
	  document.all.sDate .disabled=false;
	  document.all.sHour .disabled=false;
	  document.all.sMinute.disabled=false;
  }
  else
  {
	  document.all.sYear.disabled=true;
	  document.all.sMonth .disabled=true;
	  document.all.sDate .disabled=true;
	  document.all.sHour .disabled=true;
	  document.all.sMinute.disabled=true;
  }
}

function submitsend()
{
	if ( form1.receivers.length == 0 )
	{
		alert("您还没有选择业主！");
		return false;
	}
	
	//除去空格
		var value = form1.smContent.value;
		while ( value.substr(0,1) == " " || value.substr(0,1) == "\t" ||
		value.substr(0,1) == "\r" || value.substr(0,1) == "\n")
		{
			value = value.substr(1,value.length);
		}
		if ( value == "" )
		{
			alert("请输入消息内容！");
			form1.smContent.value = value;
			//frmMessage.msgContent.focus();
			return false;
		}
	if ( form1.smContent.value == "" )
	{
		alert("请输入消息内容！");
		return false;
	}
	
}





