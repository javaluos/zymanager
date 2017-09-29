package com.zy.cms.vo;

import java.io.Serializable;
import java.util.Date;

public class VoiceUpload implements Serializable {

	private static final long serialVersionUID = -44560301094722500L;

	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * api_account
	 */
	private String apiAccount;
	
	/**
	 * 开发平台上传地址
	 */
	private String downloadUrlS;
	
	/**
	 * 下载地址
	 */
	private String downloadUrl;
	
	/**
	 * 文件大小
	 */
	private Integer fileSize;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 文件ID
	 */
	private String fileId;
	
	/**
	 * 语音类型
	 */
	private Integer voiceType;
	
	/**
	 * 文本
	 */
	private String content;
	
	/**
	 *   备注
	 */
	private String remark;
	
	/**
	 * appID
	 */
	private String appId;
	
	/**
	 * UPDATE_TIME
	 */
	private Date updateTime;
	
	/**
	 * 语音文件类型
	 */
	private Integer voiceFileType;
	
	private String tableName;
	
	private Integer authStatus;//审核状态 (1:审核通过,2:待审核,3:审核中,4:审核失败,5:取消审核)
	
	private String authDesc;// 审核原因或结果描述
	
	private String authUser;//审核人
	
	private Date authSubmitTime;//审核提交时间
	
	private Date authResultTime;//审核结果时间
	
	private String merchantPhone;//客户账号
	private String businessName; //客户名称

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
	
	public String getDownloadUrl(){
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}
	
	public Integer getFileSize(){
		return this.fileSize;
	}

	public void setFileSize(Integer fileSize){
		this.fileSize = fileSize;
	}
	
	public Integer getStatus(){
		return this.status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}
	
	public String getFileName(){
		return this.fileName;
	}

	public void setFileName(String fileName){
		this.fileName = fileName == null ? null : fileName.trim();
	}
	
	public Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public String getFileId(){
		return this.fileId;
	}

	public void setFileId(String fileId){
		this.fileId = fileId;
	}
	
	public Integer getVoiceType(){
		return this.voiceType;
	}

	public void setVoiceType(Integer voiceType){
		this.voiceType = voiceType;
	}
	
	public String getContent(){
		return this.content;
	}

	public void setContent(String content){
		this.content = content;
	}
	
	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getAppId(){
		return this.appId;
	}

	public void setAppId(String appId){
		this.appId = appId;
	}
	
	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Integer getVoiceFileType(){
		return this.voiceFileType;
	}

	public void setVoiceFileType(Integer voiceFileType){
		this.voiceFileType = voiceFileType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getAuthDesc() {
		return authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public Date getAuthSubmitTime() {
		return authSubmitTime;
	}

	public void setAuthSubmitTime(Date authSubmitTime) {
		this.authSubmitTime = authSubmitTime;
	}

	public Date getAuthResultTime() {
		return authResultTime;
	}

	public void setAuthResultTime(Date authResultTime) {
		this.authResultTime = authResultTime;
	}

	public String getDownloadUrlS() {
		if(this.downloadUrlS==null){
			downloadUrlS="";
		}
		return downloadUrlS;
	}

	public void setDownloadUrlS(String downloadUrlS) {
		this.downloadUrlS = downloadUrlS;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
}