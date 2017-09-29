package com.zy.cms.vo;

import java.util.Date;

public class MerchantConsumeBill {
    
	private Integer id;

    private String merchantAccount;

    private Date billDate;

    private Integer appId;

    private Integer smsType;

    private Integer smsNum;

    private Date createTime;

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

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public Integer getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(Integer smsNum) {
        this.smsNum = smsNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "MerchantConsumeBill [id=" + id + ", merchantAccount="
				+ merchantAccount + ", billDate=" + billDate + ", appId="
				+ appId + ", smsType=" + smsType + ", smsNum=" + smsNum
				+ ", createTime=" + createTime + "]";
	}
    
}