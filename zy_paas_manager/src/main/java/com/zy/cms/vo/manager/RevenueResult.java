package com.zy.cms.vo.manager;

import java.util.ArrayList;
import java.util.List;

/**
 * 话单统计结果
 * 
 * @author allen.xu
 * @param <T>
 * @date 2016-10-25
 *
 */
public class RevenueResult<T> {

	private List<T> data;
	private Long times = 0L;
	private Long total_page = 0L;
	private Long total = 0L;
	private Long page_num = 0L;
	private Long page_size = 0L;
	private long viewcount = 10;// 分页列表数量
	private long pgstartno = 1;// 分页开始页
	
    private Integer feeCount;//计费条数(成功状态,包含长短信)

    private double inCome;//收入

    private double costFee;//成本
    
    private double profit;//利润
    
    private double costAveragePrice;//成本均价
    
    private double grossProfitRate;//毛利率

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

	public Integer getFeeCount() {
		return feeCount;
	}

	public void setFeeCount(Integer feeCount) {
		this.feeCount = feeCount;
	}

	public double getInCome() {
		return inCome;
	}

	public void setInCome(double inCome) {
		this.inCome = inCome;
	}

	public double getCostFee() {
		return costFee;
	}

	public void setCostFee(double costFee) {
		this.costFee = costFee;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getCostAveragePrice() {
		return costAveragePrice;
	}

	public void setCostAveragePrice(double costAveragePrice) {
		this.costAveragePrice = costAveragePrice;
	}

	public double getGrossProfitRate() {
		return grossProfitRate;
	}

	public void setGrossProfitRate(double grossProfitRate) {
		this.grossProfitRate = grossProfitRate;
	}

	public void setViewcount(long viewcount) {
		this.viewcount = viewcount;
	}

	public void setPgstartno(long pgstartno) {
		this.pgstartno = pgstartno;
	}

	
 }
