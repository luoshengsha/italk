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
	public void delete(int id);
	public List<FriendsGroup> findByUser(User user);
	public FriendsGroup find(int id);
}
