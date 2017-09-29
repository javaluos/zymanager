package com.zy.cms.vo.query;

import java.util.List;

/**
 * 短信发送查询对象
 * 
 * @author luos
 * @date 2017-2-23 11:41:18
 *
 */
public class SmsSendQuery {
	private String apiAccount;
	private String businessName;
	private String merchantPhone;
	private String channelId;
	private String channelName;
	private String receiveMobile;
	private String operator;
	private String smsContent; // 短信内容
	private String starttime;// 开始时间
	private String endtime;// 结束时间
	private String sTime;
	private String eTime;
	private String statTime;
	private String category;
	private String sortColumn; // 排序字段
	private String sortType; // 排序规则
	private String tableName; // 表名
	private String status;
	private String errorCode;// 错误码
	private String province;// 省份
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	private List apiAccounts;// 账户绑定的客户

	private String smsId;

	public String getApiAccount() {
		return apiAccount == null ? "" : apiAccount.trim();
	}

	public void setApiAccount(String apiAccount) {
		this.apiAccount = apiAccount;
	}

	public String getBusinessName() {
		return businessName == null ? "" : businessName.trim();
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantPhone() {
		return merchantPhone == null ? "" : merchantPhone.trim();
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getChannelId() {
		return channelId == null ? "" : channelId.trim();
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getReceiveMobile() {
		return receiveMobile == null ? "" : receiveMobile.trim();
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public String getOperator() {
		return operator == null ? "" : operator.trim();
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSmsContent() {
		return smsContent == null ? "" : smsContent.trim();
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public String getStatTime() {
		return statTime;
	}

	public void setStatTime(String statTime) {
		this.statTime = statTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getErrorCode() {
		return errorCode == null ? "" : errorCode.trim();
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getProvince() {
		return province == null ? "" : province.trim();
	}

	public void setProvince(String province) {
		this.province = province;
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

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

}
