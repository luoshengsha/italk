package com.italk.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClusterVo {
	private String id;
	private String name;
	private Set<String> avatars = new HashSet<String>();
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
	public Set<String> getAvatars() {
		return avatars;
	}
	public void setAvatars(Set<String> avatars) {
		this.avatars = avatars;
	}
	public List<FriendVo> getMembers() {
		return members;
	}
	public void setMembers(List<FriendVo> members) {
		this.members = members;
	}
}
