package com.zy.cms.vo.channel;

import java.util.Date;

public class SmsChannelGroupBind {

	private Integer id;

	private String channelGroupId;

	private String channelId;

	private Integer channelScore;

	private Integer thresholdValue;

	private String remark;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	public SmsChannelGroupBind() {

	}

	public SmsChannelGroupBind(String channelGroupId, String channelId, Integer channelScore, Integer thresholdValue,
			String remark, Integer status) {
		super();
		this.channelGroupId = channelGroupId;
		this.channelId = channelId;
		this.channelScore = channelScore;
		this.thresholdValue = thresholdValue;
		this.remark = remark;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelGroupId() {
		return channelGroupId;
	}

	public void setChannelGroupId(String channelGroupId) {
		this.channelGroupId = channelGroupId == null ? null : channelGroupId.trim();
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

	@Override
	public String toString() {
		return "SmsChannelGroupBind [id=" + id + ", channelGroupId=" + channelGroupId + ", channelId=" + channelId
				+ ", channelScore=" + channelScore + ", thresholdValue=" + thresholdValue + ", remark=" + remark
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}