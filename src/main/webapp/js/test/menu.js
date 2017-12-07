

//——新开窗口代码开始
function setroom(theURL){  			
window.open (theURL,'设置待租房','height=100,width=200,top=200,left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}
function lookingroom(theURL){  			
window.open (theURL,'查看房间','height=370,width=240,top=200,left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}
function setfault(theURL){
window.open (theURL,'设置故障房','height=260,width=200,top=200,left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}
function setroom2(theURL){ 					//-- 新开窗口总在最前面（其实是父页弹出来的一个子窗口）
y=window.showModalDialog(theURL,"","Height=100,Width=200;help: no"); 
} 
function look_fault(theURL){
window.open (theURL,'查看故障房','height=260,width=200,top=200,left=200, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}

function print_fault(theURL){
window.open (theURL,'打印房间故障','height=480,width=640,top=200,left=200,toolbar=yes, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
}
//——新开窗口代码结束



//——真正的屏蔽鼠标右键代码开始

if (window.Event) 
document.captureEvents(Event.MOUSEUP); 

function nocontextmenu() 
{
event.cancelBubble = true
event.returnValue = false;

return false;
}

function norightclick(e) 
{
if (window.Event) 
{
if (e.which == 2 || e.which == 3)
return false;
}
else
if (event.button == 2 || event.button == 3)
{
event.cancelBubble = true
event.returnValue = false;
return false;
}

}

document.oncontextmenu = nocontextmenu; // for IE5+
document.onmousedown = norightclick; // for all others
//——真正的屏蔽鼠标右键代码结束

//帮助内容
function about()
{
    var mess;
    mess="---------------关于---------------\r\n\r\n"+

         "威控科技科技 VControl 2007 \r\n\r\n"+
         "版本：  V 1.01 \r\n\r\n"+
         "Copyright (c) VControl 2007 All Rights Reserved\r\n\r\n"+
         "";
    alert(mess);

}

function support()
{
    var mess;
    mess="-------------技术支持-------------\r\n\r\n"+

         "威控科技科技发展有限公司 \r\n\r\n"+
         "http://www.vcontrol.com.cn \r\n\r\n"+
         "E-mail: sales@VControl.com.cn\r\n\r\n"+
         
        
	 "";
    alert(mess);

}



function help()
{
    var mess;
    mess="－－－－－－－－－－－软件帮助－－－－－－－－－－\r\n\r\n"+

         "界面简介: \r\n\r\n"+
         "1. 顶部为菜单条 \r\n\r\n"+
         "2. 左侧为软件图例以及通讯出现故障的房间列表\r\n\r\n"+
         "3. 中间为软件的房间状态显示部分 \r\n\r\n"+
         "4. 底部为软件的状态栏、版本号、系统现在的时间\r\n\r\n"+
         "－－－－－－－－－－－－－－－－－－－－－－－－－\r\n\r\n"+
         "功能说明： \r\n\r\n"+
         "1. 房间状态分为：出租、待租、退房三种，\r\n\r\n"+
         "   分别以不同颜色表示。  \r\n\r\n"+
         "2. 房态颜色的设置：在顶部“设置”菜单中  \r\n\r\n"+
         "   点击“系统设置按钮，在弹出的窗口中选择要设置的颜色； \r\n\r\n"+
         "   确定后，选择“操作”菜单中的“重载系统”。  \r\n\r\n"+
         "   房态颜色设置完成。  \r\n\r\n"+
         "3. 设置房态的刷新时间：方法同上， \r\n\r\n"+
         "   弹出窗口的第一项就是刷新时间的设定，单位为秒，  \r\n\r\n"+
         "   默认刷新时间为15秒。  \r\n\r\n"+
         "4. 清理、勿扰功能的显示：  \r\n\r\n"+
         "   当房态设置为“清理”、“勿扰”时，房间号的下面便会  \r\n\r\n"+
         "   出现相关的提示字样。  \r\n\r\n"+
         "－－－－－－－－－－－－－－－－－－－－－－－－－ \r\n\r\n"+
         "   更多帮助请参阅我们的网站：www.vcontrol.com.cn  \r\n\r\n"+
	 "";
    alert(mess);

}
//————帮助内容结束