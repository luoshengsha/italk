package com.italk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String date2Str() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//12小时制
		return sdf.format(d);
	}
	
	public static String date2Str(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//12小时制
		return sdf.format(time);
	}
	
	public static String date2Str(String pattern) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}
	
	public static String date2Str(Date time,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(time);
	}
}
