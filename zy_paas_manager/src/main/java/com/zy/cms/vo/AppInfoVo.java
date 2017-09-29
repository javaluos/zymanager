package com.zy.cms.vo;

import java.io.Serializable;

public class AppInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643994854704609407L;

	/**
	 * ID
	 */
	private String id;
	/**
	 * ACCOUNT NAME
	 */
	private String merchantAccount;

	/**
	 * appid
	 */
	private String appId;
	/**
	 * app名称
	 */
	private String appName;
	/**
	 * app token
	 */
	private String token;
	
	
	private String signer;
	/**
	 * 模板ID
	 */
	private String smsTemplate;
	
	/**
	 * 行业名称
	 */
	private String industryNm;
	/**
	 * 审核状态
	 */
	private String status;
	/**
	 * 审核备注
	 */
	private String reason;
	/**
	 * 创建时间
	 */
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	public String getIndustryNm() {
		return industryNm;
	}

	public void setIndustryNm(String industryNm) {
		this.industryNm = industryNm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
