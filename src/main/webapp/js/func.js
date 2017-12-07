/**
 * 界面控制的javascript函数
 * @version 1.0
*/

//判断是哪一种浏览器
var isIE,isNetscape,isOtherExplorer;
if (navigator.appName.toLowerCase()=='microsoft internet explorer') {
    isIE=true;
} else if (navigator.appName.toLowerCase()=='netscape') {
    isNetscape=true;
} else {
    isOtherExplorer=true;
}

/**
 * 使页面中的input,select 和textarea在提交之后置灰
 */
 function diablePageAll(src)
{
	 inputs=document.getElementsByTagName("input");
     dlts=document.getElementsByTagName("select");
     tas =document.getElementsByTagName("textarea");
     var len = inputs.length;
     for(var i=0;i<len;i++)
        inputs[i].disabled=true;
    var len = dlts.length;
    for(var i=0;i<len;i++)
        dlts[i].disabled=true;
    var len = tas.length;
    for(var i=0;i<len;i++)
        tas[i].disabled=true;
    src.disabled =false;
}

/**
* 判断文件格式是否在所给的文件格式中
* @return true
* 
*/
function checkFileNameWithPostfix(filePath,postfix)
    {
        
        // 获得文件类型 
        var filePostfix=filePath.substring(filePath.lastIndexOf(".")+1);
        var isAllow =postfix.indexOf(filePostfix);
        
        if(isAllow==-1)
        {
            return false;
        }

        return true;
}

/**
* 去掉字符串的首尾空格或tab字符
* @return 去掉首尾空格的字符串
* 用法 " abc ".trim()  -- 返回值为"abc"
*/
String.prototype.trim = function()
{
  return this.replace(/(^\s+)|(\s+$)/g,"");
}

/**
 * 检查字符串是否以字符串s开头
 */
String.prototype.startsWith = function(s)
{
  return this.indexOf(s)==0;
}

/**
* 去掉字符串的尾空格或tab字符
* @return 去掉尾空格或tab字符的字符串
* 用法 " abc ".rtrim()  -- 返回值为"abc"
*/
String.prototype.rtrim = function()
{
  return this.replace(/(\s+$)/g,"");
}

/**
* 去掉字符串的首空格或tab字符
* @return 去掉首空格或tab字符的字符串
* 用法 " abc ".ltrim()  -- 返回值为"abc"
*/
String.prototype.ltrim = function()
{
  return this.replace(/(^\s+)/g,"");
}

//检验控件里是否有中文
function checkNoChinese(ctrl,ctrlName)
{
	var cValue = ctrl.value;
	for(var i=0;i<cValue.length;i++)
	{
       var isChinese;
    	if(cValue.charCodeAt(i)<0 || cValue.charCodeAt(i)>255)
    	{
			isChinese = false;
	     }
		else
    	{
			isChinese = true;
		}
	}
	if(!isChinese)
	{
		alert(ctrlName+"不能输入中文.");
	}
	else{}
	return isChinese;
}
//实现数字过滤但是不过滤回车。。
function numInputFilter(event){
  if(event.keyCode==13)
	{
  		//pwForm.submit();
  	}
else if (event.keyCode < 48 || event.keyCode > 57){
  event.returnValue = false;
}
else {
}
}
//只能是数字
function checkOnlyNum(ctrl,info)
{
	var value = ctrl.value;
    for(var i=0;i<value.length;i++)
    {
        if(value.charCodeAt(i)<48||value.charCodeAt(i)>57)
        {
        	alert(info+"只能是数字!");
            return false;
            break;
        }
    }
    return true;
}

function IsFloat(inputStr){
	var patrn = /^(-?\d+)(\.\d+)?$/;
	return patrn.exec(inputStr);
}

/**
* 判断是否是钱的形式,允许为负值
* @return true 或 false
* @param pObj 网页控件的代码名字
* @param errMsg 网页控件的显示名称  
*/
function isMoneyN(pObj,errMsg){
 var obj = eval(pObj);
 if(obj.value=='')
 {
 	alert(errMsg+"不能为空");
 	return false;
 }
 var patrn=/^((-){0,1})(\d)*(\.(\d){0,2})?$/;
 if(patrn.exec(obj.value)==null){
 
 	alert(errMsg+"必须是数值，可包括2位小数");
 	if(obj.type="text")
 		obj.focus();
 	return false;
 }

 return true;
}
/**
* 判断是否是钱的形式
* @return true 或 false
* @param pObj 网页控件的代码名字
* @param errMsg 网页控件的显示名称  
*/
function isMoney(pObj,errMsg){
 var obj = eval(pObj);
 if(obj.value=='')
 {
 	alert(errMsg+"不能为空");
 	return false;
 }
 var patrn=/^(\d)*(\.(\d){0,2})?$/;
 if(patrn.exec(obj.value)==null){
 
 	alert(errMsg+"必须是数值，可包括2位小数");
 	if(obj.type="text")
 		obj.focus();
 	return false;
 }

 return true;
}


/**
* 判断是否是钱的形式
* @return true 或 false
* @param pObj 网页控件的代码名字
* @param errMsg 网页控件的显示名称  
*/
function checkMoney(pObj,errMsg){
	
 var obj = eval(pObj);

 var patrn=/^(\d)*(\.(\d){0,2})?$/;
 if(patrn.exec(obj.value)==null){
 
 	alert(errMsg+"必须是数值，可包括2位小数");
 	if(obj.type="text")
 		obj.focus();
 	return false;
 }

 return true;
}

////////////实现长度自动截止，不用再次判定了。
function lenInputFilter(event,len){
    s = event.srcElement.value;
    //处理各种修饰键盘控制
    if ((event.keyCode <=40)||(event.keyCode >= 37)||
    		(event.keyCode ==13)||
            (event.keyCode ==8))
    	return true;
    if (byteLen(s)>len)
    	return false;
    else
    	return true;
}
/***
* 实现任何时候都是基于字节的长度的校验
* ********/
function  byteLen(s)
{
    return s.replace(/[^\x00-\xff]/g,"aa").length;
}

 function hasUC(string){
	return string.match(/^[\s\S]*[\u00FF-\uFFFF][\s\S]*$/g);
}
String.prototype.byteSubstring=function(maxLength){//要求不能把中文截断
  //var byteLength=this.byteLength();
	var s="";
	//alert(this.length);
	//alert('haha');
	for(var i=0,j=0;i<this.length && j<=maxLength;i++){
		if(hasUC(this.substring(i,i+1))){
			if(j>=maxLength-1){
	//			alert(s.length);
				return s;
			}else{
				j+=2;
				s+=this.substring(i,i+1);
			}
		}else{
			if(j>maxLength-1){
	//			alert(s.length);
				return s;
			}else{
				j+=1;
				s+=this.substring(i,i+1);
			}
		}
	}
}

    /**
     * 检测网页控件的value属性是否为空
	 * 例如：checkField(form1.address,'家庭地址')
     * @param ctrl 网页控件的代码名字
	 * @param info 网页控件的显示名称
     */
	function checkField(ctrl,info)
	{

		if(ctrl.value.trim()=='')
		{
			alert(info+"不能为空!");
			ctrl.focus();
			return false;
		}
		return true;
	}
    /**
     * 检测网页控件的value属性是否全部为空
	 * 例如：isBlank(form1.address)
     * @param ctrl 网页控件的代码名字
     */
	function isBlank(var1)
	{
	  var checkStr = var1.value.trim();
	  for (i = 0;  i < checkStr.length;  i++)
	  {
		ch = checkStr.charAt(i);
		  if (ch!=' '&&ch!='\t')
			return false;
	  }
	  return true;
	}
    /**
     * 检测网页控件的value属性是否包含空格
	 * 例如：checkSpace(form1.password,'密码')
     * @param ctrl 网页控件的代码名字
	 * @param info 网页控件的显示名称
     */
	function checkSpace(ctrl,info)
	{
		var str=ctrl.value.rtrim();
		for (var i = 0; i <str.length; i++)
		{
			if (str.charAt(i) == " ")
			{
				alert(info+"不能含有空格!");
				ctrl.focus();
				ctrl.select();
				return false;
			}
	   }
	   return true;
	}

    /**
     * 检测网页控件的value属性值是否为大于begin并小于end的数字
	 * 如要检查数字个数是否合适，请调用checkNum(ctrl,info,begin,end)
	 * 例如：checkNum(form1.qq,'qq',5,8)
     * @param ctrl 网页控件的代码名字
	 * @param info 网页控件的显示名称
     */
	function isProperNumber(ctrl,info,begin,end)
	{
		if(begin==null||begin=="")
			return false;
		if(end==null||end=="")
			return false;
		try{
			if(parseInt(ctrl.value)>=parseInt(begin) && parseInt(ctrl.value)<=parseInt(end)){
				return true;
			}else{
				alert(info+"只能输入"+begin+"到"+end+"的数字");
                //"Please input correct "+info+" (number with a value between "+begin+" and "+end+").");
				ctrl.focus();
				ctrl.select();
				return false;
			}
		}catch(e){
			return false;
		}
	}
	/**
     * 检测网页控件的value属性是否从begin--end的数字
	 * 例如：checkNum(form1.qq,'qq',5,9) 代表5-9位数字
	 * 例如：checkNum(form1.qq,'qq',5,'') 代表至少5位数字
	 * 如要检查数字大小是否合适，请调用isProperNumber(ctrl,info,begin,end)
     * @param ctrl 网页控件的代码名字
	 * @param info 网页控件的显示名称
     */
	function checkNumer(ctrl,info,begin,end)
	{
		var boo=end=='';
		if(begin==null||begin=="")
			begin="0";
		if(end==null||end=="")
			end="9";
		var exp=new RegExp("^[0-9]{"+begin+","+end+"}$");
		if(boo) exp=new RegExp("^[0-9]{"+begin+",}$");
		if(checkField(ctrl,info))
		{
			if(ctrl.value.trim().match(exp))
			{
				return true;
			}
			else
			{
				if(!boo){
					alert("Please input correct "+info+" ("+begin+"-"+end+" number"+").");
				}else{
					alert("Please input correct "+info+" ("+begin+" number"+"at least).");
				}
				ctrl.focus();
				ctrl.select();
				return false;
			}
	   }
	   return false;
	}
	/**
		检查是否以0开头
	 */
	function checkStartsWithZero(s,info){
		if(s.startsWith("0")){
			alert(info+' can not starts with 0.');
			return false;
		}else
			return true;
	}
    /**
     * 检测网页控件的value属性是否正确的身份证号码
	 * 例如：checkIDCard(form1.IDCard)
     * @param ctrl 网页控件的代码名字
     */
	function checkIDCard(ctrl)
	{
		var exp1=new RegExp("^[0-9]{15}$");
		var exp2=new RegExp("^[0-9]{18}$");
		var info="ID";
		if(checkField(ctrl,info))
		{
			if(ctrl.value.trim().match(exp1)||ctrl.value.trim().match(exp2))
			{
				return true;
			}
			else
			{
				alert("Please input correct"+info+"！");
				ctrl.focus();
				ctrl.select();
				return false;
			}
	   }
	   return false;
	}
    /**
     * 在字符串中用一指定的字符串替换查找到的所有子串
     * @param originalString 原字符串
	 * @param findText       查找的字符串
	 * @param replaceText    替换的字符串
     */
    function stringReplace(originalString, findText, replaceText) {
      var pos = 0;
      var len = findText.length;
      pos = originalString.indexOf(findText);
      while (pos != -1) {
        preString = originalString.substring(0, pos);
        postString = originalString.substring(pos + len,
          originalString.length);
        originalString = preString + replaceText + postString;
        pos = originalString.indexOf(findText);
      }
      return originalString;
    }

//--------------- 扩展函数 -----------------------


    /**
     * 检测网页控件的value属性是否为有效的电话号码
	 * 例如：checkPhone(form1.mobile)
     * @param ctrl 网页控件的代码名字
     */
	function checkPhone(ctrl)
	{
		var exp=new RegExp("^(010||02[0-9]||0[3-9][0-9]{2,3})[0-9]{7,8}$");
		var info="mobileNo";
		if(checkField(ctrl,info))
		{
			if(ctrl.value.trim().match(exp))
			{
					return true;
			}
			else
			{
				alert("Please input correct"+info+"！");
				ctrl.focus();
				ctrl.select();
				return false;
			}
		}
		return false;
	}

/**
 * 检测网页控件的value属性是否为有效的电话号码
 * 例如：checkPhone(value,info)
 * @param ctrl 网页控件的代码名字
 */
function checkMobile(ctrl, info)	{
    var exp = new RegExp("^13[0-9]{9}$");
    var value = ctrl.value ? ctrl.value :  ctrl;
    if(value.trim().match(exp))	{
        return true;
    } else {
        alert(info+"不是合法的手机号码!");
        return false;
    }
}

	//检查手机号段(必须为11位手机号)
	function checkMobileScope(fromNum,endNum){
		if(!checkField(fromNum,'号段起始号码')|| !checkMobile(fromNum,'号段起始号码') || ! checkField(endNum,'号段结束号码')  || !checkMobile(endNum,'号段结束号码'))	{
			return false;
		}
		if(parseInt(fromNum.value.trim()) > parseInt(endNum.value.trim())){
			alert('号段结束号码不能大于起始号码!');
			return false;
		}
		return true;

	}


    /**
     * 检测网页控件的value属性是否为有效的密码
	 * 例如：checkPassword(form1.password)
     * @param ctrl 网页控件的代码名字
     */
	function checkPassword(ctrl)
	{
		var info="password";
		return checkField(ctrl,info)&&checkSpace(ctrl,info);
	}

    /**
     * 检测网页控件的value属性:ctrl1和ctrl2是否为有效的"新密码"和"新密码确认"
	 * 例如：checkModifyPassword(form1.password,form1.passwordAfrim)
     * @param ctrl1 网页控件的代码名字
     * @param ctrl2 网页控件的代码名字
     */
	function checkModifyPassword(ctrl1,ctrl2)
	{
		if(checkField(ctrl1,"New password")&&checkSpace(ctrl1,"New password")&&checkField(ctrl2,"Password confirm")&&checkSpace(ctrl2,"Password confirm"))
		{
			if(ctrl1.value.trim()==ctrl2.value.trim())
				return true;
			else
			{
				alert("New password should match its confirm!");				ctrl1.focus();
				ctrl1.select();
				return false;
			}
		}
		return false;
		}

    /**
     * 对一个数字四舍五入并保留两位小数
	 * 例如：DigitalFormat('3.165398')
     * @param str 需要四舍五入的数字
	 *  alert(!isNaN(m));
     */
	function DigitalFormat(str) {
	  var decAmount = "";
	  var dolAmount = "";
	  var decFlag = false;
	  var aChar = "";

	  // ignore all but digits and decimal points.
	  for(i=0; i < str.length; i++) {
		aChar = str.substring(i,i+1);
		if(aChar >= "0" && aChar <= "9") {
		  if(decFlag) {
			decAmount = "" + decAmount + aChar;
		  }
		  else {
			dolAmount = "" + dolAmount + aChar;
		  }
		}
		if(aChar == ".") {
		  if(decFlag) {
			dolAmount = "";
			break;
		  }
		  decFlag=true;
		}
	  }

	  // Ensure that at least a zero appears for the dollar amount.
	  if(dolAmount == "") {
		dolAmount = "0";
	  }

	  // Strip leading zeros.
	  if(dolAmount.length > 1) {
		while(dolAmount.length > 1 && dolAmount.substring(0,1) == "0") {
		  dolAmount = dolAmount.substring(1,dolAmount.length);
		}
	  }

	  // Round the decimal amount.
	  if(decAmount.length > 2) {
		if(decAmount.substring(2,3) > "4") {
		  decAmount = parseInt(decAmount.substring(0,2)) + 1;
		  if(decAmount < 10) {
			decAmount = "0" + decAmount;
		  }
		  else {
			decAmount = "" + decAmount;
		  }
		}
		else {
		  decAmount = decAmount.substring(0,2);
		}

		if (decAmount == 100) {
		  decAmount = "00";
		  dolAmount = parseInt(dolAmount) + 1;
		}
	  }

	  // Pad right side of decAmount
	  if(decAmount.length == 1) {
		decAmount = decAmount + "0";
	  }

	  if(decAmount.length == 0) {
		decAmount = decAmount + "00";
	  }

	  // Check for negative values and reset textObj
	  if(str.substring(0,1) != '-' ||(dolAmount == "0" && decAmount == "00")) {
		return dolAmount + "." + decAmount;
	  }
	  else{
		return '-' + dolAmount + "." + decAmount;
	  }
	}

    /**
     * 设置select控件的缺省值
	 * 例如：setDefaultSelect(form1.selectGroup,'1')
     * @param ctrl 需要设置缺省值的控件
     * @param defaultValue 需要设置的缺省值
     */
	function setDefaultSelect(ctrl,defaultValue) {
		try {
			ctrl.value.trim()=defaultValue;
			ctrl.options(ctrl.selectedIndex).defaultSelected=true;
		}
		catch(e) {
		}
	}

    /**
     * 设置input控件的缺省值
	 * 例如：setDefaultInput(form1.mobile,'13500000001')
     * @param ctrl 需要设置缺省值的控件
     * @param defaultValue 需要设置的缺省值
     */
	function setDefaultInput(ctrl,defaultValue) {
		try {
			ctrl.value.trim()=defaultValue;
			ctrl.defaultValue=defaultValue;
		}
		catch(e) {
		}
	}

	/**
	 * 计算某年某月有多少天
	 * @param WhichMonth 月
	 * @param WhichYear  年
	 */
	function DaysInMonth(WhichMonth, WhichYear)
	{
	  var DaysInMonth = 31;
	  if (WhichMonth == "4" || WhichMonth == "6" || WhichMonth == "9" || WhichMonth == "11") DaysInMonth = 30;
	  if (WhichMonth == "2" && (WhichYear/4) != Math.floor(WhichYear/4))	DaysInMonth = 28;
	  if (WhichMonth == "2" && (WhichYear/4) == Math.floor(WhichYear/4))	DaysInMonth = 29;
	  return DaysInMonth;
	}


	/**
	 * 取得本月月初的日期
	 */
	function getDateFromMonthBegin()
	{
		var now=new Date();
		return now.getFullYear()+"-"+(parseInt(now.getMonth()+1)<10?"0"+parseInt(now.getMonth()+1):parseInt(now.getMonth()+1))+"-"+"01";
	}

	/**
	 * 取得本月月末的日期
	 */
	function getDateFromMonthEnd()
	{
		var now=new Date();
		return now.getFullYear()+"-"+(parseInt(now.getMonth()+1)<10?"0"+parseInt(now.getMonth()+1):parseInt(now.getMonth()+1))+"-"+DaysInMonth(now.getMonth()+1,now.getFullYear());
	}

    /**
     * 计算短信的剩余字数
	 * 该函数用在发短信控件的onKeyDown和onKeyUp，及body的onLoad事件里调用
	 * 例如：textCounter(form1.sendText,form1.countText)
     * @param ctrl 发短信的控件
     * @param countCtrl 显示剩余字数的控件
     */
	function textCounter(ctrl, countCtrl)
	{
		var maxLimit=160;//能够输入的最大长度
		var ctrlLen=ctrl.value.trim().replace(/[^\x00-\xff]/g,"**").length;
		if (ctrlLen>maxLimit)
		{
			alert("You can input "+maxLimit+" characters at most.");
			ctrl.focus();
			return false;
		}
		else
			countCtrl.value.trim() = maxLimit - ctrlLen;
	}

    /**
     * 检测Email地址的有效性
	 * 例如：checkEmail(form1.email)
     * @param ctrl Email控件
     */
	function checkEmail(ctrl)
	{
		if(!checkField(ctrl,"邮箱地址"))
		{
            return false;
        }
        var emailPat=/^(.+)@(.+)$/;
        var matchArray=ctrl.value.trim().match(emailPat);
        if (matchArray==null)
        {
        	alert("不正确的邮箱地址！");
            ctrl.focus();
            ctrl.select();
            return false;
		}
       if(!checkNoChinese(ctrl,"邮箱地址"))
       {
           return false;
    	}
        return true;
	}

    /**
     * 自动伸缩text控件的大小
	 * 例如：onFocus="setInterval('autoSize(form1.mybox,10)',1)"
     * @param ctrl			设置自动伸缩的控件
     * @param defaultSize	设置控件的缺省大小
     */
	function autoSize(ctrl,defaultSize)
	{
		if(ctrl.value.trim().length <= ctrl.maxLength)
			ctrl.size = ctrl.value.trim().length + 1;
		if(ctrl.size<defaultSize)
			ctrl.size=defaultSize;
	}

String.prototype.len = function()
{
  return this.replace(/[\u00FF-\uFFFF]/g,"aa").length;
}
    /**
     * 限制input长度
     * @param ctrl			设置限制长度的控件
     * @param maxLimit		能够输入的最大长度
	 * 例如：<input name="groupName" type="text" class="tb1" value="<%=typename%>" onKeyUp="checkLength(this, 20);" onKeyDown="checkLength(this, 20);" >
     */
	function checkLength(ctrl, maxLimit)
	{
		var ctrlLen=ctrl.value.trim().length;
		if (ctrlLen>maxLimit) {
			alert("You can input "+maxLimit+" characters at most.");
			ctrl.value=ctrl.value.trim().substring(0,maxLimit);
			ctrl.focus();
			return false;
		}
		return true;
	}


    /**
     * 处理返回按钮
	 * 例如：onclick="submitBack(regeditForm,'index.jsp','')"
     * @param form		form控件的名字
     * @param url		返回的url
	 * @param target	form控件的target
     */
	function submitBack(form,url,target)
	{
		form.action=url;
		if(target!=null&&target!=""){
			form.target=target;
		}
		form.submit();
	}

	/*
	 *弹出列表选择公用过程扩展（不执行SQL语句，只对对应控件赋值）
	 *参数说明：setControls:要赋值的控件数组;clearControls：要清空的控件数组； 以","分隔
	 *theJSP:对应弹出列表名 width,height：弹出列表的宽和高
	 *如果控件没有可以传空 如：
	 *selectList('form1.BMDM,form1.BMMC','form1.ZGBH,form1.ZGXM','../../common/select/selectZG.jsp',20,30)
	 *theJSP的返回值以String.fromCharCode(9)隔离开来
	 */
	function selectList(setControls,clearControls,theJSP,width,height){
		var sArray;
		var retValue;
		retValue=window.showModalDialog(theJSP,"","help:0;resizable:0;status=0;scrollbars=0;dialogWidth="+width+";dialogHeight="+height+";center=true");
		if(retValue==String.fromCharCode(9))
			clearControl(setControls);
		else
		if(retValue!="") {
			sArray=retValue.split(String.fromCharCode(9));
			if(setControls!=""){
				var setC=setControls.split(",");
				for(var i=0;i<setC.length;i++) {
					if(setC!=""){
						var tempControl=eval("document."+setC[i]);
						tempControl.value.trim()=sArray[i];
					}
				}
			}
			if(clearControls!="") clearControl(clearControls);
		}
		return retValue;
	}
	function clearControl(ctrls){
		if(ctrls!=""){
			var clearC=ctrls.split(",");
			for (var i=0;i<clearC.length ;i++ ) {
				if(clearC!=""){
					var tempControl=eval("document."+clearC[i]);
					tempControl.value.trim()="";
				}
			}
		}
	}

	/**
     * 检测网页控件的value属性是否为空
	 * 例如：checkFieldX(form1.address,'家庭地址')
     * @param ctrl 网页控件的代码名字
	 * @param info 网页控件的显示名称
     */
	function checkFieldX(ctrl,info)
	{
		if(ctrl.value.trim()==null||isBlank(ctrl))
		{
			alert(info+"cannot be null.");
			return false;
		}
		return true;
	}
function isNullOrUndefined(obj){
	return obj==null || (typeof(obj)=='undefined');
}
function isEmpty(obj){
	return obj=='';
}
//设置网页文档中所有超链接的类(覆盖原有的类) (2005-4-7by chengyongming)
function setAllLinkClass(className){
	setAttribute(document.getElementsByTagName("a"),"className",className);
}
//设置网页文档中所有超链接的类(不覆盖原有的类) (2005-4-7by chengyongming)
function setAllLinkClassNoOverwrite(className){
setAttributeNoOverwrite(document.getElementsByTagName("a"),"className",className);
}
//设置网页文档中所有TD对象的类(覆盖原有的类) (by chengyongming)
function setAllTdClass(className){
	setAttribute(document.getElementsByTagName("td"),"className",className);
}
//设置网页文档中所有TD对象的类(不覆盖原有的类) (by chengyongming)
function setAllTdClassNoOverwrite(className){
setAttributeNoOverwrite(document.getElementsByTagName("td"),"className",className);
}
//设置网页文档中所有Table的类(覆盖原有的类)(by chengyongming)
function setAllTableClass(className){
	setAttribute(document.getElementsByTagName("table"),"className",className);
}
//设置网页文档中所有Table的类(不覆盖原有的类) (by chengyongming)
function setAllTableClassNoOverwrite(className){
setAttributeNoOverwrite(document.getElementsByTagName("table"),"className",className);
}
//设置网页文档中所有button的样式 (by chengyongming)
function setButtonOnMouseOver(button){
	button.style.fontWeight='bold';
}
function setAllButtonStyle(cursorType){
	var ss=document.getElementsByTagName("input");
	for(var i=0;i<ss.length;i++){
		if(ss[i].type){
			if(ss[i].type.toLowerCase()=='button' || ss[i].type.toLowerCase()=='submit' || ss[i].type.toLowerCase()=='reset'){
				if(ss[i].style.cursor){
					try{ss[i].style.cursor=cursorType;}catch(e){}
				}
			}
		}
	}
}

//设置网页文档中标记为tag的对象的所属类为className(覆盖原有类) (by chengyongming)
function setClass(tag,className){

	setAttribute(document.getElementsByTagName(tag),"className",className);
}

//设置网页文档中标记为tag的对象的所属类为className(不覆盖原有类) (by chengyongming)
function setClassNoOverwrite(tag,className){
	setAttributeNoOverwrite(document.getElementsByTagName(tag),"className",className);
}
//设置网页文档中所有属性type值为type的tag对象的所属类为className (by chengyongming)
/**
refer to:
Using the DHTML Object Model:
	var aDivs = document.body.all.tags("DIV");
	access method:aDivs(index)
Using the DOM:
	var aDivs = document.body.getElementsByTagName("DIV");
	access method:aDivs[index]
*/
function setAttributeNodeClass(tag,type,className){

	var ss=document.getElementsByTagName(tag);
	for(var i=0;i<ss.length;i++){
		if(ss[i].type){
			if(ss[i].type.toLowerCase()==type.toLowerCase()){
				ss[i].className=className;
			}
		}
	}
}
//设置网页文档中所有属性type值为type的tag对象的所属类为className((若原有class不为空的话) (by chengyongming)
function setAttributeNodeClassNoOverwrite(tag,type,className){

	var ss=document.getElementsByTagName(tag);
		for(var i=0;i<ss.length;i++){
			if(ss[i].type){
				if(ss[i].type.toLowerCase()==type.toLowerCase()){
					if(isEmpty(ss[i].className)){
						ss[i].className=className;
					}
				}
			}
		}
}
//绑定函数functionName到网页文档中所有属性type值为type的tag对象的事件:eventName(by chengyongming)
function attachAttributeNodeEvent(tag,type,eventName,functionName){
	var ss=document.getElementsByTagName(tag);
		for(var i=0;i<ss.length;i++){
			if(ss[i].type.toLowerCase()==type.toLowerCase()){
				ss[i].attachEvent(eventName.toLowerCase(),functionName);
			}
		}
}
//清除网页中所有属性type值为type的tag对象的样式(如果存在的话) (by chengyongming待补充)
function clearAttributeNodeStyle(tag,type){

	var ss=document.getElementsByTagName(tag);
		for(var i=0;i<ss.length;i++){
		    if(ss[i].type){
				if(ss[i].type.toLowerCase()==type.toLowerCase()){
					if(ss[i].style){
						ss[i].style = null;
					}
				}
			}
		}
}
//通过日历控件选择时间,精确到分
//receiveObject:接收返回值的网页控件的名字
function selectTime(receiverObject){
	window.open("../public/calendar.jsp?receiverObject="+receiverObject,null,'height=230,width=400,status=no,toolbar=no,menubar=no,location=no,top=200,left=350','');

	//var ret=window.open("../public/calendar.htm","","help:no;scroll:no;resizable:no;status:0;dialogWidth:400px;dialogHeight:240px;center:yes");

	//if(typeof(ret)=='undefined' || ret == 'undefined'){

	//}else{
//		document.getElementById(receiveObject).value=ret;
//	}


}
function isMatch(s,regExp,desc,format){
	//var exp=new RegExp("^(010||02[0-9]||0[3-9][0-9]{2,3})[0-9]{7,8}$");
	//if(typeof(regExp).toString().toLowerCase()!='RegExp') return false;
	//alert(s);
	//alert(regExp);
	if(!s.match(regExp)){
		alert(desc+' 日期格式必须是 '+format+"。");
		return false;
	}
	return true;
}
//设置标记为tag的对象的cursor(覆盖原cursor)
function setTagCursor(tagName,cursor){
	var ss=document.getElementsByTagName(tagName);
	for(var i=0;i<ss.length;i++){
			try{ss[i].style.cursor==cursor;}catch(e){break;}
	}
 }
//设置标记为tag的对象的cursor(不覆盖原cursor)
function setTagCursorNoOverwrite(tagName,cursorName){
	var ss=document.getElementsByTagName(tagName);
	for(var i=0;i<ss.length;i++){
		try{
			with(ss[i].style){
				cursor = isEmpty(cursor) ?  cursorName : cursor ;
			}
		}catch(e){
			alert(e);
			break;
		}
	}
 }

//设置select的宽度自适应
function setWidthForSelect(){
	var ss=document.getElementsByTagName("select");
	for(var i=0;i<ss.length;i++){
		var s=ss[i];
		var max=0;
		for(var j=0;j<s.options.length;j++){
			max=Math.max(max,s.options[j].text.length);
		}
		s.style.width=1.37*max+"ex";
		if(max<5){
			s.style.width=8+"ex";
		}
	}
}
//根据对象ID设置其类(覆盖原有类)
function setClassByElementId(id,className){
	var element=invoke(id);
	try{element.className=className;}catch(e){}
}
//根据对象ID设置其类(不覆盖原有类)
function setClassNoOverwriteByElementId(id,className){
	var element=invoke(id);
	try{element.className= isEmpty(element.className) ? className : element.className;}catch(e){}
}
//设置对象的某事件为某功能函数(覆盖原函数或语句)
/**
 *param:id:对象ID
 *param:eventName:如onclick,onmouseover,oudbclick等
 *param:functionDesc 功能函数,可带参数
 */
function setEventByElementId(id,eventName,functionDesc){
	var element=invoke(id);
	if(element){
		try{element.eventName=eval(functionDesc);}catch(e){}
	}
}
//设置对象的某事件(不覆盖原函数或语句)为某功能函数
/**
 *param:id:对象ID
 *param:eventName:如onclick,onmouseover,oudbclick等
 *param:functionDesc 功能函数,可带参数
 */
function setEventNoOverwriteByElementId(id,eventName,functionDesc){
	var element=invoke(id);
	if(element){
		try{
			if(!element.eventName)
				element.eventName=eval(functionDesc);
		}catch(e){
		}
	}
}

function setAttribute(id,attributeName,attributeValue){
	var element=invoke(id);
	if(element && element.getAttribute(attributeName)){
			element.setAttribute(attributeName,attributeValue,0);
	}
}

function setAttributeNoOverwrite(id,attributeName,attributeValue){
	var element=invoke(id);
	if(element && element.getAttribute(attributeName) && isEmpty(element.getAttribute(attributeName))){
			element.setAttribute(attributeName,attributeValue,0);
	}
}

function invoke(id){
	with(window){
		return document.getElementById(id) ? document.getElementById(id) : document.all(''+id+'');
	}
}


function setAttribute(list,attrName,attrValue){
	try{
		for(var i= 0 ; i < list.length ; i ++){
			try{
				list[i].setAttribute(attrName,attrValue,0);//覆盖所有同名属性的值，大小写不敏感
			}catch(e){
				break;
			}
		}
	}catch(e){
	//	alert(e);
	}

}

function setAttributeNoOverwrite(list,attrName,attrValue){
	try{
		for(var i= 0 ; i < list.length ; i ++){
			try{
				if(isEmpty(list[i].getAttribute(attrName))){			list[i].setAttribute(attrName,attrValue,0);//覆盖所有同名属性的值，大小写不敏感
				}
			}catch(e){
				break;
			}
		}
	}catch(e){
	//	alert(e);
	}

}




function resizeByExplorer(){

}
    //ctrl的改变会影响到ctrl2的option元素(by jiangyifeng,需要页面上的一些隐藏域的支持)
    function selectEffectSelect(ctrl, ctrl2)
    {
        document.getElementById(ctrl2).options.length = 0;
        var select1Value = document.getElementById(ctrl).value;
        select1Value = select1Value.trim().substring(select1Value.indexOf("/") +
            1, select1Value.length);
        var ctlOptions = "";
        var arrOptionName = new Array();
        if (document.getElementById(select1Value) != null)
        {
            ctlOptions = document.getElementById(select1Value).value;
//alert("ctlOptions= "+ctlOptions);
            if ( (ctlOptions!='') && ctlOptions != null)
            {
                arrOptionName = ctlOptions.split(',');
            }
            else
            {
                arrOptionName = null;
            }
            document.getElementById(ctrl2).options.length = 0;
            if (arrOptionName != null)
            {
                for (var i = 0; i < arrOptionName.length; i++)
                {
                    var oOption = document.createElement("OPTION");
                    oOption.text = arrOptionName[i];
                    oOption.value = arrOptionName[i];
                    document.getElementById(ctrl2).add(oOption);
                }
            }
            else
            {
                alert("没有找到该告警源名下辖的告警ID");
            }
        }
        else
        {}
    }

function loopTRBackgroundColor(tableId,trClassName1,trClassName2,tdNowrap,tdClassName){ //表格内tr背景交替显示
  var tables = document.getElementsByTagName("table");
  var table ;
  for(var i = 0 ; i < tables.length ; i ++){
	table = tables[i];
	if(table.id != tableId )  continue ;
	for(var j=0;j<table.rows.length;j++)
	{
		var row = table.rows[j];
		if(row.className == ''){
			row.className = j % 2 == 0 ? trClassName1 : trClassName2;
		}
		for(var k = 0 ; k<row.cells.length; k ++){
			row.cells[k].noWrap =  tdNowrap;
			row.cells[k].className=tdClassName;
		}
	}
  }

}


function loopTDBackgroundColor(tableId,tdClassName1,tdClassName2){ //表格内td背景交替显示
    var tables = document.getElementsByTagName("table");
    var table ;
  try{
    for(var i = 0 ; i < tables.length ; i ++){
    table = tables[i];
    if(table.id != tableId )  continue ;
     table.cellPadding='3';
     table.cellSpacing='1';
     table.bgColor="#000000";
     table.width = table.width == '' ? "100%" : table.width ;
     for(var j=0;j<table.rows.length;j++){
     for(var k = 0 ; k<table.rows[j].cells.length; k ++ ){
     try{table.rows[j].cells[k].bgColor= k == 0 ? "#E0E6ED" : "#FFFFFF" ;}catch(e){}
     }
     }
     }
  }catch(e){
  }
}


function gotoPage(i){
	var form = pageform;
	form.curPage.value = i;
	form.submit();
}


function setUserPath(barInfo){
	//alert(barInfo);
	var bar = isNullOrUndefined(parent.document.getElementById('barInfo'))?document.getElementById('barInfo'):parent.document.getElementById('barInfo');
	//alert(bar);
	if(!isNullOrUndefined(bar)) bar.innerHTML = barInfo;
}
function setScrollBarStyle(element){
	try{
		element.style.scrollbarFaceColor ="#D9D9D9";
		element.style.scrollbarShadowColor ="#D9D9D9";
		element.style.scrollbarHighlightColor ="#ffffff";
		element.style.scrollbar3dLightColor ="#666666";
		element.style.scrollbarArrowColor ="#ffffff";
		element.style.scrollbarTrackColor ="#e7e7e7";
		element.style.scrollbarDarkShadowColor ="#666666";
	}catch(e){
	}
}

function setWindowTitle(title){
	try{top.window.document.title = title}catch(e){}
}

function clearAllMargin(element){
	try{
		with(element.style){
			marginLeft = marginLeft != '' ? marginLeft : '0';
			marginRight = marginRight != '' ? marginRight : '0';
			marginTop = marginTop != '' ? marginTop : '0';
			marginBottom = marginBottom != '' ? marginBottom : '0';
		}
	}catch(e){
	}
}

function setAllDisabled(tf){
	var ars =  setAllDisabled.arguments;
	if( ! ars[0])  setAllDisabled(true);
	var os =  document.all;
	for(var i = 0 ; i < os.length ; i ++){
		if(os[i].disabled && os[i].isContentEditable){
			 os[i].disabled = tf ;
		}

	}
	setTagDisabled("input",tf);
	setTagDisabled("select",tf);
}
function setTagDisabled(tagName,tf){
	var tags = document.getElementsByTagName(tagName);
	setElementArrayDisabled(tags,tf);
}
function setElementArrayDisabled(elementArray,tf){
	try{
		for(var i= 0 ; i < elementArray.length ; i ++){
			try{elementArray[i].disabled = tf}catch(ie){break;}
		}
	}catch(e){
	}

}

/**
 * 替换/n
 * strA 需要替换的参数
 * strB 替换后的参数
 */
function replaceEnter(strOld, strA, strB) {
    var str = strOld;
    while(str.indexOf(strA) >= 0) {
        str = str.replace(strA, strB);
    }
    return str;
}

/**
 * 字符替换
 */
function replaceToHtml(strOld) {

    var str = strOld;

    // 检查是否String
    if (typeof(str) != "string") {
    	return;
    }

    // 替换项目(可以自己增加，切记在结尾处增加)
    str=str.replace(/&/g,"＆");
    str=str.replace(/</g,"＜");
    str=str.replace(/>/g,"＞");
    str=str.replace(/'/g,"＇");
    str=str.replace(/"/g,'＂');
    str=str.replace(/#/g,"＃");
    str=str.replace(/%/g,"％");
    str=str.replace(/\\/g,"＼");

    return str;
}

/**
 * 检查特殊字符
 */
function checkCFGName(strOld) {

    var str = strOld;

    if (str.indexOf('&') >= 0
        || str.indexOf('<') >= 0
        || str.indexOf('>') >= 0
        || str.indexOf("'") >= 0
        || str.indexOf('"') >= 0
        || str.indexOf('#') >= 0
        || str.indexOf('%') >= 0
		|| str.indexOf('—') >= 0) {
        return false;
    }
    return true;
}

/**
 * 检查特殊字符,如果有下列字符就给出提示
 */
function checkCFGName(ctrl,info) {
    var str = ctrl.value.trim();
    if (str.indexOf('&') >= 0
        || str.indexOf('<') >= 0
        || str.indexOf('>') >= 0
        || str.indexOf("'") >= 0
        || str.indexOf('"') >= 0
        || str.indexOf('#') >= 0
		|| str.indexOf('\\') >= 0
        || str.indexOf('%') >= 0
		|| str.indexOf('—') >= 0)
	{
	    alert(info+"不能输入 & < > ' \" # % \\ —");
	    ctrl.focus();
	    ctrl.select();
	    return false;
    	}
    return true;
}

function logout(){//用户退出登录

	var s = "toolbar=yes,directories=yes,status=yes,scrollbars=yes,resizable=yes,menubar=yes,left=0,top=0,location =yes,width="+screen.availWidth+",height="+screen.availHeight+"";
	top.open('/logout.jsp','_blank',''+s+'',true);
	top.opener=null;
	top.close();
}

function noRight(){
	alert('对不起，您没有此项权限,请联系系统管理员！');
}
/**
* 时间如果是一位的，就在前面补0
* */
function repairZero(str){
	if(str.length == 1){
    	str = "0" + str;
    }
    return str;
}
/**
 * 检查是否有效号码
 */
 

/**
 * addSingleQuotes
 * text String 要加单引号的字符串
 * type String 传入字符串的类型,如果是varchar char datetime 类型的才要加'
 * V4.1 问题名称:解决编辑业务定制的数据库配置时,为列值重复加'的问题。author:姜毅峰 20060110
 */
function addSingleQuotes(text,type){
	if((text.indexOf("'") == -1) || (text.indexOf("'") != 0 && text.lastIndexOf(",") != text.length)){//无单引号
        if(type.indexOf("char") > -1 || type == "datetime"){
            text = "'" + text.trim() + "'";
        }
    }
    return text;
}

function  doSum(tt,ttToal){
	 
	  var   sumrow=0.0;
	  for(var i=0;i< tt.length;i++)
	  {
	  	var tmpVal= tt[i].innerHTML;
	  	
	  	sumrow += isNaN(tmpVal.trim()) ? 0 : parseFloat(tmpVal);   
	  }
	  ttToal.innerHTML = sumrow.toFixed(2);
	  
}


function   IsDate(mystring,sm)   {   
    var   reg   =   /^(\d{4})-(\d{2})-(\d{2})$/;   
    var   str   =   mystring;   
    var   arr   =   reg.exec(str);   
    if   (str=="")   return   true;   
    if   (!reg.test(str)&&RegExp.$2<=12&&RegExp.$3<=31){   
      alert("请保证"+sm+"中输入的日期格式为yyyy-mm-dd!");   
      return   false;   
      }   
      return   true;   
  }   
 

//isTelphoneNumberStart
function isTelphoneNumber(objectName) {
	var value = objectName.value;
    var exp1 = new RegExp('^13[0-9]*$');
    var exp2 = new RegExp('^15[0-9]*$');
    var exp3 = new RegExp('^8613[0-9]*$');
    var exp4 = new RegExp('^8615[0-9]*$');
    var exp5 = new RegExp('^106[0-9]*$');
    var exp6 = new RegExp('^01[0-9]*$');
    var exp7 = new RegExp('^14[0-9]*$');
    var exp8 = new RegExp('^8614[0-9]*$');
    var exp9 = new RegExp('^18[0-9]*$');
    var exp10 = new RegExp('^8618[0-9]*$');
    
    if(  (value.trim().match(exp1)   && ( value.length==11)) ||   (value.trim().match(exp2)   && ( value.length==11)) ||   (value.trim().match(exp3)   && ( value.length==13)) ||   (value.trim().match(exp4)  ) ||   (value.trim().match(exp5)) ||   (value.trim().match(exp6))  ||   (value.trim().match(exp7))  ||   (value.trim().match(exp8))  ||   (value.trim().match(exp9))  ||   (value.trim().match(exp10))  ) {
    	return true;
    }else{
		alert('您输入的手机号码有误（长度不足或者号段错误），请检查后重新填写!');
		objectName.focus();
		objectName.select();
		return false;
	}
}

// 用来保存所有的属性名称和值 
function   allPrpos ( obj ) { 
  
    var   props = "" ; 
    // 开始遍历 
    for ( var   p in obj ){   
        // 方法 
        if ( typeof ( obj [ p ]) == " function " ){   
            obj [ p ]() ; 
        } else {   
            // p 为属性名称，obj[p]为对应属性的值 
            props += p + " = " + obj [ p ] + " \t " ; 
        }   
    }   
    // 最后显示所有的属性 
    alert ( props ) ; 
} 
//isTelphoneNumberEnd
