package com.italk.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.User;
import com.italk.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	@Resource
	private UserService userService;
	
	/*@RequestMapping(value="/list")
	public Iterable<User> findAll() {
		return userService.findAll();
	}*/
}
