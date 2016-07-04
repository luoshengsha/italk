package com.italk.vo;

import com.italk.bean.UserStatus;

/**
 * 好友vo
 * @author luoshengsha
 *
 * 2016年6月30日-下午2:43:26
 */
public class FriendVo {
	/** id **/
	private int id;
	
	/** 名称 **/
	private String name;
	
	/** 头像 **/
	private String avatar;
	
	/** 状态 **/
	private UserStatus status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
