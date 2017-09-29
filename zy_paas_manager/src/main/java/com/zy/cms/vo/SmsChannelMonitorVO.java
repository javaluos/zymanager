package com.zy.cms.vo;

import java.math.BigDecimal;


public class SmsChannelMonitorVO {
    private Integer id;

    private String smsChannelId;

    private String channelName;
    
    private String noticeTimeRange;
    
    private Integer sendCount;
    
    private Integer successCount;
    
    private Integer failtCount;
    
    private Integer unknownCount;


    private Double successRate;

    private Double failtRate;

    private Double unknownRate;

    private Integer averageSend;

    private Integer averageReveive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSmsChannelId() {
		return smsChannelId;
	}

	public void setSmsChannelId(String smsChannelId) {
		this.smsChannelId = smsChannelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getNoticeTimeRange() {
		return noticeTimeRange;
	}

	public void setNoticeTimeRange(String noticeTimeRange) {
		this.noticeTimeRange = noticeTimeRange;
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailtCount() {
		return failtCount;
	}

	public void setFailtCount(Integer failtCount) {
		this.failtCount = failtCount;
	}

	public Integer getUnknownCount() {
		return unknownCount;
	}

	public void setUnknownCount(Integer unknownCount) {
		this.unknownCount = unknownCount;
	}

	public Double getSuccessRate() {
		if(null!=successRate){
			BigDecimal bg = new BigDecimal(successRate);  
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			return f1;
		}
		return successRate;
	}

	public void setSuccessRate(Double successRate) {
		this.successRate = successRate;
	}

	public Double getFailtRate() {
		if(null!=failtRate){
			BigDecimal bg = new BigDecimal(failtRate);  
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			return f1;
		}
		return failtRate;
	}

	public void setFailtRate(Double failtRate) {
		this.failtRate = failtRate;
	}

	public Double getUnknownRate() {
		if(null!=unknownRate){
			BigDecimal bg = new BigDecimal(unknownRate);  
			double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
			return f1;
		}
		return unknownRate;
	}

	public void setUnknownRate(Double unknownRate) {
		this.unknownRate = unknownRate;
	}

	public Integer getAverageSend() {
		return averageSend;
	}

	public void setAverageSend(Integer averageSend) {
		this.averageSend = averageSend;
	}

	public Integer getAverageReveive() {
		return averageReveive;
	}

	public void setAverageReveive(Integer averageReveive) {
		this.averageReveive = averageReveive;
	}
    
}
