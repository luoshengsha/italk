package com.italk.vo;

/**
 * 文件vo
 * @author luoshengsha
 *
 * 2016年6月27日-下午9:11:37
 */
public class UploadFileVo {
	/** id **/
	private String id;
	/** 名称 **/
	private String name;
	/** 类型 **/
	private String type;
	/** 文件路径 **/
	private String path;
	/** 上传时间 **/
	private String createtime;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
