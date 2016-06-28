package com.italk.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.italk.bean.ClusChatRecord;

/**
 * 群组聊天记录接口
 * @author luoshengsha
 *
 * 2016年6月24日-下午4:47:12
 */
public interface ClusChatRecordService {

	/**
	 * 保存
	 * @param record
	 */
	public void save(ClusChatRecord record);
	
	/**
	 * 根据频道获取
	 * @param channel
	 * @param page
	 * @return
	 */
	public Page<ClusChatRecord> getByChannel(String channel, Pageable page);
}
