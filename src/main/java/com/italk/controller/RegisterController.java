package com.italk.controller;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.User;
import com.italk.bean.UserStatus;
import com.italk.service.UserService;
import com.italk.utils.WebUtil;
import com.italk.vo.ReturnObject;

/**
 * 注册控制器
 * @author luoshengsha
 *
 * 2016年6月21日-下午3:13:16
 */

@RestController
public class RegisterController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Resource
	private UserService userService;
	
	/**
	 * 注册
	 * @param name 用户名
	 * @param password 密码
	 * @param repassword 确认密码
	 * @return
	 */
	@RequestMapping(value="/register")
	public ReturnObject register(String name,String password,String repassword) {
		
		ReturnObject obj = new ReturnObject();
		
		if(StringUtils.isEmpty(name)) {
			obj.setStatus(0);
			obj.setMessage("用户名不能为空！");
			return obj;
		}
		if(StringUtils.isEmpty(password)) {
			obj.setStatus(0);
			obj.setMessage("密码不能为空！");
			return obj;
		}
		if(StringUtils.isEmpty(repassword)) {
			obj.setStatus(0);
			obj.setMessage("确认密码不能为空！");
			return obj;
		}
		if(!password.equals(repassword)) {
			obj.setStatus(0);
			obj.setMessage("两次输入的密码不一致！");
			return obj;
		}
		
		try {
			User user = new User();
			user.setUuid(WebUtil.createUuid());
			user.setName(name);
			user.setPassword(password);
			user.setStatus(UserStatus.OFFLINE);
			user.setRegistTime(new Date());
			userService.save(user);
			
			obj.setStatus(1);
			obj.setMessage("注册成功");
		} catch (Exception e) {
			logger.error("注册失败", e);
			obj.setStatus(0);
			obj.setMessage("注册失败");
		}
		
		return obj;
	}
}
