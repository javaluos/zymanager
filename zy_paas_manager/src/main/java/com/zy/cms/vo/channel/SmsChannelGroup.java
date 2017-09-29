package com.zy.cms.vo.channel;

import java.util.List;

public class SmsChannelGroup {

	private String id;

	private String groupName;

	private Integer groupType;

	private String remark;

	private Integer status;

	private String createTime;

	private String updateTime;

	List<SmsChannelGroupBind> smsChannelGroupBindList;

	public SmsChannelGroup() {

	}

	public SmsChannelGroup(String id, String groupName, Integer groupType, String remark, Integer status,
			List<SmsChannelGroupBind> smsChannelGroupBindList) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.groupType = groupType;
		this.remark = remark;
		this.status = status;
		this.smsChannelGroupBindList = smsChannelGroupBindList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<SmsChannelGroupBind> getSmsChannelGroupBindList() {
		return smsChannelGroupBindList;
	}

	public void setSmsChannelGroupBindList(List<SmsChannelGroupBind> smsChannelGroupBindList) {
		this.smsChannelGroupBindList = smsChannelGroupBindList;
	}

	@Override
	public String toString() {
		return "SmsChannelGroup [id=" + id + ", groupName=" + groupName + ", groupType=" + groupType + ", remark="
				+ remark + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", smsChannelGroupBindList=" + smsChannelGroupBindList + "]";
	}

}