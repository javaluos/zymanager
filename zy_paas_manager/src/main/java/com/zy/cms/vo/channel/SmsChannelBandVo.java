package com.zy.cms.vo.channel;

import java.util.Date;

public class SmsChannelBandVo {
    private Integer id;
    
    private String apiAccount;

    private String merchantPhone;

    private String businessName;
    
    private String channelName;
    
    private String channelId;
    
    private Integer channelType;
    
    private Integer operatorType;
    
    private String channelProperty;

    private Integer signerAudit;
    
    private Integer templateAudit;

    private Integer status;

    private String dtnProvince;
    
	private Integer useProvince;
    
    private Integer channelScore;
    
    private Integer thresholdValue;
    
    private Date createTime;

    private Date updateTime;
    
    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public String getChannelProperty() {
		return channelProperty;
	}

	public void setChannelProperty(String channelProperty) {
		this.channelProperty = channelProperty;
	}

	public Integer getSignerAudit() {
		return signerAudit;
	}

	public void setSignerAudit(Integer signerAudit) {
		this.signerAudit = signerAudit;
	}

	public Integer getTemplateAudit() {
		return templateAudit;
	}

	public void setTemplateAudit(Integer templateAudit) {
		this.templateAudit = templateAudit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getChannelScore() {
		return channelScore;
	}

	public void setChannelScore(Integer channelScore) {
		this.channelScore = channelScore;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDtnProvince() {
		return dtnProvince;
	}

	public void setDtnProvince(String dtnProvince) {
		this.dtnProvince = dtnProvince;
	}

	public Integer getUseProvince() {
		return useProvince;
	}

	public void setUseProvince(Integer useProvince) {
		this.useProvince = useProvince;
	}

	public Integer getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	
}