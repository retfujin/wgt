function show()
{
//+"</td><td align='right'><img  src='close1.gif' onclick='DialogClose()' onmouseover='Icon_Close_Over(this)' onmouseout='Icon_Close_Out(this)'/></td></tr>"
    
    var d_mask=document.getElementById('mask');
    var d_dialog = document.getElementById('dialog');
    
    
    d_mask.style.width = document.body.clientWidth ;
    d_mask.style.height=document.body.clientHeight;
    
    
    d_dialog.style.top = document.body.clientHeight / 2 - 200;
    d_dialog.style.left =document.body.clientWidth / 2 -250;
    //d_dialog.style.width = 400;
	//d_dialog.style.height = 250;
    var Inner = "<input type='button' value='Close' onclick='DialogClose()'/>"
    
  //  var info = "<table cellspacing='0' width='100%' height='100%'>"+
 //   "<tr class='dgtitle'><td>"+title+"<td></tr>"
 //   +"<tr><td>"
 //   +msg
 //   +"</td></tr>"
 //   +"<tr class='dgfooter'><td></td><td>"
 //   +"<center><input type='button' value='Close' onclick='DialogClose()'class='formButton'/></center>"
 //   +"</td></tr>"
 //   +"</table>";
    
   
 //   d_dialog.innerHTML =info;
  
   // disableSelect()
    
    d_mask.style.visibility='visible';
    d_dialog.style.visibility='visible';
   
}

function Icon_Close_Over(obj)
{
    obj.src='close.gif';
}

function Icon_Close_Out(obj)
{
    obj.src='close1.gif'
}

function DialogClose()
{
    var d_mask=document.getElementById('mask');
    var d_dialog = document.getElementById('dialog');
       
    enableSelect()
    d_mask.style.visibility='hidden';
    d_dialog.style.visibility='hidden';
}

function disableSelect()
{   
        for(i=0;i<document.all.length;i++)  
              if(document.all(i).tagName=="SELECT")
                document.all(i).disabled = true;
 } 
 
function enableSelect()
{
    for(i=0;i<document.all.length;i++){   
              if(document.all(i).tagName=="SELECT")
              {
                document.all(i).disabled = false;
              }
        }   
}  

function divBlock_event_mousedown()
{ 
var e, obj, temp;
obj=document.getElementById('dialog');  
e=window.event?window.event:e;
obj.startX=e.clientX-obj.offsetLeft;  
obj.startY=e.clientY-obj.offsetTop;  
document.onmousemove=document_event_mousemove;  
temp=document.attachEvent?document.attachEvent('onmouseup',document_event_mouseup):document.addEventListener('mouseup',document_event_mouseup,'');
}
  
  
function document_event_mousemove(e)
{
var e, obj;  
obj=document.getElementById('dialog'); 
e=window.event?window.event:e;   
with(obj.style){  
    position='absolute';  
    left=e.clientX-obj.startX+'px';  
    top=e.clientY-obj.startY+'px'; 
    }
}

function document_event_mouseup(e)
{ 
var temp; 
document.onmousemove='';
temp=document.detachEvent?document.detachEvent('onmouseup',document_event_mouseup):document.removeEventListener('mouseup',document_event_mouseup,'');
}


window.onresize = function()
{
    var d_mask=document.getElementById('mask');
    var d_dialog = document.getElementById('dialog');
    
   
    d_mask.style.width = document.body.clientWidth ;
    d_mask.style.height=document.body.clientHeight;
}

