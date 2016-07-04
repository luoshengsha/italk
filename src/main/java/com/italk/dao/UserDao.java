package com.italk.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.User;
import com.italk.bean.UserStatus;

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
	
	/**
	 * 更新用户状态
	 * @param user
	 * @param status
	 */
	@Modifying
	@Query("update User set status=?2 where uuid=?1")
	public void setStatus(String uuid, UserStatus status);
}
