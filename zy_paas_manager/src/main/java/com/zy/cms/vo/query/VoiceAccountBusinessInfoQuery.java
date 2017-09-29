package com.zy.cms.vo.query;

import java.util.List;

public class VoiceAccountBusinessInfoQuery {

	private Integer id;// 主键

	private String apiAccount;// 账户号

	private String merchantPhone;// 手机号码

	private String businessId;// 业务ID

	private Integer feeRule;// 计费规则0:6+6,1:30,2:60+60，默认0

	private Integer feerate;// 费率（100=1F）

	private String remark;// 备注信息

	private String createTimeStart;// 创建时间开始时间

	private String createTimeEnd;// 创建时间结束时间

	private String updateTimeStart;// 更新时间开始时间

	private String updateTimeEnd;// 更新时间结束时间

	private double callBack; // 1回拨电话

	private double numberGuard; // 2号码卫士

	private double directDialTelephone; // 3直拨电话

	private double voiceNotification; // 4语音通知

	private double voiceVerificationCode; // 5语音验证码

	private double callCenter; // 6呼叫中心

	private double multiTalk; // 7多方通话

	private double smsNotification; // 8短信验通知

	private double smsVeriicationCode; // 9短信验证码

	private double soundRecording; // 10录音

	private double smsMarket; // 11 短信营销

	private Integer callBackRule; // 1回拨电话计费规则

	private Integer numberGuardRule; // 2号码卫士计费规则

	private Integer directDialTelephoneRule; // 3直拨电话计费规则

	private Integer voiceNotificationRule; // 4语音通知计费规则

	private Integer voiceVerificationCodeRule; // 5语音验证码计费规则

	private Integer callCenterRule; // 6呼叫中心计费规则

	private Integer multiTalkRule; // 7多方通话计费规则

	private Integer smsNotificationRule; // 8短信验通知计费规则

	private Integer smsVeriicationCodeRule; // 9短信验证码计费规则

	private Integer soundRecordingRule; // 10录音计费规则

	private Integer smsMarketRule; // 11 短信营销

	private Integer pageNum;// 页号

	private Integer pageSize;// 每页数量

	private Integer pageOffset = 0;// 分页的开始值

	private Integer pageCount;// 统计页数
	
	private List apiAccounts;//账户绑定的客户

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPageNum() {
		if (pageNum == null) {
			pageNum = 0;
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if (pageSize == null) {
			pageSize = 0;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageCount() {
		if (pageCount == null) {
			pageCount = 0;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public double getCallBack() {
		return callBack;
	}

	public void setCallBack(double callBack) {
		this.callBack = callBack;
	}

	public double getNumberGuard() {
		return numberGuard;
	}

	public void setNumberGuard(double numberGuard) {
		this.numberGuard = numberGuard;
	}

	public double getDirectDialTelephone() {
		return directDialTelephone;
	}

	public void setDirectDialTelephone(double directDialTelephone) {
		this.directDialTelephone = directDialTelephone;
	}

	public double getVoiceNotification() {
		return voiceNotification;
	}

	public void setVoiceNotification(double voiceNotification) {
		this.voiceNotification = voiceNotification;
	}

	public double getVoiceVerificationCode() {
		return voiceVerificationCode;
	}

	public void setVoiceVerificationCode(double voiceVerificationCode) {
		this.voiceVerificationCode = voiceVerificationCode;
	}

	public double getCallCenter() {
		return callCenter;
	}

	public void setCallCenter(double callCenter) {
		this.callCenter = callCenter;
	}

	public double getMultiTalk() {
		return multiTalk;
	}

	public void setMultiTalk(double multiTalk) {
		this.multiTalk = multiTalk;
	}

	public double getSmsNotification() {
		return smsNotification;
	}

	public void setSmsNotification(double smsNotification) {
		this.smsNotification = smsNotification;
	}

	public double getSmsVeriicationCode() {
		return smsVeriicationCode;
	}

	public void setSmsVeriicationCode(double smsVeriicationCode) {
		this.smsVeriicationCode = smsVeriicationCode;
	}

	public double getSoundRecording() {
		return soundRecording;
	}

	public void setSoundRecording(double soundRecording) {
		this.soundRecording = soundRecording;
	}

	public double getSmsMarket() {
		return smsMarket;
	}

	public void setSmsMarket(double smsMarket) {
		this.smsMarket = smsMarket;
	}

	public Integer getSmsMarketRule() {
		return smsMarketRule;
	}

	public void setSmsMarketRule(Integer smsMarketRule) {
		this.smsMarketRule = smsMarketRule;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

	
}