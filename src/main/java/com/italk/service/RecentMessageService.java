package com.italk.service;

import java.util.List;

import com.italk.bean.RecentMessage;
import com.italk.bean.User;

/**
 * 最近聊天消息接口
 * @author luoshengsha
 *
 * 2016年6月28日-上午11:04:46
 */
public interface RecentMessageService {
	/**
	 * 保存聊天消息
	 * @param message
	 */
	public void save(RecentMessage message);
	
	/**
	 * 获取聊天记录
	 * @param toUser
	 * @return
	 */
	public List<RecentMessage> getMessages(User toUser);
}
