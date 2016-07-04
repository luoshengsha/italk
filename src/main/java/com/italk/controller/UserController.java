package com.italk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.User;
import com.italk.bean.UserStatus;
import com.italk.service.UserService;
import com.italk.utils.WebUtil;
import com.italk.vo.ReturnObject;

@RestController
@RequestMapping(value="/center/user")
public class UserController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;
	
	/**
	 * 编辑用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit")
	public User edit(HttpServletRequest request) {
		User user = userService.find(WebUtil.getLoginUser(request).getId());
		return user;
	}
	
	/**
	 * 更新
	 * @param id
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value="/update")
	public ReturnObject update(String nickname,HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			//ObjectMapper mapper = new ObjectMapper();
			//User user = mapper.readValue(json, User.class);
			
			User user = WebUtil.getLoginUser(request);
			user.setNickname(nickname);
			userService.update(user);
			
			obj.setStatus(1);
			obj.setMessage("更新成功！");
		} catch (Exception e) {
			logger.error("更新用户信息失败", e);
			obj.setStatus(0);
			obj.setMessage("更新失败");
		}
		
		return obj;
	}
	
	/**
	 * 更改用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/set_status")
	public ReturnObject toggleStatus(UserStatus status,HttpServletRequest request) {
		ReturnObject obj = new ReturnObject();
		
		try {
			User user = WebUtil.getLoginUser(request);
			user.setStatus(status);
			
			obj.setStatus(1);
			obj.setMessage("更改成功！");
		} catch (Exception e) {
			logger.error("更新用户状态失败", e);
			obj.setStatus(0);
			obj.setMessage("更改失败");
		}
		
		return obj;
	}
}
