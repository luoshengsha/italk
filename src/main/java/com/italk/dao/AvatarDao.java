package com.italk.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.italk.bean.Avatar;
import com.italk.bean.User;

/**
 * 头像上传dao
 * @author luoshengsha
 *
 * 2016年6月27日-下午4:49:14
 */
public interface AvatarDao extends CrudRepository<Avatar, Integer> {

	/**
	 * 根据用户获取
	 * @param user
	 * @return
	 */
	public List<Avatar> getByUser(User user);
}
