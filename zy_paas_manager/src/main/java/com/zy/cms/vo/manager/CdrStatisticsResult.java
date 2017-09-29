package com.zy.cms.vo.manager;

/**
 * 话单统计结果
 * 
 * @author allen.yuan
 * @date 2016-10-25
 *
 */
public class CdrStatisticsResult {

	private int total_count = 0;// 总条数
	private int total_call_times = 0;// 总呼叫次数
	private int total_fee_time = 0;// 总计费时长
	private int total_sum_response_times = 0;// 计费次数
	private int total_call_suc_times = 0; //总接通次数
	private double total_call_suc = 0;// 平均接通率
	private double total_response_suc = 0;// 平均应答率
	private int total_avg_acd = 0;// 平均acd
	
	private int sumHoldingTime;//通话时长(总和)
	private int sumAHoldingTimes;//平均A路通话时长
	private int sumBHoldingTimes;//平均B路通话时长
	private double pctACallSucdoubles;//平均A路接通率
	private double pctBCallSucdoubles;//平均B路接通率
	private double pctAResponseSucdouble;//平均A路应答率
	private double pctBResponseSucdouble;//平均B路应答率
	private int avgACalleepddTime;//平均A路接通时延
	private int avgBCalleepddTime;//平均B路接通时延

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public int getTotal_call_times() {
		return total_call_times;
	}

	public void setTotal_call_times(int total_call_times) {
		this.total_call_times = total_call_times;
	}

	public int getTotal_fee_time() {
		return total_fee_time;
	}

	public void setTotal_fee_time(int total_fee_time) {
		this.total_fee_time = total_fee_time;
	}

	public double getTotal_call_suc() {
		return total_call_suc;
	}

	public void setTotal_call_suc(double total_call_suc) {
		this.total_call_suc = total_call_suc;
	}

	public double getTotal_response_suc() {
		return total_response_suc;
	}

	public void setTotal_response_suc(double total_response_suc) {
		this.total_response_suc = total_response_suc;
	}

	public int getTotal_avg_acd() {
		return total_avg_acd;
	}

	public void setTotal_avg_acd(int total_avg_acd) {
		this.total_avg_acd = total_avg_acd;
	}

	public int getTotal_sum_response_times() {
		return total_sum_response_times;
	}

	public void setTotal_sum_response_times(int total_sum_response_times) {
		this.total_sum_response_times = total_sum_response_times;
	}

	public int getSumAHoldingTimes() {
		return sumAHoldingTimes;
	}

	public void setSumAHoldingTimes(int sumAHoldingTimes) {
		this.sumAHoldingTimes = sumAHoldingTimes;
	}

	public int getSumBHoldingTimes() {
		return sumBHoldingTimes;
	}

	public void setSumBHoldingTimes(int sumBHoldingTimes) {
		this.sumBHoldingTimes = sumBHoldingTimes;
	}

	public double getPctACallSucdoubles() {
		return pctACallSucdoubles;
	}

	public void setPctACallSucdoubles(double pctACallSucdoubles) {
		this.pctACallSucdoubles = pctACallSucdoubles;
	}

	public double getPctBCallSucdoubles() {
		return pctBCallSucdoubles;
	}

	public void setPctBCallSucdoubles(double pctBCallSucdoubles) {
		this.pctBCallSucdoubles = pctBCallSucdoubles;
	}

	public double getPctAResponseSucdouble() {
		return pctAResponseSucdouble;
	}

	public void setPctAResponseSucdouble(double pctAResponseSucdouble) {
		this.pctAResponseSucdouble = pctAResponseSucdouble;
	}

	public double getPctBResponseSucdouble() {
		return pctBResponseSucdouble;
	}

	public void setPctBResponseSucdouble(double pctBResponseSucdouble) {
		this.pctBResponseSucdouble = pctBResponseSucdouble;
	}

	public int getAvgACalleepddTime() {
		return avgACalleepddTime;
	}

	public void setAvgACalleepddTime(int avgACalleepddTime) {
		this.avgACalleepddTime = avgACalleepddTime;
	}

	public int getAvgBCalleepddTime() {
		return avgBCalleepddTime;
	}

	public void setAvgBCalleepddTime(int avgBCalleepddTime) {
		this.avgBCalleepddTime = avgBCalleepddTime;
	}

	public int getSumHoldingTime() {
		return sumHoldingTime;
	}

	public void setSumHoldingTime(int sumHoldingTime) {
		this.sumHoldingTime = sumHoldingTime;
	}

	public int getTotal_call_suc_times() {
		return total_call_suc_times;
	}

	public void setTotal_call_suc_times(int total_call_suc_times) {
		this.total_call_suc_times = total_call_suc_times;
	}
	
}
