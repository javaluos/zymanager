package com.zy.cms.vo;

import java.util.Date;

public class SmsChannelBalMonitorSetting {
	
    private Integer id;

    private String channelId;//通道ID(主键)

    private Integer monitorBalance;//告警(金额/条)（单位为 100=1F）

    private Integer balanceUnit;//余额单位(0:厘(1000=1元);1:条)

    private Date updateTime;//更新时间

    private Date createTime;//创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Integer getMonitorBalance() {
        return monitorBalance;
    }

    public void setMonitorBalance(Integer monitorBalance) {
        this.monitorBalance = monitorBalance;
    }

    public Integer getBalanceUnit() {
        return balanceUnit;
    }

    public void setBalanceUnit(Integer balanceUnit) {
        this.balanceUnit = balanceUnit;
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