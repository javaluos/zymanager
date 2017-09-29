package com.zy.cms.vo.manager;

import java.util.Date;

import com.zy.cms.vo.MerchantAccount;

public class CdrMonitorUser {
	
    private Integer id;

    private String apiAccount;

    private String merchantPhone;

    private Integer monitorStatus;

    private Integer noticeTimes;

    private Date updateTime;

    private Date createTime;
    
    private String businessName;
    
    private MerchantAccount merchantAccount;

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
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone == null ? null : merchantPhone.trim();
    }

    public Integer getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(Integer monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public Integer getNoticeTimes() {
        return noticeTimes;
    }

    public void setNoticeTimes(Integer noticeTimes) {
        this.noticeTimes = noticeTimes;
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

	public String getBusinessName() {
		this.businessName = businessName== null ? null : businessName.trim();
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public MerchantAccount getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(MerchantAccount merchantAccount) {
		this.merchantAccount = merchantAccount;
	}
}