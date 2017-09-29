package com.zy.cms.vo;

import java.io.Serializable;

public class TemplateInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3913131841796423008L;
	private String tempId;
	private String content;
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
