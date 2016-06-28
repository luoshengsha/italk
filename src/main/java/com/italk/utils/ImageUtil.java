package com.italk.utils;

import java.util.Arrays;

/**
 * 图片工具类
 * @author luoshengsha
 *
 * 2016年6月27日-下午8:25:06
 */
public class ImageUtil {

	private static final String [] contentTypes = {"image/jpeg","image/jpg","image/gif","image/bmp"};
	
	/**
	 * 是否是图片格式
	 * @param contentType
	 * @return
	 */
	public static boolean isPic(String contentType) {
		return Arrays.asList(contentTypes).contains(contentType);
	}
}
