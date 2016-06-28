package com.italk.vo;

/**
 * 最近聊天记录vo
 * @author luoshengsha
 *
 * 2016年6月28日-下午1:15:21
 */
public class RecentMessageVo {
	/** id **/
	private String id;
	/** 名称 **/
	private String name;
	/** 频道 **/
	private String channel;
	/** 类型 1：好友消息 0：群组消息 **/
	private int type;
	/** 内容 **/
	private String content;
	/** 时间 **/
	private String time;
	/** 头像 **/
	private Object avatar;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Object getAvatar() {
		return avatar;
	}
	public void setAvatar(Object avatar) {
		this.avatar = avatar;
	}
}
