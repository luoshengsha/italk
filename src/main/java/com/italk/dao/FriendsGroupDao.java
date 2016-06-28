package com.italk.dao;

import java.util.List;

import javax.persistence.OrderBy;

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
	 * 根据uuid获取分组
	 * @param uuid
	 * @return
	 */
	@Query("select g from FriendsGroup g where uuid=?1")
	public FriendsGroup find(String uuid);
	
	/**
	 * 获取用户的默认好友分组
	 * @param user
	 * @return
	 */
	@Query("select g from FriendsGroup g where user=?1 and isDefault=true")
	public FriendsGroup getDefaultGroup(User user);
	
	/**
	 * 获取用户的好友分组
	 * @param creater
	 * @return
	 */
	@Query("select g from FriendsGroup g where g.creater=?1")
	@OrderBy("isDefault DESC")
	public List<FriendsGroup> getByCreater(User creater);
}

