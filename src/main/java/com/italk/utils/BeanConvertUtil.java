package com.italk.utils;

import java.util.ArrayList;
import java.util.List;

import com.italk.bean.ChatRecord;
import com.italk.bean.ClusChatRecord;
import com.italk.bean.Friend;
import com.italk.bean.RecentMessage;
import com.italk.bean.User;
import com.italk.vo.ChatRecordVo;
import com.italk.vo.FriendVo;
import com.italk.vo.RecentMessageVo;

/**
 * bean转换工具
 * @author luoshengsha
 *
 * 2016年6月28日-下午1:10:17
 */
public class BeanConvertUtil {

	/**
	 * 转换好友聊天记录
	 * @param record
	 * @return
	 */
	public static ChatRecordVo convertChatRecord(ChatRecord record) {
		ChatRecordVo rv = new ChatRecordVo();
		rv.setFrom(record.getFromUser().getNickname() == null ? record.getFromUser().getName() : record.getFromUser().getNickname());
		rv.setAvatar(record.getFromUser().getAvatar() == null ? "" : record.getFromUser().getAvatar().getImgpath());
		
		String message = record.getContent().split(":")[1].replaceAll("\"", "");
		rv.setMessage(message.substring(0, message.length()-1));
		rv.setTime(DateUtil.date2Str(record.getCreateTime()));
		
		return rv;
	}
	
	/**
	 * 转换群聊记录
	 * @param record
	 * @return
	 */
	public static ChatRecordVo convertClusChatRecord(ClusChatRecord record) {
		ChatRecordVo rv = new ChatRecordVo();
		rv.setFrom(record.getFromUser().getNickname() == null ? record.getFromUser().getName() : record.getFromUser().getNickname());
		rv.setAvatar(record.getFromUser().getAvatar() == null ? "" : record.getFromUser().getAvatar().getImgpath());
		
		String message = record.getContent().split(":")[1].replaceAll("\"", "");
		rv.setMessage(message.substring(0, message.length()-1));
		rv.setTime(DateUtil.date2Str(record.getCreateTime()));
		return rv;
	}
	
	/**
	 * 转换最近聊天消息
	 * @param message
	 * @return
	 */
	public static RecentMessageVo convertRecentMessage(RecentMessage message) {
		RecentMessageVo vo = new RecentMessageVo();
		vo.setId(message.getUuid());
		if(message.getType() ==1) { //好友聊天
			vo.setName(message.getUser().getNickname() == null ? message.getUser().getName() : message.getUser().getNickname());
			
			int userid = message.getUser().getId();
			int touserid = message.getToUser().getId();
			if(userid > touserid) {
				vo.setChannel(touserid + "-" + userid);
			} else {
				vo.setChannel(userid + "-" + touserid);
			}
			
			String avatar = message.getUser().getAvatar() == null ? "" : PropertiesUtil.pic_url + message.getUser().getAvatar().getImgpath();
			vo.setAvatar(avatar);
		} else {
			vo.setName(message.getCluster().getName());
			vo.setChannel(message.getCluster().getUuid());
			
			int i=0;
			List<String> avatars = new ArrayList<String>();
			
			for(User member : message.getCluster().getMembers()) {
				if(member.getAvatar() != null) {
					String avatar = member.getAvatar() == null ? "" : PropertiesUtil.pic_url + member.getAvatar().getImgpath();
					avatars.add(avatar);
					i++;
				}
				
				if(i == 4) {//只取四个头像
					break;
				}
			}
			
			vo.setAvatar(avatars);
		}
		vo.setTime(DateUtil.date2Str(message.getCreateTime(), "HH:mm:ss"));
		vo.setType(message.getType());
		vo.setContent(message.getContent());
		vo.setAmount(message.getMessageAmount());
		
		return vo;
	}
	
	/**
	 * 转换好友
	 * @param friend
	 * @return
	 */
	public static FriendVo convertFriend(Friend friend) {
		FriendVo vo = new FriendVo();
		vo.setId(friend.getFriend().getId());
		
		String name = friend.getRemark();
		if(name == null) {
			friend.getFriend().getNickname();
		}
		if(name == null) {
			friend.getFriend().getName();
		}
		vo.setName(name);
		vo.setAvatar(friend.getFriend().getAvatar() == null ? "" : PropertiesUtil.pic_url+friend.getFriend().getAvatar());
		vo.setStatus(friend.getFriend().getStatus());
		
		return vo;
	}
}
