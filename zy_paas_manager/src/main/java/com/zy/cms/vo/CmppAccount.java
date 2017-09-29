package com.zy.cms.vo;


import java.io.Serializable;
import java.util.Date;


public class CmppAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * API_ACCOUNT
	 */
	private String apiAccount;
	
	/**
	 * APP_ID
	 */
	private String appId;
	
	/**
	 * CLIENT_ID
	 */
	private String clientId;
	
	/**
	 * PWD
	 */
	private String pwd;
	
	/**
	 * MERCHANT_ACCOUNT
	 */
	private String merchantAccount;
	
	/**
	 * REMARK
	 */
	private String remark;
	
	/**
	 * API_KEY
	 */
	private String apiKey;
	
	private String smsType;
	
	private String baseExtNumber ;//通道接入号
	
	private String startFlag ;//1开启，2关闭
	
	private Date createTime ;//创建时间
	
	private Date updateTime ;//更新时间
	
	private String defaultExtNo ;//默认扩展号
	
	private String allowSession;//连接数
	
	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public String getApiAccount(){
		return this.apiAccount;
	}

	public void setApiAccount(String apiAccount){
		this.apiAccount = apiAccount;
	}
	
	public String getAppId(){
		return this.appId;
	}

	public void setAppId(String appId){
		this.appId = appId;
	}
	
	public String getClientId(){
		return this.clientId;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}
	
	public String getPwd(){
		return this.pwd;
	}

	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	
	public String getMerchantAccount(){
		return this.merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount){
		this.merchantAccount = merchantAccount;
	}
	
	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getApiKey(){
		return this.apiKey;
	}

	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	
	public String getStartFlag() {
		return startFlag;
	}

	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
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

	public String getBaseExtNumber() {
		return baseExtNumber;
	}

	public void setBaseExtNumber(String baseExtNumber) {
		this.baseExtNumber = baseExtNumber;
	}

	public String getDefaultExtNo() {
		return defaultExtNo;
	}

	public void setDefaultExtNo(String defaultExtNo) {
		this.defaultExtNo = defaultExtNo;
	}

	public String getAllowSession() {
		return allowSession;
	}

	public void setAllowSession(String allowSession) {
		this.allowSession = allowSession;
	}
	
}