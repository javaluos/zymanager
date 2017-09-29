package com.zy.cms.vo.manager;

import org.apache.commons.lang3.StringUtils;

import com.zy.cms.util.DateUtil;

import java.util.Date;

/**
 * 
 * 短信统计汇总
 * 
 * @author JsonXu
 *
 */
public class SmsDailyStatistics {
	
    private Integer id;//主键 

    private String dateTime;//统计时间(yyyy-MM-dd)  

    private String apiAccount;// API_ACCOUNT(与voice_merchant_account表API_ACCOUNT关联)
    
    private String merchantPhone;//客户账号
    
    private String businessName;//客户名称

    private String channelMainCode;// 通道编号
    
    private String channelSmsId;// 通道ID(通道方生成)
    
    private String channelName; // 通道名称

	private Integer channelType; // 通道类型(1:通知;2:验证码;3:营销;4:通知、验证码;5:通知、验证码、营销)

    private String smsCategory;//短信分类(8:短信通知;9:短信验证码;11:短信营销)

    private Integer sendCount;//发送条数    

    private Integer successCount;//结果成功条数     

    private Integer failedCount;//结果失败条数 

    private Integer noreportCount;//结果未知条数    

    private Integer feeCount;//计费条数(成功状态,包含长短信)  
    
    private Double successSendRate;//发送成功率
    
    private Double failSendRate;//发送失败率
    
    private Double noreportRate;//未知状态比例
    
    private Integer avgSendTime;//平均发送时长(秒) 

    private Integer avgStatusTime;//平均状态报告接收时长(秒)
    
    private Integer sdUs10sCount;//10秒内到达数量  
    
    private Double sdUs10sCountRate;//10秒内到达率

    private Integer sdUs50sCount;//50秒内到达数量  
    
    private Double sdUs50sCountRate;//50秒内到达率

    private Integer sdUsgt50sCount;//大于50秒内到达数量
    
    private Double  sdUsgt50sCountRate;//大于50秒内到达率

    private Integer stBk24hCount;//状态报告24小时返回数量  
    
    private Double stBk24hCountRate;//状态报告24小时到达率

    private Integer stBk48hCount;//状态报告48小时返回数量 
    
    private Double stBk48hCountRate;//状态报告48小时到达率

    private Integer stBk72hCount;//状态报告72小时返回数量   
    
    private Double stBk72hCountRate;//状态报告72小时到达率

    private Date createTime;//创建时间

    private String dateTimeFormat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }
    public String getDateTimeFormat() {
        if(StringUtils.isNotBlank(dateTime)){
            dateTimeFormat = DateUtil.transformDateFormat(dateTime,
                    DateUtil.NUMBER_DATE_FORMAT, DateUtil.ISO_DATE_FORMAT);
        }
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime == null ? "" : dateTime.trim();
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? "" : apiAccount.trim();
    }

    public String getChannelSmsId() {
    	return channelSmsId = channelSmsId == null ? "" : channelSmsId.trim();
    }

    public void setChannelSmsId(String channelSmsId) {
        this.channelSmsId = channelSmsId == null ? null : channelSmsId.trim();
    }

    public String getSmsCategory() {
        return smsCategory;
    }

    public void setSmsCategory(String smsCategory) {
        this.smsCategory = smsCategory == null ? null : smsCategory.trim();
    }

    public Integer getSendCount() {
    	return sendCount = sendCount == null ? 0: sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getSuccessCount() {
    	return successCount = successCount == null ? 0: successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailedCount() {
    	return failedCount = failedCount == null ? 0: failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    public Integer getNoreportCount() {
    	return noreportCount = noreportCount == null ? 0: noreportCount;
    }

    public void setNoreportCount(Integer noreportCount) {
        this.noreportCount = noreportCount;
    }

    public Integer getFeeCount() {
    	return feeCount = feeCount == null ? 0 : feeCount;
    }

    public void setFeeCount(Integer feeCount) {
        this.feeCount = feeCount;
    }

    public Integer getSdUs10sCount() {
    	return sdUs10sCount = sdUs10sCount == null ? 0: sdUs10sCount;
    }

    public void setSdUs10sCount(Integer sdUs10sCount) {
        this.sdUs10sCount = sdUs10sCount;
    }

    public Integer getSdUs50sCount() {
    	return sdUs50sCount = sdUs50sCount == null ? 0 : sdUs50sCount;
    }

    public void setSdUs50sCount(Integer sdUs50sCount) {
        this.sdUs50sCount = sdUs50sCount;
    }

    public Integer getSdUsgt50sCount() {
    	return sdUsgt50sCount = sdUsgt50sCount == null ? 0: sdUsgt50sCount;
    }

    public void setSdUsgt50sCount(Integer sdUsgt50sCount) {
        this.sdUsgt50sCount = sdUsgt50sCount;
    }

    public Integer getStBk24hCount() {
    	return stBk24hCount = stBk24hCount == null ? 0: stBk24hCount;
    }

    public void setStBk24hCount(Integer stBk24hCount) {
        this.stBk24hCount = stBk24hCount;
    }

    public Integer getStBk48hCount() {
    	return stBk48hCount = stBk48hCount == null ? 0: stBk48hCount;
    }

    public void setStBk48hCount(Integer stBk48hCount) {
        this.stBk48hCount = stBk48hCount;
    }

    public Integer getStBk72hCount() {
    	return stBk72hCount = stBk72hCount == null ? 0 : stBk72hCount;
    }

    public void setStBk72hCount(Integer stBk72hCount) {
        this.stBk72hCount = stBk72hCount;
    }

    public Integer getAvgSendTime() {
    	return avgSendTime = avgSendTime == null ? 0 : avgSendTime;
    }

    public void setAvgSendTime(Integer avgSendTime) {
        this.avgSendTime = avgSendTime;
    }

    public Integer getAvgStatusTime() {
    	return avgStatusTime = avgStatusTime == null ? 0 : avgStatusTime;
    }

    public void setAvgStatusTime(Integer avgStatusTime) {
        this.avgStatusTime = avgStatusTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

	public String getChannelMainCode() {
    	return channelMainCode = channelMainCode == null ? "" : channelMainCode.trim();
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public String getChannelName() {
    	return channelName = channelName == null ? "" : channelName.trim();
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelType() {
    	return channelType = channelType == null ? -1 : channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Double getSuccessSendRate() {
    	return successSendRate = successSendRate == null ? 0 : successSendRate;
	}

	public void setSuccessSendRate(Double successSendRate) {
		this.successSendRate = successSendRate;
	}

	public Double getFailSendRate() {
    	return failSendRate = failSendRate == null ? 0 : failSendRate;
	}

	public void setFailSendRate(Double failSendRate) {
		this.failSendRate = failSendRate;
	}

	public Double getNoreportRate() {
		return noreportRate = noreportRate == null ? 0 : noreportRate;
	}

	public void setNoreportRate(Double noreportRate) {
		this.noreportRate = noreportRate;
	}

	public Double getSdUs10sCountRate() {
		return sdUs10sCountRate = sdUs10sCountRate == null ? 0 : sdUs10sCountRate;
	}

	public void setSdUs10sCountRate(Double sdUs10sCountRate) {
		this.sdUs10sCountRate = sdUs10sCountRate;
	}

	public Double getSdUs50sCountRate() {
		return sdUs50sCountRate = sdUs50sCountRate == null ? 0 : sdUs50sCountRate;
	}

	public void setSdUs50sCountRate(Double sdUs50sCountRate) {
		this.sdUs50sCountRate = sdUs50sCountRate;
	}

	public Double getSdUsgt50sCountRate() {
		return sdUsgt50sCountRate = sdUsgt50sCountRate == null ? 0 : sdUsgt50sCountRate;
	}

	public void setSdUsgt50sCountRate(Double sdUsgt50sCountRate) {
		this.sdUsgt50sCountRate = sdUsgt50sCountRate;
	}

	public Double getStBk24hCountRate() {
		return stBk24hCountRate = stBk24hCountRate == null ? 0 : stBk24hCountRate;
	}

	public void setStBk24hCountRate(Double stBk24hCountRate) {
		this.stBk24hCountRate = stBk24hCountRate;
	}

	public Double getStBk48hCountRate() {
		return stBk48hCountRate = stBk48hCountRate == null ? 0 : stBk48hCountRate;
	}

	public void setStBk48hCountRate(Double stBk48hCountRate) {
		this.stBk48hCountRate = stBk48hCountRate;
	}

	public Double getStBk72hCountRate() {
		return stBk72hCountRate = stBk72hCountRate == null ? 0 : stBk72hCountRate;
	}

	public void setStBk72hCountRate(Double stBk72hCountRate) {
		this.stBk72hCountRate = stBk72hCountRate;
	}
    
}