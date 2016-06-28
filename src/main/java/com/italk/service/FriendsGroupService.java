package com.italk.service;

import java.util.List;

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
	 * @param user
	 */
	public void moveMember(FriendsGroup fromGroup, FriendsGroup toGroup, User user);
	
	/**
	 * 删除好友
	 * @param group
	 * @param user
	 */
	public void deleteMember(FriendsGroup group, User user);
	
	/**
	 * 根据创建者获取
	 * @param creater
	 * @return
	 */
	public List<FriendsGroup> getByCreater(User creater);
}
