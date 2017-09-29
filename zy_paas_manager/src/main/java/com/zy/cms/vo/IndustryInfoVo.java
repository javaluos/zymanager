package com.zy.cms.vo;

import java.io.Serializable;

public class IndustryInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7248142610995649467L;
	private String id;

    private String pid;

    private String industryName;

    private String updateTime;

    private String ctreateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCtreateTime() {
		return ctreateTime;
	}

	public void setCtreateTime(String ctreateTime) {
		this.ctreateTime = ctreateTime;
	}
}
