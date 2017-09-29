package com.zy.cms.vo;

import java.math.BigDecimal;
import java.util.Date;

public class VoiceMerchantAccountBalance {
 
    private String apiAccount;

    private Long balance;
    
    private Long balanceS; //修改前余额

    private Integer isRecharge;

    private Date updateTime;

    private Date createTime;

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }


    public Integer getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(Integer isRecharge) {
        this.isRecharge = isRecharge;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBalanceS() {
        return balanceS;
    }

    public void setBalanceS(Long balanceS) {
        this.balanceS = balanceS;
    }
}