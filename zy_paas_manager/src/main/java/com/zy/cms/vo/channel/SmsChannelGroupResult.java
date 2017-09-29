package com.zy.cms.vo.channel;

public enum SmsChannelGroupResult {
	ADD_CHANNEL_GROUP_SUCCESS("1", "添加通道组成功"), CHANNEL_GROUP_NAME_REPECT(
			"2", "通道组名称已重复"), ADD_CHANNEL_GROUP_FAILED("3", "添加通道组失败");

	private String errorCode;
	private String errorDesc;

	private SmsChannelGroupResult(String errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

}
