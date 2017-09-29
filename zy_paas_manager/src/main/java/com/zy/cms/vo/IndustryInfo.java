package com.zy.cms.vo;

import java.util.Date;

public class IndustryInfo {
	
	private Integer id;

	private Integer pid;

	private String industryName;

	private Date updateTime;

	private Date ctreateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCtreateTime() {
		return ctreateTime;
	}

	public void setCtreateTime(Date ctreateTime) {
		this.ctreateTime = ctreateTime;
	}
}