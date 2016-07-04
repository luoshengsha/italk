package com.italk.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Column(nullable=false,length=10)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	/** 注册时间 **/
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date registTime;
	
	/** 最后一次登录时间 **/
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	public User() {}
	
	public User(int id) {
		this.id = id;
	}
	
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
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
