package com.acec.core.utils;

import java.math.BigDecimal;

public class ArithUtils {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 2;

	// 这个类不能实例化
	private ArithUtils() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */

	public static float add(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.add(b2).floatValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */

	public static float sub(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.subtract(b2).floatValue();
	}
	
	public static BigDecimal sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2);
	}
	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */

	public static float mul(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.multiply(b2).floatValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后2位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */

	public static float div(float v1, float v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */

	public static float div(float v1, float v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v  需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果 ,是字符串哦
	 */

	public static String round(float v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
	//	BigDecimal b = new BigDecimal(Float.toString(v));
		
	//	BigDecimal one = new BigDecimal("1");
	//	return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
		
		java.text.DecimalFormat f= new java.text.DecimalFormat("###.00");
		
		return f.format(v);
		
	}
	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v  需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */

	public static String round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The   scale   must   be   a   positive   integer   or   zero");
		}
	//	BigDecimal b = new BigDecimal(Double.toString(v));
	//	BigDecimal one = new BigDecimal("1");
	//	return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).floatValue();
		java.text.DecimalFormat f= new java.text.DecimalFormat("###.00");
		//System.out.println(f.format(v));
		return f.format(v);
		
	}
	
	/* 
	* 将数字转换成中文的大写货币值 
	* @param moneyValue 
	* @return 
	*/ 
	public static String convertToCapitalMoney(double moneyValue) {
		double money = moneyValue + 0.005; // 防止浮点数四舍五入造成误差
		String Result = "";
		String capitalLetter = "零壹贰叁肆伍陆柒捌玖";
		String moneytaryUnit = "分角圆拾佰仟万拾佰仟亿拾佰仟万拾佰仟亿拾佰仟";
		String tempCapital, tempUnit;

		int integer; // 钱的整数部分
		int point; // 钱的小数部分
		int tempValue; // 钱的每一位的值
		integer = (int) money;
		point = (int) (100 * (money - (float) integer));

		if (integer == 0)
			Result = "零圆";
		/*
		 * 货币整数部分操作 1. 依次取得每一位上的值 2. 转换成大写 3. 确定货币单位
		 */
		for (int i = 1; integer > 0; i++) {
			tempValue = (integer % 10);
			tempCapital = capitalLetter.substring(tempValue, tempValue + 1);
			tempUnit = moneytaryUnit.substring(i + 1, i + 2);
			Result = tempCapital + tempUnit + Result;
			integer = integer / 10;
		}
		/*
		 * 货币小数部分操作
		 */
		tempValue = (point / 10);
		for (int i = 1; i > -1; i--) {
			tempCapital = capitalLetter.substring(tempValue, tempValue + 1);
			tempUnit = moneytaryUnit.substring(i, i + 1);
			Result = Result + tempCapital + tempUnit;
			tempValue = point % 10;
		}
		return Result;
	}

	
	
	public static void main(String args[])
	{

//		System.out.println(ArithUtils.div(21, 31,2));
		
	//	System.out.println(round(1620442.82,2));
		Double f1 = Double.parseDouble("2586030.90")+Double.parseDouble("1367616.49");
		System.out.println("------------"+div(3f,7f));
		System.out.println(ArithUtils.round(f1,2));
	//	System.out.println(b.length +" " +b[1]);
	}
	
	
	
	
	
}
