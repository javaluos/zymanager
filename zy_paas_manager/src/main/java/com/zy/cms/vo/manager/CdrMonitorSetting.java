package com.zy.cms.vo.manager;

import java.util.Date;

public class CdrMonitorSetting {
    
	private Integer id;

    private String apiAccount;

    private String appId;

    private Integer businessId;

    private int callCountaUp =-1;

    private int callCountbUp =-1;

    private int callCountaDown =-1;

    private int callCountbDown =-1;

    private int successRateaDown =-1;

    private int successRatebDown =-1;

    private int responseRateaDown =-1;

    private int responseRatebDown =-1;

    private int averageTalkTimeaDown =-1;

    private int averageTalkTimebDown =-1;

    private int averageTurnOnDelayaUp =-1;

    private int averageTurnOnDelaybUp =-1;

    private int averageInTimeaUp =-1;

    private int averageInTimebUp =-1;

    private String measureTime;

    private Date updateTime;

    private Date createTime;

    private Integer startFlag;

    private Integer globalFlag;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Integer getBusinessId() {
    	
        return  this.businessId = businessId == null ? null : businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

	public int getCallCountaUp() {
		return callCountaUp;
	}

	public void setCallCountaUp(int callCountaUp) {
		this.callCountaUp = callCountaUp;
	}

	public int getCallCountbUp() {
		return callCountbUp;
	}

	public void setCallCountbUp(int callCountbUp) {
		this.callCountbUp = callCountbUp;
	}

	public int getCallCountaDown() {
		return callCountaDown;
	}

	public void setCallCountaDown(int callCountaDown) {
		this.callCountaDown = callCountaDown;
	}

	public int getCallCountbDown() {
		return callCountbDown;
	}

	public void setCallCountbDown(int callCountbDown) {
		this.callCountbDown = callCountbDown;
	}

	public int getSuccessRateaDown() {
		return successRateaDown;
	}

	public void setSuccessRateaDown(int successRateaDown) {
		this.successRateaDown = successRateaDown;
	}

	public int getSuccessRatebDown() {
		return successRatebDown;
	}

	public void setSuccessRatebDown(int successRatebDown) {
		this.successRatebDown = successRatebDown;
	}

	public int getResponseRateaDown() {
		return responseRateaDown;
	}

	public void setResponseRateaDown(int responseRateaDown) {
		this.responseRateaDown = responseRateaDown;
	}

	public int getResponseRatebDown() {
		return responseRatebDown;
	}

	public void setResponseRatebDown(int responseRatebDown) {
		this.responseRatebDown = responseRatebDown;
	}

	public int getAverageTalkTimeaDown() {
		return averageTalkTimeaDown;
	}

	public void setAverageTalkTimeaDown(int averageTalkTimeaDown) {
		this.averageTalkTimeaDown = averageTalkTimeaDown;
	}

	public int getAverageTalkTimebDown() {
		return averageTalkTimebDown;
	}

	public void setAverageTalkTimebDown(int averageTalkTimebDown) {
		this.averageTalkTimebDown = averageTalkTimebDown;
	}

	public int getAverageTurnOnDelayaUp() {
		return averageTurnOnDelayaUp;
	}

	public void setAverageTurnOnDelayaUp(int averageTurnOnDelayaUp) {
		this.averageTurnOnDelayaUp = averageTurnOnDelayaUp;
	}

	public int getAverageTurnOnDelaybUp() {
		return averageTurnOnDelaybUp;
	}

	public void setAverageTurnOnDelaybUp(int averageTurnOnDelaybUp) {
		this.averageTurnOnDelaybUp = averageTurnOnDelaybUp;
	}

	public int getAverageInTimeaUp() {
		return averageInTimeaUp;
	}

	public void setAverageInTimeaUp(int averageInTimeaUp) {
		this.averageInTimeaUp = averageInTimeaUp;
	}

	public int getAverageInTimebUp() {
		return averageInTimebUp;
	}

	public void setAverageInTimebUp(int averageInTimebUp) {
		this.averageInTimebUp = averageInTimebUp;
	}

	public String getMeasureTime() {
		return measureTime;
	}

	public void setMeasureTime(String measureTime) {
		this.measureTime = measureTime;
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

	public Integer getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(Integer startFlag) {
		this.startFlag = startFlag;
	}

	public Integer getGlobalFlag() {
		return globalFlag;
	}

	public void setGlobalFlag(Integer globalFlag) {
		this.globalFlag = globalFlag;
	}

}