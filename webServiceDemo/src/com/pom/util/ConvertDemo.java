package com.pom.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConvertDemo {

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date,String formatStr) {
//		"yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str,String formatStr) {
//		"yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("日期转字符串：" + ConvertDemo.DateToStr(date,"yyyy-MM-dd HH:mm:ss"));
		System.out.println("字符串转日期：" + ConvertDemo.StrToDate(ConvertDemo.DateToStr(date,"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));

	}
}