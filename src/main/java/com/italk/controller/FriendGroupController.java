package com.italk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italk.bean.FriendsGroup;
import com.italk.service.FriendsGroupService;
import com.italk.service.UserService;

@RestController
public class FriendGroupController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FriendsGroupService groupService;
	
	@RequestMapping(value="/getByUser")
	public void getByUser() {
		List<FriendsGroup> groups = groupService.findByUser(userService.find(1));
		/*for(FriendGroup group : groups) {
			System.out.println(group.getName());
		
			Iterator<User> members = group.getMembers().iterator();
			while(members.hasNext()) {
				User user = members.next();
				System.out.println("name: " + user.getName() + ", nick: " + user.getNickname());
			}
		}*/
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("a");
		list.add("c");
		
		for(String l : list) {
			System.out.println(l);
		}
		
		list.remove("a");
		
		for(String l : list) {
			System.out.println(l);
		}
	}
}
