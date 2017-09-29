package com.zy.cms.vo;

import java.util.Date;

public class VoiceMerchantRecharge {
    
	private Integer id;

    private String apiAccount;

    private Long changeBalance;

    private Integer balance;

    private Integer rechargeProductId;

    private Short rechargeType;

    private Short payType;

    private Short status;

    private String reason;

    private String orderNo;

    private Date balanceExpireTime;

    private Date updateTime;

    private Date createTime;

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

    public Long getChangeBalance() {
        return changeBalance;
    }

    public void setChangeBalance(Long changeBalance) {
        this.changeBalance = changeBalance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
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

    public Date getBalanceExpireTime() {
        return balanceExpireTime;
    }

    public void setBalanceExpireTime(Date balanceExpireTime) {
        this.balanceExpireTime = balanceExpireTime;
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
}