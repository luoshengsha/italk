package com.italk.utils;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.italk.bean.User;

/**
 * web工具类
 * @author luoshengsha
 *
 * 2016年6月21日-下午8:52:40
 */
public class WebUtil {

	/**
	 * 获取登录用户
	 * @param request
	 * @return
	 */
	public static User getLoginUser(HttpServletRequest request) {
		return (User) request.getAttribute(Constants.SESSION_LOGIN_USERNAME);
	}
	
	/**
	 * 生成uuid
	 * @return
	 */
	public static String createUuid() {
		return UUID.randomUUID().toString();
	}
}
