package com.zy.cms.vo.query;

/**
 * 策略名称
 * 
 * @author JasonXu
 * @date 2017-07-06
 */
public class SmsChannelPolicyQuery {
	
    private String policyName;//策略名称
	
	private Integer pageNum;// 页号
	
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数

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

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName==null?"":policyName.trim();
	}

}
