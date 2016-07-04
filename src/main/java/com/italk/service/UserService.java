package com.italk.service;

import com.italk.bean.User;
import com.italk.bean.UserStatus;

public interface UserService {
	
	public void save(User user);
	
	public void update(User user);
	
	public User find(int id);
	
	public User find(String uuid);
	
	public Iterable<User> findAll();
	
	public boolean checkUser(String username, String password);
	
	public User getByName(String name);
	
	public void setStatus(String uuid,UserStatus status);
}
