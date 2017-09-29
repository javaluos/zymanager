package com.zy.cms.vo;

import java.util.Date;

public class VoiceAccountBusinessInfo {
    
    private String apiAccount;

    private String businessId;

    private Integer feeRule;

    private Integer feerate;
    
    private String remark;

    private Date updateTime;

    private Date createTime;
    
    private Integer callBack;			    //1回拨电话
    
    private Integer numberGuard;		    //2号码卫士
    
    private Integer directDialTelephone;    //3直拨电话
    
    private Integer voiceNotification;      //4语音通知
    
    private Integer voiceVerificationCode;  //5语音验证码
    
    private Integer callCenter;			    //6呼叫中心
    
    private Integer multiTalk;			    //7多方通话
    
    private Integer smsNotification;	    //8短信验通知
    
    private Integer smsVeriicationCode;	    //9短信验证码
    
    private Integer soundRecording;			//10录音
    
    private Integer smsMarket;	    //11 短信营销
    
    private Integer callBackRule;			    //1回拨电话计费规则
    
    private Integer numberGuardRule;		    //2号码卫士计费规则
    
    private Integer directDialTelephoneRule;    //3直拨电话计费规则
    
    private Integer voiceNotificationRule;      //4语音通知计费规则
    
    private Integer voiceVerificationCodeRule;  //5语音验证码计费规则
    
    private Integer callCenterRule;			    //6呼叫中心计费规则
    
    private Integer multiTalkRule;			    //7多方通话计费规则
    
    private Integer smsNotificationRule;	    //8短信验通知计费规则
    
    private Integer smsVeriicationCodeRule;	    //9短信验证码计费规则
    
    private Integer soundRecordingRule;			//10录音计费规则
    
    private Integer smsMarketRule;	    // 11 短信营销规则
    
    private Integer callBackRule2;			    //1回拨电话计费规则
    
    private Integer numberGuardRule2;		    //2号码卫士计费规则
    
    private Integer directDialTelephoneRule2;    //3直拨电话计费规则
    
    private Integer voiceNotificationRule2;      //4语音通知计费规则
    
    private Integer voiceVerificationCodeRule2;  //5语音验证码计费规则
    
    private Integer callCenterRule2;			//6呼叫中心计费规则
    
    private Integer multiTalkRule2;			    //7多方通话计费规则 
    
    private Double callBacks;			    //1回拨电话
    
    private Double numberGuards;		    //2号码卫士
    
    private Double directDialTelephones;    //3直拨电话
    
    private Double voiceNotifications;      //4语音通知
    
    private Double voiceVerificationCodes;  //5语音验证码
    
    private Double callCenters;			    //6呼叫中心
    
    private Double multiTalks;			    //7多方通话
    
    private Double smsNotifications;	    //8短信验通知
    
    private Double smsVeriicationCodes;	    //9短信验证码
    
    private Double soundRecordings;			//10录音
    
    private Double smsMarkets;	    // 11 短信营销
    
    private String merchantPhone;
    
    private String businessName;
    
    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public Integer getFeeRule() {
        return feeRule;
    }

    public void setFeeRule(Integer feeRule) {
        this.feeRule = feeRule;
    }

    public Integer getFeerate() {
        return feerate;
    }

    public void setFeerate(Integer feerate) {
        this.feerate = feerate;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCallBack() {
		return callBack;
	}

	public void setCallBack(Integer callBack) {
		this.callBack = callBack;
	}

	public Integer getNumberGuard() {
		return numberGuard;
	}

	public void setNumberGuard(Integer numberGuard) {
		this.numberGuard = numberGuard;
	}

	public Integer getDirectDialTelephone() {
		return directDialTelephone;
	}

	public void setDirectDialTelephone(Integer directDialTelephone) {
		this.directDialTelephone = directDialTelephone;
	}

	public Integer getVoiceNotification() {
		return voiceNotification;
	}

	public void setVoiceNotification(Integer voiceNotification) {
		this.voiceNotification = voiceNotification;
	}

	public Integer getVoiceVerificationCode() {
		return voiceVerificationCode;
	}

	public void setVoiceVerificationCode(Integer voiceVerificationCode) {
		this.voiceVerificationCode = voiceVerificationCode;
	}

	public Integer getCallCenter() {
		return callCenter;
	}

	public void setCallCenter(Integer callCenter) {
		this.callCenter = callCenter;
	}

	public Integer getMultiTalk() {
		return multiTalk;
	}

	public void setMultiTalk(Integer multiTalk) {
		this.multiTalk = multiTalk;
	}

	public Integer getSmsNotification() {
		return smsNotification;
	}

	public void setSmsNotification(Integer smsNotification) {
		this.smsNotification = smsNotification;
	}

	public Integer getSmsVeriicationCode() {
		return smsVeriicationCode;
	}

	public void setSmsVeriicationCode(Integer smsVeriicationCode) {
		this.smsVeriicationCode = smsVeriicationCode;
	}

	public Integer getSoundRecording() {
		return soundRecording;
	}

	public void setSoundRecording(Integer soundRecording) {
		this.soundRecording = soundRecording;
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

	public Integer getCallBackRule() {
		return callBackRule;
	}

	public void setCallBackRule(Integer callBackRule) {
		this.callBackRule = callBackRule;
	}

	public Integer getNumberGuardRule() {
		return numberGuardRule;
	}

	public void setNumberGuardRule(Integer numberGuardRule) {
		this.numberGuardRule = numberGuardRule;
	}

	public Integer getDirectDialTelephoneRule() {
		return directDialTelephoneRule;
	}

	public void setDirectDialTelephoneRule(Integer directDialTelephoneRule) {
		this.directDialTelephoneRule = directDialTelephoneRule;
	}

	public Integer getVoiceNotificationRule() {
		return voiceNotificationRule;
	}

	public void setVoiceNotificationRule(Integer voiceNotificationRule) {
		this.voiceNotificationRule = voiceNotificationRule;
	}

	public Integer getVoiceVerificationCodeRule() {
		return voiceVerificationCodeRule;
	}

	public void setVoiceVerificationCodeRule(Integer voiceVerificationCodeRule) {
		this.voiceVerificationCodeRule = voiceVerificationCodeRule;
	}

	public Integer getCallCenterRule() {
		return callCenterRule;
	}

	public void setCallCenterRule(Integer callCenterRule) {
		this.callCenterRule = callCenterRule;
	}

	public Integer getMultiTalkRule() {
		return multiTalkRule;
	}

	public void setMultiTalkRule(Integer multiTalkRule) {
		this.multiTalkRule = multiTalkRule;
	}

	public Integer getSmsNotificationRule() {
		return smsNotificationRule;
	}

	public void setSmsNotificationRule(Integer smsNotificationRule) {
		this.smsNotificationRule = smsNotificationRule;
	}

	public Integer getSmsVeriicationCodeRule() {
		return smsVeriicationCodeRule;
	}

	public void setSmsVeriicationCodeRule(Integer smsVeriicationCodeRule) {
		this.smsVeriicationCodeRule = smsVeriicationCodeRule;
	}

	public Integer getSoundRecordingRule() {
		return soundRecordingRule;
	}

	public void setSoundRecordingRule(Integer soundRecordingRule) {
		this.soundRecordingRule = soundRecordingRule;
	}
	
	public Double getCallBacks() {
		return callBacks;
	}

	public void setCallBacks(Double callBacks) {
		this.callBacks = callBacks;
	}

	public Double getNumberGuards() {
		return numberGuards;
	}

	public void setNumberGuards(Double numberGuards) {
		this.numberGuards = numberGuards;
	}

	public Double getDirectDialTelephones() {
		return directDialTelephones;
	}

	public void setDirectDialTelephones(Double directDialTelephones) {
		this.directDialTelephones = directDialTelephones;
	}

	public Double getVoiceNotifications() {
		return voiceNotifications;
	}

	public void setVoiceNotifications(Double voiceNotifications) {
		this.voiceNotifications = voiceNotifications;
	}

	public Double getVoiceVerificationCodes() {
		return voiceVerificationCodes;
	}

	public void setVoiceVerificationCodes(Double voiceVerificationCodes) {
		this.voiceVerificationCodes = voiceVerificationCodes;
	}

	public Double getCallCenters() {
		return callCenters;
	}

	public void setCallCenters(Double callCenters) {
		this.callCenters = callCenters;
	}

	public Double getMultiTalks() {
		return multiTalks;
	}

	public void setMultiTalks(Double multiTalks) {
		this.multiTalks = multiTalks;
	}

	public Double getSmsNotifications() {
		return smsNotifications;
	}

	public void setSmsNotifications(Double smsNotifications) {
		this.smsNotifications = smsNotifications;
	}

	public Double getSmsVeriicationCodes() {
		return smsVeriicationCodes;
	}

	public void setSmsVeriicationCodes(Double smsVeriicationCodes) {
		this.smsVeriicationCodes = smsVeriicationCodes;
	}

	public Double getSoundRecordings() {
		return soundRecordings;
	}

	public void setSoundRecordings(Double soundRecordings) {
		this.soundRecordings = soundRecordings;
	}

	public Integer getCallBackRule2() {
		return callBackRule2;
	}

	public void setCallBackRule2(Integer callBackRule2) {
		this.callBackRule2 = callBackRule2;
	}

	public Integer getNumberGuardRule2() {
		return numberGuardRule2;
	}

	public void setNumberGuardRule2(Integer numberGuardRule2) {
		this.numberGuardRule2 = numberGuardRule2;
	}

	public Integer getDirectDialTelephoneRule2() {
		return directDialTelephoneRule2;
	}

	public void setDirectDialTelephoneRule2(Integer directDialTelephoneRule2) {
		this.directDialTelephoneRule2 = directDialTelephoneRule2;
	}

	public Integer getVoiceNotificationRule2() {
		return voiceNotificationRule2;
	}

	public void setVoiceNotificationRule2(Integer voiceNotificationRule2) {
		this.voiceNotificationRule2 = voiceNotificationRule2;
	}

	public Integer getVoiceVerificationCodeRule2() {
		return voiceVerificationCodeRule2;
	}

	public void setVoiceVerificationCodeRule2(Integer voiceVerificationCodeRule2) {
		this.voiceVerificationCodeRule2 = voiceVerificationCodeRule2;
	}

	public Integer getCallCenterRule2() {
		return callCenterRule2;
	}

	public void setCallCenterRule2(Integer callCenterRule2) {
		this.callCenterRule2 = callCenterRule2;
	}

	public Integer getMultiTalkRule2() {
		return multiTalkRule2;
	}

	public void setMultiTalkRule2(Integer multiTalkRule2) {
		this.multiTalkRule2 = multiTalkRule2;
	}

	public Integer getSmsMarket() {
		return smsMarket;
	}

	public void setSmsMarket(Integer smsMarket) {
		this.smsMarket = smsMarket;
	}

	public Integer getSmsMarketRule() {
		return smsMarketRule;
	}

	public void setSmsMarketRule(Integer smsMarketRule) {
		this.smsMarketRule = smsMarketRule;
	}

	public Double getSmsMarkets() {
		return smsMarkets;
	}

	public void setSmsMarkets(Double smsMarkets) {
		this.smsMarkets = smsMarkets;
	}
	
}