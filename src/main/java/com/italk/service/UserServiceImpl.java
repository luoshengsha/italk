package com.italk.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.italk.bean.User;
import com.italk.dao.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
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
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
}
