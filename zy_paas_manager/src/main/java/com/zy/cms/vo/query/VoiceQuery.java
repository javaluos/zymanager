package com.zy.cms.vo.query;

import java.util.List;

/**
 * 数据分析,查询器
 * 
 * @author allen.yuan
 * @date 2016-9-02
 */
public class VoiceQuery {

	private String apiAccount;
	private String merchantPhone;//手机号码
	private String appid;// 应用id
	private Integer querytype;// 应用类型 ([语音通知:4,语音验证码:5,直播电话:3,回拨电话:1])
	private String caller;// 主叫号码
	private String callee;// 被叫号码
	private String calleeDisplayNumber;//被叫显号
	private Integer pushStates;//发送状态
	private Integer hangupCode;//通话结束原因编码
	private String hangupReason;//通话结束原因
	private Integer state;// 通话状态
	private String starttime;// 发送开始时间
	private String endtime;// 发送结束时间
	private Long starttimeL;// 发送开始时间
	private Long endtimeL;// 发送结束时间
	private Long createTimeStart;//创建开始时间
	private Long createTimeEnd;//创建结束时间
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	private List apiAccounts;//账户绑定的客户
    
	public Integer getHangupCode() {
		if (hangupCode == null) {
			hangupCode=0;
		}
		return hangupCode;
	}

	public void setHangupCode(Integer hangupCode) {
		this.hangupCode = hangupCode;
	}
	
	public String getCalleeDisplayNumber() {
		return calleeDisplayNumber;
	}

	public void setCalleeDisplayNumber(String calleeDisplayNumber) {
		this.calleeDisplayNumber = calleeDisplayNumber == null ? null : calleeDisplayNumber.trim();
	}
	
	public Integer getPushStates() {
		if (pushStates == null) {
			pushStates=4;
		}
		return pushStates;
	}

	public void setPushStates(Integer pushStates) {
		this.pushStates = pushStates;
	}
	public String getHangupReason() {
		return hangupReason;
	}

	public void setHangupReason(String hangupReason) {
		this.hangupReason = hangupReason == null ? null : hangupReason.trim();
	}
	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone == null ? null : merchantPhone.trim();
	}
	
	public String getApiAccount() {
		return (apiAccount == null) ? "" : apiAccount.trim();
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public Integer getQuerytype() {
		if (querytype == null) {
			querytype=0;
		}
		return querytype;
	}

	public void setQuerytype(Integer querytype) {
		this.querytype = querytype;
	}

	public String getAppid() {
		return (appid == null) ? "" : appid.trim();
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCaller() {
		return (caller == null) ? "" : caller.trim();
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getCallee() {
		return (callee == null) ? "" : callee.trim();
	}

	public void setCallee(String callee) {
		this.callee = callee;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStarttime() {
		//return (starttime == null) ? "" : starttime.trim();
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		//return (endtime == null) ? "" : endtime.trim();
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

	public Long getStarttimeL() {
		if (starttimeL==null) {
			return 0L;
		}
		//return DateUtil.date2TimeStamp(getStarttime(), DateUtil.ISO_DATE_TIME_FORMAT) / 1000;
		return this.starttimeL;
	}

	public Long getEndtimeL() {
		if (endtimeL==null) {
			return 0L;
		}
		//return DateUtil.date2TimeStamp(getEndtime(), DateUtil.ISO_DATE_TIME_FORMAT) / 1000;
		return this.endtimeL;
	}

	public Long getCreateTimeStart() {
		if (createTimeStart==null) {
			createTimeStart= 0L;
		}
		return createTimeStart;
	}

	public void setCreateTimeStart(Long createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Long getCreateTimeEnd() {
		if (createTimeEnd==null) {
			createTimeEnd= 0L;
		}
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public void setStarttimeL(Long starttimeL) {
		this.starttimeL = starttimeL;
	}

	public void setEndtimeL(Long endtimeL) {
		this.endtimeL = endtimeL;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

}
