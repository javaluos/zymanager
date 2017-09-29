package com.zy.cms.vo.query;

import java.util.Date;

public class CdrMonitorNoticeLogQuery {
	
    private Integer id;

    private String monitorBody;//告警主体

    private String monitorType;//告警类型 0.平台语音 1.平台短信 2.客户语音 3.客户短信 4.通道跑量 5.通道余额 6.客户余额

    private String monitorContent;//告警内容

    private String dealUser;//处理人 

    private Date statisticalTime;//统计时间

    private String isUpMonitor;//是否升级告警 1.未升级 2.已升级
    
    private String isDeal;//是否处理 1.未处理 2.已处理
    
    private Date createTime;

    private Date updateTime;
    
    private String starttime;// 加入开始时间
    
	private String endtime;// 加入结束时间
    
    private Integer pageNum;// 页号
	
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数

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
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getMonitorBody() {
		return monitorBody == null ? "" : monitorBody.trim();
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

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	
}