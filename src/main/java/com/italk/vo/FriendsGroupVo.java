package com.italk.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友分组vo
 * @author luoshengsha
 *
 * 2016年6月23日-上午9:59:44
 */
public class FriendsGroupVo {
	private String id;
	private String name;
	private List<FriendVo> members = new ArrayList<FriendVo>();
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
	public List<FriendVo> getMembers() {
		return members;
	}
	public void setMembers(List<FriendVo> members) {
		this.members = members;
	}
}
