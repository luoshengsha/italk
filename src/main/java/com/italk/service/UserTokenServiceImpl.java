package com.italk.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.italk.bean.User;
import com.italk.bean.UserToken;
import com.italk.dao.UserTokenDao;

/**
 * 用户token接口实现
 * @author luoshengsha
 *
 * 2016年6月21日-下午5:21:00
 */
@Service
@Transactional
public class UserTokenServiceImpl implements UserTokenService {

	@Resource
	private UserTokenDao tokenDao;
	
	@Override
	public void save(UserToken token) {
		UserToken userToken = tokenDao.getByUser(token.getUser());
		if(userToken != null) {
			tokenDao.delete(token.getUser());
		}
		tokenDao.save(token);
	}

	@Override
	public void delete(Integer userid) {
		tokenDao.delete(userid);
	}

	@Override
	public UserToken find(Integer userid) {
		return tokenDao.findOne(userid);
	}

	@Override
	public boolean isExist(String token) {
		UserToken usertoken = tokenDao.getByToken(token);
		return usertoken == null ? false : true;
	}

	@Override
	public void delete(User user) {
		tokenDao.delete(user);
	}

}
