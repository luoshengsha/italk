package com.italk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.italk.bean.FriendsGroup;
import com.italk.bean.User;
import com.italk.dao.FriendsGroupDao;

/**
 * 好友分组service实现类
 * @author luoshengsha
 *
 * 2016年6月14日-下午4:26:39
 */
@Service
public class FriendsGroupServiceImpl implements FriendsGroupService {

	@Resource
	private FriendsGroupDao friendGroupDao;
	
	@Override
	public void save(FriendsGroup group) {
		friendGroupDao.save(group);
	}

	@Override
	public void update(FriendsGroup group) {
		friendGroupDao.save(group);
	}

	@Override
	public void delete(int id) {
		friendGroupDao.delete(id);
	}

	@Override
	public List<FriendsGroup> findByUser(User user) {
		// TODO Auto-generated method stub
		return friendGroupDao.findByUser(user);
	}

	@Override
	public FriendsGroup find(int id) {
		// TODO Auto-generated method stub
		return friendGroupDao.findOne(id);
	}

}
