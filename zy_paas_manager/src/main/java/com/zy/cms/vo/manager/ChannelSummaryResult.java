package com.zy.cms.vo.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * 话单统计结果
 * 
 * @author allen.yuan
 * @param <T>
 * @date 2016-10-25
 *
 */
public class ChannelSummaryResult<T> {

	private List<T> data;
	private Long times = 0L;
	private Long total_page = 0L;
	private Long total = 0L;
	private Long page_num = 0L;
	private Long page_size = 0L;
	private long viewcount = 10;// 分页列表数量
	private long pgstartno = 1;// 分页开始页
	
	private Integer sendCounts;//发送条数    
    private Integer successCounts;//结果成功条数     
    private Integer failedCounts;//结果失败条数 
    private Integer noreportCounts;//结果未知条数    
    private Integer feeCounts;//计费条数(成功状态,包含长短信)     
    private Double successSendRates;//发送成功率   
    private Double failSendRates;//发送失败率  
    private Double noreportRates;//未知状态比例   
    private Integer avgSendTimes;//平均发送时长(秒) 
    private Integer avgStatusTimes;//平均状态报告接收时长(秒)
    private Integer sdUs10sCounts;//10秒内到达数量   
    private Double sdUs10sCountRates;//10秒内到达率
    private Integer sdUs50sCounts;//50秒内到达数量   
    private Double sdUs50sCountRates;//50秒内到达率
    private Integer sdUsgt50sCounts;//大于50秒内到达数量
    private Double  sdUsgt50sCountRates;//大于50秒内到达率
    private Integer stBk24hCounts;//状态报告24小时返回数量    
    private Double stBk24hCountRates;//状态报告24小时到达率
    private Integer stBk48hCounts;//状态报告48小时返回数量    
    private Double stBk48hCountRates;//状态报告48小时到达率
    private Integer stBk72hCounts;//状态报告72小时返回数量      
    private Double stBk72hCountRates;//状态报告72小时到达率

    public List<T> getData() {
		if (data == null) {
			data = new ArrayList<T>();
		}
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getTimes() {
		return times;
	}

	public void setTimes(Long times) {
		this.times = times;
	}

	public Long getTotal_page() {
		total_page = (total / page_size) + (total % page_size != 0 ? 1 : 0);
		return total_page;
	}

	public void setTotal_page(Long total_page) {
		this.total_page = total_page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPage_num() {
		return page_num;
	}

	public void setPage_num(Long page_num) {
		this.page_num = page_num;
	}

	public Long getPage_size() {
		return page_size;
	}

	public void setPage_size(Long page_size) {
		this.page_size = page_size;
	}

	public long getViewcount() {
		if (viewcount > total_page) {
			viewcount = total_page;
		}
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public long getPgstartno() {
		pgstartno = ((page_num - 1) / viewcount) * viewcount;
		return pgstartno;
	}

	public void setPgstartno(int pgstartno) {
		this.pgstartno = pgstartno;
	}

	public Integer getSendCounts() {
		return sendCounts;
	}

	public void setSendCounts(Integer sendCounts) {
		this.sendCounts = sendCounts;
	}

	public Integer getSuccessCounts() {
		return successCounts;
	}

	public void setSuccessCounts(Integer successCounts) {
		this.successCounts = successCounts;
	}

	public Integer getFailedCounts() {
		return failedCounts;
	}

	public void setFailedCounts(Integer failedCounts) {
		this.failedCounts = failedCounts;
	}

	public Integer getNoreportCounts() {
		return noreportCounts;
	}

	public void setNoreportCounts(Integer noreportCounts) {
		this.noreportCounts = noreportCounts;
	}

	public Integer getFeeCounts() {
		return feeCounts;
	}

	public void setFeeCounts(Integer feeCounts) {
		this.feeCounts = feeCounts;
	}

	public Double getSuccessSendRates() {
		return successSendRates;
	}

	public void setSuccessSendRates(Double successSendRates) {
		this.successSendRates = successSendRates;
	}

	public Double getFailSendRates() {
		return failSendRates;
	}

	public void setFailSendRates(Double failSendRates) {
		this.failSendRates = failSendRates;
	}

	public Double getNoreportRates() {
		return noreportRates;
	}

	public void setNoreportRates(Double noreportRates) {
		this.noreportRates = noreportRates;
	}

	public Integer getAvgSendTimes() {
		return avgSendTimes;
	}

	public void setAvgSendTimes(Integer avgSendTimes) {
		this.avgSendTimes = avgSendTimes;
	}

	public Integer getAvgStatusTimes() {
		return avgStatusTimes;
	}

	public void setAvgStatusTimes(Integer avgStatusTimes) {
		this.avgStatusTimes = avgStatusTimes;
	}

	public Integer getSdUs10sCounts() {
		return sdUs10sCounts;
	}

	public void setSdUs10sCounts(Integer sdUs10sCounts) {
		this.sdUs10sCounts = sdUs10sCounts;
	}

	public Double getSdUs10sCountRates() {
		return sdUs10sCountRates;
	}

	public void setSdUs10sCountRates(Double sdUs10sCountRates) {
		this.sdUs10sCountRates = sdUs10sCountRates;
	}

	public Integer getSdUs50sCounts() {
		return sdUs50sCounts;
	}

	public void setSdUs50sCounts(Integer sdUs50sCounts) {
		this.sdUs50sCounts = sdUs50sCounts;
	}

	public Double getSdUs50sCountRates() {
		return sdUs50sCountRates;
	}

	public void setSdUs50sCountRates(Double sdUs50sCountRates) {
		this.sdUs50sCountRates = sdUs50sCountRates;
	}

	public Integer getSdUsgt50sCounts() {
		return sdUsgt50sCounts;
	}

	public void setSdUsgt50sCounts(Integer sdUsgt50sCounts) {
		this.sdUsgt50sCounts = sdUsgt50sCounts;
	}

	public Double getSdUsgt50sCountRates() {
		return sdUsgt50sCountRates;
	}

	public void setSdUsgt50sCountRates(Double sdUsgt50sCountRates) {
		this.sdUsgt50sCountRates = sdUsgt50sCountRates;
	}

	public Integer getStBk24hCounts() {
		return stBk24hCounts;
	}

	public void setStBk24hCounts(Integer stBk24hCounts) {
		this.stBk24hCounts = stBk24hCounts;
	}

	public Double getStBk24hCountRates() {
		return stBk24hCountRates;
	}

	public void setStBk24hCountRates(Double stBk24hCountRates) {
		this.stBk24hCountRates = stBk24hCountRates;
	}

	public Integer getStBk48hCounts() {
		return stBk48hCounts;
	}

	public void setStBk48hCounts(Integer stBk48hCounts) {
		this.stBk48hCounts = stBk48hCounts;
	}

	public Double getStBk48hCountRates() {
		return stBk48hCountRates;
	}

	public void setStBk48hCountRates(Double stBk48hCountRates) {
		this.stBk48hCountRates = stBk48hCountRates;
	}

	public Integer getStBk72hCounts() {
		return stBk72hCounts;
	}

	public void setStBk72hCounts(Integer stBk72hCounts) {
		this.stBk72hCounts = stBk72hCounts;
	}

	public Double getStBk72hCountRates() {
		return stBk72hCountRates;
	}

	public void setStBk72hCountRates(Double stBk72hCountRates) {
		this.stBk72hCountRates = stBk72hCountRates;
	}
	
 }
