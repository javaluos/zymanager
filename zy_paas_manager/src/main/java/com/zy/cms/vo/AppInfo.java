package com.zy.cms.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AppInfo {
	private Integer id;

	private String apiAccount;

	private String appId;

	private String appName;

	private String appToken;

	private String status;

	private Short reason;

	private String businessIds;

	private Integer balance;

	private String industry;

	private Date updateTime;

	private Date createTime;

	private String mobile;

	// 话单地址url列表
	private List<CdrPushAddr> addrList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Short getReason() {
		return reason;
	}

	public void setReason(Short reason) {
		this.reason = reason;
	}

	public String getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public List<CdrPushAddr> getAddrList() {
		return addrList;
	}

	public void setAddrList(List<CdrPushAddr> addrList) {
		this.addrList = addrList;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
