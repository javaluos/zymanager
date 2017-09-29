package com.zy.cms.vo.query;

import java.util.List;

public class SmsChannelGroupQuery {

	private String channelGroupName; // 通道名称
	private String channelId;//通道id
	private List<String> channelGroupIds;//通道ID
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数

	public String getChannelGroupName() {
		return channelGroupName == null ? "" : channelGroupName.trim();
	}

	public void setChannelGroupName(String channelGroupName) {
		this.channelGroupName = channelGroupName;
	}

	public Integer getPageNum() {
		if (pageNum == null) {
			pageNum = 0;
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if (pageSize == null) {
			pageSize = 0;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageCount() {
		if (pageCount == null) {
			pageCount = 0;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public List<String> getChannelGroupIds() {
		return channelGroupIds;
	}

	public void setChannelGroupIds(List<String> channelGroupIds) {
		this.channelGroupIds = channelGroupIds;
	}
	
	
}
