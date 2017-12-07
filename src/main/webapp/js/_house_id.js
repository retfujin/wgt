/*-----------
--得到下拉列表的效果图框
----------*/

var currentInfo = "";               //用于保存当前用户输入信息
var counter = 1;                    //读取信息计数器
var isReading = true;               //是否处于监视用户输入状态


//读取用户输入信息
function readInfo() {
    var info = document.getElementById("info").value;

    /*
      当用户信息没有变化并且非空时，计数器加1
      否则更新currentInfo变量为用户当前输入，重置计数器
    */
    if (currentInfo==info && info!="") {
        counter++;
    } else {
        currentInfo = info;
        counter = 1;
    }

    //当计数器累计到3时，如果用户信息仍没有变化，表示用户已停止输入，否则继续监视
    if (counter==3) {
        getSuggest(info);               //向服务器获取提示信息
        isReading = false;              //设置监视标记为false
    } else {
        setTimeout("readInfo()", 200);  //200毫秒后再次读取用户输入信息
    }
}

//创建提示信息节点
function createSuggest(text) {
    var sDiv = "<div class='out' onmouseover=\"this.className='over'\"" + " onmouseout = \"this.className='out'\" onclick='setSuggest(this)'>" + text + "</div>";
    document.getElementById("suggest").innerHTML += sDiv;   //将新建节点加入suggest div
}

//响应鼠标点击事件，将suggest信息写入用户文本框
function setSuggest(src) {
    document.getElementById("info").value = src.innerHTML;
    hiddenSuggest();        //隐藏提示信息
}

//当用户再次键入信息时，调用此函数重新打开监视状态
function resetReading() {
    if (!isReading) {
        isReading = true;
        readInfo();         //开始监视用户文本框
    }
}

//显示提示信息
function displaySuggest() {
    document.getElementById("suggest").style.display = "";
}

//隐藏提示信息
function hiddenSuggest() {
    document.getElementById("suggest").style.display = "none";
}

//清空提示信息
function clearSuggest() {
    document.getElementById("suggest").innerHTML = "";
}

//点击“确定”后调用此函数确定文本框内信息
function showInfo() {
    alert("文本框内的信息是 " + document.getElementById("info").value);
}