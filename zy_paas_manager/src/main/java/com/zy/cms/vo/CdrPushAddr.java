package com.zy.cms.vo;

import java.util.Date;

public class CdrPushAddr implements Cloneable {

	private String apiAccount;

	private String appId;

	private Integer businessId;

	private String ips;

	private String statusPushAddr;
	
	private String statisticAddr;//上行地址

	private Date createTime;

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

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getStatusPushAddr() {
		return statusPushAddr;
	}

	public void setStatusPushAddr(String statusPushAddr) {
		this.statusPushAddr = statusPushAddr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getStatisticAddr() {
		return statisticAddr;
	}

	public void setStatisticAddr(String statisticAddr) {
		this.statisticAddr = statisticAddr;
	}

	@Override
	public CdrPushAddr clone() {
		
		CdrPushAddr obj = null;
		try {
			obj = (CdrPushAddr) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
