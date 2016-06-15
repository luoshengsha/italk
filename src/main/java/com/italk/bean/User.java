package com.italk.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户
 * @author luoshengsha
 *
 * 2016年6月13日-下午8:13:56
 */
@Entity
@Table(name = "t_users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/** 用户uuid **/
	@Column(unique=true,nullable=false,length=50)
	private String uuid;
	
	/** 用户名 **/
	@Column(nullable=false,length=40)
	private String name;
	
	/** 昵称 **/
	@Column(length=15)
	private String nickname;
	
	/** 密码 **/
	@Column(nullable=false)
	private String password;
	
	/** 头像 **/
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="avatar_id")
	private Avatar avatar;
	
	/** 用户状态 **/
	private UserStatus status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
