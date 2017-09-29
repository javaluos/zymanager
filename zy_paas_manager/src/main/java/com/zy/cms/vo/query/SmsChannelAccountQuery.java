package com.zy.cms.vo.query;

/**
 * 通道查询器
 * 
 * @author allen.yuan
 * @date 2017-1-21
 */
public class SmsChannelAccountQuery {

	private String channelName; // 通道名称
	private String channelId;// 通道ID
	private String merchantPhone; //客户账号
	private String businessName; //客户名称
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数

	public String getChannelId() {
		return channelId == null ? "" : channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName == null ? "" : channelName.trim();
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getBusinessName() {
		return businessName == null ? "" : businessName.trim();
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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
