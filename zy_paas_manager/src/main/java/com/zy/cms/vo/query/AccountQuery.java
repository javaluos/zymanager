package com.zy.cms.vo.query;

import java.util.List;
import java.util.Map;

import com.zy.cms.util.DateUtil;
import com.zy.cms.util.StringUtil;

/**
 * 账号查询器
 * 
 * @author allen.yuan
 * @date 2016-9-02
 */
public class AccountQuery {

	private String businessname;// 企业名称
	private String apiaccount; // apiaccount
	private String merchantphone; // 手机号码
	private String merchantemail; // 邮箱
	private String regstarttime; // 注册开始时间
	private String regendtime; // 注册结束时间
	private Integer chargeflag;// 充值标识
	private Integer authflag;// 认证标识
	private Integer isLocked; //账号是否可用
	private String linestarttime;// 上线开始时间
	private String lineendtime;// 上线结束时间
	private List apiAccounts;//账户绑定的客户
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数

	public String getApiaccount() {
		return apiaccount == null ? "" : apiaccount.trim();
	}

	public void setApiaccount(String apiaccount) {
		this.apiaccount = apiaccount;
	}

	public String getMerchantphone() {
		return merchantphone == null ? "" : merchantphone.trim();
	}

	public void setMerchantphone(String merchantphone) {
		this.merchantphone = merchantphone;
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

	public String getRegstarttime() {
		return regstarttime == null ? "" : regstarttime.trim();
	}

	public void setRegstarttime(String regstarttime) {
		this.regstarttime = regstarttime;
	}

	public String getRegendtime() {
		return regendtime == null ? "" : regendtime.trim();
	}

	public void setRegendtime(String regendtime) {
		this.regendtime = regendtime;
	}

	public Integer getChargeflag() {
		return chargeflag;
	}

	public void setChargeflag(Integer chargeflag) {
		this.chargeflag = chargeflag;
	}

	public Integer getAuthflag() {
		return authflag;
	}

	public void setAuthflag(Integer authflag) {
		this.authflag = authflag;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public String getLinestarttime() {
		return linestarttime == null ? "" : linestarttime.trim();
	}

	public void setLinestarttime(String linestarttime) {
		this.linestarttime = linestarttime;
	}

	public String getLineendtime() {
		return lineendtime == null ? "" : lineendtime.trim();
	}

	public void setLineendtime(String lineendtime) {
		this.lineendtime = lineendtime;
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

	public Long getRegStarttimeL() {
		if (StringUtil.isEmpty(getRegstarttime())) {
			return 0L;
		}
		return DateUtil.date2TimeStamp(getRegstarttime(), DateUtil.ISO_DATE_TIME_FORMAT) / 1000;
	}

	public Long getRegEndtimeL() {
		if (StringUtil.isEmpty(getRegendtime())) {
			return 0L;
		}
		return DateUtil.date2TimeStamp(getRegendtime(), DateUtil.ISO_DATE_TIME_FORMAT) / 1000;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}
    
}
