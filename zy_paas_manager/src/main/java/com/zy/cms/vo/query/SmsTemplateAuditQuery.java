package com.zy.cms.vo.query;

import java.util.List;

public class SmsTemplateAuditQuery {

	private String businessName;// 客户名称

	private String merchantPhone;// 手机号码

	private String authResultTimeStart;// 审核结果开始时间

	private String authResultTimeEnd;// 审核结果结束时间

	private Integer authStatus;// 审核状态 (1:审核通过,2:待审核,3:审核中,4:审核失败,5:取消审核)

	private String authUser;// 审核人

	private String category; // 模板类型

	private String id; // 模板ID

	private Integer pageNum;// 页号

	private Integer pageSize;// 每页数量

	private Integer pageCount;// 统计页数

	private Integer isLocked = 1;

	private Integer pageOffset = 0;// 分页的开始值

	private List apiAccounts;// 账户绑定的客户

	private String content;// 短信模板内容。

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getAuthResultTimeStart() {
		return authResultTimeStart;
	}

	public void setAuthResultTimeStart(String authResultTimeStart) {
		this.authResultTimeStart = authResultTimeStart;
	}

	public String getAuthResultTimeEnd() {
		return authResultTimeEnd;
	}

	public void setAuthResultTimeEnd(String authResultTimeEnd) {
		this.authResultTimeEnd = authResultTimeEnd;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPageNum() {
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

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content == null ? "" : content.trim();
	}

	public void setContent(String content) {
		this.content = content;
	}
}
