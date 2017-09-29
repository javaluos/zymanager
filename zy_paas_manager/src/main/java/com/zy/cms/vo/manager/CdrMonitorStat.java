package com.zy.cms.vo.manager;

import java.io.Serializable;
import java.util.Date;

import com.zy.cms.util.DateUtil;

public class CdrMonitorStat implements Serializable {

	private static final long serialVersionUID = -8832592231674689043L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * APIACOUNT
	 */
	private String apiAccount;

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 业务ID
	 */
	private String businessId;

	/**
	 * 呼叫总次数
	 */
	private int totalCallCount;

	/**
	 * 路接通率A
	 */
	private int successRatea;

	/**
	 * 路接通率B
	 */
	private int successRateb;

	/**
	 * A路应答率
	 */
	private int responseRatea;

	/**
	 * B路应答率
	 */
	private int responseRateb;

	/**
	 * A路通话时长
	 */
	private int talkTimea;

	/**
	 * B路通话时长
	 */
	private int talkTimeb;

	/**
	 * A路平均通话时长
	 */
	private int averageTalkTimea;

	/**
	 * B路平均通话时长
	 */
	private int averageTalkTimeb;

	/**
	 * A路平均接通时延
	 */
	private int averageTurnOnDelaya;

	/**
	 * B路平均接通时延
	 */
	private int averageTurnOnDelayb;

	/**
	 * A路平均接续时长
	 */
	private int averageInTimea;

	/**
	 * B路平均接续时长
	 */
	private int averageInTimeb;

	/**
	 * 接通次数A
	 */
	private int ringCounta;

	/**
	 * 呼叫次数A
	 */
	private int callCounta;

	/**
	 * 应答次数A
	 */
	private int responseCounta;

	/**
	 * 总接通时延A
	 */
	private int totalTurnOnDelaya;

	/**
	 * 总接续时长A
	 */
	private int totalInTimea;

	/**
	 * 接通次数B
	 */
	private int ringCountb;

	/**
	 * 呼叫次数B
	 */
	private int callCountb;

	/**
	 * 应答次数B
	 */
	private int responseCountb;

	/**
	 * 总接通时延B
	 */
	private int totalTurnOnDelayb;

	/**
	 * 总接续时长B
	 */
	private int totalInTimeb;

	/**
	 * 统计点时间
	 */
	private Date statisticalTime;

	/**
	 * 写入时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String tablesuffix;

	private String timeString;
	
	/**
	 * 短信发送时间
	 */
	private Integer sendTime=0;
	/**
	 * 短信接收时长
	 */
	private Integer reportTime=0;

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
		this.apiAccount = apiAccount;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public int getTotalCallCount() {
		return totalCallCount;
	}

	public void setTotalCallCount(int totalCallCount) {
		this.totalCallCount = totalCallCount;
	}

	public int getSuccessRatea() {
		return successRatea;
	}

	public void setSuccessRatea(int successRatea) {
		this.successRatea = successRatea;
	}

	public int getSuccessRateb() {
		return successRateb;
	}

	public void setSuccessRateb(int successRateb) {
		this.successRateb = successRateb;
	}

	public int getResponseRatea() {
		return responseRatea;
	}

	public void setResponseRatea(int responseRatea) {
		this.responseRatea = responseRatea;
	}

	public int getResponseRateb() {
		return responseRateb;
	}

	public void setResponseRateb(int responseRateb) {
		this.responseRateb = responseRateb;
	}

	public int getTalkTimea() {
		return talkTimea;
	}

	public void setTalkTimea(int talkTimea) {
		this.talkTimea = talkTimea;
	}

	public int getTalkTimeb() {
		return talkTimeb;
	}

	public void setTalkTimeb(int talkTimeb) {
		this.talkTimeb = talkTimeb;
	}

	public int getAverageTalkTimea() {
		return averageTalkTimea;
	}

	public void setAverageTalkTimea(int averageTalkTimea) {
		this.averageTalkTimea = averageTalkTimea;
	}

	public int getAverageTalkTimeb() {
		return averageTalkTimeb;
	}

	public void setAverageTalkTimeb(int averageTalkTimeb) {
		this.averageTalkTimeb = averageTalkTimeb;
	}

	public int getAverageTurnOnDelaya() {
		return averageTurnOnDelaya;
	}

	public void setAverageTurnOnDelaya(int averageTurnOnDelaya) {
		this.averageTurnOnDelaya = averageTurnOnDelaya;
	}

	public int getAverageTurnOnDelayb() {
		return averageTurnOnDelayb;
	}

	public void setAverageTurnOnDelayb(int averageTurnOnDelayb) {
		this.averageTurnOnDelayb = averageTurnOnDelayb;
	}

	public int getAverageInTimea() {
		return averageInTimea;
	}

	public void setAverageInTimea(int averageInTimea) {
		this.averageInTimea = averageInTimea;
	}

	public int getAverageInTimeb() {
		return averageInTimeb;
	}

	public void setAverageInTimeb(int averageInTimeb) {
		this.averageInTimeb = averageInTimeb;
	}

	public int getRingCounta() {
		return ringCounta;
	}

	public void setRingCounta(int ringCounta) {
		this.ringCounta = ringCounta;
	}

	public int getCallCounta() {
		return callCounta;
	}

	public void setCallCounta(int callCounta) {
		this.callCounta = callCounta;
	}

	public int getResponseCounta() {
		return responseCounta;
	}

	public void setResponseCounta(int responseCounta) {
		this.responseCounta = responseCounta;
	}

	public int getTotalTurnOnDelaya() {
		return totalTurnOnDelaya;
	}

	public void setTotalTurnOnDelaya(int totalTurnOnDelaya) {
		this.totalTurnOnDelaya = totalTurnOnDelaya;
	}

	public int getTotalInTimea() {
		return totalInTimea;
	}

	public void setTotalInTimea(int totalInTimea) {
		this.totalInTimea = totalInTimea;
	}

	public int getRingCountb() {
		return ringCountb;
	}

	public void setRingCountb(int ringCountb) {
		this.ringCountb = ringCountb;
	}

	public int getCallCountb() {
		return callCountb;
	}

	public void setCallCountb(int callCountb) {
		this.callCountb = callCountb;
	}

	public int getResponseCountb() {
		return responseCountb;
	}

	public void setResponseCountb(int responseCountb) {
		this.responseCountb = responseCountb;
	}

	public int getTotalTurnOnDelayb() {
		return totalTurnOnDelayb;
	}

	public void setTotalTurnOnDelayb(int totalTurnOnDelayb) {
		this.totalTurnOnDelayb = totalTurnOnDelayb;
	}

	public int getTotalInTimeb() {
		return totalInTimeb;
	}

	public void setTotalInTimeb(int totalInTimeb) {
		this.totalInTimeb = totalInTimeb;
	}

	public Date getStatisticalTime() {
		return statisticalTime;
	}

	public void setStatisticalTime(Date statisticalTime) {
		this.statisticalTime = statisticalTime;
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

	public String getTablesuffix() {
		return tablesuffix;
	}

	public void setTablesuffix(String tablesuffix) {
		this.tablesuffix = tablesuffix;
	}

	/**
	 * 获得当前时间(小时:分钟)
	 * 
	 * @return
	 */
	public String getTimeString() {
		timeString = "";
		if (statisticalTime != null) { 
			timeString = DateUtil.formatDate(statisticalTime ,"HH:mm");
		}
		return timeString;
	}

	public Integer getSendTime() {
		return sendTime;
	}

	public void setSendTime(Integer sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getReportTime() {
		return reportTime;
	}

	public void setReportTime(Integer reportTime) {
		this.reportTime = reportTime;
	}
	
	

}