package com.zy.cms.vo;

import java.io.Serializable;

public class MessageVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -158700466341556121L;
	
	private String id;
	private String msg;
	private boolean isRead;
	private String createTm;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getCreateTm() {
		return createTm;
	}
	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
	
}
