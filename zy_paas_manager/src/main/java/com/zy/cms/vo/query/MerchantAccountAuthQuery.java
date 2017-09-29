package com.zy.cms.vo.query;

import java.util.Date;
import java.util.List;

public class MerchantAccountAuthQuery {
	
    private Long id;
    
	private String merchantPhone;//手机号码
	
	private String merchantEmail;//email
	
	private String businessName;//名称/企业名称

    private String apiAccount;

    private Integer merchantType;

    private String plName;

    private Integer plCretType;

    private String plCretNo;

    private String plCretFileurl;

    private String cyName;

    private String cyAddress;

    private String cyIndustry;

    private Integer cyCretType;

    private String cyUscc;

    private String cyRegistrNo;

    private String cyTrcNo;

    private String cyTrcFileurl;

    private String cyBlNo;

    private String cyBlFileurl;

    private Integer authStatus;

    private String authUser;

    private String authSubmitTimeStart;//审核提交时间开始时间
    
	private String authSubmitTimeEnd;//审核提交时间结束时间
	
	private String authResultTimeStart;//审核结果开始时间
	
	private String authResultTimeEnd;//审核结果结束时间

    private Date updateTime;

    private Date createTime;

    private String authDesc;
    
    private Integer pageNum;// 页号
	
	private Integer pageSize;// 每页数量
	
	private Integer pageOffset = 0;// 分页的开始值
	
	private Integer pageCount;// 统计页数
	
	private List apiAccounts;//账户绑定的客户

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public void setApiAccount(String apiAccount) {
        this.apiAccount = apiAccount == null ? null : apiAccount.trim();
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName == null ? null : plName.trim();
    }

    public Integer getPlCretType() {
        return plCretType;
    }

    public void setPlCretType(Integer plCretType) {
        this.plCretType = plCretType;
    }

    public String getPlCretNo() {
        return plCretNo;
    }

    public void setPlCretNo(String plCretNo) {
        this.plCretNo = plCretNo == null ? null : plCretNo.trim();
    }

    public String getPlCretFileurl() {
        return plCretFileurl;
    }

    public void setPlCretFileurl(String plCretFileurl) {
        this.plCretFileurl = plCretFileurl == null ? null : plCretFileurl.trim();
    }

    public String getCyName() {
        return cyName;
    }

    public void setCyName(String cyName) {
        this.cyName = cyName == null ? null : cyName.trim();
    }

    public String getCyAddress() {
        return cyAddress;
    }

    public void setCyAddress(String cyAddress) {
        this.cyAddress = cyAddress == null ? null : cyAddress.trim();
    }

    public String getCyIndustry() {
        return cyIndustry;
    }

    public void setCyIndustry(String cyIndustry) {
        this.cyIndustry = cyIndustry == null ? null : cyIndustry.trim();
    }

    public Integer getCyCretType() {
        return cyCretType;
    }

    public void setCyCretType(Integer cyCretType) {
        this.cyCretType = cyCretType;
    }

    public String getCyUscc() {
        return cyUscc;
    }

    public void setCyUscc(String cyUscc) {
        this.cyUscc = cyUscc == null ? null : cyUscc.trim();
    }

    public String getCyRegistrNo() {
        return cyRegistrNo;
    }

    public void setCyRegistrNo(String cyRegistrNo) {
        this.cyRegistrNo = cyRegistrNo == null ? null : cyRegistrNo.trim();
    }

    public String getCyTrcNo() {
        return cyTrcNo;
    }

    public void setCyTrcNo(String cyTrcNo) {
        this.cyTrcNo = cyTrcNo == null ? null : cyTrcNo.trim();
    }

    public String getCyTrcFileurl() {
        return cyTrcFileurl;
    }

    public void setCyTrcFileurl(String cyTrcFileurl) {
        this.cyTrcFileurl = cyTrcFileurl == null ? null : cyTrcFileurl.trim();
    }

    public String getCyBlNo() {
        return cyBlNo;
    }

    public void setCyBlNo(String cyBlNo) {
        this.cyBlNo = cyBlNo == null ? null : cyBlNo.trim();
    }

    public String getCyBlFileurl() {
        return cyBlFileurl;
    }

    public void setCyBlFileurl(String cyBlFileurl) {
        this.cyBlFileurl = cyBlFileurl == null ? null : cyBlFileurl.trim();
    }

    public Integer getAuthStatus() {
    	if(this.authStatus==null){
    		this.authStatus=3;
    	}
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuthUser() {
        return authUser;
    }

    public void setAuthUser(String authUser) {
        this.authUser = authUser == null ? null : authUser.trim();
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

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc == null ? null : authDesc.trim();
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

	public String getAuthSubmitTimeStart() {
		return authSubmitTimeStart;
	}

	public void setAuthSubmitTimeStart(String authSubmitTimeStart) {
		this.authSubmitTimeStart = authSubmitTimeStart;
	}

	public String getAuthSubmitTimeEnd() {
		return authSubmitTimeEnd;
	}

	public void setAuthSubmitTimeEnd(String authSubmitTimeEnd) {
		this.authSubmitTimeEnd = authSubmitTimeEnd;
	}

	public String getAuthResultTimeStart() {
		return authResultTimeStart;
	}

	public void setAuthResultTimeStart(String authResultTimeStart) {
		this.authResultTimeStart = authResultTimeStart;
	}

	public String getAuthResultTimeEnd() {
		return authResultTimeEnd;
	}

	public void setAuthResultTimeEnd(String authResultTimeEnd) {
		this.authResultTimeEnd = authResultTimeEnd;
	}

	public String getBusinessName() {
		return businessName == null ? "" : businessName.trim();
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public List getApiAccounts() {
		return apiAccounts;
	}

	public void setApiAccounts(List apiAccounts) {
		this.apiAccounts = apiAccounts;
	}
	
}