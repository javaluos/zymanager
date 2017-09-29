package com.zy.cms.vo.manager;

import java.util.Date;

public class CdrMonitorNoticeLog {
	
    private Integer id;

    private String monitorBody;//告警主体

    private String monitorType;//告警类型  1.平台语音 2.平台短信 3.客户语音 4.客户短信 5.通道跑量 6.通道余额 7.客户余额

    private String monitorContent;//告警内容

    private String dealUser;//处理人 

    private Date statisticalTime;//统计时间

    private String isUpMonitor;//是否升级告警  1.未升级 2.已升级
    
    private String isDeal;//是否处理 1.未处理 2.已处理
    
    private Date createTime;

    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMonitorBody() {
		return monitorBody;
	}

	public void setMonitorBody(String monitorBody) {
		this.monitorBody = monitorBody;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public String getMonitorContent() {
		return monitorContent;
	}

	public void setMonitorContent(String monitorContent) {
		this.monitorContent = monitorContent;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public Date getStatisticalTime() {
		return statisticalTime;
	}

	public void setStatisticalTime(Date statisticalTime) {
		this.statisticalTime = statisticalTime;
	}

	public String getIsUpMonitor() {
		return isUpMonitor;
	}

	public void setIsUpMonitor(String isUpMonitor) {
		this.isUpMonitor = isUpMonitor;
	}

	public String getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(String isDeal) {
		this.isDeal = isDeal;
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
}