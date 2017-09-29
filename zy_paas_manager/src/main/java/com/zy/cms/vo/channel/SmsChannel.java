package com.zy.cms.vo.channel;

import java.io.Serializable;

public class SmsChannel implements Serializable {

	private static final long serialVersionUID = -2918485959198797003L;

	private String channelId; // 通道ID
	private String channelMainCode;// 通道编号
	private String channelName; // 通道名称
	private String channelCode; // 通道号(运营商)
	private Integer channelAccessMode; // 通道接入模式(0:非直连;1:直连)
	private Integer channelType; // 通道类型(1:通知;2:验证码;3:营销;4:通知、验证码;5:通知、验证码、营销)
	private String channelProperty; // 通道属性(0:全网;10:移动;11:移动三网;20:电信;21:电信三网;30:联通;31:联通三网;40:国际)
	private Integer operateType; // 运营商类型(0:三网合一;1:移动专网;2:电信专网;3:联通专网)
	private Integer moFlag; // 是否支持上行(0:不支持;1:支持)
	private Integer longSmsFlag;// 是否支持长短信(0:不支持;1:支持)
	private Integer signerAudit; // 签名是否需要报备(0:表示签名不需要报备;1:表示签名需要报备)
	private Integer templateAudit; // 模板是否需要报备(0:表示模板不需要报备;1:表示模板需要报备)
	private String dtnProvince;//落地省份
	private Integer useProvince;//跑量省份类型(0:全国;1:本省;2:非本省)
	private Integer crestValue;// 峰值
	private Integer chargeType;//付费类型(0:预付费;1:后付费)
	private String channelFee;//通道资费(单位元)
	private Integer channelBalance;//通道余额(金额/条数)
	private Integer balanceUnit;//余额单位(0:厘(1000=1元);1:条)
	private Integer balanceMonitorFlag;//余额监控状态(0:未监控;1:已监控)
	private Integer status;// 通道状态(0:新创建;1:运营中;2:对接中;3:作废)
	private Integer channelScore; // 通道评分
	
	private String channelUserId; // 通道userid
	private String channelAccount;// 通道账号
	private String channelPassword;// 通道密码
	private String channelUrl;// 通道url
	private String channelComment; // 通道备注
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private String apiAccount;
	private Integer thresholdValue; //发送阀值
	private Integer monitorBalance; //告警值(金额/条)

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getChannelMainCode() {
		return channelMainCode;
	}

	public void setChannelMainCode(String channelMainCode) {
		this.channelMainCode = channelMainCode;
	}

	public Integer getSignerAudit() {
		return signerAudit;
	}

	public void setSignerAudit(Integer signerAudit) {
		this.signerAudit = signerAudit;
	}

	public Integer getTemplateAudit() {
		return templateAudit;
	}

	public void setTemplateAudit(Integer templateAudit) {
		this.templateAudit = templateAudit;
	}

	public String getChannelUserId() {
		return channelUserId;
	}

	public void setChannelUserId(String channelUserId) {
		this.channelUserId = channelUserId;
	}

	public String getChannelAccount() {
		return channelAccount;
	}

	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}

	public String getChannelPassword() {
		return channelPassword;
	}

	public void setChannelPassword(String channelPassword) {
		this.channelPassword = channelPassword;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getChannelComment() {
		return channelComment;
	}

	public void setChannelComment(String channelComment) {
		this.channelComment = channelComment;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelProperty() {
		return channelProperty;
	}

	public void setChannelProperty(String channelProperty) {
		this.channelProperty = channelProperty;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public Integer getMoFlag() {
		return moFlag;
	}

	public void setMoFlag(Integer moFlag) {
		this.moFlag = moFlag;
	}

	public Integer getLongSmsFlag() {
		return longSmsFlag;
	}

	public void setLongSmsFlag(Integer longSmsFlag) {
		this.longSmsFlag = longSmsFlag;
	}

	public Integer getCrestValue() {
		return crestValue;
	}

	public void setCrestValue(Integer crestValue) {
		this.crestValue = crestValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getDtnProvince() {
		return dtnProvince;
	}

	public void setDtnProvince(String dtnProvince) {
		this.dtnProvince = dtnProvince;
	}

	public Integer getUseProvince() {
		return useProvince;
	}

	public void setUseProvince(Integer useProvince) {
		this.useProvince = useProvince;
	}

	public Integer getChannelScore() {
		return channelScore;
	}

	public void setChannelScore(Integer channelScore) {
		this.channelScore = channelScore;
	}
	
	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}
	
	public Integer getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	
	public Integer getChargeType() {
		return chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}
	
	public String getChannelFee() {
		return channelFee;
	}

	public void setChannelFee(String channelFee) {
		this.channelFee = channelFee;
	}

	public Integer getChannelBalance() {
		return channelBalance;
	}

	public void setChannelBalance(Integer channelBalance) {
		this.channelBalance = channelBalance;
	}

	public Integer getBalanceUnit() {
		return balanceUnit;
	}

	public void setBalanceUnit(Integer balanceUnit) {
		this.balanceUnit = balanceUnit;
	}

	public Integer getBalanceMonitorFlag() {
		return balanceMonitorFlag;
	}

	public void setBalanceMonitorFlag(Integer balanceMonitorFlag) {
		this.balanceMonitorFlag = balanceMonitorFlag;
	}

	public Integer getMonitorBalance() {
		return monitorBalance;
	}

	public void setMonitorBalance(Integer monitorBalance) {
		this.monitorBalance = monitorBalance;
	}

	public Integer getChannelAccessMode() {
		if (channelAccessMode == null) {
			channelAccessMode = 0;
		}
		return channelAccessMode;
	}

	public void setChannelAccessMode(Integer channelAccessMode) {
		this.channelAccessMode = channelAccessMode;
	}
	@Override

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmsChannel other = (SmsChannel) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		return true;
	}

}
