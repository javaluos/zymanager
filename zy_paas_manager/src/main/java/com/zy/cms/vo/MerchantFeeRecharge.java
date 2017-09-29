package com.zy.cms.vo;

import java.util.Date;

public class MerchantFeeRecharge {
    
	private Integer id;

    private String apiAccount;

    private Integer changeFee;

    private Integer currFee;

    private Integer rechargeProductId;

    private Short rechargeType;

    private Short payType;

    private Short status;

    private String reason;

    private String orderNo;

    private Date createTime;

    private Date finishTime;

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

	public Integer getChangeFee() {
        return changeFee;
    }

    public void setChangeFee(Integer changeFee) {
        this.changeFee = changeFee;
    }

    public Integer getCurrFee() {
        return currFee;
    }

    public void setCurrFee(Integer currFee) {
        this.currFee = currFee;
    }

    public Integer getRechargeProductId() {
        return rechargeProductId;
    }

    public void setRechargeProductId(Integer rechargeProductId) {
        this.rechargeProductId = rechargeProductId;
    }

    public Short getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Short rechargeType) {
        this.rechargeType = rechargeType;
    }

    public Short getPayType() {
        return payType;
    }

    public void setPayType(Short payType) {
        this.payType = payType;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}