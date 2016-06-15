package com.italk.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.italk.Application;
import com.italk.bean.FriendsGroup;
import com.italk.bean.User;
import com.italk.service.FriendsGroupService;
import com.italk.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FriendGroupTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendsGroupService groupService;
	
	@Test
	public void save() {
		FriendsGroup group = new FriendsGroup();
		group.setName("我的好友");
		group.setDefault(true);
		group.setCreater(userService.find(1));
		groupService.save(group);
	}
	
	@Test
	public void update() {
		FriendsGroup group = groupService.find(1);
		group.setName("我的好友");
		
		Set<User> members = new HashSet<User>();
		members.add(userService.find(2));
		group.setMembers(members);
		
		groupService.update(group);
	}
	
	@Test
	public void getByUser() {
		List<FriendsGroup> groups = groupService.findByUser(userService.find(1));
		for(FriendsGroup group : groups) {
			System.out.println(group.getName());
		
			Iterator<User> members = group.getMembers().iterator();
			while(members.hasNext()) {
				User user = members.next();
				System.out.println("name: " + user.getName() + ", nick: " + user.getNickname());
			}
		}
	}
}
