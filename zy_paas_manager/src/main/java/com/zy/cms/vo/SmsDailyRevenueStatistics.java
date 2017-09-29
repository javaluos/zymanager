package com.zy.cms.vo;

import java.util.Date;

/**
 * 营收分析
 * @author JasonXu
 *
 */
public class SmsDailyRevenueStatistics {
	
    private Long id;

    private String dateTime;//统计时间(yyyy-MM-dd)

    private String apiAccount;//API_ACCOUNT(与voice_merchant_account表API_ACCOUNT关联)

    private String channelId;//短信发送的ID(通道方生成)

    private String smsCategory;//短信分类(8:短信通知;9:短信验证码;11:短信营销)

    private Integer feeCount;//计费条数(成功状态,包含长短信)

    private double inCome;//收入

    private double costFee;//成本

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime == null ? null : dateTime.trim();
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getSmsCategory() {
        return smsCategory;
    }

    public void setSmsCategory(String smsCategory) {
        this.smsCategory = smsCategory == null ? null : smsCategory.trim();
    }

    public Integer getFeeCount() {
        return feeCount;
    }

    public void setFeeCount(Integer feeCount) {
        this.feeCount = feeCount;
    }

    public double getInCome() {
        return inCome;
    }

    public void setInCome(double inCome) {
        this.inCome = inCome;
    }

    public double getCostFee() {
		return costFee;
	}

	public void setCostFee(double costFee) {
		this.costFee = costFee;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}