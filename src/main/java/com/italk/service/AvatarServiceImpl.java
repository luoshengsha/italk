package com.italk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.italk.bean.Avatar;
import com.italk.bean.User;
import com.italk.dao.AvatarDao;

/**
 * 头像接口实现
 * @author luoshengsha
 *
 * 2016年6月27日-下午4:56:50
 */
@Service
public class AvatarServiceImpl implements AvatarService {
	
	@Resource
	private AvatarDao avatarDao;

	@Override
	public void save(Avatar avatar) {
		avatarDao.save(avatar);
	}

	@Override
	public List<Avatar> getByUser(User user) {
		return avatarDao.getByUser(user);
	}

}
