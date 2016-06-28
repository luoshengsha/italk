package com.italk.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.User;
import com.italk.bean.UserToken;

/**
 * 用户token Dao
 * @author luoshengsha
 *
 * 2016年6月21日-下午5:18:13
 */
public interface UserTokenDao extends CrudRepository<UserToken, Integer> {

	/**
	 * 根据token值获取用户token
	 * @param token
	 * @return
	 */
	public UserToken getByToken(String token);
	
	/**
	 * 根据用户删除token
	 * @param user
	 */
	@Modifying
	@Query("delete from UserToken t where t.user=?1")
	public void delete(User user);
	
	/**
	 * 根据用户获取token
	 * @param user
	 * @return
	 */
	public UserToken getByUser(User user);
}
