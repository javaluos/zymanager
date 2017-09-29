package com.zy.cms.vo;

import java.io.Serializable;

/**
 * 短信接收
 * 
 * @author quanjs
 *
 */
public class SmsReply implements Serializable {

	private static final long serialVersionUID = -1858717056016369176L;
	private String id;
	private String apiAccount;// 用户账号
	private String channelId; // 通道id
	private String channelAccount; // 通道account
	private String ext_number;// 扩展号
	private String mobile;// 手机号码
	private String content;// 短信回复内容
	private String taskId;// 同批次任务id
	private String reply_time;// 接收时间(格式如：2017-02-10 15:34:55)
	private String base_extend;// 系统扩展号
	private Integer status = 0;
	private String smsId = "";// 短信Id
	private String smsContent = "";// 短信内容
	private String createTime = "";

	public String getApiAccount() {
		return apiAccount == null ? "" : apiAccount.trim();
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getId() {
		return id == null ? "" : id.trim();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelId() {
		return channelId == null ? "" : channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getMobile() {
		return mobile == null ? "" : mobile.trim();
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content == null ? "" : content.trim();
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTaskId() {
		return taskId == null ? "" : taskId.trim();
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getReply_time() {
		if (reply_time != null && reply_time.length() > 19) {
			reply_time = reply_time.substring(0, 19);// 接收时间(格式如：2017-02-10
														// 15:34:55)
		}
		return reply_time == null ? "" : reply_time.trim();
	}

	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
	}

	public String getBase_extend() {
		return base_extend == null ? "" : base_extend.trim();
	}

	public void setBase_extend(String base_extend) {
		this.base_extend = base_extend;
	}

	public String getCreateTime() {
		return createTime == null ? "" : createTime.trim();
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getExt_number() {
		return ext_number == null ? "" : ext_number.trim();
	}

	public void setExt_number(String ext_number) {
		this.ext_number = ext_number;
	}

	public String getSmsId() {
		return smsId == null ? "" : smsId.trim();
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsContent() {
		return smsContent == null ? "" : smsContent.trim();
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getChannelAccount() {
		return channelAccount == null ? "" : channelAccount.trim();
	}

	public void setChannelAccount(String channelAccount) {
		this.channelAccount = channelAccount;
	}

	@Override
	public String toString() {
		return "SMSRec [id=" + id + ", channelId=" + channelId + ", ext_number=" + ext_number + ", mobile=" + mobile
				+ ", content=" + content + ", taskId=" + taskId + ", reply_time=" + reply_time + ", base_extend="
				+ base_extend + ", smsId=" + smsId + ", smsContent=" + smsContent + "+ , status=" + status + "]";
	}

}
