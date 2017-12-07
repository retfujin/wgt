/*
 * 验证金钱
 */
//if(checkMoney(form1.price,"价格")==false)
function checkMoney(pObj,errMsg)
{
	if(pObj.value=="")
	{
		alert(errMsg+"必须是数值");
		return false;
	}
	
	 var obj = eval(pObj);
	 var patrn=/^(\d)*(\.(\d){0,4})?$/;
	 if(patrn.exec(obj.value)==null){
	 
	 	alert(errMsg+"必须是数值");
	 	if(obj.type="text")
	 	{	obj.focus();}
	 	return false;
	 }
}

function inputOnlyNumber() 
{ 
  var k = window.event.keyCode; 
  
  if (k  < 46 || k > 57) 
    return false; 
  else 
    return true; 
}


/**********************************/
/******       截取空格       ******/
/**********************************/ 
function trim(str){
    	var i = 0;
        while ((i < str.length)&&((str.charAt(i) == " ")||(str.charAt(i) == "　"))){i++;}
    	var j = str.length-1;
    	while ((j >= 0)&&((str.charAt(j) == " ")||(str.charAt(j) == "　"))){j--;}
    	if( i > j ) 
    		return "";
    	else
    		return str.substring(i,j+1);
}


/**********************************/
/*****   是否为合法的用户名   *****/
/*****用于客户开户时用户名的校验 *****/
/**********************************/
var MAX_USERNAME_LENGTH = 40;
function isValidNewUserName(str){
	var SPECIAL_USERNAME_PREFIX = "~!#$%^&_-|";//同BD_SYS_CONFIG定义的USERNAME_PREFIX保持一致
	var SPECIAL_USERNAME_SUFFIX = "~!#$%^&_-.|";//同BD_SYS_CONFIG定义的USERNAME_SUFFIX保持一致
	var USERNAME_PREFIX = "0123456789abcdefghijklmnopqrstuvwxyz"+SPECIAL_USERNAME_PREFIX;
	var USERNAME_SUFFIX = "0123456789abcdefghijklmnopqrstuvwxyz"+SPECIAL_USERNAME_SUFFIX;
	str = trim(str);
	if (str == "") {
		alert("客户名不能为空.");
		return false;
	}
	if (str.length > MAX_USERNAME_LENGTH) {
		alert("输入的客户名长度太大.");
		return false;
	}
	if ( USERNAME_PREFIX.indexOf( str.charAt(0) ) == -1) {
		alert("客户名不能以非法字符("+str.charAt(0)+")开头.");
		return false;
	}
	for(var i=1;i<str.length;i++){
		if ( USERNAME_SUFFIX.indexOf( str.charAt(i) ) == -1) {
			alert("输入的客户名含有非法字符("+str.charAt(i)+").");
			return false;
		}
	}
	/*
	if ( (/^[A-Za-z0-9~!#$%^&_-|]+$/g).test(str) == false ) {
		return false;
	}
	*/
	
	return true;
}

/********************************************************/
/*******************   是否为合法的用户名   *************/
/*****用于客户（用户）的修改和查询，兼容四期以前版本*****/
/**********************************************************/
function isValidUserName(str){
	var SPECIAL_USERNAME_PREFIX = "~!#@$%^&_-.|";//同BD_SYS_CONFIG定义的USERNAME_PREFIX保持一致
	var SPECIAL_USERNAME_SUFFIX = "~!#@$%^&_-.|";//同BD_SYS_CONFIG定义的USERNAME_SUFFIX保持一致
	var USERNAME_PREFIX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"+SPECIAL_USERNAME_PREFIX;
	var USERNAME_SUFFIX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"+SPECIAL_USERNAME_SUFFIX;
	str = trim(str);
	if (str == "") {
		alert("客户名不能为空.");
		return false;
	}
	if (str.length > MAX_USERNAME_LENGTH) {
		alert("输入的客户名长度太大.");
		return false;
	}
	if ( USERNAME_PREFIX.indexOf( str.charAt(0) ) == -1) {
		alert("客户名不能以非法字符("+str.charAt(0)+")开头.");
		return false;
	}
	for(var i=1;i<str.length;i++){
		if ( USERNAME_SUFFIX.indexOf( str.charAt(i) ) == -1) {
			alert("输入的客户名含有非法字符("+str.charAt(i)+").");
			return false;
		}
	}
	/*
	if ( (/^[A-Za-z0-9~!#$%^&_-|]+$/g).test(str) == false ) {
		return false;
	}
	*/
	
	return true;
}

/**********************************/
/******   是否为合法的名字   ******/
/***** added by lijq 20020911  *****/
/**********************************/
function isValidName(str,name){
	var SPECIAL_USERNAME_PREFIX = "~!#$%^&_-|";//同BD_SYS_CONFIG定义的USERNAME_PREFIX保持一致
	var SPECIAL_USERNAME_SUFFIX = "~!#$%^&_-|";//同BD_SYS_CONFIG定义的USERNAME_SUFFIX保持一致
	var USERNAME_PREFIX = "0123456789abcdefghijklmnopqrstuvwxyz"+SPECIAL_USERNAME_PREFIX;
	var USERNAME_SUFFIX = "0123456789abcdefghijklmnopqrstuvwxyz"+SPECIAL_USERNAME_SUFFIX;
	str = trim(str);
	if (str == "") {
		alert(name+"不能为空.");
		return false;
	}
	if (str.length > MAX_USERNAME_LENGTH) {
		alert("输入的"+name+"长度太大.");
		return false;
	}
	if ( USERNAME_PREFIX.indexOf( str.charAt(0) ) == -1) {
		alert(name+"不能以非法字符("+str.charAt(0)+")开头.");
		return false;
	}
	for(var i=1;i<str.length;i++){
		if ( USERNAME_SUFFIX.indexOf( str.charAt(i) ) == -1) {
			alert("输入的"+name+"含有非法字符("+str.charAt(i)+").");
			return false;
		}
	}
	/*
	if ( (/^[A-Za-z0-9~!#$%^&_-|]+$/g).test(str) == false ) {
		return false;
	}
	*/
	
	return true;
}

//是否为float，使用javascript提供的函数isNaN() 包括整数，小数
//返回值：true 不是数字
//        false 数字


/**********************************/
/*****  是否为单个数字符(0~9) *****/
/**********************************/
function isNumber(str){
    if ( (/^\d$/g).test(str) == true )  
        return true;
    return false;
}

/**********************************/
/*****      是否为整型        *****/
/**********************************/
function isInteger(str){
	if (/^\d+$/.test(str) == true) {
		return true;
	}
	return false;	
}


/**********************************/
/*****      是否为有效的密码  *****/
/**********************************/
/*
function isValidPassword(str){
	if (/^[\x00-\xff]+$/.test(str))
		if (str.length >= 4)//added by lijq 20020711
			return true;
	return false;
}
*/

function isValidPassword(str){
	if (!(/^[\x00-\xff]+$/.test(str))) {
		alert("密码含有非法字符！");
		return false;
	}
	if (str.length < 4) {
		alert("请输入四位以上密码！");
		return false;
	}
	if (str.length > 8) {
                alert("请输入八位（含八位）以下密码！");
                return false;
        }
	if (str.indexOf(" ") >= 0) {
		alert("密码不能含有空格！");
		return false;
	}
	
	var i = 0;
        var strNumber = "0123456789";
        var strLetter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        var hasNumber = false;
        var hasLetter = false;
	var equ_cnt  = 1;
	var decr_cnt = 1;
	var incr_cnt = 1;
	var temp  = str.charAt(0);//相等
	var decr_str = str.charCodeAt(0);//递减
	var incr_str = str.charCodeAt(0);//递增
	var length = str.length;

        if (strNumber.indexOf(str.charAt(0)) >= 0) {
                hasNumber = true;
        }
        if (strLetter.indexOf(str.charAt(0)) >= 0) {
                hasLetter = true;
        }
	for (i = 1; i < length; i++) {
                if (strNumber.indexOf(str.charAt(i)) >= 0) {
                        hasNumber = true;
                }
                if (strLetter.indexOf(str.charAt(i)) >= 0) {
                        hasLetter = true;
                }
		if (temp == str.charAt(i)) {
			equ_cnt++;
		}
		if (decr_str == (str.charCodeAt(i)-1)){
			decr_str = str.charCodeAt(i);
			decr_cnt++;
		}
		if (incr_str == (str.charCodeAt(i)+1)) {
			incr_str = str.charCodeAt(i);
			incr_cnt++;
		}
	}

        if (!hasNumber) {
                alert("密码请至少输入一个数字!");
                return false;
        }
        if (!hasLetter) {
                alert("密码请至少输入一个字母！");
                return false;
        }
	if (equ_cnt == length || decr_cnt == length || incr_cnt == length) {
		alert("输入的密码太简单，请重新输入！");
		return false;
	}
	return true;
}

/*****************************************/
/******    是否为有效的密码         ******/
/******    需要判断是否和用户名相同 ******/
/******	   或者是用户名的倒叙       ******/
/*********Lijq added 20031204****************/
/*****************************************/

function isValidUserPassword(password, username){
	
	if (!isValidPassword(password)) {
		return false;
	}
	
	if (username == null) {
		username = "";
	} else {
		username = trim(username);
	}
	var pwd_length = password.length;
	var name_length = username.length;
	
	var reversal_cnt = 0;
	if (pwd_length == name_length) {
		if (password.toLowerCase() == username.toLowerCase()) {
			alert("密码不能和名字相同！");
			return false;
		}
		for (var i = 0; i < pwd_length; i++) {
			if (password.charAt(i).toLowerCase() == username.charAt(name_length-1-i).toLowerCase()) {
				reversal_cnt++;
			} else {
				break;
			}
		}
	}
	if (reversal_cnt == pwd_length) {
		alert("密码不能是名字的倒序，请重新输入！");
		return false;
	}

	return true;
}

/**********************************/
/*****是否为指定长度的电话号码*****/
/**********************************/
function isPhoneNumber(str,n){
	if(!isInteger(str))
		return false;
	if (str.length != n)
		return false;
	//added by lijq 20020722 begin
	if (n == 7 || n == 8) {
		if (str.indexOf("0")==0 
			|| str.indexOf("1")==0 
			|| str.indexOf("9")==0) {
				return false;
		}
	}
	//added by lijq 20020722 end
	return true;
}

/**********************************/
/*****   是否为7位电话号码    *****/
/**********************************/
function is7PhoneNumber(str){
	return isPhoneNumber(str,7);
}

/**********************************/
/*****   是否为8位电话号码    *****/
/**********************************/
function is8PhoneNumber(str){
	return isPhoneNumber(str,8);
}

/**********************************/
/*****是否为正确的移动电话号码*****/
/**********************************/
function isValidGsmNumber(str){
	if(!isInteger(str))
		return false;
	if (str.length != 11)
		return false;
	if(str.indexOf("13") != 0)
		return false;
	return true;
}

/**********************************/
/******   是否为金额型数值   ******/
/**********************************/
function isMoney(s){
	if (trim(s) == "") return false;
     if (isNaN(s)){
     	return false;
     }
     if (s<0||s>200000) return false;
     split=s.split(".");
     if(split.length==2){
         if(split[1]>99){ 
             return false;
         }
     }
     return true;
}

/**********************************/
/******   是否为银行账号   ********/
/******Lijq added 20020711*********/
/**********************************/
function isBankNO(str){
	var SPECIAL_STR = "- ";
	var BANKNO_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"+SPECIAL_STR;
	str = trim(str);
	if (str == "") return false;
	for(var i=0; i<str.length; i++){
		if ( BANKNO_STR.indexOf( str.charAt(i) ) == -1) 
			return false;
	}
	return true;
}


/**********************************/
/*****   是否为有效的Email    *****/
/**********************************/
function isValidEmail(str){
	str = trim(str);
	len = str.length;
	//没有@或者@在首尾
	if((str.indexOf('@') == -1)||(str.indexOf('@') == 0)||(str.indexOf('@') == len-1))
    		return false; 
	if(str.indexOf('@') != str.lastIndexOf('@'))   
    		return false;
	if(str.indexOf('.') == -1)
    		return false;
    	if (str.lastIndexOf('.') == (len-1) )//'.'在最后一位
    		return false;
    	for(var j=0;j<len-1;j++){//判断是否有".."或者"@."
    		if( (str.charAt(j) == '.' && str.charAt(j+1) == '.') ||( str.charAt(j) == '@' && str.charAt(j+1) == '.' ) )
    			return false;
    	}
	
   	validstr = "1234567890abcdefghijklmnopqrstuvwxyz_-.~@";
   	lowerstr = str.toLowerCase();
   	for(i=0;i<len;i++){
       		if(validstr.indexOf(lowerstr.charAt(i)) == -1){
           		return false;
           	}
       	}          
 
  	return true;
}


/**********************************/
/*****     是否为特殊字符     *****/
/**********************************/
function isNotChar(s){
    if ((s>='0'&& s<='9')||(s>='a' &&s<='z')||(s>='A'&& s<='Z'))return false;
    else return true;
}

/**********************************/
/*****     是否含有特殊字符   *****/
/**********************************/
function hasSpecialChar(str){
	var SPECIAL_STR = "~!@%^&*();'\"?><[]{}\\|,:/=+—“”‘";
    	for(i=0;i<str.length;i++){
    		if (SPECIAL_STR.indexOf(str.charAt(i)) >= 0)
    			return true;
    	}
    	return false;
}


/**********************************/
/*****  是否有效的管理员密码  *****/
/**********************************/
function isValidPasswd(s){
    //至少８位，不长于30位，不与帐号同名，至少有一位是数字至少有一个特殊字符。
    if((s.length<8)||(s.length>30)){
        alert("请输入八位以上密码");
        return false;
    }
   var  nochar=0;
    for(i=0;i<8;i++){
       
        if(isNotChar(s.charAt(i))){
            nochar++;
        }
    }
    if(nochar==0){
        alert("你的管理员密码的前八位中至少应有一个特殊字符");
        return false;
    }
        
    return true;
    
}


/**********************************/
/*****    检查IP地址的合法性  *****/
/**********************************/
function isValidIP(str){
	str = trim(str);
	temp = str.split('.');
   	if(temp.length != 4){
      		return false;
   	}
   	else{
      		for(i=0;i<temp.length;i++){
         		if((!isInteger(temp[i]))||(temp[i]<0)||(temp[i]>255)){
	    			return false;
	 		}
      		}
   	}
   return true;    
}

/**********************************/
/*****    检查卡号的合法性  *****/
/**********************************/
//var MIN_CARDLENGTH = 9;//最小的卡长度
var MIN_CARDLENGTH = 2;//最小的卡长度
var AREA_LENGTH = 2;//属地长度
var NUMBER_LENGTH = 6;//卡顺序号长度
function isValidCard(str){
	if (hasSpecialChar(str)) return false;//added by lijq 20020801
	if (str == "") return false;
	if (str.length < MIN_CARDLENGTH) return false;
	//var suffix = str.substring(str.length-AREA_LENGTH-NUMBER_LENGTH);
	//for(var i=0;i<suffix.length;i++){
	//	if (!isNumber(suffix.charAt(i)))
	//		return false;
	//}
	return true;
}


/**********************************/
/*****    检查身份证的合法性  *****/
/**********************************/
function isValidIDCard(s) {
	s = trim(s);
    	if(s.length==15){
        	for(i=0;i<s.length;i++)
              		if(!(isNumber(s.charAt(i)))){
                		return false;
            		}        
        	return true;
    	}
    	else if(s.length==18){
        	for(i=0;i<s.length-1;i++)
            		if(!(isNumber(s.charAt(i)))){
                		return false;
            		}
        return true;
            
    	}
   	return false
}


/**********************************/
/*****    检查目录的合法性    *****/
/**********************************/
function isValidDir(str)
{
    len = str.length;
    if((str.charAt(0) != '/') || (str.charAt(len - 1) == '/')) return false;
    if (str.indexOf('//') != -1) return false;
    return true;
}


/**********************************/
/*****     检查日期合法性     *****/
/**********************************/
function isValidBirthday(year,month,day){
    if(year.length!=4) {
        alert("请输入四位年份如1988");
        return false;
    }
    validstr="0123456789";
    for(i=0;i<4;i++){
        if(validstr.indexOf(year.charAt(i))==-1){
            alert("生日输入有误!");            
            return false;
        }
    }
    //从1900年到当前年
    var today = new Date();
    var currentYear=today.getYear() ;//年
    
    if(year*1>currentYear || year*1<1900){
        alert("年份输入有误!");
        return false;
    }
    if(month=="04"||month=="06"||month=="09"||month=="11") {
          if(day=="31") {
              alert("本月没有31日!");
              return false;
          }
      }
     if(month=="02") {
          if(year%4==0&&year%100!=0||year%400==0) {   /*闰年 */
              if(day=="30"||day=="31") {
                  alert("本月没有"+day+"日!");
                  return false;
              }
          }
          else {      /*不是闰年*/
              if(day=="29"||day=="30"||day=="31") {
                  alert("本月没有"+day+"日!");
                  return false;
              }
          }
      }
    
    return true;
}


/**********************************/
/*****        捕捉回车        *****/
/**********************************/
function keyDown(){
        var keycode=window.event.keyCode;
        if(keycode==13){
        	//do sth like check();
        }
}
//指定回车事件document.onkeydown=keyDown;

//以下为日期的javascript


 //place these scripts within BODY tag if you are using IE 4.0 or below.
//****************************************************************************
// PopCalendar 3.50, Emailware(please mail&commend me if u like it)
// Originally coded by Liming(Victor) Weng, email: victorwon@netease.com
// Release date: 2000.3.13
// Anyone may modify it to satify his needs, but please leave this comment ahead.
//****************************************************************************

var gdCtrl = new Object();
var goSelectTag = new Array();
var gcGray = "#808080";
var gcToggle = "#ffff00";
var gcBG = "#cccccc";

var gdCurDate = new Date();
var giYear = gdCurDate.getFullYear();
var giMonth = gdCurDate.getMonth()+1;
var giDay = gdCurDate.getDate();

//****************************************************************************
// Param: popCtrl is the widget beyond which you want this calendar to appear;
//        dateCtrl is the widget into which you want to put the selected date.
// i.e.: <input type="text" name="dc" style="text-align:center" readonly><INPUT type="button" value="V" onclick="fPopCalendar(dc,dc);return false">
//****************************************************************************
function fPopCalendar(popCtrl, dateCtrl){
  event.cancelBubble=true;
  gdCtrl = dateCtrl;
  var dateValue = popCtrl.value;
  if (dateValue != "") {
  	arrDate = dateValue.split("-");
  	var selYear = arrDate[0];
  	var selMonth = arrDate[1]*1;
  	fSetYearMon(selYear,selMonth);
  }else
  	fSetYearMon(giYear, giMonth);
  var point = fGetXY(popCtrl);
  with (VicPopCal.style) {
  	left = point.x;
	top  = point.y+popCtrl.offsetHeight+1;
	width = VicPopCal.offsetWidth;
	height = VicPopCal.offsetHeight;
	fToggleTags(point);
	visibility = 'visible';
  }
  VicPopCal.focus();
}

function fSetDate(iYear, iMonth, iDay){
  gdCtrl.value = iYear+"-"+iMonth+"-"+iDay; //Here, you could modify the locale as you need !!!!
  fHideCalendar();
}

function fHideCalendar(){
  VicPopCal.style.visibility = "hidden";
  for (i in goSelectTag)
  	goSelectTag[i].style.visibility = "visible";
  goSelectTag.length = 0;
}

function fSetSelected(aCell){
  var iOffset = 0;
  var iYear = parseInt(tbSelYear.value);
  var iMonth = parseInt(tbSelMonth.value);

  aCell.bgColor = gcBG;
  with (aCell.children["cellText"]){
  	var iDay = parseInt(innerText);
  	if (color==gcGray)
		iOffset = (Victor<10)?-1:1;
	iMonth += iOffset;
	if (iMonth<1) {
		iYear--;
		iMonth = 12;
	}else if (iMonth>12){
		iYear++;
		iMonth = 1;
	}
  }
  fSetDate(iYear, iMonth, iDay);
}

function Point(iX, iY){
	this.x = iX;
	this.y = iY;
}

function fBuildCal(iYear, iMonth) {
  var aMonth=new Array();
  for(i=1;i<7;i++)
  	aMonth[i]=new Array(i);

  var dCalDate=new Date(iYear, iMonth-1, 1);
  var iDayOfFirst=dCalDate.getDay();
  var iDaysInMonth=new Date(iYear, iMonth, 0).getDate();
  var iOffsetLast=new Date(iYear, iMonth-1, 0).getDate()-iDayOfFirst+1;
  var iDate = 1;
  var iNext = 1;

  for (d = 0; d < 7; d++)
	aMonth[1][d] = (d<iDayOfFirst)?-(iOffsetLast+d):iDate++;
  for (w = 2; w < 7; w++)
  	for (d = 0; d < 7; d++)
		aMonth[w][d] = (iDate<=iDaysInMonth)?iDate++:-(iNext++);
  return aMonth;
}

function fDrawCal(iYear, iMonth, iCellHeight, iDateTextSize) {
  var WeekDay = new Array("日","一","二","三","四","五","六");
  var styleTD = " bgcolor='"+gcBG+"' bordercolor='"+gcBG+"' valign='middle' align='center' height='"+iCellHeight+"' style='font:bold "+iDateTextSize+" 宋体;";            //Coded by Liming Weng(Victor Won) email:victorwon@netease.com

  with (document) {
	write("<tr>");
	for(i=0; i<7; i++)
		write("<td "+styleTD+"color:#990099' >" + WeekDay[i] + "</td>");
	write("</tr>");

  	for (w = 1; w < 7; w++) {
		write("<tr>");
		for (d = 0; d < 7; d++) {
			write("<td id=calCell "+styleTD+"cursor:hand;' onMouseOver='this.bgColor=gcToggle' onMouseOut='this.bgColor=gcBG' onclick='fSetSelected(this)'>");
			write("<font id=cellText Victor='Liming Weng'> </font>");
			write("</td>")
		}
		write("</tr>");
	}
  }
}

function fUpdateCal(iYear, iMonth) {
  myMonth = fBuildCal(iYear, iMonth);
  var i = 0;
  for (w = 0; w < 6; w++)
	for (d = 0; d < 7; d++)
		with (cellText[(7*w)+d]) {
			Victor = i++;
			if (myMonth[w+1][d]<0) {
				color = gcGray;
				innerText = -myMonth[w+1][d];
			}else{
				color = ((d==0)||(d==6))?"red":"black";
				innerText = myMonth[w+1][d];
			}
		}
}

function fSetYearMon(iYear, iMon){
  tbSelMonth.options[iMon-1].selected = true;
  for (i = 0; i < tbSelYear.length; i++)
	if (tbSelYear.options[i].value == iYear)
		tbSelYear.options[i].selected = true;
  fUpdateCal(iYear, iMon);
}

function fPrevMonth(){
  var iMon = tbSelMonth.value;
  var iYear = tbSelYear.value;

  if (--iMon<1) {
	  iMon = 12;
	  iYear--;
  }

  fSetYearMon(iYear, iMon);
}

function fNextMonth(){
  var iMon = tbSelMonth.value;
  var iYear = tbSelYear.value;

  if (++iMon>12) {
	  iMon = 1;
	  iYear++;
  }

  fSetYearMon(iYear, iMon);
}

function fToggleTags(){
  with (document.all.tags("SELECT")){
 	for (i=0; i<length; i++)
 		if ((item(i).Victor!="Won")&&fTagInBound(item(i))){
 			item(i).style.visibility = "hidden";
 			goSelectTag[goSelectTag.length] = item(i);
 		}
  }
}

function fTagInBound(aTag){
  with (VicPopCal.style){
  	var l = parseInt(left);
  	var t = parseInt(top);
  	var r = l+parseInt(width);
  	var b = t+parseInt(height);
	var ptLT = fGetXY(aTag);
	return !((ptLT.x>r)||(ptLT.x+aTag.offsetWidth<l)||(ptLT.y>b)||(ptLT.y+aTag.offsetHeight<t));
  }
}

function fGetXY(aTag){
  var oTmp = aTag;
  var pt = new Point(0,0);
  do {
  	pt.x += oTmp.offsetLeft;
  	pt.y += oTmp.offsetTop;
  	oTmp = oTmp.offsetParent;
  } while(oTmp.tagName!="BODY");
  return pt;
}

var gMonths = new Array("&nbsp;一月","&nbsp;二月","&nbsp;三月","&nbsp;四月","&nbsp;五月","&nbsp;六月","&nbsp;七月","&nbsp;八月","&nbsp;九月","&nbsp;十月","十一月","十二月");

with (document) {
write("<Div id='VicPopCal' onclick='event.cancelBubble=true' style='POSITION:absolute;visibility:hidden;border:2px ridge;width:10;z-index:100;'>");
write("<table border='0' bgcolor='#6699cc'>");
write("<TR>");
write("<td valign='middle' align='center'><input type='button' name='PrevMonth' value='<' style='height:20;width:20;FONT:bold' onClick='fPrevMonth()'>");
write("&nbsp;<SELECT name='tbSelYear' onChange='fUpdateCal(tbSelYear.value, tbSelMonth.value)' Victor='Won'>");
for(i=1910;i<2030;i++)
	write("<OPTION value='"+i+"'>"+i+"年</OPTION>");
write("</SELECT>");
write("&nbsp;<select name='tbSelMonth' onChange='fUpdateCal(tbSelYear.value, tbSelMonth.value)' Victor='Won'>");
for (i=0; i<12; i++)
	write("<option value='"+(i+1)+"'>"+gMonths[i]+"</option>");
write("</SELECT>");
write("&nbsp;<input type='button' name='PrevMonth' value='>' style='height:20;width:20;FONT:bold' onclick='fNextMonth()'>");
write("</td>");
write("</TR><TR>");
write("<td align='center'>");
write("<DIV style='background-color:teal'><table width='100%' border='0' cellpadding='2'>");
fDrawCal(giYear, giMonth, 12, 12);
write("</table></DIV>");
write("</td>");
write("</TR><TR><TD align='center'>");
write("<B style='cursor:hand; font: bold 14 宋体' onclick='fSetDate(giYear,giMonth,giDay)' onMouseOver='this.style.color=gcToggle' onMouseOut='this.style.color=0'>今天："+giYear+"年"+giMonth+"月"+giDay+"日</B>");
write("</TD></TR>");
write("</TABLE></Div>");
write("<SCRIPT event=onclick() for=document>fHideCalendar()</SCRIPT>");
}

// End -- Coded by Liming Weng, email: victorwon@netease.com -->




function arrowtag(namestr,valuestr,size){
        istr=unescape('%u25bc');          
   document.write("<input type='text' name='"+namestr+"' readonly value='"+valuestr+"' size='"+size+"' style='text-align: center'><input style='font-size:8pt;font-family: 宋体;' type='button' value='"+istr+"'onclick='fPopCalendar("+namestr+","+namestr+");return false'>");
  }


/**********************************/
/***是否为正确的日期(yyyy-mm-dd)***/
/**********************************/

function isValidDate(datestr){
	var dateArr = datestr.split("-");
	if (dateArr.length != 3 ) return false;
	for (i=0;i<3;i++){
		if (!isInteger(dateArr[i]))
			return false;
	}
	return true;
}


/**********************************/
/***   日期date1是否大于date2   ***/
/**********************************/
function dateBDate(date1,date2){
//	if (!isValidDate(date1) || !isValidDate(date2)) 
	//	return false;
	var date1Arr = date1.split("-");
	var date2Arr = date2.split("-");
	
	var year1 = parseInt(date1Arr[0]);
	var month1 = parseInt(date1Arr[1]*1);
	var date1 = parseInt(date1Arr[2]*1);
	
	var year2 = parseInt(date2Arr[0]);
	var month2 = parseInt(date2Arr[1]*1);
	var date2 = parseInt(date2Arr[2]*1);
	
	if (year1 < year2) return false;
	if (year1 == year2){//年相等，判断月
		if(month1 < month2) return false;
		if(month1 == month2){//月相等，判断日
			if (date1 <= date2) return false;
		}
	}
	return true;
}

/**********************************/
/***   日期date1是否等于date2   ***/
/*****added by Lijq 2002-5-18******/
/**********************************/
function dateEQUDate(date1,date2){
	if (!isValidDate(date1) || !isValidDate(date2)) 
		return false;
	var date1Arr = date1.split("-");
	var date2Arr = date2.split("-");
	
	var year1 = parseInt(date1Arr[0]);
	var month1 = parseInt(date1Arr[1]*1);
	var date1 = parseInt(date1Arr[2]*1);
	
	var year2 = parseInt(date2Arr[0]);
	var month2 = parseInt(date2Arr[1]*1);
	var date2 = parseInt(date2Arr[2]*1);
	
	if ((year1 != year2)||(month1 != month2)||(date1 != date2)) {
		 return false;
	}
	return true;
}

/**********************************/
/*日期datestr是否大于等于当前日期**/
/**********************************/

function dateBECurrent(datestr){
	if (!isValidDate(datestr)) return false;
	var currentDate = new Date();
	var cYear = currentDate.getYear();
	var cMonth = currentDate.getMonth()+1;
	var cDate = currentDate.getDate();
	var dateArr = datestr.split("-");
	var year = parseInt(dateArr[0]);
	var month = parseInt(dateArr[1]*1);
	var date = parseInt(dateArr[2]*1);
	if (year < cYear) return false;
	if (year == cYear){//年相等，判断月
		if(month < cMonth) return false;
		if(month == cMonth){//月相等，判断日
			if (date < cDate) return false;
		}
	}
	return true;
}

/**********************************/
/*日期datestr是否大于当前日期**/
/** add by humh **/
/**********************************/

function dateBCurrent(datestr){
	if (!isValidDate(datestr)) return false;
	var currentDate = new Date();
	var cYear = currentDate.getYear();
	var cMonth = currentDate.getMonth()+1;
	var cDate = currentDate.getDate();
	var dateArr = datestr.split("-");
	var year = parseInt(dateArr[0]);
	var month = parseInt(dateArr[1]*1);
	var date = parseInt(dateArr[2]*1);
	if (year < cYear) return false;
	if (year == cYear){//年相等，判断月
		if(month < cMonth) return false;
		if(month == cMonth){//月相等，判断日
			if (date <= cDate) return false;
		}
	}
	return true;
}

/**********************************/
/*开始日期、结束日期的合法性检查**/
/** add by hu_minghui **/
/**********************************/

function isDate(datestr1,datestr2){
	if (!isValidDate(datestr1) || !isValidDate(datestr2)) 
		return false;
	
	
	if (dateBCurrent(datestr1)){
		alert("开始日期不得大于当前日期！");
		return false;
        }
        if (dateBCurrent(datestr2)){
		alert("结束日期不得大于当前日期！");
		return false;
        }
        /*
        if(dateBDate(datestr2,datestr1)){        
		alert("结束日期应大于开始日期!");
		return false;
        } 
        */

        var date1Arr = datestr1.split("-");
	var year1    = parseInt(date1Arr[0]);
	var month1   = parseInt(date1Arr[1]);
	var Date1    = parseInt(date1Arr[2]);
	
	var date2Arr = datestr2.split("-");
	var year2    = parseInt(date2Arr[0]);
	var month2   = parseInt(date2Arr[1]);
	var Date2    = parseInt(date2Arr[2]);
	
	if (year2 < year1) {
		  alert("开始日期不能大于结束日期，请重新输入");
                  return false;
        }
	
	if (year2 > year1){//跨年份
		if( year2 - year1 > 1 ){
                        alert("清单查询不能超过1个月，请重新输入");
                        return false;
                }
                else if( year2 - year1 == 1){
                        if( (month1==12)&&(month2==1) ){
                               if( Date2 > Date1 ){                   
                               	   alert("清单查询不能超过1个月，请重新输入");
                               	   return false;
                               }
                        }
                        else {
                               alert("清单查询不能超过1个月，请重新输入");
                               return false;
                        }	
                }	
	}
	else if (year2 == year1){//年相等，判断月
		
                if(month2 < month1){ //年份相等，终止月份小于起始月份
                	 alert("开始日期不能大于结束日期，请重新输入");
                	 return false;
                } 	 
		if(month2 == month1){//月相等，判断日
			if (Date2 < Date1){ 
			 alert("开始日期不能大于结束日期，请重新输入");
                	 return false;
                	}
		}
	
		else{	
                        if((month2 - month1 == 1)&&(Date2 > Date1)){//判断日
                               alert("清单查询不能超过1个月，请重新输入");
                               return false;
                        }	
		}
		if( month2 - month1 > 1){
		    	alert("清单查询不能超过1个月，请重新输入");
                    	return false;
                }
	}
	return true;
}


/*
	 统一显示页面的输出信息如：(错误、警告、确认、提示)
	2002-06-30 :gudg
*/
/*
type: 信息类型
	   error---错误
	   alert---警告
	   info ---提示
	   confirm---确认
title: 信息标题栏
	   标题栏内容缺省为系统指定的信息
	   错误为：错误信息
	   警告为：警告信息
	   提示为：提示信息
	   确认为：确认信息
content: 信息内容
	   信息的内容主体
buttonflag: 是否显示缺省的返回按钮
	   false:不显示
	   其他:显示	   
	   缺省参数为显示
*/
function OutPut(type,content,title,buttonflag){
	/*分析参数*/
	if (arguments.length < 4){
		document.writeln("<center><h3>JavaScript方法<font color=blue>[OutPut]</font>调用错误：没有给全参数</h3></center>");
		return;
	}
	if(!((type=="error")||(type=="alert")||(type=="info")||(type=="confirm"))){
		document.writeln("<center><h3>JavaScript方法<font color=blue>[OutPut]</font>调用错误：无效的类型</h3></center>");
		return;
	}
	if(content.length==0){
		document.writeln("<center><h3>JavaScript方法<font color=blue>[OutPut]</font>调用错误：内容为空</h3></center>");
		return;
	}
	document.writeln("<center>");
	document.writeln("<table cellpadding=0 cellspacing=0 width=48% bgcolor=#777777 align=center>");
	document.writeln("<tr><td><table cellpadding=2 cellspacing=1 width=100%>");
	document.writeln("<tr><td bgcolor=\"#6699cc\" width=100% align=center valign=middle>");
	if(type=="error"){		
			document.writeln("<font color=red><b><h4>");
			if(title.length==0)
				document.writeln("错误信息：");
			else
				document.writeln(title);
			document.writeln("</h4></b></font>");

	}
	if(type=="alert"){
		document.writeln("<font color=yellow><b><h4>");
		if(title.length==0)
			document.writeln("警告信息：");
		else
			document.writeln(title);
		document.writeln("</h4></b></font>");
	}
	if(type=="info"){
		document.writeln("<font color=green><b><h4>");
		if(title.length==0)
			document.writeln("提示信息：");
		else
			document.writeln(title);
		document.writeln("</h4></b></font>");		
	}
	if(type=="confirm"){
		document.writeln("<font color=blue><b><h4>");
		if(title.length==0)
			document.writeln("确认信息：");
		else
			document.writeln(title);
		document.writeln("</h4></b></font>");		
	}

	document.writeln("<td bgcolor=#3A6EA5 width=8% align=center>");
	/*显示相应的图标*/
	if(type=="error"){
		document.writeln("<img src=\"../images/error.gif\" ");
	}
	if(type=="alert"){
		document.writeln("<img src=\"../images/alert.gif\" ");
	}
	if(type=="info"){
		document.writeln("<img src=\"../images/message.gif\" ");
	}
	if(type=="confirm"){
		document.writeln("<img src=\"../images/ask.gif\" ");
	}
	
	document.writeln("border=0 width=36 height=36></td>");
	
	document.writeln("<tr><td colspan=2 bgcolor=#DFE7DF  align=center>");
	document.writeln("<br><font face=\"楷体_GB2312\" size=\"4\" color=\"#000000\">");
	document.writeln(content);
	document.writeln("</font><p>");
	if(buttonflag=="false"){}
	else{
		document.writeln("<input type=\"Button\" value=\"  返  回  \" onclick=\"history.back()\" class=\"s03\">");	
	}
	document.writeln("</td></tr></table></td></tr></table></center>");
}

/*
根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
    地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。
    出生日期码表示编码对象出生的年、月、日，其中年份用四位数字表示，年、月、日之间不用分隔符。
    顺序码表示同一地址码所标识的区域范围内，对同年、月、日出生的人员编定的顺序号。顺序码的奇数分给男性，偶数分给女性。
    校验码是根据前面十七位数字码，按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。

出生日期计算方法。
    15位的身份证编码首先把出生年扩展为4位，简单的就是增加一个19或18,这样就包含了所有1800-1999年出生的人;
    2000年后出生的肯定都是18位的了没有这个烦恼，至于1800年前出生的,那啥那时应该还没身份证号这个东东，⊙﹏⊙b汗...
下面是正则表达式:
 出生日期1800-2099  (18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])
 身份证正则表达式 /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i            
 15位校验规则 6位地址编码+6位出生日期+3位顺序号
 18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
 
 校验位规则     公式:∑(ai×Wi)(mod 11)……………………………………(1)
                公式(1)中： 
                i----表示号码字符从由至左包括校验码在内的位置序号； 
                ai----表示第i位置上的号码字符值； 
                Wi----示第i位置上的加权因子，其数值依据公式Wi=2^(n-1）(mod 11)计算得出。
                i 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
                Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1

*/
//身份证号合法性验证 
//支持15位和18位身份证号
//支持地址编码、出生日期、校验位验证
        function IdentityCodeValid(code) { 
            var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
            var tip = "";
            var pass= true;
            
            if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
                tip = "身份证号格式错误";
                pass = false;
            }
            
           else if(!city[code.substr(0,2)]){
                tip = "地址编码错误";
                pass = false;
            }
            else{
                //18位身份证需要验证最后一位校验位
                if(code.length == 18){
                    code = code.split('');
                    //∑(ai×Wi)(mod 11)
                    //加权因子
                    var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                    //校验位
                    var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                    var sum = 0;
                    var ai = 0;
                    var wi = 0;
                    for (var i = 0; i < 17; i++)
                    {
                        ai = code[i];
                        wi = factor[i];
                        sum += ai * wi;
                    }
                    var last = parity[sum % 11];
                    if(parity[sum % 11] != code[17]){
                        tip = "校验位错误";
                        pass =false;
                    }
                }
            }
            if(!pass) alert(tip);
            return pass;
        }
 
        
       
          
          function maleOrFemalByIdCard(idCard){   
        	    idCard = strtrim(idCard.replace(/ /g, ""));        // 对身份证号码做处理。包括字符间有空格。   
        	    if(idCard.length==15){   
        	        if(idCard.substring(14,15)%2==0){   
        	            return 'female';   
        	        }else{   
        	            return 'male';   
        	        }   
        	    }else if(idCard.length ==18){   
        	        if(idCard.substring(14,17)%2==0){   
        	            return 'female';   
        	        }else{   
        	            return 'male';   
        	        }   
        	    }else{   
        	        return null;   
        	    }   
        	}   
          
        //去掉字符串头尾空格   
        function strtrim(str) {   
            return str.replace(/(^\s*)|(\s*$)/g, "");   
        }