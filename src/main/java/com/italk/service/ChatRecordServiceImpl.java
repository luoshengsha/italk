package com.italk.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.italk.bean.ChatRecord;
import com.italk.bean.RecentMessage;
import com.italk.dao.ChatRecordDao;
import com.italk.utils.WebUtil;

/**
 * 好友聊天记录接口实现
 * @author luoshengsha
 *
 * 2016年6月24日-下午1:26:09
 */

@Service
@Transactional
public class ChatRecordServiceImpl implements ChatRecordService {

	@Resource
	private ChatRecordDao recordDao;
	@Resource
	private RecentMessageService recMsgService;
	
	@Override
	public void save(ChatRecord record) {
		//保存聊天记录
		recordDao.save(record);
		
		//保存最近聊天记录
		RecentMessage msg = new RecentMessage();
		msg.setUuid(WebUtil.createUuid());
		msg.setUser(record.getFromUser());
		msg.setToUser(record.getToUser());
		msg.setContent(record.getContent());
		msg.setType(1);
		msg.setCreateTime(record.getCreateTime());
		recMsgService.save(msg);
	}

	@Override
	public Page<ChatRecord> getByChannel(String channel, Pageable page) {
		return recordDao.getByChannel(channel, page);
	}

}
