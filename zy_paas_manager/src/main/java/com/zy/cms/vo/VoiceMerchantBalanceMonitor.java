package com.zy.cms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class VoiceMerchantBalanceMonitor implements Serializable {

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 用户名称
	 */
	private String merchantAccount;
	
	/**
	 * API_ACCOUNT
	 */
	private String apiAccount;
	
	/**
	 * 监控最小余额，单位:分
	 */
	private Integer monitorMinBalance;
	
	/**
	 * 监控时间段
	 */
	private String monitorTimeRange;
	
	/**
	 * 报警时段设置1
	 */
	private String noticeTimeRange1;
	
	/**
	 * 报警方式设置0短信1邮箱2语音
	 */
	private String noticeWay1;
	
	/**
	 * 通知邮箱,多个邮箱号用","隔开
	 */
	private String noticeEmail1;
	
	/**
	 * 通知手机号,多个手机号用","隔开
	 */
	private String noticePhone1;
	
	/**
	 * 报警时段设置2
	 */
	private String noticeTimeRange2;
	
	/**
	 * 报警方式设置0短信1邮箱2语音
	 */
	private String noticeWay2;
	
	/**
	 * 通知邮箱,多个邮箱号用","隔开
	 */
	private String noticeEmail2;
	
	/**
	 * 通知手机号,多个手机号用","隔开
	 */
	private String noticePhone2;
	
	/**
	 * 报警时段设置3
	 */
	private String noticeTimeRange3;
	
	/**
	 * 报警方式设置0短信1邮箱2语音
	 */
	private String noticeWay3;
	
	/**
	 * 通知邮箱,多个邮箱号用","隔开
	 */
	private String noticeEmail3;
	
	/**
	 * 通知手机号,多个手机号用","隔开
	 */
	private String noticePhone3;
	
	/**
	 * 0:正常用户1 已通知
	 */
	private Integer noticeFlag;
	
	/**
	 * 0:正常用户1 已通知
	 */
	private Integer noticeTenthFlag;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 时间
	 */
	private Date createTime;
	
	
	private String merchantPhone;
	
	private String merchantEmail;
	
	private Long balance;
	
	private String noticeTimeRangeStart1;
	
	private String noticeTimeRangeEnd1;
	
	private String noticeTimeRangeStart2;
	
	private String noticeTimeRangeEnd2;
	
	private String noticeTimeRangeStart3;
	
	private String noticeTimeRangeEnd3;
	
	private String monitorMinBalanceYuan;
	
	
	private String businessName;

	private List apiAccounts;//账户绑定的客户
	
	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public String getMerchantAccount(){
		return this.merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount){
		this.merchantAccount = merchantAccount;
	}
	
	public String getApiAccount(){
		return this.apiAccount;
	}

	public void setApiAccount(String apiAccount){
		this.apiAccount = apiAccount;
	}
	
	public Integer getMonitorMinBalance(){
		return this.monitorMinBalance;
	}

	public void setMonitorMinBalance(Integer monitorMinBalance){
		this.monitorMinBalance = monitorMinBalance;
	}
	
	public String getMonitorTimeRange(){
		return this.monitorTimeRange;
	}

	public void setMonitorTimeRange(String monitorTimeRange){
		this.monitorTimeRange = monitorTimeRange;
	}
	
	public String getNoticeTimeRange1(){
		return this.noticeTimeRange1;
	}

	public void setNoticeTimeRange1(String noticeTimeRange1){
		this.noticeTimeRange1 = noticeTimeRange1;
	}
	

	
	
	public String getNoticeEmail1(){
		return this.noticeEmail1;
	}

	public void setNoticeEmail1(String noticeEmail1){
		this.noticeEmail1 = noticeEmail1;
	}
	
	public String getNoticePhone1(){
		return this.noticePhone1;
	}

	public void setNoticePhone1(String noticePhone1){
		this.noticePhone1 = noticePhone1;
	}
	
	public String getNoticeTimeRange2(){
		return this.noticeTimeRange2;
	}

	public void setNoticeTimeRange2(String noticeTimeRange2){
		this.noticeTimeRange2 = noticeTimeRange2;
	}
	
	public void setNoticeWay1(String noticeWay1){
		this.noticeWay1 = noticeWay1;
	}
	public void setNoticeWay2(String noticeWay2){
		this.noticeWay2 = noticeWay2;
	}
	public void setNoticeWay3(String noticeWay3){
		this.noticeWay3 = noticeWay3;
	}
	public String getNoticeWay1(){
		return this.noticeWay1;
	}
	public String getNoticeWay2(){
		return this.noticeWay2;
	}
	
	public String getNoticeWay3(){
		return this.noticeWay3;
	}
	
	
	public String getNoticeEmail2(){
		return this.noticeEmail2;
	}

	public void setNoticeEmail2(String noticeEmail2){
		this.noticeEmail2 = noticeEmail2;
	}
	
	public String getNoticePhone2(){
		return this.noticePhone2;
	}

	public void setNoticePhone2(String noticePhone2){
		this.noticePhone2 = noticePhone2;
	}
	
	public String getNoticeTimeRange3(){
		return this.noticeTimeRange3;
	}

	public void setNoticeTimeRange3(String noticeTimeRange3){
		this.noticeTimeRange3 = noticeTimeRange3;
	}
	
	public String getNoticeEmail3(){
		return this.noticeEmail3;
	}

	public void setNoticeEmail3(String noticeEmail3){
		this.noticeEmail3 = noticeEmail3;
	}
	
	public String getNoticePhone3(){
		return this.noticePhone3;
	}

	public void setNoticePhone3(String noticePhone3){
		this.noticePhone3 = noticePhone3;
	}
	
	public Integer getNoticeFlag(){
		return this.noticeFlag;
	}

	public void setNoticeFlag(Integer noticeFlag){
		this.noticeFlag = noticeFlag;
	}
	
	public Integer getNoticeTenthFlag(){
		return this.noticeTenthFlag;
	}

	public void setNoticeTenthFlag(Integer noticeTenthFlag){
		this.noticeTenthFlag = noticeTenthFlag;
	}
	
	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public void setNoticeTimeRangeStart1(String noticeTimeRangeStart1) {
		this.noticeTimeRangeStart1 = noticeTimeRangeStart1;
	}
	
	public void setNoticeTimeRangeEnd1(String noticeTimeRangeEnd1) {
		this.noticeTimeRangeEnd1 = noticeTimeRangeEnd1;
	}
	
	public void setNoticeTimeRangeStart2(String noticeTimeRangeStart2) {
		this.noticeTimeRangeStart2 = noticeTimeRangeStart2;
	}
	
	public void setNoticeTimeRangeEnd2(String noticeTimeRangeEnd2) {
		this.noticeTimeRangeEnd2 = noticeTimeRangeEnd2;
	}
	
	public void setNoticeTimeRangeStart3(String noticeTimeRangeStart3) {
		this.noticeTimeRangeStart3 = noticeTimeRangeStart3;
	}
	
	public void setNoticeTimeRangeEnd3(String noticeTimeRangeEnd3) {
		this.noticeTimeRangeEnd3 = noticeTimeRangeEnd3;
	}
	
	
	public String getNoticeTimeRangeStart1() {
		if((null==noticeTimeRangeStart1||"".equals(noticeTimeRangeStart1))&&(null!=noticeTimeRange1&&!"".equals(noticeTimeRange1))){
			noticeTimeRangeStart1=noticeTimeRange1.substring(0, 5);
		}
		return noticeTimeRangeStart1;
	}
	
	public String getNoticeTimeRangeStart2() {
		if((null==noticeTimeRangeStart2||"".equals(noticeTimeRangeStart2))&&(null!=noticeTimeRange2&&!"".equals(noticeTimeRange2))){
			noticeTimeRangeStart2=noticeTimeRange2.substring(0, 5);
		}
		return noticeTimeRangeStart2;
	}

	public String getNoticeTimeRangeStart3() {
		if((null==noticeTimeRangeStart3||"".equals(noticeTimeRangeStart3))&&(null!=noticeTimeRange3&&!"".equals(noticeTimeRange3))){
			noticeTimeRangeStart3=noticeTimeRange3.substring(0, 5);
		}
		return noticeTimeRangeStart3;
	}
	
	public String getNoticeTimeRangeEnd1() {
		if((null==noticeTimeRangeEnd1||"".equals(noticeTimeRangeEnd1))&&(null!=noticeTimeRange1&&!"".equals(noticeTimeRange1))){
			noticeTimeRangeEnd1=noticeTimeRange1.substring(6, noticeTimeRange1.length());
		}
		return noticeTimeRangeEnd1;
	}
	public String getNoticeTimeRangeEnd2() {
		if((null==noticeTimeRangeEnd2||"".equals(noticeTimeRangeEnd2))&&(null!=noticeTimeRange2&&!"".equals(noticeTimeRange2))){
			noticeTimeRangeEnd2=noticeTimeRange2.substring(6, noticeTimeRange2.length());
		}
		return noticeTimeRangeEnd2;
	}

	public String getNoticeTimeRangeEnd3() {
		if((null==noticeTimeRangeEnd3||"".equals(noticeTimeRangeEnd3))&&(null!=noticeTimeRange3&&!"".equals(noticeTimeRange3))){
			noticeTimeRangeEnd3=noticeTimeRange3.substring(6, noticeTimeRange3.length());
		}
		return noticeTimeRangeEnd3;
	}

	public String getMonitorMinBalanceYuan() {
		if((null==monitorMinBalanceYuan||"".equals(monitorMinBalanceYuan))&&null!=monitorMinBalance){
			double balance= new   BigDecimal(monitorMinBalance).divide(new   BigDecimal(10000), 4,BigDecimal.ROUND_HALF_UP).doubleValue();
			monitorMinBalanceYuan=balance+"";
		}
		return monitorMinBalanceYuan;
	}

	public void setMonitorMinBalanceYuan(String monitorMinBalanceYuan) {
		this.monitorMinBalanceYuan = monitorMinBalanceYuan;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}
	
	
}