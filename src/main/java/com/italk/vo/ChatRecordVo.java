package com.italk.vo;

/**
 * 好友聊天记录vo
 * @author luoshengsha
 *
 * 2016年6月24日-下午3:58:14
 */
public class ChatRecordVo {
	private String from;
	private String avatar;
	private String message;
	private String time;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
