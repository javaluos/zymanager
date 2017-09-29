package com.zy.cms.vo.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VoiceUploadQuery implements Serializable {

	private static final long serialVersionUID = -44560301094722500L;

	private Integer id;//主键
	private String apiAccount;//api_account
	private String downloadUrl;//
	private Integer fileSize;//文件大小
	private Integer status;//状态
	private String fileName;//文件名称
	private Date createTime;//创建时间
	private String fileId;//文件ID
	private Integer voiceType;//语音类型
	private String content;//文本
	private String remark;// 备注
	private String appId;//appID
	private Date updateTime;//UPDATE_TIME
	private Integer voiceFileType;//语音文件类型
	private String tableName;
	private Integer authStatus;//审核状态 (1:审核通过,2:待审核,3:审核中,4:审核失败,5:取消审核)
	private String authDesc;// 审核原因或结果描述
	private String authUser;//审核人
	private String authSubmitTimeStart;//审核提交时间开始时间
	private String authSubmitTimeEnd;//审核提交时间结束时间
	private String authResultTimeStart;//审核结果开始时间
	private String authResultTimeEnd;//审核结果结束时间
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageCount;// 统计页数
	private Integer pageOffset = 0;// 分页的开始值
	
	private String merchantPhone;//手机号码
	private String merchantEmail;//email
	private List apiAccounts;//账户绑定的客户
	
	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public String getApiAccount(){
		return apiAccount == null ? "" : apiAccount.trim();
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
		if(this.authStatus==null){
			this.authStatus=0;
		}
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

	public Integer getPageCount() {
		if (pageCount == null) {
			pageCount = 0;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public String getAuthSubmitTimeStart() {
		return authSubmitTimeStart == null ? "" : authSubmitTimeStart.trim();
	}

	public void setAuthSubmitTimeStart(String authSubmitTimeStart) {
		this.authSubmitTimeStart = authSubmitTimeStart;
	}

	public String getAuthSubmitTimeEnd() {
		return authSubmitTimeEnd == null ? "" : authSubmitTimeEnd.trim();
	}

	public void setAuthSubmitTimeEnd(String authSubmitTimeEnd) {
		this.authSubmitTimeEnd = authSubmitTimeEnd;
	}

	public String getAuthResultTimeStart() {
		return authResultTimeStart == null ? "" : authResultTimeStart.trim();
	}

	public void setAuthResultTimeStart(String authResultTimeStart) {
		this.authResultTimeStart = authResultTimeStart;
	}

	public String getAuthResultTimeEnd() {
		return authResultTimeEnd == null ? "" : authResultTimeEnd.trim();
	}

	public void setAuthResultTimeEnd(String authResultTimeEnd) {
		this.authResultTimeEnd = authResultTimeEnd;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
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

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}
}