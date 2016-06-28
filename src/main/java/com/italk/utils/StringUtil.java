package com.italk.utils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author luoshengsha
 *
 * 2016年6月25日-下午4:18:09
 */
public class StringUtil {
	/**
	 * 判断是否为null
	 * @param str 字符串
	 * @return 若为null则返回true，若不为null则返回false。
	 */
	public static boolean isNull(String str) {
		return str == null;
	}
	
	/**
	 * 判断是否为空(null或空字符串)
	 * @param str 字符串
	 * @return 若为null或空字符串则返回true，若既不为null，也不为空字符串则返回false。
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || ("".equals(str.trim()));
	}
	
	/**
	 * 去除空格符，当字符串以某个分隔符进行分割时，去除分隔符中的空格
	 * @param str 字符串
	 * @param separator 分隔符
	 * @return 去除空字符串后的字符串
	 */
	public static String removeEmpty(String str,String separator) {
		String[] strs = str.split(separator);
		StringBuffer sb = new StringBuffer();
		for (String s : strs) {
			if (!"".equals(s.trim())) {
				sb.append(s.trim());
				sb.append(separator);
			}
		}

		return sb.length() > 0 ? sb.substring(0, sb.length() - 1).toString() : "";
	}
	
	/**
	 * 去除分隔符后字符串是否为空
	 * @param str 字符串
	 * @param separator 分隔符
	 * @return 
	 */
	public static boolean isEmpty(String str,String separator) {
		String[] strs = str.split(separator);
		StringBuffer sb = new StringBuffer();
		for (String s : strs) {
			if (!"".equals(s.trim())) {
				sb.append(s.trim());
			}
		}

		return sb.length() > 0 ? false : true;
	}
	
	/**
	 * html标签转义
	 * @param content 待转义的内容
	 * @return 过滤后的字符串
	 */
	public static String convertHtml(String content) {
		if(content==null) return "";        
	     String html = content;
	     
	     html = html.replace( "'", "&apos;");
	     html = html.replaceAll( "&", "&amp;");
	     html = html.replace( "\"", "&quot;");  //"
	     html = html.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
	     html = html.replace( " ", "&nbsp;");// 替换空格
	     html = html.replace("<", "&lt;");
	     html = html.replaceAll( ">", "&gt;");
	   
	     return html;
	}
	
	/**
	 * html标签反转义
	 * @param content 待转义的内容
	 * @return 过滤后的字符串
	 */
	public static String reConvertHtml(String content) {
		if(content==null) return ""; 
	     String html = content;
	     
	     html = html.replace( "&apos;", "'");
	     html = html.replaceAll( "&amp;", "&");
	     html = html.replace( "&quot;", "\"");  //"
	     html = html.replace( "&nbsp;&nbsp;", "\t");// 替换跳格
	     html = html.replace( "&nbsp;", " ");// 替换空格
	     html = html.replace("&lt;", "<");
	     html = html.replaceAll( "&gt;", ">");
	   
	     return html;
	}
	
	/**
	 * 过滤javascript标签
	 * @param content
	 * @return 
	 * @author luoshengsha
	 */
	public static String scriptFilter(String content) {
		String htmlStr = content; //含html标签的字符串
        String textStr ="";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            
            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签
         
         textStr = htmlStr;
         
        }catch(Exception e) {
                    System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;//返回文本字符串
	}
	
	/**
	 * html标签过滤器
	 * @param str 待过滤的内容
	 * @return 过滤后的字符串
	 */
	public static String htmlFilter(String inputString) {
		 String htmlStr = inputString; //含html标签的字符串
        String textStr ="";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;          
        java.util.regex.Pattern p_ba;
        java.util.regex.Matcher m_ba;
        
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            String patternStr = "\\s+";
            
            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签

            p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); //过滤style标签
         
            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签
            
            p_ba = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
            m_ba = p_ba.matcher(htmlStr);
            htmlStr = m_ba.replaceAll(""); //过滤空格
         
         textStr = htmlStr;
         
        }catch(Exception e) {
                    System.err.println("Html2Text: " + e.getMessage());
        }          
        return textStr;//返回文本字符串
	}
	
	/**
	 * 数字补齐
	 * @param resource 原始数字
	 * @param sign 所要补充的符号
	 * @param length 长度
	 * @return 补零后的字符创
	 */
	public static String fillupDecimal(int resource,String sign,int length) {
		String str = String.valueOf(resource);
		while(str.length() < length) {
			str = sign + str;
		}
		return str;
	}
	
	/**
	 * 从字符串中解析数字
	 * @param str 字符串
	 * @param isFirst 是否只解析第一个数字
	 * @return 解析后的字符串，没有数字时返回null
	 */
	public static String analyzeDigit(String str,boolean isFirst) {
		if(isEmpty(str))
			return null;
		
		boolean flag=false;
		String digitStr="";
		for(int i = 0;i < str.length();i++){
			Object _tmp = str.charAt(i);
			if(_tmp.toString().matches("[0-9]")){
				digitStr+=_tmp;
				flag=true;
			} else if(flag) {
				if(isFirst){
					break;
				} else {
					continue;
				}
			}
		}
		return digitStr.length() > 0 ? digitStr:null;
	}
	
	/**
	 * 随机生成数字字符
	 * @param numLength 字符长度
	 * @return 
	 * @author luoshengsha
	 */
	public static String random(int numLength) {
		/**
		 * 定义可生成字符
		 */
		String str[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		/**
		 * 生成的字符串
		 */
		String resultStr = "";

		/**
		 * 生成随机字符
		 */
		Random r = new Random();

		for (int i = 0; i < numLength; i++) {

			int k = r.nextInt(9);

			resultStr += str[k];

		}

		return resultStr;
	}
	
}
