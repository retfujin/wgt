//将Date转成"yyy-MM-dd HH-mm-SS"的格式返回字串
function DateFormat(d)
{
	var yy = d.getFullYear();
	
	var MM = d.getMonth()+1;
	if(MM <= 9) MM = "0" + MM;

	var dd = d.getDate();
	if(dd<=9) dd = "0" + dd;

	var hh = d.getHours();
	if(hh <= 9)hh = "0" + hh;

	var mm = d.getMinutes();
	if(mm <= 9) mm = "0" + mm;

	var ss = d.getSeconds();
	if(ss <= 9) ss = "0" + ss;

	var result = yy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss;
	return result;
}

//当表示年或月的select所选项更改时执行重新生成所有select的操作
function generateOnChange()
{
	var cal=event.srcElement.ref;
	cal.flush();
	cal.generate();
}

//当除年和月外的select所选项更改时执行更新控制对象属性和hidden元素值的操作
function flushOnChange()
{
	var cal=event.srcElement.ref;
	cal.flush();
}

var YEAR	= 1;
var MONTH	= 2;
var DATE	= 3;
var HOUR	= 4;
var MINUTE	= 5;
//根据整数值确定option的显示字符串
/**
val: 项的整数值
type:项类别：年/月/日/时/分
一般，项的整数值就可以作为显示字符串。
但，对于月，整数值0表示1月，所以要处理。
*/
function getDesc(val,type)
{
	if(type==YEAR)
		return val;
	if(type==MONTH)
		return (val+1)+"";
	if(type==DATE)
		return val;
	if(type==HOUR)
		return val;
	if(type==MINUTE)
		return val;
	
}

//根据整数值确定option的值
/**
val: 项的整数值
type:项类别：年/月/日/时/分
项的整数值就可以作为option的值，除非要格式化,如将月份月格式化为"09"。
*/
function getVal(val,type)
{
	if(type==YEAR)
		return val;
	if(type==MONTH)
		return val;
	if(type==DATE)
		return val;
	if(type==HOUR)
		return val;
	if(type==MINUTE)
		return val;
	
}


//Calendar类
function Calendar(_sHidden,_sYear,_sMonth,_sDay,_sHour,_sMinute)
{
	//连接的表单引用
	this.sHidden = _sHidden;
	this.sYear	= _sYear;
	this.sMonth = _sMonth;
	this.sDay = _sDay;
	this.sHour = _sHour;
	this.sMinute = _sMinute;

	//将控制对象的引用关联给所有的select的ref
	this.sYear.ref	= this;
	this.sMonth.ref = this;
	this.sDay.ref = this;
	this.sHour.ref = this;
	this.sMinute.ref = this;	


	
	//设置默认年份的范围
	this.fromYear = 2010;
	this.toYear = 2020;
	//设置年份的范围
	this.setYearRange = function(_fromYear,_toYear)
	{
		this.fromYear = _fromYear;
		this.toYear = _toYear;
	}
	
	//根据select当前值更新对象的属性值
	function flush()
	{
		this.year = this.sYear.value;
		this.month = this.sMonth.value;
		this.day = this.sDay.value;
		this.hour = this.sHour.value;
		this.minute = this.sMinute.value;

		var result = new Date(this.year,this.month,this.day,this.hour,this.minute,0);
		

		this.sHidden.value = result.getTime();
		
		return result;
	}
	this.flush = flush;
	this.getValue = flush;

	//按值生成各列表
	this.generate = function()
	{
		generateS(this.sYear,this.fromYear,this.toYear,this.year,YEAR);
		generateS(this.sMonth,0,11,this.month,MONTH);
		generateS(this.sDay,1,this.getMaxDay(),this.day,DATE);

		generateS(this.sHour,0,23,this.hour,HOUR);
		generateS(this.sMinute,0,59,this.minute,MINUTE);
		

		this.flush();
	}
	

	
	//设置各个select的onchange事件
	this.sYear.onchange = generateOnChange;
	this.sMonth.onchange = generateOnChange;
	this.sDay.onchange = flushOnChange;
	this.sHour.onchange = flushOnChange;
	this.sMinute.onchange = flushOnChange;

	//按最小值、最大值和默认值生成select
	function generateS(theSel,minValue,maxValue,selValue,type)
	{
		theSel.length = 0;
		for(var i=minValue;i<=maxValue;i++)
		{
			var opt = document.createElement("OPTION");
			opt.text = getDesc(i,type);
			opt.value = getVal(i,type);
			if(i==selValue)
				opt.selected = true;
			theSel.options.add(opt);
		}
	}

	//设置当前时间
	function setNow()
	{
		this.year = parseInt(arguments[0]);
		this.year = parseInt(this.year);
		this.month = arguments[1];
		this.day = arguments[2];
		this.hour = arguments[3];
		this.minute = arguments[4];
	}
	this.setNow = setNow;
	
	//当前月的最大天数
	function getMaxDay()
	{
		if(this.month == 0 || this.month == 2
		   || this.month == 4 || this.month == 6
		   || this.month == 7 || this.month == 9
		   || this.month == 11)
		{
			return 31;
		}
		else if(this.month == 1)
		{
			if(this.getYearType()==true)
				return 29;
			else 
				return 28;
		}
		else
			return 30;
	}
	this.getMaxDay = getMaxDay;

	//判断当前年类型：  true闰年  false平年
	this.getYearType =function()
	{
		var aYear = parseInt(this.year);
		if(aYear%400 == 0 || (aYear%4 == 0 && aYear%100 != 0) )
		{
			return true;
		}
		else 
			return false;
	}

}