package com.acec.wgt.service.chargemanger.generate;
 
import   java.util.*;   

import com.ql.util.express.ExpressRunner;


public class Calculator {

	public static void main(String[] args) {
		
		System.out.println(jisuan("3absss"));
	}
	
	public static double jisuan1(String express) throws Exception{
		ExpressRunner runner = new ExpressRunner();
 		Object result = runner.execute(express, null,null, false, false);
 		System.out.println("表达式计算：" + express + " = " + result);
 		return Double.valueOf(result.toString());

	}
	
	public static double jisuan(String s) {
		LinkedList<Token> oper = new LinkedList<Token>();
		oper.addFirst(new Token('#', -1));
		LinkedList<Double> num = new LinkedList<Double>();
		String t = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (t.equals("") != true) {
					num.addFirst(new Double(t));
					t = "";
				}
				oper.addFirst(new Token('(', 0));
			}
			if (s.charAt(i) == ')') {
				if (t.equals("") != true) {
					num.addFirst(new Double(t));
					t = "";
				}
				while (true) {
					Token cur = oper.removeFirst();
					if (cur.c == '(')
						break;
					double d2 = num.removeFirst();
					double d1 = num.removeFirst();
					if (cur.c == '+')
						num.addFirst(d1 + d2);
					if (cur.c == '-')
						num.addFirst(d1 - d2);
					if (cur.c == '*')
						num.addFirst(d1 * d2);
					if (cur.c == '/') {
						if (d2 == 0) {
							System.out.println("除数为0");
							System.exit(1);
						}
						num.addFirst(d1 / d2);
					}
				}
			}
			if (s.charAt(i) == '+') {
				if (t.equals("") != true) {
					num.addFirst(new Double(t));
					t = "";
				}
				Token tnew = new Token('+', 1);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+')
							num.addFirst(d1 + d2);
						if (cur.c == '-')
							num.addFirst(d1 - d2);
						if (cur.c == '*')
							num.addFirst(d1 * d2);
						if (cur.c == '/') {
							if (d2 == 0) {
								System.out.println("除数为0");
								System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}
			}
			if (s.charAt(i) == '-') {
				if (t.equals("") != true) {
					num.addFirst(new Double(t));
					t = "";
				}
				Token tnew = new Token('-', 1);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+')
							num.addFirst(d1 + d2);
						if (cur.c == '-')
							num.addFirst(d1 - d2);
						if (cur.c == '*')
							num.addFirst(d1 * d2);
						if (cur.c == '/') {
							if (d2 == 0) {
								System.out.println("除数为0");
								System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}

			}
			if (s.charAt(i) == '*') {
				if (t.equals("") != true) {
					num.addFirst(new Double(t));
					t = "";
				}
				Token tnew = new Token('*', 2);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+')
							num.addFirst(d1 + d2);
						if (cur.c == '-')
							num.addFirst(d1 - d2);
						if (cur.c == '*')
							num.addFirst(d1 * d2);
						if (cur.c == '/') {
							if (d2 == 0) {
								System.out.println("除数为0");
								System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}

			}
			if (s.charAt(i) == '/') {
				if (t.equals("") != true) {
					num.addFirst(new Double(t));
					t = "";
				}
				Token tnew = new Token('/', 2);
				while (true) {
					Token cur = oper.removeFirst();
					if (tnew.level > cur.level) {
						oper.addFirst(cur);
						oper.addFirst(tnew);
						break;
					} else {
						double d2 = num.removeFirst();
						double d1 = num.removeFirst();
						if (cur.c == '+')
							num.addFirst(d1 + d2);
						if (cur.c == '-')
							num.addFirst(d1 - d2);
						if (cur.c == '*')
							num.addFirst(d1 * d2);
						if (cur.c == '/') {
							if (d2 == 0) {
								System.out.println("除数为0");
								System.exit(1);
							}
							num.addFirst(d1 / d2);
						}
					}
				}

			}
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '.')
				t += s.charAt(i);

		}
		if (t.equals("") != true)
			num.addFirst(new Double(t));
		while (oper.size() > 1) {
			Token cur = oper.removeFirst();
			double d2 = num.removeFirst();
			double d1 = num.removeFirst();
			if (cur.c == '+') {
				num.addFirst(d1 + d2);
			}
			if (cur.c == '-')
				num.addFirst(d1 - d2);
			if (cur.c == '*')
				num.addFirst(d1 * d2);
			if (cur.c == '/') {
				if (d2 == 0) {
					System.out.println("除数为0");
					System.exit(1);
				}
				num.addFirst(d1 / d2);
			}

		}

		return num.getFirst();

	}

}

class Token {
	public char c;
	public int level;// 运算优先级 ：(:0 +:1 -:1 *:2 /:2 ):3

	public Token(char c, int level) {
		this.c = c;
		this.level = level;
	}

	public String toString() {
		return "" + c + "   " + level;
	}
}