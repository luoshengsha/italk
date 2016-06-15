package com.italk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.italk.Application;
import com.italk.bean.User;
import com.italk.service.FriendsGroupService;
import com.italk.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendsGroupService groupService;
	
	@Test
	public void save() {
		User user = new User();
		user.setName("绿竹");
		user.setUuid("646sdf6sdsdfdsf4s6f54s");
		user.setPassword("123456");
		userService.save(user);
	}
	
	@Test
	public void update() {
		User user = userService.find(1);
		//user.setName("lvzhu");
		//user.setUuid("646sdf6sdf4s6f54s");
		//user.setPassword("123456");
		user.setNickname("罗生沙");
		userService.update(user);
	}
	
	@Test
	public void findAll() {
		userService.findAll();
	}
}
