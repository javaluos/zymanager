package com.zy.cms.vo.query;

import java.util.List;

public class SmsReplyQuery {

	private String apiAccount;
	private String merchantPhone;
	private String channelId;
	private String mobile;
	private String starttime;// 开始时间
	private String endtime;// 结束时间
	private Integer status;
	private String content;// 上行内容
	private String ext_number;// 拓展号
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	private List apiAccounts;// 账户绑定的客户

	public String getApiAccount() {
		return apiAccount == null ? "" : apiAccount.trim();
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getChannelId() {
		return channelId == null ? "" : channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getMobile() {
		return mobile == null ? "" : mobile.trim();
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStarttime() {
		return starttime == null ? "" : starttime.trim();
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime == null ? "" : endtime.trim();
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content == null ? "" : content.trim();
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExt_number() {
		return ext_number == null ? "" : ext_number.trim();
	}

	public void setExt_number(String ext_number) {
		this.ext_number = ext_number;
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

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

}
