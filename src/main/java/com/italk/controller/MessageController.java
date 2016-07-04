package com.italk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.italk.bean.ChatRecord;
import com.italk.bean.ClusChatRecord;
import com.italk.bean.RecentMessage;
import com.italk.bean.User;
import com.italk.service.ChatRecordService;
import com.italk.service.ClusChatRecordService;
import com.italk.service.RecentMessageService;
import com.italk.service.UserService;
import com.italk.utils.BeanConvertUtil;
import com.italk.utils.DateUtil;
import com.italk.utils.WebUtil;
import com.italk.vo.ChatRecordVo;
import com.italk.vo.RecentMessageVo;
import com.italk.vo.ReturnObject;

/**
 * 消息频道
 * @author luoshengsha
 *
 * 2016年6月17日-下午2:48:28
 */
@RestController
public class MessageController {
	/** 日志记录 **/
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private SimpMessagingTemplate smtemp;
	@Resource
	private UserService userService;
	@Resource
	private ChatRecordService chatRecordService;
	@Resource
	private ClusChatRecordService clusRecordService;
	@Resource
	private RecentMessageService recMsgService;
	
	/**
	 * 好友间聊天
	 * @param message
	 * @param channel
	 * @param from
	 * @param to
	 */
	@MessageMapping(value="chat/p2p/sendmsg.htm")
	//@SendTo("/app/chat/get_all_msg.htm")
	public void sendP2pMsg(String message, @Header("channel") String channel, @Header("from")String from, 
			@Header("to")String to, @Header("token")String token) {
		try {
			User fromUser = userService.find(Integer.parseInt(from));
			User toUser = userService.find(Integer.parseInt(to));
			
			ChatRecordVo rv = new ChatRecordVo();
			rv.setFrom(fromUser.getNickname() == null ? fromUser.getName() : fromUser.getNickname());
			rv.setAvatar(toUser.getAvatar() == null ? "" : toUser.getAvatar().getImgpath());
			
			String msg = message.split(":")[1].replaceAll("\"", "");
			rv.setMessage(msg.substring(0, msg.length()-1));
			rv.setTime(DateUtil.date2Str());
			
			//广播聊天内容
			smtemp.convertAndSend("/app/chat/"+channel+"/get_msg.htm",new ObjectMapper().writeValueAsString(rv));
			
			//保存聊天内容
			saveChatRecord(message, channel, fromUser, toUser);
			
			//广播最近聊天消息
			smtemp.convertAndSend("/app/chat/get_recent_msg.htm",getRecentMessage(token));
			
			//广播未读聊天消息
			List<RecentMessageVo> unreadMessages = getUnreadMessage(token);
			if(!unreadMessages.isEmpty()) {
				smtemp.convertAndSend("/app/chat/get_unread_msg.htm",unreadMessages);
			}
		} catch (Exception e) {
			logger.error("保存聊天记录失败", e);
		}
	}

	/**
	 * 保存好友聊天记录
	 * @param message
	 * @param channel
	 * @param fromUser
	 * @param toUser
	 */
	private void saveChatRecord(String message, String channel, User fromUser, User toUser) {
		ChatRecord record = new ChatRecord();
		record.setFromUser(fromUser);
		record.setToUser(toUser);
		record.setContent(message);
		record.setChannel(channel);
		record.setCreateTime(new Date());
		chatRecordService.save(record);
	}
	
	/**
	 * 群组聊天
	 * @param message
	 * @param channel
	 * @param from
	 * @param to
	 */
	@MessageMapping(value="chat/cluster/sendmsg.htm")
	//@SendTo("/app/chat/get_all_msg.htm")
	public void sendClusterMsg(String message, @Header("channel") String channel, @Header("from")String from, 
			@Header(value="to",required=false)String to,@Header(value="token",required=true)String token) {
		try {
			User fromUser = userService.find(Integer.parseInt(from));
			User toUser = null;
			
			ChatRecordVo rv = new ChatRecordVo();
			rv.setFrom(fromUser.getNickname() == null ? fromUser.getName() : fromUser.getNickname());
			
			if(!StringUtils.isEmpty(to)) {
				toUser = userService.find(Integer.parseInt(to));
			}
			rv.setAvatar(fromUser.getAvatar() == null ? "" : fromUser.getAvatar().getImgpath());
			String msg = message.split(":")[1].replaceAll("\"", "");
			rv.setMessage(msg.substring(0, msg.length()-1));
			rv.setTime(DateUtil.date2Str());
			
			//广播聊天内容
			smtemp.convertAndSend("/app/chat/"+channel+"/get_msg.htm",new ObjectMapper().writeValueAsString(rv));
			
			//保存聊天内容
			saveClusChatRecord(message, channel, fromUser, toUser);
			
			//广播最近聊天消息
			smtemp.convertAndSend("/app/chat/get_recent_msg.htm",getRecentMessage(token));
			
			//广播未读聊天消息
			List<RecentMessageVo> unreadMessages = getUnreadMessage(token);
			if(!unreadMessages.isEmpty()) {
				smtemp.convertAndSend("/app/chat/get_unread_msg.htm",unreadMessages);
			}
			
		} catch (Exception e) {
			logger.error("保存聊天记录失败", e);
		}
	}

	/**
	 * 保存群聊记录
	 * @param message
	 * @param channel
	 * @param fromUser
	 * @param toUser
	 */
	private void saveClusChatRecord(String message, String channel, User fromUser, User toUser) {
		ClusChatRecord record = new ClusChatRecord();
		record.setFromUser(fromUser);
		record.setToUser(toUser);
		record.setContent(message);
		record.setChannel(channel);
		record.setCreateTime(new Date());
		clusRecordService.save(record);
	}
	
	/**
	 * 订阅最近聊天记录
	 * @return
	 */
	@SubscribeMapping("/chat/get_recent_msg.htm")
	public List<RecentMessageVo> getRecentMsg(@Header(value="token",required=true)String token) {
		return getRecentMessage(token);
	}
	
	/**
	 * 获取最近聊天记录
	 * @param request
	 * @return
	 */
	private List<RecentMessageVo> getRecentMessage(String token) {
		try {
			String id = (String)WebUtil.getPrincipal(token).get("id");
			List<RecentMessageVo> recentMessageVos = new ArrayList<RecentMessageVo>();
			List<RecentMessage> recentMessages = recMsgService.getMessages(userService.find(Integer.parseInt(id)));
			for(RecentMessage m : recentMessages) {
				recentMessageVos.add(BeanConvertUtil.convertRecentMessage(m));
			}
			return recentMessageVos;
		} catch (Exception e) {
			logger.error("获取最近聊天记录失败", e);
			return null;
		}
	}
	
	/**
	 * 订阅未读消息
	 * @param token
	 * @return
	 */
	@SubscribeMapping("/chat/get_unread_msg.htm")
	public List<RecentMessageVo> getUnreadMessage(@Header(value="token",required=true)String token) {
		String id = WebUtil.getPrincipal(token).get("id");
		List<RecentMessageVo> unreadMessageVos = new ArrayList<RecentMessageVo>();
		List<RecentMessage> unreadMessages = recMsgService.getUnreadMessage(userService.find(Integer.parseInt(id)));
		
		for(RecentMessage m : unreadMessages) {
			unreadMessageVos.add(BeanConvertUtil.convertRecentMessage(m));
		}
	
		return unreadMessageVos;
	}
	
	/**
	 * 获取好友聊天记录
	 * @param channelid
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value="/center/get_chat_records")
	public ReturnObject getChatRecord(String channelid,int pageNo,int pagesize) {
		ReturnObject obj = new ReturnObject();
		
		try {
			PageRequest page = new PageRequest(pageNo-1,pagesize,new Sort(Direction.DESC, "createTime"));
			Page<ChatRecord> recordpage = chatRecordService.getByChannel(channelid, page);
			
			List<ChatRecordVo> chatRecordVos = new ArrayList<ChatRecordVo>();
			List<ChatRecord> chatRecords = recordpage.getContent();
			for(ChatRecord record : chatRecords) {
				
				ChatRecordVo rv = BeanConvertUtil.convertChatRecord(record);
				
				chatRecordVos.add(rv);
			}
			obj.setData(chatRecordVos);
			obj.setStatus(1);
			obj.setMessage("获取成功");
		} catch (Exception e) {
			obj.setStatus(0);
			obj.setMessage("获取失败");
			logger.error("获取好友聊天记录失败",e);
		}
		
		return obj;
	}
	
	/**
	 * 获取群组聊天记录
	 * @param channelid
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value="/center/get_cluschat_records")
	public ReturnObject getClusChatRecord(String channelid,int pageNo,int pagesize) {
		ReturnObject obj = new ReturnObject();
		
		try {
			PageRequest page = new PageRequest(pageNo-1,pagesize,new Sort(Direction.DESC, "createTime"));
			Page<ClusChatRecord> recordpage = clusRecordService.getByChannel(channelid, page);
			
			List<ChatRecordVo> chatRecordVos = new ArrayList<ChatRecordVo>();
			List<ClusChatRecord> chatRecords = recordpage.getContent();
			for(ClusChatRecord record : chatRecords) {
				
				ChatRecordVo rv = BeanConvertUtil.convertClusChatRecord(record);
				
				chatRecordVos.add(rv);
			}
			obj.setData(chatRecordVos);
			obj.setStatus(1);
			obj.setMessage("获取成功");
		} catch (Exception e) {
			obj.setStatus(0);
			obj.setMessage("获取失败");
			logger.error("获取好友聊天记录失败",e);
		}
		
		return obj;
	}
	
	/**
	 * 读消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/center/message/read")
	public ReturnObject readMessage(@RequestParam(value="id",required=true) String id) {
		ReturnObject obj = new ReturnObject();
		
		try {
			
			recMsgService.readMessage(id);
			
			obj.setStatus(1);
			obj.setMessage("成功");
		} catch (Exception e) {
			obj.setStatus(0);
			obj.setMessage("失败");
			logger.error("修改最近消息状态失败",e);
		}
		
		return obj;
	}
}
