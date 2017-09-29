package com.zy.cms.vo.query;

/**
 * 语音白名单查询器
 * 
 * @author luos
 * @date 2017-02-21
 */
public class VoiceWhiteListQuery {

	private String businessname;// 企业名称
	private String merchantphone; // 账号
	private String operator;// 操作人
	private String starttime;// 加入开始时间
	private String endtime;// 加入结束时间
	private Integer pageNum;// 页号
	private Integer pageSize;// 每页数量
	private Integer pageOffset = 0;// 分页的开始值
	private Integer pageCount;// 统计页数


	public String getBusinessname() {
		return businessname == null ? "" : businessname.trim();
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	
	public String getMerchantphone() {
		return merchantphone == null ? "" : merchantphone.trim();
	}

	public void setMerchantphone(String merchantphone) {
		this.merchantphone = merchantphone;
	}

	public String getOperator() {
		return operator == null ? "" : operator.trim();
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStarttime() {
		return starttime == null ? "" : starttime.trim();
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime == null ? "" : endtime.trim();
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

}
