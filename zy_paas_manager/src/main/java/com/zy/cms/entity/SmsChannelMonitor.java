package com.zy.cms.entity;

import java.util.Date;

public class SmsChannelMonitor {

    private String channelMainCode;
    
    private String channelId;
    
    private Integer id;
    
    private String smsChannelId;

    private String channelName;

    private String noticeTimeRange1;

    private Double successRateDown1;

    private Double failtRateUp1;

    private Double unknownRateUp1;

    private Integer averageSendUp1;

    private Integer averageReveiveUp1;
    
    private Integer sendCount1;

    private String noticeTimeRange2;

    private Double successRateDown2;

    private Double failtRateUp2;

    private Double unknownRateUp2;

    private Integer averageSendUp2;

    private Integer averageReveiveUp2;
    
    private Integer sendCount2;

    private String noticeTimeRange3;

    private Double successRateDown3;

    private Double failtRateUp3;

    private Double unknownRateUp3;

    private Integer averageSendUp3;

    private Integer averageReveiveUp3;
    
    private Integer sendCount3;

    private Integer startFlag;

    private Date createTime;

    private Date updateTime;
    
    private String tablesuffix;
    
    private Date startTime;
    
    private Date endTime;
    
    private String flag;
  	
  	private String preChannelId;
  	
  	private String preChannelName;
  	
  	private String preStartFlag;

    public String getChannelMainCode() {
		return channelMainCode;
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

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
        this.smsChannelId = smsChannelId == null ? null : smsChannelId.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getNoticeTimeRange1() {
        return noticeTimeRange1;
    }

    public void setNoticeTimeRange1(String noticeTimeRange1) {
        this.noticeTimeRange1 = noticeTimeRange1 == null ? null : noticeTimeRange1.trim();
    }

    public Double getSuccessRateDown1() {
        return successRateDown1;
    }

    public void setSuccessRateDown1(Double successRateDown1) {
        this.successRateDown1 = successRateDown1;
    }

    public Double getFailtRateUp1() {
        return failtRateUp1;
    }

    public void setFailtRateUp1(Double failtRateUp1) {
        this.failtRateUp1 = failtRateUp1;
    }

    public Double getUnknownRateUp1() {
        return unknownRateUp1;
    }

    public void setUnknownRateUp1(Double unknownRateUp1) {
        this.unknownRateUp1 = unknownRateUp1;
    }

    public Integer getAverageSendUp1() {
        return averageSendUp1;
    }

    public void setAverageSendUp1(Integer averageSendUp1) {
        this.averageSendUp1 = averageSendUp1;
    }

    public Integer getAverageReveiveUp1() {
        return averageReveiveUp1;
    }

    public void setAverageReveiveUp1(Integer averageReveiveUp1) {
        this.averageReveiveUp1 = averageReveiveUp1;
    }

    public String getNoticeTimeRange2() {
        return noticeTimeRange2;
    }

    public void setNoticeTimeRange2(String noticeTimeRange2) {
        this.noticeTimeRange2 = noticeTimeRange2 == null ? null : noticeTimeRange2.trim();
    }

    public Double getSuccessRateDown2() {
        return successRateDown2;
    }

    public void setSuccessRateDown2(Double successRateDown2) {
        this.successRateDown2 = successRateDown2;
    }

    public Double getFailtRateUp2() {
        return failtRateUp2;
    }

    public void setFailtRateUp2(Double failtRateUp2) {
        this.failtRateUp2 = failtRateUp2;
    }

    public Double getUnknownRateUp2() {
        return unknownRateUp2;
    }

    public void setUnknownRateUp2(Double unknownRateUp2) {
        this.unknownRateUp2 = unknownRateUp2;
    }

    public Integer getAverageSendUp2() {
        return averageSendUp2;
    }

    public void setAverageSendUp2(Integer averageSendUp2) {
        this.averageSendUp2 = averageSendUp2;
    }

    public Integer getAverageReveiveUp2() {
        return averageReveiveUp2;
    }

    public void setAverageReveiveUp2(Integer averageReveiveUp2) {
        this.averageReveiveUp2 = averageReveiveUp2;
    }

    public String getNoticeTimeRange3() {
        return noticeTimeRange3;
    }

    public void setNoticeTimeRange3(String noticeTimeRange3) {
        this.noticeTimeRange3 = noticeTimeRange3 == null ? null : noticeTimeRange3.trim();
    }

    public Double getSuccessRateDown3() {
        return successRateDown3;
    }

    public void setSuccessRateDown3(Double successRateDown3) {
        this.successRateDown3 = successRateDown3;
    }

    public Double getFailtRateUp3() {
        return failtRateUp3;
    }

    public void setFailtRateUp3(Double failtRateUp3) {
        this.failtRateUp3 = failtRateUp3;
    }

    public Double getUnknownRateUp3() {
        return unknownRateUp3;
    }

    public void setUnknownRateUp3(Double unknownRateUp3) {
        this.unknownRateUp3 = unknownRateUp3;
    }

    public Integer getAverageSendUp3() {
        return averageSendUp3;
    }

    public void setAverageSendUp3(Integer averageSendUp3) {
        this.averageSendUp3 = averageSendUp3;
    }

    public Integer getAverageReveiveUp3() {
        return averageReveiveUp3;
    }

    public void setAverageReveiveUp3(Integer averageReveiveUp3) {
        this.averageReveiveUp3 = averageReveiveUp3;
    }

    public Integer getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(Integer startFlag) {
        this.startFlag = startFlag;
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

	public String getTablesuffix() {
		return tablesuffix;
	}

	public void setTablesuffix(String tablesuffix) {
		this.tablesuffix = tablesuffix;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

	public Integer getSendCount1() {
		return sendCount1;
	}

	public void setSendCount1(Integer sendCount1) {
		this.sendCount1 = sendCount1;
	}

	public Integer getSendCount2() {
		return sendCount2;
	}

	public void setSendCount2(Integer sendCount2) {
		this.sendCount2 = sendCount2;
	}

	public Integer getSendCount3() {
		return sendCount3;
	}

	public void setSendCount3(Integer sendCount3) {
		this.sendCount3 = sendCount3;
	}

	public String getPreChannelId() {
		return preChannelId;
	}

	public void setPreChannelId(String preChannelId) {
		this.preChannelId = preChannelId;
	}

	public String getPreChannelName() {
		return preChannelName;
	}

	public void setPreChannelName(String preChannelName) {
		this.preChannelName = preChannelName;
	}

	public String getPreStartFlag() {
		return preStartFlag;
	}

	public void setPreStartFlag(String preStartFlag) {
		this.preStartFlag = preStartFlag;
	}

	@Override
	public String toString() {
		return "SmsChannelMonitor [channelMainCode=" + channelMainCode
				+ ", channelId=" + channelId + ", id=" + id + ", smsChannelId="
				+ smsChannelId + ", channelName=" + channelName
				+ ", noticeTimeRange1=" + noticeTimeRange1
				+ ", successRateDown1=" + successRateDown1 + ", failtRateUp1="
				+ failtRateUp1 + ", unknownRateUp1=" + unknownRateUp1
				+ ", averageSendUp1=" + averageSendUp1 + ", averageReveiveUp1="
				+ averageReveiveUp1 + ", sendCount1=" + sendCount1
				+ ", noticeTimeRange2=" + noticeTimeRange2
				+ ", successRateDown2=" + successRateDown2 + ", failtRateUp2="
				+ failtRateUp2 + ", unknownRateUp2=" + unknownRateUp2
				+ ", averageSendUp2=" + averageSendUp2 + ", averageReveiveUp2="
				+ averageReveiveUp2 + ", sendCount2=" + sendCount2
				+ ", noticeTimeRange3=" + noticeTimeRange3
				+ ", successRateDown3=" + successRateDown3 + ", failtRateUp3="
				+ failtRateUp3 + ", unknownRateUp3=" + unknownRateUp3
				+ ", averageSendUp3=" + averageSendUp3 + ", averageReveiveUp3="
				+ averageReveiveUp3 + ", sendCount3=" + sendCount3
				+ ", startFlag=" + startFlag + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", tablesuffix=" + tablesuffix
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", flag=" + flag + ", preChannelId=" + preChannelId
				+ ", preChannelName=" + preChannelName + ", preStartFlag="
				+ preStartFlag + "]";
	}

	
	
    
}