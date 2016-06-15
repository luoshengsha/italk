package com.italk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.FriendsGroup;
import com.italk.bean.User;

/**
 * 好友分组
 * @author luoshengsha
 *
 * 2016年6月14日-下午4:19:18
 */
public interface FriendsGroupDao extends CrudRepository<FriendsGroup, Integer> {
	
	/**
	 * 获取用户的好友分组
	 * @param user
	 * @return
	 */
	@Query("select g from FriendsGroup g where g.creater=?1")
	public List<FriendsGroup> findByUser(User user);
}

