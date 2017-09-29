package com.zy.cms.vo.query;

import java.util.List;

/**
 * 数据分析,查询器
 * 
 * @author allen.yuan
 * @date 2016-9-02
 */
public class CdrDailyStatisticsQuery {

	private String apiAccount;//账户号
	private String merchantPhone;//手机号码
	private Integer querytype;// 应用类型 ([语音通知:4,语音验证码:5,直播电话:3,回拨电话:1])
	private String dateTimeStart;//时间开始
	private String dateTimeEnd;//时间结束
	private double pctCallSucStart;//接通率开始
	private double pctCallSucEnd;//接通率结束
	private double pctResponseSucStart;// 应答率开始
	private double pctResponseSucEnd;// 应答率结束
	private double avgAcdStart;// 平均通话时长开始
	private double avgAcdEnd;// 平均通话时长结束
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	
	private double pctCallSucStartA;//A路接通率开始
	private double pctCallSucEndA;//A路接通率结束
	private double pctCallSucStartB;//B路接通率开始
	private double pctCallSucEndB;//B路接通率结束
	private double pctResponseSucStartA;// A路应答率开始
	private double pctResponseSucEndA;// A路应答率结束
	private double pctResponseSucStartB;// B路应答率开始
	private double pctResponseSucEndB;// B路应答率结束
	
	private List apiAccounts;//账户绑定的客户
	
	public double getPctCallSucStart() {
		return pctCallSucStart;
	}

	public void setPctCallSucStart(double pctCallSucStart) {
		this.pctCallSucStart = pctCallSucStart;
	}

	public double getPctCallSucEnd() {
		return pctCallSucEnd;
	}

	public void setPctCallSucEnd(double pctCallSucEnd) {
		this.pctCallSucEnd = pctCallSucEnd;
	}

	public double getPctResponseSucStart() {
		return pctResponseSucStart;
	}

	public void setPctResponseSucStart(double pctResponseSucStart) {
		this.pctResponseSucStart = pctResponseSucStart;
	}

	public double getPctResponseSucEnd() {
		return pctResponseSucEnd;
	}

	public void setPctResponseSucEnd(double pctResponseSucEnd) {
		this.pctResponseSucEnd = pctResponseSucEnd;
	}

	public String getDateTimeStart() {
		return dateTimeStart == null ? "" : dateTimeStart.trim();
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public String getDateTimeEnd() {
		return dateTimeEnd == null ? "" : dateTimeEnd.trim();
	}

	public void setDateTimeEnd(String dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public double getAvgAcdStart() {
		return avgAcdStart;
	}

	public void setAvgAcdStart(double avgAcdStart) {
		this.avgAcdStart = avgAcdStart;
	}

	public double getAvgAcdEnd() {
		return avgAcdEnd;
	}

	public void setAvgAcdEnd(double avgAcdEnd) {
		this.avgAcdEnd = avgAcdEnd;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
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

	public double getPctCallSucStartA() {
		return pctCallSucStartA;
	}

	public void setPctCallSucStartA(double pctCallSucStartA) {
		this.pctCallSucStartA = pctCallSucStartA;
	}

	public double getPctCallSucEndA() {
		return pctCallSucEndA;
	}

	public void setPctCallSucEndA(double pctCallSucEndA) {
		this.pctCallSucEndA = pctCallSucEndA;
	}

	public double getPctCallSucStartB() {
		return pctCallSucStartB;
	}

	public void setPctCallSucStartB(double pctCallSucStartB) {
		this.pctCallSucStartB = pctCallSucStartB;
	}

	public double getPctCallSucEndB() {
		return pctCallSucEndB;
	}

	public void setPctCallSucEndB(double pctCallSucEndB) {
		this.pctCallSucEndB = pctCallSucEndB;
	}

	public double getPctResponseSucStartA() {
		return pctResponseSucStartA;
	}

	public void setPctResponseSucStartA(double pctResponseSucStartA) {
		this.pctResponseSucStartA = pctResponseSucStartA;
	}

	public double getPctResponseSucEndA() {
		return pctResponseSucEndA;
	}

	public void setPctResponseSucEndA(double pctResponseSucEndA) {
		this.pctResponseSucEndA = pctResponseSucEndA;
	}

	public double getPctResponseSucStartB() {
		return pctResponseSucStartB;
	}

	public void setPctResponseSucStartB(double pctResponseSucStartB) {
		this.pctResponseSucStartB = pctResponseSucStartB;
	}

	public double getPctResponseSucEndB() {
		return pctResponseSucEndB;
	}

	public void setPctResponseSucEndB(double pctResponseSucEndB) {
		this.pctResponseSucEndB = pctResponseSucEndB;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}
	
	
}
