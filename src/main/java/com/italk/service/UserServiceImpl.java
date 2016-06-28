package com.italk.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.italk.bean.FriendsGroup;
import com.italk.bean.User;
import com.italk.dao.FriendsGroupDao;
import com.italk.dao.UserDao;
import com.italk.utils.WebUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private FriendsGroupDao groupDao;

	@Override
	public void save(User user) {
		//创建用户
		userDao.save(user);
		
		//创建一个默认的组“我的好友”
		FriendsGroup group =  new FriendsGroup();
		group.setUuid(WebUtil.createUuid());
		group.setName("我的好友");
		group.setDefault(true);
		group.setCreater(user);
		group.setCreateTime(new Date());
		groupDao.save(group);
	}

	@Override
	public void update(User user) {
		userDao.save(user);
	}

	@Override
	public User find(int id) {
		return userDao.findOne(id);
	}

	@Override
	public User find(String uuid) {
		return userDao.findByUuid(uuid);
	}

	@Override
	public Iterable<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public boolean checkUser(String username, String password) {
		User user = userDao.findByName(username);
		
		//密码校验成功，返回true，否则返回false
		return user.getPassword().equals(password) ? true : false;
	}

	@Override
	public User getByName(String name) {
		return userDao.findByName(name);
	}
}
