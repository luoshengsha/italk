package com.italk.service;

import java.util.List;

import com.italk.bean.Avatar;
import com.italk.bean.User;

/**
 * 头像接口
 * @author luoshengsha
 *
 * 2016年6月27日-下午4:55:30
 */
public interface AvatarService {
	/**
	 * 保存
	 * @param avatar
	 */
	public void save(Avatar avatar);
	
	/**
	 * 根据用户获取头像
	 * @param user
	 * @return
	 */
	public List<Avatar> getByUser(User user);
}
