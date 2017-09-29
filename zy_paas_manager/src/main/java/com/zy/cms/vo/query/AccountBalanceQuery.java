package com.zy.cms.vo.query;

import java.util.List;

/**
 * 余额查询器
 * 
 * @author allen.yuan
 * @date 2016-10-08
 */
public class AccountBalanceQuery {

	private String businessname;// 企业名称
	private String merchantAccount;// 用户账号
	private String merchantphone; // 手机号码
	private String merchantemail; // 邮箱
	private double balancestart;// 余额开始范围
	private double balanceend;// 余额结束范围
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数
	private List apiAccounts;//账户绑定的客户
	
	public String getMerchantphone() {
		return merchantphone == null ? "" : merchantphone.trim();
	}

	public void setMerchantphone(String merchantphone) {
		this.merchantphone = merchantphone;
	}
	
	public String getMerchantAccount() {
		return merchantAccount == null ? "" : merchantAccount.trim();
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getMerchantemail() {
		return merchantemail == null ? "" : merchantemail.trim();
	}

	public void setMerchantemail(String merchantemail) {
		this.merchantemail = merchantemail;
	}

	public String getBusinessname() {
		return businessname == null ? "" : businessname.trim();
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public double getBalancestart() {
		return balancestart;
	}

	public void setBalancestart(double balancestart) {
		this.balancestart = balancestart;
	}

	public double getBalanceend() {
		return balanceend;
	}

	public void setBalanceend(double balanceend) {
		this.balanceend = balanceend;
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

	public Integer getPageOffset() {
		pageOffset = getPageNum() * getPageSize();
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
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

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}
	
	
}
