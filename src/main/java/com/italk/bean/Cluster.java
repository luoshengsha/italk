package com.italk.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 群组
 * @author luoshengsha
 *
 * 2016年6月13日-下午7:58:59
 */

@Entity
@Table(name="t_cluster")
public class Cluster {
	/** id **/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/** uuid **/
	@Column(nullable=false)
	public String uuid;
	
	/** 群名称 **/
	@Column(nullable=false)
	private String name;
	
	/** 群主 **/
	@OneToOne
	@JoinColumn(name="owner_id",nullable=false)
	private User owner;
	
	/** 创建时间 **/
	@Column(nullable=false)
	private Date createTime;
	
	/** 群成员 **/
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)
	@JoinTable(name = "t_cluster_member", joinColumns = {
			@JoinColumn(name = "cluster_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "member_id", referencedColumnName = "id") })
	private List<Member> members = new ArrayList<Member>();
	
	/** 文件 **/
	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, 
			CascadeType.REMOVE },mappedBy="cluster")
	private List<UploadFile> files = new ArrayList<UploadFile>();

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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<UploadFile> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFile> files) {
		this.files = files;
	}
}
