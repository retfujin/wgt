package com.acec.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends com.bestnet.base.util.StringUtil {
	
	private static final String _BR = "<br />";
	
	private static final String _EMAILLETTER = "[\\w\\.-_]*";
	
	private static final String _EMAILREG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	/**
	 * 功能描述：是否为空白,包括null和""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * 功能描述：判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 功能描述：判断输入的字符串是否为纯汉字
	 * 
	 * @param str
	 *            传入的字符窜
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 功能描述：判断是否为质数
	 * 
	 * @param x
	 * @return
	 */
	public static boolean isPrime(int x) {
		if (x <= 7) {
			if (x == 2 || x == 3 || x == 5 || x == 7)
				return true;
		}
		int c = 7;
		if (x % 2 == 0)
			return false;
		if (x % 3 == 0)
			return false;
		if (x % 5 == 0)
			return false;
		int end = (int) Math.sqrt(x);
		while (c <= end) {
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 6;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 6;
		}
		return true;
	}
	
	/**
	 * 功能描述：人民币转成大写
	 * 
	 * @param str
	 *            数字字符串
	 * @return String 人民币转换成大写后的字符串
	 */
	public static String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}
	
	/**
	 * 功能描述：返回指定字节长度的字符串
	 * 
	 * @param str
	 *            String 字符串
	 * @param length
	 *            int 指定长度
	 * @return String 返回的字符串
	 */
	public static String toLength(String str, int length) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		try {
			if (str.getBytes("UTF-8").length <= length) {
				return str;
			}
		} catch (Exception e) {
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= 3;
		while (length > 0) {
			c = str.charAt(index);
			if (c < 128) {
				length--;
			} else {
				length -= 3;
			}
			if (length >= 0) {
				buff.append(c);
				index++;
			}
		}
		buff.append("...");
		return buff.toString();
	}
	
	/**
	 * 功能描述：判断是不是合法的手机号码
	 * 
	 * @param handset
	 * @return boolean
	 */
	public static boolean isHandset(String handset) {
		try {
			String regex = "^1[\\d]{10}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(handset);
			return matcher.matches();

		} catch (RuntimeException e) {
			return false;
		}
	}
	
	/**
	 * 功能描述：替换字符串
	 * 
	 * @param from
	 *            String 原始字符串
	 * @param to
	 *            String 目标字符串
	 * @param source
	 *            String 母字符串
	 * @return String 替换后的字符串
	 */
	public static String replace(String from, String to, String source) {
		if (source == null || from == null || to == null)
			return null;
		StringBuffer str = new StringBuffer("");
		int index = -1;
		while ((index = source.indexOf(from)) != -1) {
			str.append(source.substring(0, index) + to);
			source = source.substring(index + from.length());
			index = source.indexOf(from);
		}
		str.append(source);
		return str.toString();
	}
	
	/**
	 * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
	 * 
	 * @param str
	 *            String 原始字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlencode(String str) {
		if (str == null) {
			return null;
		}
		return replace("\"", "&quot;", replace("<", "&lt;", str));
	}

	/**
	 * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String htmldecode(String str) {
		if (str == null) {
			return null;
		}
		return replace("&quot;", "\"", replace("&lt;", "<", str));
	}

	/**
	 * 功能描述：在页面上直接显示文本内容，替换小于号，空格，回车，TAB
	 * 
	 * @param str
	 *            String 原始字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlshow(String str) {
		if (str == null) {
			return null;
		}

		str = replace("<", "&lt;", str);
		str = replace(" ", "&nbsp;", str);
		str = replace("\r\n", _BR, str);
		str = replace("\n", _BR, str);
		str = replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", str);
		return str;
	}
	
	/**
	 * 判断是不是合法字符 c 要判断的字符
	 */
	public static boolean isEmailLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile(_EMAILLETTER);
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 从指定的字符串中提取Email content 指定的字符串
	 * 
	 * @param content
	 * @return
	 */
	public static String parseEmail(String content) {
		String email = null;
		if (content == null || content.length() < 1) {
			return email;
		}
		// 找出含有@
		int beginPos;
		int i;
		String token = "@";
		String preHalf = "";
		String sufHalf = "";

		beginPos = content.indexOf(token);
		if (beginPos > -1) {
			// 前项扫描
			String s = null;
			i = beginPos;
			while (i > 0) {
				s = content.substring(i - 1, i);
				if (isEmailLetter(s))
					preHalf = s + preHalf;
				else
					break;
				i--;
			}
			// 后项扫描
			i = beginPos + 1;
			while (i < content.length()) {
				s = content.substring(i, i + 1);
				if (isEmailLetter(s))
					sufHalf = sufHalf + s;
				else
					break;
				i++;
			}
			// 判断合法性
			email = preHalf + "@" + sufHalf;
			if (isEmail(email)) {
				return email;
			}
		}
		return null;
	}
	
	/**
	 * 功能描述：判断输入的字符串是否符合Email样式.
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是Email样式返回true,否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern
				.compile(_EMAILREG);
		return pattern.matcher(email).matches();
	}
	
	
	
	/**  
     *  编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。  
     *  但是要保证汉字不被截半个，如"我ABC"4，应该截为"我AB"，输入"我ABC汉DEF"，6，  
     *  应该输出为"我ABC"而不是"我ABC+汉的半个"。  
     */  
    public static String subString(String str,int len){   
        if(str == null && "".equals(str)){   
            return null;   
        }   
        //将字符串中的char数组转换成指定编码方式的byte数组的函数   
        byte[] strBytes = null;   
        try {   
            strBytes = str.getBytes("GBK");   
               
        } catch (UnsupportedEncodingException e) {   
            e.printStackTrace();   
        }   
        //得到字符串的长度，判断截取字符串的长度是否在判断的范围内，否则返回原串   
        int strLen = strBytes.length;   
        if(len >= strLen || len < 1){   
            return str;   
        }   
//      System.out.println("strBytes.length="+strBytes.length);   
//      System.out.println("len="+len);   
        int count = 0;   
        for(int i=0; i<len; i++){   
            //将每个字节数组转换为整型数，以为后面根据值的正负来判断是否为汉字   
            int value = strBytes[i];   
//          System.out.print(value+",");   
            //如果是汉字(负)，则统计截取字符串中的汉字所占字节数   
            if(value < 0){      
                count++;   
            }   
//          System.out.println("zh count="+count);   
        }   
        //依据判断给定的字符串是否含有汉字，利用String类的substring()方法来截取不同的长度   
           
        //根据所统计的字节数，判断截取到字符是否为半个汉字，奇数为半个汉字   
        if(count % 2 !=0){   
            //如果在截取长度为1时，则将该汉字取出，   
            //其他情况则不截取这里的截取长度则按字符长度截取（截取字节长度数-截取汉字字节数/2-截取到的半个汉字的字节数）   
            len = (len == 1)?len:len-count/2-1;   
//          System.out.println("处理后的len="+len);   
               
        }else{   
            //截取字符长度为字节长度-汉字所占字节长度/2（汉字占两个字节）   
            len = len-(count/2);   
        }   
            return str.substring(0,len);   
       
    }   
    /**
    * 获取字符串的长度，如果有中文，则每个中文字符计为2位
    * 
    * @param value
    *            指定的字符串
    * @return 字符串的长度
    */
   public static int length(String value) {
       int valueLength = 0;
       String chinese = "[\u0391-\uFFE5]";
       /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
       for (int i = 0; i < value.length(); i++) {
           /* 获取一个字符 */
           String temp = value.substring(i, i + 1);
           /* 判断是否为中文字符 */
           if (temp.matches(chinese)) {
               /* 中文字符长度为2 */
               valueLength += 2;
           } else {
               /* 其他字符长度为1 */
               valueLength += 1;
           }
       }
       return valueLength;
   }

   /**
	 * 将异常原因转化成json格式能够读取的字符串
	 * @param e
	 * @return
	 */
	public static String praseExceptionMessage(Exception e){
		String exceptionTrace = e.getMessage();
		if(exceptionTrace.indexOf("'")!=-1){    
		    //将单引号转义一下，因为JSON串中的字符串类型可以单引号引起来的    
		    exceptionTrace = exceptionTrace.replaceAll("'", "\\’");    
		}    
		if(exceptionTrace.indexOf("\"")!=-1){    
		    //将双引号转义一下，因为JSON串中的字符串类型可以单引号引起来的    
		    exceptionTrace = exceptionTrace.replaceAll("\"", "\\\"");    
		}    
		   
		if(exceptionTrace.indexOf("\r\n")!=-1){    
		    //将回车换行转换一下，因为JSON串中字符串不能出现显式的回车换行    
		    exceptionTrace = exceptionTrace.replaceAll("\r\n", "\\u000d\\u000a");    
		}    
		if(exceptionTrace.indexOf("\n")!=-1){    
		    //将换行转换一下，因为JSON串中字符串不能出现显式的换行    
		    exceptionTrace = exceptionTrace.replaceAll("\n", "\\u000a");    
		}
		
		return exceptionTrace;
	}
	
	public static boolean isMobileNO(String mobiles){
		if(mobiles==null)
			return false;
		
		Pattern p = Pattern.compile("^((13[0-9])|(14[7])|(15[^4,\\D])|(18[0,2,3,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	
	public static void main(String[] args) {
		/*System.out.println("isEmpty=" + isEmpty(null));
		System.out.println("isInteger=" + isInteger("2341.324123"));
		System.out.println("isDouble=" + isDouble("2341.3241.23"));
		System.out.println("isChinese=" + isChinese("收发室"));
		System.out.println("isPrime=" + isPrime(47));
		System.out.println("hangeToBig=" + hangeToBig("2341324123.335"));
		System.out.println("toLength=" + toLength("2341324123", 5));
		System.out.println("isHandset=" + isHandset("12341324123"));
		System.out.println("replace=" + replace("&", "pp", "fgsdfxcv%&#&$)safasf"));
		System.out.println("htmlencode=" + htmlencode("23\"413\"241\"2<fasf<3"));
		System.out.println("htmldecode=" + htmldecode("2&quot;34&quot;132&lt;41&lt;23"));*/
		/*System.out.println("isEmailLetter=" + isEmailLetter("-"));
		System.out.println("parseEmail=" + parseEmail("23413w jasonfeng.hero@gmail.com 24123"));
		System.out.println("isEmail=" + isEmail("jasonfeng.hero@gmail.com"));*/
		System.out.println(toLength("您举报了一条违规贴，感谢您对本论坛的关注！谢谢！", 60));
		System.out.println(StringUtils.replace("/forum/forumadmin%7Cillegal%7Cthreadquery!getIllegalList.action", "%7C", "|"));
	}

}
