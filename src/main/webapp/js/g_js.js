/**
本文件用于存放公用的js
*/
function getCookie(name)
{
  var arr = document.cookie.split(/;/g);
  if(!arr)return "";
  for(var i=0;i<arr.length;i++)
  {
        var aCookie = arr[i];
	var aArr = aCookie.split(/=/g);
	if(!aArr)continue;
	if(aArr.length!=2)continue;
	if(aArr[0].trim()==name)return aArr[1];
  }

  return "";
}
function setCookie(name,value)
{
	var d = new Date();
	d.setTime(d.getTime() + 365*24*60*60*1000);
	document.cookie = name + "=" + value + ";expires=" + d.toGMTString();
}

//切换控件的可见/不可见样式
function toggleDisplay(obj)
{
	if(obj.style.display == "none")
		obj.style.display = "";
	else
		obj.style.display = "none";
}

//返回字符串str的字节数，如果不包含汉字，则返回原长度，否则返回长度的二倍
function strlen(str)
{
	//检查是否包含汉字
	var containsWideChar = false;
	for(var i=0;i<str.length;i++)
		if(str.charCodeAt(i)>255)
		{
			containsWideChar = true;
			break;
		}

	if(containsWideChar)
		return str.length*2;
	else
		return str.length;

}

String.prototype.trim = function()
{
  return this.replace(/(^\s+)|(\s+$)/g,"");
}

String.prototype.len = function()
{
  return this.replace(/[\u00FF-\uFFFF]/g,"aa").length;
}

function myover()
{
    for(var i=0;i<this.cells.length;i++)
    {
      var aCell = this.cells[i];
      aCell.style.backgroundColor = "#CCCCCC";
    }
}
function myout()
{
    for(var i=0;i<this.cells.length;i++)
    {
      var aCell = this.cells[i];
      aCell.style.backgroundColor = "";
    }

}

/**
* 例：appendHandler("window.onload",myonload);
* 就是把myonload函数挂接到window.onload事件中。而且如果window.onload以前设置过处理函数，不会丢失。
*
*/
function appendHandler(eventDesc,handler)
{
   var myevent = eval(eventDesc);
   var oldhandler = myevent;
   if(myevent)
   {
     var cmd = eventDesc + "=function(){oldhandler();handler();}";
     eval(cmd);
   }else
   {
     var cmd = eventDesc + "=handler";
   }
}


