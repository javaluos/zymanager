package com.zy.cms.vo.query;

import java.util.List;

/**
 * 月结账单,查询器
 * 
 * @author allen.yuan
 * @date 2016-9-02
 */
public class CdrFeeMonthSumQuery {
	
		private String businessName;//客户名称
	
		private String merchantPhone;//客户账号

	    private Integer id;//id

	    private String dateTime;//统计时间(yyyy-MM)

	    private String apiAccount;//平台账户APIACOUNT

	    private String tablesuffix;//表名
	    
		private String dateTimeStart;//统计开始时间
		
		private String dateTimeEnd;//统计结束时间
		
		private Integer pageNum;// 页号
		
		private Integer pageSize;// 每页数量
		
		private Integer pageOffset = 0;// 分页的开始值
		
		private Integer pageCount;// 统计页数
    
		private List apiAccounts;//账户绑定的客户
		
		public String getApiAccount() {
			return (apiAccount == null) ? "" : apiAccount.trim();
		}
	
		public void setApiAccount(String apiAccount) {
			this.apiAccount = apiAccount;
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
	
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDateTime() {
			
			return (dateTime == null) ? "" : dateTime.trim();
		}

		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}

		public String getDateTimeStart() {
			if (dateTimeStart==null) {
				dateTimeStart= "";
			}
			return dateTimeStart;
		}

		public void setDateTimeStart(String dateTimeStart) {
			this.dateTimeStart = dateTimeStart;
		}

		public String getDateTimeEnd() {
			if (dateTimeEnd==null) {
				dateTimeEnd= "";
			}
			return dateTimeEnd;
		}

		public void setDateTimeEnd(String dateTimeEnd) {
			this.dateTimeEnd = dateTimeEnd;
		}

		public String getBusinessName() {
			return (businessName == null) ? "" : businessName.trim();
		}

		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		public String getMerchantPhone() {
			return (merchantPhone == null) ? "" : merchantPhone.trim();
		}

		public void setMerchantPhone(String merchantPhone) {
			this.merchantPhone = merchantPhone;
		}

		public String getTablesuffix() {
			return tablesuffix;
		}

		public void setTablesuffix(String tablesuffix) {
			this.tablesuffix = tablesuffix;
		}

		public List getApiAccounts() {
			return apiAccounts;
		}

		public void setApiAccounts(List apiAccounts) {
			this.apiAccounts = apiAccounts;
		}
}
