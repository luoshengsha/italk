package com.italk.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 好友分组
 * 
 * @author luoshengsha
 *
 *         2016年6月13日-下午7:56:52
 */
@Entity
@Table(name = "t_friendsgroup")
public class FriendsGroup {
	/** id **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/** 分组uuid **/
	@Column(nullable=false,length=50)
	private String uuid;

	/** 分组名称 **/
	@Column(nullable = false)
	private String name;

	/** 创建者 **/
	@OneToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "user_id", nullable = false)
	private User creater;

	/** 组员 **/
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.LAZY)
	@JoinTable(name = "t_friendsgroup_user", joinColumns = {
			@JoinColumn(name = "group_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", referencedColumnName = "id")})
	private Set<User> members;
	
	/** 是否默认分组（默认分组为“我的好友”） **/
	@Column(nullable=false,columnDefinition="INT default 0")
	private boolean isDefault;
	
	/** 创建时间 **/
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
