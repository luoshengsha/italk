package com.italk.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.italk.bean.ClusChatRecord;
import com.italk.bean.RecentMessage;
import com.italk.dao.ClusChatRecordDao;
import com.italk.utils.WebUtil;

/**
 * 群组聊天记录接口实现
 * @author luoshengsha
 *
 * 2016年6月24日-下午4:48:00
 */
@Service
public class ClusChatRecordServiceImpl implements ClusChatRecordService {
	
	@Resource
	private ClusChatRecordDao recordDao;
	@Resource
	private RecentMessageService recMsgService;
	@Resource
	private ClusterService clusterService;
	
	@Override
	@Transactional
	public void save(ClusChatRecord record) {
		recordDao.save(record);
		
		//保存最近聊天记录
		RecentMessage msg = new RecentMessage();
		msg.setUuid(WebUtil.createUuid());
		msg.setCluster(clusterService.find(record.getChannel()));
		msg.setToUser(record.getToUser());
		msg.setContent(record.getContent());
		msg.setType(0);
		msg.setCreateTime(record.getCreateTime());
		recMsgService.save(msg);
	}

	@Override
	public Page<ClusChatRecord> getByChannel(String channel, Pageable page) {
		return recordDao.getByChannel(channel, page);
	}

}
