package com.zy.cms.vo.query;

import java.util.List;

/**
 * 数据分析,查询器
 * 
 * @author allen.yuan
 * @date 2016-9-02
 */
public class ChannelSummaryQuery {
	
	private String channelMainCode;// 通道编号
	    
	private String channelSmsId;// 通道ID(通道方生成)
	    
    private String channelName; // 通道名称

    private Integer channelType; // 通道类型(1:通知;2:验证码;3:营销;4:通知、验证码;5:通知、验证码、营销)
    
	private String dateTimeStart;//时间开始
	
	private String dateTimeEnd;//时间结束
	
	private List<String> channelSmsIds;// 通道ID集合(通道方生成)

	private List apiAccounts;//账户绑定的客户
	
	private Integer pageNum;// 页号
	
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数

	public String getChannelMainCode() {
		return channelMainCode;
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public String getChannelSmsId() {
		return channelSmsId == null ? "" : channelSmsId.trim();
	}

	public void setChannelSmsId(String channelSmsId) {
		this.channelSmsId = channelSmsId;
	}

	public String getChannelName() {
		return channelName == null ? "" : channelName.trim();
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(String dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public List<String> getChannelSmsIds() {
		return channelSmsIds;
	}

	public void setChannelSmsIds(List<String> channelSmsIds) {
		this.channelSmsIds = channelSmsIds;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
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
	
}
