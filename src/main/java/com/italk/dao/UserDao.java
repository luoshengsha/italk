package com.italk.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.italk.bean.User;

@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

	/**
	 * 根据uuid获取用户
	 * @param uuid
	 * @return
	 */
	public User findByUuid(String uuid);
	
	/**
	 * 根据用户名获取用户
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	
}
