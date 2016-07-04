package com.italk.dao;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.italk.bean.Cluster;
import com.italk.bean.RecentMessage;
import com.italk.bean.User;

/**
 * 最近聊天消息dao
 * @author luoshengsha
 *
 * 2016年6月28日-上午10:52:10
 */
public interface RecentMessageDao extends CrudRepository<RecentMessage, Integer> {

	/**
	 * 获取最近聊天消息
	 * @param touser 接收用户
	 * @return
	 */
	@Query("select m from RecentMessage m where m.toUser=?1")
	@OrderBy("createTime DESC")
	public List<RecentMessage> getMessages(User touser);
	
	/**
	 * 获取好友间聊天消息
	 * @param user 发送者
	 * @param toUser 接收者
	 * @return
	 */
	@Query("select m from RecentMessage m where m.user=?1 and m.toUser=?2")
	public RecentMessage getFriendsMessage(User user,User toUser);
	
	/**
	 * 获取群聊消息
	 * @param cluster 群组
	 * @param toUser 接收者
	 * @return
	 */
	@Query("select m from RecentMessage m where m.cluster=?1 and m.toUser=?2")
	public RecentMessage getClusterMessage(Cluster cluster, User toUser);
	
	/**
	 * 获取未读信息
	 * @param toUser 接收者
	 * @return
	 */
	@Query("select m from RecentMessage m where m.toUser=?1 and m.messageAmount > 0")
	public List<RecentMessage> getUnreadMessage(User toUser);
	
	/**
	 * 读消息
	 * @param uuid
	 */
	@Modifying
	@Query("update RecentMessage set messageAmount=0 where uuid=?1")
	public void readMessage(String uuid);
}
