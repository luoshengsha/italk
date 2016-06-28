package com.italk.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户token
 * @author luoshengsha
 *
 * 2016年6月21日-下午5:10:12
 */
@Entity
@Table(name="t_usertoken")
public class UserToken {
	/** id **/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/** 所属用户 **/
	@OneToOne
	@JoinColumn(name="user_id",insertable=true,unique=true)
	private User user;
	
	/** 用户对应的token **/
	@Column(nullable=false)
	private String token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
