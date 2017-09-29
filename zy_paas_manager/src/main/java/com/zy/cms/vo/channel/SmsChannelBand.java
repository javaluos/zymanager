package com.zy.cms.vo.channel;

import java.util.Date;

public class SmsChannelBand {
	private Integer id;

	private String apiAccount;

	private String channelId;

	private Integer channelScore;

	private Integer thresholdValue;

	private String remark;

	private Integer status;

	// (0:测试;1:正式)
	private Integer useType = 1;

	private Date createTime;

	private Date updateTime;

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
		this.apiAccount = apiAccount == null ? null : apiAccount.trim();
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId == null ? null : channelId.trim();
	}

	public Integer getChannelScore() {
		return channelScore;
	}

	public void setChannelScore(Integer channelScore) {
		this.channelScore = channelScore;
	}

	public Integer getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUseType() {
		if (useType == null) {
			useType = 1;
		}
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}
}