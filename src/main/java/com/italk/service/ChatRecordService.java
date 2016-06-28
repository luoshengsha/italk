package com.italk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.italk.bean.ChatRecord;

/**
 * 好友聊天记录接口
 * @author luoshengsha
 *
 * 2016年6月24日-下午1:20:48
 */
public interface ChatRecordService {

	/**
	 * 保存
	 * @param record
	 */
	public void save(ChatRecord record);
	
	/**
	 * 根据频道获取
	 * @param channel
	 * @param page
	 * @return
	 */
	public Page<ChatRecord> getByChannel(String channel, Pageable page);
}
