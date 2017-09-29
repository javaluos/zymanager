package com.zy.cms.vo;

import java.util.Date;

public class MerchantAccountAuth {
    private Long id;

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

    private Date authSubmitTime;

    private Date authResultTime;

    private Date updateTime;

    private Date createTime;

    private String authDesc;
    
    private String plCretBackurl;//身份证背面url
    
    private MerchantAccount merchantAccount;//用户详细信息

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

	public MerchantAccount getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(MerchantAccount merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getPlCretBackurl() {
		return plCretBackurl;
	}

	public void setPlCretBackurl(String plCretBackurl) {
		this.plCretBackurl = plCretBackurl;
	}
	
	
}