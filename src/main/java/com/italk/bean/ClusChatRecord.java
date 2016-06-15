package com.italk.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 群聊记录
 * @author luoshengsha
 *
 * 2016年6月13日-下午8:20:04
 */
@Entity
@Table(name="t_cluschatrecord")
public class ClusChatRecord {
	/** id **/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/** 内容 **/
	@Column(nullable=false)
	private String content;
	
	/** 聊天发起人 **/
	@OneToOne
	@JoinColumn(name="fromuser_id",nullable=false)
	private User fromUser;
	
	/** 接收人 **/
	@OneToOne
	@JoinColumn(name="touser_id")
	private User toUser;
	
	/** 聊天时间 **/
	@Column(nullable=false)
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
