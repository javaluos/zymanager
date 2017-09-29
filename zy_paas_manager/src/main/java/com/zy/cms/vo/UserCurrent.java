/*
 * Copyright Guoling.com All right reserved. This software is the confidential and proprietary information of
 * Guoling.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Guoling.com.
 */
package com.zy.cms.vo;

/*
 * 类UserCurrent.java的实现描述：用于控件后台管理页面，左边控制菜单，是否是被选择的状态
 * @author ddp1j32 2015-6-8 上午9:48:50
 */
public class UserCurrent {

    private String managerCenter;//管理中心
    private String baseInfo;//基础资料
    private String infoAuth;//信息认证
    private String account;//账号信息
    private String dataAnalysis;//数据分析
    private String messageCenter;
    private String application;//管理应用
    private String applicationCreate;//应用创建
    private String smsTemplate;
    private String smsSignature;
    private String smsTest;
    private String smsSendRecord;
    private String voiceStore; //语音库 
    private String payOnLine;
    private String consumBill;
    private String giftCardActive;
    
    private String signCheck;

    public String getManagerCenter() {
        return managerCenter;
    }

    public void setManagerCenter(String managerCenter) {
        this.managerCenter = managerCenter;
    }

    public String getMessageCenter() {
        return messageCenter;
    }

    public void setMessageCenter(String messageCenter) {
        this.messageCenter = messageCenter;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public void setSmsTemplate(String smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    public String getSmsSignature() {
        return smsSignature;
    }

    public void setSmsSignature(String smsSignature) {
        this.smsSignature = smsSignature;
    }

    public String getSmsTest() {
        return smsTest;
    }

    public void setSmsTest(String smsTest) {
        this.smsTest = smsTest;
    }

    public String getSmsSendRecord() {
        return smsSendRecord;
    }

    public void setSmsSendRecord(String smsSendRecord) {
        this.smsSendRecord = smsSendRecord;
    }

    public String getPayOnLine() {
        return payOnLine;
    }

    public void setPayOnLine(String payOnLine) {
        this.payOnLine = payOnLine;
    }

    public String getConsumBill() {
        return consumBill;
    }

    public void setConsumBill(String consumBill) {
        this.consumBill = consumBill;
    }

    public String getGiftCardActive() {
        return giftCardActive;
    }

    public void setGiftCardActive(String giftCardActive) {
        this.giftCardActive = giftCardActive;
    }

    public String getDataAnalysis() {
        return dataAnalysis;
    }

    public void setDataAnalysis(String dataAnalysis) {
        this.dataAnalysis = dataAnalysis;
    }

	public String getSignCheck() {
		return signCheck;
	}

	public void setSignCheck(String signCheck) {
		this.signCheck = signCheck;
	}

	public String getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}

	public String getInfoAuth() {
		return infoAuth;
	}

	public void setInfoAuth(String infoAuth) {
		this.infoAuth = infoAuth;
	}

	public String getApplicationCreate() {
		return applicationCreate;
	}

	public void setApplicationCreate(String applicationCreate) {
		this.applicationCreate = applicationCreate;
	}

	public String getVoiceStore() {
		return voiceStore;
	}

	public void setVoiceStore(String voiceStore) {
		this.voiceStore = voiceStore;
	}
	
}
