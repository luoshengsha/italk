package com.italk.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 头像
 * @author luoshengsha
 *
 * 2016年6月13日-下午7:34:16
 */
@Entity
@Table(name="t_avatar")
public class Avatar {
	/** id **/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/** uuid **/
	@Column(nullable=false,length=40)
	private String uuid;
	
	/** 名称 **/
	@Column(nullable=false)
	private String name;
	
	/** 图片路径 **/
	@Column(nullable=false)
	private String imgpath;
	
	/** 用户 **/
	@OneToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
