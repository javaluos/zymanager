package com.zy.cms.vo.query;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果查询转换器
 * 
 * @author allen.yuan
 * @date 2016-9-6
 * 
 */
public class ResultQuery<T> {

	private List<T> data;
	private Long times = 0L;
	private Long total_page = 0L;
	private Long total = 0L;
	private Long page_num = 0L;
	private Long page_size = 0L;
	private long viewcount = 10;// 分页列表数量
	private long pgstartno = 1;// 分页开始页

	private int sumCallTimes;// 通话总次数
	private int sumFeeTimes;// 通话总计费时长
	private int sumResponseTimes;// 通话总计费条数
	private int sumCallSucTimes; // 总接通次数
	private double pctCallSucs;// 接通率//平均连通率
	private double pctResponseSucs;// 平均应答率
	private int avgAcds;// 平均ACD

	private int sumHoldingTime; // 通话时长(总和)
	private int sumAHoldingTimes;// 平均A路通话时长
	private int sumBHoldingTimes;// 平均B路通话时长
	private double pctACallSucdoubles;// 平均A路接通率
	private double pctBCallSucdoubles;// 平均B路接通率
	private double pctAResponseSucdouble;// 平均A路应答率
	private double pctBResponseSucdouble;// 平均B路应答率
	private int avgACalleepddTime;// 平均A路接通时延
	private int avgBCalleepddTime;// 平均B路接通时延

	private double sumFeeBs1;// 回拨总费用
	private double sumFeeBs2;// 号码卫士总费用
	private double sumFeeBs3;// SDK拨打总费用
	private double sumFeeBs4;// 语音通知总费用
	private double sumFeeBs5;// 语音验证码总费用
	private double sumFeeBs6;// 呼叫中心总费用
	private double sumFeeBs7;// 多方通话总费用
	private double sumFeeBs8;
	private double sumFeeBs9;
	private double sumFeeBs10;
	private double sumFeeBs11;
	private Double sumFeed;// 业务总费用(每月)
	private String dateTime;// 时间

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

	public int getSumCallTimes() {
		return sumCallTimes;
	}

	public void setSumCallTimes(int sumCallTimes) {
		this.sumCallTimes = sumCallTimes;
	}

	public int getSumFeeTimes() {
		return sumFeeTimes;
	}

	public void setSumFeeTimes(int sumFeeTimes) {
		this.sumFeeTimes = sumFeeTimes;
	}

	public int getSumResponseTimes() {
		return sumResponseTimes;
	}

	public void setSumResponseTimes(int sumResponseTimes) {
		this.sumResponseTimes = sumResponseTimes;
	}

	public double getPctCallSucs() {
		return pctCallSucs;
	}

	public void setPctCallSucs(double pctCallSucs) {
		this.pctCallSucs = pctCallSucs;
	}

	public double getPctResponseSucs() {
		return pctResponseSucs;
	}

	public void setPctResponseSucs(double pctResponseSucs) {
		this.pctResponseSucs = pctResponseSucs;
	}

	public int getAvgAcds() {
		return avgAcds;
	}

	public void setAvgAcds(int avgAcds) {
		this.avgAcds = avgAcds;
	}

	public void setViewcount(long viewcount) {
		this.viewcount = viewcount;
	}

	public void setPgstartno(long pgstartno) {
		this.pgstartno = pgstartno;
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

	public double getSumFeeBs1() {
		return sumFeeBs1;
	}

	public void setSumFeeBs1(double sumFeeBs1) {
		this.sumFeeBs1 = sumFeeBs1;
	}

	public double getSumFeeBs2() {
		return sumFeeBs2;
	}

	public void setSumFeeBs2(double sumFeeBs2) {
		this.sumFeeBs2 = sumFeeBs2;
	}

	public double getSumFeeBs3() {
		return sumFeeBs3;
	}

	public void setSumFeeBs3(double sumFeeBs3) {
		this.sumFeeBs3 = sumFeeBs3;
	}

	public double getSumFeeBs4() {
		return sumFeeBs4;
	}

	public void setSumFeeBs4(double sumFeeBs4) {
		this.sumFeeBs4 = sumFeeBs4;
	}

	public double getSumFeeBs5() {
		return sumFeeBs5;
	}

	public void setSumFeeBs5(double sumFeeBs5) {
		this.sumFeeBs5 = sumFeeBs5;
	}

	public double getSumFeeBs6() {
		return sumFeeBs6;
	}

	public void setSumFeeBs6(double sumFeeBs6) {
		this.sumFeeBs6 = sumFeeBs6;
	}

	public double getSumFeeBs7() {
		return sumFeeBs7;
	}

	public void setSumFeeBs7(double sumFeeBs7) {
		this.sumFeeBs7 = sumFeeBs7;
	}

	public double getSumFeeBs8() {
		return sumFeeBs8;
	}

	public void setSumFeeBs8(double sumFeeBs8) {
		this.sumFeeBs8 = sumFeeBs8;
	}

	public double getSumFeeBs9() {
		return sumFeeBs9;
	}

	public void setSumFeeBs9(double sumFeeBs9) {
		this.sumFeeBs9 = sumFeeBs9;
	}

	public double getSumFeeBs10() {
		return sumFeeBs10;
	}

	public void setSumFeeBs10(double sumFeeBs10) {
		this.sumFeeBs10 = sumFeeBs10;
	}

	public double getSumFeeBs11() {
		return sumFeeBs11;
	}

	public void setSumFeeBs11(double sumFeeBs11) {
		this.sumFeeBs11 = sumFeeBs11;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double getSumFeed() {
		return sumFeed;
	}

	public void setSumFeed(Double sumFeed) {
		this.sumFeed = sumFeed;
	}

	public int getSumHoldingTime() {
		return sumHoldingTime;
	}

	public void setSumHoldingTime(int sumHoldingTime) {
		this.sumHoldingTime = sumHoldingTime;
	}

	public int getSumCallSucTimes() {
		return sumCallSucTimes;
	}

	public void setSumCallSucTimes(int sumCallSucTimes) {
		this.sumCallSucTimes = sumCallSucTimes;
	}

}