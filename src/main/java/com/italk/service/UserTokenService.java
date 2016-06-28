package com.italk.service;

import com.italk.bean.User;
import com.italk.bean.UserToken;

/**
 * 用户token接口
 * @author luoshengsha
 *
 * 2016年6月21日-下午5:18:59
 */
public interface UserTokenService {
	public void save(UserToken token);
	public void delete(Integer userid);
	public UserToken find(Integer userid);
	public boolean isExist(String token);
	public void delete(User user);
}
