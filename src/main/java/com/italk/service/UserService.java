package com.italk.service;

import com.italk.bean.User;

public interface UserService {
	
	public void save(User user);
	
	public void update(User user);
	
	public User find(int id);
	
	public Iterable<User> findAll();
}
