package com.italk.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 上传的文件
 * @author luoshengsha
 *
 * 2016年6月27日-上午11:39:03
 */
@Entity
@Table(name="t_uploadfile")
public class UploadFile {
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
	
	/** 文件路径 **/
	@Column(nullable=false)
	private String filepath;
	
	/** 上传时间 **/
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;
	
	/** 上传的用户 **/
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	/** 文件类型 **/
	private String type; //application/octet-stream text/plain  image/jpeg
	
	/** 群组 **/
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH })
	@JoinColumn(name="cluster_id")
	private Cluster cluster;

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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	
}
