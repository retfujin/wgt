
/**
 * 日期中时间---年的判断
 * @param strName----ID名
 * @param strValue---验证的字符串
 * @return
 */
function check_int_year(strName,strValue){
	var temp=/^-[0-9]*[1-9][0-9]*$/;//负整数
	if(!isNaN(document.getElementById(strName).value)){
		if(temp.test(document.getElementById(strName).value)){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
		if(parseInt(document.getElementById(strName).value)>2015 || parseInt(document.getElementById(strName).value)<2000){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
	}else{
		alert(strValue);
		document.getElementById(strName).focus();
		return false;
	}
}



/**
 * 日期中时间---月的判断
 * @param strName----ID名
 * @param strValue---验证的字符串
 * @return
 */
function check_int_month(strName,strValue){
	var temp=/^-[0-9]*[1-9][0-9]*$/;//负整数
	if(!isNaN(document.getElementById(strName).value)){
		if(temp.test(document.getElementById(strName).value)){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
		if(parseInt(document.getElementById(strName).value)<1 || parseInt(document.getElementById(strName).value)>12 ){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
	}else{
		alert(strValue);
		document.getElementById(strName).focus();
		return false;
	}
}


/**
 * 日期中时间---日的判断
 * @param strName----ID名
 * @param strValue---验证的字符串
 * @return
 */
function check_int_day(strName,strValue){
	var temp=/^-[0-9]*[1-9][0-9]*$/;//负整数
	if(!isNaN(document.getElementById(strName).value)){
		if(temp.test(document.getElementById(strName).value)){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
		if(parseInt(document.getElementById(strName).value)<1 || parseInt(document.getElementById(strName).value)>31 ){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
	}else{
		alert(strValue);
		document.getElementById(strName).focus();
		return false;
	}
}



/**
 * 日期中时间---时的判断
 * @param strName----ID名
 * @param strValue---验证的字符串
 * @return
 */
function check_int_hours(strName,strValue){
	var temp=/^-[0-9]*[1-9][0-9]*$/;//负整数
	if(!isNaN(document.getElementById(strName).value)){
		if(temp.test(document.getElementById(strName).value)){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
		if(parseInt(document.getElementById(strName).value)>23){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
	}else{
		alert(strValue);
		document.getElementById(strName).focus();
		return false;
	}
}

/**
 * 日期中时间---分的判断
 * @param strName----ID名
 * @param strValue---验证的字符串
 * @return
 */
function check_int_min(strName,strValue){
	var temp=/^-[0-9]*[1-9][0-9]*$/;//负整数
	if(!isNaN(document.getElementById(strName).value)){
		if(temp.test(document.getElementById(strName).value)){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
		if(parseInt(document.getElementById(strName).value)>59){
			alert(strValue);
			document.getElementById(strName).focus();
			return false;
		}
	}else{
		alert(strValue);
		document.getElementById(strName).focus();
		return false;
	}
}
