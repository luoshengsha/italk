package com.italk.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.User;
import com.italk.service.UserTokenService;
import com.italk.vo.ReturnObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 退出登录
 * @author luoshengsha
 *
 * 2016年6月21日-下午6:53:23
 */
@RestController
public class LogoutController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	
	@Resource
	private UserTokenService tokenService;

	@RequestMapping(value="/logout")
	public ReturnObject logout(@RequestParam(value="token",required=true)String token) {
		ReturnObject obj = new ReturnObject();
		try {
			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			
			if(claims.get("id") != null) {
				int id = (Integer)claims.get("id");
				tokenService.delete(new User(id));
			}
			
			obj.setStatus(1);
			obj.setMessage("退出登录成功");
		} catch (Exception e) {
			obj.setStatus(0);
			obj.setMessage("退出登录失败");
			
			logger.error("退出登录失败", e);
		}
		
		return obj;
	}
}
