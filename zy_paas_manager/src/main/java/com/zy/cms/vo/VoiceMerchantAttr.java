package com.zy.cms.vo;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * 账号属性
 * 
 * @author JasonXu
 *
 */
public class VoiceMerchantAttr {

	private Long id;// ID主键

	private String apiAccount;// 账号apiaccount

	private String businessId;// 业务类型

	private String attrName;// 属性KEY

	private String attrValue;// 属性VALUE

	private Integer authFlag;// 帐号认证状态

	private String remark;// 备注

	private Date createTime;// 创建时间

	private Date updateTime;// 修改时间

	private String businessName;// 客户名称

	private String templateAuthFalg8;// 短信通知模板是否免签(0:审核;1:免审)，默认值:0

	private String signerAuthFlag8;// 短信通知签名是否免签(0:审核;1:免审)，默认值:0

	private String templateAuthFalg9;// 短信验证码模板是否免签(0:审核;1:免审)，默认值:0

	private String signerAuthFlag9;// 短信验证码签名是否免签(0:审核;1:免审)，默认值:0

	private String templateAuthFalg11;// 短信营销模板是否免签(0:审核;1:免审)，默认值:0

	private String signerAuthFlag11;// 短信营销签名是否免签(0:审核;1:免审)，默认值:0

	private String voicefileAuthFlag4;// 语音文件是否免签(0:审核;1:免审)，默认值:0

	private String voicefileAuthFlag5;// 语音文件是否免签(0:审核;1:免审)，默认值:0

	private String isBlackKey;// 平台敏感词是否过滤(1:是;0:否)，默认值:1

	private String smsFilterPolicy;// 客户免拦截策略

	private String smsNoFilterPolicy;// 客户拦截策略

	private String isBlackAuditFlag;// 拦截后是否进行人工审核(1:是;0:否)，默认值:1

	private String isWhiteKey;// 是否为白名单(0:是;1:否)，默认值:1

	private String smsChannelPolicy; // 分流策略
	
	private Integer accSendPerSecond;//门限(账号提交速度)

	private String dowmMobileFlag;// 是否开启单号吗下发数(0:否;1:是)，默认值:0

	private Integer secondItem;// 秒条数

	private Integer secondNum;// 秒数

	private Integer minuteItem;// 分条数

	private Integer minuteNum;// 分数

	private Integer hourItem;// 时条数

	private Integer hourNum;// 时数

	private String cmppAccessCode;// CMPP接入号(CMPP_ACCESS_CODE)
	
	private String cmppAccessAccount;// CMPP账号
	
	private String cmppAccessPwd;// CMPP密码

	private String ydExtNumber; // 拓展号配置(移动)(YD_EXTNUMBER)

	private String ltExtNumber; // 拓展号配置(联通)(LT_EXTNUMBER)

	private String dxExtNumber; // 拓展号配置(电信)(DX_EXTNUMBER)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName == null ? null : attrName.trim();
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue == null ? null : attrValue.trim();
	}

	public Integer getAuthFlag() {
		return authFlag;
	}

	public void setAuthFlag(Integer authFlag) {
		this.authFlag = authFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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

	public String getBusinessName() {
		return this.businessName = businessName == null ? "" : businessName.trim();
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTemplateAuthFalg8() {
		return this.templateAuthFalg8 = templateAuthFalg8 == null ? "0" : templateAuthFalg8.trim();
	}

	public void setTemplateAuthFalg8(String templateAuthFalg8) {
		this.templateAuthFalg8 = templateAuthFalg8;
	}

	public String getSignerAuthFlag8() {
		return this.signerAuthFlag8 = signerAuthFlag8 == null ? "0" : signerAuthFlag8.trim();
	}

	public void setSignerAuthFlag8(String signerAuthFlag8) {
		this.signerAuthFlag8 = signerAuthFlag8;
	}

	public String getTemplateAuthFalg9() {
		return this.templateAuthFalg9 = templateAuthFalg9 == null ? "0" : templateAuthFalg9.trim();
	}

	public void setTemplateAuthFalg9(String templateAuthFalg9) {
		this.templateAuthFalg9 = templateAuthFalg9;
	}

	public String getSignerAuthFlag9() {
		return this.signerAuthFlag9 = signerAuthFlag9 == null ? "0" : signerAuthFlag9.trim();
	}

	public void setSignerAuthFlag9(String signerAuthFlag9) {
		this.signerAuthFlag9 = signerAuthFlag9;
	}

	public String getTemplateAuthFalg11() {
		return this.templateAuthFalg11 = templateAuthFalg11 == null ? "0" : templateAuthFalg11.trim();
	}

	public void setTemplateAuthFalg11(String templateAuthFalg11) {
		this.templateAuthFalg11 = templateAuthFalg11;
	}

	public String getSignerAuthFlag11() {
		return this.signerAuthFlag11 = signerAuthFlag11 == null ? "0" : signerAuthFlag11.trim();
	}

	public void setSignerAuthFlag11(String signerAuthFlag11) {
		this.signerAuthFlag11 = signerAuthFlag11;
	}

	public String getVoicefileAuthFlag4() {
		return this.voicefileAuthFlag4 = voicefileAuthFlag4 == null ? "0" : voicefileAuthFlag4.trim();
	}

	public void setVoicefileAuthFlag4(String voicefileAuthFlag4) {
		this.voicefileAuthFlag4 = voicefileAuthFlag4;
	}

	public String getVoicefileAuthFlag5() {
		return this.voicefileAuthFlag5 = voicefileAuthFlag5 == null ? "0" : voicefileAuthFlag5.trim();
	}

	public void setVoicefileAuthFlag5(String voicefileAuthFlag5) {
		this.voicefileAuthFlag5 = voicefileAuthFlag5;
	}

	public String getIsBlackKey() {
		return this.isBlackKey = isBlackKey == null ? "1" : isBlackKey.trim();
	}

	public void setIsBlackKey(String isBlackKey) {
		this.isBlackKey = isBlackKey;
	}

	public String getIsWhiteKey() {
		return this.isWhiteKey = isWhiteKey == null ? "1" : isWhiteKey.trim();
	}

	public void setIsWhiteKey(String isWhiteKey) {
		this.isWhiteKey = isWhiteKey;
	}

	public String getSmsChannelPolicy() {
		return smsChannelPolicy;
	}

	public void setSmsChannelPolicy(String smsChannelPolicy) {
		this.smsChannelPolicy = smsChannelPolicy;
	}

	public String getDowmMobileFlag() {
		return dowmMobileFlag;
	}

	public void setDowmMobileFlag(String dowmMobileFlag) {
		this.dowmMobileFlag = dowmMobileFlag;
	}

	public Integer getSecondItem() {
		return this.secondItem = secondItem == null ? -1 : secondItem;
	}

	public void setSecondItem(Integer secondItem) {
		this.secondItem = secondItem;
	}

	public Integer getSecondNum() {
		return this.secondNum = secondNum == null ? -1 : secondNum;
	}

	public void setSecondNum(Integer secondNum) {
		this.secondNum = secondNum;
	}

	public Integer getMinuteItem() {
		return this.minuteItem = minuteItem == null ? -1 : minuteItem;
	}

	public void setMinuteItem(Integer minuteItem) {
		this.minuteItem = minuteItem;
	}

	public Integer getMinuteNum() {
		return this.minuteNum = minuteNum == null ? -1 : minuteNum;
	}

	public void setMinuteNum(Integer minuteNum) {
		this.minuteNum = minuteNum;
	}

	public Integer getHourItem() {
		return this.hourItem = hourItem == null ? -1 : hourItem;
	}

	public void setHourItem(Integer hourItem) {
		this.hourItem = hourItem;
	}

	public Integer getHourNum() {
		return this.hourNum = hourNum == null ? -1 : hourNum;
	}

	public void setHourNum(Integer hourNum) {
		this.hourNum = hourNum;
	}

	public String getIsBlackAuditFlag() {
		return this.isBlackAuditFlag = isBlackAuditFlag == null ? "1" : isBlackAuditFlag;
	}

	public void setIsBlackAuditFlag(String isBlackAuditFlag) {
		this.isBlackAuditFlag = isBlackAuditFlag;
	}

	public String getSmsFilterPolicy() {
		return smsFilterPolicy;
	}

	public void setSmsFilterPolicy(String smsFilterPolicy) {
		this.smsFilterPolicy = smsFilterPolicy;
	}

	public String getSmsNoFilterPolicy() {
		return smsNoFilterPolicy;
	}

	public void setSmsNoFilterPolicy(String smsNoFilterPolicy) {
		this.smsNoFilterPolicy = smsNoFilterPolicy;
	}

	public String getCmppAccessCode() {
		return cmppAccessCode == null ? "" : cmppAccessCode.trim();
	}

	public void setCmppAccessCode(String cmppAccessCode) {
		this.cmppAccessCode = cmppAccessCode;
	}

	public String getYdExtNumber() {
		return ydExtNumber == null ? "" : ydExtNumber.trim();
	}

	public void setYdExtNumber(String ydExtNumber) {
		this.ydExtNumber = ydExtNumber;
	}

	public String getLtExtNumber() {
		return ltExtNumber == null ? "" : ltExtNumber.trim();
	}

	public void setLtExtNumber(String ltExtNumber) {
		this.ltExtNumber = ltExtNumber;
	}

	public String getDxExtNumber() {
		return dxExtNumber == null ? "" : dxExtNumber.trim();
	}

	public void setDxExtNumber(String dxExtNumber) {
		this.dxExtNumber = dxExtNumber;
	}

	public String getCmppAccessAccount() {
		return cmppAccessAccount;
	}

	public void setCmppAccessAccount(String cmppAccessAccount) {
		this.cmppAccessAccount = cmppAccessAccount;
	}

	public String getCmppAccessPwd() {
		return cmppAccessPwd;
	}

	public void setCmppAccessPwd(String cmppAccessPwd) {
		this.cmppAccessPwd = cmppAccessPwd;
	}

	public Integer getAccSendPerSecond() {
		if(accSendPerSecond==null){
			accSendPerSecond=0;
		}
		return accSendPerSecond;
	}

	public void setAccSendPerSecond(Integer accSendPerSecond) {
		this.accSendPerSecond = accSendPerSecond;
	}

	
}