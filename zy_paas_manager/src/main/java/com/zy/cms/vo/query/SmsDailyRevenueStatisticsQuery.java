package com.zy.cms.vo.query;

import java.util.List;

/**
 * 数据分析,查询器
 * 
 * @author allen.yuan
 * @date 2016-9-02
 */
public class SmsDailyRevenueStatisticsQuery {

	private String apiAccount;//客户apiAccount
	private String businessName;//客户名称
	private String merchantPhone;//客户手机号
	private String smsCategory;//短信类型
	private String dateTimeStart;//日期开始时间
	private String dateTimeEnd;//日期结束时间
	private List apiAccounts;// 账户绑定的客户
	private List channelIds;//通道ID集合
	
	private String channelId; // 通道ID
	private String channelMainCode;// 通道编号
	private String channelName; // 通道名称
	
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	
	public String getApiAccount() {
		return (apiAccount == null) ? "" : apiAccount.trim();
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
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

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSmsCategory() {
		return smsCategory;
	}

	public void setSmsCategory(String smsCategory) {
		this.smsCategory = smsCategory;
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

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelMainCode() {
		return channelMainCode;
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(List channelIds) {
		this.channelIds = channelIds;
	}
	
	
	
}
