package com.zy.cms.vo.query;

import java.util.Date;
import java.util.List;

public class CdrMonitorUserQuery {
	
    private Integer id;

    private String apiAccount;
    
    private String businessName;
    
    private String merchantPhone;

    private String merchantAccount;

    private Integer monitorStatus;

    private Integer noticeTimes;

    private Date updateTime;

    private Date createTime;
    
    private Integer pageNum;// 页号
    
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数

    private List apiAccounts;//账户绑定的客户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount == null ? null : merchantAccount.trim();
    }

    public Integer getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(Integer monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public Integer getNoticeTimes() {
        return noticeTimes;
    }

    public void setNoticeTimes(Integer noticeTimes) {
        this.noticeTimes = noticeTimes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List getApiAccounts() {
        return apiAccounts;
    }

    public void setApiAccounts(List apiAccounts) {
        this.apiAccounts = apiAccounts;
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

	public String getBusinessName() {
		this.businessName = businessName== null ? null : businessName.trim();
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getMerchantPhone() {
		this.merchantPhone = merchantPhone == null ? null : merchantPhone.trim();
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}
	
	

}