package com.italk.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.italk.bean.RecentMessage;
import com.italk.bean.User;
import com.italk.dao.RecentMessageDao;

/**
 * 最近聊天消息接口实现
 * @author luoshengsha
 *
 * 2016年6月28日-上午11:07:47
 */
@Service
public class RecentMessageServiceImpl implements RecentMessageService {
	@Resource
	private RecentMessageDao messageDao;

	@Override
	public void save(RecentMessage message) {
		if(message.getType() == 1) {//好友聊天消息
			RecentMessage msg = messageDao.getFriendsMessage(message.getUser(), message.getToUser());
			if(msg != null) {
				msg.setContent(message.getContent());
				msg.setCreateTime(message.getCreateTime());
			}
			messageDao.save(msg);
		} else {//群聊消息
			RecentMessage msg = messageDao.getClusterMessage(message.getCluster(), message.getToUser());
			if(msg != null) {
				msg.setContent(message.getContent());
				msg.setCreateTime(message.getCreateTime());
			}
			messageDao.save(msg);
		}
	}

	@Override
	public List<RecentMessage> getMessages(User toUser) {
		return messageDao.getMessages(toUser);
	}

}
