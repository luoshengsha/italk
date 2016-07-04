package com.italk.service;

import java.util.List;

import com.italk.bean.Friend;
import com.italk.bean.FriendsGroup;
import com.italk.bean.User;

/**
 * 好友分组Service
 * @author luoshengsha
 *
 * 2016年6月14日-下午4:20:24
 */
public interface FriendsGroupService {
	public void save(FriendsGroup group);
	public void update(FriendsGroup group);
	public void delete(String uuid);
	public FriendsGroup find(int id);
	public FriendsGroup find(String uuid);
	/**
	 * 填充组员
	 * @param group
	 * @param user
	 */
	public void pushMember(FriendsGroup group, User user);
	
	/**
	 * 移动好友
	 * @param fromGroup
	 * @param toGroup
	 * @param friend
	 */
	public void moveMember(FriendsGroup fromGroup, FriendsGroup toGroup, Friend friend);
	
	/**
	 * 删除好友
	 * @param group
	 * @param friend
	 */
	public void deleteMember(FriendsGroup group, Friend friend);
	
	/**
	 * 根据创建者获取
	 * @param creater
	 * @return
	 */
	public List<FriendsGroup> getByCreater(User creater);
	
	/**
	 * 根据用户获取其所在的所有的分组
	 * @param userid
	 * @return
	 */
	public List<FriendsGroup> getByFriend(int userid);
}
