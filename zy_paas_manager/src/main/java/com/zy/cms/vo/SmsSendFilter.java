package com.zy.cms.vo;

import java.util.Date;

public class SmsSendFilter {
	
    private String smsId;

    private String apiAccount;

    private String appId;

    private String smsCategory;

    private String smsSignerId;

    private String smsTemplateId;

    private String receiveMobile;

    private String smsContent;

    private Integer smsNums;

    private Integer smsFee;

    private Integer smsType;

    private Integer resource;

    private Integer protocolType;

    private String province;

    private String city;

    private String carriers;

    private String blackContent;

    private Integer status;

    private Date createTime;

    private Date updateTime;
    
    
    /**
	 * 客户透传数据
	 */
	private String userData = "";
	/**
	 * 推送状态报告地址
	 */
	private String statusPushAddr = "";
	/**
	 * 客户透传ID
	 */
	private String uId="";
	
	private String signContent;//短信内容

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId == null ? null : smsId.trim();
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getSmsCategory() {
        return smsCategory;
    }

    public void setSmsCategory(String smsCategory) {
        this.smsCategory = smsCategory == null ? null : smsCategory.trim();
    }

    public String getSmsSignerId() {
        return smsSignerId;
    }

    public void setSmsSignerId(String smsSignerId) {
        this.smsSignerId = smsSignerId == null ? null : smsSignerId.trim();
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId == null ? null : smsTemplateId.trim();
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile == null ? null : receiveMobile.trim();
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    public Integer getSmsNums() {
        return smsNums;
    }

    public void setSmsNums(Integer smsNums) {
        this.smsNums = smsNums;
    }

    public Integer getSmsFee() {
        return smsFee;
    }

    public void setSmsFee(Integer smsFee) {
        this.smsFee = smsFee;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCarriers() {
        return carriers;
    }

    public void setCarriers(String carriers) {
        this.carriers = carriers == null ? null : carriers.trim();
    }

    public String getBlackContent() {
        return blackContent;
    }

    public void setBlackContent(String blackContent) {
        this.blackContent = blackContent == null ? null : blackContent.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public String getStatusPushAddr() {
		return statusPushAddr;
	}

	public void setStatusPushAddr(String statusPushAddr) {
		this.statusPushAddr = statusPushAddr;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}
    
    
}