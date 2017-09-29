package com.zy.cms.vo.manager;

/**
 * 话单统计分析实体类
 * 
 * @author allen.yuan
 * @date 2016-10-24
 */
public class CdrDailyStatistics {

	private int id;// 主键
	private String date_time;// 统计时间(yyyy-MM-dd)
	private String api_account;// 平台账户APIACOUNT
	private int type;// 业务类型:[语音通知:4,语音验证码:5,直播电话:3,回拨电话:1]
	private int sum_call_times;// 通话次数(总和)
	private int sum_a_call_times;// A路通话次数
	private int sum_b_call_times;// B路通话次数
	private int sum_holding_time;// 通话时长(总和)
	private int sum_a_holding_time;// A路通话时长
	private int sum_b_holding_time;// B路通话时长
	private int sum_calleepdd_time;// 接通时延时长(总和)
	private int sum_a_calleepdd_time;// A路接通时延时长
	private int sum_b_calleepdd_time;// B路接通时延时长
	private int sum_call_suc_times;// 接通次数(总和)
	private int sum_a_call_suc_times;// A路接通次数
	private int sum_b_call_suc_times;// B路接通次数
	private int sum_response_times;// 应答次数(总和)
	private int sum_a_response_times;// A应答次数
	private int sum_b_response_times;// B应答次数
	private int sum_fee_time;// 计费时长
	private int sum_fee;// 费用(总和)
	private double pct_call_sucdouble;// 接通率
	private double pct_a_call_sucdouble;// 路接通率
	private double pct_b_call_sucdouble;// 路接通率
	private double pct_response_sucdouble;// 应答率
	private double pct_a_response_sucdouble;// A路应答率
	private double pct_b_response_sucdouble;// B路应答率
	private int avg_calleepdd_time;// 接通时延(平均)
	private int avg_a_calleepdd_time;// A接通时延(平均)
	private int avg_b_calleepdd_time;// B接通时延(平均)
	private int avg_acd;// 平均通话时长
	private int avg_a_acd;// A路平均通话时长
	private int avg_b_acd;// B路平均通话时长
	private String create_time;// 创建时间

	private String merchant_phone;// 手机号码
	private String business_name;// 企业名称
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate_time() {
		return date_time == null ? "" : date_time.trim();
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getApi_account() {
		return api_account == null ? "" : api_account.trim();
	}

	public void setApi_account(String api_account) {
		this.api_account = api_account;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSum_call_times() {
		return sum_call_times;
	}

	public void setSum_call_times(int sum_call_times) {
		this.sum_call_times = sum_call_times;
	}

	public int getSum_a_call_times() {
		return sum_a_call_times;
	}

	public void setSum_a_call_times(int sum_a_call_times) {
		this.sum_a_call_times = sum_a_call_times;
	}

	public int getSum_b_call_times() {
		return sum_b_call_times;
	}

	public void setSum_b_call_times(int sum_b_call_times) {
		this.sum_b_call_times = sum_b_call_times;
	}

	public int getSum_holding_time() {
		return sum_holding_time;
	}

	public void setSum_holding_time(int sum_holding_time) {
		this.sum_holding_time = sum_holding_time;
	}

	public int getSum_a_holding_time() {
		return sum_a_holding_time;
	}

	public void setSum_a_holding_time(int sum_a_holding_time) {
		this.sum_a_holding_time = sum_a_holding_time;
	}

	public int getSum_b_holding_time() {
		return sum_b_holding_time;
	}

	public void setSum_b_holding_time(int sum_b_holding_time) {
		this.sum_b_holding_time = sum_b_holding_time;
	}

	public int getSum_calleepdd_time() {
		return sum_calleepdd_time;
	}

	public void setSum_calleepdd_time(int sum_calleepdd_time) {
		this.sum_calleepdd_time = sum_calleepdd_time;
	}

	public int getSum_a_calleepdd_time() {
		return sum_a_calleepdd_time;
	}

	public void setSum_a_calleepdd_time(int sum_a_calleepdd_time) {
		this.sum_a_calleepdd_time = sum_a_calleepdd_time;
	}

	public int getSum_b_calleepdd_time() {
		return sum_b_calleepdd_time;
	}

	public void setSum_b_calleepdd_time(int sum_b_calleepdd_time) {
		this.sum_b_calleepdd_time = sum_b_calleepdd_time;
	}

	public int getSum_call_suc_times() {
		return sum_call_suc_times;
	}

	public void setSum_call_suc_times(int sum_call_suc_times) {
		this.sum_call_suc_times = sum_call_suc_times;
	}

	public int getSum_a_call_suc_times() {
		return sum_a_call_suc_times;
	}

	public void setSum_a_call_suc_times(int sum_a_call_suc_times) {
		this.sum_a_call_suc_times = sum_a_call_suc_times;
	}

	public int getSum_b_call_suc_times() {
		return sum_b_call_suc_times;
	}

	public void setSum_b_call_suc_times(int sum_b_call_suc_times) {
		this.sum_b_call_suc_times = sum_b_call_suc_times;
	}

	public int getSum_response_times() {
		return sum_response_times;
	}

	public void setSum_response_times(int sum_response_times) {
		this.sum_response_times = sum_response_times;
	}

	public int getSum_a_response_times() {
		return sum_a_response_times;
	}

	public void setSum_a_response_times(int sum_a_response_times) {
		this.sum_a_response_times = sum_a_response_times;
	}

	public int getSum_b_response_times() {
		return sum_b_response_times;
	}

	public void setSum_b_response_times(int sum_b_response_times) {
		this.sum_b_response_times = sum_b_response_times;
	}

	public int getSum_fee_time() {
		return sum_fee_time;
	}

	public void setSum_fee_time(int sum_fee_time) {
		this.sum_fee_time = sum_fee_time;
	}

	public int getSum_fee() {
		return sum_fee;
	}

	public void setSum_fee(int sum_fee) {
		this.sum_fee = sum_fee;
	}

	public double getPct_call_sucdouble() {
		return pct_call_sucdouble;
	}

	public void setPct_call_sucdouble(double pct_call_sucdouble) {
		this.pct_call_sucdouble = pct_call_sucdouble;
	}

	public double getPct_a_call_sucdouble() {
		return pct_a_call_sucdouble;
	}

	public void setPct_a_call_sucdouble(double pct_a_call_sucdouble) {
		this.pct_a_call_sucdouble = pct_a_call_sucdouble;
	}

	public double getPct_b_call_sucdouble() {
		return pct_b_call_sucdouble;
	}

	public void setPct_b_call_sucdouble(double pct_b_call_sucdouble) {
		this.pct_b_call_sucdouble = pct_b_call_sucdouble;
	}

	public double getPct_response_sucdouble() {
		return pct_response_sucdouble;
	}

	public void setPct_response_sucdouble(double pct_response_sucdouble) {
		this.pct_response_sucdouble = pct_response_sucdouble;
	}

	public double getPct_a_response_sucdouble() {
		return pct_a_response_sucdouble;
	}

	public void setPct_a_response_sucdouble(double pct_a_response_sucdouble) {
		this.pct_a_response_sucdouble = pct_a_response_sucdouble;
	}

	public double getPct_b_response_sucdouble() {
		return pct_b_response_sucdouble;
	}

	public void setPct_b_response_sucdouble(double pct_b_response_sucdouble) {
		this.pct_b_response_sucdouble = pct_b_response_sucdouble;
	}

	public int getAvg_calleepdd_time() {
		return avg_calleepdd_time;
	}

	public void setAvg_calleepdd_time(int avg_calleepdd_time) {
		this.avg_calleepdd_time = avg_calleepdd_time;
	}

	public int getAvg_a_calleepdd_time() {
		return avg_a_calleepdd_time;
	}

	public void setAvg_a_calleepdd_time(int avg_a_calleepdd_time) {
		this.avg_a_calleepdd_time = avg_a_calleepdd_time;
	}

	public int getAvg_b_calleepdd_time() {
		return avg_b_calleepdd_time;
	}

	public void setAvg_b_calleepdd_time(int avg_b_calleepdd_time) {
		this.avg_b_calleepdd_time = avg_b_calleepdd_time;
	}

	public int getAvg_acd() {
		return avg_acd;
	}

	public void setAvg_acd(int avg_acd) {
		this.avg_acd = avg_acd;
	}

	public int getAvg_a_acd() {
		return avg_a_acd;
	}

	public void setAvg_a_acd(int avg_a_acd) {
		this.avg_a_acd = avg_a_acd;
	}

	public int getAvg_b_acd() {
		return avg_b_acd;
	}

	public void setAvg_b_acd(int avg_b_acd) {
		this.avg_b_acd = avg_b_acd;
	}

	public String getCreate_time() {
		return create_time == null ? "" : create_time.trim();
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getMerchant_phone() {
		return merchant_phone;
	}

	public void setMerchant_phone(String merchant_phone) {
		this.merchant_phone = merchant_phone;
	}

	public String getBusiness_name() {
		return business_name == null ? "" : business_name.trim();
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

}
