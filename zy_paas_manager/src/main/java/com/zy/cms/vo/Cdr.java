package com.zy.cms.vo;

import com.zy.cms.util.DateUtil;

public class Cdr {
	private String id;

	private String callid;

	private String requestId;

	private Integer type;

	private String caller;

	private String callerDisplayNumber;

	private String callee;

	private String calleeDisplayNumber;

	private Long callerInviteTime;

	private Long callerRingingBeginTime;

	private Long callerAnswerTime;

	private Long callerHangupTime;

	private Long calleeInviteTime;

	private Long calleeRingingBeginTime;

	private Long calleeAnswerTime;

	private Long calleeHangupTime;

	private Long holdTime;

	private Integer hangupCode;

	private String hangupReason;

	private Integer feeTime;

	private Integer fee;

	private Integer feeRate;

	private Integer record;

	private String recordingFileUrl;

	private Integer pushStates;

	private String apiAccount;

	private Long createTime;

	private String tableName;

	private String dtmf;

	private String appId;

	private String leg;

	private Integer state;// 通话状态

	private String createTimeStr;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid == null ? null : callid.trim();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId == null ? null : requestId.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller == null ? null : caller.trim();
	}

	public String getCallerDisplayNumber() {
		return callerDisplayNumber;
	}

	public void setCallerDisplayNumber(String callerDisplayNumber) {
		this.callerDisplayNumber = callerDisplayNumber == null ? null : callerDisplayNumber.trim();
	}

	public String getCallee() {
		return callee;
	}

	public void setCallee(String callee) {
		this.callee = callee == null ? null : callee.trim();
	}

	public String getCalleeDisplayNumber() {
		return calleeDisplayNumber;
	}

	public void setCalleeDisplayNumber(String calleeDisplayNumber) {
		this.calleeDisplayNumber = calleeDisplayNumber == null ? null : calleeDisplayNumber.trim();
	}

	public Long getCallerInviteTime() {
		return callerInviteTime;
	}

	public void setCallerInviteTime(Long callerInviteTime) {
		this.callerInviteTime = callerInviteTime;
	}

	public Long getCallerRingingBeginTime() {
		return callerRingingBeginTime;
	}

	public void setCallerRingingBeginTime(Long callerRingingBeginTime) {
		this.callerRingingBeginTime = callerRingingBeginTime;
	}

	public Long getCallerAnswerTime() {
		return callerAnswerTime;
	}

	public void setCallerAnswerTime(Long callerAnswerTime) {
		this.callerAnswerTime = callerAnswerTime;
	}

	public Long getCallerHangupTime() {
		return callerHangupTime;
	}

	public void setCallerHangupTime(Long callerHangupTime) {
		this.callerHangupTime = callerHangupTime;
	}

	public Long getCalleeInviteTime() {
		return calleeInviteTime;
	}

	public void setCalleeInviteTime(Long calleeInviteTime) {
		this.calleeInviteTime = calleeInviteTime;
	}

	public Long getCalleeRingingBeginTime() {
		return calleeRingingBeginTime;
	}

	public void setCalleeRingingBeginTime(Long calleeRingingBeginTime) {
		this.calleeRingingBeginTime = calleeRingingBeginTime;
	}

	public Long getCalleeAnswerTime() {
		return calleeAnswerTime;
	}

	public void setCalleeAnswerTime(Long calleeAnswerTime) {
		this.calleeAnswerTime = calleeAnswerTime;
	}

	public Long getCalleeHangupTime() {
		return calleeHangupTime;
	}

	public void setCalleeHangupTime(Long calleeHangupTime) {
		this.calleeHangupTime = calleeHangupTime;
	}

	public Long getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(Long holdTime) {
		this.holdTime = holdTime;
	}

	public Integer getHangupCode() {
		return hangupCode;
	}

	public void setHangupCode(Integer hangupCode) {
		this.hangupCode = hangupCode;
	}

	public String getHangupReason() {
		return hangupReason;
	}

	public void setHangupReason(String hangupReason) {
		this.hangupReason = hangupReason == null ? null : hangupReason.trim();
	}

	public Integer getFeeTime() {
		return feeTime;
	}

	public void setFeeTime(Integer feeTime) {
		this.feeTime = feeTime;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(Integer feeRate) {
		this.feeRate = feeRate;
	}

	public Integer getRecord() {
		return record;
	}

	public void setRecord(Integer record) {
		this.record = record;
	}

	public String getRecordingFileUrl() {
		return recordingFileUrl;
	}

	public void setRecordingFileUrl(String recordingFileUrl) {
		this.recordingFileUrl = recordingFileUrl;
	}

	public Integer getPushStates() {
		return pushStates;
	}

	public void setPushStates(Integer pushStates) {
		this.pushStates = pushStates;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDtmf() {
		return dtmf;
	}

	public void setDtmf(String dtmf) {
		this.dtmf = dtmf;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApiAccount() {
		return apiAccount;
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getLeg() {
		return leg;
	}

	public void setLeg(String leg) {
		this.leg = leg;
	}

	public String getCreateTimeStr() {
		if (createTimeStr == null) {
			createTimeStr = DateUtil.getDateTime(createTime);
		}
		return createTimeStr;
	}

	@Override
	public String toString() {
		return "Cdr [id=" + id + ", callid=" + callid + ", requestId=" + requestId + ", type=" + type + ", caller="
				+ caller + ", callerDisplayNumber=" + callerDisplayNumber + ", callee=" + callee
				+ ", calleeDisplayNumber=" + calleeDisplayNumber + ", callerInviteTime=" + callerInviteTime
				+ ", callerRingingBeginTime=" + callerRingingBeginTime + ", callerAnswerTime=" + callerAnswerTime
				+ ", callerHangupTime=" + callerHangupTime + ", calleeInviteTime=" + calleeInviteTime
				+ ", calleeRingingBeginTime=" + calleeRingingBeginTime + ", calleeAnswerTime=" + calleeAnswerTime
				+ ", state=" + state + ", calleeHangupTime=" + calleeHangupTime + ",  holdTime=" + holdTime
				+ ", hangupCode=" + hangupCode + ", hangupReason=" + hangupReason + ", feeTime=" + feeTime + ", fee="
				+ fee + ", feeRate=" + feeRate + ", record=" + record + ", recordingFileUrl=" + recordingFileUrl
				+ ", pushStates=" + pushStates + ", apiAccount=" + apiAccount + ", createTime=" + createTime
				+ ", tableName=" + tableName + ", dtmf=" + dtmf + ", appId=" + appId + ", leg=" + leg + "]";
	}

}