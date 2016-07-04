package com.italk.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.italk.bean.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

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
	
	/**
	 * 从token中获取用户名和密码
	 * @param token
	 * @return
	 */
	public static Map<String,String> getPrincipal(String token) {
		final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String name = claims.getSubject();
		String password = (String) claims.get("pw");
		String uuid = (String) claims.get("uuid");
		String id = (String) claims.get("id");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("password", password);
		map.put("uuid", uuid);
		map.put("id", id);
		
		return map;
	}
	
	/**
	 * 校验频道是否在白名单中
	 * 白名单中的频道所有用户都会订阅
	 * @param channel
	 * @return
	 */
	public static boolean isWhiteChannel(String channel) {
		List<String> whiteChannels = new ArrayList<String>();
		whiteChannels.add("/app/chat/get_recent_msg.htm");//最近聊天记录
		whiteChannels.add("/app/chat/get_unread_msg.htm");//未读消息
		
		return whiteChannels.contains(channel);
	}
	
	/**
	 * 创建两个好友之间聊天的频道
	 * @param fromUser
	 * @param toUser
	 * @return
	 */
	public static String createChannel(User fromUser, User toUser) {
		int fromUserId = fromUser.getId();
		int toUserId = toUser.getId();
		if(fromUserId > toUserId) {
			return toUserId + "-" + fromUserId;
		} else {
			return fromUserId + "-" + toUserId;
		}
	}
}
