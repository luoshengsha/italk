package com.italk.vo;

/**
 * 返回对象
 * @author luoshengsha
 *
 * 2016年6月20日-下午8:07:10
 */
public class ReturnObject {
	/** 消息 **/
	private String message;
	/** 回传的数据 **/
	private Object data = "";
	/** 状态 **/
	private int status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
