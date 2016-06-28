package com.italk.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.ClusChatRecord;

/**
 * 群组聊天记录dao
 * @author luoshengsha
 *
 * 2016年6月24日-下午4:45:33
 */
public interface ClusChatRecordDao extends CrudRepository<ClusChatRecord, Integer> {

	/**
	 * 根据频道获取
	 * @param channel 频道
	 * @param page 分页
	 * @return
	 */
	public Page<ClusChatRecord> getByChannel(String channel,Pageable page);
}
