package com.zy.cms.vo;


public class MerchantConsumeBillVo {
    private Integer id;
    private String merchantAccount;
    private Integer appId;
    private String appName;
    private Integer smsType;
    private String smsTypeName;
    private Integer smsNum;
    private String createTime;
    private String avgPrice;
    private String allPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMerchantAccount() {
		return merchantAccount;
	}
	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public Integer getSmsType() {
		return smsType;
	}
	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}
	public String getSmsTypeName() {
		return smsTypeName;
	}
	public void setSmsTypeName(String smsTypeName) {
		this.smsTypeName = smsTypeName;
	}
	public Integer getSmsNum() {
		return smsNum;
	}
	public void setSmsNum(Integer smsNum) {
		this.smsNum = smsNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}
    
}
