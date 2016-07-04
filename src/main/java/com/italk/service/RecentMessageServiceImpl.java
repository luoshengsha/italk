package com.italk.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.italk.bean.RecentMessage;
import com.italk.bean.User;
import com.italk.dao.RecentMessageDao;
import com.italk.repository.ChannelRespository;
import com.italk.utils.WebUtil;

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
	@Transactional
	public void save(RecentMessage message) {
		if(message.getType() == 1) {//好友聊天消息
			
			//获取聊天频道
			String channel = WebUtil.createChannel(message.getUser(), message.getToUser());
			
			RecentMessage msg = messageDao.getFriendsMessage(message.getUser(), message.getToUser());
			if(msg != null) {
				msg.setContent(message.getContent());
				msg.setCreateTime(message.getCreateTime());
				
				//判断接收用户是否已订阅相对应的频道
				if(!ChannelRespository.isExist(channel)) {
					//如果没有订阅，则消息数量+1
					msg.setMessageAmount(msg.getMessageAmount()+1);
				}
				
				messageDao.save(msg);
			} else {
				//判断接收用户是否已订阅相对应的频道
				if(!ChannelRespository.isExist(channel)) {
					//如果没有订阅，则消息数量为1
					message.setMessageAmount(1);
				}
				messageDao.save(message);
			}
		} else {//群聊消息
			RecentMessage msg = messageDao.getClusterMessage(message.getCluster(), message.getToUser());
			if(msg != null) {
				msg.setContent(message.getContent());
				msg.setCreateTime(message.getCreateTime());
				
				//判断接收用户是否已订阅相对应的频道
				if(!ChannelRespository.isExist(message.getUuid())) {
					//如果没有订阅，则消息数量+1
					msg.setMessageAmount(msg.getMessageAmount()+1);
				}
				
				messageDao.save(msg);
			} else {
				//判断接收用户是否已订阅相对应的频道
				if(!ChannelRespository.isExist(message.getUuid())) {
					//如果没有订阅，则消息数量为1
					message.setMessageAmount(1);
				}
				messageDao.save(message);
			}
		}
	}

	@Override
	public List<RecentMessage> getMessages(User toUser) {
		return messageDao.getMessages(toUser);
	}

	@Override
	public List<RecentMessage> getUnreadMessage(User toUser) {
		// TODO Auto-generated method stub
		return messageDao.getUnreadMessage(toUser);
	}

	@Override
	public void readMessage(String id) {
		messageDao.readMessage(id);
	}

}
