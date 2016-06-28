package com.italk.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.User;
import com.italk.bean.UserStatus;
import com.italk.bean.UserToken;
import com.italk.service.UserService;
import com.italk.service.UserTokenService;
import com.italk.utils.Constants;
import com.italk.vo.ReturnObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 登录控制器
 * @author luoshengsha
 *
 * 2016年6月21日-下午3:18:06
 */
@RestController
public class LoginController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private UserTokenService tokenService;

	@RequestMapping(value="/login")
	public ReturnObject login(String name, String password,HttpSession session) {
		ReturnObject obj = new ReturnObject();
		
		try {
			if(userService.checkUser(name, password)) {//用户名和密码校验成功
				
				User user = userService.getByName(name);
				//更新用户状态
				updateUserStatus(user);
				
				String token = Jwts.builder().setSubject(name).setIssuedAt(new Date())
						.claim("pw", password.trim()).claim("id", user.getId()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
				
				//保存token
				saveToken(user, token);

				//将用户保存在session中
				session.setAttribute(Constants.SESSION_LOGIN_USERNAME, user);
		
				obj.setStatus(1);
				obj.setMessage("登录成功");
				obj.setData(token);
			} else {
				obj.setStatus(0);
				obj.setMessage("用户名或密码失败");
			}
		} catch (Exception e) {
			logger.error("用户登录失败",e);
			obj.setStatus(0);
			obj.setMessage("用户名或密码失败");
		}
		
		return obj;
	}

	/**
	 * 更新用户状态
	 * @param user
	 */
	private void updateUserStatus(User user) {
		//设置为在线
		user.setStatus(UserStatus.ONLINE);
		//设置最后登录时间
		user.setLastLoginTime(new Date());
		
		userService.update(user);
	}

	/**
	 * 保存token
	 * @param user
	 * @param token
	 */
	private void saveToken(User user, String token) {
		UserToken userToken = new UserToken();
		userToken.setUser(user);
		userToken.setToken(token);
		tokenService.save(userToken);
	}
}
