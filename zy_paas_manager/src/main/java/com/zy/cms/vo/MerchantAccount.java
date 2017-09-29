package com.zy.cms.vo;

import java.util.Date;

public class MerchantAccount {

	private String apiAccount;

	private String apikey;
	
	private String appId;

	private String merchantAccount;

	private String merchantPhone;

	private String merchantEmail;

	private String merchantPwd;

	private String businessName;

	private Integer merchantType; // 商户类型(1:个人,2:企业)

	private Integer authFlag; // 认证标识(-1:未认证,1:已认证)

	private Short isLocked;

	private String comment;

	private Long currentFee;// 当前余额

	private Date feeExpireTime;

	private Date updateTime;

	private Date createTime;

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount == null ? null : merchantAccount.trim();
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

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
		this.merchantPhone = merchantPhone == null ? "" : merchantPhone.trim();
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail == null ? null : merchantEmail.trim();
	}

	public Short getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Short isLocked) {
		this.isLocked = isLocked;
	}

	public String getMerchantPwd() {
		return merchantPwd;
	}

	public void setMerchantPwd(String merchantPwd) {
		this.merchantPwd = merchantPwd == null ? null : merchantPwd.trim();
	}

	public Long getCurrentFee() {
		return currentFee;
	}

	public void setCurrentFee(Long currentFee) {
		this.currentFee = currentFee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFeeExpireTime() {
		return feeExpireTime;
	}

	public void setFeeExpireTime(Date feeExpireTime) {
		this.feeExpireTime = feeExpireTime;
	}

	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Integer getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(Integer authFlag) {
		this.authFlag = authFlag;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "MerchantAccount [apiAccount=" + apiAccount + ", merchantAccount=" + merchantAccount + ", apikey="
				+ apikey + ", businessName=" + businessName + ", merchantPhone=" + merchantPhone + ", merchantEmail="
				+ merchantEmail + ", isLocked=" + isLocked + ", authFlag=" + authFlag + ", merchantPwd=" + merchantPwd
				+ ", currentFee=" + currentFee + ", comment=" + comment + ", updateTime=" + updateTime + ", createTime="
				+ createTime + ", feeExpireTime=" + feeExpireTime + "]";
	}
	
}