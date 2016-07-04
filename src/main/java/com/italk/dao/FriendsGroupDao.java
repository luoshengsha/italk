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
	
	/**
	 * 通过用户id查找此用户所在的所有好友分组
	 * @param userid
	 * @return
	 */
	@Query(value="select g.* from t_friendsgroup g LEFT JOIN t_friendsgroup_friend gf on gf.group_id=g.id LEFT JOIN t_friend f on gf.friend_id=f.id where f.friend_id=?1",nativeQuery=true)
	public List<FriendsGroup> getByFriend(int userid);
}

