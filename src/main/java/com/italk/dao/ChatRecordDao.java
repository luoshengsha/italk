package com.italk.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.ChatRecord;

/**
 * 好友聊天记录dao
 * @author luoshengsha
 *
 * 2016年6月24日-下午1:12:47
 */
public interface ChatRecordDao extends CrudRepository<ChatRecord, Integer> {

	/**
	 * 根据频道获取
	 * @param channel 频道
	 * @param page 分页
	 * @return
	 */
	public Page<ChatRecord> getByChannel(String channel,Pageable page);
}
