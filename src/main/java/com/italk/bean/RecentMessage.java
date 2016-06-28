package com.italk.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 最近聊天记录
 * @author luoshengsha
 *
 * 2016年6月24日-下午1:59:19
 */
@Entity
@Table(name="t_recentmessage")
public class RecentMessage {
	/** id **/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/** uuuid **/
	@Column(nullable=false,length=50)
	private String uuid;
	
	/** 用户 **/
	@ManyToOne
	@JoinColumn(name="user_id",nullable=true)
	private User user;
	
	/** 群组 **/
	@ManyToOne
	@JoinColumn(name="cluster_id",nullable=true)
	private Cluster cluster;
	
	/** 类型 1:好友消息  0:群消息**/
	@Column(nullable=false, length=1)
	private int type;
	
	/** 接收用户 **/
	@Column(name="touser_id",nullable=false)
	private User toUser;
	
	/** 内容 **/
	@Column(nullable=false)
	private String content;
	
	/** 时间 **/
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
